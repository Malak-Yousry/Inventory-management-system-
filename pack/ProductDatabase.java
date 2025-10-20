package pack;
public class ProductDatabase extends Database<Product> {
    
    ProductDatabase (String filename){
        super(filename);
    }
@Override
    public Product createRecordFrom(String line){
        String[] productData = line.split(",");
        //productID, productName, manufacturerName, supplierName,quantity, price)
        Product product = new Product(productData[0],productData[1],productData[2],productData[3],Integer.parseInt(productData[4]),Float.parseFloat(productData[5]));
        return product;
    }
 
}

