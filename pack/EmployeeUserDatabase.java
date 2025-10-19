package pack;

public class EmployeeUserDatabase extends Database<EmployeeUser> {
    EmployeeUserDatabase(String name){
        super(name);
    }
@Override
    public EmployeeUser createRecordFrom(String line){
        //EmployeeId, Name, Email, Address, PhoneNumber;
        String[] employeeData = line.split(",");
        EmployeeUser employee = new EmployeeUser(employeeData[0],employeeData[1],employeeData[2],employeeData[3],employeeData[4]);
        return employee;
    }

}
