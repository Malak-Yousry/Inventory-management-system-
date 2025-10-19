package pack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeUserDatabase extends Database<EmployeeUser> {
    private ArrayList<EmployeeUser> records;
    private String fileName;

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
