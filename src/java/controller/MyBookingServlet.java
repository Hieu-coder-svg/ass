package controller;

import dal.DAO;
import Models.Users;
import Models.Bookings;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.logging.Level;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class MyBookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
   
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("us");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

       
        ArrayList<Bookings> bookings = DAO.INSTANCE.getBookingsByUserId(user.getId());
        request.setAttribute("bookings", bookings);
        String bookingIdStr = request.getParameter("bookingId");
        if (bookingIdStr == null || bookingIdStr.isEmpty()) {
            request.setAttribute("error", "Booking ID is missing.");
            request.getRequestDispatcher("myBooking.jsp").forward(request, response);
            return;
        }

        int bookingId;
        try {
            bookingId = Integer.parseInt(bookingIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Booking ID.");
            request.getRequestDispatcher("myBooking.jsp").forward(request, response);
            return;
        }

    
        boolean success = DAO.INSTANCE.deleteBooking(bookingId);
        if (success) {
            request.setAttribute("message", "Booking deleted successfully!");
        } else {
            request.setAttribute("error", "Failed to delete the booking. It may have already been confirmed or does not exist.");
        }
      
        request.getRequestDispatcher("myBooking.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DAO dao = DAO.INSTANCE;
         HttpSession session = request.getSession(false);
        String userIdStr = request.getParameter("bookingId");
        String action = request.getParameter("action");
        int userId = Integer.parseInt(userIdStr);

        boolean success = false;

        if ("delete".equals(action)) {
            success = dao.deleteBooking(userId);
            if (success) {
                session.setAttribute("message", "User has been deleted.");
            } else {
                session.setAttribute("error", "Failed to delete user.");

            }
        }

        response.sendRedirect(request.getContextPath() + "/myBooking");
    }
}
