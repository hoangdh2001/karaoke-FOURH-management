
package gui.swing.image;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public class BackgroundImage extends javax.swing.JPanel {
    private Icon icon;
    private Color ftColor;

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Color getFtColor() {
        return ftColor;
    }

    public void setFtColor(Color ftColor) {
        this.ftColor = ftColor;
    }
    
    public BackgroundImage(Icon icon) {
        this.icon = icon;
        setOpaque(false);
        initComponents();
    }
    
    public BackgroundImage(Icon icon, Color ftColor) {
        this.icon = icon;
        this.ftColor = ftColor;
        setOpaque(false);
        initComponents();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 691, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 442, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        if (icon != null) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.drawImage(toImage(icon), 0, 0, width, height, null);
            if(ftColor != null) {
                g2.setPaint(ftColor);
                g2.fillRect(0, 0, width, height);
            }
        }
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }
    
    private Image toImage(Icon icon) {
        return ((ImageIcon) icon).getImage();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
