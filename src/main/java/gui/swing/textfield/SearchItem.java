package gui.swing.textfield;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.Field;

public class SearchItem extends javax.swing.JPanel {
    private Object obj;
    private String columnName;
    public SearchItem(Object obj, String columnName) throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        initComponents();
        this.obj = obj;
        this.columnName = columnName;
        setData();
    }

    private void setData() throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        addEventMouse(this);
        addEventMouse(lbText);
        Class<? extends Object> c1 = obj.getClass();
        Field field = c1.getDeclaredField(columnName);
        field.setAccessible(true);
        lbText.setText(field.get(obj) + "");
    }

    private void addEventMouse(Component com) {
        com.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(215, 216, 216));
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(Color.WHITE);
            }

        });
    }

    private ActionListener eventClick;

    public void addEvent(ActionListener eventClick) {
        this.eventClick = eventClick;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbText = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        lbText.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbText.setForeground(new java.awt.Color(38, 38, 38));
        lbText.setText("Text ...");
        lbText.setIconTextGap(10);
        lbText.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbTextMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbText, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbText, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbTextMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbTextMouseClicked
        eventClick.actionPerformed(null);
    }//GEN-LAST:event_lbTextMouseClicked
    public Object getObject() {
        return obj;
    }

    public void setSelected(boolean act) {
        if (act) {
            setBackground(new Color(215, 216, 216));
        } else {
            setBackground(Color.WHITE);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbText;
    // End of variables declaration//GEN-END:variables
}
