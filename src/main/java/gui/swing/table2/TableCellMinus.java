package gui.swing.table2;

import gui.swing.event.EventMinus;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCellMinus extends DefaultCellEditor {
    private EventMinus data;
    public TableCellMinus() {
        super(new JCheckBox());
    }
    
    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        data = (EventMinus) o;
        CellMinus cell = new CellMinus(data);
        cell.getBtnCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                stopCellEditing();
            }
        });
        cell.setBackground(new Color(212, 212, 212));
        return cell;
    }

    //  Chuyển dữ liệu vào ô hiển thị khi ô mất focus
    @Override
    public Object getCellEditorValue() {
        return data;
    }
}
