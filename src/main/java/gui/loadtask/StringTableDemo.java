/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.loadtask;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.AbstractCellEditor;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;

public class StringTableDemo extends JFrame {

    public StringTableDemo() {

        final StringTableModel model = new StringTableModel();
        model.addRow("Jonas");
        model.addRow("Hello");
        model.addRow("World");

        RendererAndEditor rendererAndEditor = new RendererAndEditor(model);

        JTable table = new JTable(model);
        table.setDefaultRenderer(Record.class, rendererAndEditor);
        table.setDefaultEditor(Record.class, rendererAndEditor);

        add(new JScrollPane(table), BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    class Record {

        String string;
        boolean isDeleted;
    }

    class StringTableModel extends AbstractTableModel {

        private final List<Record> data = new ArrayList<Record>();

        @Override
        public int getColumnCount() {
            return 1;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public Object getValueAt(int row, int column) {
            return data.get(row);
        }

        @Override
        public Class<?> getColumnClass(int column) {
            return Record.class;
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return true;
        }

        @Override
        public void setValueAt(Object aValue, int row, int column) {
            if (aValue instanceof Record) {
                Record r = (Record) aValue;
                if (!r.isDeleted) {
                    data.set(row, r);
                    fireTableRowsUpdated(row, column);
                }
            } else {
                throw new IllegalStateException("aValue is not a Record");
            }
        }

        public void addRow(String s) {
            Record r = new Record();
            r.string = s;
            r.isDeleted = false;
            data.add(r);
            fireTableRowsInserted(data.size() - 1, data.size() - 1);
        }

        public void removeRow(int row) {
            data.remove(row);
            //fireTableRowsDeleted(row, row);
            fireTableDataChanged();

            System.out.println("row " + row + " deleted");
        }
        
    }

    class CellPanel extends JPanel {

        private final AbstractAction removeAction = new AbstractAction("x") {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                model.removeRow(index);
                isDeleted = true;
            }

        };
        private JButton removeBtn = new JButton(removeAction);
        private final JTextField field = new JTextField();
        private final StringTableModel model;
        private int index;
        private boolean isDeleted = false;

        public CellPanel(StringTableModel model) {
            super(new BorderLayout());
            this.model = model;
            add(field, BorderLayout.CENTER);
            add(removeBtn, BorderLayout.EAST);
        }

        public JButton getRemoveBtn() {
            return removeBtn;
        }

        public void setRemoveBtn(JButton removeBtn) {
            this.removeBtn = removeBtn;
        }
        
        public Record getRecord() {
            Record r = new Record();
            r.string = field.getText();
            r.isDeleted = isDeleted;
            return r;
        }

        public void setRecord(Record r, int index) {
            field.setText(r.string);
            this.index = index;
        }
        
        
    }

    class RendererAndEditor extends AbstractCellEditor implements
            TableCellEditor, TableCellRenderer {

        private final CellPanel renderer;
        private final CellPanel editor;

        public RendererAndEditor(StringTableModel model) {
            renderer = new CellPanel(model);
            editor = new CellPanel(model);
            editor.getRemoveBtn().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    stopCellEditing();
                }
            });
        }

        @Override
        public Object getCellEditorValue() {
            return editor.getRecord();
        }

        @Override
        public Component getTableCellRendererComponent(JTable table,
                Object value, boolean isSelected, boolean hasFocus,
                int row, int column) {

            renderer.setRecord((Record) value, row);
            return renderer;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table,
                Object value, boolean isSelected, int row, int column) {
            editor.setRecord((Record) value, row);
            return editor;
        }

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new StringTableDemo();
            }

        });
    }
}
