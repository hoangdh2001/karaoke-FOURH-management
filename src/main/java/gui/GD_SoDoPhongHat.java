package gui;


import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import net.miginfocom.swing.MigLayout;

public class GD_SoDoPhongHat extends JPanel {
    private JPanel paneForm;
    private JTabbedPane tab;
    private MigLayout layout;
    public GD_SoDoPhongHat() {
        buid_SoDoPhongHat();
    }
    
    private void buid_SoDoPhongHat() {
        setLayout(layout = new MigLayout("fillx, debug", "[100%]", "[][]"));
        paneForm = new JPanel();
        paneForm.setBackground(Color.WHITE);
        add(paneForm, "h 30%, w 100%, wrap");
        tab = new JTabbedPane();
        tab.setBackground(Color.WHITE);
        add(tab, "h 70%, w 100%");
    }
}
