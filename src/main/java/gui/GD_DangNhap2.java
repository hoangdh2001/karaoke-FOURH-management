package gui;

import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import gui.component.PanelLogin;
import gui.dialog.Message;
import gui.dialog.Message2;
import gui.swing.image.BackgroundImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.miginfocom.swing.MigLayout;

public class GD_DangNhap2 extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BackgroundImage bg;
    private MigLayout layout;
    private PanelLogin form;

    public GD_DangNhap2(String title) {
        super(title);
        initComponents();
        buidMain();
    }

    private void buidMain() {
        bg = new BackgroundImage(new ImageIcon(getClass().getResource("/icon/background2.jpg")), new Color(0, 0, 0, 0.7f));
        setContentPane(bg);
        setLocationRelativeTo(null);
        layout = new MigLayout("fill", "push[center]push");
        form = new PanelLogin();
        form.login(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                form.showMessage(gui.component.Message.MessageType.SUCCESS, "Test message");
                // Dừng lại 2s để open GD_Chính
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                            GD_DangNhap2.this.dispose();
                            new GD_Chinh("Quản lý Karaoke FourH").setVisible(true);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
        bg.setLayout(layout);
        bg.add(form, "width 90%, height 70%");
    }

    private void initComponents() {

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 1000, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 700, Short.MAX_VALUE)
        );

        pack();
    }
}
