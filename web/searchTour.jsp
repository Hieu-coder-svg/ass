<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Tours" %>
<%@ page import="Models.Users" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>TravelTour - Search Tours</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f8f9fa;
            }
            .header {
                background-color: #fff;
                padding: 20px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                text-align: center;
            }
            .header h1 {
                margin: 0;
                color: #007bff;
            }
            .search-form {
                background-color: #fff;
                padding: 20px;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                width: 600px;
                margin: 20px auto;
                display: flex;
                justify-content: space-between;
            }
            .search-form input, .search-form select {
                padding: 10px;
                width: 30%;
                border: 1px solid #ddd;
                border-radius: 5px;
            }
            .search-form button {
                padding: 10px 20px;
                background-color: #ff5722;
                color: #fff;
                border: none;
                border-radius: 5px;
                cursor: pointer;
            }
            .search-form button:hover {
                background-color: #e64a19;
            }
            .tour-list {
                width: 80%;
                margin: 20px auto;
            }
            .tour-card {
                background-color: #fff;
                padding: 15px;
                margin-bottom: 15px;
                border-radius: 5px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                display: flex;
               
                align-items: center;
            }

            .image {
               width: 120px;
            height: 80px;
            object-fit: cover;
            border-radius: 5px;
            margin-right: 10px;
                
            }
            .tour-card h3 {
                margin: 0;
                color: #007bff;
            }
            .tour-card p {
                margin: 5px 0;
                color: #6c757d;
            }
            .tour-card .price {
                font-weight: bold;
                color: #28a745;
            }
            .tour-card a {
                padding: 8px 15px;
                background-color: #007bff;
                color: #fff;
                text-decoration: none;
                border-radius: 5px;
                margin-left: auto;
            }
            .tour-card a:hover {
                background-color: #0056b3;
            }
            .no-results {
                text-align: center;
                color: #dc3545;
                margin-top: 20px;
            }
            .header .auth  {
                margin-left: 10px;
                color: #6c757d;
                text-decoration: none;
                
            }
        </style>
    </head>
    <body>
        <div class="header">
            <h1>Search Tours</h1>
            <a href="${pageContext.request.contextPath}/home.jsp" class="back-link">Back to Home</a>
            <div class="auth">
                <% 
                    Users user = (Users) session.getAttribute("us");
                    if (user != null) {
                        out.print("Welcome, " + user.getName() + " | <a href='" + request.getContextPath() + "/logout'>Logout</a>");
                    } else {
                        out.print("<a href='" + request.getContextPath() + "/login'>Login</a> | <a href='" + request.getContextPath() + "/register'>Sign Up</a>");
                    }
                %>
            </div>
        </div>

        <form class="search-form" action="${pageContext.request.contextPath}/search" method="get">
            <select name="priceRange">
                <option value="">Price Range</option>
                <option value="low" <%= "low".equals(request.getParameter("priceRange")) ? "selected" : "" %>>Under 5,000,000 VND</option>
                <option value="medium" <%= "medium".equals(request.getParameter("priceRange")) ? "selected" : "" %>>5,000,000 - 7,000,000 VND</option>
                <option value="high" <%= "high".equals(request.getParameter("priceRange")) ? "selected" : "" %>>Above 7,000,000 VND</option>
            </select>            <select name="destination">
                <option value="">Destination</option>
                <option value="Northern" <%= "Northern area".equals(request.getParameter("destination")) ? "selected" : "" %>>Northern area</option>
                <option value="Middle" <%= "Middle area".equals(request.getParameter("destination")) ? "selected" : "" %>>Middle area</option>
                <option value="Southern" <%= "Southern area".equals(request.getParameter("destination")) ? "selected" : "" %>>Southern area</option>
            </select>
            <select name="duration">
                <option value="">Duration</option>
                <option value="2" <%= "2".equals(request.getParameter("duration")) ? "selected" : "" %>>2 Days</option>
                <option value="3" <%= "3".equals(request.getParameter("duration")) ? "selected" : "" %>>3 Days</option>
                <option value="4" <%= "4".equals(request.getParameter("duration")) ? "selected" : "" %>>4 Days</option>
                <option value="5" <%= "5".equals(request.getParameter("duration")) ? "selected" : "" %>>5 Day</option>
            </select>
            <button type="submit">Search</button>
        </form>

        <div class="tour-list">
            <%
                ArrayList<Tours> tours = (ArrayList<Tours>) request.getAttribute("tours");
                if (tours != null && !tours.isEmpty()) {
                    for (Tours tour : tours) {
            %>
            <div class="tour-card">  
                <div><img src="images/<%= tour. getImages() %>" alt="alt"class="image"></div>
                <div>
                    <h3><%= tour.getTourName() %></h3>
                    <p>Destination: <%= tour.getDestination() %></p>
                    <p>Duration: <%= tour.getDuration() %> Days</p>
                    <p>Description: <%= tour.getDescription() %></p>
                    <p class="price"><%= tour.getPrice() %> VND</p>
                </div>
                <a href="${pageContext.request.contextPath}/book?tourId=<%= tour.getTourID() %>">Book Now</a>
            </div>
            <%
                    }
                } else {
            %>
            <p class="no-results">No tours found matching your criteria.</p>
            <%
                }
            %>
        </div>
    </body>
</html>