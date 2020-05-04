package com.gui;

import animatefx.animation.Pulse;
import com.database_admin.Adresa;
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

public class AddAdresa {
    private Stage addAbbStage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML
    private TextField NumarContract;
    @FXML
    private TextField Tara;
    @FXML
    private TextField Judet;
    @FXML
    private TextField Localitate;
    @FXML
    private TextField Strada;
    @FXML
    private TextField Nr;
    @FXML
    private TextField Bloc;
    @FXML
    private TextField Scara;
    @FXML
    private TextField Ap;

    public void launchAdd() throws Exception {
        Parent addAbbScene = FXMLLoader.load(getClass().getResource("sampleAddAdresa.fxml"));
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

    SessionFactory factoryAdd = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Adresa.class)
            .buildSessionFactory();

    Session sesionAdd = factoryAdd.getCurrentSession();

    public void add(ActionEvent event) throws Exception {
        try {
            //create a user object
            sesionAdd.beginTransaction();
            if(NumarContract.getText().isEmpty()||Tara.getText().isEmpty()||Judet.getText().isEmpty()||Localitate.getText().isEmpty()||Nr.getText().isEmpty())
            {
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                ErrorCompletion err=new ErrorCompletion();
                err.launchErrorCompletion();
                launchAdd();
            }
            else {
                int NrCT_con = Integer.parseInt(NumarContract.getText());
                int Nr_con=Integer.parseInt(Nr.getText());
                int Bloc_con=Integer.parseInt(Bloc.getText());
                int Scara_con=Integer.parseInt(Scara.getText());
                int Ap_con=Integer.parseInt(Ap.getText());
                Adresa tempAdr = new Adresa(NrCT_con, Tara.getText(), Judet.getText(),Localitate.getText(),Strada.getText(),Nr_con,Bloc_con,Scara_con,Ap_con);
                sesionAdd.save(tempAdr);
                sesionAdd.getTransaction().commit();
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
            }
        } finally {
            factoryAdd.close();
        }
    }
}
