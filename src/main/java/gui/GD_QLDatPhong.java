/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.toedter.calendar.JDateChooser;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhieuDat;
import gui.dialog.DL_DatPhong;
import gui.swing.graphics.ShadowType;
import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.table2.EventAction;
import gui.swing.model.ModelAction;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

import gui.swing.event.EventSelectedRow;
import gui.swing.textfield.MyTextFieldFlatlaf;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

/**
 *
 * @author Hao
 */
public class GD_QLDatPhong extends javax.swing.JPanel implements ActionListener, KeyListener{
    
    private PhieuDatPhong_DAO phieuDatPhong_Dao;
    private Phong_DAO phong_Dao;
    private List<PhieuDatPhong> dsPhieu = new ArrayList<PhieuDatPhong>();
    private MyTextFieldFlatlaf txtTimKiem;
    private Button btnHuyDatPhieu, btnLamMoi;
    private JDateChooser dcsNgayDatTK; 
    JComboBox<String> cmbTrangThaiTK;
    
    private EventSelectedRow eventSelectedRow;
    private EventAction event;
    private PanelShadow panelHidden;
    /**
     * Creates new form GD_QLDatPhong
     */
    public GD_QLDatPhong() {
        phieuDatPhong_Dao = new PhieuDatPhong_DAO();
        phong_Dao = new Phong_DAO();
        initComponents();
        buildGD_QLDatPhong();
    }
    
    public void addEvent(EventSelectedRow event) {
        this.eventSelectedRow = event;
    }
    
    private void buildGD_QLDatPhong() {
        phieuDatPhong_Dao = new PhieuDatPhong_DAO();
        phong_Dao = new Phong_DAO();
        createPanelForm();
//        setPreferredSize(new Dimension(getWidth(), 950));
        createTable();
        createPanelHidden();
        add(panelHidden);
    }
    
    private void createPanelHidden() {
        panelHidden = new PanelShadow();
        panelHidden.setShadowType(ShadowType.CENTER);
        panelHidden.setShadowOpacity(0.3f);
    }
    
    private void createPanelForm() {
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font14 = 14;
        Color colorBtn = new Color(184, 238, 241);

        pnlTop.setLayout(new MigLayout("fill", "push[center]10[center]20[center]10[]push", "60[center]20[center]20[]push"));
        

        txtTimKiem = new MyTextFieldFlatlaf();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiem.setHint("Nhập tên phòng/ khách hàng");
//        txtTimKiem.setBorderLine(true);
//        txtTimKiem.setBorderRadius(5);
        pnlTop.add(txtTimKiem, "w 20%, h 30!");

        cmbTrangThaiTK = new JComboBox<>();//cbModel
        cmbTrangThaiTK.setFont(new Font(fontName, fontPlain, font14));
//        cmbTrangThaiTK.setBorderLine(true);
        cmbTrangThaiTK.addItem("Lọc theo trạng thái");
//        cmbTrangThaiTK.setBorderRadius(10);
        pnlTop.add(cmbTrangThaiTK, "w 20%, h 30!");

        // Tìm kiếm Ngày đặt
        JLabel lblNgayDaLabelTK = new JLabel("Lọc theo ngày đặt");
        lblNgayDaLabelTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTop.add(lblNgayDaLabelTK);

        dcsNgayDatTK = new JDateChooser();
        dcsNgayDatTK.setFont(new Font(fontName, fontPlain, font14));
        dcsNgayDatTK.setOpaque(false);
        pnlTop.add(dcsNgayDatTK, "w 20%, h 30!, wrap");

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
        
        pnlTop.add(createPanelTitle(), "pos 0al 0al 100% n, h 40!");

        setOpaque(false);
        loadDataToCombobox();
        xuLyTimKiem();
    }
    
    private void xuLyTimKiem(){
        cmbTrangThaiTK.addActionListener(this);
        btnLamMoi.addActionListener(this);
        btnHuyDatPhieu.addActionListener(this);
        txtTimKiem.addKeyListener(this);
        dcsNgayDatTK.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent arg0) {
                int ngay = dcsNgayDatTK.getJCalendar().getDayChooser().getDay();
                int thang = dcsNgayDatTK.getJCalendar().getMonthChooser().getMonth();
                int nam = dcsNgayDatTK.getJCalendar().getYearChooser().getYear();
                String tuKhoa = txtTimKiem.getText().trim();
                String s=   cmbTrangThaiTK.getSelectedItem().toString();
                TrangThaiPhieuDat trangThai = TrangThaiPhieuDat.getTrangThaiPhieuDatByTrangThai(s);
                if(dcsNgayDatTK.getDate()!=null){
                    if(tuKhoa==null && cmbTrangThaiTK.getSelectedIndex()==0){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongNgay(ngay, thang+1, nam);
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }else if(cmbTrangThaiTK.getSelectedIndex()==0){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_Ngay(tuKhoa, nam, thang+1, ngay);
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }else if(tuKhoa==null){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai_Ngay(trangThai, nam, thang+1, ngay);
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }else{
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tuKhoa, trangThai, nam, thang+1, ngay);
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }
                }else{
                    if(tuKhoa==null && cmbTrangThaiTK.getSelectedIndex()==0){
                        dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }else if(tuKhoa==null){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai(trangThai);
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }else if(cmbTrangThaiTK.getSelectedIndex()==0){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName(tuKhoa);
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }else{
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_TrangThai(tuKhoa, trangThai);
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }
                }
            }
        });

        tblPhieuDatPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Nếu click chuột trái 2 lần
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    int row = tblPhieuDatPhong.getSelectedRow();
                    String maPhieu = tblPhieuDatPhong.getValueAt(row, 0).toString();
                    System.out.println(phieuDatPhong_Dao.getPhieuDatPhong(maPhieu));
                    eventSelectedRow.selectedRow(phieuDatPhong_Dao.getPhieuDatPhong(maPhieu));
                }
//                if(SwingUtilities.isRightMouseButton(e)){
//                    DL_DatPhong dl_DatPhong = new DL_DatPhong(this, true);
//                }
            }
        });
    }
    
    private void loadDataToCombobox() {
        for (TrangThaiPhieuDat trangThaiPhieuDat : TrangThaiPhieuDat.values()) {
            cmbTrangThaiTK.addItem(trangThaiPhieuDat.getTrangThai());
      } 
    }
    
    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Quản lý đặt phòng");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return  pnlTitle;
    }
    
    private void createTable() {
        tblPhieuDatPhong.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 16));
//        Object rows[][] = { {"","","","","","",""},{"","","","","","",""}, };
//        String columns[] = {"Mã phiếu đặt","Ngày lập phiếu","Khách hàng","Phòng", "Ngày đặt","Trạng thái","Tiền cọc"};
//        TableModel model = new DefaultTableModel(rows, columns){
//            boolean[] canEdit = new boolean [] {
//                false, false, false,false,true
//            }; 
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return false;
//            }
//        };
//        tblPhieuDatPhong.fixTable(sp);
        loadData();
    }
    
    public void xoaDuLieu(){
        DefaultTableModel df = (DefaultTableModel) tblPhieuDatPhong.getModel();
        df.setRowCount(0);
    }
    
    public void taiLaiDuLieu(List<PhieuDatPhong> dsPhieu){
         DecimalFormat dcf = new DecimalFormat("#,### VND");
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        for(PhieuDatPhong phieu : dsPhieu){
            tblPhieuDatPhong.addRow(new Object[]{JCheckBox.class,phieu.getMaPhieuDat(), fm.format(phieu.getNgayTao()),phieu.getKhachHang().getTenKhachHang(), phieu.getPhong().getTenPhong(), fm.format(phieu.getNgayDat()), phieu.getTrangThai(), dcf.format(phieu.getTienCoc())});
        }
    }
    
    private void loadData() {
//         event = new EventAction() {
//             KhachHang khachHang = new KhachHang();
//             Phong phong = new Phong();
//            @Override
//            public void delete(Object obj) {
//            }
//            @Override
//            public void update(ModelAction action) {
//                try {
//                    int row = tblPhieuDatPhong.getSelectedRow();
//                    PhieuDatPhong pdp = (PhieuDatPhong) action.getObj();
//                    KhachHang kh = new KhachHang();
//                    kh.setTenKhachHang(tblPhieuDatPhong.getValueAt(row, 2).toString());
//                    Phong p = new Phong();
//                    p.setTenPhong(tblPhieuDatPhong.getValueAt(row, 3).toString());
//                    String ngayDat = tblPhieuDatPhong.getValueAt(row, 3).toString();
//                    Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ngayDat);
//                    pdp.setNgayDat(date);
//                    pdp.setTienCoc(Double.parseDouble(tblPhieuDatPhong.getValueAt(row, 6).toString()));
//                    JOptionPane.showMessageDialog(null, "Delete " + pdp.getMaPhieuDat());
//                } catch (ParseException ex) {
//                    Logger.getLogger(GD_QLDatPhong.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//
//        };
        dsPhieu= phieuDatPhong_Dao.getDsPhieuDatPhong();
        DecimalFormat dcf = new DecimalFormat("#,###");
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        for(PhieuDatPhong phieu : dsPhieu){
            tblPhieuDatPhong.addRow(new Object[]{phieu.getMaPhieuDat(), fm.format(phieu.getNgayTao()),phieu.getKhachHang().getTenKhachHang(), phieu.getPhong().getTenPhong(), fm.format(phieu.getNgayDat()), phieu.getTrangThai(), dcf.format(phieu.getTienCoc())});
        }
    }

//    public void loadTrangThai(){
//        List<String> dsTrangThai = phieuDatPhong_Dao.getDSTrangThaiPhieu();
//        dsTrangThai.forEach((p)->{
//            cmbTrangThaiTK.addItem(p);
//        });
//    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        lblBang = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuDatPhong = new gui.swing.table2.MyTableFlatlaf();

        setOpaque(false);

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);

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
        pnlBottom.setLayout(new java.awt.BorderLayout(10, 10));

        lblBang.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblBang.setForeground(new java.awt.Color(4, 72, 210));
        lblBang.setText("Phiếu đặt phòng");
        pnlBottom.add(lblBang, java.awt.BorderLayout.PAGE_START);

        tblPhieuDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã phiếu đặt", "Ngày lập phiếu", "Khách hàng", "Phòng", "Ngày đặt", "Trạng thái", "Tiền cọc"
            }
        ));
        tblPhieuDatPhong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblPhieuDatPhong.setRowHeight(40);
        tblPhieuDatPhong.setSelectionBackground(new java.awt.Color(239, 244, 255));
        tblPhieuDatPhong.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblPhieuDatPhong.setShowGrid(true);
        tblPhieuDatPhong.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblPhieuDatPhong);
        if (tblPhieuDatPhong.getColumnModel().getColumnCount() > 0) {
            tblPhieuDatPhong.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblPhieuDatPhong.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblPhieuDatPhong.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblPhieuDatPhong.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblPhieuDatPhong.getColumnModel().getColumn(5).setPreferredWidth(100);
        }

        pnlBottom.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 951, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 367, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBang;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlTop;
    private gui.swing.table2.MyTableFlatlaf tblPhieuDatPhong;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
         Object obj = e.getSource();
        if(obj.equals(cmbTrangThaiTK)){
            int ngay = dcsNgayDatTK.getJCalendar().getDayChooser().getDay();
            int thang = dcsNgayDatTK.getJCalendar().getMonthChooser().getMonth();
            int nam = dcsNgayDatTK.getJCalendar().getYearChooser().getYear();
            String tuKhoa = txtTimKiem.getText().trim();
            String s=   cmbTrangThaiTK.getSelectedItem().toString();
            TrangThaiPhieuDat trangThai = TrangThaiPhieuDat.getTrangThaiPhieuDatByTrangThai(s);
            System.out.println(trangThai);
             if(!(cmbTrangThaiTK.getSelectedIndex()==0)){
                if(dcsNgayDatTK.getDate()==null && tuKhoa==null){
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai(trangThai);
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                }else if(dcsNgayDatTK.getDate()==null){
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_TrangThai(tuKhoa, trangThai);
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                }else if(tuKhoa==null){
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai_Ngay(trangThai, nam, thang+1, ngay);
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                }else{
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tuKhoa, trangThai, nam, thang+1, ngay);
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                }
            }else{
                 if(dcsNgayDatTK.getDate()==null && tuKhoa==null){
                    dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                 }else if(tuKhoa==null){
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongNgay(ngay, thang+1, nam);
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                 }else if(dcsNgayDatTK.getDate()==null){
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName(tuKhoa);
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                 }else{
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_Ngay(tuKhoa, nam, thang+1, ngay);
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                 }
             }
        }
        if (obj.equals(btnLamMoi)) {
            
            txtTimKiem.setText("");
            String tk = (String) cmbTrangThaiTK.getItemAt(0);
            cmbTrangThaiTK.setSelectedItem(tk);
            dcsNgayDatTK.setDate(null);
            dsPhieu= phieuDatPhong_Dao.getDsPhieuDatPhong();
            xoaDuLieu();
            taiLaiDuLieu(dsPhieu);
        }
        if(obj.equals(btnHuyDatPhieu)){
            int row = tblPhieuDatPhong.getSelectedRow();
            if(tblPhieuDatPhong.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(this,"Chọn phiếu bạn muốn hủy đặt.");
            }else{
                if(phieuDatPhong_Dao.capNhatTrangThaiPhieu(tblPhieuDatPhong.getValueAt(row, 0).toString())){
                    JOptionPane.showMessageDialog(this,"Bạn đã hủy thành công phiếu "+ tblPhieuDatPhong.getValueAt(row, 0));
                    dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                }else{
                    JOptionPane.showMessageDialog(this,"Quá trình hủy phiếu "+tblPhieuDatPhong.getValueAt(row, 0)+" thất bại.");
                }
            }
        }
    }
    
     @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object obj =   e.getSource();
      if(obj.equals(txtTimKiem)){
        int ngay = dcsNgayDatTK.getJCalendar().getDayChooser().getDay();
        int thang = dcsNgayDatTK.getJCalendar().getMonthChooser().getMonth();
        int nam = dcsNgayDatTK.getJCalendar().getYearChooser().getYear();
        String tuKhoa = txtTimKiem.getText().trim();
        String s=   cmbTrangThaiTK.getSelectedItem().toString();
        TrangThaiPhieuDat trangThai = TrangThaiPhieuDat.getTrangThaiPhieuDatByTrangThai(s);
        if(tuKhoa!=null){
            if(cmbTrangThaiTK.getSelectedIndex()==0 && dcsNgayDatTK.getDate()==null){
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName(tuKhoa);
                xoaDuLieu();
                taiLaiDuLieu(dsPhieu);
            }else if(cmbTrangThaiTK.getSelectedIndex()==0){
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_Ngay(tuKhoa, nam, thang+1, ngay);
                xoaDuLieu();
                taiLaiDuLieu(dsPhieu);
            }else if(dcsNgayDatTK.getDate()==null){
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_TrangThai(tuKhoa, trangThai);
                xoaDuLieu();
                taiLaiDuLieu(dsPhieu);
            }else{
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tuKhoa, trangThai, nam, thang+1, ngay);
                xoaDuLieu();
                taiLaiDuLieu(dsPhieu);
            }
        }else{
            if(cmbTrangThaiTK.getSelectedIndex()==0 && dcsNgayDatTK.getDate()==null){
                dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
                xoaDuLieu();
                taiLaiDuLieu(dsPhieu);
            }else if(cmbTrangThaiTK.getSelectedIndex()==0){
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongNgay(ngay, thang+1, nam);
                xoaDuLieu();
                taiLaiDuLieu(dsPhieu);
            }else if(dcsNgayDatTK.getDate()==null){
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai(trangThai);
                xoaDuLieu();
                taiLaiDuLieu(dsPhieu);
            }else{
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai_Ngay(trangThai, nam, thang+1, ngay);
                xoaDuLieu();
                taiLaiDuLieu(dsPhieu);
            }
        }
     }
    }
}
