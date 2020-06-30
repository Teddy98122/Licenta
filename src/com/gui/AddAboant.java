package com.gui;

import animatefx.animation.*;
import com.database_admin.Abonat;
import com.database_admin.Utilizator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AddAboant  {

    @FXML
    private TextField NumeAbonat;

    @FXML
    private TextField PrenumeAbonat;

    @FXML
    private TextField CNPAbonat;

    private Stage addAbbStage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;

    public void launchAdd() throws Exception {
        Parent addAbbScene = FXMLLoader.load(getClass().getResource("sampleAddAbonat.fxml"));
        addAbbStage.setTitle("Adaugare Abonat");
        addAbbStage.setScene(new Scene(addAbbScene));
        addAbbStage.initStyle(StageStyle.UNDECORATED);
        addAbbScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        addAbbScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                addAbbStage.setX(event.getScreenX() - xOffset);
                addAbbStage.setY(event.getScreenY() - yOffset);
            }
        });
        addAbbStage.show();
        //addAbbStage.setAlwaysOnTop(true);
        new Pulse(addAbbScene).play();
    }

    public void CloseButtonLogIn(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public void closeWindow(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    SessionFactory factoryAbonat = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Abonat.class)
            .buildSessionFactory();

    Session sesionAbonat = factoryAbonat.getCurrentSession();

    public void add(ActionEvent event) throws Exception {
        try {
            //create a user object
            sesionAbonat.beginTransaction();
            if(NumeAbonat.getText().isEmpty()||PrenumeAbonat.getText().isEmpty()||CNPAbonat.getText().isEmpty())
            {
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                ErrorCompletion err=new ErrorCompletion();
                err.launchErrorCompletion();
                launchAdd();
            }
            else {
                Abonat tempAbn = new Abonat(NumeAbonat.getText(), PrenumeAbonat.getText(), CNPAbonat.getText());
                sesionAbonat.save(tempAbn);
                sesionAbonat.getTransaction().commit();
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            }
        }
        catch(Exception ex)
        {
            System.out.println("Eroare introducere");
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            EroareIntroducere err =new EroareIntroducere();
            err.launch_EroareIntroucere();
        }
        finally {
            factoryAbonat.close();
        }
    }
}
