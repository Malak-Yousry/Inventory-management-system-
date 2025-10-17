package pack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Product {
   private String productID;
   private String productName;
   private String manufacturerName;
   private String supplierName;
   private int quantity;
   private float price;
   
   
   public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity,
		float price) {
	this.productID = productID;
	this.productName = productName;
	this.manufacturerName = manufacturerName;
	this.supplierName = supplierName;
	this.quantity = quantity;
	this.price = price;
   }

   public int getQuantity() {
	return quantity;
   }
 public float getPrice() {
	 return price;
   }
   public void setQuantity(int quantity) {
	this.quantity = quantity;
   }

   public void sellProduct() {
	   if (quantity>0)
		   quantity--;
   }
   public void returnProduct() {
	   quantity++;
   }
  public String lineRepresentation() {
	  return productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price;
  }
  public String getSearchKey() {
	  return productID;
  }
   public void storeInFile() {
	   try {FileWriter writeFile = new FileWriter(new File("Product.txt"),true);
	   writeFile.write(lineRepresentation() + "\n");
	   writeFile.close();
	   }catch(IOException e) {
		   System.out.println("Error!!");
		   e.printStackTrace();
	   }
   }

   
}
