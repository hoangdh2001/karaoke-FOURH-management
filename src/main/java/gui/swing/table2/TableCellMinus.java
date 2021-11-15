package gui.swing.table2;

import gui.swing.model.ModelMinus;
import java.awt.Color;
import java.awt.Component;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCellMinus extends DefaultCellEditor {
    private ModelMinus data;
    public TableCellMinus() {
        super(new JCheckBox());
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        data = (ModelMinus) o;
        CellMinus cell = new CellMinus(data);
        cell.setBackground(new Color(212, 212, 212));
        return cell;
    }

    //  Chuyển dữ liệu vào ô hiển thị khi ô mất focus
    @Override
    public Object getCellEditorValue() {
        return data;
    }
}
