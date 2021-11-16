
package gui.dialog;

import entity.NhanVien;
import gui.GD_Chinh;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class DL_ThongTinNhanVien extends javax.swing.JDialog {
    private int xx;
    private int xy;
    private boolean show = true;
    private Animator animator;
    public DL_ThongTinNhanVien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setBackground(new Color(0, 0, 0, 0));
        setOpacity(0);
        setLocationRelativeTo(null);
        buildDL();
    }
    
    private void buildDL() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if(show) {
                    setOpacity(fraction);
                } else {
                    setOpacity(1f - fraction);
                }
            }

            @Override
            public void end() {
                if(!show) {
                    dispose();
                }
            }
        };
        animator = new Animator(100, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                if(!animator.isRunning()) {
                    animator.start();
                }
            }
        });
        NhanVien nhanVien = GD_Chinh.NHAN_VIEN;
        lblCCCD.setText("CCCD: " + nhanVien.getCanCuocCD());
        lblGioiTinh.setText("Giới tính: " + (nhanVien.isGioiTinh() == true ? "Nữ":"Nam"));
        lblNgaySinh.setText("Ngày sinh: " + nhanVien.getNgaySinh());
        lblSdt.setText("Số điện thoại: " + nhanVien.getSoDienThoai());
        lblEmail.setText("Email: " + nhanVien.getEmail());
        lblDiaChi.setText(nhanVien.getDiaChi().toString());
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new gui.swing.panel.PanelShadow();
        avatar = new gui.swing.image.ImageAvatar();
        labelRound1 = new gui.swing.label.LabelRound();
        header = new gui.swing.panel.PanelShadow();
        button1 = new gui.swing.button.Button();
        lblName = new javax.swing.JLabel();
        btnDoiMatKhau = new gui.swing.button.Button();
        lblGioiTinh = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblSdt = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblDiaChi = new javax.swing.JLabel();
        btnXemLich = new gui.swing.button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModal(true);
        setName("Thông tin nhân viên"); // NOI18N
        setUndecorated(true);
        setResizable(false);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(7, 7, 7, 7));
        bg.setShadowOpacity(0.3F);
        bg.setShadowSize(10);

        avatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar.png"))); // NOI18N

        labelRound1.setBackground(new java.awt.Color(51, 204, 0));
        labelRound1.setBorderRadius(30);
        avatar.add(labelRound1);
        labelRound1.setBounds(190, 190, 30, 30);

        header.setBackground(new java.awt.Color(22, 27, 34));
        header.setBorder(javax.swing.BorderFactory.createEmptyBorder(3, 3, 3, 3));
        header.setShadowOpacity(0.3F);
        header.setShadowSize(3);
        header.setShadowType(gui.swing.graphics.ShadowType.BOT);
        header.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                headerMouseDragged(evt);
            }
        });
        header.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                getPoint(evt);
            }
        });

        button1.setBackground(new java.awt.Color(22, 27, 34));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/left_30px.png"))); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clossDialog(evt);
            }
        });

        javax.swing.GroupLayout headerLayout = new javax.swing.GroupLayout(header);
        header.setLayout(headerLayout);
        headerLayout.setHorizontalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        headerLayout.setVerticalGroup(
            headerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerLayout.createSequentialGroup()
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        lblName.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblName.setText("Đỗ Huy Hoàng");

        btnDoiMatKhau.setBackground(new java.awt.Color(22, 27, 34));
        btnDoiMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiMatKhau.setText("Đổi mật khẩu");
        btnDoiMatKhau.setBorderline(true);
        btnDoiMatKhau.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblGioiTinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblGioiTinh.setText("Giới tính: Nam");

        lblCCCD.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCCCD.setText("CCCD:");

        lblSdt.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblSdt.setText("Số điện thoại: 0368232674");

        lblNgaySinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblNgaySinh.setText("Ngày sinh: 14/09/2001");

        lblEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblEmail.setText("Email: huyhoang14901@gmail.com");

        lblDiaChi.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDiaChi.setText("Số nhà:");

        btnXemLich.setBackground(new java.awt.Color(22, 27, 34));
        btnXemLich.setForeground(new java.awt.Color(255, 255, 255));
        btnXemLich.setText("Xem lịch làm việc");
        btnXemLich.setBorderline(true);
        btnXemLich.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(header, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnXemLich, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(106, 106, 106)
                                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(bgLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCCCD)
                    .addComponent(lblSdt)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(lblGioiTinh)
                        .addGap(41, 41, 41)
                        .addComponent(lblNgaySinh))
                    .addComponent(lblEmail)
                    .addComponent(lblDiaChi))
                .addGap(99, 99, 99))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(header, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54)
                        .addComponent(avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(lblCCCD)
                        .addGap(13, 13, 13)
                        .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblGioiTinh)
                            .addComponent(lblNgaySinh))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSdt)
                        .addGap(14, 14, 14)
                        .addComponent(lblEmail)
                        .addGap(16, 16, 16)
                        .addComponent(lblDiaChi)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDoiMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnXemLich, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(82, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clossDialog(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clossDialog
       if(animator.isRunning()) {
           animator.stop();
       }
       show = false;
       animator.start();
       
    }//GEN-LAST:event_clossDialog

    private void getPoint(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_getPoint
        xx = evt.getX();
        xy = evt.getY();
    }//GEN-LAST:event_getPoint

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_headerMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        setLocation(x - xx, y - xy);
    }//GEN-LAST:event_headerMouseDragged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.image.ImageAvatar avatar;
    private gui.swing.panel.PanelShadow bg;
    private gui.swing.button.Button btnDoiMatKhau;
    private gui.swing.button.Button btnXemLich;
    private gui.swing.button.Button button1;
    private gui.swing.panel.PanelShadow header;
    private gui.swing.label.LabelRound labelRound1;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSdt;
    // End of variables declaration//GEN-END:variables
}
