/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.swing.textfield;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Admin
 */
public class TextFieldAnimation extends JTextField {
    private Color backgroundColor = Color.WHITE;
    private String hintText = "";

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }
    
    public String getHintText() {
        return hintText;
    }

    public void setHintText(String hintText) {
        this.hintText = hintText;
    }
    
    public TextFieldAnimation() {
        setBackground(new Color(255, 255, 255, 0)); 
        setOpaque(false);
        setBorder(new EmptyBorder(10, 10, 10, 50));
        setSelectionColor(new Color(80, 199, 255));
        setFont(new Font("sansserif", Font.PLAIN, 12));
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); // Làm mượt dòng
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR); // làm mượt hình ảnh
        g2.setColor(backgroundColor);
        g2.fillRoundRect(0, 0, width, height, height, height);
        super.paintComponent(g);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        if(getText().length()  == 0) {
            int height = getHeight();
            ((Graphics2D)g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Insets insets = getInsets();
            FontMetrics fm = g.getFontMetrics();
            int color1 = getBackground().getRGB();
            int color2 = getForeground().getRGB();
            int m = 0xfefefefe;
            int color3 = ((color1 & m) >>> 1) + ((color2 & m) >>> 1);
            g.setColor(new Color(color3, true));
            g.drawString(hintText, insets.left, height / 2 + fm.getAscent() / 2 - 2);
        }
    }
    
}
