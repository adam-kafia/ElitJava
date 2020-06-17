/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Classroom;
import Elit.services.ClassroomServices;
import Elit.utils.DbConnection;
import Elit.utils.sqlexcept;
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
public class AddClassroomController implements Initializable {

    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField capacity;
    @FXML
    private JFXTextField bloc;
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
        name.getValidators().add(rq);

        name.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                name.validate();
            }

        });

        capacity.getValidators().add(rq);
        capacity.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                capacity.validate();
            }
        });

        bloc.getValidators().add(rq);
        bloc.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue) {
                bloc.validate();
            }
        });
        cnx2 = DbConnection.getInstance().getCnx();
    }

    @FXML
    private void addClubbtn(ActionEvent event) {
        if (name.getText().isEmpty() || bloc.getText().isEmpty() || capacity.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("Verify your entries ! They cannot be empty");

            alert.showAndWait();
        } else {

            Classroom c = new Classroom(0, name.getText(), Integer.parseInt(capacity.getText()), bloc.getText());
            ClassroomServices cs = new ClassroomServices();
            cs.addClass(c);
        }
    }

}
