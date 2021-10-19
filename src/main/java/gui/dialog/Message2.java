/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialog;

import gui.StartPrograming;
import gui.swing.button.Button;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.RoundRectangle2D;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

/**
 *
 * @author Admin
 */
public class Message2 extends JDialog {

    private static final int MESSAGE_DIALOG = 1;
    private static final int CONFIRM_DIALOG = 2;
    private static final int INFOMATION_MESSAGE = 1;
    private static final int WARNING_MESSAGE = 2;
    private int xx;
    private int xy;
    private boolean show = true;
    private Animator animator;
    private Object message;

    public Message2(Component parentComponent, Object message, String title) {
        setTitle(title);
        this.message = message;
        setUndecorated(true);
        setModal(true);
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 30, 30));
            }
        });
        buidMessageDialog();
        setOpacity(0f);
        getContentPane().setBackground(Color.WHITE);
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
                    dispose();
                }
            }

        };
        animator = new Animator(200, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.start();
        setLocationRelativeTo(parentComponent);
    }

    private void buidMessageDialog() {
        setContentPane(createBackground());
        pack();
    }

    private JPanel createBackground() {
        MigLayout layout = new MigLayout("fill, insets 0", "0[100%, fill]0", "0[min!, fill]0[fill]0");
        JPanel background = new JPanel();
        background.setLayout(layout);
        background.setPreferredSize(new Dimension(300, 150));
        background.add(createWindow(), "h 40!, span");
        background.add(createPane(), "span");
        return background;
    }

    private JPanel createWindow() {
        JPanel window = new JPanel();
        window.setOpaque(false);
        window.setLayout(new MigLayout("fill, insets 0", "[100%, center][]", "[]"));
        window.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Message2.this.xx = e.getX();
                Message2.this.xy = e.getY();
            }
        });
        window.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                Message2.this.setLocation(x - Message2.this.xx, y - Message2.this.xy);
            }
        });
        window.add(createTitle(), "spany");
        window.add(createExitbtn(), "w 30!");
        return window;
    }

    private JLabel createTitle() {
        JLabel title = new JLabel(getTitle(), JLabel.CENTER);
        title.setIcon(new ImageIcon(getClass().getResource("/icon/tongue_out_30px.png")));
        title.setIconTextGap(30);
        title.setFont(new Font("sansserif", Font.PLAIN, 24));
        return title;
    }

    private Button createExitbtn() {
        Button exitBtn = new Button("", false);
        exitBtn.setIcon(new ImageIcon(getClass().getResource("/icon/multiply_30px.png")));
        exitBtn.setContentAreaFilled(false);
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setFocusable(false);
        exitBtn.setToolTipText("Close");
        exitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e); //To change body of generated methods, choose Tools | Templates.
                exitBtn.setBackground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e); //To change body of generated methods, choose Tools | Templates.
                exitBtn.setBackground(Color.WHITE);
            }
        });
        exitBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/icon/multiply_30px_white.png")));
        return  exitBtn;
    }

    private JPanel createPane() {
        JPanel pane = new JPanel();
        return pane;
    }

    public static void showMessageDialog(Component parentComponent, Object message) {
        showMessageDialog(parentComponent, message, "Thông báo");
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title) {
        showOptionDialog(parentComponent, message, title, MESSAGE_DIALOG);
    }

    public static int showOptionDialog(Component parentComponent, Object message, String title, int optionType) {
        Message2 pane = new Message2(parentComponent, message, title);
        pane.show();
        return 1;
    }

    public static void main(String[] args) {
        try {
            //        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(StartPrograming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(StartPrograming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(StartPrograming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(StartPrograming.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }

            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Message2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Message2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Message2.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Message2.class.getName()).log(Level.SEVERE, null, ex);
        }
        Message2.showMessageDialog(null, "Hello");
    }
}
