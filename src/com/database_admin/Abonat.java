package com.database_admin;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
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
    private String CNP;

    public Abonat()
    {

    }

    public Abonat(String nume, String prenume, String CNP) {
        this.Nume = nume;
        this.Prenume = prenume;
        this.CNP = CNP;
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

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }


    @Override
    public String toString() {
        return "Abonat{" +
                "Client_ID=" + Client_ID +
                ", Nume='" + Nume + '\'' +
                ", Prenume='" + Prenume + '\'' +
                ", CNP=" + CNP +
                '}';
    }
}
