package gui;

import entity.NhanVien;
import gui.swing.image.WindowIcon;
import java.awt.Color;
import java.awt.Frame;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class GD_Info extends javax.swing.JFrame {

    private boolean show = true;
    private boolean openGD_Chinh = true;
    private final Animator animator;

    public GD_Info(NhanVien nhanVien) {
        initComponents();
        WindowIcon.addWindowIcon(this);
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
                    if(openGD_Chinh) {
                        new GD_Chinh((Frame) getParent(), "Quản lý KaraokeFourH", nhanVien).setVisible(true);
                    } else {
                        getParent().setVisible(true);
                    }
                }
            }
        };
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        
        lblName.setText(nhanVien.getTenNhanVien());
        lblRole.setText(nhanVien.getLoaiNhanVien().getTenLoaiNV());
    }
    
    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        show = b;
        if(show) {
            animator.start();
        }
    }
    
    private void backGD_DangNhap() {
        if (animator.isRunning())
            animator.stop();
        show = false;
        openGD_Chinh = false;
        animator.start();
    }
    
    private void openGD_Chinh() {
        if(animator.isRunning())
            animator.stop();
        show = false;
        openGD_Chinh = true;
        animator.start();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new gui.swing.panel.LayerPaneShadow();
        pnlProfile = new gui.swing.panel.PanelTransparent();
        imageAvatar1 = new gui.swing.image.ImageAvatar();
        lblName = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        btnCancel = new gui.swing.button.Button();
        btnContinue = new gui.swing.button.Button();
        pnlProgress = new gui.swing.panel.PanelTransparent();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        bg.setShadowOpacity(0.2F);
        bg.setShadowSize(15);
        bg.setLayout(new java.awt.CardLayout());

        pnlProfile.setBackground(new java.awt.Color(255, 255, 255));

        imageAvatar1.setForeground(new java.awt.Color(51, 51, 51));
        imageAvatar1.setBorderSize(3);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1200px-Image_created_with_a_mobile_phone.png"))); // NOI18N

        lblName.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblName.setText("Đỗ Huy Hoàng");

        lblRole.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblRole.setText("Admin");

        btnCancel.setText("Hủy");
        btnCancel.setBorderRadius(5);
        btnCancel.setBorderline(true);
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });

        btnContinue.setText("Tiếp tục");
        btnContinue.setBorderRadius(5);
        btnContinue.setBorderline(true);
        btnContinue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContinueActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lblName)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole))
                .addContainerGap(169, Short.MAX_VALUE))
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRole)
                .addGap(18, 18, 18)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        bg.add(pnlProfile, "card2");

        pnlProgress.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlProgressLayout = new javax.swing.GroupLayout(pnlProgress);
        pnlProgress.setLayout(pnlProgressLayout);
        pnlProgressLayout.setHorizontalGroup(
            pnlProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 479, Short.MAX_VALUE)
        );
        pnlProgressLayout.setVerticalGroup(
            pnlProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 320, Short.MAX_VALUE)
        );

        bg.add(pnlProgress, "card3");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        backGD_DangNhap();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void btnContinueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContinueActionPerformed
        openGD_Chinh();
    }//GEN-LAST:event_btnContinueActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.LayerPaneShadow bg;
    private gui.swing.button.Button btnCancel;
    private gui.swing.button.Button btnContinue;
    private gui.swing.image.ImageAvatar imageAvatar1;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRole;
    private gui.swing.panel.PanelTransparent pnlProfile;
    private gui.swing.panel.PanelTransparent pnlProgress;
    // End of variables declaration//GEN-END:variables
}
