package gui.component;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import gui.swing.button.Button;
import gui.swing.button.LinkBtn;
import gui.swing.event.EventLogin;
import gui.swing.textfield.MyPasswordField;
import gui.swing.textfield.MyTextField;
import net.miginfocom.swing.MigLayout;
import gui.swing.label.WrapLabel;
import java.awt.CardLayout;
import javax.swing.JPanel;

public class PanelLogin extends javax.swing.JLayeredPane {
    
    private ActionListener evt;
    private EventLogin event;
    private MyTextField txtUser;
    private MyPasswordField txtPass;
    private MyTextField txtSdt;
    private MyPasswordField txtForgotPass;
    private MyPasswordField txtRePass;
    private JPanel pnlChangePass;
    private JPanel pnlSearchUser;
    
    public PanelLogin() {
        initComponents();
        buildLogin();
        buildForgotPass();
        setOpaque(false);
        login.setVisible(true);
        forgotPass.setVisible(false);
    }
    
    public void showPanel() {
        pnlSearchUser.setVisible(true);
        pnlChangePass.setVisible(false);
    }

    /**
     * Thêm sự kiện cho nút nút quên mật khẩu và quay lại
     *
     * @param evt
     */
    public void addEventOpen(ActionListener evt) {
        this.evt = evt;
    }

    /**
     * Thêm sự kiện cho nút đăng nhập
     *
     * @param event
     */
    public void addEventLogin(EventLogin event) {
        this.event = event;
    }

    /**
     * Xây dựng giao diện login
     */
    private void buildLogin() {
        login.setLayout(new MigLayout("wrap", "push[center]push", "push[]40[]10[]10[]25[]push"));
        
        JLabel label = new JLabel("Đăng nhập");
        label.setFont(new Font("sansserif", Font.BOLD, 24));
        label.setForeground(new Color(7, 164, 121));
        login.add(label);
        
        txtUser = new MyTextField();
        txtUser.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtUser.setText("0943015200");
        txtUser.setHint("Tên đăng nhập");
        txtUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sdt = txtUser.getText();
                byte[] pass = String.valueOf(txtPass.getPassword()).getBytes();
                PanelLogin.this.event.login(sdt, pass);
            }
        });
        login.add(txtUser, "w 60%");
        
        txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Mật khẩu");
        txtPass.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String sdt = txtUser.getText();
                byte[] pass = String.valueOf(txtPass.getPassword()).getBytes();
                PanelLogin.this.event.login(sdt, pass);
            }
        });
        login.add(txtPass, "w 60%");
        
        LinkBtn forgotBtn = new LinkBtn("Quên mật khẩu?");
        forgotBtn.setFont(new Font("sansserif", Font.ITALIC, 12));
        forgotBtn.setForeground(Color.GRAY);
        forgotBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                PanelLogin.this.evt.actionPerformed(arg0);
            }
        });
        login.add(forgotBtn, "w 20%, right");
        
        Button loginBtn = new Button("Đăng nhập", true);
        loginBtn.setBackground(new Color(7, 164, 121));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String sdt = txtUser.getText();
                byte[] pass = String.valueOf(txtPass.getPassword()).getBytes();
                PanelLogin.this.event.login(sdt, pass);
            }
        });
        login.add(loginBtn, "w 40%, h 40!");
    }

    /**
     * Xây dựng giao diện quên mật khẩu
     */
    private void buildForgotPass() {
        forgotPass.setLayout(new MigLayout("wrap", "push[center]push", "push[]5[]push"));
        
        JLabel label = new JLabel("Quên mật khẩu");
        label.setFont(new Font("sansserif", Font.BOLD, 24));
        label.setForeground(new Color(7, 164, 121));
        forgotPass.add(label);
        
        JPanel pnl = new JPanel();
        pnl.setLayout(new CardLayout());
        forgotPass.add(pnl, "w 100%, h 70%");
        
        pnlSearchUser = new JPanel();
        pnlSearchUser.setLayout(new MigLayout("wrap", "push[center]push", "push[]5[]20[]push"));
        pnlSearchUser.setBackground(new Color(255, 255, 255));
        pnl.add(pnlSearchUser);
        
        WrapLabel lblQuestion = new WrapLabel("Vui lòng nhập email hoặc số di dộng để tìm kiếm tài khoản của bạn.");
        lblQuestion.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlSearchUser.add(lblQuestion, "w 270!, h 40!");
        
        txtSdt = new MyTextField();
        txtSdt.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtSdt.setHint("Số điện thoại"); // text dưới nền 
        pnlSearchUser.add(txtSdt, "w 60%");
        
        Button backBtn = new Button("Quay lại", true);
        backBtn.setBackground(new Color(7, 164, 121));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                PanelLogin.this.evt.actionPerformed(arg0);
            }
        });
        pnlSearchUser.add(backBtn, "w 20%, h 40!, split 2, right");
        
        Button searchBtn = new Button("Tìm kiếm", true);
        searchBtn.setBackground(new Color(7, 164, 121));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String sdtOrEmail = txtSdt.getText();
                event.searchUser(sdtOrEmail, pnlChangePass, pnlSearchUser);
            }
        });
        pnlSearchUser.add(searchBtn, "w 20%, h 40!");
        
        pnlChangePass = new JPanel();
        pnlChangePass.setVisible(false);
        pnlChangePass.setLayout(new MigLayout("wrap", "push[center]push", "push[]5[]5[]20[]push"));
        pnlChangePass.setBackground(new Color(255, 255, 255));
        pnl.add(pnlChangePass);
        
        WrapLabel lblQuestion2 = new WrapLabel("Nhập mật khẩu mới vào để đổi mật khẩu.");
        lblQuestion2.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlChangePass.add(lblQuestion2, "w 270!, h 40!");
        
        txtForgotPass = new MyPasswordField();
        txtForgotPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtForgotPass.setHint("Mật khẩu");
        pnlChangePass.add(txtForgotPass, "w 60%");
        
        txtRePass = new MyPasswordField();
        txtRePass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtRePass.setHint("Nhập lại mật khẩu");
        pnlChangePass.add(txtRePass, "w 60%");
        
        Button btnCancel = new Button("Hủy", true);
        btnCancel.setBackground(new Color(7, 164, 121));
        btnCancel.setForeground(Color.WHITE);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                pnlChangePass.setVisible(false);
                pnlSearchUser.setVisible(true);
            }
        });
        pnlChangePass.add(btnCancel, "w 20%, h 40!, split 2, right");
        
        Button forgotPassBtn = new Button("Đổi mật khẩu", true);
        forgotPassBtn.setBackground(new Color(7, 164, 121));
        forgotPassBtn.setForeground(Color.WHITE);
        forgotPassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                byte[] rePass = String.valueOf(txtRePass.getPassword()).getBytes();
                event.forgotPass(rePass);
            }
        });
        pnlChangePass.add(forgotPassBtn, "w 20%, h 40!");
    }
    
    public void setTextWhenBack() {
        txtUser.requestFocus();
        txtUser.selectAll();
        txtPass.setText("");
    }

//    private void forgetPass() {
//        
//        txtSdt.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                
//            }
//        });
//        
//        txtEmail.addKeyListener(new KeyAdapter() {
//            @Override
//            public void keyReleased(KeyEvent e) {
//                switch (txtEmail.getText()) {
//                    case "":
//                        txtEmail.setSuffixIcon(null); // set null khi text rỗng
//                        break;
//                    case "123":
//                        txtEmail.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/ok_20px.png"))); // set icon khi nhập đúng
//                        break;
//                    default:
//                        txtEmail.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/cancel_20px.png"))); // set icon khi nhập sai
//                        break;
//                }
//            }
//        });
//    }
    /**
     * hiển thị giao diện quên mật khẩu theo tham số
     *
     * @param show
     */
    public void showForgetPass(boolean show) {
        if (show) {
            forgotPass.setVisible(false);
            login.setVisible(true);
            txtUser.requestFocus();
            txtUser.selectAll();
            txtPass.setText("");
        } else {
            forgotPass.setVisible(true);
            login.setVisible(false);
            txtSdt.requestFocus();
            txtSdt.setText("");
            txtForgotPass.setText("");
            txtRePass.setText("");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        
        login = new javax.swing.JPanel();
        forgotPass = new javax.swing.JPanel();
        
        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.CardLayout());
        
        login.setBackground(new java.awt.Color(255, 255, 255));
        
        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
        
        add(login, "card2");
        
        forgotPass.setBackground(new java.awt.Color(255, 255, 255));
        
        javax.swing.GroupLayout forgotPassLayout = new javax.swing.GroupLayout(forgotPass);
        forgotPass.setLayout(forgotPassLayout);
        forgotPassLayout.setHorizontalGroup(
                forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        forgotPassLayout.setVerticalGroup(
                forgotPassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );
        
        add(forgotPass, "card3");
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel forgotPass;
    private javax.swing.JPanel login;
    // End of variables declaration//GEN-END:variables
}
