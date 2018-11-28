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

  public void Register(ActionEvent event) throws Exception {
    createHotelSearcher(event);
  }

  private void createHotelSearcher(ActionEvent event) throws IOException {

    String name = txtFullName.getText();
    String user = txtUserName.getText();
    String pass = txtPassword.getText();
    String email = txtEmail.getText();
    String birthDate = txtDOB.getText();


    RadioButton selectedRadioButton = (RadioButton) registerType.getSelectedToggle();

    if (validFullNamePattern(txtFullName.getText())) {
      nameStatus.setText("Invalid name input!");
      nameStatus.setTextFill(Paint.valueOf("red"));

    } else if (validPSWDPattern(txtPassword.getText())) {
      passwordStatus.setText("Password must not contain special characters!");
      passwordStatus.setTextFill(Paint.valueOf("red"));

    } else if (!validUserNamePattern(txtUserName.getText())) {
      userStatus.setText("Username must not contain spaces!");
      userStatus.setTextFill(Paint.valueOf("red"));

    } else if (validEmailPattern(txtEmail.getText())) {
      emailStatus.setText("Must be a valid email address!");
      emailStatus.setTextFill(Paint.valueOf("red"));

    } else if (validDOBPattern(txtDOB.getText())) {
      dobStatus.setText("DOB Pattern: MM/DD/YYYY");
      dobStatus.setTextFill(Paint.valueOf("red"));

    } else if (selectedRadioButton == regSearcherBtn) {
      try {
          register(user, birthDate, pass, name, email, searcherSql);
        Login(event);

      } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    } else if (selectedRadioButton == regOwnerBtn) {
      try {
        register(user, birthDate, pass, name, email, ownerSql);
        Login(event);

      } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public void Login(ActionEvent event) throws IOException {

    Parent Dashboard = FXMLLoader.load(getClass().getResource("Login.fxml"));
    Scene dashboard = new Scene(Dashboard);

    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
    window.setScene(dashboard);
    window.show();
  }

}
