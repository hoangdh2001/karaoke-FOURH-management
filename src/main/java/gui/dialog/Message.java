package gui.dialog;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

import gui.swing.button.ButtonOutLine;
import gui.swing.icon.GoogleMaterialDesignIcons;
import gui.swing.icon.IconFont;
import gui.swing.icon.IconFontSwing;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.io.InputStream;


public class Message extends JDialog {

    private static final long serialVersionUID = 1L;
    private static final int INFOMATION_MESSAGE = 1;
    private int xx;
    private int xy;
    private final Animator animator;
    private boolean show = true;
    private Object message;
    private Icon icon;

    public Message(Component parentComponent, Object message, String title, Icon icon) {
        setTitle(title);
        setMessage(message);
        this.icon = icon;
        addComponentListener(new ComponentAdapter() {
               @Override
                public void componentResized(ComponentEvent e) {
                    setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));
                }
            });
        buidMessage(parentComponent);
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
    }

    private void buidMessage(Component parentComponent) {
        setModal(true);
        setUndecorated(true);
        setDefaultCloseOperation(2);
        IconFontSwing.register(new IconFont() {
            @Override
            public String getFontFamily() {
                return "Material Icons";
            }

            @Override
            public InputStream getFontInputStream() {
                return getClass().getResourceAsStream("/icon/MaterialIcons-Regular.ttf");
            }
        });
        
//        GraphicsEnvironment.getLocalGraphicsEnvironment()
//          .getDefaultScreenDevice()
//          .setFullScreenWindow(this);

        JPanel background = createBackgroud();
        background.setLayout(null);

        background.add(createTitle());

        background.add(createExitBtn());

        background.add(createWindowHide());

        background.add(createMessage());

        background.add(createOKBtn());

        setContentPane(background);
        pack();
        setLocationRelativeTo(parentComponent);
    }

    private JPanel createBackgroud() {
        final Image image = new ImageIcon(
                getClass().getResource("/icon/background-minisize.png")).getImage();
        JPanel background = new JPanel();
        background.setPreferredSize(new Dimension(image.getWidth(null), image.getHeight(null)));
        return background;
    }

    private JPanel createWindowHide() {
        JPanel windowHide = new JPanel();
        windowHide.setOpaque(false);
        windowHide.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                Message.this.xx = e.getX();
                Message.this.xy = e.getY();
            }
        });
        windowHide.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                int x = e.getXOnScreen();
                int y = e.getYOnScreen();
                Message.this.setLocation(x - Message.this.xx, y - Message.this.xy);
            }
        });
        windowHide.setBounds(0, 0, 400, 35);
        return windowHide;
    }

    private JLabel createTitle() {
        JLabel title = new JLabel(getTitle());
        title.setFont(new Font("Segoe ui Semilight", 0, 16));
        title.setForeground(Color.BLACK);
        title.setBounds(15, 2, 100, 30);
        return title;
    }

    private JLabel createMessage() {
        JLabel message = new JLabel((String) this.message, 0);
        message.setFont(new Font("Segoe ui Semilight", 0, 16));
        
        message.setForeground(Color.BLACK);
        
        if (icon != null) {
            message.setIcon(icon);
            message.setIconTextGap(20);
        }
        else {
            message.setIcon(IconFontSwing.buildIcon(GoogleMaterialDesignIcons.REPORT_PROBLEM, 40, Color.yellow));
            message.setIconTextGap(20);
        }
        message.setBounds(20, 60, 340, 60);
        return message;
    }

    private ButtonOutLine createOKBtn() {
        ButtonOutLine okBtn = new ButtonOutLine();
        okBtn.setText("OK");
        okBtn.setFont(new Font("Segoe ui Semilight", 0, 16));
        okBtn.setForeground(Color.BLACK);
        okBtn.setBackground(new Color(10, 181, 72));
        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMessage();
            }
        });
        okBtn.setBounds(150, 150, 100, 30);
        return okBtn;
    }

    private JButton createExitBtn() {
        Image image = new ImageIcon(getClass().getResource("/icon/multiply_30px.png")).getImage();
        JButton exitBtn = new JButton();
        exitBtn.setIcon(new ImageIcon(image));
        exitBtn.setContentAreaFilled(false);
        exitBtn.setFocusable(false);
        exitBtn.setToolTipText("Close");
        exitBtn.setRolloverIcon(new ImageIcon(getClass().getResource("/icon/multiply_30px_red.png")));
        exitBtn.setBounds(365, 2, image.getWidth(null), image.getHeight(null));
        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeMessage();
            }
        });
        return exitBtn;
    }

    public void closeMessage() {
        if (animator.isRunning()) {
            animator.stop();
        }
        show = false;
        animator.start();
    }

    public static void showMessageDialog(Component parentComponent, Object message) {
        showMessageDialog(parentComponent, message, "Message");
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title) {
        showMessageDialog(parentComponent, message, title, Message.INFOMATION_MESSAGE);
    }
    
    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType) {
        showMessageDialog(parentComponent, message, title, messageType, null);
    }

    public static void showMessageDialog(Component parentComponent, Object message, String title, int messageType, Icon icon) {
        showOptionDialog(parentComponent, message, title, icon);
    }
    
//    public static int showConfirmDialog(Component parentComponent, Object message) {
//        
//    }
//    
//    public static int showConfirmDialog(Component parentComponent, Object message, String title) {
//        
//    }
//    
//    public static int showConfirmDialog(Component parentComponent, Object message, String title, int optionType) {
//        
//    }

    @SuppressWarnings("deprecation")
    public static int showOptionDialog(Component parentComponent, Object message, String title, Icon icon) {
        Message pane = new Message(parentComponent, message, title, icon);
        pane.show();
        return 1;
    }
    
    /**
     * @return message
     */
    public Object getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(Object message) {
        this.message = message;
    }

    /**
     * @return the icon
     */
    public Icon getIcon() {
        return icon;
    }

}
