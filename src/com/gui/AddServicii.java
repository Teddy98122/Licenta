package com.gui;

import animatefx.animation.Pulse;
import com.database_admin.Abonat;
import com.database_admin.Servicii;
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

import javax.persistence.criteria.CriteriaBuilder;

public class AddServicii {

    @FXML
    private TextField Client_ID;
    @FXML
    private TextField Tip_serv;
    @FXML
    private TextField Pret;

    private Stage addAbbStage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;

    public void launchAdd() throws Exception {
        Parent addAbbScene = FXMLLoader.load(getClass().getResource("sampleAddServicii.fxml"));
        addAbbStage.setTitle("Error");
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

    SessionFactory factoryServ = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Abonat.class)
            .buildSessionFactory();

    Session sesionServ = factoryServ.getCurrentSession();

    public void add(ActionEvent event) throws Exception {
        try {
            //create a user object
            sesionServ.beginTransaction();
            if(Client_ID.getText().isEmpty()||Tip_serv.getText().isEmpty()||Pret.getText().isEmpty())
            {
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                ErrorCompletion err=new ErrorCompletion();
                err.launchErrorCompletion();
                launchAdd();
            }
            else {
                int Cl_id=Integer.parseInt(Client_ID.getText());
                int Pr=Integer.parseInt(Pret.getText());
                Servicii tempSrv = new Servicii(Cl_id, Tip_serv.getText(), Pr);
                sesionServ.save(tempSrv);
                sesionServ.getTransaction().commit();
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            }
        } finally {
            factoryServ.close();
        }
    }
}
