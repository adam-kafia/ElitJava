/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author ASUS
 */
public class rdv {
   private int id,cin;
   private String nom,prenom,daterdv,type,mail,etat,heure;
   private java.sql.Date date;

    public rdv(int cin, String nom,java.sql.Date date, String prenom, String daterdv,String type,String mail,String etat,String heure) {
        this.cin = cin;
        this.nom = nom;
        this.date=date;
        this.prenom = prenom;
        this.daterdv = daterdv;
        this.type=type;
        this.mail=mail;
        this.etat=etat;
        this.heure=heure;
    }

    public rdv(int cin, String nom, String prenom, String daterdv,String mail,String etat,String type,String heure) {
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.daterdv = daterdv;
        this.mail=mail;
        this.etat=etat;
        this.type=type;
        this.heure=heure;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDaterdv() {
        return daterdv;
    }

    public void setDaterdv(String daterdv) {
        this.daterdv = daterdv;
    }
   
   
}
