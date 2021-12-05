/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectcombobox;

import java.util.Objects;

/**
 *
 * @author 84975
 */
public class ObjectComboBox {
    private String ten;
    private String ma;


    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }
    
    @Override
    public String toString() {
        return ten;
    }

    public ObjectComboBox(String ten, String ma) {
        this.ten = ten;
        this.ma = ma;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final ObjectComboBox other = (ObjectComboBox) obj;
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        return true;
    }
    
    
}
