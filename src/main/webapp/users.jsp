<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %> <!-- Import List class -->
<!DOCTYPE html>
<html>
<head>
  <title>Registered Users</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background: #f4f6f9;
      padding: 20px;
    }
    h2 {
      color: #333;
    }
    table {
      border-collapse: collapse;
      width: 100%;
      background: #fff;
      box-shadow: 0 0 10px rgba(0,0,0,0.1);
    }
    th, td {
      padding: 12px;
      border-bottom: 1px solid #ddd;
      text-align: left;
    }
    th {
      background-color: #185a9d;
      color: white;
    }
  </style>
</head>
<body>
<h2>Registered Users</h2>
<table>
  <tr>
    <th>Username</th>
    <th>Email</th>
    <th>Contact</th>
    <th>Birth Date</th>
  </tr>
  <%
    // Retrieve the list of users from the request attribute
    List<String[]> users = (List<String[]>) request.getAttribute("users");

    // Check if the users list is not null and has data
    if (users != null) {
      for (String[] user : users) {
  %>
  <tr>
    <!-- Populate table with user data -->
    <td><%= user[0] %></td> <!-- Username -->
    <td><%= user[2] %></td> <!-- Email -->
    <td><%= user[3] %></td> <!-- Contact Number -->
    <td><%= user[4] %></td> <!-- Birth Date -->
  </tr>
  <%
      }
    }
  %>
</table>
</body>
</html>
