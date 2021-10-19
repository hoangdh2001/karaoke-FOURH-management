package gui.component;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.ImageIcon;

public class Message extends javax.swing.JPanel {

    private MessageType messageType = MessageType.SUCCESS;
    private boolean show;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public Message() {
        initComponents();
        setOpaque(false);
    }

    public void showMessage(MessageType messageType, String message) {
        this.messageType = messageType;
        lblMessage.setText(message);
        if (messageType == MessageType.SUCCESS) {
            lblMessage.setIcon(new ImageIcon(getClass().getResource("/icon/success.png")));
        } else {
            lblMessage.setIcon(new ImageIcon(getClass().getResource("/icon/error.png")));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMessage = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(300, 30));

        lblMessage.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblMessage.setForeground(new java.awt.Color(248, 248, 248));
        lblMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblMessage.setText("jLabel2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        if (messageType == MessageType.SUCCESS) {
            g2.setColor(new Color(15, 174, 37));
        } else {
            g2.setColor(new Color(240, 52, 53));
        }
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setColor(new Color(245, 245, 245));
        g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }

    public static enum MessageType {
        SUCCESS, ERROR
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMessage;
    // End of variables declaration//GEN-END:variables
}
