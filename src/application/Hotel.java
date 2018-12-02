package application;

public class Hotel implements Comparable<Hotel>{
  private int hotelId;
  private String name;
  private double stars;
  private String city;
  private String countryCode;
  private String countryName;
  private double lat;
  private double lng;
  private int price;
  private static int numReservations = 0;

  public Hotel(int hotelId, String name, double stars, String city, String countryCode, String countryName, double lat,
               double lng, int price) {
    this.hotelId = hotelId;
    this.name = name;
    this.stars = stars;
    this.city = city;
    this.countryCode = countryCode;
    this.countryName = countryName;
    this.lat = lat;
    this.lng = lng;
    this.price = price;
  }

 // public Hotel(String name, double stars, ){}

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

  public void setPrice(int price) {
    this.price = price;
  }

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

  public int getPrice() {
    return this.price;
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
            ", price=" + price +
            ", numReservations=" + numReservations +
            '}';
  }

  @Override
  public int compareTo(Hotel o) {
    return (this.getPrice()-o.getPrice());
  }
}
