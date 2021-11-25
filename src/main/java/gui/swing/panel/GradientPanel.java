package gui.swing.panel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.GradientPaint;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.BorderLayout;
import javax.swing.JPanel;

class GradientPanel extends JPanel {
    GradientPanel() {
        super(new BorderLayout());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Rectangle clip = g2.getClipBounds();
        Paint paint = g2.getPaint();

        g2.setPaint(new GradientPaint(0.0f, getHeight() * 0.22f, new Color(0x202737),
                                      0.0f, getHeight() * 0.9f, Color.BLACK));
        g2.fillRect(clip.x, clip.y, clip.width, clip.height);

        g2.setPaint(paint);
    }
}

