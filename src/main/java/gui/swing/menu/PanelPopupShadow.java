package gui.swing.menu;

import gui.dropshadow.ShadowRenderer;
import gui.dropshadow.ShadowType;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class PanelPopupShadow extends JPanel {

    private PopupType type = PopupType.LEFT;
    private boolean shadow = false;
    private ShadowType shadowType = ShadowType.CENTER;
    private int shadowSize = 6;
    private float shadowOpacity = 0.5f;
    private Color shadowColor = Color.BLACK;
    
    public PopupType getType() {
        return type;
    }
    
    public void setType(PopupType type)  {
        this.type = type;
    }

    public boolean isShadow() {
        return shadow;
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public ShadowType getShadowType() {
        return shadowType;
    }

    public void setShadowType(ShadowType shadowType) {
        this.shadowType = shadowType;
    }

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
    
    public PanelPopupShadow() {
        setOpaque(false);
    }

    public PanelPopupShadow(PopupType type) {
        this.type = type;
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int size = shadowSize * 2;
        int m = 0;
        int n = 0;
        int width = getWidth() - size;
        int height = getHeight() - size;
        if (shadowType == ShadowType.TOP) {
            m = shadowSize;
            n = size;
        } else if (shadowType == ShadowType.BOT) {
            m = shadowSize;
            n = 0;
        } else if (shadowType == ShadowType.TOP_LEFT) {
            m = size;
            n = size;
        } else if (shadowType == ShadowType.TOP_RIGHT) {
            m = 0;
            n = size;
        } else if (shadowType == ShadowType.BOT_LEFT) {
            m = size;
            n = 0;
        } else if (shadowType == ShadowType.BOT_RIGHT) {
            m = 0;
            n = 0;
        } else {
            //  Center
            m = shadowSize;
            n = shadowSize;
        }
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setColor(getBackground());
        if (type == PopupType.LEFT) {
            g2d.fillRect(8, 0, getSize().width - 8, getSize().height);
            int x[] = {0, 10, 10};
            int y[] = {20, 13, 27};
            g2d.fillPolygon(x, y, x.length);
        } else if (type == PopupType.TOP) {
            g2d.fillRect(0, 8, getSize().width, getSize().height - 8);
            int x[] = {80, 70, 90};
            int y[] = {0, 10, 10};
            g2d.fillPolygon(x, y, x.length);
        }
        ShadowRenderer render = new ShadowRenderer(shadowSize, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(img), 0, 0, null);
        g2.drawImage(img, m, n, null);
        super.paintComponent(grphcs);
    }

    public static enum PopupType {
        TOP,
        LEFT
    }
}
