/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package services;

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
import utils.ConnexionBD;

/**
 *
 * @author ASUS
 */
public class user_service {
Connection c= ConnexionBD.getInstance().getCnx();
    public void ajouterteacher (user teacher)
    {
        try {
            Statement st =c.createStatement();
            String req="insert into user (id,username,email,password,type,fistname,lastname,phoneNumber,profilePicture,idrdv,etat,birth,adresse) values("+teacher.getId()+",'"+teacher.getUsername()+"','"+teacher.getEmail()+"','"+teacher.getPassword()+"','"+teacher.getType()+"','"+teacher.getFirstname()+"','"+teacher.getLastname()+"','"+teacher.getPhoneNumber()+"','"+teacher.getProfilePicture()+"','"+teacher.getIdrdv()+"','ACCEPTEE','"+teacher.getBirth()+"','"+teacher.getAdresse()+"')";
            st.executeUpdate(req);
            PreparedStatement pt = c.prepareStatement("select id from user ORDER BY id DESC LIMIT 0, 1");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                teacher.setId(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public void ajouterstudent (user teacher)
    {
        try {
            Statement st =c.createStatement();
            String req="insert into user (id,username,email,password,type,fistname,lastname,phoneNumber,profilePicture,frais,type_payement,nbr_mois_payer,annee_scolaire,niveau,idrdv,etat,birth,adresse) values("+teacher.getId()+",'"+teacher.getUsername()+"','"+teacher.getEmail()+"','"+teacher.getPassword()+"','"+teacher.getType()+"','"+teacher.getFirstname()+"','"+teacher.getLastname()+"','"+teacher.getPhoneNumber()+"','"+teacher.getProfilePicture()+"','"+teacher.getFrais()+"','"+teacher.getType_payement()+"','"+teacher.getNbr_mois_payer()+"','"+teacher.getNiveau()+"','"+teacher.getAnnee_scolaire()+"','"+teacher.getIdrdv()+"','ACCEPTEE','"+teacher.getBirth()+"','"+teacher.getAdresse()+"')";
            st.executeUpdate(req);
            PreparedStatement pt = c.prepareStatement("select id from user ORDER BY id DESC LIMIT 0, 1");
            ResultSet rs = pt.executeQuery();
            while (rs.next()) {            
                teacher.setId(rs.getInt(1));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public boolean seconnecter(String mail,String mdp)
    {   boolean test= true;
        try {
           
            String stat = ("select * from user where user.email='"+mail+"' and user.password='"+mdp+"' ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            if (rs.next()) {
            test = true;
            }
            else 
            {
                System.out.println("verifier vos donnees!!!");
                test=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
      return test;  
        
    }
 
    
      public boolean verifiercompte(String mail, String mdp)
    { String etat="";
      boolean test = false;
        try {
            String stat = ("select etat from user where user.email='"+mail+"' and user.password='"+mdp+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery(); 
             if (rs.next()) {
                etat =rs.getString("etat");
            }
            if(etat.equals("ACCEPTEE"))
            {
              test=true;
            }
            else if(etat.equals("REFUSEE"))
            {
                System.out.println("vous ne pouvez plus vous connecte");
                test=false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
       return test;         
    }
    public String findtype(String mail)
    { String type="";
        try {
            String stat = ("select type from user where user.email='"+mail+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery(); 
             if (rs.next()) {
                type =rs.getString("type");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
       return type;         
    }
        public String findpwd(String mail)
    { String pwd="";
        try {
            String stat = ("select password from user where user.email='"+mail+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery(); 
             if (rs.next()) {
                pwd =rs.getString("password");
            }
           
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
       return pwd;         
    }
             public List<user> afficherstudent (String mail)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user  where email='"+mail+"' ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString(15);
                String prenom=rs.getString(16);
                int tel=rs.getInt(18);
                String birth=rs.getString(27);
                String username=rs.getString(2);            
                String img=rs.getString(19);
                int idClass=rs.getInt(14);
                int frais=rs.getInt(20);
                int nbr_mois_payer=rs.getInt(22);
                String annee_scolaire=rs.getString(23);
                String niveau =rs.getString(24);
                String adresse=rs.getString(28);
                String etat=rs.getString(26);
                String paiement=rs.getString("type_payement");
                 user student =new user(idClass,tel,frais,username,mail,prenom,nom,birth,img,nbr_mois_payer,annee_scolaire,niveau,adresse,etat,paiement);
                 arr.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
             
          public List<user> afficherteacher (String mail)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user  where email='"+mail+"' ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString(15);
                String prenom=rs.getString(16);
                int tel=rs.getInt(18);
                String birth=rs.getString(27);
                String etat = rs.getString(26);
                String type=rs.getString(13);
                int idrdv=rs.getInt(25);
                String username=rs.getString(2);            
                String img=rs.getString(19);
                String adress=rs.getString("adresse");
                user teacher =new user(tel,idrdv,username,mail,"xxxx",type,nom,prenom,birth,img,etat,adress);
             
                arr.add(teacher);
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
           public void ajouterpdp (String mail,String image)
      {
           
            try {
                PreparedStatement pt = c.prepareStatement("update user set profilePicture=? where email=?");
                pt.setString(1,image);
                pt.setString(2,mail);
                pt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
            }
       
              
      }
    public void modifierteacher(String email,String nom,String prenom,String naissance,int tel)
    { 
        try {
            
            PreparedStatement pt = c.prepareStatement("update user set fistname=? ,lastname=? , phoneNumber=?  ,birth=?  where email=? ");
            
            pt.setString(1,prenom);
            pt.setString(2,nom);
            pt.setInt(3,tel);
            pt.setString(4,naissance);
            pt.setString(5,email);
            pt.executeUpdate();
         } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
    public void activerarchiver(String email,String etat)
    { 
        try {
            
            PreparedStatement pt = c.prepareStatement("update user set etat=?  where email=? ");
            
            pt.setString(1,etat);
            pt.setString(2,email);
            pt.executeUpdate();
         } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
              public List<user> afficherAllStudent (String type)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where type='"+type+"'");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
                String nom=rs.getString(15);
                String prenom=rs.getString(16);
                int tel=rs.getInt(18);
                String birth=rs.getString(27);
                String username=rs.getString(2);            
                String img=rs.getString(19);
                int idClass=rs.getInt(14);
                int frais=rs.getInt(20);
                int nbr_mois_payer=rs.getInt(22);
                String annee_scolaire=rs.getString(23);
                String niveau =rs.getString(24);
                String mail=rs.getString(4);
                String adresse=rs.getString(28);
                String etat=rs.getString(26);
                String paiement=rs.getString("type_payement");
                 user student =new user(idClass,tel,frais,username,mail,prenom,nom,birth,img,nbr_mois_payer,annee_scolaire,niveau,adresse,etat,paiement);
                 arr.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }
     public List<user> filtrer (String etat,String type)
    {    List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where etat='"+etat+"' and type='"+type+"' ");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();            
            while (rs.next()) 
            {
               String nom=rs.getString(15);
                String prenom=rs.getString(16);
                int tel=rs.getInt(18);
                String birth=rs.getString(27);
                String username=rs.getString(2);            
                String img=rs.getString(19);
                int idClass=rs.getInt(14);
                int frais=rs.getInt(20);
                int nbr_mois_payer=rs.getInt(22);
                String annee_scolaire=rs.getString(23);
                String niveau =rs.getString(24);
                String mail=rs.getString(4);
                String adresse=rs.getString(28);
                String paiement=rs.getString("type_payement");
                 user student =new user(idClass,tel,frais,username,mail,prenom,nom,birth,img,nbr_mois_payer,annee_scolaire,niveau,adresse,etat,paiement);
                 arr.add(student);
            }
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arr;
        
    }       
   public List<user> getTrierByNom(String type)
              {
        
            List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where type='"+type+"' ORDER BY lastname DESC");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();   
            
            while (rs.next()) 
            {
                String nom=rs.getString(15);
                String prenom=rs.getString(16);
                int tel=rs.getInt(18);
                String birth=rs.getString(27);
                String username=rs.getString(2);            
                String img=rs.getString(19);
                int idClass=rs.getInt(14);
                int frais=rs.getInt(20);
                int nbr_mois_payer=rs.getInt(22);
                String annee_scolaire=rs.getString(23);
                String niveau =rs.getString(24);
                String mail=rs.getString(4);
                String adresse=rs.getString(28);
                  String etat=rs.getString(26);
                String paiement=rs.getString("type_payement");
                 user student =new user(idClass,tel,frais,username,mail,prenom,nom,birth,img,nbr_mois_payer,annee_scolaire,niveau,adresse,etat,paiement);
                 arr.add(student);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;
    }
    public List<user> getTrierByNiveau()
              {
        
            List<user> arr=new ArrayList<>();
        try {
            String stat = ("select * from user where type='Student' ORDER BY niveau DESC");
            PreparedStatement pt=c.prepareStatement(stat);
            ResultSet rs = pt.executeQuery();   
            
            while (rs.next()) 
            {
                String nom=rs.getString(15);
                String prenom=rs.getString(16);
                int tel=rs.getInt(18);
                String birth=rs.getString(27);
                String username=rs.getString(2);            
                String img=rs.getString(19);
                int idClass=rs.getInt(14);
                int frais=rs.getInt(20);
                int nbr_mois_payer=rs.getInt(22);
                String annee_scolaire=rs.getString(23);
                String niveau =rs.getString(24);
                String mail=rs.getString(4);
                String adresse=rs.getString(28);
                 String etat=rs.getString(26);
                String paiement=rs.getString("type_payement");
                 user student =new user(idClass,tel,frais,username,mail,prenom,nom,birth,img,nbr_mois_payer,annee_scolaire,niveau,adresse,etat,paiement);
                 arr.add(student);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
        }
            return arr;
    }
     public void editname (String mail,String nom)
      {
           
            try {
                PreparedStatement pt = c.prepareStatement("update user set lastname=? where email=?");
                pt.setString(1,nom);
                pt.setString(2,mail);
                pt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
            }
       
              
      }
          public void editstudent (user student)
      {
           
            try {
                PreparedStatement pt = c.prepareStatement("update user set fistname=?,lastname=?,phoneNumber=?,birth=?,annee_scolaire=?,frais=?,nbr_mois_payer=?,niveau=?,type_payement=? where email=?");
                pt.setString(1,student.getFirstname());
                pt.setString(2,student.getLastname());
                pt.setInt(3,student.getPhoneNumber());
                pt.setString(4,student.getBirth());
                pt.setString(5,student.getAnnee_scolaire());
                pt.setInt(6,student.getFrais());
                pt.setInt(7,student.getNbr_mois_payer());
                pt.setString(8,student.getNiveau());
                pt.setString(9,student.getType_payement());
                pt.setString(10,student.getEmail());
                 pt.executeUpdate();
            } catch (SQLException ex) {
                Logger.getLogger(user_service.class.getName()).log(Level.SEVERE, null, ex);
            }
       
              
      }
}
