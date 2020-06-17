/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Classe;
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
public class SmallClasseController implements Initializable {

    private BorderPane borderPane;
    private Classe c;
    @FXML
    private Pane cPane;
    @FXML
    private Label name;
    @FXML
    private Label level;
    @FXML
    private Label classroom;

    public void setC(Classe c) {
        this.c = c;
    }


    public void setName(String name) {
        this.name.setText(name);
    }

    public void setLevel(String level) {
        this.level.setText(level);
    }

    public void setClassroom(String classroom) {
        this.classroom.setText(classroom);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        name.setWrapText(true);
        level.setMaxWidth(100);
        classroom.setWrapText(true);
        classroom.setMaxWidth(100);
        classroom.setMaxHeight(40);

        // TODO
    }

    @FXML
    private void goTo(MouseEvent event) {
        FXMLLoader l = new FXMLLoader(getClass().getResource("OneClasse.fxml"));
        try {
            Parent root = l.load();
            Classe cc = c;
            OneClasseController occ = l.getController();

            occ.setName(cc.getName());
            occ.setLevel(cc.getLevel());

            occ.setC(cc);
            borderPane = (BorderPane) classroom.getScene().lookup("#borderPane");
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
