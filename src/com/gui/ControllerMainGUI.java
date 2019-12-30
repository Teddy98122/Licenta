package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ControllerMainGUI {

    private Stage MainStage=new Stage();
    public void launchMain() throws Exception
    {
        Parent MainScene = FXMLLoader.load(getClass().getResource("sampleMainGUI.fxml"));
        MainStage.setTitle("Main Page");
        MainStage.setScene(new Scene(MainScene));
        MainStage.initStyle(StageStyle.UNDECORATED);
        MainStage.show();
    }

    public void handleClicks(ActionEvent event) {
    }

    public void CloseButtonMain(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }

    public void SignOutButton(ActionEvent event) throws Exception {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        ControllerLogIn lgn=new ControllerLogIn();
        lgn.launchLogIn();
    }
}
