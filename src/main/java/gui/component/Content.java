package gui.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Content extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Content() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(10, 20, 10, 20));
        setBackground(new Color(236, 240, 245));
    }

    public void showForm(Component form) {
        removeAll();
        add(form);
        repaint();
        revalidate();
    }
}
