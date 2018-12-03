package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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

  String review;
  String rating;

  String URL = Credentials.getUrl();
  String hotelID = HotelController.getHotel().getHotelId();

  static ObservableList<Review> reviewsList;


  public void initialize() throws Exception {
    updateList();


  }

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
    try {
      Class.forName(Credentials.getDriver());
      Connection reviewConnection = DriverManager.getConnection(URL);
      Statement stmt = reviewConnection.createStatement();
      stmt.executeUpdate(create_Table_SQL);
      reviewConnection.close();
      submitButton(new ActionEvent());


    } catch (SQLException ex) {
      ex.printStackTrace();
      Class.forName(Credentials.getDriver());
      Connection reviewConnection = DriverManager.getConnection(URL);
      Statement stmt = reviewConnection.createStatement();
      stmt.executeUpdate(create_Review_SQL);
      reviewConnection.close();
    }
    initialize();
  }

  public void updateList() throws Exception{
    reviewsList = FXCollections.observableArrayList();
    try {
      String get_review_SQL = "SELECT * FROM REVIEW." +"\"" + hotelID +"\"" ;

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



//    for (Review review: reviewsList) {
//      Pane reviewPane = new Pane();
//      int reviewPaneOffset = 100;
//
//      Label userName = new Label();
//      Label reviewLabel = new Label();
//      Label userRating = new Label();
//
//      reviewPane.setPrefHeight(100);
//
//      // set fields of preview window to those from each hotel object
//      Text reviewText = new Text(review.getReview());
//      Text userRatingText = new Text(Integer.toString(review.getUserRating()));
//      Text userNameText = new Text(review.getUsername());
//
//      userName.setText(userNameText.getText());
//      reviewLabel.setText(reviewText.getText());
//      userRating.setText(userRatingText.getText());
//
//      // configure layout of elements on review list
//      int xPadding = 100;
//
//      userName.setLayoutX(0);
//      userName.setLayoutY(0);
//      userRating.setLayoutX(userName.getLayoutX() + xPadding);
//      userRating.setLayoutY(userName.getLayoutY());
//      reviewLabel.setLayoutX(userRating.getLayoutX() + xPadding);
//      reviewLabel.setLayoutY(userRating.getLayoutY());
//
//      reviewPane.getChildren().addAll(userName, reviewLabel, userRating);
//      reviewPane.setLayoutY(reviewPane.getPrefHeight() * reviewPaneOffset);
//      reviewPane.setPrefWidth(userName.getPrefWidth() + userRating.getPrefWidth() +
//          reviewLabel.getPrefWidth());
//
//      reviewList.getChildren().add(reviewPane);
//      reviewPaneOffset++;
  }


  public void backButton(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
}
