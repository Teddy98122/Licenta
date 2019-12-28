package com.database_admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="abonat")
public class Abonat {


    @Id
    @Column(name="Client_ID")
    private int Client_ID;

    @Column(name="Nume")
    private String Nume;

    @Column(name="Prenume")
    private String Prenume;

    @Column(name="CNP")
    private double CNP;

    @Column(name="Data_nastere")
    private String Data_nastere;

    public Abonat()
    {

    }

    public Abonat(String nume, String prenume, double CNP, String data_nastere) {
        Nume = nume;
        Prenume = prenume;
        this.CNP = CNP;
        Data_nastere = data_nastere;
    }

    public int getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(int client_ID) {
        Client_ID = client_ID;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getPrenume() {
        return Prenume;
    }

    public void setPrenume(String prenume) {
        Prenume = prenume;
    }

    public double getCNP() {
        return CNP;
    }

    public void setCNP(double CNP) {
        this.CNP = CNP;
    }

    public String getData_nastere() {
        return Data_nastere;
    }

    public void setData_nastere(String data_nastere) {
        Data_nastere = data_nastere;
    }

    @Override
    public String toString() {
        return "Abonat{" +
                "Client_ID=" + Client_ID +
                ", Nume='" + Nume + '\'' +
                ", Prenume='" + Prenume + '\'' +
                ", CNP=" + CNP +
                ", Data_nastere='" + Data_nastere + '\'' +
                '}';
    }
}
