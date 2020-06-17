/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Elit.entities;

/**
 *
 * @author kinga
 */
public class Classe {

   private int id;
    private String name;
    private String level;
    private int id_classroom;
    public Classe() {
    }

    
    public Classe(int id, String name, String level,int id_classroom) {
        this.id = id;
        this.name = name;
        this.level = level;
        this.id_classroom=id_classroom;
    }

    public void setId_classroom(int id_classroom) {
        this.id_classroom = id_classroom;
    }

    public int getId_classroom() {
        return id_classroom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Classe other = (Classe) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Classe{" + "id=" + id + ", name=" + name + ", level=" + level + ", id_classroom=" + id_classroom + '}';
    }

   
    
    
}
