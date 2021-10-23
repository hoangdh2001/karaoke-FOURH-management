/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 84975
 */
public class TableCustomCheckBox extends AbstractTableModel{


    public TableCustomCheckBox(DefaultTableModel model) {
        super(model);
        initButton();
    }
    
    public void initButton(){
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
        getColumnModel().getColumn(lastColumn).setCellRenderer( centerRenderer );
        getColumnModel().getColumn(lastColumn).setPreferredWidth(20);
        
        getColumn(getColumnName(getColumnCount()-1)).setCellRenderer(new RadioButtonRenderer());
        getColumn(getColumnName(getColumnCount()-1)).setCellEditor(new RadioButtonEditor(new JCheckBox(),"checkbox"));
        
        getColumnModel().getColumn(lastColumn).setPreferredWidth(2);

    }
    
}
