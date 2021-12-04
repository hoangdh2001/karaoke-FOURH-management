package gui.swing.table2;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.border.EmptyBorder;

public class CellCheckBox extends javax.swing.JPanel {

    private boolean isSelected;

    public CellCheckBox(boolean isSelected) {
        this.isSelected = isSelected;
        initComponents();
        setBorder(new EmptyBorder(2, 2, 2, 2));
    }

    public void select(boolean isSelect) {
        jCheckBox1.setSelected(isSelect);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (!isSelected) {
            grphcs.setColor(new Color(230, 230, 230));
            grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBox1 = new javax.swing.JCheckBox();

        jCheckBox1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCheckBox1, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jCheckBox1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox jCheckBox1;
    // End of variables declaration//GEN-END:variables
}
