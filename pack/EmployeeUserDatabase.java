package pack;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EmployeeUserDatabase {
    private ArrayList<EmployeeUser> records;
    private String fileName;

    EmployeeUserDatabase(String name){
        records = new ArrayList<>();
        fileName = name;
    }
    void readFromFile(){
        //open the file
        try {
            FileReader file = new FileReader(fileName);
            BufferedReader reader = new BufferedReader(file);
            String line;
            while ((line = reader.readLine()) != null) {
                String[] employeeData = line.split(",");
                //EmployeeId, Name, Email, Address, PhoneNumber;
                EmployeeUser employee = new EmployeeUser(employeeData[0],employeeData[1],employeeData[2],employeeData[3],employeeData[4]);
                records.add(employee);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public EmployeeUser createRecordFrom(String line){
        //EmployeeId, Name, Email, Address, PhoneNumber;
        String[] employeeData = line.split(",");
        EmployeeUser employee = new EmployeeUser(employeeData[0],employeeData[1],employeeData[2],employeeData[3],employeeData[4]);
        return employee;
    }
    ArrayList<EmployeeUser> returnAllRecords(){
        return records;
    }
    public boolean contains(String key){
        for(int i = 0; i < records.size(); i++){
            EmployeeUser employee = records.get(i);
            String employeeId = employee.getSearchKey();
            if(employeeId.equals(key))
                return true;
        }
        return false;
    }
    public EmployeeUser getRecord(String key){
        for(int i = 0; i < records.size(); i++){
            EmployeeUser employee = records.get(i);
            String employeeId = employee.getSearchKey();
            if(employeeId.equals(key))
                return records.get(i);
        }
        return null;
    }
    public void insertRecord(EmployeeUser record){
        records.add(record);
    }
    public void deleteRecord(String key){
        for(int i = 0; i < records.size(); i++){
            EmployeeUser e = records.get(i);
            if((e.getSearchKey()).equals(key))
                records.remove(i);
        }
    }
    public void saveToFile(){
        try {
            FileWriter file = new FileWriter(fileName);
            BufferedWriter writer = new BufferedWriter(file);
            for(int i = 0; i < records.size(); i++){
                String line = records.get(i).lineRepresentation();
                writer.write(line);
                writer.write("\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
