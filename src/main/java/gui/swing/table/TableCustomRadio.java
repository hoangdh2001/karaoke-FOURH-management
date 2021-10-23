/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.event.TableModelEvent;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author 84975
 */
public class TableCustomRadio extends AbstractTableModel{

    private ButtonGroup bg;

    public TableCustomRadio(DefaultTableModel model) {
        super(model);
        initButton();  
    }
    
    @Override
    public void tableChanged(TableModelEvent e) {
        super.tableChanged(e); //To change body of generated methods, choose Tools | Templates.
        repaint();
    }

    @Override
    public void setRowSelectionInterval(int index0, int index1) {
        super.setRowSelectionInterval(index0, index1); //To change body of generated methods, choose Tools | Templates.
        
    }
    
    public void initButton(){
        bg = new ButtonGroup();
        
        for(int i = 0; i < getRowCount(); i++){
             bg.add((JRadioButton)getModel().getValueAt(i,lastColumn));
        }
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.RIGHT );
        getColumnModel().getColumn(lastColumn).setCellRenderer( centerRenderer );
        
        getColumn(getColumnName(lastColumn)).setCellRenderer(new RadioButtonRenderer());
        getColumn(getColumnName(lastColumn)).setCellEditor(new RadioButtonEditor(new JCheckBox(),"radio"));
        
        getColumnModel().getColumn(lastColumn).setPreferredWidth(2);
    }
    
    
}
