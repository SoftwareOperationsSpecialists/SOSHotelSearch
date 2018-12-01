package application;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Reservation {
    private Hotel hotel;
    private static LocalDate checkInDate;
    private static LocalDate checkOutDate;
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static String bookedCheckInDate;
    static String bookedCheckOutDate;
    private int numberOfRooms;
    private static int finalCost;

    public Reservation(Hotel hotel, LocalDate checkInDate,
                LocalDate checkOutDate, int numberOfRooms) {

        this.hotel = hotel;
        Reservation.checkInDate = checkInDate;
        Reservation.checkOutDate = checkOutDate;
        Reservation.bookedCheckInDate = checkInDate.format(formatter);
        Reservation.bookedCheckOutDate = checkOutDate.format(formatter);
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

    public static String getBookedCheckOutDate() {
        return bookedCheckOutDate;
    }

    public static void setBookedCheckOutDate(String bookedCheckOutDate) {
        Reservation.bookedCheckOutDate = bookedCheckOutDate;
    }
    public static String getBookedCheckIutDate() {
        return bookedCheckInDate;
    }

    public static void setBookedCheckInDate(String bookedCheckInDate) {
        Reservation.bookedCheckInDate = bookedCheckInDate;
    }

}
