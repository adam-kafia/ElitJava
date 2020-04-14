/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetjava;

import entities.user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import services.user_service;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class StudentinterfaceController implements Initializable {
    @FXML
    private Label mailfield;
    @FXML
    private Button profilstudent;
    @FXML
    private Button exit;
    user_service sru=new user_service();
    List<user> arr=new ArrayList<>();
    int tel,idClass,frais,nbr_mois_payer;
    String pwd,type,nom,prenom,birth,username,img,annee_scolaire,niveau;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleprofil(ActionEvent event) {
        studentinfo();
             FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("profilstudent.fxml"));
            try {
                Parent root = loader.load();
                ProfilstudentController psc = loader.getController();
                String adresse=mailfield.getText();
                psc.setMail(adresse);
                psc.setNom(nom);
                psc.setPrenom(prenom);
                psc.setTel(tel);
                psc.setNaissance(birth);
                psc.setpdp(img);
                psc.setuser(username);
                psc.setFrais(frais);
                psc.setAnnee(annee_scolaire);
                psc.setPayer(nbr_mois_payer);
                psc.setClass(idClass);
                psc.setNiveau(niveau);
                mailfield.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
    }

    @FXML
    private void handleexit(ActionEvent event) {
            try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("index.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(StudentinterfaceController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void setMail(String email) {
    mailfield.setText(email);
    }
     public void studentinfo()
    {
       arr=sru.afficherstudent(mailfield.getText());
        for (int i = 0; i < arr.size(); i++) {
            
    tel=arr.get(i).getPhoneNumber();
    nom=arr.get(i).getFirstname();
    prenom=arr.get(i).getLastname();
    birth=arr.get(i).getBirth();
    username=arr.get(i).getUsername();
    img=arr.get(i).getProfilePicture();
    idClass=arr.get(i).getIdClass();
    frais=arr.get(i).getFrais();
    nbr_mois_payer=arr.get(i).getNbr_mois_payer();
    annee_scolaire=arr.get(i).getAnnee_scolaire();
    niveau=arr.get(i).getNiveau();
        }
    }
}
