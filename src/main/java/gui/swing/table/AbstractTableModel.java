/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package gui.swing.table;

import com.sun.java.accessibility.util.AWTEventMonitor;
import gui.swing.scrollbar.ScrollBarCustom;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author 84975
 */
public abstract class AbstractTableModel extends JTable{
    JTable table = new JTable();
    protected String fontName = "sansserif";
    protected int fontStyle = 1;
    protected int fontSize = 12;
    protected int lastColumn;
    protected ActionListener e;
//    protected 


    public void setFontName(String fontName) {
        this.fontName = fontName;
        this.setFont(new Font(fontName, fontStyle, fontSize));
    }

    public void setFontStyle(int fontStyle) {
        this.fontStyle = fontStyle;
        this.setFont(new Font(fontName, fontStyle, fontSize));
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
        this.setFont(new Font(fontName, fontStyle, fontSize));
    }
    
    public AbstractTableModel(DefaultTableModel model){
        super(model);
        lastColumn = getColumnCount()-1;
        setShowHorizontalLines(true);
        setOpaque(false); 
        setFont(new Font(fontName, fontStyle, fontSize));
        setGridColor(new Color(245, 245, 245));
        setRowHeight(30);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                if (i1 == lastColumn) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
            
        });
        
        addEventHeader();
        
        AWTEventMonitor.addActionListener(e);
    }
    
    public void addEventHeader(){
        getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int col = columnAtPoint(e.getPoint());
                String name = getColumnName(col);
                System.out.println("Column index selected " + col + " " + name);
            }
        });
    }

    @Override
    public int getSelectedRow() {
        return super.getSelectedRow(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    public void fixTable(JScrollPane scroll) {
        scroll.getViewport().setBackground(Color.cyan);
        scroll.setVerticalScrollBar(new ScrollBarCustom());
        JPanel p = new JPanel();
        p.setBackground(Color.cyan);
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scroll.setBorder(new EmptyBorder(5, 10, 5, 10));
        scroll.setBackground(Color.WHITE);
    }
    
    public void addRow(Object[] row) {
        DefaultTableModel mod = (DefaultTableModel) getModel();
        mod.addRow(row);
    }
    
    public void addEvent(ActionListener e){
        this.e = e;
    }

}
