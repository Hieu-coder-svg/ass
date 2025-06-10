package controller;

import dal.DAO;
import Models.Tours;
import Models.Users;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/book")
public class BookingServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("us");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String tourIdStr = request.getParameter("tourId");
        if (tourIdStr == null || tourIdStr.isEmpty()) {
            request.setAttribute("error", "Tour ID is missing.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        int tourId;
        try {
            tourId = Integer.parseInt(tourIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Tour ID.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

      
        Tours tour = DAO.getTourById(tourId);
        if (tour == null) {
            request.setAttribute("error", "Tour not found with ID: " + tourId);
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

  
        String tourName = tour.getTourName() != null ? tour.getTourName() : "Unknown Tour";
        String destination = tour.getDestination() != null ? tour.getDestination() : "Unknown Destination";
        String description = tour.getDescription() != null ? tour.getDescription() : "No description available";
        Double price = tour.getPrice() != 0.0 ? tour.getPrice() : 0.0;
        String duration = tour.getDuration() != 0 ? String.valueOf(tour.getDuration()) : "Unknown Duration";

  
        request.setAttribute("tourId", tour.getTourID());
        request.setAttribute("tourName", tourName);
        request.setAttribute("destination", destination);
        request.setAttribute("duration", duration);
        request.setAttribute("description", description);
        request.setAttribute("price", price);

      
        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
        HttpSession session = request.getSession();
        Users user = (Users) session.getAttribute("us");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

 
        String tourIdStr = request.getParameter("tourId");
        if (tourIdStr == null || tourIdStr.isEmpty()) {
            request.setAttribute("error", "Tour ID is missing.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        int tourId;
        try {
            tourId = Integer.parseInt(tourIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid Tour ID.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

  
        String numberOfPeopleStr = request.getParameter("numberOfPeople");
        int numberOfPeople;
        try {
            numberOfPeople = Integer.parseInt(numberOfPeopleStr);
            if (numberOfPeople <= 0) {
                throw new NumberFormatException("Number of people must be greater than 0.");
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid number of people.");
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        Tours tour = DAO.getTourById(tourId);
        if (tour == null) {
            request.setAttribute("error", "Tour not found with ID: " + tourId);
            request.getRequestDispatcher("booking.jsp").forward(request, response);
            return;
        }

        double price = tour.getPrice() != 0.0 ? tour.getPrice() : 0.0;
        double totalPrice = price * numberOfPeople;

        boolean success = DAO.bookTour(user.getId(), tourId, numberOfPeople, totalPrice);
        if (success) {
            request.setAttribute("message", "Booking successful!");
        } else {
            request.setAttribute("error", "Failed to book the tour. Please try again.");
        }

        request.getRequestDispatcher("booking.jsp").forward(request, response);
    }
}