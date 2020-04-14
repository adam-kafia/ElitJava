/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetjava;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import services.user_service;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ProfilstudentController implements Initializable {
    @FXML
    private Pane affichpdp,profilfirst,menupdp,modifp;
    @FXML
    private Button retourmenu,affich,modify,retourprofil,edit,exit,back,modifprofil,pdp,pdp1;
    @FXML
    private ImageView image1,image,grandphoto;
    @FXML
    private TextField nomtext,phonetext,prenomtext;
    @FXML
    private DatePicker datepicker;
    @FXML
    private Label noml,birthl,maill,phonel,usernamel,prenoml,anneel,niveaul,classel,fraisl,nbrmoisl;
    user_service sru = new user_service();
    String pic;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
void setPrenom(String prenom) {
        prenoml.setText(prenom); 
        prenomtext.setText(prenom); 
    }
void setTel(int tel) {
       String numtel = Integer.toString(tel);
       phonel.setText(numtel);
       phonetext.setText(numtel);
    }
void setNaissance(String birth) {birthl.setText(birth); }
void setpdp(String img) {
        File file = new File(img);
        Image im = new Image(file.toURI().toString());
        image.setImage(im);
        image1.setImage(im);
        grandphoto.setImage(im);
    }
void setuser(String username) {usernamel.setText(username);}
void setMail(String adresse) { maill.setText(adresse);}
void setNom(String nom) {
        noml.setText(nom);
        nomtext.setText(nom);
    }
void setFrais(int frais) {
    String num = Integer.toString(frais);
    fraisl.setText(num); }
void setAnnee(String annee_scolaire) {anneel.setText(annee_scolaire);   }
void setPayer(int nbr_mois_payer) {
    String nbr = Integer.toString(nbr_mois_payer);
    nbrmoisl.setText(nbr);}
void setClass(int idClass) {
    String numcl = Integer.toString(idClass);
    classel.setText(numcl); }
void setNiveau(String niveau) {niveaul.setText(niveau);}


    @FXML
    private void handlemodif(ActionEvent event) {
         // TODO code application logic here
            Webcam webcam = Webcam.getDefault();
            webcam.setViewSize(WebcamResolution.VGA.getSize());
            WebcamPanel panel = new WebcamPanel(webcam);
            panel.setFPSDisplayed(true);
            panel.setDisplayDebugInfo(true);
            panel.setImageSizeDisplayed(true);
            panel.setMirrored(true);
            
            JFrame window = new JFrame("camera");
            window.add(panel);
            window.setResizable(true);
          //  window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            window.pack();
            window.setLocationRelativeTo(null);
            window.setVisible(true);
            
                    try {
                        ImageIO.write(webcam.getImage(), "JPG", new File("src/projetjava/images/"+maill.getText()+".jpg"));
                        pic="src/projetjava/images/"+maill.getText()+".jpg";
                        sru.ajouterpdp(maill.getText(),pic);
                         setpdp(pic);
                    } catch (IOException ex) {
                        Logger.getLogger(ProfilstudentController.class.getName()).log(Level.SEVERE, null, ex);
                    }
    }
    @FXML
    private void handlepdp(ActionEvent event) {
        menupdp.toFront();
    }

    @FXML
    private void handleaffich(ActionEvent event) {
        affichpdp.toFront();
    }


    @FXML
    private void handleretourm(ActionEvent event) {
        menupdp.toFront();
    }

    @FXML
    private void handleretourp(ActionEvent event) {
        profilfirst.toFront();
    }


    @FXML
    private void handleedit(ActionEvent event) {
        String mail=maill.getText();
        String  first_name =nomtext.getText();
        String last_name = prenomtext.getText();
        String number = phonetext.getText();
        int num_tel = Integer.parseInt(number); 
        LocalDate date=datepicker.getValue();
        String naiss=date.toString();
       sru.modifierteacher(mail,first_name,last_name,naiss,num_tel);
                setNom(first_name);
                setPrenom(last_name);
                setTel(num_tel);
                setNaissance(naiss);
       profilfirst.toFront();
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

    @FXML
    private void handleback(ActionEvent event) {
         try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("studentinterface.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(ProfilteacherController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlemodifprofil(ActionEvent event) {
          modifp.toFront();
    }
    
}
