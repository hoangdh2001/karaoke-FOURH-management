/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.toedter.calendar.JDateChooser;
import dao.HoaDon_DAO;
import entity.HoaDon;
import gui.swing.button.Button;
import gui.swing.event.EventSelectedRow;
import gui.swing.graphics.ShadowType;
import gui.swing.panel.PanelShadow;
import gui.swing.table2.EventAction;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author NGUYEN HAO
 */
public class GD_HoaDon extends javax.swing.JPanel implements ActionListener{
    private HoaDon_DAO hoaDon_Dao;
    private List<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
    private EventAction event;

    JCheckBox chkSapXepThuTu;
    JDateChooser dscBatDau, dscKetThuc;
    MyComboBox<String> cmbTuyChinh, cmbCot, cmbSapXep;
    MyTextField txtTimKiem;

    private PanelShadow panelHidden;

    private MyComboBox<Object> cmbQuy;
    private MyComboBox<Object> cmbThang;
    private MyComboBox<Object> cmbNam;
    private Button btnLamMoi;
    private List<Integer> dsThang, dsQuy, dsNam;

    private EventSelectedRow eventOnClick;


    
    public void addEvent(EventSelectedRow eventOnClick) {
        this.eventOnClick = eventOnClick;
    }

    /**
     * Creates new form GD_HoaDon
     */
    public GD_HoaDon() {
        hoaDon_Dao = new HoaDon_DAO();
        initComponents();
        loadData();
        build_GDHoaDon();
    }

    private void build_GDHoaDon() {
        createForm();
        createTable();
        loadThangLenCombobox(dsThang);
        loadQuyLenCombobox(dsQuy);
        loadNamLenCombobox(dsNam);
        setOpaque(false);
        setPreferredSize(new Dimension(getWidth(), 950));
        createPanelHidden();
        add(panelHidden);
    }

    private void createPanelHidden() {
        panelHidden = new PanelShadow();
        panelHidden.setShadowType(ShadowType.CENTER);
        panelHidden.setShadowOpacity(0.3f);
    }

    private void createForm() {
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font16 = 16;
        int font14 = 14;
        Color colorBtn = new Color(184, 238, 241);
        Color colorLabel = new Color(47, 72, 210);
        int separatorHeight = 150;
       
        pnlForm.setLayout(new MigLayout("", "[center][center]", "[center][center]"));
        pnlForm.add(createPanelTitle(), "span,pos 0al 0al 100% n, h 40!, wrap");
        /*
         * Begin: group Chọn thời gian 
         */
        JPanel pnlThoiGianHD = new JPanel();
        pnlThoiGianHD.setOpaque(false);

        pnlThoiGianHD.setLayout(new MigLayout("", "10[][center] 50[] [center]10", "60[][center]10[center]"));
        pnlForm.add(pnlThoiGianHD, "w 60%, h 200!");

        JLabel lblChonThoiGian = new JLabel("Chọn thời gian");
        lblChonThoiGian.setFont(new Font(fontName, fontPlain, font16));
        lblChonThoiGian.setForeground(colorLabel);
        pnlThoiGianHD.add(lblChonThoiGian, "span, w 100%, h 30!, wrap");
        JLabel lblTu;

        // Chọn thời gian bắt đầu
        pnlThoiGianHD.add(lblTu = new JLabel("Từ: "));
        lblTu.setFont(new Font(fontName, fontPlain, font16));
        dscBatDau = new JDateChooser();
        dscBatDau.setOpaque(false);
        dscBatDau.setDateFormatString("yyyy-MM-dd");
        dscBatDau.setFont(new Font(fontName, fontPlain, font16));
        pnlThoiGianHD.add(dscBatDau, "w 50%, h 36!");
        JLabel lblDen;

        // Chọn thời gian kết thúc
        pnlThoiGianHD.add(lblDen = new JLabel("Đến: "));
        lblDen.setFont(new Font(fontName, fontPlain, font16));
        dscKetThuc = new JDateChooser();
        dscKetThuc.setOpaque(false);
        dscKetThuc.setFont(new Font(fontName, fontPlain, font16));
        pnlThoiGianHD.add(dscKetThuc, "w 50%, h 36!, wrap");

        JPanel pnlCmbThoiGian = new JPanel(new MigLayout("", "0[center]push[center]push[center]0", "[center][center]"));
        pnlCmbThoiGian.setBackground(Color.WHITE);
        pnlThoiGianHD.add(pnlCmbThoiGian, "span, w 100%");
        
        cmbNam = new MyComboBox<>();
        cmbNam.setFont(new Font(fontName, fontPlain, font16));
        cmbNam.setBorderLine(true);
        cmbNam.setBorderRadius(10);
        cmbNam.addItem("Lọc theo năm");
        pnlCmbThoiGian.add(cmbNam, "w 32%, h 36!");
        
        //Tùy chỉnh
        cmbQuy = new MyComboBox<>();
        cmbQuy.setFont(new Font(fontName, fontPlain, font16));
        cmbQuy.setBorderLine(true);
        cmbQuy.addItem("Lọc theo quý");
        cmbQuy.setBorderRadius(10);
        pnlCmbThoiGian.add(cmbQuy, "w 32%, h 36!");
        
        cmbThang = new MyComboBox<>();
        cmbThang.setFont(new Font(fontName, fontPlain, font16));
        cmbThang.setBorderLine(true);
        cmbThang.addItem("Lọc theo tháng");
        cmbThang.setBorderRadius(10);
        pnlCmbThoiGian.add(cmbThang, "w 32%, h 36!");
        /*
         * End: group Chọn thời gian bắt đầu
         */
        JSeparator spr1 = new JSeparator(SwingConstants.VERTICAL);
        spr1.setPreferredSize(new Dimension(2, separatorHeight));
        pnlForm.add(spr1,"pos 0.6al 0.9al n n");
        
        /* 
         * Begin: group Tìm kiếm
         */
        JPanel pnlTimKiemHD = new JPanel();
        pnlTimKiemHD.setOpaque(false);
        /*
        Layout: 1 cột, 2 dòng
        cột 1, dòng 1: Ô nhập dữ liệu tìm kiếm
        cột 1, dòng 2: Chọn cột cần tìm
         */
        pnlTimKiemHD.setLayout(new MigLayout("", "[]10[center]10", "60[][center]18[center]"));
        pnlForm.add(pnlTimKiemHD, "w 40%, h 200!");

        JLabel lblTimKiem = new JLabel("Tìm kiếm");
        lblTimKiem.setFont(new Font(fontName, fontPlain, font16));
        lblTimKiem.setForeground(colorLabel);
        pnlTimKiemHD.add(lblTimKiem, "span, w 100%, h 30!, wrap");

        //Chọn cột cần tìm
        cmbCot = new MyComboBox<>(new Object[]{"Chọn cột cần tìm","Mã hóa đơn","Khách hàng","Phòng"});
        cmbCot.setFont(new Font(fontName, fontPlain, font16));
        cmbCot.setBorderLine(true);
        cmbCot.setBorderRadius(10);
        //cmbCot.addItem("Chọn cột cần tìm");
        pnlTimKiemHD.add(cmbCot, "span,w 100%, h 36!, wrap");
        

        // Tìm kiếm  
        txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font16));
        txtTimKiem.setBorderLine(true);
        txtTimKiem.setBorderRadius(5);
        txtTimKiem.setHint("Nhập thông tin tìm kiếm theo tùy chọn của bạn.");
        pnlTimKiemHD.add(txtTimKiem, "w 100%, h 36!");

        // Nút Làm mới
        btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font14));
        btnLamMoi.setBackground(colorBtn);
        btnLamMoi.setBorderRadius(5);
        btnLamMoi.setBorderline(true);
        pnlTimKiemHD.add(btnLamMoi, " w 100!, h 38!");

        /*Đăng ký sự kiện*/
        cmbCot.addActionListener(this);
        btnLamMoi.addActionListener(this);
        cmbQuy.addActionListener(this);
        cmbThang.addActionListener(this);
        cmbNam.addActionListener(this);
        xuLySuKien();

        tblHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Nếu click chuột trái và click 2 lần
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    int row = tblHoaDon.getSelectedRow();
                    String maHoaDon = tblHoaDon.getValueAt(row, 0).toString();
                    System.out.println(hoaDon_Dao.getHoaDon(maHoaDon));
                    eventOnClick.selectedRow(hoaDon_Dao.getHoaDon(maHoaDon));
                }
            }
        });

    }

    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("QUẢN LÝ HÓA ĐƠN");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return pnlTitle;
    }
    
    private void createTable(){
        Object rows[][] = { {"","","","","","","","","","",""},{"","","","","","","","","","",""}, };
        String columns[] = {"Mã hóa đơn","Khách hàng","Phòng","Số giờ hát", "Ngày lập hóa đơn","Giờ bắt đầu","giờ kết thúc","Tổng mặt hàng","giá phòng","Tổng hóa đơn","Nhân viên"};
        TableModel model = new DefaultTableModel(rows, columns){
        public Class getColumnClass(int column) {
        Class returnValue;
        if ((column >= 0) && (column < getColumnCount())) {
          returnValue = getValueAt(0, column).getClass();
        } else {
          returnValue = Object.class;
        }
        return returnValue;
      }
    };
    tblHoaDon.setModel(model);
    RowSorter<TableModel> sorter = new TableRowSorter<TableModel>(model);
    tblHoaDon.setRowSorter(sorter);
    tblHoaDon.fixTable(scrHoaDon);
    }

    public void xoaDuLieu() {
        DefaultTableModel df = (DefaultTableModel) tblHoaDon.getModel();
        df.setRowCount(0);
    }
    
    public void taiLaiDuLieu(List<HoaDon> dsHoaDon){
        dsHoaDon.forEach((hoaDon)->{
            tblHoaDon.addRow(new Object[]{hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getPhong().getTenPhong(), hoaDon.getGioHat(), hoaDon.getNgayLapHoaDon(), hoaDon.getThoiGianBatDau(), hoaDon.getThoiGianKetThuc(), hoaDon.getTongTienMatHang(),  hoaDon.getPhong().getLoaiPhong().getGiaPhong(), hoaDon.getTongHoaDon(), hoaDon.getNhanVien().getTenNhanVien()});
        });
    }

    private void loadData() {
        dsHoaDon = hoaDon_Dao.getDsHoaDon();
        if(dsHoaDon != null) {
            dsHoaDon.forEach((hoaDon)->{
                tblHoaDon.addRow(new Object[]{hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getPhong().getTenPhong(), hoaDon.getGioHat(), hoaDon.getNgayLapHoaDon(), hoaDon.getThoiGianBatDau(), hoaDon.getThoiGianKetThuc(), hoaDon.getTongTienMatHang(), hoaDon.getPhong().getLoaiPhong().getGiaPhong(), hoaDon.getTongHoaDon(), hoaDon.getNhanVien().getTenNhanVien()});
            });
        }
    }

    private void xuLySuKien(){
        tblHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Nếu click chuột trái 2 lần
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    int row = tblHoaDon.getSelectedRow();
                    String maHoaDon = tblHoaDon.getValueAt(row, 0).toString();
                    System.out.println(hoaDon_Dao.getHoaDon(maHoaDon));
                    eventOnClick.selectedRow(hoaDon_Dao.getHoaDon(maHoaDon));
                }
            }
        });
        
        txtTimKiem.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent arg0) {
            }
            @Override
            public void keyPressed(KeyEvent arg0) {
            }
            @Override
            public void keyReleased(KeyEvent arg0) {
                if(cmbCot.getSelectedIndex()!=0){
                    String tk;
                    String s = txtTimKiem.getText().trim();
                    String tieuChi = cmbCot.getSelectedItem().toString();
                   switch(tieuChi){
                        case "Mã hóa đơn":
                            tk = "maHoaDon";
                            dsHoaDon = hoaDon_Dao.getDSHoaDonByTieuChiKhac(tk, s);
                            break;
                        case "Khách hàng":
                            dsHoaDon = hoaDon_Dao.getDSHoaDonByTenKhachHang(s);
                            break;
                        case "Phòng":
                            dsHoaDon= hoaDon_Dao.getDSHoaDonByTenPhong(s);
                            break;
                        default:
                            dsHoaDon = hoaDon_Dao.getDsHoaDon();
                            break;
                    }
                   xoaDuLieu();
                   taiLaiDuLieu(dsHoaDon);
                }else{
                    dsHoaDon = hoaDon_Dao.getDsHoaDon();
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }
            }
        });
                      
        dscBatDau.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
           public void propertyChange(PropertyChangeEvent arg0) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                if(dscBatDau.getDate()!=null){
                    System.out.println(df.format(dscBatDau.getDate()));
                    System.out.println(hoaDon_Dao.layNgayLapNhoNhat());
                    System.out.println(hoaDon_Dao.layNgayLapLonNhat());
                    if(dscKetThuc.getDate()==null){
                        dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(df.format(dscBatDau.getDate()), hoaDon_Dao.layNgayLapLonNhat());
                        xoaDuLieu();
                        taiLaiDuLieu(dsHoaDon);
                    }else{
                        dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(df.format(dscBatDau.getDate()), df.format(dscKetThuc.getDate()));
                        xoaDuLieu();
                        taiLaiDuLieu(dsHoaDon);
                    }
                }else{
                    if(dscKetThuc.getDate()==null){
                        xoaDuLieu();
                        loadData();
                    }else{
                        dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(hoaDon_Dao.layNgayLapNhoNhat(),df.format(dscKetThuc.getDate()));
                        xoaDuLieu();
                        taiLaiDuLieu(dsHoaDon);
                    }
                }

            }
        });
        
        dscKetThuc.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent arg0) {
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                if(dscKetThuc.getDate()!=null){
                    if(dscBatDau.getDate()==null){
                        dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(hoaDon_Dao.layNgayLapNhoNhat(),df.format(dscKetThuc.getDate()));
                        xoaDuLieu();
                        taiLaiDuLieu(dsHoaDon);
                    }else{
                        dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(df.format(dscBatDau.getDate()), df.format(dscKetThuc.getDate()));
                        xoaDuLieu();
                        taiLaiDuLieu(dsHoaDon);
                    }
                }else{
                    if(dscBatDau.getDate()==null){
                        xoaDuLieu();
                        loadData();
                    }else{
                        dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(df.format(dscBatDau.getDate()), hoaDon_Dao.layNgayLapLonNhat());
                        xoaDuLieu();
                        taiLaiDuLieu(dsHoaDon);
                    }
                }
            }
        });
    }
    
    public void loadThangLenCombobox(List<Integer> dsThang){
        dsThang = hoaDon_Dao.getDSThangTheoNgayLap();
        dsThang.forEach(t->{
            cmbThang.addItem(t);
        });
    }
    
    public void loadQuyLenCombobox(List<Integer> dsQuy){
        dsQuy = hoaDon_Dao.getDSQuyTheoNgayLap();
        dsQuy.forEach(t->{
            cmbQuy.addItem(t);
        });
    }
    
    public void loadNamLenCombobox(List<Integer> dsNam){
        dsNam = hoaDon_Dao.getDSNamTheoNgayLap();
        dsNam.forEach(t->{
            cmbNam.addItem(t);
        });
    }
    
    public String kiemTraNgayBatDau(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String from;
        if(dscBatDau.getDate()==null){
            from = hoaDon_Dao.layNgayLapNhoNhat();
        }else{
            from = df.format(dscBatDau.getDate());
        }
        return from;
    }
    
    public String kiemTraNgayKetThuc(){
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String to;
        if(dscKetThuc.getDate()==null){
            to = hoaDon_Dao.layNgayLapLonNhat();
        }else{
            to = df.format(dscKetThuc.getDate());
        }
        return to;
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
        scrHoaDon = new javax.swing.JScrollPane();
        tblHoaDon = new gui.swing.table2.MyTable();
        lblBang = new javax.swing.JLabel();

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setShadowOpacity(0.3F);
        pnlForm.setShadowSize(3);

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1079, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 215, Short.MAX_VALUE)
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setShadowOpacity(0.3F);
        pnlCenter.setShadowSize(3);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hóa Đơn", "Khách Hàng", "Phòng", "Số Giờ Hát", "Ngày Lập", "Giờ Bắt Đầu", "Giờ Kết Thúc", "Tổng Mặt Hàng", "Giá Phòng", "Tổng Hóa Đơn", "Nhân Viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        scrHoaDon.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(1).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(2).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(3).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(4).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(5).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(6).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(7).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(8).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(9).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(10).setResizable(false);
        }

        lblBang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblBang.setForeground(new java.awt.Color(4, 72, 210));
        lblBang.setText("   Danh Sách Hóa Đơn");
        lblBang.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrHoaDon)
            .addComponent(lblBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addComponent(lblBang, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblBang;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.panel.PanelShadow pnlForm;
    private javax.swing.JScrollPane scrHoaDon;
    private gui.swing.table2.MyTable tblHoaDon;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btnLamMoi)){
            dscBatDau.setDate(null);
            dscKetThuc.setDate(null);
            txtTimKiem.setText("");
            cmbThang.setSelectedIndex(0);
            cmbCot.setSelectedIndex(0);
            cmbNam.setSelectedIndex(0);
            cmbQuy.setSelectedIndex(0);
            txtTimKiem.requestFocus();
            xoaDuLieu();
            loadData();
        }
        if(obj.equals(cmbNam)){
            String to = kiemTraNgayKetThuc();
            String from = kiemTraNgayBatDau();
            if(cmbNam.getSelectedIndex()!=0){
                if(cmbQuy.getSelectedIndex()==0 && cmbThang.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByNam(from, to, Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbQuy.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Nam(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbThang.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByQuy_Nam(from, to, Integer.parseInt(cmbQuy.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else{
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Quy_Nam(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbQuy.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }
            }else{
                if(cmbQuy.getSelectedIndex()==0 && cmbThang.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(from, to);
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbQuy.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbThang.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByQuy(from, to, Integer.parseInt(cmbQuy.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else{
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Quy(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbQuy.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }
            }
        }
        if(obj.equals(cmbQuy)){
            String from = kiemTraNgayBatDau();
            String to =kiemTraNgayKetThuc();
            if(cmbQuy.getSelectedIndex()!=0){
                if(cmbNam.getSelectedIndex()==0 && cmbThang.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByQuy(from, to, Integer.parseInt(cmbQuy.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbNam.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Quy(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbQuy.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbThang.getSelectedIndex()==0){
                    dsHoaDon =hoaDon_Dao.sapXepHoaDonByQuy_Nam(from, to, Integer.parseInt(cmbQuy.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else{
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Quy_Nam(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbQuy.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }
            }else{
                if(cmbNam.getSelectedIndex()==0 && cmbThang.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(from, to);
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbNam.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbThang.getSelectedIndex()==0){
                    dsHoaDon =hoaDon_Dao.sapXepHoaDonByNam(from, to, Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else{
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Nam(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }
            }
        }
        if(obj.equals(cmbThang)){
            String from = kiemTraNgayBatDau();
            String to =kiemTraNgayKetThuc();
            if(cmbThang.getSelectedIndex()!=0){
                if(cmbNam.getSelectedIndex()==0 && cmbQuy.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbNam.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Quy(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbQuy.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbQuy.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Nam(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else{
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByThang_Quy_Nam(from, to, Integer.parseInt(cmbThang.getSelectedItem().toString()), Integer.parseInt(cmbQuy.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }
            }else{
                if(cmbNam.getSelectedIndex()==0 && cmbQuy.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.getDSHoaDonFromDateToDate(from, to);
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbNam.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByQuy(from, to, Integer.parseInt(cmbQuy.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else if(cmbQuy.getSelectedIndex()==0){
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByNam(from, to, Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }else{
                    dsHoaDon = hoaDon_Dao.sapXepHoaDonByQuy_Nam(from, to, Integer.parseInt(cmbQuy.getSelectedItem().toString()), Integer.parseInt(cmbNam.getSelectedItem().toString()));
                    xoaDuLieu();
                    taiLaiDuLieu(dsHoaDon);
                }
            }
        }
        if(obj.equals(cmbCot)){
            if(cmbCot.getSelectedIndex()==0){
                    txtTimKiem.setHint("Nhập thông tin tìm kiếm theo tùy chọn của bạn.");
                }else if(cmbCot.getSelectedItem().toString().equals("Mã hóa đơn")){
                    txtTimKiem.setHint("Nhập mã hóa đơn muốn tìm.");
                }else if(cmbCot.getSelectedItem().toString().equals("Khách hàng")){
                    txtTimKiem.setHint("Nhập tên khách hàng muốn tìm.");
                }else{
                    txtTimKiem.setHint("Nhập tên phòng hát muốn tìm.");
                }
        }
    }
}
