/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.swing.panel.slideshow;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

/**
 *
 * @author Admin
 */
public class ButtonSlide extends JButton {

    private boolean hover = false;
    private Color in = new Color(0, 0, 0, 0.3f);
    private Color out = new Color(0, 0, 0, 0);
    private boolean right = true;

    public Color getIn() {
        return in;
    }

    public void setIn(Color in) {
        this.in = in;
    }

    public Color getOut() {
        return out;
    }

    public void setOut(Color out) {
        this.out = out;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public ButtonSlide() {
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = !hover;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = !hover;
            }

        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = null;
        if (hover) {
            if (right) {
                gra = new GradientPaint(getWidth(), 0, in, 0, 0, out);
            } else {
                gra = new GradientPaint(0, 0, in, getWidth(), 0, out);
            }
            g2.setPaint(gra);
        }
        else {
            g2.setColor(new Color(0, 0, 0, 0));
        }
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }
}
