/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Classe;
import Elit.services.ClassServices;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.TilePane;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class ViewClasseController implements Initializable {

    @FXML
    private TilePane tilePane;
    @FXML
    private JFXTextField search;
    private BorderPane borderPane;
    private TableView<Classe> tableClub;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        for (Classe cc : getClubs()) {

            FXMLLoader l = new FXMLLoader(getClass().getResource("SmallClasse.fxml"));
            try {
                Parent root = l.load();
                SmallClasseController occ = l.getController();
                occ.setName(cc.getName());
                occ.setLevel(cc.getLevel());
                occ.setClassroom(cc.getId_classroom() + "");
                occ.setC(cc);
                tilePane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ViewClubsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            tilePane.getChildren().clear();
            for (Classe f : getClubs()) {
                if (f.getName().toLowerCase().contains(newValue)/* || f.getCapacity().contains(newValue)*/) {////////////////////////
                    FXMLLoader l = new FXMLLoader(getClass().getResource("SmallClasse.fxml"));
                    try {
                        Parent root = l.load();

                        SmallClasseController occ = l.getController();

                        occ.setName(f.getName());
                        occ.setLevel(f.getLevel());
                        occ.setClassroom(f.getId_classroom() + "");
                        occ.setC(f);
                        tilePane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(ViewClubsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }));
    }

    public ObservableList<Classe> getClubs() {
        ObservableList<Classe> clubs = FXCollections.observableArrayList();
        ClassServices cs = new ClassServices();
        List<Classe> lc = cs.ListClasse();
        for (Classe c : lc) {
            clubs.add(c);
        }
        return clubs;
    }

}
