package pack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Record{
	//Attributes
   private String CustomerSSN;
   private String productID;
   private LocalDate purchaseDate;
   private boolean paid;
   
//Constructor
   public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
	CustomerSSN = customerSSN;
	this.productID = productID;
	this.purchaseDate = purchaseDate;
	this.paid = false;
   }
   // A method that returns CustomerSSN
   public String getCustomerSSN() {
	return CustomerSSN;
   }
   // A method that returns ProductID
   public String getProductID() {
	return productID;
   }
   //A method that returns the purchase date
   public LocalDate getPurchaseDate() {
	return purchaseDate;
   }
    //A method that returns information comma separeted
   public String lineRepresentation() {
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  return CustomerSSN + "," + productID + "," + purchaseDate.format(formatter) + "," + paid;
	  }
//A method that returns the status of payement
   public boolean isPaid() {
	return paid;
}
//A method that sets the status of payement
   public void setPaid(boolean paid) {
	this.paid = paid;
   }
   //A method that returns the key
   public String getSearchKey() {
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  return CustomerSSN + "," + productID + "," + purchaseDate.format(formatter);
	  }
	  //A method that stores information in file
   public void storeInFile() {
	   try {FileWriter writeFile = new FileWriter(new File("CustomersProduct.txt"),true);
	   writeFile.write(lineRepresentation() + "\n");
	   writeFile.close();
	   }catch(IOException e) {
		   System.out.println("Error!!");
		   e.printStackTrace();
	   }
   }   
}
