package com.database_admin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="utilizator")
public class Utilizator {


    @Id
    @Column(name="User_ID")
    private int User_ID;

    @Column(name="Nume")
    private String Nume;

    @Column(name="Parola")
    private String Parola;

    @Column(name="Email")
    private String Email;

    public Utilizator()
    {

    }

    public Utilizator(String nume, String parola, String email) {
        Nume = nume;
        Parola = parola;
        Email = email;
    }

    public int getUser_ID() {
        return User_ID;
    }

    public void setUser_ID(int user_ID) {
        User_ID = user_ID;
    }

    public String getNume() {
        return Nume;
    }

    public void setNume(String nume) {
        Nume = nume;
    }

    public String getParola() {
        return Parola;
    }

    public void setParola(String parola) {
        Parola = parola;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Override
    public String toString() {
        return "Utilizator{" +
                "User_ID=" + User_ID +
                ", Nume='" + Nume + '\'' +
                ", Parola='" + Parola + '\'' +
                ", Email='" + Email + '\'' +
                '}';
    }
}
