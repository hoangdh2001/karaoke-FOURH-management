package gui.swing.button;

import gui.swing.panel.PanelShadow;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class PopupMenu extends JPopupMenu {

    private PanelShadow pnlCenter;

    public PopupMenu() {
        setOpaque(false);
        buildDisplay();
        setBorder(new EmptyBorder(0, 0, 0, 0));
    }

    private void buildDisplay() {
        createPanelCenter();
    }

    private void createPanelCenter() {
        pnlCenter = new PanelShadow();
        pnlCenter.setShadowSize(5);
        pnlCenter.setShadowOpacity(0.2f);

        pnlCenter.setLayout(new MigLayout("wrap, fillx, insets 15", "[fill]", "[]0[]"));
        add(pnlCenter);
    }

    public void addItem(Component com) {
        pnlCenter.add(com, "h 40!");
    }

    public void addSeperator() {
        JSeparator e = new JSeparator();
        pnlCenter.add(e);
    }

    public int getItemCount() {
        return pnlCenter.getComponentCount();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        pnlCenter.setBackground(getBackground());
        g2.setColor(new Color(0, 0, 0, 0));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
    }
}
