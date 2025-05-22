package com.realestate.model;

public class User {
    private String username;
    private String password;
    private String email;
    private String contact;

    public User(String username, String password, String email, String contact) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.contact = contact;
    }

    // Getters
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public String getContact() { return contact; }
}
