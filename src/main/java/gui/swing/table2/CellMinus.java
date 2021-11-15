package gui.swing.table2;

import gui.swing.model.ModelMinus;
import java.awt.event.ActionEvent;

public class CellMinus extends javax.swing.JPanel {

    public CellMinus(ModelMinus data) {
        initComponents();
        btnMinus.addActionListener((ActionEvent arg0) -> {
            data.getEvent().minus(data.getObject());
        });
        btnCancel.addActionListener((ActionEvent arg0) -> {
            data.getEvent().cancel(data.getObject());
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnMinus = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();

        btnMinus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/minus_15px.png"))); // NOI18N

        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/cancel_15px.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(btnMinus, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnMinus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnMinus;
    // End of variables declaration//GEN-END:variables
}
