package pack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class ProductDatabase extends Database<Product> {
    
    ProductDatabase (String filename){
        super(filename);
    }
@Override
    Product createRecordFrom(String line){
        String[] productData = line.split(",");
        //productID, productName, manufacturerName, supplierName,quantity, price)
        Product product = new Product(productData[0],productData[1],productData[2],productData[3],Integer.parseInt(productData[4]),Float.parseFloat(productData[5]));
        return product;
    }
 
}

