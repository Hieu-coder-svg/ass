package controller;

import Models.Users;
import dal.DAO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("us") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Users currentUser = (Users) session.getAttribute("us");
        if (!"Admin".equalsIgnoreCase(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/search");
            return;
        }

        ArrayList<Users> users = DAO.getUser();
        request.setAttribute("users", users);
        request.getRequestDispatcher("adminBoard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("us") == null || !"Admin".equalsIgnoreCase(((Users) session.getAttribute("us")).getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String userIdStr = request.getParameter("userId");
        String action = request.getParameter("action");
        int userId = Integer.parseInt(userIdStr);

        boolean success = false;
        if (action.equals("add")) {
            success = DAO.updateUserRole(userId, "Moderator");
            if (success) {
                session.setAttribute("message", "User has been made a Moderator.");
            } else {
                session.setAttribute("error", "Failed to make user a Moderator.");
            }
        }
        if ("remove".equals(action)) {
            success = DAO.updateUserRole(userId, "User");
            if (success) {
                session.setAttribute("message", "Moderator status has been removed.");
            } else {
                session.setAttribute("error", "Failed to remove Moderator status.");
            }
        }
        if ("delete".equals(action)) {
            success = DAO.deleteUser(userId);
            if (success) {
                session.setAttribute("message", "User has been deleted.");
            } else {
                session.setAttribute("error", "Failed to delete user.");

            }
        }
        response.sendRedirect(request.getContextPath() + "/admin");
    }

    @Override
    public String getServletInfo() {
        return "Handles admin dashboard functionality";
    }
}
