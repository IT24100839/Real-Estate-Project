<%@ page import="java.io.*" %>
<%@ page session="true" contentType="text/html;charset=UTF-8" language="java" %>
<%
  String username = (String) session.getAttribute("username");
  if (username == null) {
    response.sendRedirect("login.jsp");
    return;
  }

  String usersPath = getServletContext().getRealPath("/WEB-INF/users.txt");
  String userDetails = null;

  try (BufferedReader reader = new BufferedReader(new FileReader(usersPath))) {
    String line;
    while ((line = reader.readLine()) != null) {
      String[] parts = line.split(",");
      if (parts.length >= 5 && parts[0].equals(username)) {
        userDetails = line;
        break;
      }
    }
  } catch (IOException e) {
    e.printStackTrace();
  }

  if (userDetails == null) {
    response.sendRedirect("login.jsp");
    return;
  }

  String[] userFields = userDetails.split(",");
  String email = userFields[2];
  String contact = userFields[3];
  String birthDate = userFields[4];
%>

<!DOCTYPE html>
<html>
<head>
  <title>User Profile</title>
  <style>
    body {
      margin: 0;
      font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
      background: url('https://digiestateorg.wordpress.com/wp-content/uploads/2023/11/ask-us-1024x583-1.jpg') no-repeat center center fixed;
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
    }

    .glass-box {
      background: rgba(255, 255, 255, 0.15);
      border-radius: 16px;
      padding: 40px;
      width: 60%;
      color: #fff;
      text-shadow: 0 1px 2px rgba(0,0,0,0.7);
      backdrop-filter: blur(12px);
      -webkit-backdrop-filter: blur(12px);
      box-shadow: 0 8px 32px rgba(0,0,0,0.2);
    }

    h2 {
      text-align: center;
      margin-bottom: 20px;
    }

    .profile-info p {
      font-size: 18px;
      margin: 10px 0;
    }

    .profile-info strong {
      color: #ffd700;
    }

    .buttons {
      margin-top: 30px;
      text-align: center;
    }

    .btn {
      background: #ffd700;
      color: #333;
      padding: 12px 24px;
      margin: 8px;
      border: none;
      border-radius: 8px;
      cursor: pointer;
      font-size: 16px;
      transition: 0.3s;
    }

    .btn:hover {
      background: #e1c400;
    }

    .btn.logout {
      background: #ff4b5c;
      color: white;
    }

    .btn.logout:hover {
      background: #c0392b;
    }

    .btn.delete {
      background: #8e44ad;
      color: white;
    }

    .btn.delete:hover {
      background: #6c3483;
    }

    .btn.dashboard {
      background: #3498db;
      color: white;
    }

    .btn.dashboard:hover {
      background: #217dbb;
    }
  </style>
</head>
<body>
<div class="glass-box">
  <h2>Welcome, <%= username %>!</h2>
  <div class="profile-info">
    <p><strong>Email:</strong> <%= email %></p>
    <p><strong>Contact Number:</strong> <%= contact %></p>
    <p><strong>Birth Date:</strong> <%= birthDate %></p>
  </div>

  <div class="buttons">
    <form action="dashboard.jsp" method="get" style="display:inline;">
      <button class="btn dashboard">Go to Dashboard</button>
    </form>

    <form action="editProfile" method="get" style="display:inline;">
      <button class="btn">Edit Profile</button>
    </form>

    <form action="deleteUser" method="post" style="display:inline;" onsubmit="return confirm('Are you sure you want to delete your account?');">
      <input type="hidden" name="username" value="<%= username %>">
      <button class="btn delete">Delete Account</button>
    </form>

    <form action="logout.jsp" method="post" style="display:inline;">
      <button class="btn logout">Logout</button>
    </form>
  </div>
</div>
</body>
</html>
