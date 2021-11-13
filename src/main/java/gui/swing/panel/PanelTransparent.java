package gui.swing.panel;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

public class PanelTransparent extends JPanel {
    
    private float alpha = 1f;

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public PanelTransparent() {
        setOpaque(false);
        setFocusCycleRoot(true);
        setFocusable(true);
        addMouseListener(new MouseAdapter() {
        });
    }

    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(getBackground());
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2.fillRect(0, 0, getWidth(), getHeight());
        g2.setComposite(AlphaComposite.SrcOver);
        super.paint(grphcs);
    }
}
