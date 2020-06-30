package com.gui;

import com.database_admin.Adresa;
import com.database_admin.Contract;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DeleteContract {

    @FXML
    private TextField Contr_NR;

    private Stage DeleteStage=new Stage();
    private double xOffset = 0;
    private double yOffset = 0;
    public void launchDelete() throws Exception
    {
        Parent DeleteScene = FXMLLoader.load(getClass().getResource("sampleDeleteContract.fxml"));
        DeleteStage.setAlwaysOnTop(true);
        DeleteStage.setTitle("Delete");
        DeleteStage.setScene(new Scene(DeleteScene));
        DeleteStage.initStyle(StageStyle.UNDECORATED);
        DeleteStage.initModality(Modality.WINDOW_MODAL);
        DeleteStage.show();

        DeleteScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        DeleteScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                DeleteStage.setX(event.getScreenX() - xOffset);
                DeleteStage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    SessionFactory factoryServ = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Contract.class)
            .buildSessionFactory();

    Session sesionServ = factoryServ.getCurrentSession();

    public void anulare(ActionEvent event) {
        factoryServ.close();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }


    public void stergere(ActionEvent event) throws Exception {
        try {
            sesionServ.beginTransaction();
            List<Contract> list = sesionServ.createQuery("from Contract").getResultList();
            for (Contract temp : list) {
                String Contr_NR_str = Integer.toString(temp.getContr_NR());
                if (Contr_NR_str.equals(Contr_NR.getText())) {
                    sesionServ.delete(temp);
                }
            }
            sesionServ.getTransaction().commit();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        }catch (Exception e){
            EroareIntroducere err=new EroareIntroducere();
            err.launch_EroareIntroucere();
        }
    }

    public void CloseButtonLogIn(ActionEvent event) {
        factoryServ.close();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }
}
