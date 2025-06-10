package controller;

import Models.Users;
import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

public class LoginServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String u = request.getParameter("name");
            String p = request.getParameter("password");

            ArrayList<Users> users = DAO.getUser();
            if (users == null || users.isEmpty()) {
                response.sendRedirect(request.getContextPath() + "/login?error=server");
                return;
            }

            Users currentUser = null;
            for (Users user : users) {
                if (user.getInname().equals(u) && user.getPassword().equals(p)) {
                    currentUser = user;
                    break;
                }
            }

            if (currentUser != null) {
                HttpSession ses = request.getSession();
                ses.setAttribute("us", currentUser);
               
                if ("Admin".equalsIgnoreCase(currentUser.getRole())) {
                    response.sendRedirect(request.getContextPath() + "/admin");
                } else if("Moderator".equalsIgnoreCase(currentUser.getRole())) {
                  response.sendRedirect(request.getContextPath() + "/moderator");
                }
                else {
                    request.getRequestDispatcher("/home.jsp").forward(request, response); 
                }
            } else {
                response.sendRedirect(request.getContextPath() + "/login?error=invalid");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/login?error=server");
        }
    }

    @Override
    public String getServletInfo() {
        return "Handles user login functionality";
    }
}