/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.swing.button.Button;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author NGUYENHUNG
 */
public class GD_QLDatPhong extends javax.swing.JPanel {

    /**
     * Creates new form GD_QLDatPhong
     */
    public GD_QLDatPhong() {
        initComponents();
        buildGD_QLDatPhong();
    }

    public void buildGD_QLDatPhong() {
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font16 = 16;
        Color colorBtn = new Color(184, 238, 241);

        lblMenu.setFont(new Font(fontName, fontPlain, font16));

        /*
        Chiều cao pnlForm là 241, chiều cao pnlThongTinPhieu(có borderTitle) là 235
        -> chênh lệch là 6(border shadow)
        Tương tự cho pnlTimKiemPhieu
         */
        pnlForm.setPreferredSize(new Dimension(1119, 241));
        /*
        Layout: 2 cột, 1 dòng
        cột 1, dòng 1: group thông tin phiếu đặt
        cột 2, dòng 1: group Tìm kiếm
         */
        pnlForm.setLayout(new MigLayout("", "3[center] 20 [center]3", "6[center]5"));

        /*Begin: group thông tin phiếu đặt*/
        JPanel pnlThongTinPhieu = new JPanel();
        pnlThongTinPhieu.setOpaque(false);
        pnlThongTinPhieu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin phiếu đặt", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));
        /*
        Layout: 4 cột, 4 dòng
        cột 1+2, dòng 1: Mã phiếu
        cột 3+4, dòng 1: Tên khách hàng
        
        cột 1+2, dòng 2: Ngày lập phiếu
        cột 3+4, dòng 2: Phòng
        
        cột 1+2, dòng 3: Trạng thái
        cột 3+4, dòng 3: Ngày đặt
        
        cột 1+2+3+4, dòng 4: Panel nút chức năng        
         */
        pnlThongTinPhieu.setLayout(new MigLayout("", "10[center][center] 10 [center][center]10", "[center]10[center]10[center] 20[center]"));
        pnlForm.add(pnlThongTinPhieu, "w 60%, h 235!");

        // Mã phiếu
        JLabel lblMaPhieu = new JLabel("Mã phiếu:");
        lblMaPhieu.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(lblMaPhieu, "align right");

        JTextField txtMaNV = new MyTextField();
        txtMaNV.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(txtMaNV, "w 80%, h 36!");

        //Tên khách hàng
        JLabel lblKhachHang = new JLabel("Khách hàng:");
        lblKhachHang.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(lblKhachHang, "align right");

        JTextField txtTenNV = new MyTextField();
        txtTenNV.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(txtTenNV, "w 80%, h 36!, wrap");

        //Ngày lập phiếu
        JLabel lblNgayLap = new JLabel("Ngày lập phiếu:");
        lblNgayLap.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(lblNgayLap, "align right");

        JTextField txtNgayLap = new MyTextField();
        txtNgayLap.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(txtNgayLap, "w 80%, h 36!");

        //Phòng
        JLabel lblPhong = new JLabel("Phòng:");
        lblPhong.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(lblPhong, "align right");

        JTextField txtPhong = new MyTextField();
        txtPhong.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(txtPhong, "w 80%, h 36!, wrap");

        //Trạng thái
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(lblTrangThai, "align right");

        JComboBox<String> cmbTrangThai = new JComboBox<>();
        cmbTrangThai.setFont(new Font(fontName, fontPlain, font16));
        cmbTrangThai.addItem("Tất cả");
        pnlThongTinPhieu.add(cmbTrangThai, "w 80%, h 36!");

        //Ngày đặt
        JLabel lblNgayDat = new JLabel("Ngày đặt:");
        lblNgayDat.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(lblNgayDat, "align right");

        JTextField txtNgayDat = new MyTextField();
        txtNgayDat.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(txtNgayDat, "w 80%, h 36!, wrap");

        /*Panel nút chức năng*/
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]20[]20[]0", "push[]push"));
        pnlThongTinPhieu.add(pnlButton, "span , w 100%, h 36!");

        // Nút Sửa
        Button btnSuaNV = new Button("Sửa");
        btnSuaNV.setFont(new Font(fontName, fontPlain, font16));
        btnSuaNV.setBackground(colorBtn);
        pnlButton.add(btnSuaNV, "w 100!, h 36!");

        // Nút Hủy đặt
        Button btnHuyDatPhieu = new Button("Hủy đặt");
        btnHuyDatPhieu.setFont(new Font(fontName, fontPlain, font16));
        btnHuyDatPhieu.setBackground(colorBtn);
        pnlButton.add(btnHuyDatPhieu, "w 100!, h 36!");

        // Nút Làm mới
        Button btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font16));
        btnLamMoi.setBackground(colorBtn);
        pnlButton.add(btnLamMoi, "w 100!, h 36!");
        /*End: group thông tin phiếu đặt*/

 /*Begin:group Tìm kiếm*/
        JPanel pnlTimKiemPhieu = new JPanel();
        pnlTimKiemPhieu.setOpaque(false);
        pnlTimKiemPhieu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Tìm kiếm", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));
        /*
        Layout: 3 cột, 4 dòng
        cột 1+2, dòng 1: Ô tìm kiếm
        cột 3,   dòng 1: Cột cần tìm
        
        cột 1+2, dòng 2: Trạng thái
        cột 3, dòng 2: (trống)
        
        cột 1+2, dòng 3: Tìm kiếm Ngày đặt
        cột 3, dòng 3: (trống)
        
        cột 1+2+3, dòng 4: Nút tìm kiếm
         */
        pnlTimKiemPhieu.setLayout(new MigLayout("", "[center][center][center]", "[]10[]10[] 20[]"));
        pnlForm.add(pnlTimKiemPhieu, "w 40%, h 235!");

        // Ô tìm kiếm
        JTextField txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font16));
        pnlTimKiemPhieu.add(txtTimKiem, "span 2, w 100%, h 36!");

        //Cột cần tìm
        JComboBox<String> cmbCotTK = new JComboBox<>();
        cmbCotTK.setFont(new Font(fontName, fontPlain, font16));
        cmbCotTK.addItem("text");
        pnlTimKiemPhieu.add(cmbCotTK, "w 100%, h 36!, wrap");

        //Trạng thái
        JLabel lblTrangThaiTK = new JLabel("Trạng thái:");
        lblTrangThaiTK.setFont(new Font(fontName, fontPlain, font16));
        pnlTimKiemPhieu.add(lblTrangThaiTK, "align right");

        JComboBox<String> cmbTrangThaiTK = new JComboBox<>();
        cmbTrangThaiTK.setFont(new Font(fontName, fontPlain, font16));
        cmbTrangThaiTK.addItem("Tất cả");
        pnlTimKiemPhieu.add(cmbTrangThaiTK, "align left,w 100%, h 36!, wrap");

        // Tìm kiếm Ngày đặt
        JLabel lblNgayDaLabelTK = new JLabel("Ngày đặt");
        lblNgayDaLabelTK.setFont(new Font(fontName, fontPlain, font16));
        pnlTimKiemPhieu.add(lblNgayDaLabelTK, "align right");

        JTextField txtNgayDatTK = new MyTextField();
        txtNgayDatTK.setFont(new Font(fontName, fontPlain, font16));
        pnlTimKiemPhieu.add(txtNgayDatTK, "w 60%, h 36!, wrap");

        //Button tìm kiếm
        Button btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setFont(new Font(fontName, fontPlain, font16));
        btnTimKiem.setBackground(colorBtn);
        pnlTimKiemPhieu.add(btnTimKiem, "span, align right, w 100!, h 36!");
        /*End: group Tìm kiếm*/
        
            /*Begin: group danh sách phiếu đặt phòng*/
        pnlCenter.setLayout(new MigLayout("", "[]", "[]"));

        JPanel subPanelCenter = new JPanel();
        subPanelCenter.setOpaque(false);
        subPanelCenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2), "Danh sách phiếu đặt phòng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));

        pnlCenter.add(subPanelCenter, "w 100%, h 1200");
        /*End: group danh sách phiếu đặt phòng*/

        setPreferredSize(new Dimension(getWidth(), 1500));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMenu = new javax.swing.JLabel();
        pnlForm = new gui.panel.PanelShadow();
        pnlCenter = new gui.component.PanelMap();

        lblMenu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(4, 72, 210));
        lblMenu.setText("Quản lý đặt phòng");

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setShadowOpacity(0.3F);
        pnlForm.setShadowSize(3);
        pnlForm.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setShadowOpacity(0.3F);
        pnlCenter.setShadowSize(3);
        pnlCenter.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMenu)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMenu;
    private gui.component.PanelMap pnlCenter;
    private gui.panel.PanelShadow pnlForm;
    // End of variables declaration//GEN-END:variables
}
