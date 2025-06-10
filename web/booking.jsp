<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Users" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Book Tour - TravelTour</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f8f9fa;
            text-align: center;
        }
        .top-header {
            background-color: #fff;
            padding: 10px 20px;
            display: flex;
            justify-content: flex-end;
            align-items: center;
            border-bottom: 1px solid #ddd;
        }
        .top-header .auth a {
            margin-left: 10px;
            color: #007bff;
            text-decoration: none;
            font-size: 14px;
        }
        h1 {
            color: #007bff;
            margin-top: 30px;
        }
        .tour-details {
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            max-width: 500px;
            margin: 20px auto;
        }
        .tour-details p {
            margin: 5px 0;
        }
        .form-group {
            margin: 15px 0;
        }
        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-group input {
            padding: 8px;
            width: 100%;
            max-width: 200px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            border: none;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .message {
            color: green;
            font-size: 18px;
            margin: 20px 0;
        }
        .error {
            color: red;
            font-size: 18px;
            margin: 20px 0;
        }
        .links {
            margin-top: 20px;
        }
        .links a {
            display: inline-block;
            padding: 10px 20px;
            margin: 0 10px;
            background-color: #007bff;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
        }
        .links a:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
   <div class="top-header">
    <div class="auth">
        <%
            Users user = (Users) session.getAttribute("us");
            if (user != null) {
                out.print("Welcome, " + user.getName() + " | <a href='" + request.getContextPath() + "/myBooking'>My Bookings</a> | <a href='" + request.getContextPath() + "/logout'>Logout</a>");
            } else {
                out.print("<a href='" + request.getContextPath() + "/login'>Login</a> | <a href='" + request.getContextPath() + "/register'>Sign Up</a>");
            }
        %>
    </div>
</div>

    <h1>Book Tour</h1>

    <%
        String message = (String) request.getAttribute("message");
        String error = (String) request.getAttribute("error");

        if (message != null) {
    %>
        <div class="message"><%= message %></div>
        <div class="links">
            <a href="${pageContext.request.contextPath}/search">Back to Search</a>
            <a href="${pageContext.request.contextPath}/search">Book Another Tour</a>
        </div>
    <%
        } else if (error != null) {
    %>
        <div class="error"><%= error %></div>
        <div class="links">
            <a href="${pageContext.request.contextPath}/search">Back to Search</a>
        </div>
    <%
        } else {
            Integer tourId = (Integer) request.getAttribute("tourId");
            String tourName = (String) request.getAttribute("tourName");
            String destination = (String) request.getAttribute("destination");
            String duration = (String) request.getAttribute("duration");
            String description = (String) request.getAttribute("description");
            Double price = (Double) request.getAttribute("price");

            if (tourId == null || tourName == null || price == null) {
                out.println("<div class='error'>Error: Tour information is missing.</div>");
            } else {
    %>
        <div class="tour-details">
            <h3><%= tourName %></h3>
            <p><strong>Destination:</strong> <%= destination %></p>
            <p><strong>Duration:</strong> <%= duration %> Days</p>
            <p><strong>Description:</strong> <%= description %></p>
            <p><strong>Price per person:</strong> <%= String.format("%,.0f", price) %> VND</p>
        </div>

        <form action="${pageContext.request.contextPath}/book" method="post" onsubmit="return confirm('Are you sure you want to book this tour? Total Price: ' + document.getElementById('totalPrice').innerText);">
            <input type="hidden" name="tourId" value="<%= tourId %>">
            <div class="form-group">
                <label for="numberOfPeople">Number of People:</label>
                <input type="number" id="numberOfPeople" name="numberOfPeople" min="1" required oninput="calculateTotalPrice()">
            </div>
            <p><strong>Total Price:</strong> <span id="totalPrice">0 VND</span></p>
            <button type="submit" class="btn">Confirm Booking</button>
        </form>

        <script>
            function calculateTotalPrice() {
                const numberOfPeople = document.getElementById('numberOfPeople').value;
                const pricePerPerson = <%= price %>;
                const totalPrice = numberOfPeople * pricePerPerson;
                document.getElementById('totalPrice').innerText = totalPrice.toLocaleString('vi-VN') + ' VND';
            }
        </script>
    <%
            }
        }
    %>
</body>
</html>