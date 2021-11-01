/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.toedter.calendar.JDateChooser;
import entity.KhachHang;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhieuDat;
import gui.swing.button.Button;
import gui.swing.table2.EventAction;
import gui.swing.table2.ModelAction;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
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
        initData();
        table.fixTable(sp);
        setSizeComlumnTable();
    }

    private void initData() {
//        , "1", "25/10/2021", "Đỗ Huy Hoàng", "Nguyễn Hưng", "Phòng A1", "25/10/2021", TrangThaiPhieuDat.DANG_DOI
        KhachHang khachHang = new KhachHang();
        khachHang.setTenKhachHang("Đỗ Huy Hoàng");
        Phong phong = new Phong();
        phong.setTenPhong("Phòng A1");
        EventAction event = new EventAction() {
            @Override
            public void delete(Object obj) {
                PhieuDatPhong pdp = (PhieuDatPhong) obj;
                JOptionPane.showMessageDialog(null, "Delete " + pdp.getMaPhieuDat());
            }

            @Override
            public void update(ModelAction action) {
                PhieuDatPhong pdp = (PhieuDatPhong) action.getObj();
                pdp.setNgayDat(Date.valueOf(table.getValueAt(table.getSelectedRow(), 5).toString()));
                pdp.setTienCoc(Double.valueOf(table.getValueAt(table.getSelectedRow(), 7).toString()));
                action.setObj(pdp);
                System.out.println(pdp);
            }
        };
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        table.addRow(new PhieuDatPhong("1", khachHang, phong, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now()), TrangThaiPhieuDat.DANG_DOI, 100000f).convertToRowTable(event));
        
    }

    private void buildGD_QLDatPhong() {
        buildPanelForm();
        setPreferredSize(new Dimension(getWidth(), 720));
    }
    
    private void buildPanelForm() {
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font16 = 16;
        int font14 = 14;
        int font12 = 12;
        Color colorBtn = new Color(184, 238, 241);
        Color colorLabel = new Color(47, 72, 210);

        /*
        Layout: 2 cột, 1 dòng
        cột 1, dòng 1: group thông tin phiếu đặt
        cột 2, dòng 1: group Tìm kiếm
         */
        pnlForm.setLayout(new MigLayout("", "3[center] [center] [center]3", "6[center]5"));

        /*Begin: group thông tin phiếu đặt*/
        JPanel pnlThongTinPhieu = new JPanel();
        pnlThongTinPhieu.setOpaque(false);

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
        pnlThongTinPhieu.setLayout(new MigLayout("", "10[center][center] 10 [center][center]10", "[][center]10[center]10[center] 20[center]"));
        pnlForm.add(pnlThongTinPhieu, "w 60%, h 235!");

        JLabel lblThongTinPhieu = new JLabel("Thông tin phiếu");
        lblThongTinPhieu.setFont(new Font(fontName, fontPlain, font16));
        lblThongTinPhieu.setForeground(colorLabel);
        pnlThongTinPhieu.add(lblThongTinPhieu, "span, w 100%, h 30!, wrap");

        // Mã phiếu
        JLabel lblMaPhieu = new JLabel("Mã phiếu:");
        lblMaPhieu.setFont(new Font(fontName, fontPlain, font14));

        pnlThongTinPhieu.add(lblMaPhieu, "align right");

        MyTextField txtMaNV = new MyTextField();
        txtMaNV.setFont(new Font(fontName, fontPlain, font14));
        txtMaNV.setBorderLine(true);
        pnlThongTinPhieu.add(txtMaNV, "w 80%, h 36!");

        //Tên khách hàng
        JLabel lblKhachHang = new JLabel("Khách hàng:");
        lblKhachHang.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblKhachHang, "align right");

        MyTextField txtTenNV = new MyTextField();
        txtTenNV.setFont(new Font(fontName, fontPlain, font14));
        txtTenNV.setBorderLine(true);
        pnlThongTinPhieu.add(txtTenNV, "w 80%, h 36!, wrap");

        //Ngày lập phiếu
        JLabel lblNgayLap = new JLabel("Ngày lập phiếu:");
        lblNgayLap.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblNgayLap, "align right");

        JDateChooser dcsNgayLap = new JDateChooser();
        dcsNgayLap.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(dcsNgayLap, "w 80%, h 36!");

        //Phòng
        JLabel lblPhong = new JLabel("Phòng:");
        lblPhong.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblPhong, "align right");

        MyTextField txtPhong = new MyTextField();
        txtPhong.setFont(new Font(fontName, fontPlain, font14));
        txtPhong.setBorderLine(true);
        pnlThongTinPhieu.add(txtPhong, "w 80%, h 36!, wrap");

        //Trạng thái
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblTrangThai, "align right");

        MyComboBox<String> cmbTrangThai = new MyComboBox<>();
        cmbTrangThai.setFont(new Font(fontName, fontPlain, font14));
        cmbTrangThai.setBorderLine(true);
        cmbTrangThai.setBorderRadius(10);
        cmbTrangThai.addItem("Tất cả");
        pnlThongTinPhieu.add(cmbTrangThai, "w 80%, h 36!");

        //Ngày đặt
        JLabel lblNgayDat = new JLabel("Ngày đặt:");
        lblNgayDat.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblNgayDat, "align right");

        JDateChooser dscNgayDat = new JDateChooser();
        dscNgayDat.setFont(new Font(fontName, fontPlain, font16));
        pnlThongTinPhieu.add(dscNgayDat, "w 80%, h 36!, wrap");

        /*Panel nút chức năng*/
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]20[]20[]0", "push[]push"));
        pnlThongTinPhieu.add(pnlButton, "span , w 100%, h 36!");

        // Nút Sửa
        Button btnSuaNV = new Button("Sửa");
        btnSuaNV.setFont(new Font(fontName, fontPlain, font14));
        btnSuaNV.setBackground(colorBtn);
        pnlButton.add(btnSuaNV, "w 100!, h 36!");

        // Nút Hủy đặt
        Button btnHuyDatPhieu = new Button("Hủy đặt");
        btnHuyDatPhieu.setFont(new Font(fontName, fontPlain, font14));
        btnHuyDatPhieu.setBackground(colorBtn);
        pnlButton.add(btnHuyDatPhieu, "w 100!, h 36!");

        // Nút Làm mới
        Button btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font14));
        btnLamMoi.setBackground(colorBtn);
        pnlButton.add(btnLamMoi, "w 100!, h 36!");
        /*End: group thông tin phiếu đặt*/

 /**/
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(20, 210));
        pnlForm.add(spr);


        /*Begin:group Tìm kiếm*/
        JPanel pnlTimKiemPhieu = new JPanel();
        pnlTimKiemPhieu.setOpaque(false);

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
        pnlTimKiemPhieu.setLayout(new MigLayout("", "[center][center][center]10", "[]5[]10[]10[] 20[]"));
        pnlForm.add(pnlTimKiemPhieu, "w 40%, h 235!");

        JLabel lblTimKiem = new JLabel("Tìm kiếm");
        lblTimKiem.setFont(new Font(fontName, fontPlain, font16));
        lblTimKiem.setForeground(colorLabel);
        pnlTimKiemPhieu.add(lblTimKiem, "w 100%, h 30!, wrap");

        // Ô tìm kiếm
        MyTextField txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiem.setBorderLine(true);
        pnlTimKiemPhieu.add(txtTimKiem, "span 2, w 100%, h 36!");

        //Cột cần tìm
        MyComboBox<String> cmbCotTK = new MyComboBox<>();
        cmbCotTK.setFont(new Font(fontName, fontPlain, font14));
        cmbCotTK.setBorderLine(true);
        cmbCotTK.addItem("text");
        cmbCotTK.setBorderRadius(10);
        pnlTimKiemPhieu.add(cmbCotTK, "w 100%, h 36!, wrap");

        //Trạng thái
        JLabel lblTrangThaiTK = new JLabel("Trạng thái");
        lblTrangThaiTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemPhieu.add(lblTrangThaiTK, "split 2");

        MyComboBox<String> cmbTrangThaiTK = new MyComboBox<>();
        cmbTrangThaiTK.setFont(new Font(fontName, fontPlain, font14));
        cmbTrangThaiTK.setBorderLine(true);
        cmbTrangThaiTK.addItem("Tất cả");
        cmbTrangThaiTK.setBorderRadius(10);
        pnlTimKiemPhieu.add(cmbTrangThaiTK, "span , w 100%, h 36!, wrap");

        // Tìm kiếm Ngày đặt
        JLabel lblNgayDaLabelTK = new JLabel("   Ngày đặt");
        lblNgayDaLabelTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemPhieu.add(lblNgayDaLabelTK, "split 3");

        JDateChooser dscNgayDatTK = new JDateChooser();
        dscNgayDatTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemPhieu.add(dscNgayDatTK, "span , w 60%, h 36!, wrap");

        //Button tìm kiếm
        Button btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setFont(new Font(fontName, fontPlain, font14));
        btnTimKiem.setBackground(colorBtn);
        pnlTimKiemPhieu.add(btnTimKiem, "span, align right, w 100!, h 36!");
        /*End: group Tìm kiếm*/
    }
    
    private void setSizeComlumnTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setPreferredWidth(100);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setMaxWidth(100);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setMinWidth(100);
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
        pnlForm = new gui.swing.panel.PanelShadow();
        pnlCenter = new gui.swing.panel.PanelShadow();
        jLabel1 = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        table = new gui.swing.table2.MyTable();

        setOpaque(false);

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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnlCenter.setShadowOpacity(0.3F);
        pnlCenter.setShadowSize(3);
        pnlCenter.setShadowType(gui.dropshadow.ShadowType.TOP);
        pnlCenter.setLayout(new java.awt.BorderLayout(10, 10));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Phiếu đặt phòng");
        pnlCenter.add(jLabel1, java.awt.BorderLayout.PAGE_START);

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã phiếu đặt", "Ngày lập phiếu", "Khách hàng", "Phòng", "Ngày đặt", "Trạng thái", "Tiền cọc", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sp.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(7).setResizable(false);
            table.getColumnModel().getColumn(8).setResizable(false);
        }

        pnlCenter.add(sp, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForm, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(lblMenu)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblMenu;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.panel.PanelShadow pnlForm;
    private javax.swing.JScrollPane sp;
    private gui.swing.table2.MyTable table;
    // End of variables declaration//GEN-END:variables
}
