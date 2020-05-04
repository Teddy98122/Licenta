package com.database_admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="servicii")
public class Servicii {

    @Id
    @Column(name="Id_serv")
    private int Id_serv;

    @Column(name="Client_ID")
    private int Client_ID;

    @Column(name="Tip_serv")
    private String Tip_serv;

    @Column(name="Pret")
    private int Pret;

    public Servicii()
    {

    }

    public Servicii(int client_ID, String tip_serv, int pret) {
        Client_ID = client_ID;
        Tip_serv = tip_serv;
        Pret = pret;
    }

    public int getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(int client_ID) {
        Client_ID = client_ID;
    }

    public String getTip_serv() {
        return Tip_serv;
    }

    public void setTip_serv(String tip_serv) {
        Tip_serv = tip_serv;
    }

    public int getPret() {
        return Pret;
    }

    public void setPret(int pret) {
        Pret = pret;
    }

    @Override
    public String toString() {
        return "Servicii{" +
                "Client_ID=" + Client_ID +
                ", Tip_serv='" + Tip_serv + '\'' +
                ", Pret=" + Pret +
                '}';
    }
}
