package pack;

public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole() {
        database = new EmployeeUserDatabase();
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
        database.addEmployee(newEmployee);
    }

    public EmployeeUser[] getListOfEmployees() {
        return database.getAllEmployees();
    }

    public void removeEmployee(String key) {
        database.removeEmployeeById(key);
    }

    public void logout() {
        database.saveToFile();
    }
}