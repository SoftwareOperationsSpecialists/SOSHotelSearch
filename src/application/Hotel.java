package application;

public class Hotel {
  private int hotelId;
  private String name;
  private double stars;
  private String city;
  private String countryCode;
  private String countryName;
  private double lat;
  private double lng;
  private int stdPrice;
  private int dlxPrice;
  private int suitePrice;
  
  // holds the unique hotel information
  public Hotel(int hotelId, String name, double stars, String city, String countryCode, String countryName, double lat,
               double lng, int stdPrice, int dlxPrice, int suitePrice) {
    this.hotelId = hotelId;           // id to identify this specific hotel
    this.name = name;                 // name of hotel
    this.stars = stars;               // star rating (1-5)
    this.city = city;                 // city of hotel
    this.countryCode = countryCode;   // country code
    this.countryName = countryName;   // country name
    this.lat = lat;                   // latitude
    this.lng = lng;                   // longitude
    this.stdPrice = stdPrice;         // standard price
    this.dlxPrice = dlxPrice;         // deluxe price
    this.suitePrice = suitePrice;     // suite price
  }

  public void setHotelId(int id) { this.hotelId = id; }

  public int getHotelId() { return hotelId; }

  // "set" functions set the value of the respective variable
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

  // "get" functions return the value of the respective variable
  public String getName() {
    return name;
  }

  public double getStars() {
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
}
