package gui;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Desktop;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Application {

    public static GD_DangNhap login;

    public static GD_DangNhap getLogin() {
        return login;
    }

    public static void setLogin(GD_DangNhap login) {
        Application.login = login;
    }

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean openWebpage(File file) {
        return openWebpage(file.toURI());
    }

    public Application() {
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            java.util.logging.Logger.getLogger(Application.class.getName()).log(java.util.logging.Level.SEVERE, null, e);
        }
        SwingUtilities.invokeLater(() -> {
            login = new GD_DangNhap("Đăng nhập");
            login.setVisible(true);
        });

    }
}
