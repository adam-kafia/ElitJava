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
public class TeacherinterfaceController implements Initializable {
    @FXML
    private Label mailfield;
    @FXML
    private Button profilteacher;
    int tel,idrdv;
    String pwd,type,nom,prenom,birth,etat,username,img;
    user_service sru=new user_service();
    List<user> arr=new ArrayList<>();
    @FXML
    private Button exit;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    void setMail(String email) {
    mailfield.setText(email);
    }


    @FXML
    private void handleprofil(ActionEvent event) {
              teacherinfo();
                   FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("profilteacher.fxml"));
            try {
                Parent root = loader.load();
                ProfilteacherController tic = loader.getController();
                String adresse=mailfield.getText();
                tic.setMail(adresse);
                tic.setNom(nom);
                tic.setPrenom(prenom);
                tic.setTel(tel);
                tic.setNaissance(birth);
                tic.setpdp(img);
                tic.setuser(username);
                mailfield.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
    }
     public void teacherinfo()
    {
       arr=sru.afficherteacher(mailfield.getText());
        for (int i = 0; i < arr.size(); i++) {
            
    tel=arr.get(i).getPhoneNumber();
    nom=arr.get(i).getFirstname();
    prenom=arr.get(i).getLastname();
    birth=arr.get(i).getBirth();
    etat=arr.get(i).getEtat();
    username=arr.get(i).getUsername();
    img=arr.get(i).getProfilePicture();
            
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
            Logger.getLogger(ProfilteacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
