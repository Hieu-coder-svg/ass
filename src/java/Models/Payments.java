/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Models;

public class Payments {
    private int paymentID;
    private int amount;
    private Bookings bType;

    public Payments() {
    }

    public Payments(int paymentID, int amount, Bookings bType) {
        this.paymentID = paymentID;
        this.amount = amount;
        this.bType = bType;
    }

    public int getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(int paymentID) {
        this.paymentID = paymentID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Bookings getbType() {
        return bType;
    }

    public void setbType(Bookings bType) {
        this.bType = bType;
    }
    
}
