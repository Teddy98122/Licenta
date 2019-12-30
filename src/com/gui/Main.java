package com.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception{
        ControllerLogIn cnt=new ControllerLogIn();
        cnt.launchLogIn();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
