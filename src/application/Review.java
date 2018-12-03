package application;

public class Review {

  private String username;
  private String review;
  private String userRating;


  public Review(String username, String review, String userRating) {
    this.username = username;
    this.review = review;
    this.userRating = userRating;
  }

  public String getUsername() {
    return username;
  }

  public String getReview() {
    return review;
  }

  public String getUserRating() {
    return userRating;
  }
}
