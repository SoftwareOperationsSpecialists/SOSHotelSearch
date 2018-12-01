package application;

import javafx.event.ActionEvent;

public class SavedHotelsController {
  //Side panel buttons
  public void dashboard(ActionEvent event) throws Exception {
    Navigator.dashboard(event);
  }
  public void myAccount(ActionEvent event) throws Exception {
    Navigator.myAccount(event);
  }
  public void reservation(ActionEvent event) throws Exception {
   Navigator.reservation(event);
  }
  public void logout(ActionEvent event) throws Exception {
    Navigator.logout(event);
  }
}
