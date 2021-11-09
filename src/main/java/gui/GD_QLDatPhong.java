/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.toedter.calendar.JDateChooser;
import entity.TrangThaiPhieuDat;
import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author NGUYENHUNG
 */
public class GD_QLDatPhong extends javax.swing.JPanel {
    
    private MyTextField txtTimKiem;
    private Button btnHuyDatPhieu, btnLamMoi;
    private JDateChooser dscNgayDatTK; 
    private DefaultComboBoxModel<TrangThaiPhieuDat> cbModel;
    /**
     * Creates new form GD_QLDatPhong
     */
    public GD_QLDatPhong() {
        initComponents();
        buildGD_QLDatPhong();
    }

    private void buildGD_QLDatPhong() {
        createPanelForm();
        setPreferredSize(new Dimension(getWidth(), 950));
        createTable();
    }
    
    private void createPanelForm() {
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font14 = 14;
        Color colorBtn = new Color(184, 238, 241);

        pnlTop.setLayout(new MigLayout("", "10[center] 20[center] 20[center]5[center] 25[center]10[center]10", "60[center]10[center]10"));
        pnlTop.add(createPanelTitle(), "span,pos 0al 0al 100% n, h 40!");

        txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiem.setHint("Nhập tên phòng/ khách hàng");
        txtTimKiem.setBorderLine(true);
        txtTimKiem.setBorderRadius(5);
        pnlTop.add(txtTimKiem, "w 25%, h 36!");

        cbModel = new DefaultComboBoxModel<TrangThaiPhieuDat>();
        MyComboBox<String> cmbTrangThaiTK = new MyComboBox<>(cbModel);
        cmbTrangThaiTK.setFont(new Font(fontName, fontPlain, font14));
        cmbTrangThaiTK.setBorderLine(true);
        cmbTrangThaiTK.addItem("Lọc theo trạng thái");
        cmbTrangThaiTK.setBorderRadius(10);
        pnlTop.add(cmbTrangThaiTK, "w 25%, h 36!");

        // Tìm kiếm Ngày đặt
        JLabel lblNgayDaLabelTK = new JLabel("Lọc theo ngày đặt");
        lblNgayDaLabelTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTop.add(lblNgayDaLabelTK);

        dscNgayDatTK = new JDateChooser();
        dscNgayDatTK.setFont(new Font(fontName, fontPlain, font14));
        dscNgayDatTK.setOpaque(false);
        pnlTop.add(dscNgayDatTK, "w 25%, h 36!");

        // Nút Làm mới
        btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font14));
        btnLamMoi.setBackground(colorBtn);
        btnLamMoi.setBorderRadius(5);
        btnLamMoi.setBorderline(true);
        pnlTop.add(btnLamMoi, "w 100!, h 36!");
        
        // Nút Hủy đặt
        btnHuyDatPhieu = new Button("Hủy đặt");
        btnHuyDatPhieu.setFont(new Font(fontName, fontPlain, font14));
        btnHuyDatPhieu.setBackground(colorBtn);
        btnHuyDatPhieu.setBorderline(true);
        btnHuyDatPhieu.setBorderRadius(5);
        pnlTop.add(btnHuyDatPhieu, "w 100!, h 36!");

        setOpaque(false);
        loadDataToCombobox();
    }
    
    private void loadDataToCombobox() {
        TrangThaiPhieuDat[] listTrangThaiPhieuDat = TrangThaiPhieuDat.values();
        for (TrangThaiPhieuDat trangThaiPhieuDat : listTrangThaiPhieuDat) {
            cbModel.addElement(trangThaiPhieuDat);
        }
    }
    
    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("QUẢN LÝ ĐẶT PHÒNG");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return  pnlTitle;
    }

    
    private void createTable() {
        tblPhieuDatPhong.fixTable(sp);
        loadData();
        setSizeComlumnTable();
    }
    
    private void loadData() {
        
    }
    
    private void setSizeComlumnTable() {
        tblPhieuDatPhong.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblPhieuDatPhong.getColumnModel().getColumn(0).setMinWidth(50);
        tblPhieuDatPhong.getColumnModel().getColumn(0).setMaxWidth(50);
        tblPhieuDatPhong.getColumnModel().getColumn(tblPhieuDatPhong.getColumnCount() - 1).setPreferredWidth(100);
        tblPhieuDatPhong.getColumnModel().getColumn(tblPhieuDatPhong.getColumnCount() - 1).setMaxWidth(100);
        tblPhieuDatPhong.getColumnModel().getColumn(tblPhieuDatPhong.getColumnCount() - 1).setMinWidth(100);
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        lblBang = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        tblPhieuDatPhong = new gui.swing.table2.MyTable();

        setOpaque(false);

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);
        pnlTop.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setShadowType(gui.dropshadow.ShadowType.TOP);
        pnlBottom.setLayout(new java.awt.BorderLayout(10, 10));

        lblBang.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblBang.setForeground(new java.awt.Color(4, 72, 210));
        lblBang.setText("Phiếu đặt phòng");
        pnlBottom.add(lblBang, java.awt.BorderLayout.PAGE_START);

        tblPhieuDatPhong.setModel(new javax.swing.table.DefaultTableModel(
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
        sp.setViewportView(tblPhieuDatPhong);
        if (tblPhieuDatPhong.getColumnModel().getColumnCount() > 0) {
            tblPhieuDatPhong.getColumnModel().getColumn(0).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(1).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(2).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(3).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(4).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(5).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(6).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(7).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(8).setResizable(false);
        }

        pnlBottom.add(sp, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 398, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBang;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane sp;
    private gui.swing.table2.MyTable tblPhieuDatPhong;
    // End of variables declaration//GEN-END:variables
}
