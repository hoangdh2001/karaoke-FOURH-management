package gui.dialog;

import dao.NhanVien_DAO;
import entity.NhanVien;
import gui.GD_Chinh;
import java.awt.Color;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import service.NhanVienService;

public class DL_DoiMatKhau extends javax.swing.JDialog {

    private NhanVienService nhanVienService;

    public DL_DoiMatKhau(java.awt.Frame parent) {
        super(parent, true);
        this.nhanVienService = new NhanVien_DAO();
        initComponents();
    }

    private void showDoiMatKhau(boolean show) {
        if (show) {
            jPanel2.setBackground(Color.WHITE);
            txtMatKhauCu.setEnabled(show);
            txtMatKhauMoi.setEnabled(show);
            txtNhapLai.setEnabled(show);
        } else {
            jPanel2.setBackground(new Color(242, 242, 242));
            txtMatKhauCu.setEnabled(show);
            txtMatKhauMoi.setEnabled(show);
            txtNhapLai.setEnabled(show);
            txtMatKhauCu.setText("");
            txtMatKhauMoi.setText("");
            txtNhapLai.setText("");
        }
    }

    private void showCapNhatThongTin(boolean show) {
        if (show) {
            jPanel3.setBackground(Color.WHITE);
            txtSdt.setEnabled(show);
            txtEmail.setEnabled(show);
        } else {
            jPanel3.setBackground(new Color(242, 242, 242));
            txtSdt.setEnabled(show);
            txtEmail.setEnabled(show);
            txtSdt.setText("");
            txtEmail.setText("");
        }
    }

    private boolean validData() {
        if (cbShowCapNhat.isSelected()) {
            String sdt = txtSdt.getText().trim();
            if (!(sdt.length() > 0)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại.");
                txtSdt.requestFocus();
                return false;
            } else {
                if (!(sdt.matches("^0[0-9]{9}$"))) {
                    JOptionPane.showMessageDialog(null,
                            "Nhập sai định dạng.\nSố điện thoại bao gồm 10 chữ số bắt đầu là số 0.");
                    txtSdt.selectAll();
                    txtSdt.requestFocus();
                    return false;
                }
            }

            String email = txtEmail.getText().trim();
            if (!(email.length() > 0)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập email.");
                txtEmail.requestFocus();
                return false;
            } else {
                if (!(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))) {
                    JOptionPane.showMessageDialog(null, "Nhập sai định dạng email.");
                    txtEmail.selectAll();
                    txtEmail.requestFocus();
                    return false;
                }
            }
        }
        if (cbShow.isSelected()) {
            byte[] matKhauCu = String.valueOf(txtMatKhauCu.getPassword()).getBytes();
            if (!(matKhauCu.length > 0)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu cũ.");
                txtMatKhauCu.requestFocus();
                return false;
            } else {
                if (!Arrays.equals(GD_Chinh.NHAN_VIEN.getMatKhau(), matKhauCu)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng.");
                    txtMatKhauCu.selectAll();
                    txtMatKhauCu.requestFocus();
                    return false;
                }
            }
            byte[] matKhauMoi = String.valueOf(txtMatKhauMoi.getPassword()).getBytes();
            byte[] nhapLai = String.valueOf(txtNhapLai.getPassword()).getBytes();
            if (!(matKhauMoi.length > 0)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu mới.");
                txtMatKhauMoi.requestFocus();
                return false;
            } else if (!(nhapLai.length > 0)) {
                JOptionPane.showMessageDialog(null, "Chưa nhập lại mật khẩu.");
                txtNhapLai.requestFocus();
                return false;
            } else {
                if (!Arrays.equals(matKhauMoi, nhapLai)) {
                    JOptionPane.showMessageDialog(null, "Mật khẩu cũ không đúng.");
                    txtEmail.selectAll();
                    txtEmail.requestFocus();
                    return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnLuu = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        txtMatKhauMoi = new gui.swing.textfield.MyPasswordField();
        txtMatKhauCu = new gui.swing.textfield.MyPasswordField();
        txtNhapLai = new gui.swing.textfield.MyPasswordField();
        jPanel3 = new javax.swing.JPanel();
        txtEmail = new gui.swing.textfield.MyTextField();
        txtSdt = new gui.swing.textfield.MyTextField();
        cbShow = new javax.swing.JCheckBox();
        cbShowCapNhat = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đổi mật khẩu");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnLuu.setBackground(new java.awt.Color(54, 88, 153));
        btnLuu.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu thay đổi");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtMatKhauMoi.setBorderLine(true);
        txtMatKhauMoi.setBorderRadius(5);
        txtMatKhauMoi.setEnabled(false);
        txtMatKhauMoi.setHint("Mật khẩu mới");

        txtMatKhauCu.setBorderLine(true);
        txtMatKhauCu.setBorderRadius(5);
        txtMatKhauCu.setEnabled(false);
        txtMatKhauCu.setHint("Mật khẩu cũ");

        txtNhapLai.setBorderLine(true);
        txtNhapLai.setBorderRadius(5);
        txtNhapLai.setEnabled(false);
        txtNhapLai.setHint("Nhập lại");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 288, Short.MAX_VALUE)
                    .addComponent(txtMatKhauCu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtNhapLai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txtMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNhapLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)));

        txtEmail.setBackgroundColor(new java.awt.Color(255, 255, 255));
        txtEmail.setBorderLine(true);
        txtEmail.setBorderRadius(5);
        txtEmail.setHint("Email");
        txtEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtEmailKeyReleased(evt);
            }
        });

        txtSdt.setBackgroundColor(new java.awt.Color(255, 255, 255));
        txtSdt.setBorderLine(true);
        txtSdt.setBorderRadius(5);
        txtSdt.setHint("Số điện thoại");
        txtSdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSdtKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        cbShow.setText("Đổi mật khẩu");
        cbShow.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbShowActionPerformed(evt);
            }
        });

        cbShowCapNhat.setSelected(true);
        cbShowCapNhat.setText("Cập nhật thông tin");
        cbShowCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbShowCapNhatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbShowCapNhat)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(btnLuu)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cbShow)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(9, Short.MAX_VALUE)
                .addComponent(cbShowCapNhat)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbShow)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        String email = txtEmail.getText().trim();
        String sdt = txtSdt.getText().trim();
        byte[] matKhauCu = String.valueOf(txtMatKhauCu.getPassword()).getBytes();
        byte[] matKhauMoi = String.valueOf(txtMatKhauMoi.getPassword()).getBytes();
        byte[] nhapLai = String.valueOf(txtNhapLai.getPassword()).getBytes();
        NhanVien nhanVien = GD_Chinh.NHAN_VIEN;
        if (validData()) {
            if (cbShowCapNhat.isSelected()) {
                nhanVien.setEmail(email);
                nhanVien.setSoDienThoai(sdt);
            }
            if (cbShow.isSelected()) {
                nhanVien.setMatKhau(nhapLai);
            }
            if (nhanVienService.updateNhanVien(nhanVien)) {
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thành công!");
                dispose();
            } else {
                JOptionPane.showMessageDialog(rootPane, "Cập nhật thất bại!");
            }
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void cbShowActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbShowActionPerformed
        showDoiMatKhau(cbShow.isSelected());
    }//GEN-LAST:event_cbShowActionPerformed

    private void txtEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtEmailKeyReleased
        String email = txtEmail.getText().trim();
        if (!(email.length() > 0)) {
            txtEmail.setSuffixIcon(null); // set null khi text rỗng
        } else {
            if (!(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))) {
                txtEmail.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/cancel_20px.png"))); // set icon khi nhập sai
            } else {
                txtEmail.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/ok_20px.png"))); // set icon khi nhập đúng
            }
        }
    }//GEN-LAST:event_txtEmailKeyReleased

    private void txtSdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKeyReleased
        String sdt = txtSdt.getText().trim();
        if (!(sdt.length() > 0)) {
            txtSdt.setSuffixIcon(null); // set null khi text rỗng
        } else {
            if (!(sdt.matches("^0[0-9]{9}$"))) {
                txtSdt.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/cancel_20px.png"))); // set icon khi nhập sai
            } else {
                txtSdt.setSuffixIcon(new ImageIcon(getClass().getResource("/icon/ok_20px.png"))); // set icon khi nhập đúng
            }
        }
    }//GEN-LAST:event_txtSdtKeyReleased

    private void cbShowCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbShowCapNhatActionPerformed
        showCapNhatThongTin(cbShowCapNhat.isSelected());
    }//GEN-LAST:event_cbShowCapNhatActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JCheckBox cbShow;
    private javax.swing.JCheckBox cbShowCapNhat;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private gui.swing.textfield.MyTextField txtEmail;
    private gui.swing.textfield.MyPasswordField txtMatKhauCu;
    private gui.swing.textfield.MyPasswordField txtMatKhauMoi;
    private gui.swing.textfield.MyPasswordField txtNhapLai;
    private gui.swing.textfield.MyTextField txtSdt;
    // End of variables declaration//GEN-END:variables
}
