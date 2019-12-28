package com.gui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class FunctiiUtile {
    public void creareStagiu(String numeFXML,String pageName) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource(numeFXML));
        Stage primaryStage=new Stage();
        primaryStage.setTitle(pageName);
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }
}
