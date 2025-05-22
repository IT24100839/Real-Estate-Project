package com.realestate;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usersPath = getServletContext().getRealPath("/WEB-INF/users.txt");
        File file = new File(usersPath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        String username  = request.getParameter("username");
        String password  = request.getParameter("password");
        String confirm   = request.getParameter("confirmPassword");
        String email     = request.getParameter("email");
        String contact   = request.getParameter("contactNumber");
        String birthDay  = request.getParameter("birthDay");
        String birthMonth= request.getParameter("birthMonth");
        String birthYear = request.getParameter("birthYear");

        String birthDate = (birthDay != null && birthMonth != null && birthYear != null)
                ? birthDay + "/" + birthMonth + "/" + birthYear : null;

        boolean hasError = false;

        if (username == null || username.trim().isEmpty()) {
            request.setAttribute("usernameError", "Username is required.");
            hasError = true;
        }
        if (password == null) {
            request.setAttribute("passwordError", "Password is required.");
            hasError = true;
        }
        if (confirm == null || !confirm.equals(password)) {
            request.setAttribute("confirmPasswordError", "Passwords do not match.");
            hasError = true;
        }
        if (email == null || !email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            request.setAttribute("emailError", "Enter a valid email address.");
            hasError = true;
        }
        if (contact == null || contact.trim().isEmpty()) {
            request.setAttribute("contactError", "Contact number is required.");
            hasError = true;
        }
        if (birthDate == null) {
            request.setAttribute("birthError", "Birth date is required.");
            hasError = true;
        }

        // Duplicate email/username checks
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    if (parts[0].equalsIgnoreCase(username)) {
                        request.setAttribute("usernameError", "Username already exists.");
                        hasError = true;
                    }
                    if (parts[2].equalsIgnoreCase(email)) {
                        request.setAttribute("emailError", "Email already registered.");
                        hasError = true;
                    }
                }
            }
        }

        if (hasError) {
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(String.join(",", username, password, email, contact, birthDate));
            writer.newLine();
        } catch (IOException e) {
            request.setAttribute("generalError", "Error saving user data.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
            return;
        }

        response.sendRedirect("login.jsp");
    }
}
