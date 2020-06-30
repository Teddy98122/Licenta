package com.gui;

import animatefx.animation.Pulse;
import com.database_admin.Abonat;
import com.database_admin.Deranjamente;
import com.mysql.cj.xdevapi.Client;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class AddDeranjamente {

    @FXML
    private TextField Client_ID;
    @FXML
    private TextField Contr_NR;
    @FXML
    private TextField Problema;
    @FXML
    private DatePicker Data_deranjament;
    @FXML
    private TextField Serviciu;
    @FXML
    private TextField Stare;

    private Stage addAbbStage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;

    public void launchAdd() throws Exception {
        Parent addAbbScene = FXMLLoader.load(getClass().getResource("sampleAddDeranjamente.fxml"));
        addAbbStage.setTitle("");
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

    SessionFactory factoryDrj = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Abonat.class)
            .buildSessionFactory();

    Session sesionDrj = factoryDrj.getCurrentSession();

    public void launchAdd(ActionEvent event) throws Exception {
        try {
            //create a user object
            sesionDrj.beginTransaction();
            if(Client_ID.getText().isEmpty()||Contr_NR.getText().isEmpty()||Problema.getText().isEmpty()||Serviciu.getText().isEmpty()||Stare.getText().isEmpty())
            {
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                ErrorCompletion err=new ErrorCompletion();
                err.launchErrorCompletion();
                launchAdd();
            }
            else {
                int Cl_id= Integer.parseInt(Client_ID.getText());
                int Cl_nr= Integer.parseInt(Contr_NR.getText());
                Deranjamente drj = new Deranjamente(Cl_id,Cl_nr,Problema.getText(),Data_deranjament.getValue(),Serviciu.getText(),Stare.getText());
                sesionDrj.save(drj);
                sesionDrj.getTransaction().commit();
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
            factoryDrj.close();
        }
    }
}
