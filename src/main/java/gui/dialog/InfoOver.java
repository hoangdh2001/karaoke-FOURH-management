package gui.dialog;

import gui.component.PanelInfoOverBottom;
import gui.component.PanelInfoOverTop;
import gui.swing.panel.PanelShadow;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class InfoOver extends javax.swing.JDialog {

    private boolean show = true;
    private final Animator animator;
    private MigLayout layout;
    private final PanelShadow panelShadow;
    private PanelInfoOverBottom pnlBottom;
    private final MigLayout layout2;
    private final DecimalFormat df = new DecimalFormat("##0.###");

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public InfoOver(java.awt.Frame parent) {
        super(parent, false);
        initComponents();
        layout = new MigLayout("fillx, insets 0", "[fill]");
        jLayeredPane1.setLayout(layout);
        
        panelShadow = new PanelShadow();
        panelShadow.setShadowSize(10);
        panelShadow.setShadowOpacity(0.2f);
        panelShadow.setBackground(Color.WHITE);
        panelShadow.setBorder(new EmptyBorder(15, 15, 15, 15));
        panelShadow.setLayout(layout2 = new MigLayout("fillx, debug, insets 0", "[fill]", "[fill]"));
        
        PanelInfoOverTop pnlTop = new PanelInfoOverTop();
        pnlTop.setBorder(new MatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTop.setBackground(Color.WHITE);
        panelShadow.add(pnlTop, "h 180!");
        
        
        pnlBottom = new PanelInfoOverBottom();
        pnlBottom.setBackground(Color.WHITE);
        pnlBottom.setVisible(false);
        panelShadow.add(pnlBottom, "pos 0al 0 n n, h 30%");
        jLayeredPane1.add(panelShadow, "h 210!");
        
        setBackground(new Color(0, 0, 0, 0));
        setOpacity(0);
        
        TimingTarget target1 = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionSize = 210 + ((getHeight() - 210) * fraction);
                double fractionPoint;
                fractionPoint = Double.valueOf(df.format(210 * fraction));
                if(fraction >= 0.5f) {
                    pnlBottom.setVisible(true);
                }
                layout.setComponentConstraints(panelShadow, "h "+ fractionSize +"!");
                layout2.setComponentConstraints(pnlBottom, "pos 0al "+ fractionPoint +" n n, h 70%");
                jLayeredPane1.repaint();
                jLayeredPane1.revalidate();
            }
        };
        Animator animator2 = new Animator(600, target1);
        animator2.setResolution(0);
        animator2.setStartDelay(800);
        animator2.setAcceleration(0.5f);
        animator2.setDeceleration(0.5f);
        
        TimingTarget target2 = new TimingTargetAdapter() {
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
                if (show == false) {
                    setVisible(false);
                } else {
                    animator2.start();
                }
            }
        };
        animator = new Animator(200, target2);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        
    }
    
    
    

    @Override
    public void setVisible(boolean bln) {
        super.setVisible(bln);
        if (show) {
            animator.start();
        }
    }

    private void closeMenu() {
        if (animator.isRunning()) {
            animator.stop();
        }
        show = false;
        animator.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLayeredPane1)
        );

        setBounds(0, 0, 392, 396);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        closeMenu();
    }//GEN-LAST:event_formWindowLostFocus

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration//GEN-END:variables
}
