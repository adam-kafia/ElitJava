/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Equipment;
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
public class SmallEquipmentController implements Initializable {

    
    private Equipment c;
    private BorderPane borderPane;
    @FXML
    private Pane cPane;
    @FXML
    private Label label;
    @FXML
    private Label category;
    @FXML
    private Label qte;
    @FXML
    private Label qte_init;

    public void setC(Equipment c) {
        this.c = c;
    }

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        label.setWrapText(true);
        category.setMaxWidth(100);
        qte.setWrapText(true);
        qte.setMaxWidth(100);
        qte.setMaxHeight(40);
        qte_init.setWrapText(true);
        qte_init.setMaxWidth(100);
        qte_init.setMaxHeight(40);
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

    @FXML
    private void goTo(MouseEvent event) {
         FXMLLoader l = new FXMLLoader(getClass().getResource("OneEquipment.fxml"));
        try {
            Parent root = l.load();
            Equipment cc = c;
            OneEquipmentController occ = l.getController();

            occ.setLabel(cc.getLabel());
            occ.setCategory(cc.getCategory());
            occ.setQte(c.getQte()+"");
            occ.setQte_init(c.getQteInit()+"");

            occ.setC(cc);
            borderPane = (BorderPane) category.getScene().lookup("#borderPane");
            borderPane.setCenter(root);
        } catch (IOException ex) {
            Logger.getLogger(ViewClubsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
