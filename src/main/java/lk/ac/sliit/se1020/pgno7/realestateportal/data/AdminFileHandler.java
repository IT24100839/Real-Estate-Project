package lk.ac.sliit.se1020.pgno7.realestateportal.data;

import lk.ac.sliit.se1020.pgno7.realestateportal.model.Admin;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Iterator; // Import Iterator

// This class handles reading and writing Admin data to a file
public class AdminFileHandler {

    private static final String FILENAME = "admins.txt";
    private static final String DATA_DIR = "data";
    private static final String FILE_PATH = DATA_DIR + File.separator + FILENAME;

    // Method to read all admin records from the file
    public List<Admin> readAdminsFromFile() {
        List<Admin> admins = new ArrayList<>();

        File dataFile = new File(FILE_PATH);

        if (!dataFile.exists()) {
            System.out.println("Admin data file not found at " + dataFile.getAbsolutePath() + ". Returning empty list.");
            return admins;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                Admin admin = Admin.fromFileString(line);
                if (admin != null) {
                    admins.add(admin);
                }
            }
            System.out.println("Successfully read " + admins.size() + " admin records from " + dataFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error reading admin data from file: " + dataFile.getAbsolutePath() + " - " + e.getMessage());
            e.printStackTrace();
        }

        return admins;
    }

    // Method to write a list of admin records to the file
    public void writeAdminsToFile(List<Admin> admins) {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdir(); // Create the data directory if it doesn't exist
        }
        File dataFile = new File(dataDir, FILENAME);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
            for (Admin admin : admins) {
                writer.write(admin.toFileString());
                writer.newLine();
            }
            System.out.println("Admin data successfully written to " + dataFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error writing admin data to file: " + dataFile.getAbsolutePath() + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Method to Find an Admin by User ID
    public Optional<Admin> findAdminById(String userId) {
        List<Admin> admins = readAdminsFromFile();
        return admins.stream()
                .filter(admin -> admin.getUserId().equals(userId))
                .findFirst();
    }

    // --- New Method to Delete an Admin by User ID ---

    public boolean deleteAdminById(String userId) {
        List<Admin> admins = readAdminsFromFile(); // Read all admins

        // Use an Iterator to safely remove an element while iterating
        Iterator<Admin> iterator = admins.iterator();
        boolean removed = false;
        while (iterator.hasNext()) {
            Admin admin = iterator.next();
            if (admin.getUserId().equals(userId)) {
                iterator.remove(); // Remove the admin from the list
                removed = true;
                break; // Assuming user IDs are unique, we can stop after finding one
            }
        }

        // If an admin was removed, write the modified list back to the file
        if (removed) {
            writeAdminsToFile(admins);
            System.out.println("Admin with ID " + userId + " deleted successfully.");
        } else {
            System.out.println("Admin with ID " + userId + " not found for deletion.");
        }

        return removed; // Return true if an admin was deleted, false otherwise
    }
}