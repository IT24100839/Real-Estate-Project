package lk.ac.sliit.se1020.pgno7.realestateportal.model; // Using pgno7

// Represents a generic user in the system
public class AdminUser {
    private String userId;       // Unique identifier for the user
    private String username;
    private String password;     // In a real-world app, hash passwords! For this project, plain text might be acceptable per instructions.
    private String name;
    private String contactInfo;

    // Constructor to create a new User object
    public AdminUser(String userId, String username, String password, String name, String contactInfo) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.name = name;
        this.contactInfo = contactInfo;
    }

    // --- Getters ---
    public String getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password; // Remember the note about password security!
    }

    public String getName() {
        return name;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    // --- Setters (Optional, but useful for Update operations later) ---
    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password; // Remember the note about password security!
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    // --- Methods for File Handling ---

    // Converts the User object into a string format suitable for writing to a file
    // Using a comma as a delimiter
    public String toFileString() {
        return userId + "," + username + "," + password + "," + name + "," + contactInfo;
    }

    // Creates a User object by parsing a string read from a file
    // This is a static method because you call it on the class itself, not an instance
    public static User fromFileString(String fileString) {
        String[] parts = fileString.split(",");
        // Basic validation: ensure we have the expected number of parts
        if (parts.length == 5) {
            return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
        } else {
            // Handle error: the file format might be wrong
            System.err.println("Error parsing User from file string: " + fileString);
            return null; // Or throw an exception
        }
    }

    // Optional: toString method for easier debugging
    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", contactInfo='" + contactInfo + '\'' +
                '}';
    }
}
