package com.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.awt.event.ActionEvent;

public class PleaseWait {
    private Stage plsCompStage=new Stage();
    public void launchPleaseWait() throws Exception
    {
        Parent errCompScene = FXMLLoader.load(getClass().getResource("samplePleaseWait.fxml"));
        plsCompStage.setAlwaysOnTop(true);
        plsCompStage.setTitle("Please Wait");
        plsCompStage.setScene(new Scene(errCompScene));
        plsCompStage.initStyle(StageStyle.UNDECORATED);
        plsCompStage.initModality(Modality.WINDOW_MODAL);
        plsCompStage.show();
    }

    public void closePleaseWait()
    {
        plsCompStage.close();
    }
}
