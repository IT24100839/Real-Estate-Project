<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login - Real Estate Portal</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background: linear-gradient(rgba(67, 206, 162, 0), rgba(24, 90, 157, 0)),
            url('https://images.pexels.com/photos/186077/pexels-photo-186077.jpeg?cs=srgb&dl=pexels-binyaminmellish-186077.jpg&fm=jpg') no-repeat center center;
            background-size: cover;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        .login-box {
            background-color: rgba(255, 255, 255, 0.95);
            padding: 40px;
            border-radius: 12px;
            box-shadow: 0 10px 20px rgba(0, 0, 0, 0.2);
            width: 360px;
            text-align: center;
        }

        .login-box h2 {
            color: #185a9d;
            font-size: 24px;
            margin-bottom: 20px;
        }

        .login-box label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
            text-align: left;
        }

        .login-box input[type="text"],
        .login-box input[type="password"] {
            width: 100%;
            padding: 15px;
            margin-top: 8px;
            border: 1px solid #ccc;
            border-radius: 6px;
            box-sizing: border-box;
        }

        .forgot-password {
            text-align: right;
            margin-top: 5px;
        }

        .forgot-password a {
            color: #185a9d;
            font-size: 13px;
            text-decoration: none;
        }

        .forgot-password a:hover {
            text-decoration: underline;
        }

        .login-box input[type="submit"] {
            width: 100%;
            background-color: #185a9d;
            color: white;
            padding: 12px;
            margin-top: 20px;
            border: none;
            border-radius: 6px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s;
        }

        .login-box input[type="submit"]:hover {
            background-color: #154a82;
        }

        .error-message {
            color: red;
            margin-top: 10px;
            font-weight: bold;
        }

        .register-link {
            margin-top: 20px;
        }

        .register-link a {
            color: #185a9d;
            text-decoration: none;
            font-weight: bold;
        }

        .register-link a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="login-box">
    <h2>User Login</h2>
    <form method="post" action="login">
        <label>Username</label>
        <input type="text" name="username" required>

        <label>Password</label>
        <input type="password" name="password" required>

        <!-- Forgot Password link -->
        <div class="forgot-password">
            <a href="forgotPassword.jsp">Forgot Password?</a>
        </div>

        <input type="submit" value="Login">

        <%
            String error = (String) request.getAttribute("errorMessage");
            if (error != null) {
        %>
        <div class="error-message"><%= error %></div>
        <%
            }
        %>
    </form>
    <div class="register-link">
        Don't have an account? <a href="register.jsp">Register</a>
    </div>
</div>
</body>
</html>
