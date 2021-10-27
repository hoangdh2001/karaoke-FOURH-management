package gui.swing.table2;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;

public class LabelStatus extends JLabel {
    private Color colorStatus = new Color(253, 187, 65);

    public Color getColorStatus() {
        return colorStatus;
    }

    public void setColorStatus(Color colorStatus) {
        this.colorStatus = colorStatus;
    }
    
    public LabelStatus() {
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setHorizontalAlignment(JLabel.CENTER);
    }
    
    public LabelStatus(String text, Color colorStatus) {
        super(text);
        this.colorStatus = colorStatus;
        setForeground(Color.WHITE);
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setHorizontalAlignment(JLabel.CENTER);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(colorStatus);
        int x[] = {5, getWidth(), getWidth() - 5, 0};
        int y[] = {0, 0, getHeight(), getHeight()};
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f));
        g2.fillPolygon(x, y, x.length);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        super.paintComponent(grphcs);
    }
}
