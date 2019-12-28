package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerMainGUI {
    public void handleClicks(ActionEvent event) {
    }

    public void CloseButtonMain(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void SignOutButton(ActionEvent event) throws Exception {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        Parent root = FXMLLoader.load(getClass().getResource("sampleLogIn.fxml"));
        Stage primaryStage=new Stage();
        primaryStage.setTitle("LogIn Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}
