package com.gui;

import animatefx.animation.FadeIn;
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


public class ControllerRegister {

    private Stage RegisterStage=new Stage();
    private double xOffset=0;
    private double yOffset=0;
    public void launchRegister() throws Exception
    {
        Parent registerScene = FXMLLoader.load(getClass().getResource("sampleRegisterPage.fxml"));
        RegisterStage.setTitle("Register Page");
        RegisterStage.setScene(new Scene(registerScene));
        RegisterStage.initStyle(StageStyle.UNDECORATED);
        registerScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        registerScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                RegisterStage.setX(event.getScreenX() - xOffset);
                RegisterStage.setY(event.getScreenY() - yOffset);
            }
        });
        RegisterStage.show();
        new FadeIn(registerScene).play();
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
        /*
        String musicFile = "src/com/sounds/clicksoundeffect.mp3";     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play(); */

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
                    EroareIntroducere err =new EroareIntroducere();
                    err.launch_EroareIntroucere();
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
        /*
        String musicFile = "src/com/sounds/clicksoundeffect.mp3";     // For example
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.play(); */

        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        ControllerLogIn cnt=new ControllerLogIn();
        cnt.launchLogIn();
    }

    public void CloseButtonRegister(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
