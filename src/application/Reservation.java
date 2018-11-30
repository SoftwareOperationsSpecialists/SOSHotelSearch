package application;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private Hotel hotel;
    private long numOfNights;
    private int numOfRooms;
    private long finalCost;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    public Reservation(LocalDate checkInDate,
                       LocalDate checkOutDate, int numOfRooms) {
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numOfRooms = numOfRooms;
        numOfNights = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
        System.out.println(numOfNights);
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
        finalCost = hotel.getStdPrice() * numOfRooms * numOfNights;
    }

    public long getNumOfNights() {
        return numOfNights;
    }

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
