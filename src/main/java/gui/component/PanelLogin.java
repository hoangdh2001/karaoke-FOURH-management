package gui.component;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class PanelLogin extends javax.swing.JPanel {

    private MigLayout layout;
    private boolean isLogin;
    private PanelCover image;
    private PanelForm form;
    private final double coverSize = 40;
    private final double formSize = 60;
    private final double addSize = 30;
    private final DecimalFormat df = new DecimalFormat("##0.###");

    public PanelLogin() {
        initComponents();
        buildPanelForm();
    }

    private void buildPanelForm() {
        layout = new MigLayout("fill, insets 0");
        image = new PanelCover(new ImageIcon(getClass().getResource("/icon/background2.jpg")));
        form = new PanelForm();
        setLayout(layout);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionForm;
                double size = coverSize;
                if (fraction <= 0.5f) {
                    size += fraction * size;
                } else {
                    size += addSize - fraction * addSize;
                }

                if (isLogin) {
                    fractionCover = 1f - fraction;
                    fractionForm = fraction;
                    if (fraction >= 0.5f) {
                        image.forgotPassRight(fractionCover * 100);
                    } else {
                        image.loginRight(fractionForm * 100);
                    }
                } else {
                    fractionCover = fraction;
                    fractionForm = 1f - fraction;
                    if (fraction <= 0.5f) {
                        image.forgotPassLeft(fraction * 100);
                    } else {
                        image.loginLeft((1f - fraction) * 100);
                    }
                }
                if (fraction >= 0.5f) {
                    form.showForgetPass(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover));
                fractionForm = Double.valueOf(df.format(fractionForm));
                layout.setComponentConstraints(image, "width " + size + "%, pos " + fractionCover + "al 0 n 100%");
                layout.setComponentConstraints(form, "width " + formSize + "%, pos" + fractionForm + "al 0 n 100%");
                PanelLogin.this.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin;
            }
        };
        Animator animator = new Animator(1000, target);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0); // mượt
        add(image, "width " + coverSize + "%, pos 0al 0 n 100%");
        add(form, "width " + formSize + "%, pos 1al 0 n 100%");
        form.addEventOpen(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });

    }

    public void login(ActionListener evt) {
        form.addEventLogin(evt);
    }

    public void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if(!ms.isShow()) {
                    PanelLogin.this.add(ms, "pos 0.5al -30", 0); // Chèn thêm message vào panel login
                    ms.setVisible(true);
                    PanelLogin.this.repaint();
                }
            }

            @Override
            public void timingEvent(float fraction) {
                float f;
                if(ms.isShow()) {
                    f = 40 * (1f - fraction);
                }
                else {
                    f = 40 * fraction;
                }
                layout.setComponentConstraints(ms, "pos 0.5al " + (int) (f - 30)); // Hiện thị message theo 
                PanelLogin.this.repaint();
                PanelLogin.this.revalidate();
            }
            
            // Sau khi kết thúc sự kiện timing thì xóa ms ra khỏi Panel login
            @Override
            public void end() {
                if(ms.isShow()) {
                    PanelLogin.this.remove(ms);
                    PanelLogin.this.repaint();
                    PanelLogin.this.revalidate();
                }
                else {
                    ms.setShow(true);
                }
            }
        };
        Animator animator = new Animator(300, target);
        animator.setResolution(0);
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.start();
        
        // Lập lại sự kiện timing để tắt message, message sẽ hiện thị trong 2s
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                    animator.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(255, 255, 255));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }
}
