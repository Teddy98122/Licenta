package com.gui;

import animatefx.animation.Shake;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class EroareIntroducere {

    private Stage IntErrorStage=new Stage();

    public void launch_EroareIntroucere() throws Exception{
        Parent errIntScene = FXMLLoader.load(getClass().getResource("sampleEroareIntroducere.fxml"));
        IntErrorStage.setTitle("Eroare introducere");
        IntErrorStage.setScene(new Scene(errIntScene));
        IntErrorStage.initStyle(StageStyle.UNDECORATED);
        IntErrorStage.show();
        IntErrorStage.setAlwaysOnTop(true);
        new Shake(errIntScene).play();
    }


    public void okButton(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
