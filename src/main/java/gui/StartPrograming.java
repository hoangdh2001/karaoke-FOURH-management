package gui;

import java.awt.EventQueue;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class StartPrograming {
    
    public static void main(String[] args) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StartPrograming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StartPrograming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StartPrograming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartPrograming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
//        try {
//            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
//                | UnsupportedLookAndFeelException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        EventQueue.invokeLater(() -> {

//            new GD_DangNhap("Đăng nhập").setVisible(true);
            new GD_Chinh("Quản lý karaoke fourh").setVisible(true);
//            new GD_DangNhap("Đăng nhập").setVisible(true);

            new GD_TiepNhanDatPhong().setVisible(true);
//              new GD_LapHoaDon().setVisible(true);
        });
    }
}
