package gui.swing.table2;

import gui.swing.model.ModelAction;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JTable;

public class TableCellAction extends DefaultCellEditor {

    private ModelAction data;

    public TableCellAction() {
        super(new JCheckBox());
    }

    @Override
    public Component getTableCellEditorComponent(JTable jtable, Object o, boolean bln, int i, int i1) {
        data = (ModelAction) o;
        CellAction cell = new CellAction(data, true);
        cell.getBtnDelete().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                stopCellEditing();
            }
        });
        cell.getBtnEdit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                stopCellEditing();
            }
        });
        cell.setBackground(new Color(239, 244, 255));
        return cell;
    }

    //  Chuyển dữ liệu vào ô hiển thị khi ô mất focus
    @Override
    public Object getCellEditorValue() {
        return data;
    }
}
