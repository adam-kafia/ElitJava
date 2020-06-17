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
import com.jfoenix.controls.JFXTextField;
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
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class OneClasseController implements Initializable {

    private Classe cr;
    @FXML
    private JFXTextField level;
    @FXML
    private ComboBox<Integer> classroom;

    public void setC(Classe cr) {
        this.cr = cr;
    }

    public void setLevel(String level) {
        this.level.setText(level);
    }

    public void setName(String name) {
        this.name.setText(name);
    }
    public void setClassroom(int a)
    {
       
    }
    @FXML
    private JFXTextField name;
    @FXML
    private Button edit;
    @FXML
    private Button delete;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Classroom> list_classrooms = new ArrayList<>();
        ClassroomServices cr = new ClassroomServices();
        ObservableList<Integer> classroms = FXCollections.observableArrayList();
        list_classrooms=cr.ListClasse();
        for(Classroom c : list_classrooms)
        {
            classroms.add(c.getId());
        }
        classroom.setItems(classroms);
        
    }

    @FXML
    private void editClub(ActionEvent event) {
        ClassServices cs = new ClassServices();

        if (name.getText().isEmpty() || level.getText().isEmpty() || classroom.getSelectionModel().getSelectedIndex() == -1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Invalid data");
            alert.setHeaderText("Cannot add a new entry");
            alert.setContentText("Verify your entries ! They cannot be empty");

            alert.showAndWait();
        } else {

            Classe c = new Classe(cr.getId(), name.getText(), level.getText(), classroom.getSelectionModel().getSelectedItem());
            System.out.println(c);
            cs.UpdateClasse(c);
        }
    }

    @FXML
    private void deleteClub(ActionEvent event) {
        ClassServices cs = new ClassServices();
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
