package gui.swing.textfield;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import javax.swing.JTextField;

public class MyTextFieldFlatlaf extends JTextField {
    private String hint;

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }
    
    public MyTextFieldFlatlaf() {
        
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        if(getText().length() == 0) {
            int h = getHeight();
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Insets ins = getInsets();
            FontMetrics fm = g.getFontMetrics();
            g.setColor(new Color(200, 200, 200));
            g.drawString(hint, ins.left, h / 2 + fm.getAscent() / 2 - 2);
        }
    }
}
