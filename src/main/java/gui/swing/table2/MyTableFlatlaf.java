package gui.swing.table2;

import entity.TrangThaiPhieuDat;
import entity.TrangThaiPhong;
import gui.swing.event.EventMinus;
import gui.swing.model.ModelAction;
import gui.swing.model.ModelAdd;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

public class MyTableFlatlaf extends JTable {

    public MyTableFlatlaf() {
        setGridColor(new Color(230, 230, 230));
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                if (value instanceof TrangThaiPhong | value instanceof TrangThaiPhieuDat) {
                    CellStatus cell = new CellStatus(value, isSelected);
                    if (isSelected) {
                        cell.setBackground(getSelectionBackground());
                        cell.setForeground(getSelectionForeground());
                    } else {
                        cell.setBackground(Color.WHITE);
                        cell.setForeground(Color.BLACK);
                    }
                    return cell;
                } else if (value instanceof ModelAdd) {
                    ModelAdd data = (ModelAdd) value;
                    CellAdd button = new CellAdd(data);
                    if (isSelected) {
                        button.setBackground(getSelectionBackground());
                        button.setForeground(getSelectionForeground());
                    } else {
                        button.setBackground(Color.WHITE);
                        button.setForeground(getForeground());
                    }
                    return button;
                } else if(value instanceof Class) {
                    CellCheckBox cell = new CellCheckBox(isSelected);
                    cell.select(isSelected);
                    if (isSelected) {
                        cell.setBackground(getSelectionBackground());
                        cell.setForeground(getSelectionForeground());
                    } else {
                        cell.setBackground(Color.WHITE);
                        cell.setForeground(Color.BLACK);
                    }
                    return cell;
                }else if(value instanceof ModelAction) {
                    ModelAction action = (ModelAction) value;
                    CellAction cell = new CellAction(action, isSelected);
                    if (isSelected) {
                        cell.setBackground(getSelectionBackground());
                        cell.setForeground(getSelectionForeground());
                    } else {
                        cell.setBackground(Color.WHITE);
                        cell.setForeground(Color.BLACK);
                    }
                    return cell;
                } else if (value instanceof EventMinus) {
                    EventMinus data = (EventMinus) value;
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
    
    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

    @Override
    public TableCellEditor getCellEditor(int row, int column) {
        if (getValueAt(row, column) instanceof ModelAdd) {
            return new TableCellAdd();
        } else if(getValueAt(row, column) instanceof EventMinus) {
            return new TableCellMinus();
        } else if(getValueAt(row, column) instanceof  ModelAction) {
            return  new TableCellAction();
        }
        return super.getCellEditor(row, column);
    }
}
