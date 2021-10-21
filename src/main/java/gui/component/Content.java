package gui.component;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Content extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public Content() {
        setOpaque(false);
        setLayout(new MigLayout("fill", "0[fill]0", "[fill]"));
        setBorder(new EmptyBorder(10, 20, 10, 20));
    }

    public void showForm(Component form) {
        removeAll();
        add(form);
        repaint();
        revalidate();
    }
}
