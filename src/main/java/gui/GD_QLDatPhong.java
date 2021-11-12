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
import gui.swing.button.Button;
import gui.swing.table2.EventAction;
import gui.swing.table2.ModelAction;
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
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author NGUYENHUNG
 */
public class GD_QLDatPhong extends javax.swing.JPanel implements  ActionListener, KeyListener{
    private PhieuDatPhong_DAO phieuDatPhong_Dao;
    private Phong_DAO phong_Dao;
    private MyComboBox<String> cmbTrangThai,cmbTrangThaiTK;
    private MyTextField txtTimKiem, txtPhong, txtKhachHang, txtMaPhieu , txtNgayLap, txtNgayDat;
    private JDateChooser dcsNgayDatTK; 
    private Button btnLamMoi, btnHuyDatPhieu, btnTimKiemNgayDat; 
    private EventAction event;
    
    /**
     * Creates new form GD_QLDatPhong
     */
    public GD_QLDatPhong() {
        phieuDatPhong_Dao = new PhieuDatPhong_DAO();
        phong_Dao = new Phong_DAO();
        initComponents();
        buildGD_QLDatPhong();
        loadTrangThai();
        initData();
        table.fixTable(sp);
        setSizeComlumnTable();
    }

    private void initData() {
        KhachHang khachHang = new KhachHang();
        khachHang.setTenKhachHang("Đỗ Huy Hoàng");
        Phong phong = new Phong();
        phong.setTenPhong("Phòng A1");
        event = new EventAction() {
            @Override
            public void delete(Object obj) {
                
            }

            @Override
            public void update(ModelAction action) {
                try {
                    int row = table.getSelectedRow();
                    PhieuDatPhong pdp = (PhieuDatPhong) action.getObj();
                    KhachHang kh = new KhachHang();
                    kh.setTenKhachHang(table.getValueAt(row, 3).toString());
                    Phong p = new Phong();
                    p.setTenPhong(table.getValueAt(row, 4).toString());
                    String ngayDat = table.getValueAt(row, 4).toString();
                    Date date = (Date) new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(ngayDat);
                    //pdp.set
                    pdp.setNgayDat(date);
                    pdp.setTienCoc(Double.parseDouble(table.getValueAt(row, 7).toString()));
                    JOptionPane.showMessageDialog(null, "Delete " + pdp.getMaPhieuDat());
                } catch (ParseException ex) {
                    Logger.getLogger(GD_QLDatPhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        };
        List<PhieuDatPhong> dsPhieu = null;
        dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
        
        dsPhieu.forEach((phieuDatPhong) -> {
            table.addRow(phieuDatPhong.convertToRowTable(event));
        });
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
        pnlForm.setLayout(new MigLayout("", "3[center] [center]3", "40[center] 5"));
        pnlForm.add(createPanelTitle(), "span,pos 0al 0al 100% n, h 40!");

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

        txtMaPhieu = new MyTextField();
        txtMaPhieu.setFont(new Font(fontName, fontPlain, font14));
        txtMaPhieu.setBorderLine(true);
        txtMaPhieu.setBorderRadius(5);
        pnlThongTinPhieu.add(txtMaPhieu, "w 80%, h 36!");

        //Tên khách hàng
        JLabel lblKhachHang = new JLabel("Khách hàng:");
        lblKhachHang.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblKhachHang, "align right");

        txtKhachHang = new MyTextField();
        txtKhachHang.setFont(new Font(fontName, fontPlain, font14));
        txtKhachHang.setBorderLine(true);
        txtKhachHang.setBorderRadius(5);
        pnlThongTinPhieu.add(txtKhachHang, "w 80%, h 36!, wrap");

        //Ngày lập phiếu
        JLabel lblNgayLap = new JLabel("Ngày lập phiếu:");
        lblNgayLap.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblNgayLap, "align right");

        
        txtNgayLap = new MyTextField();
        txtNgayLap.setFont(new Font(fontName, fontPlain, font14));
        txtNgayLap.setBorderLine(true);
        txtNgayLap.setBorderRadius(5);
        pnlThongTinPhieu.add(txtNgayLap, "w 80%, h 36!");

        //Phòng
        JLabel lblPhong = new JLabel("Phòng:");
        lblPhong.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblPhong, "align right");

        txtPhong = new MyTextField();
        txtPhong.setFont(new Font(fontName, fontPlain, font14));
        txtPhong.setBorderLine(true);
        txtPhong.setBorderRadius(5);
        pnlThongTinPhieu.add(txtPhong, "w 80%, h 36!, wrap");

        //Trạng thái
        JLabel lblTrangThai = new JLabel("Trạng thái:");
        lblTrangThai.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblTrangThai, "align right");

        cmbTrangThai = new MyComboBox<>();
        cmbTrangThai.setFont(new Font(fontName, fontPlain, font14));
        cmbTrangThai.setBorderLine(true);
        cmbTrangThai.setBorderRadius(10);
        //cmbTrangThai.addItem("Tất cả");
        pnlThongTinPhieu.add(cmbTrangThai, "w 80%, h 36!");

        //Ngày đặt
        JLabel lblNgayDat = new JLabel("Ngày đặt:");
        lblNgayDat.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTinPhieu.add(lblNgayDat, "align right");

        
        txtNgayDat = new MyTextField();
        txtNgayDat.setFont(new Font(fontName, fontPlain, font14));
        txtNgayDat.setBorderLine(true);
        txtNgayDat.setBorderRadius(5);
        pnlThongTinPhieu.add(txtNgayDat, "w 80%, h 36!, wrap");

        /*Panel nút chức năng*/
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]0", "push[]push"));
        pnlThongTinPhieu.add(pnlButton, "span , w 100%, h 36!");

        // Nút Hủy đặt
        btnHuyDatPhieu = new Button("Hủy đặt");
        btnHuyDatPhieu.setFont(new Font(fontName, fontPlain, font14));
        btnHuyDatPhieu.setBackground(colorBtn);
        btnHuyDatPhieu.setBorderline(true);
        btnHuyDatPhieu.setBorderRadius(5);
        pnlButton.add(btnHuyDatPhieu, "w 100!, h 36!");

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
        txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiem.setBorderLine(true);
        txtTimKiem.setHint("Tên phòng/khách hàng");
        txtTimKiem.setBorderRadius(5);
        pnlTimKiemPhieu.add(txtTimKiem, "span 2, w 100%, h 36!, wrap");

        cmbTrangThaiTK = new MyComboBox<>();
        cmbTrangThaiTK.setFont(new Font(fontName, fontPlain, font14));
        cmbTrangThaiTK.setBorderLine(true);
        cmbTrangThaiTK.addItem("Trạng thái phòng");
        cmbTrangThaiTK.setBorderRadius(10);
        pnlTimKiemPhieu.add(cmbTrangThaiTK, "span 2, w 100%, h 36!, wrap");

        dcsNgayDatTK = new JDateChooser();
        dcsNgayDatTK.setFont(new Font(fontName, fontPlain, font14));
        dcsNgayDatTK.setOpaque(false);
        dcsNgayDatTK.setDateFormatString("yyyy-MM-dd");
        pnlTimKiemPhieu.add(dcsNgayDatTK, "span 2, w 100%, h 36!, wrap");
//       // Nút Tìm theo ngày đặt
//        btnTimKiemNgayDat = new Button("Ngày Đặt");
//        btnTimKiemNgayDat.setFont(new Font(fontName, fontPlain, font14));
//        btnTimKiemNgayDat.setBackground(colorBtn);
//        btnTimKiemNgayDat.setBorderline(true);
//        btnTimKiemNgayDat.setBorderRadius(5);
//        pnlTimKiemPhieu.add(btnTimKiemNgayDat, "w 100!, h 36!, wrap");

        /*Panel nút chức năng*/
        JPanel pnlButtonTim = new JPanel();
        pnlButtonTim.setOpaque(false);
        pnlButtonTim.setLayout(new MigLayout("", "push[]0", "push[]push"));
        pnlTimKiemPhieu.add(pnlButtonTim, "span , w 100%, h 36!");
         // Nút Làm mới
        btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font14));
        btnLamMoi.setBackground(colorBtn);
        btnLamMoi.setBorderline(true);
        btnLamMoi.setBorderRadius(5);
        pnlButtonTim.add(btnLamMoi, "span, align right,w 100!, h 36!");
        //pnlTimKiemPhieu.add(btnTimKiem, "span, align right, w 100!, h 36!");
        /*End: group Tìm kiếm*/
        
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
                String trangThai = cmbTrangThaiTK.getSelectedItem().toString();
                List<PhieuDatPhong> dsPhieu = null;
                if(dcsNgayDatTK.getDate()!=null){
                    if(tuKhoa==null && cmbTrangThaiTK.getSelectedIndex()==0){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongNgay(ngay, thang+1, nam);
                        taiLaiDuLieu(dsPhieu);
                    }else if(cmbTrangThaiTK.getSelectedIndex()==0){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_Ngay(tuKhoa, nam, thang+1, ngay);
                        taiLaiDuLieu(dsPhieu);
                    }else if(tuKhoa==null){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai_Ngay(trangThai, nam, thang+1, ngay);
                        taiLaiDuLieu(dsPhieu);
                    }else{
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tuKhoa, trangThai, nam, thang+1, ngay);
                        taiLaiDuLieu(dsPhieu);
                    }
                }else{
                    if(tuKhoa==null && cmbTrangThaiTK.getSelectedIndex()==0){
                        dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
                        taiLaiDuLieu(dsPhieu);
                    }else if(tuKhoa==null){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai(trangThai);
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    }else if(cmbTrangThaiTK.getSelectedIndex()==0){
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName(tuKhoa);
                        taiLaiDuLieu(dsPhieu);
                    }else{
                        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_TrangThai(tuKhoa, trangThai);
                        taiLaiDuLieu(dsPhieu);
                    }
                }
            }

          
        });
        
       


        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                txtMaPhieu.setText(table.getValueAt(row, 1).toString());
                txtKhachHang.setText(table.getValueAt(row, 3).toString());
                txtPhong.setText(table.getValueAt(row, 4).toString());
                
                cmbTrangThai.setSelectedItem(table.getValueAt(row, 6).toString());
                 
                String ngayLap = table.getValueAt(row, 2).toString();
                txtNgayLap.setText(ngayLap);
                
                String ngayDat = table.getValueAt(row, 5).toString();
                txtNgayDat.setText(ngayDat);
            }
       });
        
        
    }
    
    private void setSizeComlumnTable() {
        table.getColumnModel().getColumn(0).setPreferredWidth(50);
        table.getColumnModel().getColumn(0).setMinWidth(50);
        table.getColumnModel().getColumn(0).setMaxWidth(50);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setPreferredWidth(100);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setMaxWidth(100);
        table.getColumnModel().getColumn(table.getColumnCount() - 1).setMinWidth(100);
    }
   
    
    public void loadTrangThai(){
        List<String> dsTrangThai = phieuDatPhong_Dao.getDSTrangThaiPhieu();
        dsTrangThai.forEach((p)->{
            cmbTrangThai.addItem(p);
            cmbTrangThaiTK.addItem(p);
        });
    }
    
    
    public void xoaDuLieu(){
        DefaultTableModel df = (DefaultTableModel) table.getModel();
        df.setRowCount(0);
    }
    
    
    public void taiLaiDuLieu(List<PhieuDatPhong> dsPhieu){
        xoaDuLieu();
        if(dsPhieu != null){
            dsPhieu.forEach((phieu) -> {
                 table.addRow(phieu.convertToRowTable(event));
                 System.out.println(phieu);
                 System.out.println("Here");
            });
            
        }else if(dsPhieu.size() == 0){
            xoaDuLieu();
            System.out.println("null cmmr");
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlForm = new gui.swing.panel.PanelShadow();
        pnlCenter = new gui.swing.panel.PanelShadow();
        jLabel1 = new javax.swing.JLabel();
        sp = new javax.swing.JScrollPane();
        table = new gui.swing.table2.MyTable();

        setOpaque(false);

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
            .addGap(0, 135, Short.MAX_VALUE)
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
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 1103, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.panel.PanelShadow pnlForm;
    private javax.swing.JScrollPane sp;
    private gui.swing.table2.MyTable table;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(cmbTrangThaiTK)){
            int ngay = dcsNgayDatTK.getJCalendar().getDayChooser().getDay();
            int thang = dcsNgayDatTK.getJCalendar().getMonthChooser().getMonth();
            int nam = dcsNgayDatTK.getJCalendar().getYearChooser().getYear();
            String tuKhoa = txtTimKiem.getText().trim();
            String trangThai = cmbTrangThaiTK.getSelectedItem().toString();
            List<PhieuDatPhong> dsPhieu = null;
             if(!(cmbTrangThaiTK.getSelectedIndex()==0)){
                if(dcsNgayDatTK.getDate()==null && tuKhoa==null){
                    xoaDuLieu();
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai(trangThai);
                    taiLaiDuLieu(dsPhieu);
                }else if(dcsNgayDatTK.getDate()==null){
                    xoaDuLieu();
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_TrangThai(tuKhoa, trangThai);
                    taiLaiDuLieu(dsPhieu);
                }else if(tuKhoa==null){
                    xoaDuLieu();
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai_Ngay(trangThai, nam, thang+1, ngay);
                    taiLaiDuLieu(dsPhieu);
                }else{
                    xoaDuLieu();
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tuKhoa, trangThai, nam, thang+1, ngay);
                    taiLaiDuLieu(dsPhieu);
                }
            }else{
                 if(dcsNgayDatTK.getDate()==null && tuKhoa==null){
                     xoaDuLieu();
                    dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
                    taiLaiDuLieu(dsPhieu);
                 }else if(tuKhoa==null){
                     xoaDuLieu();
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongNgay(ngay, thang+1, nam);
                    taiLaiDuLieu(dsPhieu);
                 }else if(dcsNgayDatTK.getDate()==null){
                     xoaDuLieu();
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName(tuKhoa);
                    taiLaiDuLieu(dsPhieu);
                 }else{
                     xoaDuLieu();
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_Ngay(tuKhoa, nam, thang+1, ngay);
                    taiLaiDuLieu(dsPhieu);
                 }
             }
           
        }
        
        if (obj.equals(btnLamMoi)) {
            txtKhachHang.setText("");
            txtMaPhieu.setText("");
            txtPhong.setText("");
            txtTimKiem.setText("");
            cmbTrangThai.setSelectedItem(null);
            String tk = (String) cmbTrangThaiTK.getItemAt(0);
            cmbTrangThaiTK.setSelectedItem(tk);
            txtNgayDat.setText("");
            txtNgayLap.setText("");
            dcsNgayDatTK.setDate(null);
            List<PhieuDatPhong> dsPhieu = null;
            dsPhieu= phieuDatPhong_Dao.getDsPhieuDatPhong();
            //xoaDuLieu();
            taiLaiDuLieu(dsPhieu);
        }
        
        if(obj.equals(btnHuyDatPhieu)){
            if(table.getSelectedRow()==-1){
                JOptionPane.showMessageDialog(this,"Chọn phiếu bạn muốn hủy đặt.");
            }else{
                if(phieuDatPhong_Dao.capNhatTrangThaiPhieu(txtMaPhieu.getText().trim())){
                    JOptionPane.showMessageDialog(this,"Thành công");
                    List<PhieuDatPhong> dsPhieu = null;
                    dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
                    //xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                }else{
                    JOptionPane.showMessageDialog(this,"Thất bại");
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
        String trangThai = cmbTrangThaiTK.getSelectedItem().toString();
        List<PhieuDatPhong> dsPhieu = null;
        if(tuKhoa!=null){
            if(cmbTrangThaiTK.getSelectedIndex()==0 && dcsNgayDatTK.getDate()==null){
                xoaDuLieu();
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName(tuKhoa);
                taiLaiDuLieu(dsPhieu);
            }else if(cmbTrangThaiTK.getSelectedIndex()==0){
                xoaDuLieu();
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_Ngay(tuKhoa, nam, thang+1, ngay);
                taiLaiDuLieu(dsPhieu);
            }else if(dcsNgayDatTK.getDate()==null){
                xoaDuLieu();
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByName_TrangThai(tuKhoa, trangThai);
                taiLaiDuLieu(dsPhieu);
            }else{
                xoaDuLieu();
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tuKhoa, trangThai, nam, thang+1, ngay);
                taiLaiDuLieu(dsPhieu);
            }
        }else{
            if(cmbTrangThaiTK.getSelectedIndex()==0 && dcsNgayDatTK.getDate()==null){
                xoaDuLieu();
                dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong();
                taiLaiDuLieu(dsPhieu);
            }else if(cmbTrangThaiTK.getSelectedIndex()==0){
                xoaDuLieu();
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongNgay(ngay, thang+1, nam);
                taiLaiDuLieu(dsPhieu);
            }else if(dcsNgayDatTK.getDate()==null){
                xoaDuLieu();
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai(trangThai);
                taiLaiDuLieu(dsPhieu);
            }else{
                xoaDuLieu();
                dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByTrangThai_Ngay(trangThai, nam, thang+1, ngay);
                taiLaiDuLieu(dsPhieu);
            }
        }
     }
    }
}
