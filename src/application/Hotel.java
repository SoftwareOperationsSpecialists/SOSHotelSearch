package application;

public class Hotel implements Comparable<Hotel>{
  private int hotelId;
  private String name;
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
    this.name = name;
    this.stars = stars;
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

  public void setName(String name) {
    this.name = name;
  }

  public void setStars(double stars) {
    this.stars = stars;
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
