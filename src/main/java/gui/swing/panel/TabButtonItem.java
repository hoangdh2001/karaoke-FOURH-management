/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.swing.panel;

import gui.swing.event.EventTabSelected;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.JButton;
/**
 *
 * @author Admin
 */
public class TabButtonItem extends  JButton {
    private Color lineColor = Color.BLACK;
    private Color borderColor = new Color(0, 0, 0, 0.1f);
    private boolean selectedTab = false;
    private int index;
    private EventTabSelected event;
    
    public Color getLineColor() {
        return lineColor;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public boolean isSelectedTab() {
        return selectedTab;
    }

    public void setSelectedTab(boolean selectedTab) {
        this.selectedTab = selectedTab;
    }

    public EventTabSelected getEvent() {
        return event;
    }

    public void setEvent(EventTabSelected event) {
        this.event = event;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
    
    public TabButtonItem(String text, int index) {
        super(text);
        this.index = index;
        setOpaque(false);
        setContentAreaFilled(false);
        setFocusable(false);
        setBackground(Color.WHITE);
        setFont(new Font("sansserif", Font.BOLD, 14));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                selectedTab = event.selected(index, selectedTab);
                repaint();
                revalidate();
            }
        });
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        BufferedImage img = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        int width = img.getWidth();
        int height = img.getHeight();
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRect(0, 0, width, height);
        if(selectedTab) {
            g2.setColor(lineColor);
            g2.fillRect(0, 0, width, 3);
            g2.setColor(borderColor);
            g2.fillRect(width - 1, 0, 1, height);
            g2.setColor(borderColor);
            g2.fillRect(0, 0, 1, height);
        }
        else {
            g2.setColor(borderColor);
            g2.fillRect(0, height - 1, width, 1);
        }
        g2.dispose();
        g.drawImage(img, 0, 0, null);
        super.paintComponent(g);
    }
}
