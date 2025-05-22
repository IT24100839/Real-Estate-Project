package lk.ac.sliit.se1020.pgno7.realestateportal.model; // Using pgno7

// Represents an administrator, inheriting from the User class
public class Admin extends User {
    // Admins might have specific attributes later, e.g., admin level or permissions
    // For now, we'll keep it simple, inheriting all User properties.
    // private String adminLevel;

    // Constructor for the Admin class
    public Admin(String userId, String username, String password, String name, String contactInfo) {
        // Call the constructor of the parent class (User)
        super(userId, username, password, name, contactInfo);
        // Initialize any admin-specific attributes here if you add them
        // this.adminLevel = "SuperAdmin"; // Example
    }

    // --- Getters/Setters for Admin-specific attributes (if you add them) ---
    /*
    public String getAdminLevel() {
        return adminLevel;
    }

    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
    */

    // --- Override methods for File Handling (Optional but good for clarity) ---

    @Override
    public String toFileString() {
        // You might want to prefix admin lines in the file to distinguish them
        // Although if you only store Admins in admins.txt, it might not be strictly necessary.
        // Let's add a prefix "ADMIN," for demonstration.
        return "ADMIN," + super.toFileString(); // Calls the toFileString of the parent (User) class
    }

    // Method to create an Admin object by parsing a string from a file
    // This method needs to handle the potential "ADMIN," prefix
    public static Admin fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        // Assuming the format is ADMIN,userId,username,password,name,contactInfo
        // We need at least 6 parts if we add the "ADMIN," prefix
        if (parts.length >= 6 && parts[0].equals("ADMIN")) {
            // Call the User constructor (or a parsing method) with the relevant parts
            return new Admin(parts[1], parts[2], parts[3], parts[4], parts[5]);
        } else {
            // Handle error: the file format is incorrect or not an admin record
            System.err.println("Error parsing Admin from file string: " + fileString);
            return null; // Or throw an exception
        }
    }

    // Optional: Override toString for easier debugging
    @Override
    public String toString() {
        return "Admin{" +
                "userId='" + getUserId() + '\'' + // Use getters to access parent properties
                ", username='" + getUsername() + '\'' +
                ", name='" + getName() + '\'' +
                ", contactInfo='" + getContactInfo() + '\'' +
                // Add any admin-specific attributes here too
                // ", adminLevel='" + adminLevel + '\'' +
                '}';
    }
}