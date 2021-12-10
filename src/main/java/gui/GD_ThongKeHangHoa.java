package gui;

import dao.HoaDon_DAO;
import dao.LoaiDichVu_DAO;
import dao.MatHang_DAO;
import dao.NhaCungCapVaNhapHang_DAO;
import entity.HoaDon;
import entity.LoaiPhong;
import gui.swing.event.EventPagination;
import gui.swing.model.ModelChart;
import gui.swing.table2.MyTableFlatlaf;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.HoaDonService;
import service.LoaiDichVuService;
import service.MatHangService;
import service.NhaCungCapVaNhapHangDaoService;

public class GD_ThongKeHangHoa extends javax.swing.JPanel {
    private final DecimalFormat df = new DecimalFormat("#,###");
    private final DecimalFormat dfPercent = new DecimalFormat("#,##%");
    private final SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd");
    private LoaiDichVuService loaiDichVuDao;
    private MatHangService matHangDao;
    private HoaDonService hoaDonDao;
    private NhaCungCapVaNhapHangDaoService nhaCungCapVaNhapHang_DAO;
    public GD_ThongKeHangHoa() {
        initComponents();
        buildDisplay();
    }
    
    private void buildDisplay() {
        createChart();
        createPanelBottom();
        init();
        addAction();
        
    }
    
    private void createChart() {
        barChart.addLegend("Income", new Color(0, 108, 247), new Color(0, 108, 247));
        barChart.addLegend("Expense", new Color(104, 49, 200), new Color(0, 108, 247));
        barChart.addLegend("Profit", new Color(95, 209, 69), new Color(0, 108, 247));
        barChart.addLegend("Cost", new Color(241, 100, 120), new Color(0, 108, 247));
    }
    
    private void loadDataChart() {
        barChart.addData(new ModelChart("January", new double[]{500, 200, 80, 89}));
        barChart.addData(new ModelChart("February", new double[]{600, 750, 90, 150}));
        barChart.addData(new ModelChart("March", new double[]{200, 350, 460, 900}));
        barChart.addData(new ModelChart("April", new double[]{480, 150, 750, 700}));
        barChart.addData(new ModelChart("May", new double[]{350, 540, 300, 150}));
        barChart.addData(new ModelChart("June", new double[]{190, 280, 81, 200}));
        barChart.start();
    }
    
    private void hiddenLoading() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                barChart.setVisible(true);
            }

            @Override
            public void timingEvent(float fraction) {
                pnlLoading.setAlpha(1f - fraction);
                repaint();
            }

            @Override
            public void end() {
                pnlLoading.setVisible(false);
            }
        };
        Animator animator2 = new Animator(200, target);
        animator2.setResolution(0);
        animator2.setAcceleration(0.5f);
        animator2.start();
    }
    
    private void init() {
        loaiDichVuDao = new LoaiDichVu_DAO();
        matHangDao = new MatHang_DAO();
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
        hoaDonDao = new HoaDon_DAO();
    }

    
    private void addAction() {
        dscKetThuc.addPropertyChangeListener(new createPropertyChangeListener());
        dscBatDau.addPropertyChangeListener(new createPropertyChangeListener());
        cmbTKTheo.addActionListener(new createActionListener());
        cmbTKChiTiet.addActionListener(new createActionListener());
    }
    
    private void removeData(MyTableFlatlaf table) {
        DefaultTableModel df = (DefaultTableModel) table.getModel();
        df.setRowCount(0);
    }

    private void resetCalendar() {
        dscBatDau.setCalendar(null);
        dscKetThuc.setCalendar(null);
    }
    
    private void resetCombobox() {
        cmbTKTheo.setSelectedIndex(0);
        cmbTKChiTiet.removeAllItems();
        cmbTKChiTiet.addItem("--Chọn--");
    }
    
    private void loadAllPage(){
        int page = 0;
        if (cmbTKTheo.getSelectedIndex() != 0) {
            int thangOrNam = 0;
            boolean theothang = true;
            int year = 0;
            if (cmbTKTheo.getSelectedIndex() == 1) {
                thangOrNam = cmbTKChiTiet.getSelectedIndex();
                theothang = true;
                Date date = new Date();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                year = calendar.get(Calendar.YEAR);
            } else {
                thangOrNam = Integer.parseInt(cmbTKChiTiet.getSelectedItem().toString());
                theothang = false;
            }
            page = matHangDao.getPage(thangOrNam, theothang,year);
        }else{
            String batDau = gio.format(dscBatDau.getDate());
            String ketThuc = gio.format(dscKetThuc.getDate());
            page = matHangDao.getPageByDate(batDau, ketThuc);
        }
        
        loadPageInt(page);
    }
    
    private void filter(int page){
        int thangOrNam = 0;
            boolean theothang = true;
            int year = 0;

            if (cmbTKTheo.getSelectedIndex() == 1) {
                thangOrNam = cmbTKChiTiet.getSelectedIndex();
                theothang = true;
                Date date = new Date();
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(date);
                year = calendar.get(Calendar.YEAR);

            } else {
                thangOrNam = Integer.parseInt(cmbTKChiTiet.getSelectedItem().toString());
                theothang = false;
            }
            
            List<String> percentLoai = loaiDichVuDao.getPercent(thangOrNam,theothang, year);
            List<String> test = matHangDao.getListTK(thangOrNam,theothang, year,page);
            new Thread(new Runnable() {
            @Override
            public void run() {
                loadDataTableBanChay(test);
                loadDataTypePercent(percentLoai);
            }
        }).start();
            double tong = matHangDao.getTotal(thangOrNam, theothang, year);
            txtTongSPBanChay.setText(df.format(tong));
    }
    
    private void filterDateChooser(int page) {
        String batDau = gio.format(dscBatDau.getDate());
        String ketThuc = gio.format(dscKetThuc.getDate());
        
        List<String> percentLoai = loaiDichVuDao.getPercent(batDau, ketThuc);
        List<String> test = matHangDao.getListTKByDate(batDau, ketThuc,page);
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadDataTableBanChay(test);
                loadDataTypePercent(percentLoai);
            }
        }).start();
        double tong = matHangDao.getTotalBydate(batDau, ketThuc);
            txtTongSPBanChay.setText(df.format(tong));
    }
    
    private void loadDataTypePercent(List<String> list){
        removeData(tblPhanTram);
        double tong = 0;
        for(String i: list){
            String sl = i.split(";")[1];
            tong += Double.parseDouble(sl);
        }
        double tongCong = 0;
        for(String i: list){
            String [] row = i.split(";");
            tblPhanTram.addRow(new Object[]{row[0],row[1],dfPercent.format(Double.parseDouble(row[1])/tong),df.format(Double.parseDouble(row[2]))});
            tongCong+= Double.parseDouble(row[2]);
        }
        
        txtTongTieuThu.setText(df.format(tongCong));
    }
    
    private void loadDataTableBanChay(List<String> list){
        removeData(tblSPBanChay);
        for(String i: list){
            String [] row = i.split(";");
            tblSPBanChay.addRow(new Object[]{row[0],row[1],row[2],row[3],df.format(Double.parseDouble(row[4].toString()))});
        }
        
    }
    
    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }
    
    private boolean valiData() {
        if (cmbTKTheo.getSelectedIndex() == 0 || cmbTKChiTiet.getSelectedIndex() == 0) {
            return false;
        }
        return true;
    }
    
    private class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj.equals(cmbTKTheo)) {
                if (cmbTKTheo.getSelectedIndex() == 1) {
                    cmbTKChiTiet.removeAllItems();
                    resetCalendar();
                    lblThangNam.setText("Tháng");
                    cmbTKChiTiet.addItem("--Chọn tháng--");
                    for (int i = 0; i < 12; i++) {
                        cmbTKChiTiet.addItem(i + 1);
                    }
                } else if (cmbTKTheo.getSelectedIndex() == 2) {
                    resetCalendar();
                    cmbTKChiTiet.removeAllItems();
                    cmbTKChiTiet.addItem("--Chọn năm--  ");
                    lblThangNam.setText("Năm");
//                    nhaCungCapVaNhapHang_DAO.getAllYearExist().forEach(doc -> cmbTKChiTiet.addItem(doc));
                    hoaDonDao.getDSNamTheoNgayLap().forEach(doc -> cmbTKChiTiet.addItem(doc));
                } else if (cmbTKTheo.getSelectedIndex() == 0) {
                    cmbTKChiTiet.removeAllItems();
                    cmbTKChiTiet.addItem("--Chọn--       ");
                }
            } else if (obj.equals(cmbTKChiTiet)) {
                if (cmbTKChiTiet.getSelectedIndex() != -1 && cmbTKChiTiet.getSelectedIndex() != 0) {
                    loadAllPage();
                    filter(0);
//                    loadDataChart();
                }
            }
        }
    }

    private boolean checkDate() {
        if (dscBatDau.getDate() == null || dscKetThuc.getDate() == null) {
            return false;
        }

        if (dscBatDau.getDate().getTime() > dscKetThuc.getDate().getTime()) {
            showMsg("Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
            return false;
        }

        return true;
    }

    private class createPropertyChangeListener implements PropertyChangeListener {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            if (checkDate()) {
                resetCombobox();
                loadAllPage();
                filterDateChooser(0);
            }
        }
    }
    
    private void loadPageInt(int soLuong) {
        if(soLuong == 0){
            showMsg("Không có sản phẩm nào được tiêu thụ gần đây");
            return;
        }
        pnlPageBanCHay.init(soLuong% 5 == 0 ? soLuong / 5: (soLuong / 5) + 1);
    }
    
    private void createPanelBottom() {
        pnlPageBanCHay.addEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                if(cmbTKTheo.getSelectedIndex() != 0){
                    filter(pageClick);
                }else{
                    filterDateChooser(pageClick);
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cmbTKChiTiet = new javax.swing.JComboBox<>();
        lblThangNam = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dscBatDau = new com.toedter.calendar.JDateChooser();
        dscKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        cmbTKTheo = new javax.swing.JComboBox<>();
        pnlCenter = new gui.swing.panel.PanelShadow();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        pnlPageBanCHay = new gui.swing.table2.PanelPage();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSPBanChay = new gui.swing.table2.MyTableFlatlaf();
        txtTongSPBanChay = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblPhanTram = new gui.swing.table2.MyTableFlatlaf();
        txtTongTieuThu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        barChart = new gui.swing.chart.Chart();
        pnlLoading = new gui.component.PanelLoading();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 5));

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(2);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

        jPanel1.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 1, 0, new java.awt.Color(204, 204, 204)));
        jPanel1.setOpaque(false);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(68, 68, 68));
        jLabel1.setText("Thống kê hàng hóa");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 954, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cmbTKChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTKChiTiet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn--" }));
        cmbTKChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTKChiTietActionPerformed(evt);
            }
        });

        lblThangNam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblThangNam.setText("Năm:");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_advance_20px_3.png"))); // NOI18N

        dscBatDau.setDate(new Date());
        dscBatDau.setDateFormatString("dd-MM-yyyy");
        dscBatDau.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dscBatDau.setOpaque(false);

        dscKetThuc.setDateFormatString("dd-MM-yyyy");
        dscKetThuc.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        dscKetThuc.setOpaque(false);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Thống kê theo:");

        cmbTKTheo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTKTheo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn--", "Tháng", "Năm" }));

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dscBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(dscKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(41, 41, 41)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTKTheo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(48, 48, 48)
                .addComponent(lblThangNam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTKChiTiet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(248, 248, 248))
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dscBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dscKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cmbTKTheo)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblThangNam, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTKChiTiet, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))))
                .addGap(34, 34, 34))
        );

        add(pnlTop, java.awt.BorderLayout.PAGE_START);

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlCenter.setShadowOpacity(0.3F);
        pnlCenter.setShadowSize(2);
        pnlCenter.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlCenter.setLayout(new java.awt.CardLayout());

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        jPanel2.setOpaque(false);

        pnlPageBanCHay.setOpaque(false);

        tblSPBanChay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên mặt hàng", "Loại mặt hàng", "Số lượng bán ra", "Số lượng nhập", "Tổng trị giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSPBanChay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblSPBanChay.setRowHeight(40);
        tblSPBanChay.setShowGrid(true);
        tblSPBanChay.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblSPBanChay);
        if (tblSPBanChay.getColumnModel().getColumnCount() > 0) {
            tblSPBanChay.getColumnModel().getColumn(0).setPreferredWidth(350);
        }

        txtTongSPBanChay.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongSPBanChay.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tổng cộng:");

        tblPhanTram.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Loaị dịch vụ", "Số lượng tiêu thụ", "Chiếm", "Tổng trị giá"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhanTram.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblPhanTram.setRowHeight(40);
        tblPhanTram.setShowGrid(true);
        tblPhanTram.setShowVerticalLines(false);
        jScrollPane2.setViewportView(tblPhanTram);

        txtTongTieuThu.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTongTieuThu.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Tổng cộng:");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel9.setText("Tỉ lệ tiêu thụ của các loại dịch vụ");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel10.setText("Các sản phẩm bán chạy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongTieuThu, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jScrollPane1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(pnlPageBanCHay, javax.swing.GroupLayout.PREFERRED_SIZE, 733, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 109, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTongSPBanChay, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTongSPBanChay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pnlPageBanCHay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(1, 1, 1)
                .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTongTieuThu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4))
        );

        jTabbedPane1.addTab("Bảng thống kê", jPanel2);

        jPanel3.setOpaque(false);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tháng 1", "Tháng 2", "Tháng 3", "Tháng 4", "Tháng 5" }));

        jPanel4.setOpaque(false);
        jPanel4.setLayout(new java.awt.CardLayout());
        jPanel4.add(barChart, "card2");

        pnlLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Ellipsis-1s-58px.gif"))); // NOI18N
        jPanel4.add(pnlLoading, "card3");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(179, 179, 179)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Biểu đồ", jPanel3);

        pnlCenter.add(jTabbedPane1, "card2");

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 1) {
            barChart.clear();
//            pnlLoading.setVisible(true);
//            barChart.setVisible(false);
            loadDataChart();
        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void cmbTKChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTKChiTietActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTKChiTietActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.chart.Chart barChart;
    private javax.swing.JComboBox<Object> cmbTKChiTiet;
    private javax.swing.JComboBox<String> cmbTKTheo;
    private com.toedter.calendar.JDateChooser dscBatDau;
    private com.toedter.calendar.JDateChooser dscKetThuc;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblThangNam;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.component.PanelLoading pnlLoading;
    private gui.swing.table2.PanelPage pnlPageBanCHay;
    private gui.swing.panel.PanelShadow pnlTop;
    private gui.swing.table2.MyTableFlatlaf tblPhanTram;
    private gui.swing.table2.MyTableFlatlaf tblSPBanChay;
    private javax.swing.JTextField txtTongSPBanChay;
    private javax.swing.JTextField txtTongTieuThu;
    // End of variables declaration//GEN-END:variables
}
