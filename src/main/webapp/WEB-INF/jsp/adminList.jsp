<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin List</title>
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        /* Apply Inter font globally */
        body {
            font-family: 'Inter', sans-serif;
            /* Background Image Styles */
            background-image: url('https://img.freepik.com/free-photo/office-supplies_23-2148103974.jpg?semt=ais_hybrid&w=740'); /* Your background image URL */
            background-size: cover;       /* Cover the entire element */
            background-position: center;  /* Center the image */
            background-repeat: no-repeat; /* Do not repeat the image */
            background-attachment: fixed; /* Keep background fixed when scrolling */
        }
    </style>
    <script>
        function confirmDelete(username) {
            return confirm("Are you sure you want to delete the admin: " + username + "?");
        }
    </script>
</head>
<body class="min-h-screen flex items-center justify-center py-10">
<div class="bg-white p-8 rounded-lg shadow-xl w-full max-w-4xl">
    <h2 class="text-3xl font-bold text-gray-800 mb-6 text-center">Admin List</h2>

    <c:if test="${not empty message}">
        <div class="bg-blue-100 border border-blue-400 text-blue-700 px-4 py-3 rounded relative mb-4" role="alert">
            <c:out value="${message}"/>
        </div>
    </c:if>

    <c:if test="${empty admins}">
        <p class="text-gray-600 text-center">No administrators found.</p>
    </c:if>

    <c:if test="${not empty admins}">
        <div class="overflow-x-auto mb-6">
            <table class="min-w-full bg-white rounded-lg overflow-hidden">
                <thead class="bg-gray-50 border-b border-gray-200">
                <tr>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">User ID</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Username</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Full Name</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Contact Info</th>
                    <th class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Actions</th>
                </tr>
                </thead>
                <tbody class="divide-y divide-gray-200">
                <c:forEach var="admin" items="${admins}">
                    <tr class="hover:bg-gray-50">
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium text-gray-900"><c:out value="${admin.userId}"/></td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700"><c:out value="${admin.username}"/></td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700"><c:out value="${admin.name}"/></td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm text-gray-700"><c:out value="${admin.contactInfo}"/></td>
                        <td class="px-6 py-4 whitespace-nowrap text-sm font-medium">
                            <a href="/admin/edit?userId=<c:out value="${admin.userId}"/>" class="text-indigo-600 hover:text-indigo-900 mr-4">Edit</a>
                            <a href="/admin/delete?userId=<c:out value="${admin.userId}"/>"
                               onclick="return confirmDelete('<c:out value="${admin.username}"/>');"
                               class="text-red-600 hover:text-red-900">Delete</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </c:if>

    <div class="text-center">
        <a href="/admin/register" class="inline-flex items-center px-6 py-3 border border-transparent text-base font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500">
            Register New Admin
        </a>
    </div>
</div>
</body>
</html>