package gui;

import gui.dropshadow.ShadowType;
import gui.swing.panel.PanelShadow;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class GD_NhanVien extends JLayeredPane {

    private MigLayout layout;
    private PanelShadow panelTop;
    private PanelShadow panelBottom;
    private PanelShadow panelHidden;
    private Animator animator;
    private boolean show;
    private final DecimalFormat df = new DecimalFormat("##0.000");

    public GD_NhanVien() {
        initComponents();
        buildGD();
    }

    private void buildGD() {
        layout = new MigLayout("fill, insets 0", "[fill]", "[fill][fill]");
        setLayout(layout);
        createPanelTop();
        createPanelBottom();
        createPanelHidden();
        setLayer(panelHidden, JLayeredPane.POPUP_LAYER);
        add(panelTop, "grow, wrap, h 250!");
        add(panelBottom, "grow, h 100%");
        add(panelHidden, "pos -400 0 n n, h 100%, w 400!");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float size = fraction * 400;
                if(show) {
                    size = - size;
                } else {
                    size -= 400;
                }
                layout.setComponentConstraints(panelHidden, "pos " + (int) size + " 0 n n, h 100%, w 400!");
                revalidate();
            }

            @Override
            public void end() {
                show = !show;
                if(!show) {
                    panelHidden.setVisible(false);
                }
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);
    }

    private void createPanelTop() {
        panelTop = new PanelShadow();
        panelTop.setBackground(new Color(255, 255, 255));
        panelTop.setShadowType(ShadowType.TOP);
        panelTop.setShadowOpacity(0.3f);
        panelTop.setShadowSize(3);
        JButton button = new JButton("Open");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if(!animator.isRunning()) {
                    if(!show) {
                        animator.start();
                        panelHidden.setVisible(true);
                    }
                }
            }
        });
        panelTop.add(button);

    }

    private void createPanelBottom() {
        panelBottom = new PanelShadow();
        panelBottom.setBackground(new Color(255, 255, 255));
        panelBottom.setShadowType(ShadowType.TOP);
        panelBottom.setShadowOpacity(0.3f);
        panelBottom.setShadowSize(3);
    }

    private void createPanelHidden() {
        panelHidden = new PanelShadow();
        panelHidden.setBackground(Color.red);
        panelHidden.setShadowType(ShadowType.CENTER);
        panelHidden.setShadowOpacity(0.3f);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1119, 620));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 620, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
