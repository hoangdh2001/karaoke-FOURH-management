package gui;

import com.toedter.calendar.JDateChooser;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.PhieuDatPhong;
import entity.TrangThaiPhieuDat;
import gui.swing.graphics.ShadowType;
import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.table2.EventAction;
import java.awt.Color;
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
import gui.swing.panel.slideshow.EventPagination;
import gui.swing.textfield.MyTextFieldFlatlaf;
import java.util.Date;
import javax.swing.JCheckBox;

import javax.swing.JComboBox;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class GD_QLDatPhong extends javax.swing.JPanel implements ActionListener, KeyListener{
    
    private PhieuDatPhong_DAO phieuDatPhong_Dao;
    private Phong_DAO phong_Dao;
    private List<PhieuDatPhong> dsPhieu = new ArrayList<PhieuDatPhong>();
    private MyTextFieldFlatlaf txtTimKiemKhachHang, txtTimKiemPhong;
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
        
        initComponents();
        phieuDatPhong_Dao = new PhieuDatPhong_DAO();
        buildGD_QLDatPhong();
       
    }
    
    public void addEvent(EventSelectedRow event) {
        this.eventSelectedRow = event;
    }
    
    private void buildGD_QLDatPhong() {
        createPanelForm();
        createTable();
        
       // phong_Dao = new Phong_DAO();
        createPanelBottom();
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

        pnlTop.setLayout(new MigLayout("fill", "push[center]10[center]20[center]20[center]5[center]30[center]10[center]push", "60[center]20[center]20[]push"));
        

        txtTimKiemPhong = new MyTextFieldFlatlaf();
        txtTimKiemPhong.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiemPhong.setHint("Nhập tên phòng");
        pnlTop.add(txtTimKiemPhong, "w 15%, h 30!");
        
        txtTimKiemKhachHang = new MyTextFieldFlatlaf();
        txtTimKiemKhachHang.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiemKhachHang.setHint("Nhập tên khách hàng");
        pnlTop.add(txtTimKiemKhachHang, "w 15%, h 30!");

        cmbTrangThaiTK = new JComboBox<>();//cbModel
        cmbTrangThaiTK.setFont(new Font(fontName, fontPlain, font14));
        cmbTrangThaiTK.addItem("Lọc theo trạng thái");
        pnlTop.add(cmbTrangThaiTK, "w 15%, h 30!");

        // Tìm kiếm Ngày đặt
        JLabel lblNgayDaLabelTK = new JLabel("Lọc theo ngày đặt");
        lblNgayDaLabelTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTop.add(lblNgayDaLabelTK);

        dcsNgayDatTK = new JDateChooser();
        dcsNgayDatTK.setFont(new Font(fontName, fontPlain, font14));
        dcsNgayDatTK.setOpaque(false);
        pnlTop.add(dcsNgayDatTK, "w 15%, h 30!");

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
        txtTimKiemPhong.addKeyListener(this);
        txtTimKiemKhachHang.addKeyListener(this);
        dcsNgayDatTK.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent arg0) {
                Date  ngayDat = dcsNgayDatTK.getDate();
                String tenPhong = txtTimKiemPhong.getText().trim();
                String tenKhachHang = txtTimKiemKhachHang.getText().trim();
                String s=   cmbTrangThaiTK.getSelectedItem().toString();
                TrangThaiPhieuDat trangThai = TrangThaiPhieuDat.getTrangThaiPhieuDatByTrangThai(s);
                if(dcsNgayDatTK.getDate()!=null){
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat);
                    xoaDuLieu();
                    taiLaiDuLieu(dsPhieu);
                }}
        });
        
        tblPhieuDatPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Nếu click chuột trái 2 lần
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    int row = tblPhieuDatPhong.getSelectedRow();
                    String maPhieu = tblPhieuDatPhong.getValueAt(row, 1).toString();
                    System.out.println(phieuDatPhong_Dao.getPhieuDatPhong(maPhieu));
                    eventSelectedRow.selectedRow(phieuDatPhong_Dao.getPhieuDatPhong(maPhieu));
                }
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
        tblPhieuDatPhong.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 14));
        loadData(pnlPage.getCurrentIndex());
    }
    
    public void xoaDuLieu(){
        DefaultTableModel df = (DefaultTableModel) tblPhieuDatPhong.getModel();
        df.setRowCount(0);
    }
    
    public void taiLaiDuLieu(List<PhieuDatPhong> dsPhieu){
         DecimalFormat dcf = new DecimalFormat("#,### VNĐ");
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        for(PhieuDatPhong phieu : dsPhieu){
            tblPhieuDatPhong.addRow(new Object[]{JCheckBox.class,phieu.getMaPhieuDat(), fm.format(phieu.getNgayTao()),phieu.getKhachHang().getTenKhachHang(), phieu.getPhong().getTenPhong(), fm.format(phieu.getNgayDat()), phieu.getTrangThai(), dcf.format(phieu.getTienCoc())});
        }
    }
    
    private void loadData(int numPage) {
        ((DefaultTableModel) tblPhieuDatPhong.getModel()).setRowCount(0);
        dsPhieu= phieuDatPhong_Dao.getDsPhieuDatPhong(numPage);
        DecimalFormat dcf = new DecimalFormat("#,###NĐ");
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        for(PhieuDatPhong phieu : dsPhieu){
            tblPhieuDatPhong.addRow(new Object[]{JCheckBox.class,phieu.getMaPhieuDat(), fm.format(phieu.getNgayTao()),phieu.getKhachHang().getTenKhachHang(), phieu.getPhong().getTenPhong(), fm.format(phieu.getNgayDat()), phieu.getTrangThai(), dcf.format(phieu.getTienCoc())});
        }
        tblPhieuDatPhong.repaint();
        tblPhieuDatPhong.revalidate();
    }
    
     private void createPanelBottom() {
        pnlPage.addEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                loadData(pageClick);
            }
        });
        int soLuongPhieu = phieuDatPhong_Dao.getSoLuongPhieuDatPhong();
        pnlPage.init(soLuongPhieu % 20 == 0 ? soLuongPhieu / 20 : (soLuongPhieu / 20) + 1);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPhieuDatPhong = new gui.swing.table2.MyTableFlatlaf();
        jPanel1 = new javax.swing.JPanel();
        pnlPage = new gui.swing.table2.PanelPage();

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
        pnlBottom.setLayout(new java.awt.BorderLayout());

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
        tblPhieuDatPhong.setSelectionForeground(new java.awt.Color(51, 51, 51));
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlPage, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 640, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPage, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        pnlBottom.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.table2.PanelPage pnlPage;
    private gui.swing.panel.PanelShadow pnlTop;
    private gui.swing.table2.MyTableFlatlaf tblPhieuDatPhong;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
         Object obj = e.getSource();
        if(obj.equals(cmbTrangThaiTK)){
            Date  ngayDat = dcsNgayDatTK.getDate();
            String tenPhong = txtTimKiemPhong.getText().trim();
             String tenKhachHang = txtTimKiemKhachHang.getText().trim();
            String s=   cmbTrangThaiTK.getSelectedItem().toString();
            TrangThaiPhieuDat trangThai = TrangThaiPhieuDat.getTrangThaiPhieuDatByTrangThai(s);
            dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat);
            xoaDuLieu();
            taiLaiDuLieu(dsPhieu);
            }
        if (obj.equals(btnLamMoi)) {
            
            txtTimKiemPhong.setText("");
            txtTimKiemKhachHang.setText("");
            String tk = (String) cmbTrangThaiTK.getItemAt(0);
            cmbTrangThaiTK.setSelectedItem(tk);
            dcsNgayDatTK.setDate(null);
            dsPhieu= phieuDatPhong_Dao.getDsPhieuDatPhong(pnlPage.getCurrentIndex());
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
                    dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong(pnlPage.getCurrentIndex());
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
      if(obj.equals(txtTimKiemPhong)){
       Date ngayDat = dcsNgayDatTK.getDate();
        String tenPhong = txtTimKiemPhong.getText().trim();
        String tenKhachHang = txtTimKiemKhachHang.getText().trim();
        String s = cmbTrangThaiTK.getSelectedItem().toString();
        TrangThaiPhieuDat trangThai = TrangThaiPhieuDat.getTrangThaiPhieuDatByTrangThai(s);
            dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat);
            xoaDuLieu();
            taiLaiDuLieu(dsPhieu);
        }

      if(obj.equals(txtTimKiemKhachHang)){
          Date ngayDat = dcsNgayDatTK.getDate();
        String tenPhong = txtTimKiemPhong.getText().trim();
         String tenKhachHang = txtTimKiemKhachHang.getText().trim();
        String s=   cmbTrangThaiTK.getSelectedItem().toString();
        TrangThaiPhieuDat trangThai = TrangThaiPhieuDat.getTrangThaiPhieuDatByTrangThai(s);
        dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat);
        xoaDuLieu();
        taiLaiDuLieu(dsPhieu);
            
        }
    
     }

   
}


