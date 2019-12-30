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

    private Stage logInStage=new Stage();

    public void launchLogIn() throws Exception
    {
        Parent logInScene = FXMLLoader.load(getClass().getResource("sampleLogIn.fxml"));
        logInStage.setTitle("LogIn Page");
        logInStage.setScene(new Scene(logInScene));
        logInStage.initStyle(StageStyle.UNDECORATED);
        logInStage.show();
    }

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
        ControllerRegister rgs=new ControllerRegister();
        rgs.launchRegister();
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
                    ControllerMainGUI mn=new ControllerMainGUI();
                    mn.launchMain();
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
