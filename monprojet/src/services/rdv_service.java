/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

import entities.rdv;
import entities.user;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import utils.ConnexionBD;

/**
 *
 * @author ASUS
 */
public class rdv_service {
        Connection c= ConnexionBD.getInstance().getCnx();
    public void ajouterrdv (rdv rdv)
    {
        try {
            Statement st =c.createStatement();
            String req="insert into rendezvous values("+rdv.getId()+",'"+rdv.getDate()+"','"+rdv.getPrenom()+"','"+rdv.getNom()+"','"+rdv.getCin()+"','"+rdv.getType()+"','"+rdv.getDaterdv()+"','"+rdv.getHeure()+"','"+rdv.getMail()+"','"+rdv.getEtat()+"')";
            st.executeUpdate(req);
            PreparedStatement pt = c.prepareStatement("select id from rendezvous ORDER BY id DESC LIMIT 0, 1");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                rdv.setId(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(rdv_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public List<rdv> afficherRdvNt (String etat,String etat2)
    {    List<rdv> arr=new ArrayList<>();
        try {
            String stat = ("select * from rendezvous where etat='"+etat+"' or etat='"+etat2+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int cin=rs.getInt("cin");
                String daterdv=rs.getString("daterdv");
                String mail=rs.getString("mail");
                String type=rs.getString("type");
                String heure=rs.getString("heurerdv");
                rdv rdvnt =new rdv(cin,nom,prenom,daterdv,mail,etat,type,heure);
                 arr.add(rdvnt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(rdv_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
     rdv traite;
        public rdv traiterrdv (int cin)
    { 
        try {
            String stat = ("select * from rendezvous where cin='"+cin+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                String daterdv=rs.getString("daterdv");
                String mail=rs.getString("mail");
                String type=rs.getString("type");
                String heure=rs.getString("heurerdv");
                traite =new rdv(cin,nom,prenom,daterdv,mail,"Non Traitee",type,heure);
            }
        } catch (SQLException ex) {
            Logger.getLogger(rdv_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return traite;
        
    }
            public void modifieretat(int cin,String etat)
    { 
        try {
            
            PreparedStatement pt = c.prepareStatement("update rendezvous set etat=?   where cin=? ");
            
            pt.setString(1,etat);
            pt.setInt(2,cin);
            pt.executeUpdate();
         } catch (SQLException ex) {
            Logger.getLogger(rdv_service.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
   
         public List<rdv> displayAllReserved ()
    {    List<rdv> arr=new ArrayList<>();
        try {
            String stat = ("select * from rendezvous ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString("nom");
                String prenom=rs.getString("prenom");
                int cin=rs.getInt("cin");
                String daterdv=rs.getString("daterdv");
                String mail=rs.getString("mail");
                String type=rs.getString("type");
                String heure=rs.getString("heurerdv");
                String etat=rs.getString("etat");
                rdv rdvnt =new rdv(cin,nom,prenom,daterdv,mail,etat,type,heure);
                 arr.add(rdvnt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(rdv_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
         
}
