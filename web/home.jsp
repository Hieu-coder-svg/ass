<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Users" %>
<%@ page import="dal.DAO" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TravelTour - Home</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }
        .header {
            background-color: #f8f9fa;
            padding: 10px 20px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 1px solid #ddd;
        }
        .header .contact {
            color: #6c757d;
        }
        .header .social a {
            margin-left: 10px;
            color: #6c757d;
            text-decoration: none;
        }
        .header .auth a {
            margin-left: 10px;
            color: #6c757d;
            text-decoration: none;
        }
        .navbar {
            background-color: #fff;
            padding: 10px 20px;
            border-bottom: 1px solid #ddd;
            display: flex;
            align-items: center;
        }
        .navbar .logo {
            font-size: 24px;
            font-weight: bold;
            color: #007bff;
        }
        .navbar .nav-links a {
            margin-left: 20px;
            color: #6c757d;
            text-decoration: none;
            font-weight: 500;
        }
        .navbar .nav-links a.active {
            color: #007bff;
            border-bottom: 2px solid #007bff;
        }
        .hero {
            background: url('https://via.placeholder.com/1200x400') no-repeat center center;
            background-size: cover;
            height: 400px;
            position: relative;
            color: #fff;
            text-align: center;
            padding-top: 100px;
        }
        .hero h1 {
            font-size: 48px;
            margin: 0;
        }
        .hero p {
            font-size: 18px;
            margin: 10px 0;
        }
        .search-form {
            background-color: rgba(255, 255, 255, 0.9);
            padding: 20px;
            border-radius: 5px;
            width: 600px;
            margin: 20px auto;
            display: flex;
            justify-content: space-between;
        }
        .search-form select {
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
    </style>
</head>
<body>
    <div class="header">
        <div class="contact">
            <span>📞 +1 820-3345-33</span> | <span>📧 Contact@TravelTourWP.com</span>
        </div>
        <div class="social">
            <a href="#">Facebook</a> | <a href="#">Twitter</a> | <a href="#">Instagram</a>
        </div>
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
    <div class="navbar">
        <div class="logo">TRAVELTOUR</div>
        <div class="nav-links">
            <a href="${pageContext.request.contextPath}/home.jsp" class="active">HOME</a>
            <a href="#">PAGES</a>
            <a href="#">TOUR LIST</a>
            <a href="${pageContext.request.contextPath}/search">TOUR SEARCH</a>
            <a href="#">DESTINATIONS</a>
            <a href="#">DATE & PRICING</a>
            <a href="#">BLOG</a>
           <a href=" ${pageContext.request.contextPath}/myBooking">My Booking</a>
        </div>
    </div>
    <div class="hero">
        <h1>Find Next Place To Visit</h1>
        <p>Discover amazing places at exclusive deals</p>
        <form class="search-form" action="${pageContext.request.contextPath}/search" method="get">
            <select name="priceRange">
                <option value="">Price Range</option>
                <option value="low" <%= "low".equals(request.getParameter("priceRange")) ? "selected" : "" %>>Under 5,000,000 VND</option>
                <option value="medium" <%= "medium".equals(request.getParameter("priceRange")) ? "selected" : "" %>>5,000,000 - 7,000,000 VND</option>
                <option value="high" <%= "high".equals(request.getParameter("priceRange")) ? "selected" : "" %>>Above 7,000,000 VND</option>
            </select>
            <select name="destination">
                <option value="">Destination</option>
                <%
                    ArrayList<String> destinations = DAO.getUniqueDestinations();
                    for (String dest : destinations) {
                %>
                    <option value="<%= dest %>" <%= dest.equals(request.getParameter("destination")) ? "selected" : "" %>><%= dest %></option>
                <%
                    }
                %>
            </select>
            <select name="duration">
                <option value="">Duration</option>
                <option value="2" <%= "2".equals(request.getParameter("duration")) ? "selected" : "" %>>2 Days</option>
                <option value="3" <%= "3".equals(request.getParameter("duration")) ? "selected" : "" %>>3 Days</option>
                <option value="4" <%= "4".equals(request.getParameter("duration")) ? "selected" : "" %>>4 Days</option>
                <option value="5" <%= "5".equals(request.getParameter("duration")) ? "selected" : "" %>>5 Days</option>
            </select>
            <button type="submit">Search</button>
        </form>
    </div>
</body>
</html>