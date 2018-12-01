package application;

public class Hotel implements Comparable<Hotel>{
  private int hotelId;
  private static String name;
  private static double stars;
  private String city;
  private String countryCode;
  private String countryName;
  private double lat;
  private double lng;
  private int stdPrice;
  private int dlxPrice;
  private int suitePrice;
  private static int numReservations = 0;

  public Hotel(int hotelId, String name, double stars, String city, String countryCode, String countryName, double lat,
               double lng, int stdPrice, int dlxPrice, int suitePrice) {
    this.hotelId = hotelId;
    Hotel.name = name;
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

 // public Hotel(String name, double stars, ){}

  public void setHotelId(int id) { this.hotelId = id; }

  public int getHotelId() { return hotelId; }

  public void setName(String name) {
    Hotel.name = name;
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

  public static String getName() {
    return Hotel.name;
  }

  static double getStars() {
    return stars;
  }

  String getCity() {
    return city;
  }

  String getCountryCode() {
    return countryCode;
  }

  String getCountryName() {
    return countryName;
  }

  double getLat() {
    return lat;
  }

  double getLng() {
    return lng;
  }

  int getStdPrice() {
    return stdPrice;
  }

  public int getDlxPrice() {
    return dlxPrice;
  }

  public int getSuitePrice() {
    return suitePrice;
  }

  public void addReservation(int numUserRooms) {
    numReservations += numUserRooms;
  }
  
  @Override
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
  public int compareTo(Hotel o) {
    return (this.getStdPrice()-o.getStdPrice());
  }
}
