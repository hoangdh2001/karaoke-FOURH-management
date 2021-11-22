/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.toedter.calendar.JDateChooser;
import dao.HoaDon_DAO;
import dao.LoaiPhong_DAO;
import dao.NhaCungCapVaNhapHang_DAO;
import entity.HoaDon;
import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;
import objectcombobox.ObjectComboBox;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GD_ThongKeDoanhThu extends javax.swing.JPanel {

    private JDateChooser dscBatDau;
    private JDateChooser dscKetThuc;
    
    private JLabel lblChenhLech;
    
    private MyComboBox<String> cmbTKTheo;
    private MyComboBox<String> cmbTKChiTiet;
    private MyComboBox<String> cmbKhac;
    
    private MyTextField txtTong;
    
    private Button btnXuat;
    private MyTextField txtSoHD;
    private MyTextField txtChenhLech;
    
    private DecimalFormat df;
    
    private SimpleDateFormat gio;
    
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhapHang_DAO;
    
    private HoaDon_DAO hoaDon_Dao;
    
    private HoaDon hoaDon;
    
    private LoaiPhong_DAO loaiPhong_DAO;
    
    public GD_ThongKeDoanhThu() {
        initComponents();
        buildGD();
        tblThongKe.fixTable(scrThongKe);
        init();
        addAction();
    }
    
    public void buildGD(){
        
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 14;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setPreferredSize(new Dimension(getWidth(), getHeight()));
        pnlTop.setLayout(new MigLayout());
        
        pnlTop.add(createPanelTitle(), "span,pos 0al 0al 100% n, h 40!");
        pnlThongKeTheo.setOpaque(false);
        pnlThongKeTheo.setLayout(new MigLayout("", "10[center]10[center]20 [center]10[center]20[center] 20 [center]10[center]10", "70[center] 40"));
        pnlTop.add(pnlThongKeTheo, "w 100%, h 15%, wrap");
        
        // Chọn thời gian băt đầu
        dscBatDau = new JDateChooser();
        dscBatDau.setOpaque(false);
        dscBatDau.setFocusable(false);
        dscBatDau.setDateFormatString("yyyy-MM-dd");
        dscBatDau.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(dscBatDau, "w 80%, h 36!");
        

        // Chọn thời gian kết thúc
        dscKetThuc = new JDateChooser();
        dscKetThuc.setOpaque(false);
        dscKetThuc.setDateFormatString("yyyy-MM-dd");
        dscKetThuc.setFocusable(false);
        dscKetThuc.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(dscKetThuc, "w 80%, h 36!");
        
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(20,50));
        pnlThongKeTheo.add(spr);
        
        //Thống kê theo
        JLabel lblTKTheo = new JLabel("Thống kê theo:");
        lblTKTheo.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(lblTKTheo,"align right");
        
        
        cmbTKTheo = new MyComboBox<>(new String[] {"--Chọn--","Tháng","Năm"});
        cmbTKTheo.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTKTheo.setBorderLine(true);
        cmbTKTheo.setBorderRadius(10);
        pnlThongKeTheo.add(cmbTKTheo, "w 80%, h 36!");
        
        cmbTKChiTiet = new MyComboBox<>(new String[] {"--Chọn--"});
        cmbTKChiTiet.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTKChiTiet.setBorderLine(true);
        cmbTKChiTiet.setBorderRadius(10);
        pnlThongKeTheo.add(cmbTKChiTiet, "w 80%, h 36!");
        
        //Khác
        JLabel lblKhac = new JLabel("Phòng:");
        lblKhac.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(lblKhac,"align right");
        
        cmbKhac = new MyComboBox<>(new String[] {"--Tất cả--"});
        cmbKhac.setFont(new Font(fontName, fontStyle, fontSize));
        cmbKhac.setBorderLine(true);
        cmbKhac.setBorderRadius(10);
        pnlThongKeTheo.add(cmbKhac, ",w 80%, h 36!");
       
        pnlBangThongKe.setOpaque(false);
//        pnlBangThongKe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Bảng thống kê", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));
        pnlTop.add(pnlBangThongKe, "w 100%, h 75%, wrap");//
       
          
        pnlTinhTong.setOpaque(false);
        pnlTinhTong.setLayout(new MigLayout("","10[center]5[center]10[center]5[center]10[center]5[center]10[center]10",""));
        pnlTop.add(pnlTinhTong, "w 100%, h 10%");
        
        lblChenhLech = new JLabel("Chênh lệch so với tháng trước:");
        lblChenhLech.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTinhTong.add(lblChenhLech, "align right");

        txtChenhLech = new MyTextField();
        txtChenhLech.setFont(new Font(fontName, fontStyle, fontSize));
        txtChenhLech.setBorderLine(true);
        txtChenhLech.setEnabled(false);
        txtChenhLech.setBorderRadius(5);
        pnlTinhTong.add(txtChenhLech, "w 17%, h 36!");
        
        JLabel lblSoHD = new JLabel("Tổng số hóa đơn:");
        lblSoHD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTinhTong.add(lblSoHD, "align right");

        txtSoHD = new MyTextField();
        txtSoHD.setFont(new Font(fontName, fontStyle, fontSize));
        txtSoHD.setBorderLine(true);
        txtSoHD.setEnabled(false);
        txtSoHD.setBorderRadius(5);
        pnlTinhTong.add(txtSoHD, "w 20%, h 36!");
        
        
        //Tính tổng
        JLabel lblTong = new JLabel("Tổng doanh thu:");
        lblTong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTinhTong.add(lblTong, "align right");

        txtTong = new MyTextField();
        txtTong.setFont(new Font(fontName, fontStyle, fontSize));
        txtTong.setBorderLine(true);
        txtTong.setEnabled(false);
        txtTong.setBorderRadius(5);
        pnlTinhTong.add(txtTong, "w 30%, h 36!");
        
       
        // Xuất danh sách
        btnXuat = new Button("Xuất danh sách");
        btnXuat.setFont(new Font(fontName, fontStyle, fontSize));
        btnXuat.setBackground(colorBtn);
        btnXuat.setBorderline(true);
        btnXuat.setBorderRadius(5);
        pnlTinhTong.add(btnXuat, "align right,w 200!, h 36!");
        
        setOpaque(false);
        setPreferredSize(new Dimension(getWidth(), 950));
    }

    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("QUẢN LÝ KHÁCH HÀNG");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return  pnlTitle;
    }
    
    public void init(){
        df = new DecimalFormat("#,##0.00");
        gio = new SimpleDateFormat("yyyy-MM-dd");
        hoaDon_Dao = new HoaDon_DAO();
        loaiPhong_DAO = new LoaiPhong_DAO();
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
        
        loaiPhong_DAO.getDsLoaiPhong().forEach(doc -> {
            cmbKhac.addItem(new ObjectComboBox(doc.getTenLoaiPhong(),doc.getMaLoaiPhong()));
        });
    }
    
    public void addAction(){
        dscKetThuc.addPropertyChangeListener(new createPropertyChangeListener());
        dscBatDau.addPropertyChangeListener(new createPropertyChangeListener());
        cmbTKTheo.addActionListener(new createActionListener());
        cmbTKChiTiet.addActionListener(new createActionListener());
        btnXuat.addActionListener(new createActionListener());
        cmbKhac.addActionListener(new createActionListener());
    }
    
    public void removeData(){
        DefaultTableModel df = (DefaultTableModel)tblThongKe.getModel();
        df.setRowCount(0);
    }
    
    public void resetCalendar(){
        dscBatDau.setCalendar(null);
        dscKetThuc.setCalendar(null);
    }
    
    public void resetCombobox(){
        cmbTKTheo.setSelectedIndex(0);
        cmbTKChiTiet.removeAllItems();
        cmbTKChiTiet.addItem("--Chọn--");
    }
    
    public void loadData(List<HoaDon> dsHoaDon){
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
    
    public double tinhTong(List<HoaDon> dsHoaDon){
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
                    List<HoaDon> dsHoaDon = hoaDon_Dao.findHoaDonByThangNam(thangOrNam,ma,theothang,year);
                    List<HoaDon> dsHoaDonCu = hoaDon_Dao.findHoaDonByThangNam(thangOrNam - 1,ma,theothang,year);
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
                       ObjectComboBox cb = (ObjectComboBox)cmbKhac.getSelectedItem();
                       ma = cb.getMa();
                    }
        
        String batDau = gio.format(dscBatDau.getDate());
        String ketThuc = gio.format(dscKetThuc.getDate());
        List<HoaDon> dsHoaDon = hoaDon_Dao.findHoaDon(batDau, ketThuc,ma);
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
                    
                    hoaDon_Dao.getDSNamTheoNgayLap().forEach(doc -> cmbTKChiTiet.addItem(doc));
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
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlThongKeTheo = new gui.swing.panel.PanelShadow();
        pnlBangThongKe = new gui.swing.panel.PanelShadow();
        scrThongKe = new javax.swing.JScrollPane();
        tblThongKe = new gui.swing.table2.MyTable();
        pnlTinhTong = new gui.swing.panel.PanelShadow();

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);

        pnlThongKeTheo.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongKeTheo.setShadowColor(new java.awt.Color(255, 255, 255));
        pnlThongKeTheo.setShadowOpacity(0.3F);
        pnlThongKeTheo.setShadowSize(3);

        javax.swing.GroupLayout pnlThongKeTheoLayout = new javax.swing.GroupLayout(pnlThongKeTheo);
        pnlThongKeTheo.setLayout(pnlThongKeTheoLayout);
        pnlThongKeTheoLayout.setHorizontalGroup(
            pnlThongKeTheoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlThongKeTheoLayout.setVerticalGroup(
            pnlThongKeTheoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 94, Short.MAX_VALUE)
        );

        pnlBangThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pnlBangThongKe.setShadowColor(new java.awt.Color(255, 255, 255));
        pnlBangThongKe.setShadowOpacity(0.3F);
        pnlBangThongKe.setShadowSize(3);

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hóa đơn", "Ngày lập hóa đơn", "Tên khách hàng", "Nhân viên", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrThongKe.setViewportView(tblThongKe);
        if (tblThongKe.getColumnModel().getColumnCount() > 0) {
            tblThongKe.getColumnModel().getColumn(0).setResizable(false);
            tblThongKe.getColumnModel().getColumn(1).setResizable(false);
            tblThongKe.getColumnModel().getColumn(2).setResizable(false);
            tblThongKe.getColumnModel().getColumn(3).setResizable(false);
            tblThongKe.getColumnModel().getColumn(4).setResizable(false);
        }

        javax.swing.GroupLayout pnlBangThongKeLayout = new javax.swing.GroupLayout(pnlBangThongKe);
        pnlBangThongKe.setLayout(pnlBangThongKeLayout);
        pnlBangThongKeLayout.setHorizontalGroup(
            pnlBangThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
        );
        pnlBangThongKeLayout.setVerticalGroup(
            pnlBangThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
        );

        pnlTinhTong.setBackground(new java.awt.Color(255, 255, 255));
        pnlTinhTong.setShadowColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTinhTongLayout = new javax.swing.GroupLayout(pnlTinhTong);
        pnlTinhTong.setLayout(pnlTinhTongLayout);
        pnlTinhTongLayout.setHorizontalGroup(
            pnlTinhTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTinhTongLayout.setVerticalGroup(
            pnlTinhTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongKeTheo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBangThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTinhTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(pnlThongKeTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBangThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTinhTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow pnlBangThongKe;
    private gui.swing.panel.PanelShadow pnlThongKeTheo;
    private gui.swing.panel.PanelShadow pnlTinhTong;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane scrThongKe;
    private gui.swing.table2.MyTable tblThongKe;
    // End of variables declaration//GEN-END:variables
}
