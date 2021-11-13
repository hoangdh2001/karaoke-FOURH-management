package gui.component;

import dao.NhanVien_DAO;
import entity.NhanVien;
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
import gui.swing.event.EventSelectedRow;

public class PanelLogin extends javax.swing.JLayeredPane {
    private ActionListener evt;
    private EventLogin event;
    private MyTextField txtUser;
    private MyPasswordField txtPass;
    private MyTextField txtSdt;
    private MyTextField txtEmail;
    private MyPasswordField txtForgotPass;
    private MyPasswordField txtRePass;
    
    
    public PanelLogin() {
        initComponents();
        buildLogin();
        buildForgotPass();
        setOpaque(false);
        login.setVisible(true);
        forgotPass.setVisible(false);
    }
    
    /**
     * Thêm sự kiện cho nút nút quên mật khẩu và quay lại
     * @param evt 
     */
    public void addEventOpen(ActionListener evt) {
        this.evt = evt;
    }
    /**
     * Thêm sự kiện cho nút đăng nhập
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
        txtUser.setHint("Tên đăng nhập");
        login.add(txtUser, "w 60%");
        
        txtPass = new MyPasswordField();
        txtPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtPass.setHint("Mật khẩu");
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
        forgotPass.setLayout(new MigLayout("wrap", "push[center]push", "push[]40[]10[]10[]10[]25[]10[]push"));
        
        JLabel label = new JLabel("Quên mật khẩu");
        label.setFont(new Font("sansserif", Font.BOLD, 24));
        label.setForeground(new Color(7, 164, 121));
        forgotPass.add(label);
        
        txtSdt = new MyTextField();
        txtSdt.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/user.png")));
        txtSdt.setHint("Số điện thoại"); // text dưới nền 
        forgotPass.add(txtSdt, "w 60%");
        
        txtEmail = new MyTextField();
        txtEmail.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/mail.png")));
        txtEmail.setHint("Email");
        forgotPass.add(txtEmail, "w 60%");
        
        txtForgotPass = new MyPasswordField();
        txtForgotPass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtForgotPass.setHint("Mật khẩu");
        forgotPass.add(txtForgotPass, "w 60%");
        
        txtRePass = new MyPasswordField();
        txtRePass.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/pass.png")));
        txtRePass.setHint("Nhập lại mật khẩu");
        forgotPass.add(txtRePass, "w 60%");
        
        
        Button forgotPassBtn = new Button("Đổi mật khẩu", true);
        forgotPassBtn.setBackground(new Color(7, 164, 121));
        forgotPassBtn.setForeground(Color.WHITE);
        forgotPassBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                String sdt = txtSdt.getText();
                String email = txtEmail.getText();
                byte[] rePass = String.valueOf(txtRePass.getPassword()).getBytes();
                event.forgotPass(sdt, email, rePass);
            }
        });
        forgotPass.add(forgotPassBtn, "w 40%, h 40!");
        
        Button backBtn = new Button("Quay lại", true);
        backBtn.setBackground(new Color(7, 164, 121));
        backBtn.setForeground(Color.WHITE);
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                PanelLogin.this.evt.actionPerformed(arg0);
            }
        });
        forgotPass.add(backBtn, "w 40%, h 40!");
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
     * @param show 
     */
    public void showForgetPass(boolean show) {
        if(show) {
            forgotPass.setVisible(false);
            login.setVisible(true);
            txtUser.requestFocus();
            txtUser.selectAll();
            txtPass.setText("");
        }
        else {
            forgotPass.setVisible(true);
            login.setVisible(false);
            txtSdt.requestFocus();
            txtSdt.selectAll();
            txtEmail.selectAll();
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
