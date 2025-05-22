<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Dashboard - All Users</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background: url('https://images.unsplash.com/photo-1600585154340-be6161a56a0c') no-repeat center center fixed;
            background-size: cover;
            padding: 40px;
        }
        .container {
            background: rgba(255,255,255,0.95);
            padding: 30px;
            border-radius: 10px;
            max-width: 900px;
            margin: auto;
            box-shadow: 0 8px 20px rgba(0,0,0,0.2);
        }
        h2 {
            color: #2c3e50;
            margin-bottom: 20px;
        }
        form {
            margin-bottom: 20px;
        }
        input[type="text"] {
            padding: 10px;
            width: 300px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }
        input[type="submit"] {
            padding: 10px 16px;
            background-color: #3498db;
            border: none;
            color: white;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }
        table {
            width: 100%; border-collapse: collapse;
            background: white;
        }
        th, td {
            padding: 12px;
            border-bottom: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #2980b9;
            color: white;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Registered Users</h2>

    <form method="get" action="dashboard.jsp">
        <input type="text" name="search" placeholder="Search by username..." value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">
        <input type="submit" value="Search">
    </form>

    <table>
        <tr>
            <th>Username</th>
            <th>Email</th>
            <th>Contact</th>
            <th>Birth Date</th>
        </tr>
        <%
            String search = request.getParameter("search");
            String path = application.getRealPath("/WEB-INF/users.txt");
            try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
                String line;
                boolean found = false;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length >= 5) {
                        String uname = parts[0];
                        if (search == null || search.trim().isEmpty() || uname.toLowerCase().contains(search.toLowerCase())) {
                            found = true;
        %>
        <tr>
            <td><%= parts[0] %></td>
            <td><%= parts[2] %></td>
            <td><%= parts[3] %></td>
            <td><%= parts[4] %></td>
        </tr>
        <%
                    }
                }
            }
            if (!found) {
        %>
        <tr><td colspan="4">No user found.</td></tr>
        <%
                }
            } catch (Exception e) {
               System.out.println("<tr><td colspan='4'>Error loading users.</td></tr>");
            }
        %>
    </table>
</div>
</body>
</html>
