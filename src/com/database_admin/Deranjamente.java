package com.database_admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="deranjamente")
public class Deranjamente {

    @Column(name="Client_ID")
    private int Client_ID;

    @Column(name="Contr_NR")
    private int Contr_NR;

    @Column(name="Problema")
    private String Problema;

    @Column(name="Data_deranjament")
    private String Data_deranjament;

    @Column(name="Serviciu")
    private String Serviciu;

    @Column(name="Stare")
    private String Stare;

    public Deranjamente()
    {

    }

    public Deranjamente(int client_ID, int contr_NR, String problema, String data_deranjament, String serviciu, String stare) {
        Client_ID = client_ID;
        Contr_NR = contr_NR;
        Problema = problema;
        Data_deranjament = data_deranjament;
        Serviciu = serviciu;
        Stare = stare;
    }

    public int getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(int client_ID) {
        Client_ID = client_ID;
    }

    public int getContr_NR() {
        return Contr_NR;
    }

    public void setContr_NR(int contr_NR) {
        Contr_NR = contr_NR;
    }

    public String getProblema() {
        return Problema;
    }

    public void setProblema(String problema) {
        Problema = problema;
    }

    public String getData_deranjament() {
        return Data_deranjament;
    }

    public void setData_deranjament(String data_deranjament) {
        Data_deranjament = data_deranjament;
    }

    public String getServiciu() {
        return Serviciu;
    }

    public void setServiciu(String serviciu) {
        Serviciu = serviciu;
    }

    public String getStare() {
        return Stare;
    }

    public void setStare(String stare) {
        Stare = stare;
    }

    @Override
    public String toString() {
        return "Deranjamente{" +
                "Client_ID=" + Client_ID +
                ", Contr_NR=" + Contr_NR +
                ", Problema='" + Problema + '\'' +
                ", Data_deranjament='" + Data_deranjament + '\'' +
                ", Serviciu='" + Serviciu + '\'' +
                ", Stare='" + Stare + '\'' +
                '}';
    }
}
