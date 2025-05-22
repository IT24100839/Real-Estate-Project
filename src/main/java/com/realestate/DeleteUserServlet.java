package com.realestate;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class DeleteUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // 1) Locate users.txt in WEB-INF
        String usersPath = getServletContext().getRealPath("/WEB-INF/users.txt");
        File inFile = new File(usersPath);
        File tempFile = new File(usersPath + ".tmp");

        String usernameToDelete = req.getParameter("username");

        // 2) Copy all lines except the one for this user
        try (BufferedReader reader = new BufferedReader(new FileReader(inFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.startsWith(usernameToDelete + ",")) {
                    writer.write(line);
                    writer.newLine();
                }
            }
        }

        // 3) Replace original file with temp file
        if (!inFile.delete()) {
            throw new IOException("Could not delete original users.txt");
        }
        if (!tempFile.renameTo(inFile)) {
            throw new IOException("Could not rename temp file to users.txt");
        }

        // 4) Invalidate session and redirect to login
        req.getSession().invalidate();
        resp.sendRedirect("login.jsp");
    }
}
