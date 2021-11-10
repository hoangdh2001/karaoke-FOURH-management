package gui;

import entity.NhanVien;
import gui.component.Message;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

import gui.component.PanelForm;
import gui.event.EventOnClick;
import gui.swing.image.BackgroundImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.miginfocom.swing.MigLayout;

public class GD_DangNhap extends JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BackgroundImage bg;
    private MigLayout layout;
    private PanelForm form;

    public GD_DangNhap(String title) {
        super(title);
        initComponents();
        buidGD_DangNhap();
    }
    /**
     * Xây dựng giao diện đăng nhập
     */
    private void buidGD_DangNhap() {
        bg = new BackgroundImage(new ImageIcon(getClass().getResource("/icon/background2.jpg")), new Color(0, 0, 0, 0.7f));
        setContentPane(bg);
        setLocationRelativeTo(null);
        layout = new MigLayout("fill", "push[center]push"); // layout hiện thị các thành phần ở giữa
        form = new PanelForm();
        form.login(new EventOnClick() {
            @Override
            public void onClick(Object object) {
                NhanVien nhanVien = (NhanVien) object;
                if (nhanVien != null) {
                    GD_DangNhap.this.dispose();
                    new GD_Chinh("Quản lý Karaoke FourH", nhanVien).setVisible(true);
                } else {
                    form.showMessage(Message.MessageType.ERROR, "Sai mật khẩu");
                }
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
