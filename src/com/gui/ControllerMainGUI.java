package com.gui;

import animatefx.animation.*;
import com.database_admin.*;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.transform.Scale;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.imageio.ImageIO;
import javax.jws.soap.SOAPBinding;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControllerMainGUI implements Initializable {


    private int semaforMenu = 0;

    @FXML
    private Text MenuName;

    @FXML
    private TableView MyTable;

    @FXML
    private Button AddREG;

    @FXML
    private ImageView ImageURLUser;

    @FXML
    private Label UserLabelName;

    @FXML
    private Button MaximizeButton;

    @FXML
    private Pane MenuText;

    @FXML
    private Button CloseButtonMain;

    @FXML
    private AnchorPane AncPane;

    @FXML
    private Label NrLabel;

    @FXML
    private Button StrREG;

    @FXML
    private Button ExcREG;

    @FXML
    private Button cautareButton;


    FileChooser fileChooser = new FileChooser();
    BufferedImage bufferedImage;
    Image image;

    SessionFactory factoryMain2 = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Utilizator.class)
            .addAnnotatedClass(Abonat.class)
            .addAnnotatedClass(Contract.class)
            .addAnnotatedClass(Adresa.class)
            .addAnnotatedClass(Deranjamente.class)
            .addAnnotatedClass(Servicii.class)
            .buildSessionFactory();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Session sesionMain = factoryMain2.getCurrentSession();
        TableColumn Client_ID = new TableColumn("Client_ID");
        TableColumn Nume = new TableColumn("Nume");
        TableColumn Prenume = new TableColumn("Prenume");
        TableColumn CNP = new TableColumn("CNP");
        MyTable.getColumns().addAll(Client_ID, Nume, Prenume, CNP);
        ControllerLogIn logc = new ControllerLogIn();
        UserLabelName.setText("Bun venit " + logc.getNameHolder() + " !");
        FunctiiUtile fnc1 = new FunctiiUtile();
        NrLabel.setText("Nr de abonati :" + fnc1.getNr_Abonat());
        sesionMain.beginTransaction();
        List<Abonat> abonatList = sesionMain.createQuery("from Abonat").getResultList();
        List<Utilizator> list_utl = sesionMain.createQuery("from Utilizator").getResultList();
        ControllerLogIn logd = new ControllerLogIn();
        logd.getNameHolder();
        for (Utilizator tempUtilizator : list_utl) {
            if ((tempUtilizator.getNume().equals(logd.getNameHolder()))) {
                String imageGot = tempUtilizator.getImageURL();
                final Circle clip = new Circle(120, 120, 105);
                clip.setStroke(Color.SEAGREEN);
                ImageURLUser.setClip(clip);
                System.out.println(imageGot);
                Image image2 = null;
                try {
                    if (tempUtilizator.getImageURL() == null) {
                        System.out.println("Nu se intampla nimic !");
                        image2 = new Image(new FileInputStream("src/com/images/avatar.png"));
                    } else {
                        image2 = new Image(new FileInputStream(tempUtilizator.getImageURL()));
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                ImageURLUser.setImage(image2);
            }
        }
       /*
        //Pentru test
        for(Abonat tempabonat:abonatList)
        {
            System.out.println(tempabonat);
        }
        */

        ObservableList<Object> AbonatObs = FXCollections.observableArrayList(abonatList);

        Client_ID.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Client_ID"));
        Nume.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Nume"));
        Prenume.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Prenume"));
        CNP.setCellValueFactory(new PropertyValueFactory<Abonat, String>("CNP"));

        MyTable.setItems(AbonatObs);


        sesionMain.getTransaction().commit();
    }

    private double xOffset = 0;
    private double yOffset = 0;
    private Stage MainStage = new Stage();
    Parent MainScene;

    public void launchMain() throws Exception {

        MainScene = FXMLLoader.load(getClass().getResource("sampleMainGUI.fxml"));
        MainStage.setTitle("Main Page");
        MainStage.setScene(new Scene(MainScene));
        MainStage.initStyle(StageStyle.UNDECORATED);
        MainStage.show();

        MainScene.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        MainScene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                MainStage.setX(event.getScreenX() - xOffset);
                MainStage.setY(event.getScreenY() - yOffset);
            }
        });

        new Pulse(MainScene).play();
    }

    public void handleClicks(ActionEvent event) {
    }

    public void CloseButtonMain(ActionEvent event) {


        factoryMain2.close();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
    }

    public void SignOutButton(ActionEvent event) throws Exception {
        factoryMain2.close();
        ((Stage) (((Button) event.getSource()).getScene().getWindow())).close();
        ControllerLogIn lgn = new ControllerLogIn();
        lgn.launchLogIn();
    }

    public void buttonImageChange(ActionEvent event) throws Exception {

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        try {
            bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            final Circle clip = new Circle(120, 120, 105);
            clip.setStroke(Color.SEAGREEN);
            ImageURLUser.setClip(clip);
            ImageURLUser.setImage(image);
        } catch (IOException ex) {
            Logger.getLogger(ControllerMainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        SessionFactory factoryUtilizator = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Utilizator.class)
                .buildSessionFactory();

        Session sesionUtilizator = factoryUtilizator.getCurrentSession();
        try {
            //start a transaction
            sesionUtilizator.beginTransaction();
            //testare daca utilizatorul exista deja
            @SuppressWarnings("unchecked")
            List<Utilizator> list_utl = sesionUtilizator.createQuery("from Utilizator").getResultList();
            ControllerLogIn logc = new ControllerLogIn();
            logc.getNameHolder();
            for (Utilizator tempUtilizator : list_utl) {
                if ((tempUtilizator.getNume().equals(logc.getNameHolder()))) {
                    Utilizator tempUtl = sesionUtilizator.get(Utilizator.class, tempUtilizator.getUser_ID());
                    tempUtl.setImageURL(file.getPath());
                }
            }
            //commit transaction
            sesionUtilizator.getTransaction().commit();

            System.out.println("Done file updated !");

        } finally {
            factoryUtilizator.close();
        }

    }

    public void handleClicksAbonati(ActionEvent event) throws Exception {
        //  PleaseWait please= new PleaseWait();
        //please.launchPleaseWait();
        MyTable.getColumns().clear();
        TableColumn Client_ID = new TableColumn("Client_ID");
        TableColumn Nume = new TableColumn("Nume");
        TableColumn Prenume = new TableColumn("Prenume");
        TableColumn CNP = new TableColumn("CNP");
        MyTable.getColumns().addAll(Client_ID, Nume, Prenume, CNP);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
               /* String soundName = "src/com/sounds/sound2.wav";
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
                Clip clip = AudioSystem.getClip();
                clip.open(audioInputStream);
                clip.start(); */

                Session sesionMain = factoryMain2.getCurrentSession();
                semaforMenu = 1;
                MenuName.setText("Wait...");
                MenuName.setFill(Color.RED);
                MenuName.setTextAlignment(TextAlignment.CENTER);
                new Shake(MenuName).play();


                sesionMain.beginTransaction();
                List<Abonat> abonatList = sesionMain.createQuery("from Abonat").getResultList();
       /*
        //Pentru test
        for(Abonat tempabonat:abonatList)
        {
            System.out.println(tempabonat);
        }
        */

                ObservableList<Object> AbonatObs = FXCollections.observableArrayList(abonatList);

                Client_ID.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Client_ID"));
                Nume.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Nume"));
                Prenume.setCellValueFactory(new PropertyValueFactory<Abonat, String>("Prenume"));
                CNP.setCellValueFactory(new PropertyValueFactory<Abonat, String>("CNP"));

                MyTable.setItems(AbonatObs);


                sesionMain.getTransaction().commit();
                //please.closePleaseWait();

                return null;
            }

            @Override
            protected void succeeded() {
                MenuName.setText("Abonati");
                MenuName.setFill(Color.valueOf("#76a9e1"));
                FunctiiUtile fnc1 = new FunctiiUtile();
                NrLabel.setText("Nr de abonati :" + fnc1.getNr_Abonat());
                new Pulse(MyTable).play();
                new Tada(MenuName).play();
            }
        };
        new Thread(task).start();

    }

    public void handleClicksDeranjamente(ActionEvent event) {

        MyTable.getColumns().clear();
        TableColumn Client_ID = new TableColumn("Client_ID");
        TableColumn Contr_NR = new TableColumn("Contr_NR");
        TableColumn Problema = new TableColumn("Problema");
        TableColumn Data_deranjament = new TableColumn("Data_deranjament");
        TableColumn Serviciu = new TableColumn("Serviciu");
        TableColumn Stare = new TableColumn("Stare");
        TableColumn Id_deranjament = new TableColumn("Id_Deranjament");
        MyTable.getColumns().addAll(Contr_NR, Client_ID, Problema, Data_deranjament, Serviciu, Stare);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                /*
                String musicFile = "src/com/sounds/clicksoundeffect.mp3";     // For example
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play(); */

                semaforMenu = 2;
                MenuName.setText("Wait...");
                MenuName.setFill(Color.RED);
                MenuName.setTextAlignment(TextAlignment.CENTER);
                new Shake(MenuName).play();

                Session sesionMain = factoryMain2.getCurrentSession();


                sesionMain.beginTransaction();
                List<Deranjamente> deranjList = sesionMain.createQuery("from Deranjamente").getResultList();
       /*
        //Pentru test
        for(Abonat tempabonat:abonatList)
        {
            System.out.println(tempabonat);
        }
        */

                ObservableList<Object> DeranjObs = FXCollections.observableArrayList(deranjList);

                Contr_NR.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Contr_NR"));
                Client_ID.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Client_ID"));
                Problema.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Problema"));
                Data_deranjament.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Data_deranjament"));
                Serviciu.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Serviciu"));
                Stare.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Stare"));
                Id_deranjament.setCellValueFactory(new PropertyValueFactory<Deranjamente, String>("Id_deranjament"));

                MyTable.setItems(DeranjObs);
                sesionMain.getTransaction().commit();
                return null;
            }

            @Override
            protected void succeeded() {
                System.out.println("work done!");
                MenuName.setText("Deranjamente");
                MenuName.setFill(Color.valueOf("#76a9e1"));
                FunctiiUtile fnc1 = new FunctiiUtile();
                NrLabel.setText("Nr de deranjamente :" + fnc1.getNr_Deranjamente());
                new Pulse(MyTable).play();
                new Tada(MenuName).play();
            }
        };

        new Thread(task).start();


    }

    public void handleClicksServicii(ActionEvent event) {

        MyTable.getColumns().clear();
        TableColumn Client_ID = new TableColumn("Client_ID");
        TableColumn Tip_serv = new TableColumn("Tip_serv");
        TableColumn Pret = new TableColumn("Pret");
        TableColumn Id_serv = new TableColumn("Id_serv");
        MyTable.getColumns().addAll(Client_ID, Tip_serv, Pret);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                /*
                String musicFile = "src/com/sounds/clicksoundeffect.mp3";     // For example
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play(); */

                semaforMenu = 3;
                MenuName.setText("Wait...");
                MenuName.setFill(Color.RED);
                MenuName.setTextAlignment(TextAlignment.CENTER);
                new Shake(MenuName).play();
                Session sesionMain = factoryMain2.getCurrentSession();
                sesionMain.beginTransaction();
                List<Servicii> servList = sesionMain.createQuery("from Servicii").getResultList();
       /*
        //Pentru test
        for(Abonat tempabonat:abonatList)
        {
            System.out.println(tempabonat);
        }
        */

                ObservableList<Object> ServObs = FXCollections.observableArrayList(servList);

                Client_ID.setCellValueFactory(new PropertyValueFactory<Servicii, String>("Client_ID"));
                Tip_serv.setCellValueFactory(new PropertyValueFactory<Servicii, String>("Tip_serv"));
                Pret.setCellValueFactory(new PropertyValueFactory<Servicii, String>("Pret"));
                Id_serv.setCellValueFactory(new PropertyValueFactory<Servicii, String>("Id_serv"));

                MyTable.setItems(ServObs);
                sesionMain.getTransaction().commit();
                return null;
            }

            @Override
            protected void succeeded() {
                System.out.println("work done!");
                MenuName.setText("Servicii");
                MenuName.setFill(Color.valueOf("#76a9e1"));
                FunctiiUtile fnc1 = new FunctiiUtile();
                NrLabel.setText("Nr de servicii :" + fnc1.getNr_Servicii());
                new Pulse(MyTable).play();
                new Tada(MenuName).play();
            }
        };

        new Thread(task).start();

    }

    public void handleClicksAdrese(ActionEvent event) {

        MyTable.getColumns().clear();
        TableColumn Contr_NR = new TableColumn("Contr_NR");
        TableColumn Tara = new TableColumn("Tara");
        TableColumn Judet = new TableColumn("Judet");
        TableColumn Localitate = new TableColumn("Localitate");
        TableColumn Strada = new TableColumn("Strada");
        TableColumn Numar = new TableColumn("Numar");
        TableColumn Bloc = new TableColumn("Bloc");
        TableColumn Scara = new TableColumn("Scara");
        TableColumn Apartament = new TableColumn("Apartament");
        TableColumn Table_Key = new TableColumn("Table_Key");
        MyTable.getColumns().addAll(Contr_NR, Tara, Judet, Localitate, Strada, Numar, Scara, Apartament);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                /*
                String musicFile = "src/com/sounds/clicksoundeffect.mp3";     // For example
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play();
                 */

                semaforMenu = 4;

                Session sesionMain = factoryMain2.getCurrentSession();
                MenuName.setText("Wait...");
                MenuName.setFill(Color.RED);
                MenuName.setTextAlignment(TextAlignment.CENTER);
                new Shake(MenuName).play();
                sesionMain.beginTransaction();
                List<Adresa> adresaList = sesionMain.createQuery("from Adresa").getResultList();
       /*
        //Pentru test
        for(Abonat tempabonat:abonatList)
        {
            System.out.println(tempabonat);
        }
        */

                ObservableList<Object> AdresaObs = FXCollections.observableArrayList(adresaList);

                Contr_NR.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Contr_NR"));
                Tara.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Tara"));
                Judet.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Judet"));
                Localitate.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Localitate"));
                Strada.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Strada"));
                Numar.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Numar"));
                Bloc.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Bloc"));
                Scara.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Scara"));
                Apartament.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Apartament"));
                Table_Key.setCellValueFactory(new PropertyValueFactory<Adresa, String>("Table_Key"));

                MyTable.setItems(AdresaObs);
                sesionMain.getTransaction().commit();
                return null;
            }

            @Override
            protected void succeeded() {
                System.out.println("work done!");
                MenuName.setText("Adrese");
                MenuName.setFill(Color.valueOf("#76a9e1"));
                FunctiiUtile fnc1 = new FunctiiUtile();
                NrLabel.setText("Nr de adrese :" + fnc1.getNr_Adresa());
                new Pulse(MyTable).play();
                new Tada(MenuName).play();
            }
        };

        new Thread(task).start();


    }

    public void handleClicksContracte(ActionEvent event) {

        MyTable.getColumns().clear();
        TableColumn Contr_NR = new TableColumn("Contr_NR");
        TableColumn Client_ID = new TableColumn("Client_ID");
        TableColumn Data_contract = new TableColumn("Data_contract");
        TableColumn Durata = new TableColumn("Durata");
        MyTable.getColumns().addAll(Contr_NR, Client_ID, Data_contract, Durata);

        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                /*
                String musicFile = "src/com/sounds/clicksoundeffect.mp3";     // For example
                Media sound = new Media(new File(musicFile).toURI().toString());
                MediaPlayer mediaPlayer = new MediaPlayer(sound);
                mediaPlayer.play(); */

                semaforMenu = 5;


                Session sesionMain = factoryMain2.getCurrentSession();
                MenuName.setText("Wait...");
                MenuName.setFill(Color.RED);
                MenuName.setTextAlignment(TextAlignment.CENTER);
                new Shake(MenuName).play();
                sesionMain.beginTransaction();
                List<Contract> adresaList = sesionMain.createQuery("from Contract").getResultList();
       /*
        //Pentru test
        for(Abonat tempabonat:abonatList)
        {
            System.out.println(tempabonat);
        }
        */

                ObservableList<Object> ContrObs = FXCollections.observableArrayList(adresaList);

                Contr_NR.setCellValueFactory(new PropertyValueFactory<Contract, String>("Contr_NR"));
                Client_ID.setCellValueFactory(new PropertyValueFactory<Contract, String>("Client_ID"));
                Data_contract.setCellValueFactory(new PropertyValueFactory<Contract, String>("Data_contract"));
                Durata.setCellValueFactory(new PropertyValueFactory<Contract, String>("Durata"));

                MyTable.setItems(ContrObs);
                sesionMain.getTransaction().commit();

                return null;
            }

            @Override
            protected void succeeded() {
                System.out.println("work done!");
                MenuName.setText("Contracte");
                MenuName.setFill(Color.valueOf("#76a9e1"));
                FunctiiUtile fnc1 = new FunctiiUtile();
                NrLabel.setText("Nr de contracte :" + fnc1.getNr_Contract());
                new Pulse(MyTable).play();
                new Tada(MenuName).play();
            }
        };

        new Thread(task).start();


    }

    public void MaxButton(ActionEvent event) throws Exception {

        Stage stage = (Stage) MaximizeButton.getScene().getWindow();
        /*
        ****Momentan in asteptare*****
        Stage stage = (Stage) MaximizeButton.getScene().getWindow();
        stage.setResizable(true);
        stage.setFullScreen(true);

         */

        stage.setIconified(true);

    }




    public void adaugareInregistrare(ActionEvent event) throws Exception {


        switch (semaforMenu) {
            case 0:
                AddAboant add = new AddAboant();
                add.launchAdd();
                break;
            case 1:
                AddAboant add2 = new AddAboant();
                add2.launchAdd();
                break;
            case 2:
                AddDeranjamente add3 = new AddDeranjamente();
                add3.launchAdd();
                break;
            case 3:
                AddServicii add4 = new AddServicii();
                add4.launchAdd();
                break;
            case 4:
                AddAdresa add5 = new AddAdresa();
                add5.launchAdd();
                break;
            case 5:
                AddContract add6 = new AddContract();
                add6.launchAdd();
                break;

        }
    }

    public void handleCautareAvasata(ActionEvent event) throws Exception {
        Parent Scene2;
        Scene2 = FXMLLoader.load(getClass().getResource("sampleCautareAvansata.fxml"));
        MainStage.setTitle("Cautare avansata");
        MainStage = (Stage)((Node)event.getSource()).getScene().getWindow();
        MainStage.setScene(new Scene(Scene2));
    }
}