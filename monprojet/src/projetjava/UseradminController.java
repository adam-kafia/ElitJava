/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projetjava;

import com.sun.glass.events.MouseEvent;
import entities.user;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import services.user_service;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class UseradminController implements Initializable {
    @FXML
    private Pane studentp,teacherp,index,statistiquep;
    @FXML
    private Button btnstudent,btnteach,btnstat;
    user_service sru = new user_service();
    @FXML
    private TableView<user> studenttable;
    @FXML
    private TableColumn<user, String> nomstudent,prenomstudent,mailstudent,adressestudent,niveaustudent;
    @FXML
    private TableColumn<user, Integer> telstudent,classestudent;
    @FXML
    private ComboBox<String> tri;
    @FXML
    private TextField search;
    @FXML
    private CheckBox Refusee,Acceptee;
    List<user> student=new ArrayList<>();    
    List<user> teacher=new ArrayList<>();   
    List<user> arr=new ArrayList<>();
    List<String> comb=new ArrayList<>();
    List<String> combteacher=new ArrayList<>();
    List<user> listetrie=new ArrayList<>();
    List<String> listeclasse=new ArrayList<>();
    List<String> listpayer=new ArrayList<>();
    List<String> listepaiement=new ArrayList<>();
    List<String> listeniveau=new ArrayList<>();
    int i=0,j=0;
    @FXML
    private Button statniveau,statpayement,details;
    String studentselect,teacherselect,etat;
    int tel,idClass,frais,nbr_mois_payer,telteach;
    String pwd,type,nom,prenom,birth,username,img,annee_scolaire,niveau;
    String nomteach,prenomteach,birthteach,usernameteach,imgteach,type_payement;
    @FXML
    private Pane studentinfo;
    @FXML
    private TableColumn<user, String> nomteacher,prenomteacher,mailteacher,adresseteacher;
    @FXML
    private TableColumn<user, Integer> phoneteacher;
    @FXML
    private ComboBox<String> triteacher;
    @FXML
    private TextField searchteacher;
    @FXML
    private Button detailsteacher;
    @FXML
    private TableView<user> teachertable;
    @FXML
    private Pane teacherinfo;
    @FXML
    private ImageView image;
    @FXML
    private Label noml,birthl,maill,phonel,usernamel,prenoml;
    @FXML
    private Button modifprofil;
    @FXML
    private Label anneel,niveaul,classel,fraisl,typepl,payerl;
    @FXML
    private Button archiverstudent,activerstudent,back,back1,back2,back3,back4;
    @FXML
    private TextField nomfield,prenomfield,telfield,anneefield,fraisfield;
    @FXML
    private ComboBox<String> niveaufield,classefield,typefield,nbrmoisfield;
    @FXML
    private DatePicker naissfield;
    @FXML
    private Button update;
    @FXML
    private Pane modifstudentp;
    @FXML
    private Button back21;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        index.toFront();
        comb.add("nom");        
        comb.add("prenom");
        comb.add("niveau");
        comb.add("classe");
        ObservableList<String> trib= FXCollections.observableArrayList(comb);
        tri.setItems(trib);
        combteacher.add("nom");        
        combteacher.add("prenom");
        ObservableList<String> triteach= FXCollections.observableArrayList(combteacher);
        triteacher.setItems(triteach);
    }    
    public void Teacherinfo()
    {
       arr=sru.afficherteacher(teacherselect);
        for (int i = 0; i < arr.size(); i++) {
            
    telteach=arr.get(i).getPhoneNumber();
    nomteach=arr.get(i).getFirstname();
    prenomteach=arr.get(i).getLastname();
    birthteach=arr.get(i).getBirth();
    usernameteach=arr.get(i).getUsername();
    imgteach=arr.get(i).getProfilePicture();
        }
    }
        public void studentinfo()
    {
       arr=sru.afficherstudent(studentselect);
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
    etat=arr.get(i).getEtat();
    type_payement=arr.get(i).getType_payement();
        }
    }
        public void tablestudent ()
    {
            nomstudent.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            prenomstudent.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            telstudent.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            mailstudent.setCellValueFactory(new PropertyValueFactory<>("email"));
            adressestudent.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            niveaustudent.setCellValueFactory(new PropertyValueFactory<>("niveau"));
            classestudent.setCellValueFactory(new PropertyValueFactory<>("idClass"));
           studenttable.setEditable(true);
           nomstudent.setCellFactory(TextFieldTableCell.forTableColumn());
    }
        public void tableTeacher()
    {
            nomteacher.setCellValueFactory(new PropertyValueFactory<>("lastname"));
            prenomteacher.setCellValueFactory(new PropertyValueFactory<>("firstname"));
            phoneteacher.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
            mailteacher.setCellValueFactory(new PropertyValueFactory<>("email"));
            adresseteacher.setCellValueFactory(new PropertyValueFactory<>("adresse"));
            teachertable.setEditable(true);
            nomteacher.setCellFactory(TextFieldTableCell.forTableColumn());
    }
    @FXML
    private void study(ActionEvent event) {
        studentp.toFront();
            tablestudent();
            student= sru.afficherAllStudent("Student");
            ObservableList<user> dataList= FXCollections.observableArrayList(student);
            studenttable.setItems(dataList);
     }

    @FXML
    private void teach(ActionEvent event) {
        teacherp.toFront();
            tableTeacher();
            teacher= sru.afficherAllStudent("Teacher");
            ObservableList<user> dataList= FXCollections.observableArrayList(teacher);
            teachertable.setItems(dataList);
    }

    @FXML
    private void stat(ActionEvent event) {
        statistiquep.toFront();
    }

    @FXML
    private void handlefiltrearchive(ActionEvent event) {
        tablestudent();
        j++;
        if(j%2!=0 && i%2==0)
        {
            student= sru.filtrer("REFUSEE","Student");
            ObservableList<user> archivestudent= FXCollections.observableArrayList(student);
            studenttable.setItems(archivestudent);
        }
        else
        {
            student= sru.afficherAllStudent("Student");
            ObservableList<user> allstudent= FXCollections.observableArrayList(student);
            studenttable.setItems(allstudent);
        }
    }

       @FXML
    private void handlefiltreactive(ActionEvent event) {
        i++;
        tablestudent();
        if(i%2!=0 && j%2==0)
        {
            student= sru.filtrer("ACCEPTEE","Student");
            ObservableList<user> activestudent= FXCollections.observableArrayList(student);
            studenttable.setItems(activestudent);
        }
        else
        {
            student= sru.afficherAllStudent("Student");
            ObservableList<user> allstudent= FXCollections.observableArrayList(student);
            studenttable.setItems(allstudent);
        }
    }

    @FXML
    private void handletrierstudent(ActionEvent event) {
        tablestudent();
        String choix=tri.getValue().toString();
        if(choix.equals("nom"))
        {
            listetrie= sru.getTrierByNom("Student");
            ObservableList<user> obstrie= FXCollections.observableArrayList(listetrie);
            studenttable.setItems(obstrie);
        }
        else if(choix.equals("niveau"))
        {
            listetrie= sru.getTrierByNiveau();
            ObservableList<user> obstrie= FXCollections.observableArrayList(listetrie);
            studenttable.setItems(obstrie);
        }
    }

    @FXML
    private void handlesearch(javafx.scene.input.MouseEvent event) {
               tablestudent();
            student= sru.afficherAllStudent("Student");
            ObservableList<user> dataList= FXCollections.observableArrayList(student);
            studenttable.setItems(dataList);
            FilteredList<user> filteredData = new FilteredList<>(dataList, b -> true);
		search.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (user.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (user.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (user.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (user.getNiveau().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (String.valueOf(user.getPhoneNumber()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
				else if (String.valueOf(user.getIdClass()).indexOf(lowerCaseFilter)!=-1)
				     return true;
				     else  
                        {
                            return false;
                            
                        }
			});
		});
                SortedList<user> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(studenttable.comparatorProperty());
		studenttable.setItems(sortedData);
    }

    @FXML
    private void handlestatparniveau(ActionEvent event) {
       pie_chart_1 p=new pie_chart_1();
       Stage s=new Stage();
       p.start(s);
    }

    @FXML
    private void handlestatpayement(ActionEvent event) {
        pie_chart_2 p=new pie_chart_2();
        Stage s=new Stage();
        p.start(s);
    } 

    @FXML
    private void handledetails(ActionEvent event) {
        user us = studenttable.getSelectionModel().getSelectedItem();
        if(us != null)
        {
        studentselect=us.getEmail();
        studentinfo();
        if(etat.equals("ACCEPTEE"))
        {
        
        activerstudent.setVisible(false);
        archiverstudent.setVisible(true);
        studentinfo.toFront();
        noml.setText(nom);
        prenoml.setText(prenom);
        maill.setText(studentselect);
        birthl.setText(birth);
        niveaul.setText(niveau);
        anneel.setText(annee_scolaire);
        String classe=Integer.toString(idClass);
        classel.setText(classe);
        String flous=Integer.toString(frais);
        fraisl.setText(flous);
        String numtel=Integer.toString(tel);
        phonel.setText(numtel);
        String mois=Integer.toString(nbr_mois_payer);
        payerl.setText(mois);
        usernamel.setText(username);
        typepl.setText(type_payement);
        }
        else if(etat.equals("REFUSEE"))
        {
        
        activerstudent.setVisible(true);
        archiverstudent.setVisible(false);
        studentinfo.toFront();
        noml.setText(nom);
        prenoml.setText(prenom);
        maill.setText(studentselect);
        birthl.setText(birth);
        niveaul.setText(niveau);
        anneel.setText(annee_scolaire);
        String classe=Integer.toString(idClass);
        classel.setText(classe);
        String flous=Integer.toString(frais);
        fraisl.setText(flous);
        String numtel=Integer.toString(tel);
        phonel.setText(numtel);
        String mois=Integer.toString(nbr_mois_payer);
        payerl.setText(mois);
        usernamel.setText(username);
        typepl.setText(type_payement);
        }
        }
    }



    @FXML
    private void handletrierteacher(ActionEvent event) {
        tableTeacher();
        String choix=triteacher.getValue().toString();
        if(choix.equals("nom"))
        {
            listetrie= sru.getTrierByNom("Teacher");
            ObservableList<user> obstrie= FXCollections.observableArrayList(listetrie);
            teachertable.setItems(obstrie);
        }
    }

    @FXML
    private void handlesearchteacher(javafx.scene.input.MouseEvent event) {
            tableTeacher();
            teacher= sru.afficherAllStudent("Teacher");
            ObservableList<user> dataList= FXCollections.observableArrayList(teacher);
            teachertable.setItems(dataList);
            FilteredList<user> filteredData = new FilteredList<>(dataList, b -> true);
		searchteacher.textProperty().addListener((observable, oldValue, newValue) -> {
			filteredData.setPredicate(user -> {
				if (newValue == null || newValue.isEmpty()) {
					return true;
				}
				String lowerCaseFilter = newValue.toLowerCase();
				if (user.getLastname().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
					return true;
				} else if (user.getFirstname().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (user.getEmail().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (user.getAdresse().toLowerCase().indexOf(lowerCaseFilter) != -1) {
					return true; // Filter matches last name.
				}
                                else if (String.valueOf(user.getPhoneNumber()).indexOf(lowerCaseFilter)!=-1) {
					return true; // Filter matches last name.
				}
				     else  
                        {
                            return false;
                            
                        }
			});
		});
                SortedList<user> sortedData = new SortedList<>(filteredData);
		sortedData.comparatorProperty().bind(teachertable.comparatorProperty());
		teachertable.setItems(sortedData);
    }

    @FXML
    private void handlefiltreactiveteacher(ActionEvent event) {
         i++;
        tableTeacher();
        if(i%2!=0 && j%2==0)
        {
            teacher= sru.filtrer("ACCEPTEE","Teacher");
            ObservableList<user> activeteacher= FXCollections.observableArrayList(teacher);
            teachertable.setItems(activeteacher);
        }
        else
        {
            teacher= sru.afficherAllStudent("Teacher");
            ObservableList<user> allteacher= FXCollections.observableArrayList(teacher);
            teachertable.setItems(allteacher);
        }
    }

    @FXML
    private void handlefiltrearchiveteacher(ActionEvent event) {
        tableTeacher();
        j++;
        if(j%2!=0 && i%2==0)
        {
            teacher= sru.filtrer("REFUSEE","Teacher");
            ObservableList<user> archiveteacher= FXCollections.observableArrayList(teacher);
            teachertable.setItems(archiveteacher);
        }
        else
        {
            teacher= sru.afficherAllStudent("Teacher");
            ObservableList<user> allteacher= FXCollections.observableArrayList(teacher);
            teachertable.setItems(allteacher);
        }
    }

    private void handledetailsteacher(ActionEvent event) {
        user userteach = teachertable.getSelectionModel().getSelectedItem();
        if(userteach != null)
        {
        teacherselect=userteach.getEmail();
        Teacherinfo();
        teacherinfo.toFront();
        }
    }


    @FXML
    private void handlemodifstudent(ActionEvent event) {
        setcombo();
        modifstudentp.toFront();
        nomfield.setText(nom);
        prenomfield.setText(prenom);
        telfield.setText(Integer.toString(tel));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(birth, formatter);
        naissfield.setValue(localDate);
        fraisfield.setText(Integer.toString(frais));
        if(nbr_mois_payer==3)
        nbrmoisfield.setValue("3 Mois");
        if(nbr_mois_payer==6)
        nbrmoisfield.setValue("6 Mois");
        if(nbr_mois_payer==9)
        nbrmoisfield.setValue("9 Mois");
        anneefield.setText(annee_scolaire);
        niveaufield.setValue(niveau);
        typefield.setValue(type_payement);
    }


    @FXML
    private void handleetatarchive(ActionEvent event) {
        sru.activerarchiver(studentselect,"REFUSEE");
        activerstudent.setVisible(true);
        archiverstudent.setVisible(false);
    }

    @FXML
    private void handleetatactive(ActionEvent event) {
        sru.activerarchiver(studentselect,"ACCEPTEE");
        activerstudent.setVisible(false);
        archiverstudent.setVisible(true);
    }

    @FXML
    private void handleback(ActionEvent event) {
        studenttable.getItems().clear();
        study(event);
    }

    @FXML
    private void handleback1(ActionEvent event) {
        index.toFront();
    }

    @FXML
    private void handleback2(ActionEvent event) {
         try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("admininterface.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UseradminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @FXML
    private void handleupdate(ActionEvent event) {
        
        update.setVisible(true);
        int moisp=0;
        String namemodif=nomfield.getText();
        String prenommodif=prenomfield.getText();
        int phonemodif=Integer.valueOf(telfield.getText());
        String naissmodif=naissfield.getValue().toString();
        String anneemodif =anneefield.getText();
        int fraismodif =Integer.valueOf(fraisfield.getText());
        String usernamemodif=namemodif+"_"+prenommodif;
        if(nbrmoisfield.getValue().equals("3 Mois"))
                moisp=3;
        if(nbrmoisfield.getValue().equals("6 Mois"))
                moisp=6;
        if(nbrmoisfield.getValue().equals("9 Mois"))
                moisp=9;
        String niveaumodif =niveaufield.getValue();
        int classemodif=Integer.valueOf(classefield.getValue());
        String typepayementmodif = typefield.getValue();
         user student=new user(classemodif,phonemodif,fraismodif,moisp,studentselect,prenommodif,namemodif,naissmodif,anneemodif,niveaumodif,typepayementmodif,usernamemodif);
         sru.editstudent(student);
         handledetails(event);
    }

    void setcombo ()
    {
    listpayer.add("3 Mois");        
    listpayer.add("6 Mois");
    listpayer.add("9 Mois");
    ObservableList<String> moispayer= FXCollections.observableArrayList(listpayer);
    nbrmoisfield.setItems(moispayer);
    new ComboBoxAutoComplete<String>(nbrmoisfield);
    listepaiement.add("Espece");        
    listepaiement.add("Cheque");
    listepaiement.add("Virrement");
    ObservableList<String> type= FXCollections.observableArrayList(listepaiement);
    typefield.setItems(type);
    new ComboBoxAutoComplete<String>(typefield);
    listeclasse.add("17");        
    listeclasse.add("12");
    listeclasse.add("10");
    ObservableList<String> cl= FXCollections.observableArrayList(listeclasse);
    classefield.setItems(cl);
    new ComboBoxAutoComplete<String>(classefield);
    listeniveau.add("1ere");        
    listeniveau.add("2eme");
    listeniveau.add("3eme");
    listeniveau.add("4eme");
    listeniveau.add("5eme");
    listeniveau.add("6eme");
    ObservableList<String> level= FXCollections.observableArrayList(listeniveau);
    niveaufield.setItems(level);
    new ComboBoxAutoComplete<String>(niveaufield);
    }




 





 


}
