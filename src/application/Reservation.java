package application;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    //initialization
    private Hotel hotel;
    private long numOfNights;
    private int numOfRooms;
    private long finalCost;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    //makes a reservation with the check-in date, checkout date, and number of rooms as parameters
    public Reservation(LocalDate checkInDate,
                       LocalDate checkOutDate, int numOfRooms) {
        this.checkInDate = checkInDate;     //check-in date
        this.checkOutDate = checkOutDate;   //check-out date
        this.numOfRooms = numOfRooms;       //number of rooms
        numOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);   //gets the number of nights by finding the difference
        System.out.println(numOfNights);                                    //  between the check-in and check-out dates
    }

    public Hotel getHotel() {
        return hotel;
    }

    //sets the hotel to the current hotel and finds the final cost based on standard price, number of rooms, and nights
    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
        finalCost = hotel.getStdPrice() * numOfRooms * numOfNights;
    }

    public long getNumOfNights() {
        return numOfNights;
    }

    //sets number of nights to difference between check-in and check-out dates
    public void setNumOfNights(LocalDate checkInDate, LocalDate checkOutDate) {
      numOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
    }

    public int getNumOfRooms() {
        return numOfRooms;
    }

    public void setNumOfRooms(int numberOfRooms) {
        this.numOfRooms = numberOfRooms;
    }

    public long getFinalCost() {
        return finalCost;
    }

    public LocalDate getCheckInDate() {
      return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
      this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
      return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
      this.checkOutDate = checkOutDate;
    }
}
