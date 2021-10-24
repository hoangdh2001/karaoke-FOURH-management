/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JTable;

/**
 *
 * @author 84975
 */

    class RadioButtonEditor extends DefaultCellEditor implements ItemListener {
        
        private JRadioButton radioButton;
        private JCheckBox checkBox;
        private String type;
        public RadioButtonEditor(JCheckBox checkBox,String type) {
          super(checkBox);
          this.type = type;
        }

        public Component getTableCellEditorComponent(JTable table, Object value,
            boolean isSelected, int row, int column) {
          if (value == null)
            return null;
          if(type.equalsIgnoreCase("checkbox")){
                checkBox = (JCheckBox) value;
                checkBox.addItemListener(this);
                return (Component) value;
            }
          radioButton = (JRadioButton) value;
          radioButton.addItemListener(this);
          return (Component) value;
        }

        public Object getCellEditorValue() {
            if(type.equalsIgnoreCase("checkbox")){
                checkBox.removeItemListener(this);
                return checkBox;
            }
          radioButton.removeItemListener(this);
          return radioButton;
        }

        public void itemStateChanged(ItemEvent e) {
          super.fireEditingStopped();
        }
    }
