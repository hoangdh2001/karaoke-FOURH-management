package gui;

import dao.HoaDon_DAO;
import dao.LoaiPhong_DAO;
import entity.HoaDon;
import entity.LoaiPhong;
import gui.swing.event.EventPagination;
import gui.swing.model.ModelChart;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.HoaDonService;
import service.LoaiPhongService;

public class GD_ThongKeDoanhThu extends javax.swing.JPanel {

    private HoaDonService hoaDonService;
    private LoaiPhongService loaiPhongService;
    private final DecimalFormat df = new DecimalFormat("#,###");
    private final SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd");
    private List<HoaDon> dsHoaDon;
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private List<LoaiPhong> dsLoaiPhong;

    public GD_ThongKeDoanhThu() {
        this.hoaDonService = new HoaDon_DAO();
        this.loaiPhongService = new LoaiPhong_DAO();
        initComponents();
        buildDisplay();

    }

    private void buildDisplay() {
        createForm();
        createTable();
        createChart();
        createPanelBottom();
    }

    private void createForm() {
        init();
        addAction();
    }

    private void createTable() {
        tblThongKe.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 14));
    }

    private void init() {
        dsLoaiPhong = loaiPhongService.getDsLoaiPhong();
        DefaultComboBoxModel<Object> cmbLoaiPhongModel = new DefaultComboBoxModel<>();
        cmbLoaiPhongModel.addElement("--Tất cả--");
        for (LoaiPhong loaiPhong : dsLoaiPhong) {
            cmbLoaiPhongModel.addElement(loaiPhong.getTenLoaiPhong());
        }
        cmbKhac.setModel(cmbLoaiPhongModel);
    }

    private void addAction() {
        dscKetThuc.addPropertyChangeListener(new createPropertyChangeListener());
        dscBatDau.addPropertyChangeListener(new createPropertyChangeListener());
        cmbTKTheo.addActionListener(new createActionListener());
        cmbTKChiTiet.addActionListener(new createActionListener());
        btnXuat.addActionListener(new createActionListener());
        cmbKhac.addActionListener(new createActionListener());
    }

    private void removeData() {
        DefaultTableModel df = (DefaultTableModel) tblThongKe.getModel();
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

    private void loadData(List<HoaDon> dsHoaDon) {
        removeData();
        if ((dsHoaDon.size() == 0 || dsHoaDon == null) && valiData()) {
            showMsg("Không có hóa đơn nào vào tháng " + cmbTKChiTiet.getSelectedItem().toString());
            return;
        } else if (dsHoaDon.size() == 0 || dsHoaDon == null) {
            showMsg("Không có hóa đơn nào từ ngày " + gio.format(dscBatDau.getDate()) + " đến ngày " + gio.format(dscKetThuc.getDate()));
            return;
        }

        for (HoaDon hoaDon : dsHoaDon) {
            tblThongKe.addRow(new Object[]{hoaDon.getMaHoaDon(), 
                gio.format(hoaDon.getNgayLapHoaDon()), 
                hoaDon.getKhachHang() == null ? "Trống":hoaDon.getKhachHang().getTenKhachHang(),
                hoaDon.getNhanVien().getTenNhanVien(),
                df.format(hoaDon.getTongHoaDon())});
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

    private void filter(int page) {
        String ma = getMaLoaiPhong();
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
        List<HoaDon> dsHoaDonPage = hoaDonService.findHoaDonByThangNam(thangOrNam, ma, theothang, year,page);
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadData(dsHoaDonPage);
            }
        }).start();
    }
    
    private String getMaLoaiPhong(){
        if (cmbKhac.getSelectedIndex() == 0) {
            return null;
        } else {
            LoaiPhong cb = (LoaiPhong) cmbKhac.getSelectedItem();
            return cb.getMaLoaiPhong();
        }
    }
    
    private void loadAllPage(){
        String ma = getMaLoaiPhong();
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
            double tongCu = 0;
            double tongMoi = hoaDonService.getTotalOfRecord(thangOrNam, ma, theothang, year);
            if(thangOrNam == 1){
                tongCu = hoaDonService.getTotalOfRecord(12, ma, theothang, year -1);
            }else{
                tongCu = hoaDonService.getTotalOfRecord(thangOrNam - 1, ma, theothang, year);
            }
            
            page = hoaDonService.getNumOfRecord(thangOrNam, ma, theothang, year);
            txtChenhLech.setText(df.format(tongMoi - tongCu));
            txtTong.setText(df.format(hoaDonService.getTotalOfRecord(thangOrNam, ma, theothang, year)));
            
        }else{
            String batDau = gio.format(dscBatDau.getDate());
            String ketThuc = gio.format(dscKetThuc.getDate());
            page = hoaDonService.getNumOfRecordByDate(batDau, ketThuc, ma);   
            txtTong.setText(df.format(hoaDonService.getTotalOfRecordByDate(batDau, ketThuc, ma)));
        }
        loadPageInt(page);
        txtSoHD.setText(String.valueOf(page));
    }

    private void filterDateChooser(int page) {
        String ma = null;
        if (cmbKhac.getSelectedIndex() == 0) {
            ma = null;
        } else {
            LoaiPhong cb = (LoaiPhong) cmbKhac.getSelectedItem();
            ma = cb.getMaLoaiPhong();
        }
        
        String batDau = gio.format(dscBatDau.getDate());
        String ketThuc = gio.format(dscKetThuc.getDate());
        
        List<HoaDon> dsHoaDonPage = hoaDonService.findHoaDon(batDau, ketThuc, ma,page);
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadData(dsHoaDonPage);
            }
        }).start();
    }

    public void exportTableToExcel() throws FileNotFoundException {

        FileOutputStream excelFos = null;
        Workbook excelJTableExport = null;
        try {

            JFileChooser excelFileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory().toPath().toString());
            excelFileChooser.setDialogTitle("Save As ..");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "xlsm");
            excelFileChooser.setFileFilter(fnef);
            int chooser = excelFileChooser.showSaveDialog(null);

            Workbook wb = new XSSFWorkbook(); //Excell workbook
            Sheet sheet = wb.createSheet(); //WorkSheet
            Row row = sheet.createRow(2); //Row created at line 3
            TableModel model = tblThongKe.getModel(); //Table model
            
            List<HoaDon> hoaDons = null;
            String ma = getMaLoaiPhong();
            String fileName = " Thống kê ";
            if(cmbTKTheo.getSelectedIndex() != 0){
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
                
                if(cmbTKTheo.getSelectedIndex() == 1){
                    fileName += "Tháng " + cmbTKChiTiet.getSelectedItem().toString() +" "+ cmbKhac.getSelectedItem().toString();
                }else if(cmbTKTheo.getSelectedIndex() == 2){
                    fileName += "Năm " + cmbTKChiTiet.getSelectedItem().toString() +" "+ cmbKhac.getSelectedItem().toString();
                }
                hoaDons = hoaDonService.findHoaDonByThangNam(thangOrNam,ma, theothang, year, -1);
            }else if(checkDate()){
                    String batDau = gio.format(dscBatDau.getDate());
                    String ketThuc = gio.format(dscKetThuc.getDate());
                    fileName += "Từ " + batDau + " đến " + ketThuc;
                hoaDons = hoaDonService.findHoaDon(batDau, ketThuc, ma, -1);
            }
            
            if (chooser == JFileChooser.APPROVE_OPTION) {
                Row headerRow = sheet.createRow(0);
                for (int headings = 0; headings < model.getColumnCount(); headings++) {
                    headerRow.createCell(headings).setCellValue(model.getColumnName(headings));
                }
                for (int rows = 0; rows < hoaDons.size(); rows++) {
                    HoaDon hd = hoaDons.get(rows);
                    row.createCell(0).setCellValue(hd.getMaHoaDon());
                    row.createCell(1).setCellValue(hd.getNgayLapHoaDon().toString());
                    row.createCell(2).setCellValue(hd.getKhachHang().getTenKhachHang());
                    row.createCell(3).setCellValue(hd.getNhanVien().getTenNhanVien());
                    row.createCell(4).setCellValue(hd.getTongHoaDon());

                    row = sheet.createRow((rows + 3));
                }
                System.out.println(fileName);
                wb.write(new FileOutputStream(excelFileChooser.getSelectedFile()+ fileName +".xlsx"));
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (excelFos != null) {
                    excelFos.close();
                }
                if (excelJTableExport != null) {
                    excelJTableExport.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    private class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj.equals(cmbTKTheo)) {
                if (cmbTKTheo.getSelectedIndex() == 1) {
                    cmbTKChiTiet.removeAllItems();
                    lblChenhLech.setText("Chênh lệch so với tháng trước:");
                    resetCalendar();
                    jLabel8.setText("Tháng");
                    cmbTKChiTiet.addItem("--Chọn tháng--");
                    for (int i = 0; i < 12; i++) {
                        cmbTKChiTiet.addItem(i + 1);
                    }
                } else if (cmbTKTheo.getSelectedIndex() == 2) {
                    resetCalendar();
                    lblChenhLech.setText("Chênh lệch so với năm trước  :");
                    cmbTKChiTiet.removeAllItems();
                    cmbTKChiTiet.addItem("--Chọn năm--  ");
                    jLabel8.setText("Năm");
                    hoaDonService.getDSNamTheoNgayLap().forEach(doc -> cmbTKChiTiet.addItem(doc));
                } else if (cmbTKTheo.getSelectedIndex() == 0) {
                    cmbTKChiTiet.removeAllItems();
                    cmbTKChiTiet.addItem("--Chọn--       ");
                }
            } else if (obj.equals(cmbTKChiTiet)) {
                if (cmbTKChiTiet.getSelectedIndex() != -1 && cmbTKChiTiet.getSelectedIndex() != 0) {
                    loadAllPage();
                    filter(0);
                    loadDataChart();
                }
            } else if (obj.equals(cmbKhac)) {
                if (cmbTKChiTiet.getSelectedIndex() != -1 && cmbTKChiTiet.getSelectedIndex() != 0) {
                    loadAllPage();
                    filter(0);
                } else {
                    loadAllPage();
                    filterDateChooser(0);
                }
            } else if (obj.equals(btnXuat)) {
                try {
                    exportTableToExcel();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GD_XemMatHang.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private void loadPage(List<HoaDon> dsHoaDon) {
        int soLuong = dsHoaDon.size();
        pnlPage.init(soLuong% 20 == 0 ? soLuong / 20 : (soLuong / 20) + 1);
    }
    
    private void loadPageInt(int soLuong) {
        pnlPage.init(soLuong% 20 == 0 ? soLuong / 20 : (soLuong / 20) + 1);
    }
    
    private void createPanelBottom() {
        pnlPage.addEventPagination(new EventPagination() {
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

    private void createChart() {
//        chart.setDescription("Tổng tiền");
        chart.addLegend("Doanh thu", new Color(12, 84, 175), new Color(0, 108, 247));
    }

    private void loadDataChart() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                chart.clear();
                if (cmbTKTheo.getSelectedIndex() == 1) {
                    int thang = 0;
                    try {
                        thang = Integer.parseInt(cmbTKChiTiet.getSelectedItem().toString());
                    } catch (Exception e) {

                    }
                    sleep();
                    Map<Integer, Double> rs = hoaDonService.getDoanhThuHoaDonTheoThang(thang, 2021);
                    System.err.println(rs);
                    if (rs != null) {
                        rs.entrySet()
                                .iterator()
                                .forEachRemaining(entry -> {
                                    chart.addData(new ModelChart(entry.getKey() + "", new double[]{entry.getValue()}));
                                });
                        chart.start();
                    }
                } else if (cmbTKTheo.getSelectedIndex() == 2) {
                    int nam = 0;
                    try {
                        nam = Integer.parseInt(cmbTKChiTiet.getSelectedItem().toString());
                    } catch (Exception e) {

                    }
                    sleep();
                    Map<Integer, Double> rs = hoaDonService.getDoanhThuHoaDonTheoNam(nam);
                    if (rs != null) {
                        rs.entrySet()
                                .iterator()
                                .forEachRemaining(entry -> {
                                    chart.addData(new ModelChart("Tháng " + entry.getKey(), new double[]{entry.getValue()}));
                                });
                        chart.start();
                    }
                } else {
                    return;
                }
            }
        }).start();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GD_ThongKeDoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dscBatDau = new com.toedter.calendar.JDateChooser();
        dscKetThuc = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        cmbTKTheo = new javax.swing.JComboBox<>();
        cmbTKChiTiet = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        cmbKhac = new javax.swing.JComboBox<>();
        jLabel8 = new javax.swing.JLabel();
        pnlCenter = new gui.swing.panel.PanelShadow();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblThongKe = new gui.swing.table.MyTableFlatlaf();
        jPanel6 = new javax.swing.JPanel();
        lblChenhLech = new javax.swing.JLabel();
        txtChenhLech = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSoHD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTong = new javax.swing.JTextField();
        btnXuat = new javax.swing.JButton();
        pnlPage = new gui.swing.table.PanelPage();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        chart = new gui.swing.chart.LineChart();

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
        jLabel1.setText("Thống kê doanh thu");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 933, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

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

        cmbTKChiTiet.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbTKChiTiet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Chọn--" }));
        cmbTKChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTKChiTietActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Phòng:");

        cmbKhac.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbKhac.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "--Tất cả--" }));

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setText("Năm:");

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
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbTKChiTiet, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(43, 43, 43)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbKhac, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dscBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(pnlTopLayout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(cmbKhac, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                            .addComponent(dscKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTKTheo)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlTopLayout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cmbTKChiTiet))))
                .addGap(32, 32, 32))
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
        jPanel2.setLayout(new java.awt.BorderLayout());

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày lập hóa đơn", "Khách hàng", "Nhân viên", "Tổng hóa đơn'"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblThongKe.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tblThongKe.setRowHeight(40);
        tblThongKe.setSelectionBackground(new java.awt.Color(239, 244, 255));
        tblThongKe.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblThongKe.setShowGrid(true);
        tblThongKe.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblThongKe);
        if (tblThongKe.getColumnModel().getColumnCount() > 0) {
            tblThongKe.getColumnModel().getColumn(1).setResizable(false);
            tblThongKe.getColumnModel().getColumn(3).setResizable(false);
            tblThongKe.getColumnModel().getColumn(4).setResizable(false);
        }

        jPanel2.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        jPanel6.setOpaque(false);

        lblChenhLech.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblChenhLech.setText("Chênh lệch so với  tháng trước:");

        txtChenhLech.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtChenhLech.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Tổng số hóa đơn: ");

        txtSoHD.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtSoHD.setEnabled(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Tổng hóa đơn: ");

        txtTong.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTong.setEnabled(false);

        btnXuat.setBackground(new java.awt.Color(54, 88, 153));
        btnXuat.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnXuat.setForeground(new java.awt.Color(255, 255, 255));
        btnXuat.setText("Xuất danh sách");

        pnlPage.setOpaque(false);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlPage, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(lblChenhLech)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtChenhLech, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSoHD, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTong, javax.swing.GroupLayout.DEFAULT_SIZE, 204, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnXuat))))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlPage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(txtChenhLech)
                        .addComponent(lblChenhLech, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTong)
                        .addComponent(btnXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtSoHD)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel2.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab("Bảng thống kê", jPanel2);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Biểu đồ thể hiện tổng doanh thu");
        jPanel3.add(jLabel9, java.awt.BorderLayout.PAGE_START);
        jPanel3.add(chart, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Biểu đồ", jPanel3);

        pnlCenter.add(jTabbedPane1, "card2");

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
//        if (jTabbedPane1.getSelectedIndex() == 1) {
//            loadDataChart();
//
//        }
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void cmbTKChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTKChiTietActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbTKChiTietActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuat;
    private gui.swing.chart.LineChart chart;
    private javax.swing.JComboBox<Object> cmbKhac;
    private javax.swing.JComboBox<Object> cmbTKChiTiet;
    private javax.swing.JComboBox<String> cmbTKTheo;
    private com.toedter.calendar.JDateChooser dscBatDau;
    private com.toedter.calendar.JDateChooser dscKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChenhLech;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.table.PanelPage pnlPage;
    private gui.swing.panel.PanelShadow pnlTop;
    private gui.swing.table.MyTableFlatlaf tblThongKe;
    private javax.swing.JTextField txtChenhLech;
    private javax.swing.JTextField txtSoHD;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
