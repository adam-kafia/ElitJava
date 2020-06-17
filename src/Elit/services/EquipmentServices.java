/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.services;

import Elit.entities.Equipment;
import Elit.utils.DbConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kinga
 */
public class EquipmentServices {
        Connection c;

    public EquipmentServices() {
        c = DbConnection.getInstance().getCnx();
    }

    public void addEquipment(Equipment cl) {
        try {
            String requete = "insert into equipement (label,category,qte,qte_init) values(?,?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, cl.getLabel());
            pst.setString(2, cl.getCategory());
            pst.setDouble(3, cl.getQte());
            pst.setDouble(4, cl.getQteInit());
            pst.executeUpdate();
            System.out.println("Equipment added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Equipment> ListClasse() {
        List<Equipment> Mylist = new ArrayList<>();
        try {
            String requete = "select * from equipement";
            PreparedStatement pst = c.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Equipment p = new Equipment();
                p.setId(rs.getInt("id"));
                p.setLabel(rs.getString("label"));
                p.setCategory(rs.getString("category"));
                p.setQte(rs.getFloat("qte"));
                p.setQteInit(rs.getFloat("qte_init"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    public void UpdateClasse(Equipment cl) {
        try {
            String requete = "update equipement set label=?,category=?,qte=?,qte_init=? where ? = id";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, cl.getLabel());
            pst.setString(2, cl.getCategory());
            pst.setFloat(3, cl.getQte());
            pst.setFloat(4, cl.getQteInit());
            pst.setInt(5, cl.getId());
            pst.executeUpdate();
            System.out.println("Equipment Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void DeleteClasse(Equipment cl) {
        try {
            String requete = "delete from Equipement where ? = id";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, cl.getId());
            pst.executeUpdate();
            System.out.println("Equipement Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
}
