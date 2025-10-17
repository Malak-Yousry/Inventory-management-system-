package pack;
import java.io.File;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class CustomerProductDatabase {
	private ArrayList<CustomerProduct> records;
	private String filename;
	
	// constructor
	public CustomerProductDatabase(String filename) {
		
		this.filename = filename;
		this.records=new ArrayList<>();
	}
	// methods
	 public void readFromFile() {
		 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 records=new ArrayList<>();
		try(Scanner read = new Scanner(new File(filename))){
		
			while(read.hasNextLine()) {
				String info = read.nextLine().trim();
				if(info.isEmpty())continue;
				String[] parts = info.split(",");
				int j=0;
				String customerSSN = parts[j++];
				String productID= parts[j++];
				LocalDate purchaseDate=LocalDate.parse(parts[j++],formater);
				boolean paid=Boolean.parseBoolean(parts[j++]);
				
				CustomerProduct record=new CustomerProduct(customerSSN,productID,purchaseDate,paid);
	            records.add(record);
				}}
			catch(FileNotFoundException e) {
				System.out.println("An error occurred");
				e.printStackTrace();
			}
	
	}
	 public CustomerProduct createRecordFrom(String line) {
		 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 String[] parts = line.split(",");
			int j=0;
			String customerSSN = parts[j++];
			String productID= parts[j++];
			LocalDate purchaseDate=LocalDate.parse(parts[j++],formater);
			boolean paid=Boolean.parseBoolean(parts[j++]);
			CustomerProduct customer= new CustomerProduct(customerSSN,productID,purchaseDate,paid);
			return customer;
	 }
	 public ArrayList<CustomerProduct> returnAllRecords(){
		 return records;
		 
	 }
	 public boolean contains(String key ){
		 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 for(int i=0;i<records.size();i++) {
			 String current=records.getCustomerSSN()+","+
		                    records.getProductID()+","+
					        records.getPurchaseDate().format(formater);
			 if(key.equals(current))
				 return true;
		 }
		 return false;
		 
	 }
	 public CustomerProduct getRecord(String key) {
		 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 for(int i=0;i<records.size();i++) {
			 String current=records.getCustomerSSN()+","+
		                    records.getProductID()+","+
					        records.getPurchaseDate().format(formater);
			 if(key.equals(current)) {
				 
		        return records;
			 }}
		 return null;
	 }
	 public void insertRecord(CustomerProduct record) {
		 records.add(record);
	 }
	 public void deleteRecord(String key) {
		
			 CustomerProduct temp = getRecord(key);
		    if(temp!=null)
		    	records.remove(temp);
	 }
	 public void saveToFile() {
		 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		try(FileWriter writer = new FileWriter(new File(filename))){
			for(int i=0;i<records.size();i++) {
			writer.write("\n" + records.get(i).getCustomerSSN() + "," + 
					records.get(i).getProductID() + "," +
					records.get(i).getPurchaseDate().format(formater)+
					"," + records.get(i).isPaid());
					
					
		}}
		catch(IOException e) {
			System.out.println("An error occurred");
			e.printStackTrace();
		}
	}
}
