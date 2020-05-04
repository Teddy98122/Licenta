package com.database_admin;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adresa")
public class Adresa {

    @Id
    @Column(name="Table_Key")
    private int Table_Key;

    @Column(name="Contr_NR")
    private int Contr_NR;

    @Column(name="Tara")
    private String Tara;

    @Column(name="Judet")
    private String Judet;

    @Column(name="Localitate")
    private String Localitate;

    @Column(name="Strada")
    private String Strada;

    @Column(name="Numar")
    private int Numar;

    @Column(name="Bloc")
    private int Bloc;

    @Column(name="Scara")
    private int Scara;

    @Column(name="Apartament")
    private int Apartament;


    public Adresa()
    {

    }

    public Adresa(int contr_NR, String tara, String judet, String localitate, String strada, int numar, int bloc, int scara, int apartament) {
        Contr_NR = contr_NR;
        Tara = tara;
        Judet = judet;
        Localitate = localitate;
        Strada = strada;
        Numar = numar;
        Bloc = bloc;
        Scara = scara;
        Apartament = apartament;
    }


    public int getContr_NR() {
        return Contr_NR;
    }

    public void setContr_NR(int contr_NR) {
        Contr_NR = contr_NR;
    }

    public String getTara() {
        return Tara;
    }

    public void setTara(String tara) {
        Tara = tara;
    }

    public String getJudet() {
        return Judet;
    }

    public void setJudet(String judet) {
        Judet = judet;
    }

    public String getLocalitate() {
        return Localitate;
    }

    public void setLocalitate(String localitate) {
        Localitate = localitate;
    }

    public String getStrada() {
        return Strada;
    }

    public void setStrada(String strada) {
        Strada = strada;
    }

    public int getNumar() {
        return Numar;
    }

    public void setNumar(int numar) {
        Numar = numar;
    }

    public int getBloc() {
        return Bloc;
    }

    public void setBloc(int bloc) {
        Bloc = bloc;
    }

    public int getScara() {
        return Scara;
    }

    public void setScara(int scara) {
        Scara = scara;
    }

    public int getApartament() {
        return Apartament;
    }

    public void setApartament(int apartament) {
        Apartament = apartament;
    }


    @Override
    public String toString() {
        return "Adresa{" +
                "Contr_NR=" + Contr_NR +
                ", Tara='" + Tara + '\'' +
                ", Judet='" + Judet + '\'' +
                ", Localitate='" + Localitate + '\'' +
                ", Strada='" + Strada + '\'' +
                ", Numar=" + Numar +
                ", Bloc=" + Bloc +
                ", Scara=" + Scara +
                ", Apartament=" + Apartament +
                '}';
    }
}
