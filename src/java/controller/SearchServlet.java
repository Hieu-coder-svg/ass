package controller;


import Models.Tours;
import dal.DAO;
import java.io.IOException;
import java.util.ArrayList;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String keywords = request.getParameter("keywords");
        String destination = request.getParameter("destination");
        String durationStr = request.getParameter("duration");
        String priceRange = request.getParameter("priceRange");

        if (keywords == null) keywords = "";
        if (destination == null) destination = "";
        int duration = durationStr != null && !durationStr.isEmpty() ? Integer.parseInt(durationStr) : 0;

        
        double minPrice = 0;
        double maxPrice = Double.MAX_VALUE;
        if (priceRange != null) {
            switch (priceRange) {
                case "low":
                    maxPrice = 5000000;
                    break;
                case "medium":
                    minPrice = 5000000;
                    maxPrice = 7000000;
                    break;
                case "high":
                    minPrice = 7000000;
                    break;
            }
        }
        ArrayList<Tours> tours = DAO.searchTours(keywords, destination, duration, minPrice, maxPrice);
        request.setAttribute("tours", tours);
        request.getRequestDispatcher("searchTour.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Handles tour search functionality";
    }
}