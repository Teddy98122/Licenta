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

import java.util.List;

public class ControllerLogIn {

    @FXML
    private TextField NumeLogIn;

    @FXML
    private TextField ParolaLogIn;

    SessionFactory factoryUtilizator=new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Utilizator.class)
            .buildSessionFactory();

    Session sesionUtilizator=factoryUtilizator.getCurrentSession();

    public void registerPage (ActionEvent event) throws Exception
    {
        ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
        Parent root = FXMLLoader.load(getClass().getResource("sampleRegisterPage.fxml"));
        Stage primaryStage=new Stage();
        primaryStage.setTitle("Register Page");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    public void LogInPage(ActionEvent event) throws Exception {
        try
        {
            //start a transaction
            sesionUtilizator.beginTransaction();
            //testare daca utilizatorul exista deja
            @SuppressWarnings("unchecked")
            List<Utilizator> list_utl=sesionUtilizator.createQuery("from Utilizator").getResultList();

            for(Utilizator tempUtilizator:list_utl) {
                if ((tempUtilizator.getNume().equals(NumeLogIn.getText())) && (tempUtilizator.getParola().equals(ParolaLogIn.getText()))&&(!NumeLogIn.getText().isEmpty())&&(!ParolaLogIn.getText().isEmpty())) {
                    ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
                    Parent root = FXMLLoader.load(getClass().getResource("sampleMainGUI.fxml"));
                    Stage primaryStage = new Stage();
                    primaryStage.setTitle("Main Page");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.show();
                }
                else if(NumeLogIn.getText().isEmpty()||ParolaLogIn.getText().isEmpty())
                {
                    System.out.println("Trebuie completate toate campurile");
                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                    Parent root = FXMLLoader.load(getClass().getResource("sampleLogIn.fxml"));
                    Stage primaryStage=new Stage();
                    primaryStage.setTitle("LogIn Page");
                    primaryStage.setScene(new Scene(root));
                    primaryStage.initStyle(StageStyle.UNDECORATED);
                    primaryStage.show();

                    Parent root1 = FXMLLoader.load(getClass().getResource("sampleErrorCompletion.fxml"));
                    Stage primaryStage1=new Stage();
                    primaryStage1.setTitle("Error");
                    primaryStage1.setScene(new Scene(root1));
                    primaryStage1.initStyle(StageStyle.UNDECORATED);
                    primaryStage1.show();
                    break;
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
