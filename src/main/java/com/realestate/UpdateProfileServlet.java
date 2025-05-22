package com.realestate;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UpdateProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            resp.sendRedirect("login.jsp");
            return;
        }

        // Populate form with current session data
        req.setAttribute("username", session.getAttribute("username"));
        req.setAttribute("contact", session.getAttribute("contactNumber"));
        req.setAttribute("birthDate", session.getAttribute("birthDate"));

        req.getRequestDispatcher("editProfile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String path    = getServletContext().getRealPath("/WEB-INF/users.txt");
        File inFile    = new File(path), tmpFile = new File(path + ".tmp");
        String username = req.getParameter("username");
        String newPass  = req.getParameter("password");
        String newContact = req.getParameter("contact");
        String newBirth   = req.getParameter("birthDate");

        List<String[]> allUsers = new ArrayList<>();

        // Read and modify
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equals(username)) {
                    // Update fields: keep old if new is blank
                    parts[1] = (newPass == null || newPass.isEmpty()) ? parts[1] : newPass;
                    parts[3] = newContact;
                    parts[4] = newBirth;
                }
                allUsers.add(parts);
            }
        }

        // Write back
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tmpFile))) {
            for (String[] u : allUsers) {
                writer.write(String.join(",", u));
                writer.newLine();
            }
        }

        // Replace original
        inFile.delete();
        tmpFile.renameTo(inFile);

        // Update session so profile.jsp shows new data
        HttpSession session = req.getSession();
        session.setAttribute("contactNumber", newContact);
        session.setAttribute("birthDate", newBirth);
        if (newPass != null && !newPass.isEmpty()) {
            // (If you also store password in session)
            session.setAttribute("password", newPass);
        }

        resp.sendRedirect("profile.jsp");
    }
}
