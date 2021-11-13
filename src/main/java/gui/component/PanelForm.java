package gui.component;

import gui.swing.event.EventLogin;
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
import gui.swing.panel.PanelTransparent;
import gui.swing.event.EventSelectedRow;

public class PanelForm extends PanelTransparent {

    private MigLayout layout;
    private boolean isLogin;
    private PanelCover image;
    private PanelLogin login;
    private final double coverSize = 40; // 40 phần trăm
    private final double loginSize = 60; // 60 phần trăm
    private final double addSize = 30; // 30 phần trăm
    private final DecimalFormat df = new DecimalFormat("##0.###");

    public PanelForm() {
        initComponents();
        buildPanelLogin();
    }
    /**
     * Xây dựng pane đăng nhập
     */
    private void buildPanelLogin() {
        layout = new MigLayout("fill, insets 0");
        image = new PanelCover(new ImageIcon(getClass().getResource("/icon/background2.jpg")));
        login = new PanelLogin();
        setLayout(layout);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                double fractionCover;
                double fractionForm;
                double size = coverSize;
                // Khi panel cover đến giữa 
                if (fraction <= 0.5f) {
                    size += fraction * size; // khi fraction đến ngưỡng 0.5 sẽ giảm cho đúng kích thước ban đầu
                } else {
                    size += addSize - fraction * addSize; // size sẽ thành đổi liên tục theo fraction
                }
                // nếu pane login đang được hiện thị
                if (isLogin) {
                    fractionCover = fraction;
                    fractionForm = 1f - fraction;
                    if (fraction <= 0.5f) {
                        image.forgotPassLeft(fraction * 100);
                    } else {
                        image.loginLeft((1f - fraction) * 100);
                    }
                } else {
                    // gán để căn lề theo các giá trị
                    fractionCover = 1f - fraction;
                    fractionForm = fraction;
                    if (fraction >= 0.5f) {
                        // Chuyển các text trên panel cover 
                        image.forgotPassRight(fractionCover * 100);
                    } else {
                        image.loginRight(fractionForm * 100);
                    }
                }
                // Mở giao diện quên mật khẩu khi đến qua ngưỡng fraction 0.5 
                // hiển thị giao diện quên mật khẩu khi isLogin true và ngược lại
                if (fraction >= 0.5f) {
                    login.showForgetPass(isLogin);
                }
                fractionCover = Double.valueOf(df.format(fractionCover)); // các giá trị số thực tránh số vô hạn tuần hoàn hoặc các số có số thập phân dài
                fractionForm = Double.valueOf(df.format(fractionForm));
                layout.setComponentConstraints(image, "width " + size + "%, pos " + fractionCover + "al 0 n 100%"); // pos định vị các thành phân theo giá trị tuyệt đối, các thành phần sẽ không bị ảnh hưởng bởi layout 
                layout.setComponentConstraints(login, "width " + loginSize + "%, pos" + fractionForm + "al 0 n 100%"); // pos x y w h
                PanelForm.this.revalidate();
            }

            @Override
            public void end() {
                isLogin = !isLogin; // đặt cho isLogin = false để hiểu theo pane đăng nhập không được hiện thị và ngược lại
            }
        };
        Animator animator = new Animator(1000, target); // Xảy ra sự kiện timing trong 1s
        animator.setAcceleration(0.5f);
        animator.setDeceleration(0.5f);
        animator.setResolution(0); // mượt
        add(image, "width " + coverSize + "%, pos 1al 0 n 100%"); // đặt image width 40% , căn trái 
        add(login, "width " + loginSize + "%, pos 0al 0 n 100%"); // đặt login width 60%, căn phải
        login.addEventOpen(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (!animator.isRunning()) {
                    animator.start();
                }
            }
        });

    }

    public void addEventLogin(EventLogin evt) {
        login.addEventLogin(evt);
    }
    
    public void setTextWhenBack() {
        login.setTextWhenBack();
    }

    public void showMessage(Message.MessageType messageType, String message) {
        Message ms = new Message();
        ms.showMessage(messageType, message);
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                if(!ms.isShow()) {
                    PanelForm.this.add(ms, "pos 0.5al -30", 0); // Chèn thêm message vào panel login
                    ms.setVisible(true);
                    PanelForm.this.repaint();
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
                PanelForm.this.repaint();
                PanelForm.this.revalidate();
            }
            
            // Sau khi kết thúc sự kiện timing thì xóa ms ra khỏi Panel login
            @Override
            public void end() {
                if(ms.isShow()) {
                    PanelForm.this.remove(ms);
                    PanelForm.this.repaint();
                    PanelForm.this.revalidate();
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
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10); // bo tròn 
        super.paintComponent(g);
    }
}
