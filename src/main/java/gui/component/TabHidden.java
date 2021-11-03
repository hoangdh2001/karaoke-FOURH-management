package gui.component;

import gui.swing.panel.PanelShadow;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class TabHidden extends PanelShadow {
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
        setFocusable(true);
        btnClose.setMnemonic(KeyEvent.VK_X);
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnClose = new gui.swing.button.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 1, 1));
        setShadowOpacity(0.2F);
        setShadowSize(2);
        setShadowType(gui.dropshadow.ShadowType.TOP_LEFT);
        setLayout(new java.awt.BorderLayout());

        btnClose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_multiply_30px_1.png"))); // NOI18N
        btnClose.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnClose.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        add(btnClose, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.button.Button btnClose;
    // End of variables declaration//GEN-END:variables
}
