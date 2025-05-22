package com.realestate;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String newPassword = request.getParameter("newPassword");

        String filePath = getServletContext().getRealPath("/WEB-INF/users.txt");
        File file = new File(filePath);
        File tempFile = new File(file.getAbsolutePath() + ".tmp");

        boolean found = false;

        try (
                BufferedReader reader = new BufferedReader(new FileReader(file));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5 && parts[2].equalsIgnoreCase(email)) {
                    parts[1] = newPassword; // update password
                    found = true;
                    line = String.join(",", parts);
                }
                writer.write(line);
                writer.newLine();
            }
        }

        if (found) {
            if (!file.delete() || !tempFile.renameTo(file)) {
                request.setAttribute("error", "Failed to update password.");
            } else {
                request.setAttribute("success", "Password reset successfully.");
            }
        } else {
            request.setAttribute("error", "Email not found.");
            tempFile.delete();
        }

        request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
    }
}
