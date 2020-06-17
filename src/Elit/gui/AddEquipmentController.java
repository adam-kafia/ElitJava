/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Equipment;
import Elit.services.EquipmentServices;
import Elit.utils.DbConnection;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.Connection;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class AddEquipmentController implements Initializable {

    @FXML
    private JFXTextField label;
    @FXML
    private JFXTextField category;
    @FXML
    private JFXTextField qte;
    @FXML
    private JFXTextField qte_init;
    @FXML
    private Button addClub;
    Connection cnx2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         RequiredFieldValidator rq = new RequiredFieldValidator();
        NumberValidator n = new NumberValidator();

        rq.setMessage("Cannot be empty !");
        label.getValidators().add(rq);

        label.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                label.validate();
            }

        });

        category.getValidators().add(rq);
        category.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                category.validate();
            }
        });

        qte.getValidators().add(rq);
        qte.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                qte.validate();
            }
        });
        qte_init.getValidators().add(rq);
        qte_init.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                qte_init.validate();
            }
        });
        cnx2 = DbConnection.getInstance().getCnx();
    }    

    @FXML
    private void addClubbtn(ActionEvent event) {
        if (label.getText().isEmpty() || category.getText().isEmpty() || qte.getText().isEmpty() || qte_init.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("Verify your entries ! They cannot be empty");

            alert.showAndWait();
        } else {

            Equipment c = new Equipment(0, label.getText(), category.getText(), Float.parseFloat(qte.getText()), Float.parseFloat(qte_init.getText()));
            EquipmentServices cs = new EquipmentServices();
            cs.addEquipment(c);
        }
    }
    
}
