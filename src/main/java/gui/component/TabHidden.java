package gui.component;

import gui.swing.panel.PanelShadow;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import net.miginfocom.swing.MigLayout;

public class TabHidden extends PanelShadow {
    
    private MigLayout layout;
    private boolean tabShow;

    public boolean isTabShow() {
        return tabShow;
    }

    public void setTabShow(boolean tabShow) {
        this.tabShow = tabShow;
    }
    
    public void addAction(ActionListener evt) {
        btnClose.addActionListener(evt);
    }
    
    public TabHidden() {
        initComponents();
        btnClose.setMnemonic(KeyEvent.VK_X);
        
        layout = new MigLayout("fill, insets 0", "[fill]", "50[fill]");
        setLayout(layout);
        
        add(btnClose, "pos -20 0al");
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnClose = new gui.swing.button.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 30, 1, 1));
        setShadowOpacity(0.2F);
        setShadowSize(2);
        setShadowType(gui.swing.graphics.ShadowType.TOP_LEFT);

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_multiply_30px_1.png"))); // NOI18N
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 487, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(btnClose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(434, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.button.Button btnClose;
    // End of variables declaration//GEN-END:variables
}
