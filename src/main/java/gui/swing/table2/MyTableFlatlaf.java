package gui.swing.table2;

import gui.swing.button.Button;
import gui.swing.model.ModelAdd;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableFlatlaf extends JTable {
    public MyTableFlatlaf() {
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if(value instanceof ModelAdd) {
                    ModelAdd data = (ModelAdd) value;
                    Button button = new Button("Thêm");
                    button.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent arg0) {
                            data.getEvt().add(data.getObj());
                        }
                    });
                    return button;
                } else {
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column); //To change body of generated methods, choose Tools | Templates.
                }
            }
        });
    }
}
