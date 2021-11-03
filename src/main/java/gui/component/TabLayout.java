package gui.component;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import net.miginfocom.swing.MigLayout;

public class TabLayout extends javax.swing.JPanel {
    
    private boolean show;
    private float alpha;

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public void addAction(ActionListener evt) {
        tab.addAction(evt);
    }

    public TabLayout() {
        initComponents();
        setLayout(new MigLayout("fill, insets0"));
        add(tab, "pos 1al 1al n n, w 30%, h 90%");
        setOpaque(false);
        setVisible(false);
        tab.addMouseListener(new MouseAdapter() {
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tab = new gui.component.TabHidden();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 661, Short.MAX_VALUE)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, 400, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(new Color(150, 150, 150));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        super.paintComponent(grphcs);
    }
    
    public void showDetail(Component com) {
        if(tab.getComponentCount() >= 2) {
            tab.remove(1);
        }
        tab.add(com, "w 100%, h 100%");
        tab.repaint();
        tab.revalidate();
    }

    public TabHidden getTabHidden() {
        return tab;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.component.TabHidden tab;
    // End of variables declaration//GEN-END:variables
}
