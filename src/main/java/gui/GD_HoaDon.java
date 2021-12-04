package gui;

import com.toedter.calendar.JDateChooser;
import dao.HoaDon_DAO;
import dao.NhanVien_DAO;
import entity.HoaDon;
import gui.swing.button.Button;
import gui.swing.event.EventSelectedRow;
import gui.swing.graphics.ShadowType;
import gui.swing.panel.PanelShadow;
import gui.swing.table2.EventAction;
import gui.swing.textfield.MyTextFieldFlatlaf;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.RowSorter;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import net.miginfocom.swing.MigLayout;

public class GD_HoaDon extends javax.swing.JPanel implements ActionListener {
    
    private HoaDon_DAO hoaDon_Dao;
    private List<HoaDon> dsHoaDon = new ArrayList<HoaDon>();
    private EventAction event;

    JCheckBox chkSapXepThuTu;
    JDateChooser dscBatDau, dscKetThuc;
    JComboBox<String> cmbTuyChinh, cmbCot, cmbSapXep;
    MyTextFieldFlatlaf txtTimKiem;

    private PanelShadow panelHidden;

    private JComboBox<Object> cmbQuy;
    private JComboBox<Object> cmbThang;
    private JComboBox<Object> cmbNam;
    private Button btnLamMoi;
    private List<Integer> dsThang, dsQuy, dsNam;

    private EventSelectedRow eventOnClick;
    private NhanVien_DAO nhanVien_Dao;
    
    public void addEvent(EventSelectedRow eventOnClick) {
        this.eventOnClick = eventOnClick;
    }

    public GD_HoaDon() {
        hoaDon_Dao = new HoaDon_DAO();
        nhanVien_Dao = new NhanVien_DAO();
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
    }
    
    private void createForm() {
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font16 = 16;
        int font14 = 14;
        Color colorBtn = new Color(184, 238, 241);
        Color colorLabel = new Color(47, 72, 210);
//        int separatorHeight = 150;
       
        pnlForm.setLayout(new MigLayout("fillx, insets 0, wrap", "[fill][fill]", "40[][]"));
        pnlForm.add(createPanelTitle(), "pos 0al 0al 100% n, h 40!");
        /*
         * Begin: group Chọn thời gian 
         */
        JPanel pnlThoiGianHD = new JPanel();
        pnlThoiGianHD.setOpaque(false);
        pnlThoiGianHD.setBorder(new MatteBorder(0, 0, 0, 1, new Color(0, 0, 0, 0.1f)));

        pnlThoiGianHD.setLayout(new MigLayout("wrap, insets 0", "10[][fill][][fill]10", "8[][]10[]"));
        pnlForm.add(pnlThoiGianHD, " w 55%");

        JLabel lblChonThoiGian = new JLabel("Tìm kiếm theo thời gian");
        lblChonThoiGian.setFont(new Font(fontName, fontPlain, font14));
        lblChonThoiGian.setForeground(colorLabel);
        pnlThoiGianHD.add(lblChonThoiGian, "span 4");
        JLabel lblTu;

        // Chọn thời gian bắt đầu
        pnlThoiGianHD.add(lblTu = new JLabel("Từ: "));
        lblTu.setFont(new Font(fontName, fontPlain, font14));
        dscBatDau = new JDateChooser();
        dscBatDau.setOpaque(false);
        dscBatDau.setDateFormatString("yyyy-MM-dd");
        dscBatDau.setFont(new Font(fontName, fontPlain, font14));
        pnlThoiGianHD.add(dscBatDau, "w 40%, h 30!");
        JLabel lblDen;

        // Chọn thời gian kết thúc
        pnlThoiGianHD.add(lblDen = new JLabel("Đến: "));
        lblDen.setFont(new Font(fontName, fontPlain, font14));
        dscKetThuc = new JDateChooser();
        dscKetThuc.setOpaque(false);
        dscKetThuc.setDateFormatString("yyyy-MM-dd");
        dscKetThuc.setFont(new Font(fontName, fontPlain, font14));
        pnlThoiGianHD.add(dscKetThuc, "w 40%, h 30!");

        JPanel pnlCmbThoiGian = new JPanel(new MigLayout("", "0[center]push[center]push[center]0", "[][]"));
        pnlCmbThoiGian.setBackground(Color.WHITE);
        pnlThoiGianHD.add(pnlCmbThoiGian, "span 4, w 100%");
        
        cmbNam = new JComboBox<>();
        cmbNam.setFont(new Font(fontName, fontPlain, font14));
//        cmbNam.setBorderLine(true);
//        cmbNam.setBorderRadius(10);
        cmbNam.addItem("Lọc theo năm");
        pnlCmbThoiGian.add(cmbNam, "w 32%, h 30!");
        
//        //Tùy chỉnh
        cmbQuy = new JComboBox<>();
        cmbQuy.setFont(new Font(fontName, fontPlain, font14));
//        cmbQuy.setBorderLine(true);
        cmbQuy.addItem("Lọc theo quý");
//        cmbQuy.setBorderRadius(10);
        pnlCmbThoiGian.add(cmbQuy, "w 32%, h 30!");
        
        cmbThang = new JComboBox<>();
        cmbThang.setFont(new Font(fontName, fontPlain, font14));
//        cmbThang.setBorderLine(true);
        cmbThang.addItem("Lọc theo tháng");
//        cmbThang.setBorderRadius(10);
        pnlCmbThoiGian.add(cmbThang, "w 32%, h 30!");
        /*
         * End: group Chọn thời gian bắt đầu
         */
//        JSeparator spr1 = new JSeparator(SwingConstants.VERTICAL);
//        spr1.setPreferredSize(new Dimension(2, separatorHeight));
//        pnlForm.add(spr1,"pos 0.6al 0.9al n n");
        
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
        pnlTimKiemHD.setLayout(new MigLayout("wrap, insets 0", "[]10[]10", "[][]10[]"));
        pnlForm.add(pnlTimKiemHD);

        JLabel lblTimKiem = new JLabel("Tìm kiếm cụ thể");
        lblTimKiem.setFont(new Font(fontName, fontPlain, font14));
        lblTimKiem.setForeground(colorLabel);
        pnlTimKiemHD.add(lblTimKiem, "span 2");

        //Chọn cột cần tìm
        cmbCot = new JComboBox<>(new String[]{"Chọn cột cần tìm","Mã hóa đơn","Khách hàng","Phòng"});
        cmbCot.setFont(new Font(fontName, fontPlain, font14));
//        cmbCot.setBorderLine(true);
//        cmbCot.setBorderRadius(10);
        cmbCot.addItem("Chọn cột cần tìm");
        pnlTimKiemHD.add(cmbCot, "span 2, h 30!, w 100%");
        

        // Tìm kiếm  
        txtTimKiem = new MyTextFieldFlatlaf();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font14));
//        txtTimKiem.setBorderLine(true);
//        txtTimKiem.setBorderRadius(5);
        txtTimKiem.setHint("Nhập thông tin tìm kiếm theo tùy chọn của bạn.");
        pnlTimKiemHD.add(txtTimKiem, "h 30!, w 100%");

        // Nút Làm mới
        btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font14));
        btnLamMoi.setBackground(colorBtn);
        btnLamMoi.setBorderRadius(5);
        btnLamMoi.setBorderline(true);
        pnlTimKiemHD.add(btnLamMoi, "w 100!, h 36!");

        /*Đăng ký sự kiện*/
//        cmbCot.addActionListener(this);
//        btnLamMoi.addActionListener(this);
//        cmbQuy.addActionListener(this);
//        cmbThang.addActionListener(this);
//        cmbNam.addActionListener(this);
//        xuLySuKien();
//
//        tblHoaDon.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mousePressed(MouseEvent e) {
//                //Nếu click chuột trái và click 2 lần
//                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
//                    int row = tblHoaDon.getSelectedRow();
//                    String maHoaDon = tblHoaDon.getValueAt(row, 0).toString();
//                    System.out.println(hoaDon_Dao.getHoaDon(maHoaDon));
//                    eventOnClick.selectedRow(hoaDon_Dao.getHoaDon(maHoaDon));
//                }
//            }
//        });

    }

    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Quản lý hóa đơn");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return pnlTitle;
    }
    
    private void createTable(){
//        Object rows[][] = { {"","","","","","","","","","",""},{"","","","","","","","","","",""}, };
//        String columns[] = {"Mã hóa đơn","Khách hàng","Phòng","Số phút hát", "Ngày lập hóa đơn","Giờ bắt đầu","Tổng mặt hàng","giá phòng","Tổng hóa đơn","Nhân viên"};
//        TableModel model = new DefaultTableModel(rows, columns){
//            boolean[] canEdit = new boolean [] {
//                false, false, false,false,true
//            }; 
//            public boolean isCellEditable(int rowIndex, int columnIndex) {
//                return false;
//            }
//            public Class getColumnClass(int column) {
//                Class returnValue;
//                if ((column >= 0) && (column < getColumnCount())) {
//                  returnValue = getValueAt(0, column).getClass();
//                } else {
//                  returnValue = Object.class;
//                }
//                return returnValue;
//            }
//        };
//        tblHoaDon.setModel(model);
        tblHoaDon.getTableHeader().setFont(new Font("sansserif", Font.BOLD, 14));
        RowSorter<TableModel> sorter = new TableRowSorter<>((DefaultTableModel) tblHoaDon.getModel());
        tblHoaDon.setRowSorter(sorter);
    }
    
    public void xoaDuLieu() {
        DefaultTableModel df = (DefaultTableModel) tblHoaDon.getModel();
        df.setRowCount(0);
    }
    
    public void taiLaiDuLieu(List<HoaDon> dsHoaDon){
        DecimalFormat dcf = new DecimalFormat("#,###");
        SimpleDateFormat fm1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
        List<HoaDon> dsTam = xuLyLoai(dsHoaDon);
        for(HoaDon hoaDon: dsTam){
            tblHoaDon.addRow(new Object[]{hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getPhong().getTenPhong(), hoaDon.getGioHat(), fm1.format(hoaDon.getNgayLapHoaDon()), fm2.format(hoaDon.getThoiGianBatDau()), dcf.format(hoaDon.getTongTienMatHang()), dcf.format(hoaDon.getPhong().getLoaiPhong().getGiaPhong()), dcf.format(hoaDon.getTongHoaDon()), hoaDon.getNhanVien().getTenNhanVien()});
        }
    }
    
    public List<HoaDon> xuLyLoai(List<HoaDon> dsHoaDon){
        List<HoaDon> dsTam = new ArrayList<>();
        if(nhanVien_Dao.getMaNhanVienQuanLy().contains(GD_Chinh.NHAN_VIEN.getMaNhanVien())){
            return dsHoaDon;
        }else{
            for(HoaDon hoaDon: dsHoaDon){
                if(hoaDon.getNhanVien().getMaNhanVien().equals(GD_Chinh.NHAN_VIEN.getMaNhanVien())){
                    dsTam.add(hoaDon);
                }
            }
            return dsTam;
        }
    }    
    private void loadData() {
        DecimalFormat dcf = new DecimalFormat("#,###");
        SimpleDateFormat fm1 = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
        dsHoaDon = hoaDon_Dao.getDsHoaDon();
        if(dsHoaDon != null) {
            List<HoaDon> dsTam = xuLyLoai(dsHoaDon);
            for(HoaDon hoaDon: dsTam){
                tblHoaDon.addRow(new Object[]{hoaDon.getMaHoaDon(), hoaDon.getKhachHang().getTenKhachHang(), hoaDon.getPhong().getTenPhong(), hoaDon.getGioHat(), fm1.format(hoaDon.getNgayLapHoaDon()), fm2.format(hoaDon.getThoiGianBatDau()), dcf.format(hoaDon.getTongTienMatHang()), dcf.format(hoaDon.getPhong().getLoaiPhong().getGiaPhong()), dcf.format(hoaDon.getTongHoaDon()), hoaDon.getNhanVien().getTenNhanVien()});
            }
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
                
                if(cmbCot.getSelectedIndex()==0){
                    JOptionPane.showMessageDialog(GD_HoaDon.this, "Hãy chọn cột mà bạn muốn tìm kiếm.");
                    txtTimKiem.setText("");
                    txtTimKiem.requestFocus();
                }
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlForm = new gui.swing.panel.PanelShadow();
        panelShadow2 = new gui.swing.panel.PanelShadow();
        jPanel1 = new javax.swing.JPanel();
        panelPage1 = new gui.swing.table2.PanelPage();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblHoaDon = new gui.swing.table2.MyTableFlatlaf();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 5));

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setShadowOpacity(0.3F);
        pnlForm.setShadowSize(2);
        pnlForm.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 903, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        add(pnlForm, java.awt.BorderLayout.PAGE_START);

        panelShadow2.setBackground(new java.awt.Color(255, 255, 255));
        panelShadow2.setShadowOpacity(0.3F);
        panelShadow2.setShadowSize(2);
        panelShadow2.setShadowType(gui.swing.graphics.ShadowType.TOP);
        panelShadow2.setLayout(new java.awt.BorderLayout());

        jPanel1.setOpaque(false);

        panelPage1.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(panelPage1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 755, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelPage1, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        panelShadow2.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "Khách hàng", "Phòng", "Giờ hát", "Ngày lập hóa đơn", "Giờ bắt đầu", "Tổng mặt hàng", "Giá phòng", "Tổng hóa đơn", "Nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblHoaDon);
        if (tblHoaDon.getColumnModel().getColumnCount() > 0) {
            tblHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(1).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(2).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(3).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(4).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblHoaDon.getColumnModel().getColumn(5).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(6).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(7).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(8).setResizable(false);
            tblHoaDon.getColumnModel().getColumn(9).setResizable(false);
        }

        panelShadow2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(panelShadow2, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents
    
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
            dscBatDau.setDate(null);
            dscKetThuc.setDate(null);
            cmbThang.setSelectedIndex(0);
            cmbNam.setSelectedIndex(0);
            cmbQuy.setSelectedIndex(0);
            xoaDuLieu();
            taiLaiDuLieu(dsHoaDon);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private gui.swing.table2.PanelPage panelPage1;
    private gui.swing.panel.PanelShadow panelShadow2;
    private gui.swing.panel.PanelShadow pnlForm;
    private gui.swing.table2.MyTableFlatlaf tblHoaDon;
    // End of variables declaration//GEN-END:variables
}
