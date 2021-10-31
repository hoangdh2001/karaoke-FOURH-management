/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objectcombobox;

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

    @Override
    public String toString() {
        return ten;
    }

    public ObjectComboBox(String ten, String ma) {
        this.ten = ten;
        this.ma = ma;
    }
}
