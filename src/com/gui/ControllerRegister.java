package com.gui;

import com.database_admin.Utilizator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.awt.*;
import java.util.List;


public class ControllerRegister {

    private Stage RegisterStage=new Stage();

    public void launchRegister() throws Exception
    {
        Parent registerScene = FXMLLoader.load(getClass().getResource("sampleRegisterPage.fxml"));
        RegisterStage.setTitle("Register Page");
        RegisterStage.setScene(new Scene(registerScene));
        RegisterStage.initStyle(StageStyle.UNDECORATED);
        RegisterStage.show();
    }

    @FXML
    private TextField NumeRegister;

    @FXML
    private TextField ParolaRegister;

    @FXML
    private TextField EmailRegister;
    SessionFactory factoryUtilizator=new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Utilizator.class)
            .buildSessionFactory();

    Session sesionUtilizator=factoryUtilizator.getCurrentSession();

    @FXML
    public void createAccount(ActionEvent event) throws Exception {
        int semafor=0;
        try
        {
            //create a user object
            System.out.println("Creating new user object");
            Utilizator tempUser=new Utilizator(NumeRegister.getText(),ParolaRegister.getText(),EmailRegister.getText());
            //start a transaction
            sesionUtilizator.beginTransaction();
            //testare daca utilizatorul exista deja
            @SuppressWarnings("unchecked")
            List <Utilizator> list_utl=sesionUtilizator.createQuery("from Utilizator").getResultList();

            for(Utilizator tempUtilizator:list_utl)
            {
                if((tempUtilizator.getNume().equals(NumeRegister.getText()))||(tempUtilizator.getEmail().equals(EmailRegister.getText())))
                {
                    semafor=1;
                }
                else if ((NumeRegister.getText().isEmpty())||(ParolaRegister.getText().isEmpty())||EmailRegister.getText().isEmpty())
                {
                    semafor=2;
                }
                else if((!tempUtilizator.getNume().equals(NumeRegister.getText()))||(!tempUtilizator.getEmail().equals(EmailRegister.getText())))
                {
                    semafor=3;
                }
            }
            switch (semafor)
            {
                case 1:
                    System.out.println("Numele sau email-ul exista deja !");
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    launchRegister();
                    break;
                case 2:
                    System.out.println("Trebuie completate toate campurile");
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    launchRegister();

                    ErrorCompletion erc=new ErrorCompletion();
                    erc.launchErrorCompletion();
                    break;
                case 3:
                    sesionUtilizator.save(tempUser);
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    ControllerLogIn cnt=new ControllerLogIn();
                    cnt.launchLogIn();
                    break;
                default:
                    System.out.println("Nu s-a intamplat nimic !");


            }
            //commit transaction
            sesionUtilizator.getTransaction().commit();

            System.out.println("Done !");

        } finally {
            factoryUtilizator.close();
        }
    }

    public void BackToLogIn(ActionEvent event) throws Exception {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        ControllerLogIn cnt=new ControllerLogIn();
        cnt.launchLogIn();
    }

    public void CloseButtonRegister(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
