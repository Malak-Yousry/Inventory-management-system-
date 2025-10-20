package pack;
//Employee User class
public class EmployeeUser implements Record{
    private String employeeID;
    private String name;
    private String email;
    private String address;
    private String phoneNumber;
    
   public  EmployeeUser(String employeeID, String name, String email, String address,String phoneNumber){
        this.employeeID = employeeID;
        this.name = name;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;

    }
    public String lineRepresentation(){
        return employeeID + "," + name + "," + email + "," + address + "," + phoneNumber;
    }
   public String getSearchKey() {
    return employeeID;
       
   }
}
