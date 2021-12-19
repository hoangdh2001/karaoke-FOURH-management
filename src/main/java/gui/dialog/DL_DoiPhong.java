package gui.dialog;

import dao.HoaDon_DAO;
import dao.Phong_DAO;
import entity.HoaDon;
import entity.Phong;
import entity.TrangThaiPhong;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import service.HoaDonService;
import service.PhongService;

public class DL_DoiPhong extends javax.swing.JDialog implements ActionListener {

    private PhongService phongService;
    private HoaDonService hoaDonService;
    private HoaDon hoaDon;
    private final DecimalFormat df = new DecimalFormat("#,##0.#");
    private List<Phong> dsPhong;
    
    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public DL_DoiPhong(Frame frame, HoaDon hoaDon) throws Exception {
        super(frame, true);
        this.phongService = new Phong_DAO();
        this.hoaDonService = new HoaDon_DAO();
        this.hoaDon = hoaDon;
        hoaDon.setThoiGianKetThuc(new Date());
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        createForm();
    }

    private void createForm() {
        cmbPhong.addActionListener(this);
        btnDoiPhong.addActionListener(this);
        btnHuy.addActionListener(this);
        loadDataForm();
    }

    private void loadDataForm() {
        txtPhongCu.setText(hoaDon.getPhong().getTenPhong());
        txtGioDaHat.setText(hoaDon.getGioHat());
        txtTienPhong.setText(df.format(hoaDon.getDonGiaPhong()));
        dsPhong = phongService.getDSPhongByTrangThai(TrangThaiPhong.TRONG);
        if (dsPhong != null) {
            for (Phong phong : dsPhong) {
                ((DefaultComboBoxModel) cmbPhong.getModel()).addElement(phong.getTenPhong());
            }
            cmbPhong.setSelectedIndex(0);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(cmbPhong)) {
            Phong phong = dsPhong.get(cmbPhong.getSelectedIndex());
            txtLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
            txtGiaPhong.setText(df.format(phong.getLoaiPhong().getGiaPhong()));
        } else if (obj.equals(btnDoiPhong)) {
            try {
                Phong phong = dsPhong.get(cmbPhong.getSelectedIndex());
                Phong phongCu = hoaDon.getPhong();
                phongCu.setTrangThai(TrangThaiPhong.BAN);
                phongService.updatePhong(phongCu);
                hoaDon.setDonGiaPhongCu(hoaDon.getDonGiaPhongCu() + hoaDon.getDonGiaPhong());
                hoaDon.setPhong(phong);
                hoaDon.setThoiGianBatDau(new Date());
                hoaDon.setThoiGianKetThuc(new Date());
                if(hoaDonService.finishHoaDon(hoaDon)) {
                    JOptionPane.showMessageDialog(rootPane, "Đổi phòng thành công!");
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Đổi phòng thất bại!");
                }
                
                dispose();
            } catch (Exception ex) {
                Logger.getLogger(DL_DoiPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (obj.equals(btnHuy)) {
            dispose();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        cmbPhong = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtPhongCu = new javax.swing.JTextField();
        txtGioDaHat = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtTienPhong = new gui.swing.textfield.MyTextFieldPerUnit();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        txtGiaPhong = new gui.swing.textfield.MyTextFieldPerUnit();
        txtLoaiPhong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnDoiPhong = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        cmbPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Phòng chuyển đến");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Phòng cũ");

        txtPhongCu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtPhongCu.setEnabled(false);

        txtGioDaHat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtGioDaHat.setEnabled(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Giờ đã hát");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tiền phòng");

        txtTienPhong.setEnabled(false);
        txtTienPhong.setUnit("VND");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Giá phòng");

        txtGiaPhong.setEnabled(false);
        txtGiaPhong.setUnit("VND");

        txtLoaiPhong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtLoaiPhong.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Loại Phòng");

        btnDoiPhong.setBackground(new java.awt.Color(54, 88, 153));
        btnDoiPhong.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDoiPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnDoiPhong.setText("Đổi phòng");

        btnHuy.setBackground(new java.awt.Color(54, 88, 153));
        btnHuy.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtPhongCu, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtGioDaHat, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(27, 27, 27)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(182, 182, 182)
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnDoiPhong)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                            .addComponent(txtGiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbPhong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPhongCu, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGioDaHat, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnDoiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDoiPhong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<Object> cmbPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private gui.swing.textfield.MyTextFieldPerUnit txtGiaPhong;
    private javax.swing.JTextField txtGioDaHat;
    private javax.swing.JTextField txtLoaiPhong;
    private javax.swing.JTextField txtPhongCu;
    private gui.swing.textfield.MyTextFieldPerUnit txtTienPhong;
    // End of variables declaration//GEN-END:variables

}
