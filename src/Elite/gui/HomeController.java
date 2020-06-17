/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elite.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class HomeController implements Initializable {

    @FXML
    private Button btnClassroom;
    @FXML
    private Button btnEquipment;
    @FXML
    private Button btnClass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
    @FXML
    void gotoClasse() throws IOException
    {
        Stage stage;
        Parent root;
        
  
       
        stage = (Stage) btnClass.getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Classe.fxml"));
        stage.setScene(new Scene(root));
        
    }
    
}
