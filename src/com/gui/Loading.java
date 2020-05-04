package com.gui;

import animatefx.animation.FadeIn;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Loading {
    private Stage LodingStage=new Stage();
    Parent LodingScene;
    private double xOffset=0;
    private double yOffset=0;
    public void launchLogIn() throws Exception
    {
        LodingScene = FXMLLoader.load(getClass().getResource("sampleLoading.fxml"));
        LodingStage.setTitle("LogIn Page");
        LodingStage.setScene(new Scene(LodingScene));
        LodingStage.initStyle(StageStyle.UNDECORATED);
        LodingScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        LodingScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LodingStage.setX(event.getScreenX() - xOffset);
                LodingStage.setY(event.getScreenY() - yOffset);
            }
        });
        LodingStage.show();
        new FadeIn(LodingScene).play();
    }
    public void closeLoading() throws Exception
    {
        LodingStage.close();
    }
}
