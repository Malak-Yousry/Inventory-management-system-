package pack;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct {
   private String CustomerSSN;
   private String productID;
   private LocalDate purchaseDate;
   private boolean paid;
   

   public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate) {
	CustomerSSN = customerSSN;
	this.productID = productID;
	this.purchaseDate = purchaseDate;
   }
   public String getCustomerSSN() {
	return CustomerSSN;
   }
   public String getProductID() {
	return productID;
   }
   public LocalDate getPurchaseDate() {
	return purchaseDate;
   }
   public String lineRepresentation() {
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  return CustomerSSN + "," + productID + "," + purchaseDate.format(formatter) + "," + paid;
	  }

   public boolean isPaid() {
	return paid;
}
   public void setPaid(boolean paid) {
	this.paid = paid;
   }
   public String getSearchKey() {
	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		  return CustomerSSN + "," + generateID() + "," + purchaseDate.format(formatter);
	  }
   public void storeInFile() {
	   try {FileWriter writeFile = new FileWriter(new File("CustomersProduct.txt"),true);
	   writeFile.write(lineRepresentation() + "\n");
	   writeFile.close();
	   }catch(IOException e) {
		   System.out.println("Error!!");
		   e.printStackTrace();
	   }
   }
   public String generateID() {
	   return "P" + Long.toString(System.currentTimeMillis()%10000);
	   
   }
   
}
