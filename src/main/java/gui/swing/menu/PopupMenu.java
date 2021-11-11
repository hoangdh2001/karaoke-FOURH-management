package gui.swing.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;

import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import gui.swing.event.EventMenuSelected;

public class PopupMenu extends javax.swing.JDialog {

    private Animator animator;
    private boolean show = true;
    private Color background = new Color(50, 50, 50);

    public Color getBackgroundPane() {
        return background;
    }

    public void setBackgroundPane(Color bg) {
        this.background = bg;
        panel.setBackground(bg);
    }

    public PopupMenu(java.awt.Frame parent, int index, EventMenuSelected eventSelected, Color fg, String... subMenu) {
        super(parent, false);
        initComponents();
        buidPopupMenu(index, eventSelected, fg, subMenu);
    }

    public PopupMenu(java.awt.Frame parent, int index, EventMenuSelected eventSelected, String... subMenu) {
        super(parent, false);
        initComponents();
        buidPopupMenu(index, eventSelected, Color.BLACK, subMenu);
    }

    private void buidPopupMenu(int index, EventMenuSelected eventSelected, Color fg, String... subMenu) {
        setOpacity(0f);
        setBackground(new Color(0, 0, 0, 0));
        panel.setLayout(new MigLayout("fill, wrap", "8[fill, 120]0", "0[35, fill]0[35, fill]0"));
        int subMenuIndex = -1;
        for (String st : subMenu) {
            MenuButton item = new MenuButton(st, true);
            item.setForeground(fg);
            item.setIndex(++subMenuIndex);
            item.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    eventSelected.menuSelected(index, item.getIndex());
                    closeMenu();
                }
            });
            panel.add(item);
            setSize(new Dimension(150, 35 * subMenu.length));
        }
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
                if (show == false) {
                    setVisible(false);
                }
            }
        };
        animator = new Animator(200, target);
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
    private void initComponents() {

        panel = new PanelPopup();

        panel.setBackground(background);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            @Override
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }

            @Override
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                formWindowLostFocus(evt);
            }
        });

        GroupLayout panelLayout = new GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 200, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
                panelLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGap(0, 0, Short.MAX_VALUE)
        );

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(panel, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }

    private void formWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowLostFocus
        closeMenu();
    }

    private PanelPopup panel;
}
