<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>CMS | Sign Up</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <link rel="stylesheet" href="../css/style.css">
</head>
<body class="bg-gradient-to-r from-purple-400 via-pink-500 to-red-500 min-h-screen flex items-center justify-center">

<div class="bg-white p-10 rounded-lg shadow-2xl max-w-md w-full transform transition duration-500 hover:scale-105">
    <div class="flex justify-center mb-8">
        <img src="../img/logo.jpeg" alt="Logo" class="h-32 w-32 rounded-full object-cover"> <!-- Add logo here -->
    </div>
        
    <form action="../signUp" method="post">
        <div class="mb-6">
            <input type="text" name="fullname" placeholder="Full Name" class="w-full px-5 py-4 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-purple-500 shadow-sm" required>
        </div>
        <div class="mb-6">
            <input type="email" name="email" placeholder="Email Address" class="w-full px-5 py-4 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-purple-500 shadow-sm" required>
        </div>
        <div class="mb-6">
            <input type="password" name="password" placeholder="Password" class="w-full px-5 py-4 rounded-lg border border-gray-300 focus:outline-none focus:ring-2 focus:ring-purple-500 shadow-sm" required>
        </div>
        <button type="submit" class="w-full bg-purple-600 text-white py-4 rounded-lg hover:bg-purple-700 transition-colors shadow-lg hover:shadow-xl transform hover:scale-105">Sign Up</button>
    </form>
    
    <p class="text-center text-gray-500 mt-8">Already have an account? 
        <a href="../index.jsp" class="text-purple-500 hover:underline">Login</a>
    </p>
</div>
</body>
</html>
