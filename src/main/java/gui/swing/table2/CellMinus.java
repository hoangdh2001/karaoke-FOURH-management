package gui.swing.table2;

import gui.swing.event.EventMinus;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class CellMinus extends javax.swing.JPanel {

    public CellMinus(EventMinus event) {
        initComponents();
        btnCancel.addActionListener((ActionEvent arg0) -> {
            event.cancel();
        });
    }

    public JButton getBtnCancel() {
        return btnCancel;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancel = new javax.swing.JButton();

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cancel_15px.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    // End of variables declaration//GEN-END:variables
}
