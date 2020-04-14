/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author ASUS
 */
public class user {
    private int id,idClass,phoneNumber,frais,idrdv,nbr_mois_payer;
    private String username,email,password,type,firstname,lastname,Birth,profilePicture;
    private String annee_scolaire,niveau,etat,adresse,type_payement;

    public user(int phoneNumber, int idrdv, String username, String email, String password, String type, String firstname, String lastname, String Birth, String profilePicture, String etat,String adresse) {
       
        this.phoneNumber = phoneNumber;
        this.idrdv = idrdv;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.firstname = firstname;
        this.lastname = lastname;
        this.Birth = Birth;
        this.profilePicture = profilePicture;
        this.etat = etat;
        this.adresse=adresse;
    }

    public user(int idClass, int phoneNumber, int frais, String username, String email, String firstname, String lastname, String Birth, String profilePicture, int nbr_mois_payer, String annee_scolaire, String niveau) {
        
        this.idClass = idClass;
        this.phoneNumber = phoneNumber;
        this.frais = frais;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.Birth = Birth;
        this.profilePicture = profilePicture;
        this.nbr_mois_payer = nbr_mois_payer;
        this.annee_scolaire = annee_scolaire;
        this.niveau = niveau;
    }
    public user(int idClass, int phoneNumber, int frais, String username, String email, String firstname, String lastname, String Birth, String profilePicture, int nbr_mois_payer, String annee_scolaire, String niveau,String adresse,String etat,String type_payement) {
        this.type_payement=type_payement;
        this.idClass = idClass;
        this.phoneNumber = phoneNumber;
        this.frais = frais;
        this.username = username;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.Birth = Birth;
        this.profilePicture = profilePicture;
        this.nbr_mois_payer = nbr_mois_payer;
        this.annee_scolaire = annee_scolaire;
        this.niveau = niveau;
        this.adresse=adresse;
        this.etat=etat;
    }

    public user(int idClass, int phoneNumber, int frais, int idrdv, int nbr_mois_payer, String username, String email, String password, String type, String firstname, String lastname, String Birth, String profilePicture, String annee_scolaire, String niveau, String adresse, String type_payement) {
        this.idClass = idClass;
        this.phoneNumber = phoneNumber;
        this.frais = frais;
        this.idrdv = idrdv;
        this.nbr_mois_payer = nbr_mois_payer;
        this.username = username;
        this.email = email;
        this.password = password;
        this.type = type;
        this.firstname = firstname;
        this.lastname = lastname;
        this.Birth = Birth;
        this.profilePicture = profilePicture;
        this.annee_scolaire = annee_scolaire;
        this.niveau = niveau;
        this.adresse = adresse;
        this.type_payement = type_payement;
    }
    public user(int idClass, int phoneNumber, int frais, int nbr_mois_payer, String email, String firstname, String lastname, String Birth, String annee_scolaire, String niveau, String type_payement,String username) {
        this.idClass = idClass;
        this.phoneNumber = phoneNumber;
        this.frais = frais;
        this.nbr_mois_payer = nbr_mois_payer;
        this.email = email;
        this.firstname = firstname;
        this.lastname = lastname;
        this.Birth = Birth;
        this.annee_scolaire = annee_scolaire;
        this.niveau = niveau;
        this.type_payement = type_payement;
        this.username=username;
    }

    public String getType_payement() {
        return type_payement;
    }

    public void setType_payement(String type_payement) {
        this.type_payement = type_payement;
    }


    
    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdClass() {
        return idClass;
    }

    public void setIdClass(int idClass) {
        this.idClass = idClass;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getFrais() {
        return frais;
    }

    public void setFrais(int frais) {
        this.frais = frais;
    }

    public int getIdrdv() {
        return idrdv;
    }

    public void setIdrdv(int idrdv) {
        this.idrdv = idrdv;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String Birth) {
        this.Birth = Birth;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public int getNbr_mois_payer() {
        return nbr_mois_payer;
    }

    public void setNbr_mois_payer(int nbr_mois_payer) {
        this.nbr_mois_payer = nbr_mois_payer;
    }

    public String getAnnee_scolaire() {
        return annee_scolaire;
    }

    public void setAnnee_scolaire(String annee_scolaire) {
        this.annee_scolaire = annee_scolaire;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    
}
