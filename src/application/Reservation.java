package application;


import java.time.LocalDate;

public class Reservation {
    private Hotel hotel;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private int numberOfRooms;
    private int numberOfNights;
    private int finalCost;

    public Reservation(Hotel hotel, LocalDate checkInDate,
                LocalDate checkOutDate, int numberOfRooms) {

        this.hotel = hotel;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.numberOfRooms = numberOfRooms;
        this.numberOfNights = Math.abs(checkInDate.compareTo(checkOutDate));
        this.finalCost = hotel.getPrice() * numberOfRooms * numberOfNights;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
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

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public void setNumberOfNights(LocalDate date1, LocalDate date2) {
        this.numberOfNights = Math.abs(date1.compareTo(date2));
    }

    public int getNumberOfNights() {
        return this.numberOfNights;
    }

    public int getFinalCost() {
        return finalCost;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "hotel=" + hotel +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", numberOfRooms=" + numberOfRooms +
                ", numberOfNights=" + numberOfNights +
                ", finalCost=" + finalCost +
                '}';
    }
}
