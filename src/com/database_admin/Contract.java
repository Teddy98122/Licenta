package com.database_admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Contract")
public class Contract {

    @Id
    @Column(name="Contr_NR")
    private int Contr_NR;

    @Column(name="Client_ID")
    private int Client_ID;

    @Column(name="Data_contract")
    private String Data_contract;

    @Column(name="Durata")
    private int Durata;

    public Contract()
    {

    }

    public Contract(String data_contract, int durata) {
        Data_contract = data_contract;
        Durata = durata;
    }

    public int getContr_NR() {
        return Contr_NR;
    }

    public void setContr_NR(int contr_NR) {
        Contr_NR = contr_NR;
    }

    public int getClient_ID() {
        return Client_ID;
    }

    public void setClient_ID(int client_ID) {
        Client_ID = client_ID;
    }

    public String getData_contract() {
        return Data_contract;
    }

    public void setData_contract(String data_contract) {
        Data_contract = data_contract;
    }

    public int getDurata() {
        return Durata;
    }

    public void setDurata(int durata) {
        Durata = durata;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "Contr_NR=" + Contr_NR +
                ", Client_ID=" + Client_ID +
                ", Data_contract='" + Data_contract + '\'' +
                ", Durata=" + Durata +
                '}';
    }
}
