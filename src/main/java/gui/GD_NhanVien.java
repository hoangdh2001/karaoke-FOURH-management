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
 * @author NGUYE
 */
public class GD_NhanVien extends javax.swing.JPanel {

    /**
     * Creates new form GD_NhanVien
     */
    public GD_NhanVien() {
        initComponents();
        buildGD();
    }

    public void buildGD() {
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font16 = 16;
        Color colorBtn = new Color(184, 238, 241);

        lblMenu.setFont(new Font(fontName, fontPlain, font16));

        panelForm.setPreferredSize(new Dimension(1119, 341));
        panelForm.setLayout(new MigLayout("", "3[center] 20 [center]3", "6[center]5"));

        /*Begin: group thông tin nhân viên*/
        JPanel pnlThongTinNV = new JPanel();
        pnlThongTinNV.setOpaque(false);
        pnlThongTinNV.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin nhân viên", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));
        pnlThongTinNV.setLayout(new MigLayout("", "10[center]10[center] 10 [center][center]10", "[center]10[center]10[center]10[center]10[center] 20[center]"));
        panelForm.add(pnlThongTinNV, "w 60%, h 335!");

        // Mã nhân viên
        JLabel lblMaNV = new JLabel("Mã nhân viên:");
        lblMaNV.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblMaNV, "align right");

        JTextField txtMaNV = new MyTextField();
        txtMaNV.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(txtMaNV, "w 80%, h 36!");

        //Tên nhân viên
        JLabel lblTenNV = new JLabel("Tên nhân viên:");
        lblTenNV.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblTenNV, "align right");

        JTextField txtTenNV = new MyTextField();
        txtTenNV.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(txtTenNV, "w 80%, h 36!, wrap");

        //Giới tính
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblGioiTinh, "align right");

        JComboBox<String> cmbGioiTinh = new JComboBox<>();
        cmbGioiTinh.setFont(new Font(fontName, fontPlain, font16));
        cmbGioiTinh.addItem("Nam");
        pnlThongTinNV.add(cmbGioiTinh, "w 80%, h 36!");

        //Ngày sinh
        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblNgaySinh, "align right");

        JTextField txtNgaySinh = new MyTextField();
        txtNgaySinh.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(txtNgaySinh, "w 80%, h 36!, wrap");

        //Số điện thoại
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblSDT, "align right");

        JTextField txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(txtSDT, "w 80%, h 36!");

        //Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblEmail, "align right");

        JTextField txtEmail = new MyTextField();
        txtEmail.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(txtEmail, "w 80%, h 36!, wrap");

        //Địa chỉ
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblDiaChi, "align right");

        JTextField txtDiaChi = new MyTextField();
        txtDiaChi.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(txtDiaChi, "w 80%, h 36!");

        //Loại nhân viên
        JLabel lblLoaiNV = new JLabel("Loại nhân viên:");
        lblLoaiNV.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblLoaiNV, "align right");

        JComboBox<String> cmbLoaiNV = new JComboBox<>();
        cmbLoaiNV.setFont(new Font(fontName, fontPlain, font16));
        cmbLoaiNV.addItem("Nhân viên lễ tân");
        pnlThongTinNV.add(cmbLoaiNV, "w 80%, h 36!, wrap");

        //Ca làm
        JLabel lblCaLam = new JLabel("Ca làm:");
        lblCaLam.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinNV.add(lblCaLam, "align right");

        JComboBox<String> cmbCaLam = new JComboBox<>();
        cmbCaLam.setFont(new Font(fontName, fontPlain, font16));
        cmbCaLam.addItem("Ca 1");
        pnlThongTinNV.add(cmbCaLam, "w 80%, h 36!, wrap");

        /*Panel nút chức năng*/
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]20[]20[]20[]0", "push[]push"));
        pnlThongTinNV.add(pnlButton, "span , w 100%, h 36!");

        // Nút Thêm
        Button btnThemNV = new Button("Thêm");
        btnThemNV.setFont(new Font(fontName, fontPlain, font16));
        btnThemNV.setBackground(colorBtn);
        pnlButton.add(btnThemNV, "w 100!, h 36!, growx");

        // Nút Xóa
        Button btnXoaNV = new Button("Xóa");
        btnXoaNV.setFont(new Font(fontName, fontPlain, font16));
        btnXoaNV.setBackground(colorBtn);
        pnlButton.add(btnXoaNV, "w 100!, h 36!");

        // Nút Sửa
        Button btnSuaNV = new Button("Sửa");
        btnSuaNV.setFont(new Font(fontName, fontPlain, font16));
        btnSuaNV.setBackground(colorBtn);
        pnlButton.add(btnSuaNV, "w 100!, h 36!");

        // Nút Làm mới
        Button btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font16));
        btnLamMoi.setBackground(colorBtn);
        pnlButton.add(btnLamMoi, "w 100!, h 36!");
        /*End: group thông tin nhân viên*/


 /*Begin: group tìm nhân viên*/
        JPanel pnlTimKiemNV = new JPanel();
        pnlTimKiemNV.setOpaque(false);
        pnlTimKiemNV.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin nhân viên", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));
        pnlTimKiemNV.setLayout(new MigLayout("", "[center][center]", "[]10[]10[]10[]10[]20[]"));
        panelForm.add(pnlTimKiemNV, "w 40%, h 335!");

        // Tìm kiếm
        JTextField txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font16));
        pnlTimKiemNV.add(txtTimKiem, "span, w 100%, h 36!, wrap");

        //Cột cần tìm kiếm
        JComboBox<String> cmbCot = new JComboBox<>();
        cmbCot.setFont(new Font(fontName, fontPlain, font16));
        cmbCot.addItem("Chọn cột cần tìm");
        pnlTimKiemNV.add(cmbCot, "span, w 100%, h 36!, wrap");

        //Giới tính cần tìm
        JLabel lblGioiTinhTK = new JLabel("Giới tính:");
        lblGioiTinhTK.setFont(new Font(fontName, fontPlain, font16));
        pnlTimKiemNV.add(lblGioiTinhTK, "align right");

        JComboBox<String> cmbGioiTinhTK = new JComboBox<>();
        cmbGioiTinhTK.setFont(new Font(fontName, fontPlain, font16));
        cmbGioiTinhTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbGioiTinhTK, "w 80%,h 36!, wrap");

        //Loại nhân viên cầm tìm
        JLabel lblLoaiNVTK = new JLabel("Loại nhân viên:");
        lblLoaiNVTK.setFont(new Font(fontName, fontPlain, font16));

        pnlTimKiemNV.add(lblLoaiNVTK, "align right");

        JComboBox<String> cmbLoaiNVTK = new JComboBox<>();
        cmbLoaiNVTK.setFont(new Font(fontName, fontPlain, font16));
        cmbLoaiNVTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbLoaiNVTK, "w 80%,h 36!, wrap");

        //Ca làm cần tìm
        JLabel lblCaLamTK = new JLabel("Ca làm:");
        lblCaLamTK.setFont(new Font(fontName, fontPlain, font16));
        pnlTimKiemNV.add(lblCaLamTK, "align right");

        JComboBox<String> cmbCaLamTK = new JComboBox<>();
        cmbCaLamTK.setFont(new Font(fontName, fontPlain, font16));
        cmbCaLamTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbCaLamTK, "w 80%,h 36!, wrap");

        //Button tìm kiếm
        Button btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setFont(new Font(fontName, fontPlain, font16));
        btnTimKiem.setBackground(colorBtn);
        pnlTimKiemNV.add(btnTimKiem, "span, align right, w 100!, h 36!");
        /* End: group tìm nhân viên*/

 /*Begin: group danh sách nhân viên*/
        panelCenter.setLayout(new MigLayout("", "[]", "[]"));

        JPanel subPanelCenter = new JPanel();
        subPanelCenter.setOpaque(false);
        subPanelCenter.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2), "Danh sách nhân viên", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));

        panelCenter.add(subPanelCenter, "w 100%, h 1100");
        /*End: group danh sách nhân viên*/

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
        panelForm = new gui.panel.PanelShadow();
        panelCenter = new gui.component.PanelMap();

        setPreferredSize(new java.awt.Dimension(1119, 620));

        lblMenu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(4, 72, 210));
        lblMenu.setText("Quản lý nhân viên");

        panelForm.setBackground(new java.awt.Color(255, 255, 255));
        panelForm.setShadowOpacity(0.3F);
        panelForm.setShadowSize(3);
        panelForm.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        panelCenter.setBackground(new java.awt.Color(255, 255, 255));
        panelCenter.setShadowOpacity(0.3F);
        panelCenter.setShadowSize(3);
        panelCenter.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout panelCenterLayout = new javax.swing.GroupLayout(panelCenter);
        panelCenter.setLayout(panelCenterLayout);
        panelCenterLayout.setHorizontalGroup(
            panelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCenterLayout.setVerticalGroup(
            panelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMenu)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMenu;
    private gui.component.PanelMap panelCenter;
    private gui.panel.PanelShadow panelForm;
    // End of variables declaration//GEN-END:variables
}
