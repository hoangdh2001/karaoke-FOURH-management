/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.toedter.calendar.JDateChooser;
import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
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
        int font14 = 14;
        Color colorBtn = new Color(184, 238, 241);
        Color colorLabel = new Color(47, 72, 210);

        lblMenu.setFont(new Font(fontName, fontPlain, font16));

//        panelForm.setPreferredSize(new Dimension(1119, 341));
        panelForm.setLayout(new MigLayout("", "3[center] [] [center]3", "6[center]5"));

        /*Begin: group thông tin nhân viên*/
        JPanel pnlThongTinNV = new JPanel();
        pnlThongTinNV.setOpaque(false);
        pnlThongTinNV.setLayout(new MigLayout("", "10[center][center] 10 [center][center]10", "[][center]10[center]10[center]10[center]10[center] 20[center]"));
        panelForm.add(pnlThongTinNV, "w 60%, h 335!");
        
        JLabel lblThongTinNV = new JLabel("Thông tin nhân viên");
        lblThongTinNV.setFont(new Font(fontName, fontPlain, font16));
        lblThongTinNV.setForeground(colorLabel);
        pnlThongTinNV.add(lblThongTinNV, "span, w 100%, h 30!, wrap");

        // Mã nhân viên
        JLabel lblMaNV = new JLabel("Mã nhân viên:");
        lblMaNV.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblMaNV, "align right");

        MyTextField txtMaNV = new MyTextField();
        txtMaNV.setFont(new Font(fontName, fontPlain, font14));
        txtMaNV.setBorderLine(true);
        pnlThongTinNV.add(txtMaNV, "w 80%, h 36!");

        //Tên nhân viên
        JLabel lblTenNV = new JLabel("Tên nhân viên:");
        lblTenNV.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblTenNV, "align right");

        MyTextField txtTenNV = new MyTextField();
        txtTenNV.setFont(new Font(fontName, fontPlain, font14));
        txtTenNV.setBorderLine(true);
        pnlThongTinNV.add(txtTenNV, "w 80%, h 36!, wrap");

        //Giới tính
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblGioiTinh, "align right");

        MyComboBox<String> cmbGioiTinh = new MyComboBox<>();
        cmbGioiTinh.setFont(new Font(fontName, fontPlain, font14));
        cmbGioiTinh.setBorderLine(true);
        cmbGioiTinh.addItem("Nam");
        pnlThongTinNV.add(cmbGioiTinh, "w 80%, h 36!");

        //Ngày sinh
        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblNgaySinh, "align right");

        JDateChooser dscNgaySinh = new JDateChooser();
        dscNgaySinh.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(dscNgaySinh, "w 80%, h 36!, wrap");

        //Số điện thoại
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblSDT, "align right");

        MyTextField txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontPlain, font14));
        txtSDT.setBorderLine(true);
        pnlThongTinNV.add(txtSDT, "w 80%, h 36!");

        //Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblEmail, "align right");

        MyTextField txtEmail = new MyTextField();
        txtEmail.setFont(new Font(fontName, fontPlain, font14));
        txtEmail.setBorderLine(true);
        pnlThongTinNV.add(txtEmail, "w 80%, h 36!, wrap");

        //Địa chỉ
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblDiaChi, "align right");

        MyTextField txtDiaChi = new MyTextField();
        txtDiaChi.setFont(new Font(fontName, fontPlain, font14));
        txtDiaChi.setBorderLine(true);
        pnlThongTinNV.add(txtDiaChi, "w 80%, h 36!");

        //Loại nhân viên
        JLabel lblLoaiNV = new JLabel("Loại nhân viên:");
        lblLoaiNV.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblLoaiNV, "align right");

        MyComboBox<String> cmbLoaiNV = new MyComboBox<>();
        cmbLoaiNV.setFont(new Font(fontName, fontPlain, font14));
        cmbLoaiNV.setBorderLine(true);
        cmbLoaiNV.addItem("Nhân viên lễ tân");
        pnlThongTinNV.add(cmbLoaiNV, "w 80%, h 36!, wrap");

        //Ca làm
        JLabel lblCaLam = new JLabel("Ca làm:");
        lblCaLam.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinNV.add(lblCaLam, "align right");

        MyComboBox<String> cmbCaLam = new MyComboBox<>();
        cmbCaLam.setFont(new Font(fontName, fontPlain, font14));
        cmbCaLam.setBorderLine(true);
        cmbCaLam.addItem("Ca 1");
        pnlThongTinNV.add(cmbCaLam, "w 80%, h 36!, wrap");

        /*Panel nút chức năng*/
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]20[]20[]20[]0", "push[]push"));
        pnlThongTinNV.add(pnlButton, "span , w 100%, h 36!");

        // Nút Thêm
        Button btnThemNV = new Button("Thêm");
        btnThemNV.setFont(new Font(fontName, fontPlain, font14));
        btnThemNV.setBackground(colorBtn);
        pnlButton.add(btnThemNV, "w 100!, h 36!, growx");

        // Nút Xóa
        Button btnXoaNV = new Button("Xóa");
        btnXoaNV.setFont(new Font(fontName, fontPlain, font14));
        btnXoaNV.setBackground(colorBtn);
        pnlButton.add(btnXoaNV, "w 100!, h 36!");

        // Nút Sửa
        Button btnSuaNV = new Button("Sửa");
        btnSuaNV.setFont(new Font(fontName, fontPlain, font14));
        btnSuaNV.setBackground(colorBtn);
        pnlButton.add(btnSuaNV, "w 100!, h 36!");

        // Nút Làm mới
        Button btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font14));
        btnLamMoi.setBackground(colorBtn);
        pnlButton.add(btnLamMoi, "w 100!, h 36!");
        /*End: group thông tin nhân viên*/
        
        
        
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(2, 300));
        panelForm.add(spr);


 /*Begin: group tìm nhân viên*/
        JPanel pnlTimKiemNV = new JPanel();
        pnlTimKiemNV.setOpaque(false);
        pnlTimKiemNV.setLayout(new MigLayout("", "10[center][center]10", "[][]10[]10[]10[]10[]20[]"));
        panelForm.add(pnlTimKiemNV, "w 40%, h 335!");
        
        JLabel lblTimKiemNV = new JLabel("Tìm nhân viên");
        lblTimKiemNV.setFont(new Font(fontName, fontPlain, font16));
        lblTimKiemNV.setForeground(colorLabel);
        pnlTimKiemNV.add(lblTimKiemNV, "span, w 100%, h 30!, wrap");

        // Tìm kiếm
        MyTextField txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiem.setBorderLine(true);
        pnlTimKiemNV.add(txtTimKiem, "span, w 100%, h 36!, wrap");

        //Cột cần tìm kiếm
        MyComboBox<String> cmbCot = new MyComboBox<>();
        cmbCot.setFont(new Font(fontName, fontPlain, font14));
        cmbCot.setBorderLine(true);
        cmbCot.addItem("Chọn cột cần tìm");
        pnlTimKiemNV.add(cmbCot, "span, w 100%, h 36!, wrap");

        //Giới tính cần tìm
        JLabel lblGioiTinhTK = new JLabel("Giới tính:");
        lblGioiTinhTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemNV.add(lblGioiTinhTK, "align right");

        MyComboBox<String> cmbGioiTinhTK = new MyComboBox<>();
        cmbGioiTinhTK.setFont(new Font(fontName, fontPlain, font14));
        cmbGioiTinhTK.setBorderLine(true);
        cmbGioiTinhTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbGioiTinhTK, "w 80%,h 36!, wrap");

        //Loại nhân viên cầm tìm
        JLabel lblLoaiNVTK = new JLabel("Loại nhân viên:");
        lblLoaiNVTK.setFont(new Font(fontName, fontPlain, font14));

        pnlTimKiemNV.add(lblLoaiNVTK, "align right");

        MyComboBox<String> cmbLoaiNVTK = new MyComboBox<>();
        cmbLoaiNVTK.setFont(new Font(fontName, fontPlain, font14));
        cmbLoaiNVTK.setBorderLine(true);
        cmbLoaiNVTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbLoaiNVTK, "w 80%,h 36!, wrap");

        //Ca làm cần tìm
        JLabel lblCaLamTK = new JLabel("Ca làm:");
        lblCaLamTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemNV.add(lblCaLamTK, "align right");

        MyComboBox<String> cmbCaLamTK = new MyComboBox<>();
        cmbCaLamTK.setFont(new Font(fontName, fontPlain, font14));
        cmbCaLamTK.setBorderLine(true);
        cmbCaLamTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbCaLamTK, "w 80%,h 36!, wrap");

        //Button tìm kiếm
        Button btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setFont(new Font(fontName, fontPlain, font14));
        btnTimKiem.setBackground(colorBtn);
        pnlTimKiemNV.add(btnTimKiem, "span, align right, w 100!, h 36!");
        /* End: group tìm nhân viên*/

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
        panelForm = new gui.swing.panel.PanelShadow();
        panelShadow1 = new gui.swing.panel.PanelShadow();

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
            .addGap(0, 270, Short.MAX_VALUE)
        );

        panelShadow1.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow1.setShadowOpacity(0.3F);
        panelShadow1.setShadowSize(3);
        panelShadow1.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout panelShadow1Layout = new javax.swing.GroupLayout(panelShadow1);
        panelShadow1.setLayout(panelShadow1Layout);
        panelShadow1Layout.setHorizontalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelShadow1Layout.setVerticalGroup(
            panelShadow1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 309, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMenu)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelShadow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMenu;
    private gui.swing.panel.PanelShadow panelForm;
    private gui.swing.panel.PanelShadow panelShadow1;
    // End of variables declaration//GEN-END:variables
}
