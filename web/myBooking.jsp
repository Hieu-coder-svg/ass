<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Users, Models.Bookings, java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Bookings - TravelTour</title>
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
        .bookings-table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .bookings-table th, .bookings-table td {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
        .bookings-table th {
            background-color: #007bff;
            color: #fff;
        }
        .bookings-table tr:nth-child(even) {
            background-color: #f2f2f2;
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
         .action-btn {
            padding: 5px 10px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            color: #fff;
            margin-right: 5px;
        }
        .grant-btn {
            background-color: #28a745;
        }
        .revoke-btn {
            background-color: #dc3545;
        }
        .delete-btn {
            background-color: #dc3545;
        }
        .action-btn:hover {
            opacity: 0.9;
        }
    </style>
</head>
<body>
    <div class="top-header">
        <div class="auth">
            <%
                Users user = (Users) session.getAttribute("us");
               
                if (user != null) {
                    out.print("Welcome, " + user.getName() + " | <a href='" + request.getContextPath() + "/logout'>Logout</a>");
                } else {
                    out.print("<a href='" + request.getContextPath() + "/login'>Login</a> |<a href='" + request.getContextPath() + "/login'>Login</a> | <a href='" + request.getContextPath() + "/register'>Sign Up</a>");
                }
                 out.print("<a href='" + request.getContextPath() + "/home.jsp'>Home</a>");
            %>
        </div>
    </div>

    <h1>My Bookings</h1>

    <%
        ArrayList<Bookings> bookings = (ArrayList<Bookings>) request.getAttribute("bookings");
        if (bookings == null || bookings.isEmpty()) {
    %>
        <div class="message">You have not booked any tours yet.</div>
        <div class="links">
            <a href="${pageContext.request.contextPath}/search">Book a Tour</a>
        </div>
    <%
        } else {
    %>
        <table class="bookings-table">
            <thead>
                <tr>
                    <th>Booking ID</th>
                    <th>Tour Name</th>
                    <th>Destination</th>
                    <th>Duration</th>
                    <th>Booking Date</th>
                    <th>Number of People</th>
                    <th>Total Price</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Bookings booking : bookings) {
                %>
                <tr>
                    <td><%= booking.getBookingID() %></td>
                    <td><%= booking.getTour().getTourName() %></td>
                    <td><%= booking.getTour().getDestination() %></td>
                    <td><%= booking.getTour().getDuration() %> Days</td>
                    <td><%= booking.getBookingDate() %></td>
                    <td><%= booking.getNumberOfPeople() %></td>
                    <td><%= String.format("%,.0f", booking.getTotalPrice()) %> VND</td>
                    <td> <form action="${pageContext.request.contextPath}/myBooking" method="post" style="display:inline;">
                            <input type="hidden" name="bookingId" value="<%= booking.getBookingID() %>">
                            <button type="submit" name="action" value="delete" class="action-btn delete-btn">Delete</button>
                        </form>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <div class="links">
            <a href="${pageContext.request.contextPath}/search">Book Another Tour</a>
        </div>
    <%
        }
    %>
</body>
</html>