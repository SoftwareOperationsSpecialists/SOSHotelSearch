package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ReviewsController {

  @FXML
  TextField Review;
  @FXML
  ChoiceBox<Integer> userRating;

  String review;
  Integer rating;

  String URL = Credentials.getUrl();
  String hotelID = Integer.toString(Integer.parseInt(HotelController.getHotel().getHotelId()));


  String check_For_Table_SQL = " SELECT USERNAME FROM REVIEW." + hotelID;

  String create_Table_SQL = "CREATE TABLE REVIEW." + hotelID
      + "("
      + "USERNAME varchar(255),"
      + "REVIEW varchar(255),"
      + "USER_RATING int"
      + ")";

  String create_Review_SQL = "INSERT INTO REVIEW." + hotelID
      + " ( USERNAME, REVIEW, USER_RATING) "
      + "VALUES ("
      + "'" + Credentials.getClientUsername() + "', "
      + "'" + review + "', "
      + rating
      + " )";

  public void submitButton(ActionEvent event) throws Exception {
    try {
      Class.forName(Credentials.getDriver());
      Connection reviewConnection = DriverManager.getConnection(URL);
      Statement stmt = reviewConnection.createStatement();
    } catch (SQLException ex) {
      System.out.println();
    }
    try {
      review = Review.getText();
      rating = userRating.getSelectionModel().getSelectedItem();

      Class.forName(Credentials.getDriver());
      Connection reviewConnection = DriverManager.getConnection(URL);
      Statement stmt = reviewConnection.createStatement();
      stmt.executeQuery(create_Review_SQL);
      reviewConnection.close();

    } catch (SQLException ex) {
      Class.forName(Credentials.getDriver());
      Connection reviewConnection = DriverManager.getConnection(URL);
      Statement stmt = reviewConnection.createStatement();
      stmt.executeQuery(create_Table_SQL);
      reviewConnection.close();
    }

//    try {
//       review = Review.getText();
//       rating = userRating.getSelectionModel().getSelectedItem();
//
//      Class.forName(Credentials.getDriver());
//      Connection reviewConnection = DriverManager.getConnection(URL);
//      Statement statement = reviewConnection.createStatement();
//      ResultSet resultSet = statement.executeQuery(check_For_Table_SQL);
//
//      if (resultSet.next()) {
//        Statement stmt = reviewConnection.createStatement();
//        stmt.executeQuery(create_Review_SQL);
//
//      } else {
//        Statement stmt = reviewConnection.createStatement();
//        stmt.executeQuery(create_Table_SQL);
//      }
//
//    } catch (SQLException ex) {
//      System.out.println(ex);
//    }
  }

  public void backButton(ActionEvent event) throws Exception {
    Navigator.hotelInfo(event);
  }
}
