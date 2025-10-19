/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaapplication6;

/**
 *
 * @author Hannah Emad
 */
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

    private static class EmployeeUserDatabase {

        public EmployeeUserDatabase() {
        }
    }
}
