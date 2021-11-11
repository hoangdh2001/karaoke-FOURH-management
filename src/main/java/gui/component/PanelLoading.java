package gui.component;

import entity.NhanVien;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.geom.Path2D;
import javax.swing.ImageIcon;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class PanelLoading extends javax.swing.JPanel {

    private final Animator animator;
    private boolean slideLeft;
    private float animate;
    private boolean isMessage;
    private NhanVien nhanVien;

    public PanelLoading() {
        initComponents();
        pnlLoading.setVisible(true);
        pnlProfile.setVisible(false);
        pnlMessage.setVisible(false);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if (isMessage) {
                    pnlMessage.setVisible(true);
                } else {
                    pnlProfile.setVisible(true);
                }
            }

            @Override
            public void timingEvent(float fraction) {
                if (isMessage) {
                    pnlMessage.setAlpha(fraction);
                    pnlLoading.setAlpha(1f - fraction);
                } else {
                    pnlProfile.setAlpha(fraction);
                    pnlLoading.setAlpha(1f - fraction);
                }
                repaint();
            }
        };
        animator = new Animator(500, target);
        animator.setResolution(0);
    }
    
    public void setAnimate(boolean slideLeft, float animate) {
        this.slideLeft = slideLeft;
        this.animate = animate;
    }

    public void addEventBack(ActionListener event) {
        btnCancel.addActionListener(event);
        btnCancel2.addActionListener(event);
        btnCancel3.addActionListener(event);
    }

    public void addEventContinue(ActionListener event) {
        btnContinue.addActionListener(event);
    }

    public void doneLogin(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        if(nhanVien.isGioiTinh()) {
            pic.setIcon(new ImageIcon(getClass().getResource("/icon/avatar.png")));
        }
        btnContinue.setText("Tiếp tục với " + nhanVien.getTenNhanVien());
        animator.start();
    }
    
    public void showError(String ms) {
        lbMessage.setText(ms);
        isMessage = true;
        animator.start();
    }

    public void reset() {
        pnlLoading.setAlpha(1f);
        pnlLoading.setVisible(true);
        pnlProfile.setVisible(false);
        pnlMessage.setVisible(false);
    }
    
    @Override
    public void paint(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        int width = getWidth();
        int height = getHeight();
        int x = 0;
        int y = 0;
        if (slideLeft) {
            int centerY = height / 2;
            Path2D.Float p = new Path2D.Float();
            p.moveTo(x, y);
            p.lineTo(width, y);
            p.lineTo(width, height);
            p.lineTo(x, height);
            p.curveTo(x, height, easeOutBounce(animate) * width, centerY, x, y);
            g2.fill(p);
        } else {
            g2.fillRect(x, y, width, height);
        }
        g2.dispose();
        super.paint(grphcs);
    }

    private float easeOutBounce(float x) {
        float n1 = 7.5625f;
        float d1 = 2.75f;
        double v;
        if (x < 1 / d1) {
            v = n1 * x * x;
        } else if (x < 2 / d1) {
            v = n1 * (x -= 1.5 / d1) * x + 0.75;
        } else if (x < 2.5 / d1) {
            v = n1 * (x -= 2.25 / d1) * x + 0.9375;
        } else {
            v = n1 * (x -= 2.625 / d1) * x + 0.984375;
        }
        if (slideLeft) {
            return (float) (1f - v);
        } else {
            return (float) v;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlProfile = new gui.swing.panel.PanelTransparent();
        btnCancel = new gui.swing.button.Button();
        lblRole = new javax.swing.JLabel();
        lblName = new javax.swing.JLabel();
        pic = new gui.swing.image.ImageAvatar();
        btnContinue = new gui.swing.button.Button();
        pnlLoading = new gui.swing.panel.PanelTransparent();
        gifLoad = new gui.swing.image.ImageAvatar();
        btnCancel2 = new gui.swing.button.Button();
        pnlMessage = new gui.swing.panel.PanelTransparent();
        lbMessage = new javax.swing.JLabel();
        btnCancel3 = new gui.swing.button.Button();

        setLayout(new java.awt.CardLayout());

        btnCancel.setText("Hủy");
        btnCancel.setBorderRadius(5);
        btnCancel.setBorderline(true);
        btnCancel.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblRole.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblRole.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblRole.setText("Admin");

        lblName.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblName.setText("Đỗ Huy Hoàng");

        pic.setForeground(new java.awt.Color(255, 255, 255));
        pic.setBorderSize(3);
        pic.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/1200px-Image_created_with_a_mobile_phone.png"))); // NOI18N

        btnContinue.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_advance_20px_3.png"))); // NOI18N
        btnContinue.setText("Tiếp tục");
        btnContinue.setBorderRadius(5);
        btnContinue.setBorderline(true);
        btnContinue.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        btnContinue.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        btnContinue.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);

        javax.swing.GroupLayout pnlProfileLayout = new javax.swing.GroupLayout(pnlProfile);
        pnlProfile.setLayout(pnlProfileLayout);
        pnlProfileLayout.setHorizontalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addGroup(pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblName)
                    .addComponent(lblRole)
                    .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(263, 263, 263))
        );
        pnlProfileLayout.setVerticalGroup(
            pnlProfileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlProfileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pic, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblName)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblRole)
                .addGap(18, 18, 18)
                .addComponent(btnContinue, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        add(pnlProfile, "card2");

        gifLoad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/loading.gif"))); // NOI18N

        btnCancel2.setText("Hủy");
        btnCancel2.setBorderRadius(5);
        btnCancel2.setBorderline(true);

        javax.swing.GroupLayout pnlLoadingLayout = new javax.swing.GroupLayout(pnlLoading);
        pnlLoading.setLayout(pnlLoadingLayout);
        pnlLoadingLayout.setHorizontalGroup(
            pnlLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoadingLayout.createSequentialGroup()
                .addGroup(pnlLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlLoadingLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(gifLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlLoadingLayout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(240, Short.MAX_VALUE))
        );
        pnlLoadingLayout.setVerticalGroup(
            pnlLoadingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoadingLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(gifLoad, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 69, Short.MAX_VALUE)
                .addComponent(btnCancel2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43))
        );

        add(pnlLoading, "card3");

        lbMessage.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        lbMessage.setForeground(new java.awt.Color(235, 90, 90));
        lbMessage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbMessage.setText("Message");

        btnCancel3.setText("Hủy");
        btnCancel3.setBorderRadius(5);
        btnCancel3.setBorderline(true);

        javax.swing.GroupLayout pnlMessageLayout = new javax.swing.GroupLayout(pnlMessage);
        pnlMessage.setLayout(pnlMessageLayout);
        pnlMessageLayout.setHorizontalGroup(
            pnlMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMessageLayout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(pnlMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(lbMessage, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
                    .addComponent(btnCancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(200, 200, 200))
        );
        pnlMessageLayout.setVerticalGroup(
            pnlMessageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMessageLayout.createSequentialGroup()
                .addGap(122, 122, 122)
                .addComponent(lbMessage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(btnCancel3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(62, 62, 62))
        );

        add(pnlMessage, "card4");
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.button.Button btnCancel;
    private gui.swing.button.Button btnCancel2;
    private gui.swing.button.Button btnCancel3;
    private gui.swing.button.Button btnContinue;
    private gui.swing.image.ImageAvatar gifLoad;
    private javax.swing.JLabel lbMessage;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblRole;
    private gui.swing.image.ImageAvatar pic;
    private gui.swing.panel.PanelTransparent pnlLoading;
    private gui.swing.panel.PanelTransparent pnlMessage;
    private gui.swing.panel.PanelTransparent pnlProfile;
    // End of variables declaration//GEN-END:variables
}
