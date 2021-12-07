package gui.swing.table2;

import gui.swing.model.ModelAdd;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class CellAdd extends javax.swing.JPanel {

    public CellAdd(ModelAdd data) {
        initComponents();
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                data.getEvt().add(data.getObj());
            }
        });
    }

    public JButton getBtnAdd() {
        return btnAdd;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAdd = new javax.swing.JButton();

        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/add_15px.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAdd, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 24, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    // End of variables declaration//GEN-END:variables
}
