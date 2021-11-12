package gui;

import entity.NhanVien;
import gui.component.Message;
import gui.dialog.DL_Progress;
import gui.swing.event.EventOnClick;
import java.awt.Color;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class GD_DangNhap extends javax.swing.JFrame {

    private boolean show = true;
    private boolean close = true;
    private final Animator animator;
    private NhanVien nhanVien;

    public GD_DangNhap(String title) {
        super(title);
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
                    if(!close)
                        new DL_Progress(GD_DangNhap.this, nhanVien).setVisible(true);
                }
            }
        };
        animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        
        pnlForm.addEventLogin(new EventOnClick() {
            @Override
            public void onClick(Object object) {
                if(object != null) {
                    NhanVien nhanVien = (NhanVien) object;
                    GD_DangNhap.this.nhanVien = nhanVien;
                    showDLProgress();
                } else {
                    pnlForm.showMessage(Message.MessageType.ERROR, "Sai mật khẩu!");
                }
            }
        });
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        show = b;
        if(show) {
            pnlForm.setTextWhenBack();
            animator.start();
        }
    }
    
    private void close() {
        if(animator.isRunning()) 
            animator.stop();
        show = false;
        close = true;
        animator.start();
    }
    
    private void showDLProgress() {
        if(animator.isRunning())
            animator.stop();
        show = false;
        close = false;
        animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new gui.swing.panel.PanelShadow();
        pnlForm = new gui.component.PanelForm();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        bg.setBackground(new java.awt.Color(255, 255, 255));
        bg.setBorder(javax.swing.BorderFactory.createEmptyBorder(15, 15, 15, 15));
        bg.setShadowOpacity(0.2F);
        bg.setShadowSize(15);

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, 728, Short.MAX_VALUE)
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
        );

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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow bg;
    private gui.component.PanelForm pnlForm;
    // End of variables declaration//GEN-END:variables
}
