/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetjava;

import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import entities.rdv;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.rdv_service;
import services.user_service;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RdvadminController implements Initializable {
    
    rdv_service rdv =new rdv_service();
    user_service sru =new user_service();
    @FXML
    private TableView<rdv> tablerdv,tablerdv1;
    @FXML
    private TableColumn<rdv,String> nomrdv,prenomrdv,daterdv,mailrdv,nomrdvarchive, prenomrdvarchive,daterdvarchive,mailrdvarchive;
    @FXML
    private TableColumn<rdv,Integer> cinrdv,cinrdvarchive;
    @FXML
    private Button archiverdv,nontraite;
    @FXML
    private Pane nontraitepane,archivepane,traitementpane;
    @FXML
    private CheckBox accepted,failed;
    List listerdvnt = new ArrayList<>();
    List listerdvarchive= new ArrayList<>();
    List listpayer= new ArrayList<>();
    List wileya= new ArrayList<>();
    List typepaiement= new ArrayList<>();
    List listeclasse= new ArrayList<>();
    @FXML
    private TableColumn<rdv,String> typerdvarchive,typerdv;
    int rdvselect,cin;
    String nom,prenom,date,mail,type;
    rdv arr;
    @FXML
    private TextField nomtext,fraistext,anneetext,mailtext,teltext,prenomtext,niveautext;
    @FXML
    private ComboBox<String> adressecombo,classetext,nbrmoistext,typetext;
    @FXML
    private DatePicker datetext;
    @FXML
    private Button signup;
    @FXML
    private MenuItem accepteritem,refuseritem;
    @FXML
    private Button back;
    @FXML
    private Button back1;
    @FXML
    private Button back2;
    @FXML
    private TableColumn<?, ?> heurerdv;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nontraitepane.toFront();
        tablerdvnt();
        listerdvnt= rdv.afficherRdvNt("Non Traitee","Non Traitee");
        ObservableList<rdv> dataList= FXCollections.observableArrayList(listerdvnt);
        tablerdv.setItems(dataList);
        setcombo();
        fraistext.setVisible(false);
        typetext.setVisible(false);
        nbrmoistext.setVisible(false);
        anneetext.setVisible(false);
        niveautext.setVisible(false);
        classetext.setVisible(false);
    }   
 

    @FXML
    private void handlearchive(ActionEvent event) {
        archivepane.toFront();
        tablerdvarchive();
        listerdvarchive= rdv.afficherRdvNt("ACCEPTEE","REFUSEE");
        ObservableList<rdv> dataList= FXCollections.observableArrayList(listerdvarchive);
        tablerdv1.setItems(dataList);
    }

    @FXML
    private void handlenontraitee(ActionEvent event) {
        nontraitepane.toFront();
        tablerdvnt();
        listerdvnt= rdv.afficherRdvNt("Non Traitee","Non Traitee");
        ObservableList<rdv> dataList= FXCollections.observableArrayList(listerdvnt);
        tablerdv.setItems(dataList);
    }

    @FXML
    private void handleaccepted(ActionEvent event) {
         rdv traiterrdv = tablerdv.getSelectionModel().getSelectedItem();
        if(traiterrdv != null)
        {
        rdvselect=traiterrdv.getCin();
        rdvinfo();
        if(type.equals("teacher"))
        {
        traitementpane.toFront();
        nomtext.setText(nom);
        prenomtext.setText(prenom);
        mailtext.setText(mail);
        }
        else if(type.equals("student"))
        {
        traitementpane.toFront();
        nomtext.setText(nom);
        prenomtext.setText(prenom);
        mailtext.setText(mail);
        fraistext.setVisible(true);
        typetext.setVisible(true);
        nbrmoistext.setVisible(true);
        anneetext.setVisible(true);
        niveautext.setVisible(true);
        classetext.setVisible(true);
        }
        }
    }

    @FXML
    private void handlefailed(ActionEvent event) {
        
            rdv.modifieretat(rdvselect,"REFUSEE");
            tablerdv.getItems().clear();
            handlenontraitee(event);
    }
    public void tablerdvnt()
    {
            nomrdv.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomrdv.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            mailrdv.setCellValueFactory(new PropertyValueFactory<>("mail"));
            cinrdv.setCellValueFactory(new PropertyValueFactory<>("cin"));
            typerdv.setCellValueFactory(new PropertyValueFactory<>("type"));
            daterdv.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
            heurerdv.setCellValueFactory(new PropertyValueFactory<>("heure"));
    } 
    public void tablerdvarchive()
    {
            nomrdvarchive.setCellValueFactory(new PropertyValueFactory<>("nom"));
            prenomrdvarchive.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            mailrdvarchive.setCellValueFactory(new PropertyValueFactory<>("mail"));
            cinrdvarchive.setCellValueFactory(new PropertyValueFactory<>("cin"));
            typerdvarchive.setCellValueFactory(new PropertyValueFactory<>("type"));
            daterdvarchive.setCellValueFactory(new PropertyValueFactory<>("daterdv"));
    } 


    @FXML
    private void handleajout(ActionEvent event) throws IOException, NexmoClientException {
        int telephone = Integer.valueOf(teltext.getText());
        String date = datetext.getValue().toString();
        String adress=adressecombo.getValue();
        int moisp=0;
        String username=nom+"_"+prenom;
        String mdp=Integer.toString(rdvselect);
        if(type.equals("student"))
        {
            
            int classe=Integer.valueOf(classetext.getValue());
            int frais=Integer.valueOf(fraistext.getText());
            String typep=typetext.getValue();
            if(nbrmoistext.getValue().equals("3 Mois"))
                moisp=3;
            if(nbrmoistext.getValue().equals("6 Mois"))
                moisp=6;
            if(nbrmoistext.getValue().equals("9 Mois"))
                moisp=9;
            String annes=anneetext.getText();
            String niveau=niveautext.getText();
            String Img="src/projetjava/images/student.png";
            user studentrdv=new user(classe,telephone,frais,rdvselect,moisp,username,mail,mdp,"Student",prenom,nom,date,Img,annes,niveau,adress,typep);
            sru.ajouterstudent(studentrdv);
            rdv.modifieretat(rdvselect,"ACCEPTEE");
            tablerdv.getItems().clear();
            handlenontraitee(event);
        }
        else if(type.equals("teacher"))
        {
            String Img="src/projetjava/images/teacher.png";
            user teacherrdv=new user(telephone,rdvselect,username,mail,mdp,"Teacher",prenom,nom,date,Img,"ACCEPTEE",adress);
            sru.ajouterteacher(teacherrdv);
            rdv.modifieretat(rdvselect,"ACCEPTEE");
            tablerdv.getItems().clear();
            handlenontraitee(event);
        }
   
    }
    void setcombo ()
    {
    wileya.add("Ariana");
    wileya.add("Beja");
    wileya.add("Ben Arous");
    wileya.add("Bizerte");
    wileya.add("Gabès");
    wileya.add("Gafsa");
    wileya.add("Jendouba");
    wileya.add("Kairouan");
    wileya.add("Kasserine");
    wileya.add("Kébili");
    wileya.add("Le Kef");
    wileya.add("Mahdia");
    wileya.add("La Manouba");
    wileya.add("Médenine");
    wileya.add("Monastir");
    wileya.add("Nabeul");
    wileya.add("Sfax");
    wileya.add("Sidi Bouzid");
    wileya.add("Siliana");
    wileya.add("Sousse");
    wileya.add("Tataouine");
    wileya.add("Tozeur");
    wileya.add("Tunis");
    wileya.add("Zaghouan");
    ObservableList<String> wileyet= FXCollections.observableArrayList(wileya);
    adressecombo.setItems(wileyet);
    new ComboBoxAutoComplete<String>(adressecombo);
    listpayer.add("3 Mois");        
    listpayer.add("6 Mois");
    listpayer.add("9 Mois");
    ObservableList<String> moispayer= FXCollections.observableArrayList(listpayer);
    nbrmoistext.setItems(moispayer);
    typepaiement.add("Espece");        
    typepaiement.add("Cheque");
    typepaiement.add("Virrement");
    ObservableList<String> type= FXCollections.observableArrayList(typepaiement);
    typetext.setItems(type);
    listeclasse.add("17");        
    listeclasse.add("12");
    listeclasse.add("10");
    ObservableList<String> cl= FXCollections.observableArrayList(listeclasse);
    classetext.setItems(cl);
    }
    public void rdvinfo()
    {
    arr=rdv.traiterrdv(rdvselect);
    nom=arr.getNom();
    prenom=arr.getPrenom();
    cin=arr.getCin();
    date=arr.getDaterdv();
    mail=arr.getMail();
    type=arr.getType();
    }

    @FXML
    private void handleback(ActionEvent event) {
         try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("admininterface.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(RdvadminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlebackaff(ActionEvent event) {
            tablerdv.getItems().clear();
            handlenontraitee(event);
    }
}
