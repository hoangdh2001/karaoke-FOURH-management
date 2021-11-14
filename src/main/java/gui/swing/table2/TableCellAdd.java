package gui.swing.table2;

import gui.swing.model.ModelAdd;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCellAdd extends DefaultCellEditor {

    private ModelAdd data;

    public TableCellAdd() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        data = (ModelAdd) value;
        CellAdd cell = new CellAdd(data);
        cell.setBackground(new Color(212, 212, 212));
        return cell;
    }

    @Override
    public Object getCellEditorValue() {
        return data;
    }
}
