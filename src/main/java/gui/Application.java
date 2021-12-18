package gui;

import com.formdev.flatlaf.FlatLightLaf;
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
