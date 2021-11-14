package gui.dialog;

import entity.NhanVien;
import gui.GD_Chinh;
import java.awt.Color;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class DL_Progress extends javax.swing.JDialog {

    private boolean show = true;
    private boolean openGD_Chinh = true;
    private final Animator animator;

    public DL_Progress(java.awt.Frame parent, NhanVien nhanVien) {
        super(parent, false);
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
                    if(openGD_Chinh) {
                        new GD_Chinh("Quản lý KaraokeFourH", nhanVien).setVisible(true);
                    } else {
                        getParent().setVisible(true);
                    }
                }
            }
        };
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        button1 = new gui.swing.button.Button();
        button2 = new gui.swing.button.Button();
        pnlProgress = new gui.swing.panel.PanelTransparent();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        bg.setShadowOpacity(0.2F);
        bg.setShadowSize(15);
        bg.setLayout(new java.awt.CardLayout());

        imageAvatar1.setForeground(new java.awt.Color(51, 51, 51));
        imageAvatar1.setBorderSize(3);
        imageAvatar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1200px-Image_created_with_a_mobile_phone.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("Đỗ Huy Hoàng");

        jLabel2.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel2.setText("Admin");

        button1.setText("Hủy");
        button1.setBorderRadius(5);
        button1.setBorderline(true);
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        button2.setText("Tiếp tục");
        button2.setBorderRadius(5);
        button2.setBorderline(true);
        button2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(148, Short.MAX_VALUE))
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(imageAvatar1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(button2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        bg.add(pnlProfile, "card2");

        javax.swing.GroupLayout pnlProgressLayout = new javax.swing.GroupLayout(pnlProgress);
        pnlProgress.setLayout(pnlProgressLayout);
        pnlProgressLayout.setHorizontalGroup(
            pnlProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 458, Short.MAX_VALUE)
        );
        pnlProgressLayout.setVerticalGroup(
            pnlProgressLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 317, Short.MAX_VALUE)
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

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        backGD_DangNhap();
    }//GEN-LAST:event_button1ActionPerformed

    private void button2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button2ActionPerformed
        openGD_Chinh();
    }//GEN-LAST:event_button2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.LayerPaneShadow bg;
    private gui.swing.button.Button button1;
    private gui.swing.button.Button button2;
    private gui.swing.image.ImageAvatar imageAvatar1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private gui.swing.panel.PanelTransparent pnlProfile;
    private gui.swing.panel.PanelTransparent pnlProgress;
    // End of variables declaration//GEN-END:variables
}
