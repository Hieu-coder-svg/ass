package controller;

import Models.Users;
import dal.DAO;
import java.io.IOException;
import java.sql.Date;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        
            String name = request.getParameter("name");
            Date birthDate = Date.valueOf(request.getParameter("birthDate"));
            boolean gender = "1".equals(request.getParameter("gender"));
            String phoneNumber = request.getParameter("phoneNumber");
            String email = request.getParameter("email");
            String address = request.getParameter("address");
            String inname = request.getParameter("inname");
            String password = request.getParameter("password");

            ArrayList<Users> users = DAO.getUser();
           
            boolean HasError = false;
            if (DAO.getUserByInname(inname) != null) {
                request.setAttribute("errorInName", "Username already exists!");
                HasError = true;
            }
            if (name.length() < 10) {
                request.setAttribute("errorName", "Your name must be at least 10 characters");
                HasError = true;
            }
            if (password.length() < 6) {
                request.setAttribute("errorPass", "Your password must be at least 6 characters");
                HasError = true;
            }
            if (DAO.getUserByPhone(phoneNumber) != null) {
                request.setAttribute("errorPhone", "Your Phone already exists!");
                HasError = true;
            }
            if (Integer.parseInt(phoneNumber.trim()) < 0) {
                request.setAttribute("errorPhoneNu", "Wrong phone number");
                HasError = true;
            } else if (phoneNumber.length() != 10) {
                request.setAttribute("errorPhoneN", "Your phone number must be 10 characters");
                HasError = true;
            }

            if (HasError) {
                request.getRequestDispatcher("register.jsp").forward(request, response);

            } else {
             
                Users newUser = new Users(users.size() + 1, name, birthDate, gender, phoneNumber, email, address, inname, password, "user");

             
                DAO.addUser(newUser);

             
                request.setAttribute("success", "Registration successful! Please login.");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred during registration. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles user registration functionality";
    }
}
