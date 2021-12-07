package gui.swing.image;

import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Image;
import java.net.URL;

public class WindowIcon {

    public static void addWindowIcon(Frame window) {
        URL url = WindowIcon.class.getResource("/icon/logo.png");
        if (url != null) {
            Image image = java.awt.Toolkit.getDefaultToolkit().getImage(url);
            if (image != null) {
                window.setIconImage(image);
            }
        }
    }

    public static void addWindowIcon(Dialog window) {
        URL url = WindowIcon.class.getResource("/icon/logo.png");
        if (url != null) {
            Image image = java.awt.Toolkit.getDefaultToolkit().getImage(url);
            if (image != null) {
                window.setIconImage(image);
            }
        }
    }
}
