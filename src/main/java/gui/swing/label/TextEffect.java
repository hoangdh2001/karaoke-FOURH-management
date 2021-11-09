package gui.swing.label;

import gui.dropshadow.ShadowRenderer;
import gui.dropshadow.ShadowType;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JLabel;

public class TextEffect extends JLabel {
    private int shadowSize = 5;
    private float shadowOpacity = 0.5f;
    private Color shadowColor = Color.BLACK;
    private ShadowType shadowType = ShadowType.CENTER;

    public int getShadowSize() {
        return shadowSize;
    }

    public void setShadowSize(int shadowSize) {
        this.shadowSize = shadowSize;
    }

    public float getShadowOpacity() {
        return shadowOpacity;
    }

    public void setShadowOpacity(float shadowOpacity) {
        this.shadowOpacity = shadowOpacity;
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }
    
    public TextEffect() {
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int width = getWidth();
        int height = getHeight();
        int size = shadowSize * 2;
        int x = 0;
        int y = 0;
        if (shadowType == ShadowType.TOP) {
            x = shadowSize;
            y = size;
        } else if (shadowType == ShadowType.BOT) {
            x = shadowSize;
            y = 0;
        } else if (shadowType == ShadowType.TOP_LEFT) {
            x = size;
            y = size;
        } else if (shadowType == ShadowType.TOP_RIGHT) {
            x = 0;
            y = size;
        } else if (shadowType == ShadowType.BOT_LEFT) {
            x = size;
            y = 0;
        } else if (shadowType == ShadowType.BOT_RIGHT) {
            x = 0;
            y = 0;
        } else {
            //  Center
            x = shadowSize;
            y = shadowSize;
        }
        
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g.setFont(getFont());
        // lấy font
        FontMetrics ft = g.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(getText(), g);
        
        // vị trí text sẽ hiển thị
        double x2 = (width - r2.getWidth()) / 2;
        double y2 = (height - r2.getHeight()) / 2;
        g.setColor(getForeground());
        g.drawString(getText(), (int) x2, (int) (y2 + ft.getAscent()));
        // bóng text
        ShadowRenderer renderer = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
        g2.drawImage(renderer.createShadow(img), 0, 0, null);
        g2.drawImage(img, x, y, null);
    }
}
