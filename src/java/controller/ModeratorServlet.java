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

public class ModeratorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("us") == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        Users currentUser = (Users) session.getAttribute("us");
        if (!"Moderator".equalsIgnoreCase(currentUser.getRole())) {
            response.sendRedirect(request.getContextPath() + "/search");
            return;
        }

        ArrayList<Users> users = DAO.getUser();
        request.setAttribute("users", users);
        request.getRequestDispatcher("moderatorBoard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("us") == null || !"Moderator".equalsIgnoreCase(((Users) session.getAttribute("us")).getRole())) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String userIdStr = request.getParameter("userId");
        String action = request.getParameter("action");
        int userId = Integer.parseInt(userIdStr);

        boolean success = false;
      
        if ("delete".equals(action)) {
            success = DAO.deleteUser(userId);
            if (success) {
                session.setAttribute("message", "User has been deleted.");
            } else {
                session.setAttribute("error", "Failed to delete user.");

            }
        }
        
        response.sendRedirect(request.getContextPath() + "/moderator");
    }

    @Override
    public String getServletInfo() {
        return "Handles moderator dashboard functionality";
    }
}
