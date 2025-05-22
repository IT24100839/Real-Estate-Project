<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome to Real Estate Property Listings</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            background-image: url('https://img.freepik.com/premium-photo/lot-townhouses-made-brick-line_244784-777.jpg?semt=ais_hybrid&w=740'); /* Ensure the correct image path */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            font-family: Arial, sans-serif;
            color: white;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
        }
        .content {
            background: rgba(0, 0, 0, 0.6); /* Semi-transparent overlay */
            padding: 40px 60px;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.5);
        }
        h1 {
            font-size: 50px;
            margin-bottom: 20px;
            font-weight: bold;
            color: #bdb76b;
            font-style: italic;
        }
        p {
            font-size: 22px;
            margin-top: 0;
            margin-bottom: 30px;
            font-style: oblique;
        }
        a.button {
            display: inline-block;
            margin-top: 20px;
            padding: 12px 25px;
            background: #ff7e5f;
            color: white;
            text-decoration: none;
            font-size: 18px;
            border-radius: 5px;
            transition: background 0.3s;
            text-transform: uppercase;
            font-style: oblique;
            font-weight: bold;
        }
        a.button:hover {
            background: #feb47b;
        }
    </style>
</head>
<body>
<div class="content">
    <h1>Welcome to Real Estate Property Listings Portal</h1>
    <p>Find your dream home or list your property with ease. Discover the best opportunities today.</p>
    <a href="login.jsp" class="button">Get Started</a>
</div>
</body>
</html>
