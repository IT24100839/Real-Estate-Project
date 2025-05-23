<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Edit Profile</title>
  <style>
    body {
      margin: 0;
      font-family: Arial, sans-serif;
      height: 100vh;
      background: url('https://eycrk5cno2n.exactdn.com/wp-content/uploads/2022/02/The-Importance-of-High-Quality-Real-Estate-Photos-v3.jpg?strip=all&lossy=1&ssl=1') no-repeat center center fixed;
      background-size: cover;
      display: flex;
      justify-content: center;
      align-items: center;
    }
    .form-container {
      background: rgba(255, 255, 255, 0.5);
      padding: 30px;
      border-radius: 12px;
      width: 350px;
      box-shadow: 0 8px 20px rgba(0,0,0,0.2);
    }
    h2 {
      text-align: center;
      color: #154a82;
      margin-bottom: 20px;
    }
    label {
      display: block;
      margin-top: 12px;
      font-weight: bold;
      color: #34495e;
    }
    input {
      width: 100%;
      padding: 10px;
      margin-top: 6px;
      border: 1px solid #ccc;
      border-radius: 6px;
      box-sizing: border-box;
    }
    button {
      margin-top: 20px;
      width: 100%;
      padding: 12px;
      background: #2c3e50;
      color: white;
      border: none;
      border-radius: 6px;
      font-size: 16px;
      cursor: pointer;
      transition: background 0.3s;
    }
    button:hover {
      background: #2980b9;
    }
  </style>
</head>
<body>
<div class="form-container">
  <h2>Edit Your Profile</h2>
  <form action="updateProfile" method="post">
    <label>Username</label>
    <input type="text" name="username" value="<%=request.getAttribute("username")%>" readonly>

    <label>New Password</label>
    <input type="password" name="password" placeholder="Leave blank to keep current">

    <label>Contact Number</label>
    <input type="text" name="contact" value="<%=request.getAttribute("contact")%>" required>

    <label>Birth Date</label>
    <input type="date" name="birthDate" value="<%=request.getAttribute("birthDate")%>" required>

    <button type="submit">Save Changes</button>
  </form>
</div>
</body>
</html>
