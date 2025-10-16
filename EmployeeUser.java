public class EmployeeUser {
    private String EmployeeId, Name, Email, Address, PhoneNumber;
    
    EmployeeUser(String employeeId, String name, String email, String address,String phoneNumber){
        EmployeeId = employeeId;
        Name = name;
        Email = email;
        Address = address;
        PhoneNumber = phoneNumber;

    }
    public String lineRepresentation(){
        return EmployeeId + "," + Name + "," + Email + "," + Address + "," + PhoneNumber;
    }
    public String getSearchKey(){
        return EmployeeId;
    }

}
