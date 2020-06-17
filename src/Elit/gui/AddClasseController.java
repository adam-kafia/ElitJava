/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Classe;
import Elit.entities.Classroom;
import Elit.services.ClassServices;
import Elit.services.ClassroomServices;
import Elit.utils.DbConnection;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class AddClasseController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private ComboBox<Integer> classroom;
    @FXML
    private Button addClub;
    Connection cnx2;
    @FXML
    private JFXTextField level;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        List<Classroom> list_classrooms = new ArrayList<>();
        ClassroomServices cr = new ClassroomServices();
        ObservableList<Integer> classroms = FXCollections.observableArrayList();
        list_classrooms = cr.ListClasse();
        for (Classroom c : list_classrooms) {
            classroms.add(c.getId());
        }
        classroom.setItems(classroms);
        RequiredFieldValidator rq = new RequiredFieldValidator();
        NumberValidator n = new NumberValidator();

        rq.setMessage("Cannot be empty !");
        name.getValidators().add(rq);

        name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                name.validate();
            }

        });

        level.getValidators().add(rq);
        level.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                level.validate();
            }
        });

        cnx2 = DbConnection.getInstance().getCnx();
    }

    @FXML
    private void addClubbtn(ActionEvent event) {
         if (name.getText().isEmpty() || level.getText().isEmpty() || classroom.getSelectionModel().getSelectedIndex()==-1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("Verify your entries ! They cannot be empty");

            alert.showAndWait();
        } else {

            Classe c = new Classe(0, name.getText(), level.getText(), classroom.getSelectionModel().getSelectedItem());
            ClassServices cs = new ClassServices();
            cs.addClass(c);
        }
    }

}
