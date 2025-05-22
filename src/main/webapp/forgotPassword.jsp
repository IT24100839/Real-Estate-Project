<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Forgot Password - Real Estate Portal</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: linear-gradient(rgba(0,0,0,0.5), rgba(0,0,0,0.5)),
            url('https://images.unsplash.com/photo-1600585154340-be6161a56a0c?auto=format&fit=crop&w=1400&q=80') no-repeat center center;
            background-size: cover;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .forgot-box {
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.25);
            width: 400px;
            text-align: center;
        }

        .forgot-box h2 {
            color: #185a9d;
            margin-bottom: 20px;
        }

        .forgot-box label {
            font-weight: bold;
            display: block;
            text-align: left;
            margin-top: 12px;
        }

        .forgot-box input[type="email"],
        .forgot-box input[type="password"] {
            width: 100%;
            padding: 12px;
            margin-top: 6px;
            border: 1px solid #ccc;
            border-radius: 6px;
        }

        .forgot-box input[type="submit"] {
            width: 100%;
            background-color: #185a9d;
            color: white;
            padding: 12px;
            margin-top: 20px;
            border: none;
            border-radius: 6px;
            font-size: 16px;
            cursor: pointer;
        }

        .forgot-box input[type="submit"]:hover {
            background-color: #154a82;
        }

        .message {
            margin-top: 12px;
            font-size: 14px;
        }

        .message.success {
            color: #28a745;
        }

        .message.error {
            color: #e74c3c;
        }
    </style>
</head>
<body>
<div class="forgot-box">
    <h2>Reset Password</h2>
    <form action="ForgotPasswordServlet" method="post">
        <label>Email:</label>
        <input type="email" name="email" required>

        <label>New Password:</label>
        <input type="password" name="newPassword" required>

        <input type="submit" value="Reset Password">

        <div class="message error"><%= request.getAttribute("error") != null ? request.getAttribute("error") : "" %></div>
        <div class="message success"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></div>
    </form>
</div>
</body>
</html>
