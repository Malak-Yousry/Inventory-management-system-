package pack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Product implements Record{
   //Attributes
   private String productID;
   private String productName;
   private String manufacturerName;
   private String supplierName;
   private int quantity;
   private float price;
   
   //Constructor
   //Constructor that sets the values
   public Product(String productID, String productName, String manufacturerName, String supplierName, int quantity,
		float price) {
	this.productID = productID;
	this.productName = productName;
	this.manufacturerName = manufacturerName;
	this.supplierName = supplierName;
	this.quantity = quantity;
	this.price = price;
   }
   //Methods
   //A method that returns the quantity
   public int getQuantity() {
	return quantity;
   }
   //A method that returns the price
 public float getPrice() {
	 return price;
   }
   //A method that sets the price
    public void setPrice(float price) {
	 this.price = price;
   }
     //A method that sets the quantity
   public void setQuantity(int quantity) {
	this.quantity = quantity;
   }
  //A method that returns information comma separeted
  public String lineRepresentation() {
	  return productID + "," + productName + "," + manufacturerName + "," + supplierName + "," + quantity + "," + price;
  }
  //A method that returns the ProductID
  public String getSearchKey() {
	  return productID;
  }
  //A method that stores information in file
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
