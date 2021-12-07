package gui.swing.textfield;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import javax.swing.JTextField;

public class MyTextFieldPerUnit extends JTextField {
    private String unit;

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public MyTextFieldPerUnit() {
//        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 13));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 5, 5);
        super.paintComponent(g);
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if (unit != null) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            g.setFont(getFont());
            FontMetrics fm = g.getFontMetrics();
            Rectangle2D r2 = fm.getStringBounds(unit, g);
            int y = (int) r2.getHeight();
            g.setColor(getForeground());
            g.drawString(unit, getWidth() - ((int) r2.getWidth()) - 10, y);
        }
    }
}
