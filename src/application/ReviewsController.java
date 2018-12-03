package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class ReviewsController {
  @FXML
  private StackPane reviewList;
  @FXML
  TextField Review;
  @FXML
  ChoiceBox<Integer> userRating;

  String review;
  Integer rating;

  String URL = Credentials.getUrl();
  String hotelID = HotelController.getHotel().getHotelId();


  public void initialize(){
    ArrayList<>
  }

  public void submitButton(ActionEvent event) throws Exception {
    review = Review.getText();
    rating = userRating.getSelectionModel().getSelectedItem();

    String create_Review_SQL = "INSERT INTO REVIEW." + "\"" + hotelID + "\""
        + " ( USERNAME, REVIEW, USER_RATING) "
        + "VALUES ("
        + "'" + Credentials.getClientUsername() + "', "
        + "'" + review + "', "
        + rating
        + " )";

    String create_Table_SQL = "CREATE TABLE REVIEW." + "\"" + hotelID + "\""
        + "("
        + "USERNAME varchar(255),"
        + "REVIEW varchar(255),"
        + "USER_RATING int"
        + ")";
    try {
      Class.forName(Credentials.getDriver());
      Connection reviewConnection = DriverManager.getConnection(URL);
      Statement stmt = reviewConnection.createStatement();
      stmt.executeUpdate(create_Table_SQL);
      System.out.println("Created new Table");
      reviewConnection.close();
      submitButton(new ActionEvent());


    } catch (SQLException ex) {
      Class.forName(Credentials.getDriver());
      Connection reviewConnection = DriverManager.getConnection(URL);
      Statement stmt = reviewConnection.createStatement();
      stmt.executeUpdate(create_Review_SQL);
      reviewConnection.close();
      System.out.println("Created Review");

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
