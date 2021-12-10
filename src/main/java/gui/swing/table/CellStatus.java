package gui.swing.table;

import entity.TrangThaiPhieuDat;
import entity.TrangThaiPhong;
import java.awt.Color;
import java.awt.Graphics;

public class CellStatus extends javax.swing.JPanel {

    private boolean selected;

    public CellStatus(Object o, boolean isSelected) {
        this.selected = isSelected;
        initComponents();
        if (o instanceof TrangThaiPhong) {
            TrangThaiPhong trangThai = (TrangThaiPhong) o;
            lblStatus.setText(trangThai.getTrangThai());
            lblStatus.setColorStatus(trangThai.getColor());
        } else if (o instanceof TrangThaiPhieuDat) {
            TrangThaiPhieuDat trangThai = (TrangThaiPhieuDat) o;
            lblStatus.setText(trangThai.getTrangThai());
            lblStatus.setColorStatus(trangThai.getStatusColor());
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (!selected) {
            grphcs.setColor(new Color(230, 230, 230));
            grphcs.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new gui.swing.table.LabelStatus();

        lblStatus.setText("Status");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.table.LabelStatus lblStatus;
    // End of variables declaration//GEN-END:variables
}
