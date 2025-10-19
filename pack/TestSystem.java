package pack;
import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class TestSystem{
    public static void main(String[] args) {
    Scanner Scan = new Scanner(System.in);
    menu1();
    System.out.println("Do you want another operation??");
    System.out.print("Your Choice[Yes or NO]: ");
    String operation = Scan.next();

//A loop that ends when the user wants to close the system
    while(operation.equalsIgnoreCase("Yes")){
     menu1();
     System.out.println("Do you want another operation??");
     System.out.print("Your Choice[Yes or NO]: ");
     operation = Scan.next();
    }
    System.out.println("Thank You!!");
    System.exit(0);
  }
  //A fuction that generates a new EmployeeID
public static String generateNewEmployeeID(){
    return "E" + Long.toString(System.currentTimeMillis()%10000);
 }
   //A fuction that generates a new ProductID
 public static String generateNewProductID(){
    return "P" + Long.toString(System.currentTimeMillis()%10000);
 }
   //A fuction that generates a new CustomerSSN
 public static String generateCustomerSSN(){
    return Long.toString(System.currentTimeMillis());
 }
public static void menu1(){ 
    Scanner Scan = new Scanner(System.in);
    System.out.println("Choose one of the following: ");
    System.out.println("1)Admin \n2)Employee \n3)Exit");
    System.out.print("Your Choice[1 or 2 or 3]: ");
    int choice = Scan.nextInt();
    menu2(choice);
 }
public static void menu2(int choice){
    Scanner Scan = new Scanner(System.in);
       switch (choice) {
        case 1:
            {
                AdminRole admin = new AdminRole();
                adminMenu( admin);
            break;
            }
       case 2:
           {
            EmployeeRole employee = new EmployeeRole();
            employeeMenu( employee);
            break;
           }
        case 3:
              System.out.println("Thank You!!");
              System.exit(0);
              break;

        default:
            System.out.println("Incorrect Choice!!");
    } 
 }
 public static void adminMenu(AdminRole admin){
    Scanner Scan = new Scanner(System.in);
     System.out.println("Choose one of the following: ");
     System.out.println("1)Add Employee.\n2)Get List Of Employees \n3)Remove Employee \n4)Log Out");
     System.out.print("Your Choice[1 or 2 or 3 or 4]: ");
     int choice = Scan.nextInt();
     switch(choice){
        case 1: 
             {
            System.out.println(".....Adding Employee .....");
            System.out.print("Enter Employee name: ");
            Scan.nextLine();
            String name = Scan.nextLine();
            System.out.print("Enter Employee E-mail: ");
            String email = Scan.next();
              int k = 1;
              //checks that the email formate is correct
            while(!email.contains("@gmail.com") && !email.contains("@yahoo.com")){
                if(k<3){
                    System.out.println("Incorrect E-mail Format!!");
                    System.out.println("Try Again!![You have " + (3-k) + " TRIALS!!]");
                    System.out.print("Enter Employee E-mail: ");
                    email = Scan.next();
                    k++;
                    }
                    else {
                    System.out.println("No more Trials!! Try Again Later!!");
                    System.exit(0);
                   }
            }
             System.out.print("Enter Employee Address: ");
            String Address = Scan.next();
            System.out.print("Enter Employee Phone Number: ");
             String phoneNumber = Scan.next();
             int i = 1;
             //checks that the phone number formate is correct
                while(phoneNumber.length()!=11){
                    if(i<3){
                    System.out.println("Incorrect Phone Number!! (NOT 11 DIGITS)");
                    System.out.println("Try Again!![You have " + (3-i) + " TRIALS!!]");
                    System.out.print("Enter Employee Phone Number: ");
                    phoneNumber = Scan.next();
                    i++;}
                    else {
                    System.out.println("No more Trials!! Try Again Later!!");
                    System.exit(0);}
                }
                String employeeID = generateNewEmployeeID();
                System.out.println("Your Employee ID:" + employeeID );
                admin.addEmployee(employeeID, name, email, Address, phoneNumber);
            break;
            }
    case 2:
        {
            System.out.println(".....Employee List.....");
            for(EmployeeUser e: admin.getListOfEmployees())
            System.out.println(e.lineRepresentation());
            break;
        }
    case 3:{
            System.out.println(".....Removing Employee .....");
             System.out.print("Enter EmployeeID to be removed: ");
             String removeID = Scan.next();
             admin.removeEmployee(removeID);
             break;
           }   
    case 4: {
        System.out.println(".....Logging Out.....");
        admin.logout();
        break;
    }
    default:
        System.out.println("Incorrect Choice!!");
 }
}
 public static void employeeMenu(EmployeeRole employee){
    Scanner Scan = new Scanner(System.in);
         System.out.println("Choose one of the following: ");
     System.out.println("1)Add Product.\n2)Purchase Product. \n3)Remove Product. \n4)Log Out");
         System.out.print("Your Choice[1 or 2 or 3 or 4]: ");
            int choice = Scan.nextInt();

      switch(choice){
        case 1:
            {
                 System.out.println(".....Adding Product.....");
                 System.out.print("Enter Product Name: ");
                 String productName = Scan.next();
                 System.out.print("Enter Manufacturer Name: ");
                 String manufacturerName = Scan.next();
                 System.out.print("Enter Supplier Name: ");
                 String  supplierName = Scan.next();
                 System.out.print("Enter the Quantity: ");
                 int quantity = Scan.nextInt();
                 System.out.print("Enter the Price: ");
                 float price = Scan.nextFloat();
                 String productID = generateNewProductID();
                 employee.addProduct(productID, productName, manufacturerName, supplierName, quantity);
                 employee.updatePrice(productID,price);
                 break;
            }
        case 2:
            {
                 System.out.println(".....Purchasing Product.....");
                 String customerSSN = generateCustomerSSN();
                 System.out.print("Enter ProductID: ");
                 String productID = Scan.next();
                 LocalDate  today = LocalDate.now();
                if(employee.purchaseProduct(customerSSN, productID, today)){
                  System.out.println("Do You Want To Pay now??");
                    System.out.print("Yes or No: ");
                    String payement = Scan.next();
                 if(payement.equalsIgnoreCase("Yes"))
                     employee.applyPayment(customerSSN, today);
                
            }
                
                 break; 
            }    
        case 3:
            {
                 System.out.println(".....Removing Product.....");
                 System.out.println("Enter CustomerSSN: ");
                 String customerSSN = Scan.next();
                 System.out.print("Enter ProductID: ");
                 String productID = Scan.next();
                 System.out.print("Enter Purchase Date[dd-MM-yyyy]: ");
                 String purchase = Scan.next();
                 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                 LocalDate purchaseDate = LocalDate.parse(purchase,formatter);
                 LocalDate  today = LocalDate.now();

                 double price = employee.returnProduct(customerSSN,productID,purchaseDate,today);
                 
                 if(price != -1.0)   
                    System.out.println("Product Price: " + price);

                 else 
                 System.out.println("Incorrect Information!!");   

                 break;
            }   
        case 4: {
            System.out.println(".....Logging Out.....");
        employee.logout();
        break;
         } 
    default:
        System.out.println("Incorrect Choice!!");
      }      
}
}