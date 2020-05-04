package com.gui;

import animatefx.animation.FadeIn;
import animatefx.animation.Shake;
import com.database_admin.Abonat;
import com.database_admin.Utilizator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerLogIn {


    private Stage logInStage=new Stage();
    private double xOffset=0;
    private double yOffset=0;
    public void launchLogIn() throws Exception
    {
        Parent logInScene = FXMLLoader.load(getClass().getResource("sampleLogIn.fxml"));
        logInStage.setTitle("LogIn Page");
        logInStage.setScene(new Scene(logInScene));
        logInStage.initStyle(StageStyle.UNDECORATED);
        logInScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        logInScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                logInStage.setX(event.getScreenX() - xOffset);
                logInStage.setY(event.getScreenY() - yOffset);
            }
        });
        logInStage.show();
        new FadeIn(logInScene).play();
    }


    @FXML
    private TextField NumeLogIn;

    @FXML
    private TextField ParolaLogIn;


    static private String NameHolder;

    SessionFactory factoryUtilizator=new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Utilizator.class)
            .buildSessionFactory();

    Session sesionUtilizator=factoryUtilizator.getCurrentSession();

    public String getNameHolder()
    {
        return NameHolder;
    }

    public void registerPage (ActionEvent event) throws Exception
    {
        /*
        String soundName = "res/sound2.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start(); */


        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        ControllerRegister rgs=new ControllerRegister();
        rgs.launchRegister();
    }

    public void LogInPage(ActionEvent event) throws Exception {

        /*
        String soundName = "res/sound2.wav";
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName));
        Clip clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        clip.start(); */

        int semafor=0;
        int semafor2=0;
        try
        {
            //start a transaction
            sesionUtilizator.beginTransaction();
            //testare daca utilizatorul exista deja
            @SuppressWarnings("unchecked")
            List<Utilizator> list_utl=sesionUtilizator.createQuery("from Utilizator").getResultList();
            NameHolder=NumeLogIn.getText();
            for(Utilizator tempUtilizator:list_utl) {
                if ((tempUtilizator.getNume().equals(NumeLogIn.getText())) && (tempUtilizator.getParola().equals(ParolaLogIn.getText()))&&(!NumeLogIn.getText().isEmpty())&&(!ParolaLogIn.getText().isEmpty())) {
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    ControllerMainGUI mn=new ControllerMainGUI();
                    mn.launchMain();
                    semafor=1;
                }
                else if(NumeLogIn.getText().isEmpty()||ParolaLogIn.getText().isEmpty())
                {
                    System.out.println("Trebuie completate toate campurile");
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    launchLogIn();

                    ErrorCompletion erc=new ErrorCompletion();
                    erc.launchErrorCompletion();
                    break;
                }

            }
            for(Utilizator tempUtilizator:list_utl) {
                if(!tempUtilizator.getNume().equals(NumeLogIn.getText())&&(!tempUtilizator.getParola().equals(ParolaLogIn.getText()))&&(!NumeLogIn.getText().isEmpty()&&!ParolaLogIn.getText().isEmpty()))
                {
                    if(semafor==0) {
                        System.out.println("Trebuie completate toate campurile");
                        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                        launchLogIn();

                        ErrorUser erc = new ErrorUser();
                        erc.launchErrorCompletion();
                    }
                    else
                    {
                        break;
                    }
                }
            }
            //commit transaction
            sesionUtilizator.getTransaction().commit();

            System.out.println("Done !");

        } finally {
            factoryUtilizator.close();
        }
    }

    public void CloseButtonLogIn(ActionEvent event) {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
    }
}
