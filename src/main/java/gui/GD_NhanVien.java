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
        add(panelHidden, "pos " + getPreferredSize().getWidth() + 20 + " 0al n n, h 100%, w 400!");
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double size = getPreferredSize().getWidth() + 20 - 400;
                double sizeClose = getPreferredSize().getWidth();
                if (show) {
                    if (fraction <= 0.3f) {
                        size = GD_NhanVien.this.getPreferredSize().getWidth() * (1f - fraction);
                        layout.setComponentConstraints(panelHidden, "pos " + size + " 0al n n, h 100%, w 400!");
                        revalidate();
                    }
                } else {
                    if (fraction >= 0.7f) {
                        sizeClose = GD_NhanVien.this.getPreferredSize().getWidth() * fraction;
                        layout.setComponentConstraints(panelHidden, "pos " + sizeClose + " 0al n n, h 100%, w 400!");
                        revalidate();
                    }
                }
            }
        };
        animator = new Animator(1000, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
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
                if (!animator.isRunning()) {
                    animator.start();
                } else {
                    animator.stop();
                    animator.start();
                }
                show = true;
            }
        });
        JButton buttonClose = new JButton("Close");
        buttonClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!animator.isRunning()) {
                    animator.start();
                } else {
                    animator.stop();
                    animator.start();
                }
                show = false;
            }
        });
        panelTop.add(button);
        panelTop.add(buttonClose);

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
