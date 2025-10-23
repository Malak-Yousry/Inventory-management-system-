/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package students;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Hannah Emad
 */
public class Deletestudent {
    private String filename;

 
    public Deletestudent (String filename) {
        this.filename = filename;
    }
 
    public boolean deleteStudentById(int idToDelete) throws FileNotFoundException, IOException {
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");
        boolean deleted = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);

                if (id == idToDelete) {
                    deleted = true;
                    continue;
                }

                writer.write(line);
                writer.newLine();
            }

        if (deleted) {
            inputFile.delete();
            tempFile.renameTo(inputFile);
        } else {
            tempFile.delete();
        }

        return deleted;
        }
    }
   