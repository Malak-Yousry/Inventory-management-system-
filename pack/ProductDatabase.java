package pack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ProductDatabase {
    private ArrayList<Product> records;
    private String fileName;

    ProductDatabase (String filename){
        records = new ArrayList<>();
        fileName = filename;
    }
    public void readFromFile(){
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] productData = line.split(",");
                //EmployeeId, Name, Email, Address, PhoneNumber;
                Product product = new Product(productData[0],productData[1],productData[2],productData[3],Integer.parseInt(productData[4]),Float.parseFloat(productData[5]));
                records.add(product);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    Product createRecordFrom(String line){
        String[] productData = line.split(",");
        //productID, productName, manufacturerName, supplierName,quantity, price)
        Product product = new Product(productData[0],productData[1],productData[2],productData[3],Integer.parseInt(productData[4]),Float.parseFloat(productData[5]));
        return product;
    }
    public ArrayList<Product> returnAllRecords(){
        return records;
    }
    public boolean contains(String key ){
        for(int i = 0; i < records.size(); i++){
            Product product = records.get(i);
            if((product.getSearchKey()).equals(key))
                return true;
        }
        return false;
    }
    public Product getRecord(String key){
        for(int i = 0; i < records.size(); i++){
            Product product = records.get(i);
            if((product.getSearchKey()).equals(key))
                return product;
        }
        return null;
        //TODO: IN THE MAIN IF THE RETURN IS NULL SO THE KEY NOT CONTAIN
        //TODO: ALSO IN THE EMPLOYEEDATABASE
    }
    
    public void insertRecord(Product record){
        records.add(record);
    }
    public void deleteRecord(String key){
        for(int i = 0; i < records.size(); i++){
            Product product = records.get(i);
            if((product.getSearchKey()).equals(key)){
                records.remove(product);
                break;
            }
        }
    }
    public void saveToFile(){
        try {
            FileWriter file = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(file);
            for(int i = 0; i < records.size(); i++){
                String line = records.get(i).lineRepresentation();
                writer.write(line);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

