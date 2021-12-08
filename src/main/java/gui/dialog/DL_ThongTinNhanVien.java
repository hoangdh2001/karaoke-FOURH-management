
package gui.dialog;

import entity.NhanVien;
import gui.GD_Chinh;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;

public class DL_ThongTinNhanVien extends javax.swing.JDialog {
    private final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    public DL_ThongTinNhanVien(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildDL();
    }
    
    private void buildDL() {
        loadData();
    }
    
    private void loadData() {
        NhanVien nhanVien = GD_Chinh.NHAN_VIEN;
        if (nhanVien.isGioiTinh()) {
            imgAvatar.setIcon(new ImageIcon(getClass().getResource("/icon/avatar_female.png")));
        } else {
            imgAvatar.setIcon(new ImageIcon(getClass().getResource("/icon/avatar_male.png")));
        }
        lblID.setText("ID: " + nhanVien.getMaNhanVien());
        lblTen.setText(nhanVien.getTenNhanVien());
        lblLoaiNhanVien.setText(nhanVien.getLoaiNhanVien().getTenLoaiNV());
        lblNgaySinh.setText("Ngày sinh: " + sdf.format(nhanVien.getNgaySinh()));
        lblCCCD.setText("CCCD: " + nhanVien.getCanCuocCD());
        lblSDT.setText("SDT: " + nhanVien.getSoDienThoai());
        lblEmail.setText("Email: " + nhanVien.getEmail());
        lblDiaChi.setText("Địa chỉ: " + nhanVien.getDiaChi().getSoNha() + ", " + nhanVien.getDiaChi().getTenDuong() + ", " + nhanVien.getDiaChi().getXaPhuong()
        + ", " + nhanVien.getDiaChi().getQuanHuyen() + ", " + nhanVien.getDiaChi().getTinhThanh());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        imgAvatar = new gui.swing.image.ImageAvatar();
        lblTen = new javax.swing.JLabel();
        lblLoaiNhanVien = new javax.swing.JLabel();
        btnChinhSua = new javax.swing.JButton();
        lblID = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        btnXacNhan = new javax.swing.JButton();
        lblDiaChi = new gui.swing.label.WrapLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thông tin nhân viên");
        setBackground(new java.awt.Color(0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setModal(true);
        setName("Thông tin nhân viên"); // NOI18N
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        imgAvatar.setBorderSize(2);
        imgAvatar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/avatar_male.png"))); // NOI18N

        lblTen.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTen.setText("Đỗ Huy Hoàng");

        lblLoaiNhanVien.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblLoaiNhanVien.setForeground(new java.awt.Color(101, 103, 107));
        lblLoaiNhanVien.setText("Quản lý");

        btnChinhSua.setBackground(new java.awt.Color(216, 218, 223));
        btnChinhSua.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnChinhSua.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/fix.png"))); // NOI18N
        btnChinhSua.setText("Chỉnh sửa thông tin các nhân");

        lblID.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblID.setForeground(new java.awt.Color(102, 102, 102));
        lblID.setText("ID: NV000001");

        lblCCCD.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblCCCD.setText("CCCD: 077201006061");

        lblNgaySinh.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblNgaySinh.setText("Ngày sinh: 14/09/2001");

        lblSDT.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblSDT.setText("SĐT: 0368232674");

        lblEmail.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lblEmail.setText("Email: huyhoang14901@gmail.com");

        btnXacNhan.setBackground(new java.awt.Color(54, 88, 153));
        btnXacNhan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnXacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btnXacNhan.setText("Xác nhận");
        btnXacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXacNhanActionPerformed(evt);
            }
        });

        lblDiaChi.setText("Địa chỉ:");
        lblDiaChi.setVAlignStyle(0.0F);
        lblDiaChi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(imgAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTen)
                                    .addComponent(lblLoaiNhanVien)
                                    .addComponent(lblID, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(btnXacNhan)
                                    .addComponent(btnChinhSua))
                                .addGap(16, 16, 16))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblNgaySinh)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblCCCD)
                                    .addComponent(lblEmail)
                                    .addComponent(lblSDT)
                                    .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(imgAvatar, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addComponent(lblID)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTen)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLoaiNhanVien)
                        .addGap(4, 4, 4)
                        .addComponent(btnChinhSua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addComponent(lblNgaySinh)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblCCCD)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSDT)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblEmail)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnXacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXacNhanActionPerformed
        dispose();
    }//GEN-LAST:event_btnXacNhanActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChinhSua;
    private javax.swing.JButton btnXacNhan;
    private gui.swing.image.ImageAvatar imgAvatar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCCCD;
    private gui.swing.label.WrapLabel lblDiaChi;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblID;
    private javax.swing.JLabel lblLoaiNhanVien;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblTen;
    // End of variables declaration//GEN-END:variables
}
