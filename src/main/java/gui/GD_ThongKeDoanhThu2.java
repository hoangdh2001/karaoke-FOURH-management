package gui;

import dao.HoaDon_DAO;
import dao.LoaiPhong_DAO;
import entity.HoaDon;
import entity.LoaiPhong;
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
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import objectcombobox.ObjectComboBox;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import service.HoaDonService;
import service.LoaiPhongService;

public class GD_ThongKeDoanhThu2 extends javax.swing.JPanel {

    private HoaDonService hoaDonService;
    private LoaiPhongService loaiPhongService;
    private final DecimalFormat df  = new DecimalFormat("#,##0.00");
    private final SimpleDateFormat gio  = new SimpleDateFormat("yyyy-MM-dd");

    public GD_ThongKeDoanhThu2() {
        this.hoaDonService = new HoaDon_DAO();
        this.loaiPhongService = new LoaiPhong_DAO();
        initComponents();
        buildDisplay();

    }

    private void buildDisplay() {
        createForm();
        createTable();
        createChart();
    }
    
    private void createForm() {
        init();
        addAction();
    }
    
    private void createTable() {
        tblThongKe.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 14));
    }

    private void init() {
        List<LoaiPhong> dsLoaiPhong = loaiPhongService.getDsLoaiPhong();
        DefaultComboBoxModel<Object> cmbLoaiPhongModel = new DefaultComboBoxModel<>();
        cmbLoaiPhongModel.addElement("--Tất cả--");
        for (LoaiPhong loaiPhong : dsLoaiPhong) {
            cmbLoaiPhongModel.addElement(loaiPhong);
        }
        cmbKhac.setModel(cmbLoaiPhongModel);
    }
    
    private void addAction(){
        dscKetThuc.addPropertyChangeListener(new createPropertyChangeListener());
        dscBatDau.addPropertyChangeListener(new createPropertyChangeListener());
        cmbTKTheo.addActionListener(new createActionListener());
        cmbTKChiTiet.addActionListener(new createActionListener());
        btnXuat.addActionListener(new createActionListener());
        cmbKhac.addActionListener(new createActionListener());
    }
    
    private void removeData(){
        DefaultTableModel df = (DefaultTableModel)tblThongKe.getModel();
        df.setRowCount(0);
    }
    
    private void resetCalendar(){
        dscBatDau.setCalendar(null);
        dscKetThuc.setCalendar(null);
    }
    
    private void resetCombobox(){
        cmbTKTheo.setSelectedIndex(0);
        cmbTKChiTiet.removeAllItems();
        cmbTKChiTiet.addItem("--Chọn--");
    }
    
    private void loadData(List<HoaDon> dsHoaDon){
        removeData();
        if((dsHoaDon.size() == 0 || dsHoaDon == null)&& valiData()){
            showMsg("Không có hóa đơn nào vào tháng " + cmbTKChiTiet.getSelectedItem().toString());
            return;
        }else if(dsHoaDon.size() == 0 || dsHoaDon == null ){
            showMsg("Không có hóa đơn nào từ ngày "+gio.format(dscBatDau.getDate())+" đến ngày "+ gio.format(dscKetThuc.getDate()));
            return;
        }
        
        for (int i = 0; i < dsHoaDon.size(); i++) {
            tblThongKe.addRow(dsHoaDon.get(i).convertToRowTableInGDThongKeDoanhThu());
        }
        
        txtSoHD.setText(String.valueOf(tblThongKe.getRowCount()));
    }
    
    private double tinhTong(List<HoaDon> dsHoaDon){
        double tong = 0;
        for (int i = 0; i < dsHoaDon.size(); i++) {
            tong += dsHoaDon.get(i).getTongHoaDon();
        }
        return tong;
    }
    
    private void showMsg(String msg) {
	JOptionPane.showMessageDialog(null, msg);
    }
    
    private boolean valiData(){
        if(cmbTKTheo.getSelectedIndex() == 0 || cmbTKChiTiet.getSelectedIndex() == 0){
            return false;
        } 
        return true;
    }
    
    private void filter (){
                    int thangOrNam = 0;
                    boolean theothang = true;
                    String ma = null;
                    int year = 0;
                    
                    if(cmbTKTheo.getSelectedIndex() == 1){
                        thangOrNam = cmbTKChiTiet.getSelectedIndex();  
                        theothang = true;
                        Date date = new Date();
                        Calendar calendar = new GregorianCalendar();
                        calendar.setTime(date);
                        year = calendar.get(Calendar.YEAR);
                        
                    }else {
                        thangOrNam = Integer.parseInt(cmbTKChiTiet.getSelectedItem().toString());  
                        theothang = false;
                    }
                    
                    if(cmbKhac.getSelectedIndex() == 0){
                       ma = null;
                    }else{
                       ObjectComboBox cb = (ObjectComboBox)cmbKhac.getSelectedItem();
                       ma = cb.getMa();
                    }
                    System.out.println(ma);
                    
                    double tongCu = 0;
                    List<HoaDon> dsHoaDon = hoaDonService.findHoaDonByThangNam(thangOrNam,ma,theothang,year);
                    List<HoaDon> dsHoaDonCu = hoaDonService.findHoaDonByThangNam(thangOrNam - 1,ma,theothang,year);
//                    for (int i = 0; i < dsHoaDon.size(); i++) {
//                        tongCu += dsHoaDon.get(i).getTongHoaDon();
//                        tblThongKe.addRow(dsHoaDon.get(i).convertToRowTableInGDThongKeDoanhThu());
//                    }
                    loadData(dsHoaDon);
        
        txtSoHD.setText(String.valueOf(tblThongKe.getRowCount()));
        txtTong.setText(df.format(tinhTong(dsHoaDon)));
        txtChenhLech.setText(df.format(tinhTong(dsHoaDon) - tinhTong(dsHoaDonCu)));
    }
    
    private void filterDateChooser(){
        String ma = null;
        if(cmbKhac.getSelectedIndex() == 0){
                       ma = null;
                    }else{
                       LoaiPhong cb = (LoaiPhong )cmbKhac.getSelectedItem();
                       ma = cb.getMaLoaiPhong();
                    }
        
        String batDau = gio.format(dscBatDau.getDate());
        String ketThuc = gio.format(dscKetThuc.getDate());
        List<HoaDon> dsHoaDon = hoaDonService.findHoaDon(batDau, ketThuc,ma);
        loadData(dsHoaDon);
    }
    
    public void exportTableToExcel() throws FileNotFoundException{
        
        FileOutputStream excelFos = null;
        Workbook excelJTableExport = null;
        try {
            
            JFileChooser excelFileChooser = new JFileChooser("D:\\");
            excelFileChooser.setDialogTitle("Save As ..");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "xlsm");
            excelFileChooser.setFileFilter(fnef);
            int chooser = excelFileChooser.showSaveDialog(null);
            
            Workbook wb = new XSSFWorkbook(); //Excell workbook
            Sheet sheet = wb.createSheet(); //WorkSheet
            Row row = sheet.createRow(2); //Row created at line 3
            TableModel model = tblThongKe.getModel(); //Table model
            
            if (chooser == JFileChooser.APPROVE_OPTION) {
                Row headerRow = sheet.createRow(0);
                for(int headings = 0; headings < model.getColumnCount(); headings++){ 
                    headerRow.createCell(headings).setCellValue(model.getColumnName(headings));
                }
                for(int rows = 0; rows < model.getRowCount(); rows++){ 
                    for(int cols = 0; cols < tblThongKe.getColumnCount(); cols++){ 
                        row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); 
                    }

                    row = sheet.createRow((rows + 3)); 
                }
                wb.write(new FileOutputStream(excelFileChooser.getSelectedFile()+ ".xlsx")); 
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
            Object obj =e.getSource();
            if(obj.equals(cmbTKTheo)){
                if(cmbTKTheo.getSelectedIndex() == 1){
                    cmbTKChiTiet.removeAllItems();
                    lblChenhLech.setText("Chênh lệch so với tháng trước:");
                    resetCalendar();
                    cmbTKChiTiet.addItem("--Chọn tháng--");
                    for (int i =0; i< 12; i++){
                        cmbTKChiTiet.addItem(i+1);
                    }
                }else if(cmbTKTheo.getSelectedIndex() == 2){
                    resetCalendar();
                    lblChenhLech.setText("Chênh lệch so với năm trước  :");
                    cmbTKChiTiet.removeAllItems();
                    cmbTKChiTiet.addItem("--Chọn năm--");
                    
                    hoaDonService.getDSNamTheoNgayLap().forEach(doc -> cmbTKChiTiet.addItem(doc));
                }else if(cmbTKTheo.getSelectedIndex() == 0){
                    cmbTKChiTiet.removeAllItems();
                    cmbTKChiTiet.addItem("--Chọn--");
                }
            }else if(obj.equals(cmbTKChiTiet)){
                if(cmbTKChiTiet.getSelectedIndex() != -1 && cmbTKChiTiet.getSelectedIndex() != 0){
                    filter();
                }
            }else if(obj.equals(cmbKhac)){
                if(cmbTKChiTiet.getSelectedIndex() != -1 && cmbTKChiTiet.getSelectedIndex() != 0){
                    filter();
                }else{
                    filterDateChooser();
                }
            }else if(obj.equals(btnXuat)){
                try {
                    exportTableToExcel();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GD_XemDichVu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        
        }
    }
    
    private boolean checkDate(){
        if(dscBatDau.getDate() == null || dscKetThuc.getDate() == null){
            return false;
        }
        
        if(dscBatDau.getDate().getTime() > dscKetThuc.getDate().getTime()){
            showMsg("Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
            return false;
        }
        
        return true;
    }
    
    private class createPropertyChangeListener implements PropertyChangeListener{
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
                if(checkDate()){
                    resetCombobox();
                    filterDateChooser();
                }
        }
    }
    
    private void createChart() {
        chart.setDescription("Tổng tiền");
        chart.addLegend("Doanh thu", new Color(12, 84, 175));
    }

    private void loadDataChart() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                sleep();
                Map<Integer, Double> rs = hoaDonService.getDoanhThuHoaDonTheoNam(2021);
                if (rs != null) {
                    hiddenLoading();
                    rs.entrySet()
                            .iterator()
                            .forEachRemaining(entry -> {
                                chart.addData(new ModelChart("Tháng " + entry.getKey(), new double[]{entry.getValue()}));
                            });
                    chart.start();
                }
            }
        }).start();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GD_ThongKeDoanhThu2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void hiddenLoading() {
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void begin() {
                chart.setVisible(true);
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
        tblThongKe = new gui.swing.table2.MyTableFlatlaf();
        jPanel6 = new javax.swing.JPanel();
        lblChenhLech = new javax.swing.JLabel();
        txtChenhLech = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtSoHD = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTong = new javax.swing.JTextField();
        btnXuat = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        pnlLoading = new gui.component.PanelLoading();
        chart = new gui.swing.chart.Chart();

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
                .addContainerGap(8, Short.MAX_VALUE))
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
        cmbTKChiTiet.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2021", "2020" }));

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
        tblThongKe.setRowHeight(40);
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

        btnXuat.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnXuat.setText("Xuất danh sách");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblChenhLech)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtChenhLech, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSoHD, javax.swing.GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTong, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXuat, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtSoHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtChenhLech, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblChenhLech, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(btnXuat, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(txtTong, javax.swing.GroupLayout.Alignment.LEADING)))
                .addContainerGap())
        );

        jPanel2.add(jPanel6, java.awt.BorderLayout.PAGE_END);

        jTabbedPane1.addTab("Bảng thống kê", jPanel2);

        jPanel3.setOpaque(false);
        jPanel3.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Tổng doanh thu");
        jPanel3.add(jLabel5, java.awt.BorderLayout.SOUTH);

        jPanel5.setOpaque(false);
        jPanel5.setLayout(new java.awt.CardLayout());

        pnlLoading.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Ellipsis-1s-58px.gif"))); // NOI18N
        jPanel5.add(pnlLoading, "card2");
        jPanel5.add(chart, "card3");

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jTabbedPane1.addTab("Biểu đồ", jPanel3);

        pnlCenter.add(jTabbedPane1, "card2");

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 1) {
            chart.clear();
            pnlLoading.setVisible(true);
            chart.setVisible(false);
            loadDataChart();

        }
    }//GEN-LAST:event_jTabbedPane1StateChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXuat;
    private gui.swing.chart.Chart chart;
    private javax.swing.JComboBox<Object> cmbKhac;
    private javax.swing.JComboBox<Object> cmbTKChiTiet;
    private javax.swing.JComboBox<String> cmbTKTheo;
    private com.toedter.calendar.JDateChooser dscBatDau;
    private com.toedter.calendar.JDateChooser dscKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblChenhLech;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.component.PanelLoading pnlLoading;
    private gui.swing.panel.PanelShadow pnlTop;
    private gui.swing.table2.MyTableFlatlaf tblThongKe;
    private javax.swing.JTextField txtChenhLech;
    private javax.swing.JTextField txtSoHD;
    private javax.swing.JTextField txtTong;
    // End of variables declaration//GEN-END:variables
}
