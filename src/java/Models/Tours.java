package Models;

import java.util.Date;

public class Tours {
    private int tourID;
    private String tourName;
    private String description;
    private double price;
    private int duration;
    private String destination;
    private Date startDate;
private String images;

    public Tours() {
    }

    public Tours(int tourID, String tourName, String description, double price, int duration, String destination, Date startDate, String images) {
        this.tourID = tourID;
        this.tourName = tourName;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.destination = destination;
        this.startDate = startDate;
        this.images = images;
    }

    public int getTourID() {
        return tourID;
    }

    public void setTourID(int tourID) {
        this.tourID = tourID;
    }

    public String getTourName() {
        return tourName;
    }

    public void setTourName(String tourName) {
        this.tourName = tourName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
  
}