package com.realestate.dao;

import com.realestate.model.User;

import java.io.*;
import java.util.*;

public class UserDAO {
    private final String usersFilePath;

    public UserDAO(String usersFilePath) {
        this.usersFilePath = usersFilePath;
    }

    public User authenticate(String username, String password) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(usersFilePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 4
                        && parts[0].equals(username)
                        && parts[1].equals(password)) {
                    return new User(parts[0], parts[1], parts[2], parts[3]);
                }
            }
        }
        return null;
    }
}
