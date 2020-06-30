package com.gui;

import animatefx.animation.Pulse;
import animatefx.animation.Shake;
import com.database_admin.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class CautareAvansata implements Initializable {

    @FXML
    private TableView TableAboanti;

    @FXML
    private TableView TableContracte;

    @FXML
    private TableView TableServicii;

    @FXML
    private TableView TableDeranjamente;

    @FXML
    private TableView TableAdrese;

    FileChooser fileChooser = new FileChooser();


    SessionFactory factoryMain2 = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Abonat.class)
            .addAnnotatedClass(Contract.class)
            .addAnnotatedClass(Adresa.class)
            .addAnnotatedClass(Deranjamente.class)
            .addAnnotatedClass(Servicii.class)
            .buildSessionFactory();

    public void initialize(URL url, ResourceBundle rb)
    {
        TableColumn Client_ID = new TableColumn("Client_ID");
        TableColumn Nume = new TableColumn("Nume");
        TableColumn Prenume = new TableColumn("Prenume");
        TableColumn CNP = new TableColumn("CNP");
        TableAboanti.getColumns().addAll(Client_ID, Nume, Prenume, CNP);

        TableColumn Client_ID2 = new TableColumn("Client_ID");
        TableColumn Contr_NR = new TableColumn("Contr_NR");
        TableColumn Problema = new TableColumn("Problema");
        TableColumn Data_deranjament = new TableColumn("Data_deranjament");
        TableColumn Serviciu = new TableColumn("Serviciu");
        TableColumn Stare = new TableColumn("Stare");
        TableColumn Id_deranjament = new TableColumn("Id_Deranjament");
        TableDeranjamente.getColumns().addAll(Contr_NR, Client_ID, Problema, Data_deranjament, Serviciu, Stare);

        TableColumn Client_ID3 = new TableColumn("Client_ID");
        TableColumn Tip_serv = new TableColumn("Tip_serv");
        TableColumn Pret = new TableColumn("Pret");
        TableColumn Id_serv = new TableColumn("Id_serv");
        TableServicii.getColumns().addAll(Client_ID, Tip_serv, Pret);

        TableColumn Contr_NR2 = new TableColumn("Contr_NR");
        TableColumn Tara = new TableColumn("Tara");
        TableColumn Judet = new TableColumn("Judet");
        TableColumn Localitate = new TableColumn("Localitate");
        TableColumn Strada = new TableColumn("Strada");
        TableColumn Numar = new TableColumn("Numar");
        TableColumn Bloc = new TableColumn("Bloc");
        TableColumn Scara = new TableColumn("Scara");
        TableColumn Apartament = new TableColumn("Apartament");
        TableColumn Table_Key = new TableColumn("Table_Key");
        TableAdrese.getColumns().addAll(Contr_NR, Tara, Judet, Localitate, Strada, Numar, Scara, Apartament);

        TableColumn Contr_NR3 = new TableColumn("Contr_NR");
        TableColumn Client_ID4 = new TableColumn("Client_ID");
        TableColumn Data_contract = new TableColumn("Data_contract");
        TableColumn Durata = new TableColumn("Durata");
        TableContracte.getColumns().addAll(Contr_NR, Client_ID, Data_contract, Durata);

        ///////////////
        Session sesionMain = factoryMain2.getCurrentSession();
        sesionMain.beginTransaction();
        List<Contract> cntList = sesionMain.createQuery("from Contract").getResultList();
        List<Abonat> abnList = sesionMain.createQuery("from Abonat").getResultList();
        List<Servicii> srvList = sesionMain.createQuery("from Servicii").getResultList();
        List<Deranjamente> drjList = sesionMain.createQuery("from Deranjamente").getResultList();
        List<Adresa> adrList = sesionMain.createQuery("from Adresa").getResultList();

        ObservableList<Object> ContrObs = FXCollections.observableArrayList(cntList);
        Contr_NR3.setCellValueFactory(new PropertyValueFactory<Contract, String>("Contr_NR"));
        Client_ID4.setCellValueFactory(new PropertyValueFactory<Contract, String>("Client_ID"));
        Data_contract.setCellValueFactory(new PropertyValueFactory<Contract, String>("Data_contract"));
        Durata.setCellValueFactory(new PropertyValueFactory<Contract, String>("Durata"));
        TableContracte.setItems(ContrObs);

        ObservableList<Object> AbonatObs = FXCollections.observableArrayList(abnList);
        Client_ID.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Client_ID"));
        Nume.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Nume"));
        Prenume.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Prenume"));
        CNP.setCellValueFactory(new PropertyValueFactory<Abonat, String>("CNP"));
        TableAboanti.setItems(AbonatObs);

        ObservableList<Object> DeranjObs = FXCollections.observableArrayList(drjList);
        Contr_NR.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Contr_NR"));
        Client_ID2.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Client_ID"));
        Problema.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Problema"));
        Data_deranjament.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Data_deranjament"));
        Serviciu.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Serviciu"));
        Stare.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Stare"));
        Id_deranjament.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Id_deranjament"));
        TableDeranjamente.setItems(DeranjObs);

        ObservableList<Object> ServObs = FXCollections.observableArrayList(srvList);
        Client_ID3.setCellValueFactory(new PropertyValueFactory<Servicii, String>("Client_ID"));
        Tip_serv.setCellValueFactory(new PropertyValueFactory<Servicii, String>("Tip_serv"));
        Pret.setCellValueFactory(new PropertyValueFactory<Servicii, String>("Pret"));
        Id_serv.setCellValueFactory(new PropertyValueFactory<Servicii, String>("Id_serv"));
        TableServicii.setItems(ServObs);

        ObservableList<Object> AdresaObs = FXCollections.observableArrayList(adrList);
        Contr_NR2.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Contr_NR"));
        Tara.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Tara"));
        Judet.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Judet"));
        Localitate.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Localitate"));
        Strada.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Strada"));
        Numar.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Numar"));
        Bloc.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Bloc"));
        Scara.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Scara"));
        Apartament.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Apartament"));
        Table_Key.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Table_Key"));
        TableAdrese.setItems(AdresaObs);



        sesionMain.getTransaction().commit();


        ///////////////

        new Pulse(TableAboanti).play();
        new Pulse(TableContracte).play();
        new Pulse(TableServicii).play();
        new Pulse(TableDeranjamente).play();
        new Pulse(TableAdrese).play();
    }

    public void CloseButtonMain(ActionEvent event) {
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        factoryMain2.close();
    }


    public void backtoMain(ActionEvent event) throws Exception {
        URL url = ClassLoader.getSystemResource("sound2.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();

        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        ControllerMainGUI cntt=new ControllerMainGUI();
        cntt.launchMain();
        factoryMain2.close();
    }

    public void csvExport(ActionEvent event) throws Exception {
        URL url = ClassLoader.getSystemResource("sound2.wav");
        AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
        Clip clip = AudioSystem.getClip();
        clip.open(audioIn);
        clip.start();

        Session sesionMain = factoryMain2.getCurrentSession();
        sesionMain.beginTransaction();
        List<Contract> cntList = sesionMain.createQuery("from Contract").getResultList();
        List<Abonat> abnList = sesionMain.createQuery("from Abonat").getResultList();
        List<Servicii> srvList = sesionMain.createQuery("from Servicii").getResultList();
        List<Deranjamente> drjList = sesionMain.createQuery("from Deranjamente").getResultList();
        List<Adresa> adrList = sesionMain.createQuery("from Adresa").getResultList();
        File fileAbn = fileChooser.showSaveDialog(null);
        // FileChooser.ExtensionFilter extFilterCSV = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.CSV");
        // fileChooser.getExtensionFilters().add(extFilterCSV);

        try (PrintWriter writer = new PrintWriter(fileAbn+".csv")) {

            StringBuilder sb = new StringBuilder();

            sb.append('\n');
            sb.append("Client_ID");
            sb.append(',');
            sb.append("Nume");
            sb.append(',');
            sb.append("Prenume");
            sb.append(',');
            sb.append("CNP");
            sb.append('\n');
            for(Abonat tempAbn:abnList)
            {
                sb.append('\n');
                sb.append(tempAbn.getClient_ID());
                sb.append(',');
                sb.append(tempAbn.getNume());
                sb.append(',');
                sb.append(tempAbn.getNume());
                sb.append(',');
                sb.append(tempAbn.getCNP());

            }
            sb.append('\n');
            sb.append("Contr_NR");
            sb.append(',');
            sb.append("Data_contract");
            sb.append(',');
            sb.append("Durata");
            sb.append('\n');
            for(Contract tempCnt:cntList)
            {
                sb.append('\n');
                sb.append(tempCnt.getContr_NR());
                sb.append(',');
                sb.append(tempCnt.getData_contract());
                sb.append(',');
                sb.append(tempCnt.getDurata());

            }
            sb.append('\n');
            sb.append("Tip_serv");
            sb.append(',');
            sb.append("Pret");
            sb.append('\n');
            for(Servicii tempSrv:srvList)
            {
                sb.append('\n');
                sb.append(tempSrv.getTip_serv());
                sb.append(',');
                sb.append(tempSrv.getPret());
            }
            sb.append('\n');
            sb.append("Tara");
            sb.append(',');
            sb.append("Judet");
            sb.append(',');
            sb.append("Localitate");
            sb.append(',');
            sb.append("Strada");
            sb.append(',');
            sb.append("Numar");
            sb.append(',');
            sb.append("Bloc");
            sb.append(',');
            sb.append("Scara");
            sb.append('\n');
            for(Adresa tempAdr:adrList)
            {
                sb.append('\n');
                sb.append(tempAdr.getTara());
                sb.append(',');
                sb.append(tempAdr.getJudet());
                sb.append(',');
                sb.append(tempAdr.getLocalitate());
                sb.append(',');
                sb.append(tempAdr.getStrada());
                sb.append(',');
                sb.append(tempAdr.getNumar());
                sb.append(',');
                sb.append(tempAdr.getBloc());
                sb.append(',');
                sb.append(tempAdr.getScara());
            }
            sb.append('\n');
            sb.append("Problema");
            sb.append(',');
            sb.append("Data_deranjament");
            sb.append(',');
            sb.append("Serviciu");
            sb.append(',');
            sb.append("Stare");
            sb.append('\n');
            for(Deranjamente tempDrj:drjList)
            {
                sb.append('\n');
                sb.append(tempDrj.getProblema());
                sb.append(',');
                sb.append(tempDrj.getData_deranjament());
                sb.append(',');
                sb.append(tempDrj.getServiciu());
                sb.append(',');
                sb.append(tempDrj.getStare());

            }



            writer.write(sb.toString());

            System.out.println("done!");

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        sesionMain.getTransaction().commit();
    }
}
