package com.gui;

import com.database_admin.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FunctiiUtile {
    public void creareStagiu(String numeFXML,String pageName) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource(numeFXML));
        Stage primaryStage=new Stage();
        primaryStage.setTitle(pageName);
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
    }

    private int Nr_Abonat;
    private int Nr_Adresa;
    private int Nr_Deranjamente;
    private int Nr_Servicii;
    private int Nr_Contract;

    public int getNr_Contract() {
        SessionFactory factoryMain2 = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Contract.class)
                .buildSessionFactory();
        Session sesionMain = factoryMain2.getCurrentSession();
        try {
            sesionMain.beginTransaction();
            List<Contract> contrList = sesionMain.createQuery("from Contract").getResultList();
            Nr_Contract=contrList.size();
            sesionMain.getTransaction().commit();
        }finally {
            factoryMain2.close();
        }
        return Nr_Contract;
    }


    public int getNr_Abonat() {
        SessionFactory factoryMain2 = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Abonat.class)
                .buildSessionFactory();
        Session sesionMain = factoryMain2.getCurrentSession();
        try {
            sesionMain.beginTransaction();
            List<Abonat> abonatList = sesionMain.createQuery("from Abonat").getResultList();
            Nr_Abonat=abonatList.size();
            sesionMain.getTransaction().commit();
        }finally {
            factoryMain2.close();
        }
        return Nr_Abonat;
    }

    public int getNr_Adresa() {
        SessionFactory factoryMain2 = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Adresa.class)
                .buildSessionFactory();
        Session sesionMain = factoryMain2.getCurrentSession();
        try {
            sesionMain.beginTransaction();
            List<Adresa> adresaList = sesionMain.createQuery("from Adresa").getResultList();
            Nr_Adresa=adresaList.size();
            sesionMain.getTransaction().commit();
        }finally {
            factoryMain2.close();
        }
        return Nr_Adresa;
    }

    public int getNr_Deranjamente() {
        SessionFactory factoryMain2 = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Deranjamente.class)
                .buildSessionFactory();
        Session sesionMain = factoryMain2.getCurrentSession();
        try {
            sesionMain.beginTransaction();
            List<Deranjamente> deranjList = sesionMain.createQuery("from Deranjamente").getResultList();
            Nr_Deranjamente=deranjList.size();
            sesionMain.getTransaction().commit();
        }finally {
            factoryMain2.close();
        }
        return Nr_Deranjamente;
    }

    public int getNr_Servicii() {
        SessionFactory factoryMain2 = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Servicii.class)
                .buildSessionFactory();
        Session sesionMain = factoryMain2.getCurrentSession();
        try {
            sesionMain.beginTransaction();
            List<Servicii> servList = sesionMain.createQuery("from Servicii").getResultList();
            Nr_Servicii=servList.size();
            sesionMain.getTransaction().commit();
        }finally {
            factoryMain2.close();
        }
        return Nr_Servicii;
    }



}
