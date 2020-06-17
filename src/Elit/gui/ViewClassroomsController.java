/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Classroom;
import Elit.services.ClassroomServices;
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
public class ViewClassroomsController implements Initializable {

    private BorderPane borderPane;
    private TableView<Classroom> tableClub;
    @FXML
    private TilePane tilePane;
    @FXML
    private JFXTextField search;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tilePane.setHgap(10);
        tilePane.setVgap(10);
        for (Classroom cc : getClubs()) {

            FXMLLoader l = new FXMLLoader(getClass().getResource("SmallClaasrooms.fxml"));
            try {
                Parent root = l.load();

                SmallClaasroomsController occ = l.getController();

                occ.setName(cc.getName());
                occ.setCapacity(cc.getCapacity() + "");
                occ.setBloc(cc.getBloc());
                occ.setC(cc);
                tilePane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ViewClubsController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            tilePane.getChildren().clear();
            for (Classroom f : getClubs()) {
                if (f.getName().toLowerCase().contains(newValue)/* || f.getCapacity().contains(newValue)*/) {////////////////////////
                    FXMLLoader l = new FXMLLoader(getClass().getResource("SmallClaasrooms.fxml"));
                    try {
                        Parent root = l.load();

                        SmallClaasroomsController occ = l.getController();

                        occ.setName(f.getName());
                        occ.setCapacity(Integer.toString(f.getCapacity()));
                        occ.setBloc(f.getBloc());
                        occ.setC(f);
                        tilePane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(ViewClubsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }));
    }

    public ObservableList<Classroom> getClubs() {
        ObservableList<Classroom> clubs = FXCollections.observableArrayList();
        ClassroomServices cs = new ClassroomServices();
        List<Classroom> lc = cs.ListClasse();
        for (Classroom c : lc) {
            clubs.add(c);
        }
        return clubs;
    }

    private void loadMenu(String m) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(m + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ClubMenuController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPane = (BorderPane) tableClub.getScene().lookup("#borderPane");
        borderPane.setCenter(root);
    }  
    
}
