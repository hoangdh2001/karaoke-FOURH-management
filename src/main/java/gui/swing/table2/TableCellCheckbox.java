package gui.swing.table2;

import java.awt.Color;
import java.awt.Component;
import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class TableCellCheckbox extends AbstractCellEditor implements TableCellRenderer, TableCellEditor {

    private boolean selected;

    public TableCellCheckbox() {
        
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        selected = (Boolean) value;
        CellCheckBox cell = new CellCheckBox();
        cell.select(selected);
        cell.setBackground(new Color(239, 244, 255));
        return cell;
    }
    
    @Override
    public Object getCellEditorValue() {
        return selected;
    }

    @Override
    public Component getTableCellRendererComponent(JTable arg0, Object arg1, boolean arg2, boolean arg3, int arg4, int arg5) {
        selected = (boolean) arg1;
        CellCheckBox cell = new CellCheckBox();
        cell.select(selected);
        cell.setBackground(new Color(239, 244, 255));
        return cell;
    }
}
