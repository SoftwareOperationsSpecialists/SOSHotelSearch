package application;

public class Hotel implements Comparable<Hotel>{
  private int hotelId;            //identifies the specific hotel
  private String name;            //hotel name
  private static double stars;    //stars
  private String city;            //city
  private String countryCode;     //country name abbreviation
  private String countryName;     //country name
  private double lat;             //latitude
  private double lng;             //longitude
  private int stdPrice;           //standard price
  private int dlxPrice;           //deluxe price
  private int suitePrice;         //suite price
  private static int numReservations = 0; //number of reservations

  //Hotels will store their own specific data from the parameters
  public Hotel(int hotelId, String name, double stars, String city, String countryCode, String countryName, double lat,
               double lng, int stdPrice, int dlxPrice, int suitePrice) {
    this.hotelId = hotelId;
    this.name = name;
    Hotel.stars = stars;
    this.city = city;
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.lat = lat;
    this.lng = lng;
    this.stdPrice = stdPrice;
    this.dlxPrice = dlxPrice;
    this.suitePrice = suitePrice;
  }

  public void setHotelId(int id) { this.hotelId = id; }

  public int getHotelId() { return hotelId; }

  //"set" functions will set assign the respective variable to this specific hotel
  public void setName(String name) {
    this.name = name;
  }

  public void setStars(double stars) {
    Hotel.stars = stars;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public void setCountryCode(String countryCode) {
    this.countryCode = countryCode;
  }

  public void setCountryName(String countryName) {
    this.countryName = countryName;
  }

  public void setLat(double lat) {
    this.lat = lat;
  }

  public void setLng(double lng) {
    this.lng = lng;
  }

  public void setStdPrice(int stdPrice) {
    this.stdPrice = stdPrice;
  }

  public void setDlxPrice(int dlxPrice) {
    this.dlxPrice = dlxPrice;
  }

  public void setSuitePrice(int suitePrice) {
    this.suitePrice = suitePrice;
  }

  //"get" functions return the respective variable
  public String getName() {
    return name;
  }

  public static double getStars() {
    return stars;
  }

  public String getCity() {
    return city;
  }

  public String getCountryCode() {
    return countryCode;
  }

  public String getCountryName() {
    return countryName;
  }

  public double getLat() {
    return lat;
  }

  public double getLng() {
    return lng;
  }

  public int getStdPrice() {
    return stdPrice;
  }

  public int getDlxPrice() {
    return dlxPrice;
  }

  public int getSuitePrice() {
    return suitePrice;
  }

  //increases number of reservations by numUserRooms
  public void addReservation(int numUserRooms) {
    numReservations += numUserRooms;
  }
  
  @Override
  //prints all hotel information
  public String toString() {
    return "Hotel{" +
            "hotelId=" + hotelId +
            ", name='" + name + '\'' +
            ", stars=" + stars +
            ", city='" + city + '\'' +
            ", countryCode='" + countryCode + '\'' +
            ", countryName='" + countryName + '\'' +
            ", lat=" + lat +
            ", lng=" + lng +
            ", stdPrice=" + stdPrice +
            ", dlxPrice=" + dlxPrice +
            ", suitePrice=" + suitePrice +
            ", numReservations=" + numReservations +
            '}';
  }

  @Override
  //compares the standard price of hotels
  public int compareTo(Hotel o) {
    return (this.getStdPrice()-o.getStdPrice());
  }
}
