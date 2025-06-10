<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="Models.Users" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Admin Dashboard - TravelTour</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f8f9fa;
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
            text-align: center;
        }
        .message, .error {
            text-align: center;
            margin-top: 10px;
        }
        .message {
            color: green;
        }
        .error {
            color: red;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #007bff;
            color: #fff;
        }
        tr:hover {
            background-color: #f1f1f1;
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
        .back-link {
            display: block;
            text-align: center;
            margin-top: 20px;
            color: #007bff;
            text-decoration: none;
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
                    out.print("<a href='" + request.getContextPath() + "/login'>Login</a> | <a href='" + request.getContextPath() + "/register'>Sign Up</a>");
                }
            %>
        </div>
    </div>

    <h1>Admin Dashboard</h1>

    <div class="message">
        <% 
            String message = (String) session.getAttribute("message");
            if (message != null) {
                out.print(message);
                session.removeAttribute("message");
            }
        %>
    </div>
    <div class="error">
        <% 
            String error = (String) session.getAttribute("error");
            if (error != null) {
                out.print(error);
                session.removeAttribute("error");
            }
        %>
    </div>

    <table>
        <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>UserName</th>
                <th>Role</th>
                <th>Actions</th>
            </tr>
        </thead>
        <tbody>
            <% 
                ArrayList<Users> users = (ArrayList<Users>) request.getAttribute("users");
                if (users != null) {
                    for (Users u : users) {
            %>
            <tr>
                <td><%= u.getId() %></td>
                <td><%= u.getName() %></td>
                <td><%= u.getEmail() != null ? u.getEmail() : "N/A" %></td>
                <td><%= u.getInname() %></td>
                <td><%= u.getRole() %></td>
                <td>
                    <% if (!"Admin".equalsIgnoreCase(u.getRole())) { %>
                        <form action="${pageContext.request.contextPath}/admin" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="<%= u.getId() %>">
                            <% if ("User".equalsIgnoreCase(u.getRole())) { %>
                                <button type="submit" name="action" value="add" class="action-btn grant-btn">Add Moderator</button>
                            <% } else if ("Moderator".equalsIgnoreCase(u.getRole())) { %>
                                <button type="submit" name="action" value="remove" class="action-btn revoke-btn">Remove Moderator</button>
                            <% } %>
                        </form>
                        <form action="${pageContext.request.contextPath}/admin" method="post" style="display:inline;">
                            <input type="hidden" name="userId" value="<%= u.getId() %>">
                            <button type="submit" name="action" value="delete" class="action-btn delete-btn">Delete</button>
                        </form>
                    <% } %>
                </td>
            </tr>
            <% 
                    }
                }
            %>
        </tbody>
    </table>
    <a href="${pageContext.request.contextPath}/home.jsp" class="back-link">Back to Home</a>
</body>
</html>