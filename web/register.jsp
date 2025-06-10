<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TravelTour - Register</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f8f9fa;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
            }
            .register-container {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                width: 400px;
            }
            .register-container h2 {
                color: #007bff;
                text-align: center;
                margin-bottom: 20px;
            }
            .register-container label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .register-container input {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
                box-sizing: border-box;
            }
            .register-container select {
                width: 100%;
                padding: 8px;
                margin-bottom: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .register-container button {
                width: 100%;
                padding: 10px;
                background-color: #007bff;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .register-container button:hover {
                background-color: #0056b3;
            }
            .register-container .errorInName {
                color: #dc3545;
                text-align: center;
                margin-bottom: 10px;
                display: <%= request.getAttribute("errorInName") != null ? "block" : "none" %>;
            }
            .register-container .errorName {
                color: #dc3545;
                text-align: center;
                margin-bottom: 10px;
                display: <%= request.getAttribute("errorName") != null ? "block" : "none" %>;
            }
            .register-container .errorPass {
                color: #dc3545;
                text-align: center;
                margin-bottom: 10px;
                display: <%= request.getAttribute("errorPass") != null ? "block" : "none" %>;
            }
            .register-container .errorPhone {
                color: #dc3545;
                text-align: center;
                margin-bottom: 10px;
                display: <%= request.getAttribute("errorPhone") != null ? "block" : "none" %>;
            }
            .register-container .errorPhoneN {
                color: #dc3545;
                text-align: center;
                margin-bottom: 10px;
                display: <%= request.getAttribute("errorPhoneN") != null ? "block" : "none" %>;
            }
            .register-container .errorPhoneN {
                color: #dc3545;
                text-align: center;
                margin-bottom: 10px;
                display: <%= request.getAttribute("errorPhoneNu") != null ? "block" : "none" %>;
            }
            .register-container .success {
                color: #28a745;
                text-align: center;
                margin-bottom: 10px;
                display: <%= request.getAttribute("success") != null ? "block" : "none" %>;
            }
            .register-container a {
                display: block;
                text-align: center;
                color: #007bff;
                text-decoration: none;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <div class="register-container">
            <h2>Register</h2>

            <div class="success"><%= request.getAttribute("success") != null ? request.getAttribute("success") : "" %></div>
            <form action="${pageContext.request.contextPath}/register" method="post">
                <label for="name">Name:</label>
                <input type="text" id="name" name="name" required>
                <div class="errorName"><%= request.getAttribute("errorName") != null ? request.getAttribute("errorName") : "" %></div>

                <label for="birthDate">Birth Date:</label>
                <input type="date" id="birthDate" name="birthDate" required>

                <label for="gender">Gender:</label>
                <select id="gender" name="gender" required>
                    <option value="1">Male</option>
                    <option value="0">Female</option>
                </select>

                <label for="phoneNumber">Phone Number:</label>
                <input type="number" id="phoneNumber" name="phoneNumber" required>
                <div class="errorPhone"><%= request.getAttribute("errorPhone") != null ? request.getAttribute("errorPhone") : "" %></div>
                <div class="errorPhoneN"><%= request.getAttribute("errorPhoneN") != null ? request.getAttribute("errorPhoneN") : "" %></div>
                 <div class="errorPhoneN"><%= request.getAttribute("errorPhoneNu") != null ? request.getAttribute("errorPhoneNu") : "" %></div>

                <label for="email">Email:</label>
                <input type="email" id="email" name="email" required>

                <label for="address">Address:</label>
                <input type="text" id="address" name="address" required>

                <label for="inname">Username:</label>
                <input type="text" id="inname" name="inname" required>
                <div class="errorInName"><%= request.getAttribute("errorInName") != null ? request.getAttribute("errorInName") : "" %></div>
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
                <div class="errorPass"><%= request.getAttribute("errorPass") != null ? request.getAttribute("errorPass") : "" %></div>



                <button type="submit">Register</button>
            </form>
            <a href="${pageContext.request.contextPath}/login">Already have an account? Login</a>
        </div>
    </body>
</html>