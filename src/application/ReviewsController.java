package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

/**
* Desc: creates review list and allows user to submit reviews
*/
public class ReviewsController {

  @FXML
  private TextField Review;
  @FXML
  private ChoiceBox<String> userRating;
  @FXML
  private TableView reviewTable;
  @FXML
  private TableColumn usernameColumn;
  @FXML
  private TableColumn userRatingColumn;
  @FXML
  private TableColumn reviewColumn;
  @FXML
  private Label status;

  String review;
  String rating;

  String URL = Credentials.getUrl();
  String hotelID = HotelController.getHotel().getHotelId();

  static ObservableList<Review> reviewsList;

   /**
  * Desc: loads the list of reviews
  * @throws: Exception
  */
  public void initialize() throws Exception {
    updateList();


  }

  /**
  * Desc: submits the review
  * @param: event - the ActionEvent for the button
  * @throws: Exception
  */
  public void submitButton(ActionEvent event) throws Exception {
    review = Review.getText();
    rating = userRating.getSelectionModel().getSelectedItem();

    String create_Review_SQL = "INSERT INTO REVIEW." + "\"" + hotelID + "\""
        + " ( USERNAME, REVIEW, USER_RATING) "
        + "VALUES ("
        + "'" + Credentials.getClientUsername() + "', "
        + "'" + review + "', "
        + "'" + rating + "'"
        + " )";

    String create_Table_SQL = "CREATE TABLE REVIEW." + "\"" + hotelID + "\""
        + "("
        + "USERNAME varchar(255),"
        + "REVIEW varchar(255),"
        + "USER_RATING varchar(10) "
        + ")";

    if (review.length() < 10 || rating == null) {
      status.setText("Please enter more text and choose a rating.");
    } else if (review.length() > 255) {
      status.setText("Review is too long. 255 characters maximum.");
    }
    else {
      try {
        Class.forName(Credentials.getDriver());
        Connection reviewConnection = DriverManager.getConnection(URL);
        Statement stmt = reviewConnection.createStatement();
        stmt.executeUpdate(create_Table_SQL);
        reviewConnection.close();
        submitButton(new ActionEvent());


      } catch (SQLException ex) {
        Class.forName(Credentials.getDriver());
        Connection reviewConnection = DriverManager.getConnection(URL);
        Statement stmt = reviewConnection.createStatement();
        stmt.executeUpdate(create_Review_SQL);
        reviewConnection.close();
      }

      initialize();
    }
  }

  /**
  * Desc: updates the review list
  * @throws: Exception
  */
  public void updateList() throws Exception {
    reviewsList = FXCollections.observableArrayList();
    try {
      String get_review_SQL = "SELECT * FROM REVIEW." + "\"" + hotelID + "\"";

      Connection getReviewConnection = DriverManager.getConnection(URL);
      Statement statement = getReviewConnection.createStatement();
      ResultSet resultSet = statement.executeQuery(get_review_SQL);

      while (resultSet.next()) {

        Review review = new Review(
            resultSet.getString("USERNAME"),
            resultSet.getString("REVIEW"),
            resultSet.getString("USER_RATING"));
        reviewsList.add(review);

      }
      usernameColumn.setCellValueFactory(new PropertyValueFactory<Review, String>("username"));
      userRatingColumn.setCellValueFactory(new PropertyValueFactory<Review, String>("userRating"));
      reviewColumn.setCellValueFactory(new PropertyValueFactory<Review, String>("review"));

      reviewTable.getItems().setAll(reviewsList);
    } catch (Exception ex) {
      ex.printStackTrace();
      System.out.println("No reviews");
    }
  }

  /**
  * Desc: goes back to the hotel info scene
  * @param: event - the ActionEvent for the button
  * @throws: Exception
  */
  public void backButton(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
}
