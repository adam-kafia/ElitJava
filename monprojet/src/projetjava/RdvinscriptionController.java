/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetjava;



import com.jfoenix.controls.JFXTimePicker;
import doryan.windowsnotificationapi.fr.Notification;
import entities.rdv;
import java.awt.TrayIcon;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import services.rdv_service;
import projetjava.Javamailutil;
/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class RdvinscriptionController implements Initializable {


    @FXML
       
    private DatePicker daterdvpicker;
    @FXML
    private TextField cintext;
    @FXML
    private TextField prenomtext;
    @FXML
    private TextField nomtext;
    @FXML
    private Button validrdv;
    String cin,prenom,nom,date,mail,heure;
    int numcin;
    rdv_service srv = new rdv_service();
    @FXML
    private ComboBox<String> type;
    List<String> comb=new ArrayList<>();
    @FXML
    private TextField mailtext;
    @FXML
    private Button back;
    @FXML
    private JFXTimePicker time;
    @FXML
    private Pane rdvpane;
    @FXML
    private Pane comfirmationpane;
    @FXML
    private Label nomc;
    @FXML
    private Label prenomc;
    @FXML
    private Label datec;
    @FXML
    private Label hourc;
    @FXML
    private Button btnok;
    @FXML
    private Label lbldeb;
    @FXML
    private Label lbcin;
    @FXML
    private Label lbempty;
     List<LocalDate> holidays1 = new ArrayList<>();
    @FXML
    private Label lbcomplet;
    @FXML
    private Label lbmail;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        rdvpane.toFront();
      comb.add("teacher");
      comb.add("student");
      ObservableList<String> combtype= FXCollections.observableArrayList(comb);
      type.setItems(combtype);
           DatePickerColor();
    }    


    public void DatePickerColor(){
   
        List<rdv> holidays = new ArrayList<>();
        holidays=srv.afficherRdvNt ("ACCEPTEE","REFUSEE");
        for(int i=0;i<holidays.size();i++)
        {
       DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
       LocalDate localDate = LocalDate.parse(holidays.get(i).getDaterdv(), formatter);
       holidays1.add(localDate);
        }
    daterdvpicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
	@Override
	public DateCell call(DatePicker param) {
		return new DateCell(){
			@Override
			public void updateItem(LocalDate item, boolean empty) {
				super.updateItem(item, empty);

				if (!empty && item != null) {
					if (holidays1.contains(item)) {
						this.setStyle("-fx-background-color: red");
                                                
					}
				}
			}
		};
	}
});
    }
    @FXML
    private void handleajoutrdv(ActionEvent event) throws Exception {
        
        DatePickerColor();
        LocalDate d=daterdvpicker.getValue();    
        LocalDate today= LocalDate.now();
       
       
    if (nomtext.getText().isEmpty() ||prenomtext.getText().isEmpty() || mailtext.getText().isEmpty() || holidays1.contains(d) )
    {

      lbempty.setVisible(true);
      lbcomplet.setVisible(true);
      lbempty.setTextFill(Color.RED ) ;
      lbempty.setText("Veuillez Remplir Tout les champs");  
      lbcomplet.setTextFill(Color.RED ) ;
      lbcomplet.setText("Choisissez une autre date ! ");
        
    }
    else 
    {
        
        lbempty.setVisible(false);
             if (d.isAfter(today) && (cintext.getText().length()==8) )
              {
        lbmail.setVisible(false);
        lbcin.setVisible(false);
        lbldeb.setVisible(false);
        lbcomplet.setVisible(false);
        cin = cintext.getText();  
        numcin=Integer.parseInt(cin);	
        nom=nomtext.getText();
        prenom=prenomtext.getText();
        date =d.toString();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDate localDate = localDateTime.toLocalDate();
        java.sql.Date nowdate = java.sql.Date.valueOf(localDate);
        String proffession = type.getValue();
        mail=mailtext.getText();
        heure=time.getValue().toString();
        rdv r = new rdv(numcin,nom,nowdate,prenom,date,proffession,mail,"Non Traitee",heure);
        srv.ajouterrdv(r);
       // Notification.sendNotification("Rendez vous", "Rendez vous ajoute avec succes!",TrayIcon.MessageType.INFO);
       Alert alert = new Alert(AlertType.INFORMATION);
       alert.setTitle("Rendez-vous");
       alert.setHeaderText(" appointment confirmed");
       alert.setContentText(" approve this to view your appointment informations");
       alert.showAndWait();
        comfirmationpane.toFront();
        nomc.setText(nom);
        prenomc.setText(prenom);
        datec.setText(date);
        hourc.setText(heure);
       
         }
       else
       {
        lbcin.setVisible(true);
        lbldeb.setVisible(true);
        lbmail.setVisible(true);
       if(d.isBefore(today) || (holidays1.contains(d)))
       {
            lbldeb.setTextFill(Color.RED ) ;
            lbldeb.setText("Veuillez entrer une date superieure \n a la date d'aujourd'hui");
       }
       if (cintext.getText().length()!=8)
       {
            lbcin.setTextFill(Color.RED ) ;
            lbcin.setText("Veuillez entrer une CIN valide");
       }
       else 
       {
           lbmail.setTextFill(Color.RED ) ;
           lbmail.setText("Veuillez entrer une adresse mail valide ");
       }
           

       }
    }
        
    }

    @FXML
    private void handleback(ActionEvent event) {
         try {
            Parent  conn_page = FXMLLoader.load(getClass().getResource("index.fxml"));
            Scene conn_scene = new Scene(conn_page);
            Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            conn_stage.setScene(conn_scene);
            conn_stage.show();
        } catch (IOException ex) {
            Logger.getLogger(UseradminController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
    
}
