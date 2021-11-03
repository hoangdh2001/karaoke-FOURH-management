/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.swing.table2;

import entity.TrangThaiPhieuDat;
import entity.TrangThaiPhong;
import gui.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author Admin
 */
public class MyTable extends JTable {
    private ModelAction action;
    public MyTable() {
        setShowHorizontalLines(true);
        setSelectionBackground(new Color(239, 244, 255));
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                MyTableHeader header = new MyTableHeader(o + "");
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean bln1, int i, int i1) {
                if (o instanceof TrangThaiPhong | o instanceof TrangThaiPhieuDat) {
                    CellStatus cell = new CellStatus(o);
                    if (selected) {
                        cell.setBackground(new Color(239, 244, 255));
                        cell.setForeground(new Color(15, 89, 140));
                    } else {
                        cell.setBackground(Color.WHITE);
                        cell.setForeground(new Color(102, 102, 102));
                    }
                    return cell;     //        fix ở đây
                } else if(o instanceof Boolean) {
                    CellCheckBox cell = new CellCheckBox();
                    cell.select(selected);
                    if (selected) {
                        cell.setBackground(new Color(239, 244, 255));
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    return cell;
                } else if(o instanceof Class) {
                    CellCheckBox cell = new CellCheckBox();
                    cell.select(selected);
                    if (selected) {
                        cell.setBackground(new Color(239, 244, 255));
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    return cell;
                } else if(o instanceof ModelAction) {
                    action = (ModelAction) o;
                    CellAction cell = new CellAction(action);
                    if (selected) {
                        cell.setBackground(new Color(255, 255, 255));
                    } else {
                        cell.setBackground(Color.WHITE);
                    }
                    return cell;
                }
                else {
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, bln1, i, i1);
                    setBorder(noFocusBorder);
                    if (selected) {
                        com.setBackground(new Color(239, 244, 255));
                        com.setForeground(new Color(15, 89, 140));
                    } else {
                        com.setBackground(Color.WHITE);
                        com.setForeground(new Color(102, 102, 102));
                    }
                    return com;
                }
            }

        });
    }
    
    @Override
    public TableCellEditor getCellEditor(int row, int col) {
//        fix ở đây
        if(getColumnName(col).equalsIgnoreCase("Chọn") || getColumnName(col).equalsIgnoreCase("Số lượng")){
            return super.getCellEditor(row, col);
        }else if (col == getColumnCount() - 1) {
            if (getValueAt(row, col) instanceof ModelAction) {
                return new TableCellAction();
            }
            return super.getCellEditor(row, col);
        } else {
            return super.getCellEditor(row, col);
        }
    }
    
    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }

    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.WHITE);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.WHITE);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
    }
}
