package com.realestate;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String usersPath = getServletContext().getRealPath("/WEB-INF/users.txt");
        boolean valid = false;
        String email = null, contact = null, birthDate = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(usersPath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 &&
                        parts[0].equals(username) &&
                        parts[1].equals(password)) {

                    valid = true;
                    email = parts[2];
                    contact = parts[3];
                    birthDate = parts[4];
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error reading user data.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        if (valid) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("email", email);
            session.setAttribute("contactNumber", contact);
            session.setAttribute("birthDate", birthDate);
            response.sendRedirect("profile.jsp");
        } else {
            request.setAttribute("errorMessage", "Invalid username or password.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
