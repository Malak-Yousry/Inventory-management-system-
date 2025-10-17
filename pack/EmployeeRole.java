package pack;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
public class EmployeeRole {
	// attributes
	private ProductDatabase productsDatabase;
	private CustomerProductDatabase customerProductDatabase;
	
	// constructor
	public EmployeeRole() {
		
	}
	
	// methods
	public void addProduct(String productID, String productName ,String manufacturerName , String supplierName , int quantity) {
		Product product =productsDatabase.getRecord(productID);
		float price;
		if(product!=null) {
		price = product.getPrice();
		Product newProduct=new Product(productID,productName,manufacturerName,supplierName,quantity,price);
		productsDatabase.insertRecord(newProduct);
			productsDatabase.saveToFile();
	}
}
	public Product[] getListOfProducts() {
		ArrayList<Product> list = productsDatabase.returnAllRecords();
		return list.toArray(new Product[list.size()]);
	}

	public CustomerProduct[] getListOfPurchasingOperations() {
	ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();
		return list.toArray(new CustomerProduct[list.size()]);
	}
	
	public boolean purchaseProduct(String customerSSN, String 
			productID, LocalDate purchaseDate) {
		Product[] products= getListOfProducts();
		for(int i=0;i<products.length;i++) {
			if(products[i].getSearchKey().equals(productID)) {
				if(products[i].getQuantity()==0){
					return false;}
			}
			
				products[i].setQuantity(products[i].getQuantity()-1);
				CustomerProduct newProduct = new CustomerProduct(customerSSN,productID,purchaseDate);
				customerProductDatabase.insertRecord(newProduct);
				customerProductDatabase.saveToFile();

				productsDatabase.deleteRecord(productID);
				productsDatabase.insertRecord(products[i]);
				productsDatabase.saveToFile();
				return true;
			
		}
		return false;
		
	}
	public double returnProduct(String customerSSN, String productID, 
			LocalDate purchaseDate ,LocalDate returnDate) {
		if(returnDate.isBefore(purchaseDate))return -1.0;
		else if(returnDate.isAfter(purchaseDate.plusDays(14)))return -1.0;
			 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
			 String key=customerSSN + "," + productID + "," +purchaseDate.format(formater);
		if (!customerProductDatabase.contains(key)) return -1.0;

			Product[] products= getListOfProducts();
			Product flag=null;
		for(int i=0;i<products.length;i++) {
			if(products[i].getSearchKey().equals(productID)) {
				flag=products[i];
				break;
			}
		}
		if (flag==null)return -1.0;

		flag.setQuantity(flag.getQuantity()+1);
			customerProductDatabase.deleteRecord(key);
			customerProductDatabase.saveToFile();
		    productsDatabase.deleteRecord(productID);
			productsDatabase.insertRecord(flag);
			productsDatabase.saveToFile();
				return flag.getPrice();
			
		}	
	public boolean applyPayment(String customerSSN, LocalDate  
purchaseDate){
	 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
	 	String key=customerSSN  + "," +purchaseDate.format(formater)+","+false;
    CustomerProduct customer =customerProductDatabase.getRecord(key);

if(customer == null){
	return false;
}
	
	if(customer.isPaid())
	      return false;
	customerProductDatabase.deleteRecord(key);
	customerProductDatabase.insertRecord(new CustomerProduct(customer.getCustomerSSN(),customer.getProductID() ,purchaseDate));
	customerProductDatabase.saveToFile();
	return true;
}
public void logout(){
	productsDatabase.saveToFile();
	customerProductDatabase.saveToFile();
}



}

	


	
	

