package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import sun.rmi.runtime.Log;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class MyAccountController extends Credentials implements Initializable {

    @FXML
    private TextField txtFullName;
    @FXML
    private TextField txtPassword;
    @FXML
    private TextField txtUserName;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtDOB;

    @FXML
    private Label updateStatus;

    //function that will edit the information
    public void editInformation() {

        String newName = txtFullName.getText();
        String newPassword = txtPassword.getText();
        String newEmail = txtEmail.getText();
        String newBirthDate = txtDOB.getText();

        if (validFullNamePattern(txtFullName.getText())) {
            updateStatus.setText("Invalid name input!");
            updateStatus.setTextFill(Paint.valueOf("red"));

        } else if (validPSWDPattern(txtPassword.getText())) {
            updateStatus.setText("Password must not contain special characters!");
            updateStatus.setTextFill(Paint.valueOf("red"));

        } else if (validEmailPattern(txtEmail.getText())) {
            updateStatus.setText("Must be a valid email address!");
            updateStatus.setTextFill(Paint.valueOf("red"));

        } else if (validDOBPattern(txtDOB.getText())) {
            updateStatus.setText("DOB Pattern: MM/DD/YYYY");
            updateStatus.setTextFill(Paint.valueOf("red"));

        } else {
            try {
                String username = txtUserName.getText();
                update(newName, newPassword, newEmail, newBirthDate, username,
                        LogInController.getUpdateSQL(), updateStatus);

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //Side panel buttons
    public void dashboard(ActionEvent event) throws Exception {
        Navigator.dashboard(event);
    }

    public void savedHotels(ActionEvent event) throws Exception {
        Navigator.savedHotels(event);
    }

    public void reservation(ActionEvent event) throws Exception {
        Navigator.reservation(event);
    }

    public void logout(ActionEvent event) throws Exception {
        Navigator.logout(event);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtUserName.setText(LogInController.getClientPassword());
        txtPassword.setText(LogInController.getClientPassword());
        if (LogInController.getIsSearcher()) {
            String searcher = "SOS.SEARCHER";
            getAccountData(searcher);
        } else {
            String owner = "SOS.OWNER";
            getAccountData(owner);
        }
    }

    private void getAccountData(String accTypeSQL){
        Statement grabInfo = null;
        try {
            Class.forName(LogInController.getDriver());
            String getInfoSQL = "SELECT NAME, DOB, PASSWORD, EMAIL FROM " + accTypeSQL + " WHERE USERNAME='"
                    + txtUserName.getText() + "'";
            Connection loginConnection = DriverManager.getConnection(LogInController.getUrl());
            grabInfo = loginConnection.createStatement();
            ResultSet result = grabInfo.executeQuery(getInfoSQL);
            if (result.next()) {
                txtFullName.setText(result.getString(1));
                txtEmail.setText(result.getString(4));
                txtDOB.setText(result.getString(2));
                loginConnection.close();
                grabInfo.close();
                result.close();
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

