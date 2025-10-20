package pack;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
public class EmployeeRole implements Logout{
	// attributes
	private ProductDatabase productsDatabase;
	private CustomerProductDatabase customerProductDatabase;
	
	// constructor
	public EmployeeRole() {
		productsDatabase = new ProductDatabase("Product.txt");
		customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
		productsDatabase.readFromFile();
		customerProductDatabase.readFromFile();
	}
	
	// methods
	public void addProduct(String productID, String productName ,String manufacturerName , String supplierName , int quantity) {
		Product product =productsDatabase.getRecord(productID);
		
		if(product==null) {
		float price=0.0f;
		Product newProduct=new Product(productID,productName,manufacturerName,supplierName,quantity,price);
		productsDatabase.insertRecord(newProduct);
			productsDatabase.saveToFile();
	}
	else 
	System.out.println("Product Found!!");
}
	public Product[] getListOfProducts() {
		ArrayList<Product> list = productsDatabase.returnAllRecords();
		return list.toArray(new Product[list.size()]);
	}

	public CustomerProduct[] getListOfPurchasingOperations() {
	ArrayList<CustomerProduct> list = customerProductDatabase.returnAllRecords();
		return list.toArray(new CustomerProduct[list.size()]);
	}
	public void updatePrice(String productID,float price){
	Product product =productsDatabase.getRecord(productID);
	if(product!=null){
		product.setPrice(price);
		productsDatabase.deleteRecord(productID);
		productsDatabase.insertRecord(product);
		productsDatabase.saveToFile();

	}
	}
	public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
		Product[] products= getListOfProducts();
		for(int i=0;i<products.length;i++) {
			if(products[i].getSearchKey().equals(productID)) {
				if(products[i].getQuantity()==0){
					System.out.println("Quantity equals to zero!");
					return false;}

			
			
				products[i].setQuantity(products[i].getQuantity()-1);
				CustomerProduct newProduct = new CustomerProduct(customerSSN,productID,purchaseDate);
				customerProductDatabase.insertRecord(newProduct);
				customerProductDatabase.saveToFile();
				productsDatabase.deleteRecord(productID);
				productsDatabase.insertRecord(products[i]);
				productsDatabase.saveToFile();
                System.out.println("Purchase Done Successfully!!");
				return true;
			}
			
		}
		System.out.println("Product not found");
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
	public boolean applyPayment(String customerSSN, LocalDate purchaseDate){
DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
boolean update = false;

CustomerProduct[] cP = this.getListOfPurchasingOperations();   
for(int i = 0; i < cP.length; i++){          
	  if((cP[i].getCustomerSSN().equals(customerSSN) && cP[i].getPurchaseDate().equals(purchaseDate)) && !(cP[i].isPaid())){
		cP[i].setPaid(true);
String key = cP[i].getCustomerSSN() + "," + cP[i].getProductID() + "," + (cP[i].getPurchaseDate()).format(formater);             
   customerProductDatabase.deleteRecord(key);                               
customerProductDatabase.insertRecord(cP[i]);
update= true;               
        }      
		  } 
		 if(update)
		 customerProductDatabase.saveToFile();
 return update;
			}
public void logout(){
	productsDatabase.saveToFile();
	customerProductDatabase.saveToFile();
}


}

	


	
	

