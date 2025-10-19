package pack;
import java.util.ArrayList;
public class AdminRole implements Logout{
    // attributes
    private EmployeeUserDatabase database;
 
    public AdminRole() { 
        database=new EmployeeUserDatabase("Employees.txt");
        database.readFromFile();
    }
    public void addEmployee(String employeeId,String name,String email,String address,String phoneNumber) {
        EmployeeUser newEmp=new EmployeeUser(employeeId,name,email,address,phoneNumber);
        if(!database.contains(employeeId)) {
            database.insertRecord(newEmp);
            database.saveToFile();}
        else
            System.out.println("Employee already exists!");
        
    }
    public EmployeeUser[] getListOfEmployees() {
        ArrayList<EmployeeUser> list = database.returnAllRecords();
        return list.toArray(new EmployeeUser[list.size()]); 
    }
    public void removeEmployee(String key) {
        if(database.contains(key)){
            database.deleteRecord(key);
        database.saveToFile();}
        else 
            System.out.println("Employee not found!");
    }
    public void logout() {
        database.saveToFile();
    }
  
}