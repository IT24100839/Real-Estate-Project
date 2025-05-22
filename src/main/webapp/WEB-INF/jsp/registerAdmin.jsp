<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Register New Admin</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        /* Apply Inter font globally */
        body {
            font-family: 'Inter', sans-serif;
            /* Background Image Styles */
            background-image: url('https://img.freepik.com/free-photo/office-supplies_23-2148103974.jpg?uid=R155620179&ga=GA1.1.900343090.1747928413&semt=ais_hybrid&w=740'); /* Your background image URL */
            background-size: cover;
            background-position: center;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }
    </style>
</head>
<body class="min-h-screen flex items-center justify-center py-10">
<div class="bg-white p-8 rounded-lg shadow-xl w-full max-w-md">
    <h2 class="text-3xl font-bold text-gray-800 mb-6 text-center">Register New Admin</h2>

    <%-- Removed the blue alert bar for messages --%>
    <%--
    <c:if test="${not empty message}">
        <div class="bg-blue-100 border border-blue-400 text-blue-700 px-4 py-3 rounded relative mb-4" role="alert">
            <c:out value="${message}"/>
        </div>
    </c:if>
    --%>

    <form action="/admin/register" method="post">
        <div class="mb-4">
            <label for="userId" class="block text-gray-700 text-sm font-bold mb-2">User ID:</label>
            <input type="text" id="userId" name="userId" required
                   class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
        </div>
        <div class="mb-4">
            <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Username:</label>
            <input type="text" id="username" name="username" required
                   class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
        </div>
        <div class="mb-4">
            <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password:</label>
            <input type="password" id="password" name="password" required
                   class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
        </div>
        <div class="mb-4">
            <label for="name" class="block text-gray-700 text-sm font-bold mb-2">Full Name:</label>
            <input type="text" id="name" name="name" required
                   class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
        </div>
        <div class="mb-6">
            <label for="contactInfo" class="block text-gray-700 text-sm font-bold mb-2">Contact Info:</label>
            <input type="text" id="contactInfo" name="contactInfo"
                   class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline focus:ring-2 focus:ring-indigo-500 focus:border-transparent">
        </div>
        <div class="flex items-center justify-between">
            <button type="submit"
                    class="bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
                Register Admin
            </button>
            <a href="/admin/list" class="inline-block align-baseline font-bold text-sm text-indigo-500 hover:text-indigo-800">
                Cancel
            </a>
        </div>
    </form>
</div>
</body>
</html>