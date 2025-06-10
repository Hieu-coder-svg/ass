package Models;

import java.sql.Date;

public class Bookings {
    private int bookingID;
    private int userID;
    private int tourID;
    private Date bookingDate;
    private int numberOfPeople;
    private double totalPrice;
    private String status;
    private Tours tour; 

    
    public Bookings() {
    }

    public Bookings(int bookingID, int userID, int tourID, Date bookingDate, int numberOfPeople, double totalPrice, String status, Tours tour) {
        this.bookingID = bookingID;
        this.userID = userID;
        this.tourID = tourID;
        this.bookingDate = bookingDate;
        this.numberOfPeople = numberOfPeople;
        this.totalPrice = totalPrice;
        this.status = status;
        this.tour = tour;
    }

    // Getters and Setters
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Tours getTour() {
        return tour;
    }

    public void setTour(Tours tour) {
        this.tour = tour;
    }
}