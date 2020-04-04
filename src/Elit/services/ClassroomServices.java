/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.services;

import Elit.entities.Classroom;
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
public class ClassroomServices {
      Connection c;

    public ClassroomServices() {
        c = DbConnection.getInstance().getCnx();
    }

    public void addClassroom(Classroom cl) {
        try {
            String requete = "insert into classrooms (name,capacity,bloc) values(?,?,?)";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, cl.getName());
            pst.setInt(2, cl.getCapacity());
            pst.setString(3, cl.getBloc());
            pst.executeUpdate();
            System.out.println("Classroom added !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Classroom> ListClassroom() {
        List<Classroom> Mylist = new ArrayList<>();
        try {
            String requete = "select * from classrooms";
            PreparedStatement pst = c.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Classroom p = new Classroom();
                p.setName(rs.getString("name"));
                p.setCapacity(rs.getInt("capacity"));
                p.setBloc(rs.getString("bloc"));
                p.setId(rs.getInt("id"));
                Mylist.add(p);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Mylist;
    }

    public void UpdateClassroom(Classroom cl) {
        try {
            String requete = "update classrooms set name=?,capacity=?,bloc=?  where ? = id";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setString(1, cl.getName());
            pst.setInt(2, cl.getCapacity());
            pst.setString(3, cl.getBloc());
            pst.setInt(4, cl.getId());
            pst.executeUpdate();
            System.out.println("Classroom Updated !!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void DeleteClasse(Classroom cl) {
        try {
            String requete = "delete from classrooms where ? = id";
            PreparedStatement pst = c.prepareStatement(requete);
            pst.setInt(1, cl.getId());
            pst.executeUpdate();
            System.out.println("Classroom Deleted !!!!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
    }
    
}
