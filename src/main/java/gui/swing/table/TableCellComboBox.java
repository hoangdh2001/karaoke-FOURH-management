package gui.swing.table;

import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCellComboBox extends DefaultCellEditor {
    private EventAction data;
    public TableCellComboBox() {
        super(new JCheckBox());
    }

//    @Override
//    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
//        data = (EventAction) value;
//        CellComboBox cell = new CellComboBox(data);
////        cell.get
//    }
}
