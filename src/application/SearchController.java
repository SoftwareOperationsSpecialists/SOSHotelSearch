package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;

public class SearchController {

  // intended to set map location from previous screen on startup. Flagged false
  private boolean isNewInstance = true;
  private int offset = 0;
  private boolean isLowToHigh = false;
  private ArrayList<Hotel> hotels;
  private static Navigator navigator = new Navigator();

  @FXML
  private BorderPane mapPane;

  @FXML
  private VBox mapBox;

  @FXML
  private TextField searchBar;

  @FXML
  private AnchorPane hotelList;

  //Side panel buttons
  public void dashboard(ActionEvent event) throws Exception {
    navigator.dashboard(event);
  }
  public void myAccount(ActionEvent event) throws Exception {
    navigator.myAccount(event);
  }
  public void logout(ActionEvent event) throws Exception {
    navigator.logout(event);
  }

  public void initialize() {
    // need to pass the current stage to the constructor of the mapManager
      MapManager mapManager = new MapManager();

    // get list of hotels returned from mapManager
    hotels = mapManager.getHotelsToDisplay();

    if (isNewInstance) {
      mapManager.setAddress(DashController.getLocation());
    } else {
      mapManager.setAddress(searchBar.getText());
    }

    isNewInstance = false;
    mapManager.createMap();

    if (!mapManager.getErrorStatus()) {
      mapPane.getChildren().clear();
      mapPane = new BorderPane(mapManager.getMapView());
    }

    sortList();
  }

  private void sortList() {
    //clear any children on the list
    hotelList.getChildren().removeAll();

    // sort the list based on price (either high to low or low to high)
    if (isLowToHigh) {
      Collections.sort(hotels);
    } else {
      Collections.sort(hotels, Collections.reverseOrder());
    }

    int hotelPaneOffset = 0; // Y offset that each successive hotel pane will need to be at

    for (Hotel hotel : hotels) {
      // create nodes for each hotel preview
      Pane hotelPane = new Pane();
      ImageView hotelImage = new ImageView(new Image("application/hotelthumbs/La-Quinta.jpg"));
      Label hotelName = new Label();
      Label hotelStars = new Label();
      Label hotelPrice = new Label();
      Label hotelRating = new Label();
      Button infoButton = new Button();

      // set the height of each pane to be equal to the thumbnail in it
      hotelPane.setPrefHeight(hotelImage.getImage().getHeight());

      // set fields of preview window to those from each hotel object
      Text name = new Text(hotel.getName());
      Text stars = new Text(hotel.getStars() + "-Star Hotel");
      Text rating = new Text("User rating: 4/5");
      Text price = new Text("$" + hotel.getStdPrice());

      price.setFill(Paint.valueOf("#1b9dc1"));

      hotelName.setText(name.getText());
      hotelStars.setText(stars.getText());
      hotelRating.setText(rating.getText());
      hotelPrice.setText(price.getText());

      // configure layout of elements on hotel list
      double baseXOffset = hotelImage.getImage().getWidth() + 10;
      double baseYOffset = (hotelImage.getImage().getHeight() + 5)
                            - hotelImage.getImage().getHeight();
      int yPadding = 17;
      int hotelPriceOffset = 100;

      Font hotelNameFont = new Font(14);
      Font hotelStarsFont = new Font(12);
      Font hotelRatingFont = new Font(16);
      Font hotelPriceFont = new Font(30);

      hotelName.setLayoutX(baseXOffset);
      hotelName.setLayoutY(baseYOffset);
      hotelName.setFont(hotelNameFont);
      hotelName.setMaxWidth(450);

      hotelStars.setLayoutX(baseXOffset);
      hotelStars.setLayoutY(hotelName.getLayoutY() + yPadding);
      hotelStars.setFont(hotelStarsFont);

      hotelRating.setLayoutX(baseXOffset);
      hotelRating.setLayoutY(hotelStars.getLayoutY() + yPadding);
      hotelRating.setFont(hotelRatingFont);

      hotelPrice.setLayoutX(baseXOffset + hotelPriceOffset);
      hotelPrice.setLayoutY(hotelRating.getLayoutY() + yPadding);
      hotelPrice.setFont(hotelPriceFont);

      infoButton.setOpacity(0); // make button invisible
      infoButton.setLayoutX(hotelImage.getLayoutX());
      infoButton.setLayoutY(hotelImage.getLayoutY());
      infoButton.setPrefWidth(hotelImage.getImage().getWidth() + baseXOffset
                              + hotelName.getMaxWidth() + 10);
      infoButton.setPrefHeight(hotelImage.getImage().getHeight());

      infoButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
          try {
            navigator.hotelInfo(hotel, event);
          } catch (Exception e) {
            e.printStackTrace();
          }
        }
      });

      // add each element to the hotel preview pane one after the other
      hotelPane.getChildren().addAll(hotelImage, hotelName, hotelStars, hotelPrice, infoButton);
      hotelPane.setLayoutY(hotelPane.getPrefHeight() * hotelPaneOffset);
      hotelPane.setPrefWidth(infoButton.getPrefWidth());

      // make every other pane have a light background
      if (hotelPaneOffset % 2 == 0) {
        hotelPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#78c9f4"),
                                          null, null)));
      } else {
        hotelPane.setBackground(new Background(new BackgroundFill(Paint.valueOf("#FFFFFF"),
                                          null, null)));
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

  public void setLowToHigh() {
    isLowToHigh = true;
    sortList();
  }

  public void setHighToLow () {
    isLowToHigh = false;
    sortList();
  }

  public void hotelInfo(ActionEvent event) throws Exception {
    Parent Hotel = FXMLLoader.load(getClass().getResource("Hotel.fxml"));
    Scene hotel = new Scene(Hotel);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(hotel);
    window.show();
  }
}
