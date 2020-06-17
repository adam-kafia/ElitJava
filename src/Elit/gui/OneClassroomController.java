/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Classroom;
import Elit.services.ClassroomServices;
import Elit.utils.sqlexcept;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class OneClassroomController implements Initializable {

    private Classroom cr;

    public void setC(Classroom cr) {
        this.cr = cr;
    }
    @FXML
    private Button edit;
    @FXML
    private Button delete;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXTextField capacity;
    @FXML
    private JFXTextField bloc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setCapacity(String capacity) {
        this.capacity.setText(capacity);
    }

    public void setBloc(String bloc) {
        this.bloc.setText(bloc);
    }

    @FXML
    private void editClub(ActionEvent event) {
        ClassroomServices cs = new ClassroomServices();

        if (name.getText().isEmpty() || capacity.getText().isEmpty() || bloc.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("Verify your entries ! They cannot be empty");

            alert.showAndWait();
        } else {

            Classroom c = new Classroom(cr.getId(), name.getText(), Integer.parseInt(capacity.getText()), bloc.getText());
            System.out.println(c);
            cs.UpdateClasse(c);
        }
    }

    @FXML
    private void deleteClub(ActionEvent event) {
           ClassroomServices cs = new ClassroomServices();
           cs.DeleteClasse(cr);
           Parent root=null;
        try {
            root = FXMLLoader.load(getClass().getResource("viewClassrooms.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ClubMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
       BorderPane borderPane =(BorderPane) name.getScene().lookup("#borderPane");
        borderPane.setCenter(root);
    }

}
