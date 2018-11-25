package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;

public class SearchController {

  private boolean isNewInstance = true; // intended to set map location from previous screen on startup. Flagged false
  private int offset = 0;

  @FXML
  private BorderPane mapPane;

  @FXML
  private VBox mapBox;

  @FXML
  private TextField searchBar;

  @FXML
  private AnchorPane hotelList;

  //Side panel buttons
  public void Dashboard(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }
  public void MyAccount(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("MyAccount.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }
  public void logout(ActionEvent event) throws Exception {
    Parent Logout = FXMLLoader.load(getClass().getResource("login.fxml"));
    Scene logoutScene = new Scene(Logout);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(logoutScene);
    window.show();
  }

  public void initialize() {
    MapManager mapManager = new MapManager();

    if (isNewInstance) {
      mapManager.setAddress(DashController.getLocation());
    }
    else {
      mapManager.setAddress(searchBar.getText());
    }

    isNewInstance = false;
    mapManager.createMap();

    if (!mapManager.getErrorStatus()) {
      mapPane.getChildren().clear();
      mapPane = new BorderPane(mapManager.getMapView());

      ArrayList<String> hotelsToDisplay = mapManager.getHotelsToDisplay(); // get the list of hotels to display in the sidebar

      // create hotel objects from each line in the hotelsToDisplay array and place on dashboard

      int hotelPaneOffset  = 0; // Y offset that each successive hotel pane will need to be at

      for(String hotelString : hotelsToDisplay) {
        String[] h = hotelString.split(",");

        // create a hotel object based on the array returned from mapManager
        Hotel hotel = new Hotel(Integer.parseInt(h[0]),h[1],Double.parseDouble(h[2]),h[3],h[4],h[5],Double.parseDouble(h[6]),
                Double.parseDouble(h[7]),Integer.parseInt(h[8]),Integer.parseInt(h[9]),Integer.parseInt(h[10]));

        // create nodes for each hotel preview
        Pane hotelPane = new Pane();
        ImageView hotelImage = new ImageView(new Image("application/hotelthumbs/La-Quinta.jpg"));
        Label hotelName = new Label();
        Label hotelStars = new Label();
        Label hotelPrice = new Label();
        Label hotelRating = new Label();

        // set the height of each pane to be equal to the thumbnail in it
        hotelPane.setPrefHeight(hotelImage.getImage().getHeight());

        // set fields of preview window to those from each hotel object
        hotelName.setText(hotel.getName());
        hotelStars.setText(hotel.getStars() + "-Star Hotel");
        hotelRating.setText("User rating: 4/5");
        hotelPrice.setText("$" + hotel.getStdPrice());

        // configure layout of elements on hotel list
        double baseXOffset = hotelImage.getImage().getWidth() + 10;
        double baseYOffset = (hotelImage.getImage().getHeight() + 5) - hotelImage.getImage().getHeight();
        double yPadding = 17;

        Font hotelNameFont = new Font(14);
        Font hotelStarsFont = new Font(12);
        Font hotelRatingFont = new Font(16);

        hotelName.setLayoutX(baseXOffset);
        hotelName.setLayoutY(baseYOffset);
        hotelName.setFont(hotelNameFont);
        hotelName.setMaxWidth(260);

        hotelStars.setLayoutX(baseXOffset);
        hotelStars.setLayoutY(hotelName.getLayoutY() + yPadding);
        hotelStars.setFont(hotelStarsFont);

        hotelRating.setLayoutX(baseXOffset);
        hotelRating.setLayoutY(hotelStars.getLayoutY());
        hotelRating.setFont(hotelRatingFont);

        // add each element to the hotel preview pane one after the other
        hotelPane.getChildren().addAll(hotelImage, hotelName, hotelStars, hotelPrice);
        hotelPane.setLayoutY(hotelPane.getPrefHeight() * hotelPaneOffset);
        hotelPane.setPrefWidth(800);

        //make every other pane have a light background
        if (hotelPaneOffset % 2 == 0) {
          hotelPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#E4EFFF"), null, null)));
        }
        else {
          hotelPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"), null, null)));
        }
        hotelList.getChildren().add(hotelPane);

        // increment hotelPaneOffset so that the next hotel preview pane is situated below the last
        hotelPaneOffset++;
      }

      // for some reason the prefHeight has to be set manually and also shifted up every time the map
      // is redrawn. Unsure of reason. Please ignore magic numbers.
      mapPane.setPrefHeight(900.0 + offset);
      offset += 600;

      mapBox.getChildren().add(mapPane);
    }
  }

  public void HotelInfo(ActionEvent event) throws Exception {
    Parent Hotel = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
    Scene hotel = new Scene(Hotel);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotel);
    window.show();
  }

  public void HighToLow(ActionEvent event) throws Exception {

  }


}
