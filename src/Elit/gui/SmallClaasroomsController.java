/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Classroom;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class SmallClaasroomsController implements Initializable {

    private Classroom c;
    private BorderPane borderPane;
    @FXML
    private Pane cPane;
    @FXML
    private Label name;
    @FXML
    private Label capacity;
    @FXML
    private Label bloc;

    public void setName(String name) {
        this.name.setText(name);
    }

    public void setC(Classroom c) {
        this.c = c;
    }

    public void setCapacity(String capacity) {
        this.capacity.setText(capacity);
    }

    public void setBloc(String Bloc) {
        this.bloc.setText(Bloc);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setWrapText(true);
        capacity.setMaxWidth(100);
        bloc.setWrapText(true);
        bloc.setMaxWidth(100);
        bloc.setMaxHeight(40);

        // TODO
    }

    @FXML
    private void goTo(MouseEvent event) {
        FXMLLoader l = new FXMLLoader(getClass().getResource("OneClassroom.fxml"));
        try {
            Parent root = l.load();
            Classroom cc = c;
            OneClassroomController occ = l.getController();

            occ.setName(cc.getName());
            occ.setCapacity(Integer.toString(cc.getCapacity()));
            occ.setBloc(cc.getBloc());
            occ.setC(cc);
            borderPane = (BorderPane) bloc.getScene().lookup("#borderPane");
            borderPane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void noHover(MouseEvent event) {
        Pane p = cPane;
        p.setStyle(null);
        p.setStyle("-fx-border-color:black;"
                + "-fx-border-width:2px;"
                + "-fx-border-radius:30px;");
    }

    @FXML
    private void hover(MouseEvent event) {
        Pane p = cPane;
        p.setStyle("-fx-background-color: #8fbc8f;");
    }

}
