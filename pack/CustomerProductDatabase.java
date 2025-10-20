package pack;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProductDatabase extends Database<CustomerProduct>{
	
	// constructor
	public CustomerProductDatabase(String filename) {
		super(filename);
	}
	// methods
	@Override
	 public CustomerProduct createRecordFrom(String line) {
		 DateTimeFormatter formater=DateTimeFormatter.ofPattern("dd-MM-yyyy");
		 String[] parts = line.split(",");
			int j=0;
			String customerSSN = parts[j++];
			String productID= parts[j++];
			LocalDate purchaseDate=LocalDate.parse(parts[j++],formater);
			boolean paid=Boolean.parseBoolean(parts[j++]);
			CustomerProduct customer= new CustomerProduct(customerSSN,productID,purchaseDate);
			customer.setPaid(paid);
			return customer;
	 }
	 

}
