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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.user_service;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class LoginController implements Initializable {
    @FXML
    private Button cnx;
    @FXML
    private TextField emailtext;
    @FXML
    private PasswordField pwdtext;
    String email,pwd,type;
    user_service sru=new user_service();
    @FXML
    private Button back;
    @FXML
    private Hyperlink mdpoublie;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handlelogin(ActionEvent event) throws IOException, NexmoClientException {
       email=emailtext.getText();
       pwd=pwdtext.getText();
       typeuser();
       boolean active=sru.verifiercompte(email, pwd);
       boolean test=sru.seconnecter(email, pwd);
       if(test)
       {  
           if(active)
           {
           if(type.equals("User"))
           {
               try {
                   Parent  conn_page = FXMLLoader.load(getClass().getResource("admininterface.fxml"));
                   Scene conn_scene = new Scene(conn_page);
                   Stage conn_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                   conn_stage.setScene(conn_scene);
                   conn_stage.show();
               } catch (IOException ex) {
                   Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
               }
           }
           else if(type.equals("Teacher"))
           {
                   FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("teacherinterface.fxml"));
            try {
                Parent root = loader.load();
                TeacherinterfaceController tic = loader.getController();
                tic.setMail(email);
                emailtext.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
           }
           else if(type.equals("Student"))
           {
                   FXMLLoader loader = new FXMLLoader
                        (getClass()
                         .getResource("studentinterface.fxml"));
            try {
                Parent root = loader.load();
                StudentinterfaceController sic = loader.getController();
                sic.setMail(email);
               // nicc.setid(id);
                emailtext.getScene().setRoot(root);
            }
             catch (IOException ex) {
                System.out.println(ex.getMessage());
                }
           }
       }
       else 
        {
            System.out.println("compte mayekhdemch");     
        }
       }
       else 
       {
           System.out.println("compte ghalet");
       }
       
       
   
         
    }
    
    
    
    public void typeuser()
    {
        type=sru.findtype(email);
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

    @FXML
    private void handlemdpoublie(ActionEvent event) {
        
        try {
            String  recep=emailtext.getText();
            String password=sru.findpwd(emailtext.getText());
             String msg ="Bonjour ,voici votre mot de passe actuel :   "+password;
            Javamailutil.sendMail(recep,msg);
        } catch (Exception ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
   
    
}
