/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elite.gui;

import Elit.entities.Classe;
import Elit.entities.Classroom;
import Elit.services.ClassServices;
import Elit.services.ClassroomServices;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.IntegerStringConverter;

/**
 * FXML Controller class
 *
 * @author kinga
 */
public class ClasseController implements Initializable {

    @FXML
    private TableView<Classe> view;
    @FXML
    private Button btnRemove;
    @FXML
    private TableColumn<Classe, String> class_name;
    @FXML
    private TableColumn<Classe, String> class_level;
    @FXML
    private TableColumn<Classe, Integer> class_classrom;
    
    
    private final ObservableList<Classe> data = FXCollections.observableArrayList();
    private final ObservableList<String> classes_list = FXCollections.observableArrayList();
    private final ObservableList<Integer> classroms = FXCollections.observableArrayList();
    
    
    private  List<Classe> classes = new ArrayList<>();  
    private List<Classroom> list_classrooms = new ArrayList<>();   
    private ClassServices cs = new ClassServices();
    private ClassroomServices cr = new ClassroomServices();
    
    @FXML
    private ComboBox<String> classes_combo;
    @FXML
    private TextField name_txt;
    @FXML
    private TextField level_txt;
    @FXML
    private ComboBox<Integer> combo_classroms;
    @FXML
    private Button btnEdit;
    
    

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listClasses();
    }    
    
    private void listClasses()
    {
        classes= cs.ListClasse();
        list_classrooms= cr.ListClassroom();
        
        data.clear();
        classes_list.clear();
        classroms.clear();
        
        
        for(Classe c : classes)
        {
            data.add(c);
            classes_list.add(c.getName());
            
        }
        
        for(Classroom c : list_classrooms)
        {
            classroms.add(c.getId());
        }
       
        
        
        
        class_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        class_level.setCellValueFactory(new PropertyValueFactory<>("level"));
        class_classrom.setCellValueFactory(new PropertyValueFactory<>("id_classroom"));
        
        view.setItems(data);
        classes_combo.setItems(classes_list);
        classes_combo.getSelectionModel().selectFirst();
        
        combo_classroms.setItems(classroms);
        onComboClassChangeValue();
        
        
        view.setEditable(true);
        
        class_name.setCellFactory(TextFieldTableCell.forTableColumn());
        class_level.setCellFactory(TextFieldTableCell.forTableColumn());
        class_classrom.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        
        //class_classrom.setCellFactory(e-> new SimpleStringProperty(e.getValue().getClassrom().getClassName()));
        
    }
    
    /*@FXML
	 public void Change_ClassName(TableColumn.CellEditEvent evt) {
	     Classe selectedClasse = view.getSelectionModel().getSelectedItem();
	     selectedClasse.setName(evt.getNewValue().toString());
	     cs.UpdateClasse(selectedClasse);
	     listClasses();
	 }
    */
    
    @FXML
        public void onComboClassChangeValue()
        {
            String classname = classes_combo.getSelectionModel().getSelectedItem();
            Classe c = cs.findCLasseByName(classname);
            
            name_txt.setText(c.getName());
            level_txt.setText(c.getLevel());
            
           ObservableList<Integer> l = combo_classroms.getItems();
           for(Integer item : l)
           {
               if(item==c.getId_classroom())
               {
                   //combo_classroms.getSelectionModel().select(l.indexOf(item));
                   combo_classroms.getSelectionModel().select(item);
                   
               }
           } 
            
        }
        
        @FXML
        public void onbtnEditClick()
        {
            String classname = classes_combo.getSelectionModel().getSelectedItem();
            Classe c = new Classe(cs.findCLasseByName(classname).getId(), name_txt.getText(), level_txt.getText(), combo_classroms.getSelectionModel().getSelectedItem());
            cs.UpdateClasse(c);
            listClasses();
            Alert a = new Alert(AlertType.INFORMATION);
			a.setTitle("Edit");
			a.setHeaderText(null);
			a.setContentText("Edited Succesfully ");
			a.showAndWait();
            
            
        }
        
        @FXML
        public void deleteClass()
        {
            ObservableList<Classe> allDemandes,SingleDemandes ;
        allDemandes=view.getItems();
        SingleDemandes=view.getSelectionModel().getSelectedItems();
        Classe a = SingleDemandes.get(0);
        cs.DeleteClasse(a);
        SingleDemandes.forEach(allDemandes::remove);
        listClasses();
        }
        
     /*   public void addClass()
        {
            Classe c = new Classe(0, name, level, 0);
        }*/
    
}
