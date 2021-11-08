package gui.component;

import entity.TrangThaiPhong;
public class PanelStatus extends javax.swing.JPanel {

    /**
     * Creates new form PanelStatus
     * @param trangThai
     */
    public PanelStatus(TrangThaiPhong trangThai) {
        initComponents();
        lblNum.setBorderRadius(10);
        lblNum.setBackground(trangThai.getColor());
        lblStatus.setText(": " + trangThai.getTrangThai());
    }
    
    public void setText(Object obj) {
        lblNum.setText(obj.toString());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblStatus = new javax.swing.JLabel();
        lblNum = new gui.swing.label.LabelRound();

        setOpaque(false);

        lblStatus.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblNum.setForeground(new java.awt.Color(255, 255, 255));
        lblNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblNum.setText("0");
        lblNum.setBorderRadius(10);
        lblNum.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblNum, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lblNum, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.label.LabelRound lblNum;
    private javax.swing.JLabel lblStatus;
    // End of variables declaration//GEN-END:variables
}
