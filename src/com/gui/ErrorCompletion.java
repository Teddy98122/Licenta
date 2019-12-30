package com.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ErrorCompletion {

    private Stage errCompStage=new Stage();
    public void launchErrorCompletion() throws Exception
    {
        Parent errCompScene = FXMLLoader.load(getClass().getResource("sampleErrorCompletion.fxml"));
        errCompStage.setTitle("Error");
        errCompStage.setScene(new Scene(errCompScene));
        errCompStage.initStyle(StageStyle.UNDECORATED);
        errCompStage.show();
    }

    public void OKClose(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
