package gui.swing.textfield;

import gui.swing.event.EventSelectedRow;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.util.List;
import net.miginfocom.swing.MigLayout;

public class PanelSearch extends javax.swing.JPanel {

    private EventSelectedRow event;
    private int selectedIndex = -1;
    private String columnName;

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }
    
    public void addEventClick(EventSelectedRow event) {
        this.event = event;
    }

    public PanelSearch(String columnName) {
        this.columnName = columnName;
        initComponents();
        setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
    }

    public void setData(List<?> data) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        selectedIndex = -1;
        this.removeAll();
        for (Object obj : data) {
            SearchItem item = new SearchItem(obj, columnName);
            item.addEvent(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    event.selectedRow(obj);
                }
            });
            this.add(item, "wrap");
        }
        repaint();
        revalidate();
    }

    public int getItemSize() {
        return getComponentCount();
    }

    public void keyUp() {
        int size = getComponentCount();
        if (size > 0) {
            if (selectedIndex <= 0) {
                selectedIndex = size - 1;
            } else {
                selectedIndex--;
            }
            showSelected();
        }
    }

    public void keyDown() {
        int size = getComponentCount();
        if (size > 0) {
            if (selectedIndex >= size - 1) {
                selectedIndex = 0;
            } else {
                selectedIndex++;
            }
            showSelected();
        }
    }

    public Object getSelectedRow() {
        if (selectedIndex != -1 && selectedIndex < getComponentCount()) {
            return ((SearchItem) getComponent(selectedIndex)).getObject();
        }
        return "";
    }

    public void clearSelected() {
        selectedIndex = -1;
        showSelected();
    }

    private void showSelected() {
        Component com[] = getComponents();
        for (int i = 0; i < com.length; i++) {
            ((SearchItem) com[i]).setSelected(i == selectedIndex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 168, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
