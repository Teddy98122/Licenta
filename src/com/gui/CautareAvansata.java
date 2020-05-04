package com.gui;

import com.database_admin.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.LinkedList;
import java.util.List;

public class CautareAvansata {

    @FXML
    private TableView MyTable;

    //CheckBox Abonati

    @FXML
    private CheckBox Abonati_Client_ID;
    @FXML
    private CheckBox Abonati_Nume;
    @FXML
    private CheckBox Abonati_Prenume;
    @FXML
    private CheckBox CNP;

    //CheckBox Contracte

    @FXML
    private CheckBox Contracte_Contr_NR;
    @FXML
    private CheckBox Contracti_Client_ID;
    @FXML
    private CheckBox Contracte_Data_contract;
    @FXML
    private CheckBox Contracte_Durata;

    //CheckBox Deranjamente

    @FXML
    private CheckBox Deranjamente_Contr_NR;
    @FXML
    private CheckBox Deranjamente_Client_ID;
    @FXML
    private CheckBox Deranjamente_Problema;
    @FXML
    private CheckBox Deranjamnete_Data_dereanjament;
    @FXML
    private CheckBox Deranjamente_Serviciu;
    @FXML
    private CheckBox Deranjamente_Stare;

    //Adrese

    @FXML
    private CheckBox Adrese_Contr_NR;
    @FXML
    private CheckBox Adrese_Tara;
    @FXML
    private CheckBox Adrese_Judet;
    @FXML
    private CheckBox Adrese_Localitate;
    @FXML
    private CheckBox Adrese_Strada;
    @FXML
    private CheckBox Adrese_Numar;
    @FXML
    private CheckBox Adrese_Bloc;
    @FXML
    private CheckBox Adrese_Scara;
    @FXML
    private CheckBox Adrese_Apartament;

    //Servicii

    @FXML
    private CheckBox Servicii_Client_ID;
    @FXML
    private CheckBox Servicii_Tip_serv;
    @FXML
    private CheckBox Servicii_Pret;


    SessionFactory factoryMain2 = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Abonat.class)
            .addAnnotatedClass(Contract.class)
            .addAnnotatedClass(Adresa.class)
            .addAnnotatedClass(Deranjamente.class)
            .addAnnotatedClass(Servicii.class)
            .buildSessionFactory();


    public void CloseButtonMain(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public void cautare(ActionEvent event) throws Exception {


        Session sesionMain = factoryMain2.getCurrentSession();
        sesionMain.beginTransaction();

        //Abonat
        TableColumn Abonat_Client_ID = new TableColumn("Client_ID");
        TableColumn Abonat_Nume = new TableColumn("Nume");
        TableColumn Abonat_Prenume = new TableColumn("Prenume");
        TableColumn Abonat_CNP = new TableColumn("CNP");

        //Deranjamente
        TableColumn Deranjamente_Client_ID = new TableColumn("Client_ID");
        TableColumn Deranjamente_Contr_NR = new TableColumn("Contr_NR");
        TableColumn Deranjamente_Problema = new TableColumn("Problema");
        TableColumn Deranjamente_Data_deranjament = new TableColumn("Data_deranjament");
        TableColumn Deranjamente_Serviciu = new TableColumn("Serviciu");
        TableColumn Deranjamente_Stare = new TableColumn("Stare");
        TableColumn Deranjamente_Id_deranjament = new TableColumn("Id_Deranjament");

        //Servicii
        TableColumn Servicii_Client_ID = new TableColumn("Client_ID");
        TableColumn Servicii_Tip_serv = new TableColumn("Tip_serv");
        TableColumn Servicii_Pret = new TableColumn("Pret");
        TableColumn Servicii_Id_serv = new TableColumn("Id_serv");

        //Adrese
        TableColumn Adrese_Contr_NR = new TableColumn("Contr_NR");
        TableColumn Adrese_Tara = new TableColumn("Tara");
        TableColumn Adrese_Judet = new TableColumn("Judet");
        TableColumn Adrese_Localitate = new TableColumn("Localitate");
        TableColumn Adrese_Strada = new TableColumn("Strada");
        TableColumn Adrese_Numar = new TableColumn("Numar");
        TableColumn Adrese_Bloc = new TableColumn("Bloc");
        TableColumn Adrese_Scara = new TableColumn("Scara");
        TableColumn Adrese_Apartament = new TableColumn("Apartament");
        TableColumn Adrese_Table_Key = new TableColumn("Table_Key");

        //Contracte
        TableColumn Contracte_Contr_NR = new TableColumn("Contr_NR");
        TableColumn Contracte_Client_ID = new TableColumn("Client_ID");
        TableColumn Contracte_Data_contract = new TableColumn("Data_contract");
        TableColumn Contracte_Durata = new TableColumn("Durata");

        //Query

        //Abonat
        List<Abonat> abonatList = sesionMain.createQuery("from Abonat").getResultList();
        ObservableList<Object> AbonatObs = FXCollections.observableArrayList(abonatList);

        //Contract
        List<Contract> contractList = sesionMain.createQuery("from Contract").getResultList();
        ObservableList<Object> ContrObs = FXCollections.observableArrayList(contractList);

        //Deranjamente
        List<Deranjamente> deranjList = sesionMain.createQuery("from Deranjamente").getResultList();
        ObservableList<Object> DeranjObs  = FXCollections.observableArrayList(deranjList);

        //Adrese
        List<Adresa> adresaList = sesionMain.createQuery("from Adresa").getResultList();
        ObservableList<Object> AdresaObs  = FXCollections.observableArrayList(adresaList);

        //Servicii
        List<Servicii> servList = sesionMain.createQuery("from Servicii").getResultList();
        ObservableList<Object> ServObs  = FXCollections.observableArrayList(servList);

        if((Abonati_Client_ID.isSelected())&&(Contracti_Client_ID.isSelected()))
        {
           List<Object> tmp= (List<Object>) sesionMain.createQuery("select abonat.Client_ID, contract.Client_ID, where abonat.Client_ID=contract.Client_ID");
            ObservableList<Object> obs  = FXCollections.observableArrayList(tmp);
            Abonat_Client_ID.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Client_ID"));
            MyTable.setItems(obs);

        }
        sesionMain.getTransaction().commit();
    }

    public void backtoMain(ActionEvent event) throws Exception {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        ControllerMainGUI cntt=new ControllerMainGUI();
        cntt.launchMain();
    }
}
