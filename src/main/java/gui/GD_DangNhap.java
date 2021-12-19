package gui;

import dao.NhanVien_DAO;
import entity.NhanVien;
import gui.component.Message;
import gui.swing.image.WindowIcon;
import gui.swing.event.EventLogin;
import java.awt.Color;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import java.awt.Component;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import service.NhanVienService;

public class GD_DangNhap extends javax.swing.JFrame {

    private boolean show = true;
    private boolean close = true;
    private final Animator animator;
    private NhanVien nhanVien;
    private NhanVienService nhanVienService;
    private Thread thread;

    public GD_DangNhap(String title) {
        super(title);
        WindowIcon.addWindowIcon(this);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setOpacity(0);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (show) {
                    setOpacity(fraction);
                } else {
                    setOpacity(1f - fraction);
                }
            }

            @Override
            public void end() {
                if (!show) {
                    dispose();
                    if (!close) {
                        new GD_Chinh(GD_DangNhap.this, "Quản lý Karaoke FourH", nhanVien).setVisible(true);
                    }
                }
            }
        };
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);

        pnlLoading.setVisible(true);
        pnlForm.setVisible(false);

        thread = new Thread(() -> {
            nhanVienService = new NhanVien_DAO();
            if (nhanVienService.checkConnect()) {
                hiddenLoading();
            } else {
                close();
                System.out.println("Not connect");
            }
        });
        
        thread.start();

        pnlForm.addEventLogin(new EventLogin() {
            @Override
            public void login(String sdt, byte[] matKhau) {
                if (sdt.equals("")) {
                    pnlForm.setTextWhenBack();
                    pnlForm.showMessage(Message.MessageType.ERROR, "Nhập tên tài khoản");
                    return;
                }
                if (matKhau.length <= 0) {
                    pnlForm.setTextWhenBack();
                    pnlForm.showMessage(Message.MessageType.ERROR, "Nhập mật khẩu");
                    return;
                }
                pnlLoading.setAlpha(0.5f);
                pnlLoading.setVisible(true);
                thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        nhanVien = nhanVienService.getNhanVienByLogin(sdt, matKhau);
                        if (nhanVien != null) {
                            pnlLoading.setVisible(false);
                            showDLProgress();
                        } else {
                            pnlLoading.setVisible(false);
                            pnlForm.setTextWhenBack();
                            pnlForm.showMessage(Message.MessageType.ERROR, "Tên tài khoản hoặc mật khẩu bạn đã nhập không chính xác");
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GD_DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                
                thread.start();
            }

            @Override
            public void searchUser(String sdtOrEmail, Component comShow, Component comHidden) {
                pnlLoading.setAlpha(0.5f);
                pnlLoading.setVisible(true);
                thread = new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        nhanVien = nhanVienService.getNhanVienBySdtOrEmail(sdtOrEmail);
                        if (nhanVien != null) {
                            pnlLoading.setVisible(false);
                            comShow.setVisible(true);
                            comHidden.setVisible(false);
                        } else {
                            pnlLoading.setVisible(false);
                            pnlForm.showMessage(Message.MessageType.ERROR, "Số điện thoại hoặc email không tồn tại!");
                        }
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GD_DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                    }
                });
                thread.start();
            }

            @Override
            public void forgotPass(byte[] pass, byte[] rePass) {
                if(!(pass.length > 0)) {
                    pnlForm.showMessage(Message.MessageType.ERROR, "Nhập mật khẩu mới!");
                    return;
                } else {
                    if(!Arrays.equals(pass, rePass)) {
                        pnlForm.showMessage(Message.MessageType.ERROR, "Mật khẩu nhập lại không trùng!");
                        return;
                    }
                }
                pnlLoading.setAlpha(0.5f);
                pnlLoading.setVisible(true);
                new Thread(() -> {
                    try {
                        Thread.sleep(2000);
                        nhanVien.setMatKhau(rePass);
                        if (nhanVienService.updateNhanVien(nhanVien)) {
                            pnlForm.showMessage(Message.MessageType.SUCCESS, "Đổi mật khẩu thành công");
                            pnlForm.display();
                        } else {
                            pnlForm.showMessage(Message.MessageType.ERROR, "Số điện thoại hoặc email không đúng");
                        }
                        pnlLoading.setVisible(false);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GD_DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception ex) {
                        Logger.getLogger(GD_DangNhap.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }).start();
            }
        });
    }
    
    private void hiddenLoading() {
//        pnlLoading.setVisible(false);
//        pnlForm.setVisible(true);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                pnlForm.setVisible(true);
            }

            @Override
            public void timingEvent(float fraction) {
                pnlForm.setAlpha(fraction);
                pnlLoading.setAlpha(1f - fraction);
                repaint();
            }

            @Override
            public void end() {
                pnlLoading.setVisible(false);
            }
        };
        Animator animator2 = new Animator(200, target);
        animator2.setResolution(0);
        animator2.setAcceleration(0.5f);
        animator2.start();
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        show = b;
        if (show) {
            pnlForm.setTextWhenBack();
            animator.start();
        }
    }

    private void close() {
        if (animator.isRunning()) {
            animator.stop();
        }
        show = false;
        close = true;
        animator.start();
    }

    private void showDLProgress() {
        if (animator.isRunning()) {
            animator.stop();
        }
        show = false;
        close = false;
        animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new gui.swing.panel.LayerPaneShadow();
        pnlLoading = new gui.component.PanelLoading();
        pnlForm = new gui.component.PanelForm();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        bg.setShadowOpacity(0.2F);
        bg.setShadowSize(15);
        bg.setLayout(new java.awt.CardLayout());
        bg.add(pnlLoading, "card3");
        bg.add(pnlForm, "card2");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.LayerPaneShadow bg;
    private gui.component.PanelForm pnlForm;
    private gui.component.PanelLoading pnlLoading;
    // End of variables declaration//GEN-END:variables
}
