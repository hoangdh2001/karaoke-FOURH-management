package gui.swing.textfield;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.AbstractButton;
import javax.swing.ComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;

public class MyComboBox<E> extends JComboBox {

    private Color backgroundColor = new Color(230, 245, 241);
    private boolean borderLine = false;
    private Color borderColor = new Color(0, 0, 0, 0.3f);
    private int borderRadius = 0;
    private final Icon arrowIcon = new ImageIcon(getClass().getResource("/icon/sort_15px.png"));

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public boolean isBorderLine() {
        return borderLine;
    }

    public void setBorderLine(boolean borderLine) {
        this.borderLine = borderLine;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderRadius() {
        return borderRadius;
    }

    public void setBorderRadius(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    public MyComboBox(ComboBoxModel aModel) {
        super(aModel);
        buildMyComboBox();
    }

    public MyComboBox(Object[] items) {
        super(items);
        buildMyComboBox();
    }
    
    public MyComboBox() {
        buildMyComboBox();
    }
    
    private void buildMyComboBox() {
        setBackground(new Color(0, 0, 0, 0));
        setForeground(Color.decode("#7A8C8D"));
        setFont(new java.awt.Font("sansserif", 0, 13));

        for (Component component : this.getComponents()) {
            if (component instanceof AbstractButton) {
                if (component.isVisible()) {
                    component.setVisible(false);
                    this.revalidate();
                    this.repaint();
                }
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        GradientPaint gra = new GradientPaint(0, getHeight() / 3, Color.WHITE, 0, getHeight(), new Color(225, 225, 225));
        if (borderLine) {
            g2.setPaint(gra);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
            g2.setColor(borderColor);
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, borderRadius, borderRadius);
        } else {
            g2.setPaint(gra);
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), borderRadius, borderRadius);
        }
        paintIcon(g);
        super.paintComponent(g);
    }

    private void paintIcon(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Image icon = ((ImageIcon) arrowIcon).getImage();
        int y = (getHeight() - arrowIcon.getIconHeight()) / 2;
        g2.drawImage(icon, getWidth() - arrowIcon.getIconWidth() - 10, y, this);
    }
}
