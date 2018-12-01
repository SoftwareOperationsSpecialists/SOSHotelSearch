package application;


import java.time.LocalDate;

public class Reservation {
    private Hotel hotel;
    private String hotelName;
    private static LocalDate checkInDate;
    private static LocalDate checkOutDate;
    private int numberOfRooms;
    private static int finalCost;

    Reservation(Hotel hotel, LocalDate checkInDate,
                LocalDate checkOutDate, int numberOfRooms) {
        this.hotel = hotel;
        this.hotelName = hotel.getName();
        Reservation.checkInDate = checkInDate;
        Reservation.checkOutDate = checkOutDate;
        this.numberOfRooms = numberOfRooms;
        finalCost = hotel.getStdPrice() * numberOfRooms;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    static LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        Reservation.checkInDate = checkInDate;
    }

    static LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        Reservation.checkOutDate = checkOutDate;
    }

    public int getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(int numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    static int getFinalCost() {
        return finalCost;
    }
}
