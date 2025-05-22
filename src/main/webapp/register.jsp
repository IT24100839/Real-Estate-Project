<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register - Real Estate Portal</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: url('https://images.unsplash.com/photo-1600585154340-be6161a56a0c?ixlib=rb-4.0.3&auto=format&fit=crop&w=1950&q=80') no-repeat center center fixed;
            background-size: cover;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .register-box {
            background: rgba(255, 255, 255, 0.9);
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.3);
            width: 420px;
            text-align: center;
            backdrop-filter: blur(10px);
        }
        .register-box h2 {
            color: #2c3e50;
            margin-bottom: 25px;
        }
        .register-box label {
            font-weight: bold;
            display: block;
            margin-top: 15px;
            text-align: left;
            color: #34495e;
        }
        .register-box input[type="text"],
        .register-box input[type="password"],
        .register-box input[type="email"],
        .register-box select {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            border: 1px solid #ccc;
            border-radius: 8px;
            box-sizing: border-box;
            background: #ecf0f1;
        }
        .register-box input[type="submit"] {
            width: 100%;
            background-color: #2980b9;
            color: white;
            padding: 14px;
            margin-top: 25px;
            border: none;
            border-radius: 8px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .register-box input[type="submit"]:hover {
            background-color: #1c5980;
        }
        .error-message {
            color: red;
            margin-top: 4px;
            font-size: 13px;
            text-align: left;
        }
        .birth-date {
            display: flex;
            gap: 8px;
            margin-top: 8px;
        }
        .birth-date select {
            flex: 1;
        }
    </style>
</head>
<body>
<div class="register-box">
    <h2>User Registration</h2>
    <form method="post" action="register">
        <label for="username">Username</label>
        <input type="text" name="username" id="username" required>
        <div class="error-message"><%= request.getAttribute("usernameError") != null ? request.getAttribute("usernameError") : "" %></div>

        <label for="password">Password</label>
        <input type="password" name="password" id="password" required>
        <div class="error-message"><%= request.getAttribute("passwordError") != null ? request.getAttribute("passwordError") : "" %></div>

        <label for="confirmPassword">Confirm Password</label>
        <input type="password" name="confirmPassword" id="confirmPassword" required>
        <div class="error-message"><%= request.getAttribute("confirmPasswordError") != null ? request.getAttribute("confirmPasswordError") : "" %></div>

        <label for="email">Email</label>
        <input type="email" name="email" id="email" required>
        <div class="error-message"><%= request.getAttribute("emailError") != null ? request.getAttribute("emailError") : "" %></div>

        <label for="contactNumber">Contact Number</label>
        <input type="text" name="contactNumber" id="contactNumber" required pattern="\d{10}" title="Enter a 10-digit number">
        <div class="error-message"><%= request.getAttribute("contactError") != null ? request.getAttribute("contactError") : "" %></div>

        <label>Birth Date</label>
        <div class="birth-date">
            <select name="birthDay" required>
                <option value="" selected disabled>Day</option>
                <% for (int d = 1; d <= 31; d++) { %>
                <option value="<%=d%>"><%=d%></option>
                <% } %>
            </select>

            <select name="birthMonth" required>
                <option value="" selected disabled>Month</option>
                <% String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun",
                        "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
                    for (int m = 1; m <= 12; m++) { %>
                <option value="<%=m%>"><%=months[m - 1]%></option>
                <% } %>
            </select>

            <select name="birthYear" required>
                <option value="" selected disabled>Year</option>
                <% for (int y = 1980; y <= 2100; y++) { %>
                <option value="<%=y%>"><%=y%></option>
                <% } %>
            </select>
        </div>
        <div class="error-message"><%= request.getAttribute("birthError") != null ? request.getAttribute("birthError") : "" %></div>

        <input type="submit" value="Register">
        <div class="error-message"><%= request.getAttribute("generalError") != null ? request.getAttribute("generalError") : "" %></div>
    </form>
</div>
</body>
</html>
