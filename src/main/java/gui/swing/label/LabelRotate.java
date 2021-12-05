package gui.swing.label;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import javax.swing.JLabel;

public class LabelRotate extends JLabel {

    private int rotate = 90;

    public int getRotate() {
        return rotate;
    }

    public void setRotate(int rotate) {
        this.rotate = rotate;
    }
    
    public LabelRotate() {

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        Font font = getFont();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.rotate(Math.toRadians(rotate), 0, 0);
        Font rotatedFont = font.deriveFont(affineTransform);
        g2.setFont(rotatedFont);
        FontMetrics ft = g2.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(getText(), g2);
        
        // vị trí text sẽ hiển thị
        double x2 = (getWidth() - r2.getWidth()) / 2;
        double y2 = (getHeight() - r2.getHeight()) / 2;
        
        g2.setColor(getForeground());
        g2.drawString(getText(), (int) (x2 + 15), (int) (y2 + ft.getAscent()));
        g2.dispose();
        super.paint(g);
    }
}
