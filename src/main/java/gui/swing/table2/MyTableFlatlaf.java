package gui.swing.table2;

import gui.swing.model.ModelAdd;
import gui.swing.model.ModelMinus;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;

public class MyTableFlatlaf extends JTable {

    public MyTableFlatlaf() {
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof ModelAdd) {
                    ModelAdd data = (ModelAdd) value;
                    CellAdd button = new CellAdd(data);
                    if (isSelected) {
                        button.setBackground(getSelectionBackground());
                        button.setForeground(getSelectionForeground());
                    } else {
                        button.setBackground(Color.WHITE);
                        button.setForeground(Color.BLACK);
                    }
                    return button;
                } else if (value instanceof ModelMinus) {
                    ModelMinus data = (ModelMinus) value;
                    CellMinus button = new CellMinus(data);
                    if (isSelected) {
                        button.setBackground(getSelectionBackground());
                        button.setForeground(getSelectionForeground());
                    } else {
                        button.setBackground(Color.WHITE);
                        button.setForeground(Color.BLACK);
                    }
                    return button;
                } else {
                    Component com = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                    setBorder(noFocusBorder);
                    if (isSelected) {
                        com.setBackground(getSelectionBackground());
                        com.setForeground(getSelectionForeground());
                    } else {
                        com.setBackground(Color.WHITE);
                        com.setForeground(Color.BLACK);
                    }
                    return com;
                }
            }
        });
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        if (getValueAt(row, column) instanceof ModelAdd) {
            return new TableCellAdd();
        } else if(getValueAt(row, column) instanceof ModelMinus) {
            return new TableCellMinus();
        }
        return super.getCellEditor(row, column);
    }
}
