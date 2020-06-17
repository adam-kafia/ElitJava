/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Equipment;
import Elit.services.ClassroomServices;
import Elit.services.EquipmentServices;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class OneEquipmentController implements Initializable {

    private Equipment cr;

    public void setC(Equipment cr) {
        this.cr = cr;
    }
    @FXML
    private JFXTextField label;
    @FXML
    private JFXTextField category;
    @FXML
    private JFXTextField qte;
    @FXML
    private JFXTextField qte_init;
    @FXML
    private Button edit;
    @FXML
    private Button delete;

    public void setLabel(String label) {
        this.label.setText(label);
    }

    public void setCategory(String category) {
        this.category.setText(category);
    }

    public void setQte(String qte) {
        this.qte.setText(qte);
    }

    public void setQte_init(String qte_init) {
        this.qte_init.setText(qte_init);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      
    }

    @FXML
    private void editClub(ActionEvent event) {
        EquipmentServices cs = new EquipmentServices();

        if (label.getText().isEmpty() || category.getText().isEmpty() || qte.getText().isEmpty()|| qte_init.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("Verify your entries ! They cannot be empty");

            alert.showAndWait();
        } else {

            Equipment c = new Equipment(cr.getId(), label.getText(), category.getText(), Float.parseFloat(qte.getText()), Float.parseFloat(qte_init.getText()));
            System.out.println(c);
            cs.UpdateClasse(c);
        }
    }

    @FXML
    private void deleteClub(ActionEvent event) {
           EquipmentServices cs = new EquipmentServices();
           cs.DeleteClasse(cr);
           Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("viewClassrooms.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ClubMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
       BorderPane borderPane =(BorderPane) label.getScene().lookup("#borderPane");
        borderPane.setCenter(root);
    }
   
}
