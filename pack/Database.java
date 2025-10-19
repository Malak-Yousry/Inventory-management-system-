
package pack;
import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Database<T extends Record>{
    // attributes should be protected
    protected ArrayList<T> records;
    protected String filename;

public Database(String filename){
    this.filename = filename;
	this.records=new ArrayList<>();
}
	 public void readFromFile() {
		 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 records=new ArrayList<>();
		try(Scanner read = new Scanner(new File(filename))){
		
			while(read.hasNextLine()) {
				String info = read.nextLine().trim();
				if(info.isEmpty())continue;
				T record=createRecordFrom(info);
	            records.add((T) record);
				}}
			catch(FileNotFoundException e) {
				System.out.println("An error occurred");
				e.printStackTrace();
			}
	
	}
    	 public void saveToFile() {
		 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try(FileWriter writer = new FileWriter(new File(filename))){
			for(int i=0;i<records.size();i++) {
			writer.write(records.get(i).lineRepresentation() + "\n");
			
		}}
		catch(IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
     public void insertRecord(T record) {
		 records.add(record);
	 }
     	 public void deleteRecord(String key) {
		
		 T temp = (T) getRecord(key);
		    if(temp!=null)
		    	records.remove(temp);
	 }
	 public T getRecord(String key) {
	
		 for(int i=0;i<records.size();i++) {
			 String current=records.get(i).getSearchKey();
			 if(key.equals(current)) {
		        return records.get(i);
			 }}
		 return null;
	 }
 public abstract T createRecordFrom(String line);
public ArrayList<T> returnAllRecords(){
		 return records;
		 
	 }
      public boolean contains(String key ){
		
	for(int i=0;i<records.size();i++) {
			 String current=records.get(i).getSearchKey();
			 if(key.equals(current)) {
		    return true;
			 }}
		 return false; 
	 }
	
}