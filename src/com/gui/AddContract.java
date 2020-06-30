package com.gui;

import animatefx.animation.Pulse;
import com.database_admin.Abonat;
import com.database_admin.Contract;
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


public class AddContract {

    @FXML
    private TextField Client_ID;
    @FXML
    private TextField Durata;
    @FXML
    private DatePicker Data_contract;

    private Stage addAbbStage = new Stage();
    private double xOffset = 0;
    private double yOffset = 0;

    public void launchAdd() throws Exception {
        Parent addAbbScene = FXMLLoader.load(getClass().getResource("sampleAddContract.fxml"));
        addAbbStage.setTitle("Adaugare Contract");
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

    SessionFactory factoryContract = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Contract.class)
            .buildSessionFactory();

    Session sesionAbonat = factoryContract.getCurrentSession();

    public void add(ActionEvent event) throws Exception {
        try {
            //create a user object
            sesionAbonat.beginTransaction();
            System.out.println("Date1"+Data_contract.getValue());
            String date2;
            date2=Data_contract.toString();
            System.out.println("Date2:"+date2);
            if(Client_ID.getText().isEmpty()||Durata.getText().isEmpty())
            {
                ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                ErrorCompletion err=new ErrorCompletion();
                err.launchErrorCompletion();
                launchAdd();
            }
            else {
                int id=Integer.parseInt(Client_ID.getText());
                int durata=Integer.parseInt(Durata.getText());
                Contract tempAbn = new Contract(id, Data_contract.getValue(), durata);
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
            factoryContract.close();
        }
    }
}
