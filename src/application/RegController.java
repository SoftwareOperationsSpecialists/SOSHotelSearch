package application;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

//functionality to register for an account
public class RegController extends Credentials {
  @FXML
  private Label nameStatus;
  @FXML
  private Label userStatus;
  @FXML
  private Label passwordStatus;
  @FXML
  private Label emailStatus;
  @FXML
  private Label dobStatus;

  @FXML
  private TextField txtFullName;
  @FXML
  private TextField txtUserName;
  @FXML
  private TextField txtEmail;
  @FXML
  private TextField txtDOB;

  @FXML
  private PasswordField txtPassword;

  @FXML
  private RadioButton regSearcherBtn;
  @FXML
  private RadioButton regOwnerBtn;

  @FXML
  private ToggleGroup registerType;

  //creates account
  public void Register(ActionEvent event) throws Exception {
    createHotelSearcher(event);
  }

  //enters info into a created hotel searcher account
  private void createHotelSearcher(ActionEvent event) throws IOException {

    //makes account information
    String name = txtFullName.getText();    //full name
    String user = txtUserName.getText();    //username
    String pass = txtPassword.getText();    //password
    String email = txtEmail.getText();      //email address
    String birthDate = txtDOB.getText();    //date of birth

    //recognizes which radio button is selected (hotel searcher or hotel owner)
    RadioButton selectedRadioButton = (RadioButton) registerType.getSelectedToggle();

    //checks if full name is invalid
    if (validFullNamePattern(txtFullName.getText())) {
      nameStatus.setText("Invalid name input!");
      nameStatus.setTextFill(Paint.valueOf("red"));

    //checks if password is invalid
    } else if (validPSWDPattern(txtPassword.getText())) {
      passwordStatus.setText("Password must not contain special characters!");
      passwordStatus.setTextFill(Paint.valueOf("red"));

    //checks if username is invalid
    } else if (!validUserNamePattern(txtUserName.getText())) {
      userStatus.setText("Username must not contain spaces!");
      userStatus.setTextFill(Paint.valueOf("red"));

    //checks if email is invalid
    } else if (validEmailPattern(txtEmail.getText())) {
      emailStatus.setText("Must be a valid email address!");
      emailStatus.setTextFill(Paint.valueOf("red"));

    //checks if DOB is invalid
    } else if (validDOBPattern(txtDOB.getText())) {
      dobStatus.setText("DOB Pattern: MM/DD/YYYY");
      dobStatus.setTextFill(Paint.valueOf("red"));

    //checks if hotel searcher is selected
    } else if (selectedRadioButton == regSearcherBtn) {
      try {
        //create hotel searcher account
        register(user, birthDate, pass, name, email, searcherSql);
        Login(event);

      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    //checks if hotel owner account is selected
    } else if (selectedRadioButton == regOwnerBtn) {
      try {
        //creates hotel owner account
        register(user, birthDate, pass, name, email, ownerSql);
        Login(event);

      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    }
  }

  //go to dashboard scene
  public void Login(ActionEvent event) throws IOException {

    Parent Dashboard = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }

}
