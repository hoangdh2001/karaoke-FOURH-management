package gui.swing.button;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

public class LinkBtn extends JButton {
    
    private Color overColor = new Color(31, 111, 235);
    private Color textColor = new Color(0, 0, 0);

    public Color getOverColor() {
        return overColor;
    }

    public void setOverColor(Color overColor) {
        this.overColor = overColor;
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        overColor = fg;
    }
    
    public LinkBtn() {
        setOpaque(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setFont(new Font("sansserif", Font.BOLD, 12));
    }
    
    public LinkBtn(String text) {
        super(text);
        setOpaque(false);
        setContentAreaFilled(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setBorder(new EmptyBorder(2, 10, 2, 10));
        setFont(new Font("sansserif", Font.BOLD, 12));
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                textColor = overColor;
                repaint();
                revalidate();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                textColor = getForeground();
                repaint();
                revalidate();
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(getFont());
        
        FontMetrics ft = g.getFontMetrics();
        Rectangle2D r2 = ft.getStringBounds(getText(), g);
        
        double x2 = (width - r2.getWidth()) / 2;
        double y2 = (height - r2.getHeight()) / 2;
        
        g2.setColor(textColor);
        g2.fillRect((int) (x2), (int) (y2 + ft.getAscent() + 3),(int) r2.getWidth(), 1);
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
    }
}
