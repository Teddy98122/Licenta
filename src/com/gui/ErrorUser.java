package com.gui;

import animatefx.animation.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;

public class ErrorUser {
    private Stage errCompStage=new Stage();
    public void launchErrorCompletion() throws Exception
    {
        Parent errCompScene = FXMLLoader.load(getClass().getResource("sampleErrorUser.fxml"));
        errCompStage.setTitle("Error");
        errCompStage.setScene(new Scene(errCompScene));
        errCompStage.initStyle(StageStyle.UNDECORATED);
        errCompStage.show();
        errCompStage.setAlwaysOnTop(true);
        new Shake(errCompScene).play();
    }

    public void OKClose(ActionEvent event) {

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
