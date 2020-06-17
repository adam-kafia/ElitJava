/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.gui;

import Elit.entities.Equipment;
import Elit.services.EquipmentServices;
import com.jfoenix.controls.JFXTextField;
import com.nexmo.client.NexmoClient;
import com.nexmo.client.NexmoClientException;
import com.nexmo.client.sms.SmsSubmissionResponse;
import com.nexmo.client.sms.SmsSubmissionResponseMessage;
import com.nexmo.client.sms.messages.TextMessage;
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
public class ViewEquipmentController implements Initializable {

    private BorderPane borderPane;
    private TableView<Equipment> tableClub;
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
        for (Equipment cc : getClubs()) {

            FXMLLoader l = new FXMLLoader(getClass().getResource("smallEquipment.fxml"));
            try {
                Parent root = l.load();

                SmallEquipmentController occ = l.getController();

                occ.setLabel(cc.getLabel());
                occ.setCategory(cc.getCategory());
                occ.setQte(cc.getQte() + "");
                if (cc.getQte() < 1) {
                    NexmoClient client = new NexmoClient.Builder()
                            .apiKey("3b9026c6")
                            .apiSecret("FBL00BRPwnrM2Tww")
                            .build();

                    String messageText = "This message is generated from ElitApp\nEquipment:\nName: "+cc.getLabel()+
                            "\nCategory: "+cc.getCategory()+"\n The folowing equipment quantity is all in use.";
                    TextMessage message = new TextMessage("Vonage APIs", "21651343560", messageText);

                    SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

                    for (SmsSubmissionResponseMessage responseMessage : response.getMessages()) {
                        System.out.println(responseMessage);
                    }
                }
                occ.setQte_init(cc.getQteInit() + "");
                occ.setC(cc);
                tilePane.getChildren().add(root);
            } catch (IOException ex) {
                Logger.getLogger(ViewClubsController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NexmoClientException ex) {
                Logger.getLogger(ViewEquipmentController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        search.textProperty().addListener(((observable, oldValue, newValue) -> {
            tilePane.getChildren().clear();
            for (Equipment f : getClubs()) {
                if (f.getLabel().toLowerCase().contains(newValue) || f.getCategory().contains(newValue)) {////////////////////////
                    FXMLLoader l = new FXMLLoader(getClass().getResource("SmallEquipment.fxml"));
                    try {
                        Parent root = l.load();

                        SmallEquipmentController occ = l.getController();

                        occ.setLabel(f.getLabel());
                        occ.setCategory(f.getCategory());
                        occ.setQte(f.getQte() + "");
                        occ.setQte_init(f.getQteInit() + "");
                        occ.setC(f);
                        tilePane.getChildren().add(root);
                    } catch (IOException ex) {
                        Logger.getLogger(ViewClubsController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        }));
    }

    public ObservableList<Equipment> getClubs() {
        ObservableList<Equipment> clubs = FXCollections.observableArrayList();
        EquipmentServices cs = new EquipmentServices();
        List<Equipment> lc = cs.ListClasse();
        for (Equipment c : lc) {
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
