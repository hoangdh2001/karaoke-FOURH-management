/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import dao.HoaDon_DAO;
import dao.NhaCungCapVaNhapHang_DAO;
import dao.Phong_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.swing.panel.PanelShadow;
import gui.swing.button.Button;
import gui.swing.table.SpinnerEditor;
import gui.swing.table2.MyTable;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.time.temporal.ChronoUnit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 84975
 */
public class GD_LapHoaDon extends javax.swing.JDialog {
    private JPanel pnlInfoTop;
    private JPanel pnlInfoBottom;
    private JPanel pnlThanhToan;
    private JPanel pnlInfoRoom;
    private JPanel pnlDanhSachDichVu;
    private String fontName = "sansserif";
    
    private DecimalFormat df;
    
    private MyTextField txtTongDV;
    
    private MyTextField txtKhachHang;
    private MyTextField txtTenPhong;
    private MyTextField txtLoai;
    private MyTextField txtGia;
    private MyTextField txtNhanVien;
    private MyTextField txtStart;
    private MyTextField txtEnd;
    private MyTextField txtTongTienPhongCu;
    private MyTextField txtTongTienPhong;
    
    private MyTextField txtTongTien;
    
    private MyTextField txtTienDua;
    private MyTextField txtTraLai;
    
    private MyTable tableSelected;
    
    private Button btnHuy;
    private Button btnThanhToan;
    
    private Phong phong;
    private NhanVien nhanVien;
    private HoaDon hoaDon;
    
    private String GioHat;
    
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhapHang_DAO;
    private HoaDon_DAO hoaDonDao;
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    private int font12 = 12;
    private Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);
    
    public GD_LapHoaDon(Phong phong,NhanVien nhanVien) {
        super();
        this.phong = phong;
        this.nhanVien= nhanVien;
        setModal(true);
        initComponents();
        
        initForm();
        addAction();
        initData(phong,nhanVien);
        TongTien();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        mainPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GD_LapHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GD_LapHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GD_LapHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GD_LapHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Phong phong = new Phong_DAO().getPhong("PH0001");
                System.out.println(phong);
                NhanVien nhanVien = new NhaCungCapVaNhapHang_DAO().getNhanVienByID("NV0001");
                GD_LapHoaDon dialog = new GD_LapHoaDon(phong,nhanVien);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    
    public void initService(){
        pnlDanhSachDichVu = new JPanel();
        pnlDanhSachDichVu.setOpaque(false);
        
        pnlDanhSachDichVu.setLayout(new MigLayout("","[]","10[]10"));
        pnlDanhSachDichVu.setBackground(Color.WHITE);
        
        JLabel lblDanhSachPhieu = new JLabel("Dịch vụ");
        lblDanhSachPhieu.setFont(new Font(fontName, fontPlain, font16));
        lblDanhSachPhieu.setForeground(colorLabel);
        pnlDanhSachDichVu.add(lblDanhSachPhieu, "span, w 100%, h 30!, wrap");

        String colSelected[] = {"Tên","Số lượng","Giá","Tổng"};
        
        DefaultTableModel model = new DefaultTableModel(
            colSelected,
            0
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false,false
            }; 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        tableSelected = new MyTable(){
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return String.class;
				
				}
			}
        };
    
        tableSelected.setModel(model);
        
        TableColumnModel tcm = tableSelected.getColumnModel();
        TableColumn tcSpinner = tcm.getColumn(1);
        SpinnerEditor sp = new SpinnerEditor(100);
        tcSpinner.setCellEditor(sp);
        
        JScrollPane spSelected = new JScrollPane(tableSelected);
        tableSelected.fixTable(spSelected);
        
        JPanel tong =  new JPanel();
        tong.setLayout(new MigLayout("","13[]5[]push","push[]"));
        tong.setBackground(Color.WHITE);
        
        JLabel lblTong = new JLabel("Tổng tiền dịch vụ :");
        lblTong.setFont(new Font(fontName, fontPlain, font14));
        tong.add(lblTong, "align right");

        txtTongDV = new MyTextField();
        txtTongDV.setFont(new Font(fontName, fontPlain, font14));
        txtTongDV.setEnabled(false);
        txtTongDV.setBorderLine(true);
        txtTongDV.setBorderRadius(5);
        tong.add(txtTongDV, "w 100:200:260, h 36! , wrap");

        pnlDanhSachDichVu.add(spSelected,"w 100%,h 90%,wrap");
        pnlDanhSachDichVu.add(tong,"w 100%,h 10%");
        pnlInfoTop.add(pnlDanhSachDichVu,"w 50%,h 100%");
        
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(20, 440));
        pnlInfoTop.add(spr);
    }
    
    public void initInfoRoom(){
        JPanel pnlInfoRightWrap = new JPanel();
        pnlInfoRightWrap.setLayout(new MigLayout("","[]","10[center]10[center]18[center]18"));
        
        JLabel lblTTPhong = new JLabel("Thông tin sử dụng");
        lblTTPhong.setFont(new Font(fontName, fontPlain, font16));
        lblTTPhong.setForeground(colorLabel);
        pnlInfoRightWrap.add(lblTTPhong, "span, w 100%, h 30!, wrap");
        
        pnlInfoRightWrap.setBackground(Color.WHITE);
        
        
        pnlInfoRoom = new JPanel();
        pnlInfoRoom.setLayout(new MigLayout("","28[][]10","10[]5"));

        JLabel lblKhachHang= new JLabel("Khách hàng: ");
        lblKhachHang.setFont(new Font(fontName, fontPlain, font14));
        
        pnlInfoRoom.add(lblKhachHang, "align right");

        txtKhachHang = new MyTextField();
        txtKhachHang.setFont(new Font(fontName, fontPlain, font14));
        txtKhachHang.setEnabled(false);
        txtKhachHang.setBorderLine(true);
        txtKhachHang.setBorderRadius(5);
        
        pnlInfoRoom.add(txtKhachHang, "w 100%, h 36! , wrap");
        
        JLabel lblTenPhong= new JLabel("Tên phòng :");
        lblTenPhong.setFont(new Font(fontName, fontPlain, font14));
        
        pnlInfoRoom.add(lblTenPhong, "align right");

        txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontPlain, font14));
        txtTenPhong.setEnabled(false);
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setBorderRadius(5);
        
        pnlInfoRoom.add(txtTenPhong, "w 100%, h 36! , wrap");
        
        JLabel lblLoai= new JLabel("Loại phòng :");
        lblLoai.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblLoai, "align right");

        txtLoai = new MyTextField();
        txtLoai.setFont(new Font(fontName, fontPlain, font14));
        txtLoai.setEnabled(false);
        txtLoai.setBorderLine(true);
        txtLoai.setBorderRadius(5);
        
        pnlInfoRoom.add(txtLoai, "w 100%, h 36! , wrap");
        
        JLabel lblGia= new JLabel("Giá phòng/giờ :");
        lblGia.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblGia, "align right");

        txtGia = new MyTextField();
        txtGia.setFont(new Font(fontName, fontPlain, font14));
        txtGia.setEnabled(false);
        txtGia.setBorderLine(true);
        txtGia.setBorderRadius(5);
        
        pnlInfoRoom.add(txtGia, "w 100%, h 36! , wrap");
        
        JLabel lblNhanVien= new JLabel("Nhân viên :");
        lblNhanVien.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblNhanVien, "align right");

        txtNhanVien = new MyTextField();
        txtNhanVien.setFont(new Font(fontName, fontPlain, font14));
        txtNhanVien.setEnabled(false);
        txtNhanVien.setBorderLine(true);
        txtNhanVien.setBorderRadius(5);
        
        pnlInfoRoom.add(txtNhanVien, "w 100%, h 36! , wrap");
        
        JLabel lblStart = new JLabel("Giờ Bắt đầu :");
        lblStart.setFont(new Font(fontName, fontPlain, font14));
        
        pnlInfoRoom.add(lblStart, "align right");

        txtStart = new MyTextField();
        txtStart.setFont(new Font(fontName, fontPlain, font14));
        txtStart.setEnabled(false);
        txtStart.setBorderLine(true);
        txtStart.setBorderRadius(5);
        
        pnlInfoRoom.add(txtStart, "w 100%, h 36! , wrap");
        
        JLabel lblEnd = new JLabel("Giờ kết thúc :");
        lblEnd.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblEnd, "align right");

        txtEnd = new MyTextField();
        txtEnd.setFont(new Font(fontName, fontPlain, font14));
        txtEnd.setEnabled(false);
        txtEnd.setBorderLine(true);
        txtEnd.setBorderRadius(5);
        
        pnlInfoRoom.add(txtEnd, "w 100%, h 36! , wrap");
        
        JLabel lblTienCu = new JLabel("Tiền phòng cũ :");
        lblTienCu.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblTienCu, "align right");

        txtTongTienPhongCu = new MyTextField();
        txtTongTienPhongCu.setFont(new Font(fontName, fontPlain, font14));
        txtTongTienPhongCu.setEnabled(false);
        txtTongTienPhongCu.setBorderLine(true);
        txtTongTienPhongCu.setBorderRadius(5);
        
        pnlInfoRoom.add(txtTongTienPhongCu, "w 100%, h 36! , wrap");
        
        pnlInfoRoom.setBackground(Color.WHITE);
        
        JPanel tongTienPhong =  new JPanel();
        tongTienPhong.setLayout(new MigLayout("fill,insets 0","13[]5[]push"));
        tongTienPhong.setBackground(Color.WHITE);
        
        JLabel lblTongTien = new JLabel("Tổng tiền phòng :");
        lblTongTien.setFont(new Font(fontName, fontPlain, font14));
        
        tongTienPhong.add(lblTongTien, "align left");

        txtTongTienPhong = new MyTextField();
        txtTongTienPhong.setFont(new Font(fontName, fontPlain, font14));
        txtTongTienPhong.setEnabled(false);
        txtTongTienPhong.setBorderLine(true);
        txtTongTienPhong.setBorderRadius(5);
        
        tongTienPhong.add(txtTongTienPhong, "w 100:200:270, h 36!");
        
        pnlInfoRightWrap.add(pnlInfoRoom,"w 100%,h 90%,wrap");
        pnlInfoRightWrap.add(tongTienPhong,"w 100%,h 10%");
        
        pnlInfoTop.add(pnlInfoRightWrap,"w 50%,h 100%");
        
    }
    
    public void initActionPanel(){
        pnlThanhToan = new JPanel();
        pnlThanhToan.setBackground(Color.WHITE);
        pnlThanhToan.setLayout(new MigLayout("","28[][]","10[]5"));
        
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setLayout(new MigLayout("","30[][]20","0[]10"));
        
        JLabel lblTongTien = new JLabel("Tổng tiền :");
        lblTongTien.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblTongTien, "align right");

        txtTongTien = new MyTextField();
        txtTongTien.setFont(new Font(fontName, fontPlain, font14));
        txtTongTien.setEnabled(false);
        txtTongTien.setBorderLine(true);
        txtTongTien.setBorderRadius(5);
        
        pnlThongTin.add(txtTongTien, "w 100:200:270, h 36! , wrap");
        
        JLabel lblTienDua = new JLabel("Tiền khách đưa :");
        lblTienDua.setFont(new Font(fontName, fontPlain, font14));
        
        pnlThongTin.add(lblTienDua, "align right");

        txtTienDua = new MyTextField();
        txtTienDua.setFont(new Font(fontName, fontPlain, font14));
        txtTienDua.setBorderLine(true);
        txtTienDua.setBorderRadius(5);
        
        pnlThongTin.add(txtTienDua, "w 100:200:270, h 36! , wrap");
        
        JLabel lblTraLai = new JLabel("Tiền trả lại :");
        lblTraLai.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblTraLai, "align right");

        txtTraLai= new MyTextField();
        txtTraLai.setFont(new Font(fontName, fontPlain, font14));
        txtTraLai.setEnabled(false);
        txtTraLai.setBorderLine(true);
        txtTraLai.setBorderRadius(5);
        
        pnlThongTin.add(txtTraLai, "w 100:200:270, h 36! , wrap");
        
        pnlThongTin.setBackground(Color.WHITE);
           
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new MigLayout("","push[]10[]20","push[]20"));
        pnlButton.setBackground(Color.WHITE);
        
        btnHuy = new Button("Hủy xem");
        btnHuy.setFont(new Font(fontName, fontPlain, font14));
        btnHuy.setBackground(colorBtn);
        btnHuy.setBorderRadius(5);
        
        btnThanhToan = new Button("Thanh toán");
        btnThanhToan.setFont(new Font(fontName, fontPlain, font14));
        btnThanhToan.setBackground(colorBtn);
        btnThanhToan.setBorderRadius(5);
        
        pnlButton.add(btnHuy,"h 36!");
        pnlButton.add(btnThanhToan,"h 36!");
        
        pnlInfoBottom.add(pnlThongTin,"w 50%,h 100%");
        pnlInfoBottom.add(pnlButton,"w 50%,h 100%");
        
    }

     public void initForm(){
        setSize(new Dimension(1200,780));
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
        nhaCungCapVaNhapHang_DAO= new NhaCungCapVaNhapHang_DAO();
        df = new DecimalFormat("#,##0.00");
        mainPanel.setLayout(new MigLayout("","20[center]20"));
//        MainPanel.setBackground(Color.WHITE);
        
        pnlInfoTop = new PanelShadow();
        pnlInfoTop.setLayout(new MigLayout("", "20[center] 20 [center]20", "20[]20"));
        pnlInfoBottom = new PanelShadow();
        pnlInfoBottom.setLayout(new MigLayout("", "20[center][center]20", "20[]"));
        
        initService();
        initActionPanel();
        initInfoRoom();
        
        pnlInfoTop.setBackground(Color.WHITE);
        pnlInfoBottom.setBackground(Color.WHITE);
        
        mainPanel.add(pnlInfoTop,"w 100%,h 70%,wrap");
        mainPanel.add(pnlInfoBottom,"w 100%,h 30%");
    }
     
     public void initData(Phong phong,NhanVien nv){
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
        hoaDonDao = new HoaDon_DAO();
        hoaDon = hoaDonDao.getHoaDon(phong);
        
        txtTongTienPhongCu.setText(String.valueOf(hoaDon.getDonGiaPhongCu()));
        List<ChiTietHoaDon> dsCTHoaDon = hoaDon.getDsChiTietHoaDon();
        dsCTHoaDon.forEach(ctHoaDon -> {
            tableSelected.addRow(ctHoaDon.convertToRowTableInGDLapHoaDon());
        });
        
        
        SimpleDateFormat formatterNgay = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatterGio = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        
        Date date = new Date(System.currentTimeMillis());
        String strNgay = formatterNgay.format(date);
        
        String strBatDau = formatterGio.format(hoaDon.getThoiGianBatDau());
        String strGio = formatterGio.format(date);
        
        txtKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        txtTenPhong.setText(phong.getTenPhong());
        txtLoai.setText(phong.getLoaiPhong().getTenLoaiPhong());
        txtGia.setText(df.format(phong.getLoaiPhong().getGiaPhong()));
        txtNhanVien.setText(nhanVien.getTenNhanVien());
        txtStart.setText(strBatDau);
        txtEnd.setText(strGio);
        
     }
     
    public void addAction(){
//        tableSelected.addActionListener(new actionListener());
        tableSelected.addKeyListener(new actionKeyListenner());
        txtTienDua.addKeyListener(new actionKeyListenner());
        btnHuy.addMouseListener(new actionMouselistenner());
        btnThanhToan.addActionListener(new actionListener());
    }
    
    public double TongTienDichVu(){
        double tong = 0;
	int soLuongMH = tableSelected.getRowCount();
        
        TableModel model = tableSelected.getModel();
        for (int i = 0;i < soLuongMH;i++ ) {
            int soluong = Integer.parseInt(model.getValueAt(i, 1).toString()); 
            double giathanh = hoaDon.getDsChiTietHoaDon().get(i).getMatHang().getDonGia();
            String tongTienSP = df.format(soluong * giathanh);
            tableSelected.getModel().setValueAt(tongTienSP,i,3);
            
            tong += soluong * giathanh;
	}
        txtTongDV.setText(df.format(tong));
        
        return tong;
    }
    
    public double TongTienPhong() throws ParseException{
        
        String txtgioBatDau = txtStart.getText().trim();
        String txtgioKetThuc = txtEnd.getText().trim();
        SimpleDateFormat gio = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Date dateGioBatDau = (Date) gio.parse(txtgioBatDau);
        Date dateGioKetThuc = (Date) gio.parse(txtgioKetThuc);
        
        long ngayDaSuDung = ChronoUnit.DAYS.between(dateGioBatDau.toInstant(), dateGioKetThuc.toInstant()) - 1;
        
        long millisBatDau = dateGioBatDau.getTime();
        long millisKetThuc = dateGioKetThuc.getTime();
        long tongThoiGian = 0;
        int hours = 0;
        int minutes = 0;
        
        double tienPhong = hoaDon.getDonGiaPhongCu();
        double giaPhong = phong.getLoaiPhong().getGiaPhong();
        if(ngayDaSuDung > 0){
            int hoursBatDau= 0;
            int minutesBatDau = 60 - (int) ((millisBatDau / (1000*60)) % 60);
            if((int) ((millisBatDau / (1000*60)) % 60) > 0){
                hoursBatDau = 24 - (int) ((millisBatDau / (1000*60*60)) % 24) - 1;
            }else{
                hoursBatDau = 24 - (int) ((millisBatDau / (1000*60*60)) % 24);
            }
            int minutesKetThuc = (int) ((millisKetThuc / (1000*60)) % 60);
            int hoursKetThuc = (int) ((millisKetThuc / (1000*60*60)) % 24);
            
            hours = hoursBatDau + hoursKetThuc;
            minutes = minutesBatDau + minutesKetThuc ;
            if(minutes >= 60){
                hours += 1;
                minutes -= 60;
            }
            tienPhong += (ngayDaSuDung-1)*24*giaPhong + hours*giaPhong + (Double.parseDouble(String.valueOf(minutes))/60)*giaPhong;
            GioHat = String.valueOf((ngayDaSuDung - 1)*24+hours) +":"+String.valueOf(minutes);
        }else{
            tongThoiGian = millisKetThuc - millisBatDau;
            minutes = (int) ((tongThoiGian / (1000*60)) % 60);
            hours   = (int) ((tongThoiGian / (1000*60*60)) % 24);
            tienPhong += hours*giaPhong + (Double.parseDouble(String.valueOf(minutes))/60)*giaPhong;
            System.out.println(tongThoiGian);
            GioHat = String.valueOf(hours) +":"+String.valueOf(minutes);
        }
        
        txtTongTienPhong.setText(df.format(tienPhong));
        return tienPhong;
    }
    
    public double TongTien(){
//        PhieuDatPhong pdp = nhaCungCapVaNhapHang_DAO.getPhieuCuaPhong(hoaDon.getKhachHang().getMaKhachHang());
        double tong =0;
        double tongTienDichVu = TongTienDichVu();
	double tongTienPhong = 0;
        try {
            tongTienPhong = TongTienPhong();
        } catch (ParseException ex) {
            Logger.getLogger(GD_LapHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
        tong = tongTienDichVu + tongTienPhong;
//        if(tong > pdp.getTienCoc()){
//            tong -= pdp.getTienCoc();
//        }
        
        txtTongTien.setText(df.format(tong));
	return tongTienDichVu + tongTienPhong;
    }
     
    public boolean tinhTienThoi() {
		if (!txtTongTien.getText().equals("")) {
			String tienKT = txtTienDua.getText().trim();
			double tienKhachTra = 0;
			double tongTien = 0;
			try {
				tienKhachTra = Double.parseDouble(tienKT);
				tongTien = TongTien();
			} catch (Exception e) {
                            e.printStackTrace();
				showMsg("Tiền khách trả không hợp lệ");
                                txtTienDua.requestFocus();
                                txtTienDua.selectAll();
				return false;
			}
			if (tienKT.matches("^\\d+$") && tienKhachTra >= tongTien) {
				txtTraLai.setText(df.format(tienKhachTra - tongTien));
			} else {
				showMsg("Số tiền trả Không đủ");
				txtTienDua.selectAll();
				txtTienDua.requestFocus();
				return false;
			}
		}
		return true;
    }
    
    public boolean validateData(){
        if (txtTienDua.getText().trim().equals("")) {
			showMsg("Nhập số tiền khách trả!");
			txtTienDua.selectAll();
			txtTienDua.requestFocus();
			return false;
		}
        return true;
    }
    
    private void showMsg(String msg) {
	JOptionPane.showMessageDialog(null, msg);
    }
    
    private double convertMoneyToDouble(String money){
        String[] text = money.trim().split("\\,")[0].split("\\.");
        String tienTra = "";
        for(int i=0;i< text.length;i++){
            tienTra+=text[i];
        }
        return Double.parseDouble(tienTra);
    }
    
    private class actionMouselistenner implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            Object obj = e.getSource();
            if(obj.equals(tableSelected)){
                System.out.println(tableSelected.getModel().getValueAt(0,1));
            }else if(obj.equals(btnHuy)){
                dispose();
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    
    private class actionKeyListenner implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Object obj = e.getSource();
            if(e.getKeyChar() == KeyEvent.VK_ENTER && obj.equals(txtTienDua)){
                if(tinhTienThoi()) {
                    txtTienDua.setText(df.format(Double.parseDouble(txtTienDua.getText().trim())));
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Object obj = e.getSource();
            if (e.getKeyChar() == KeyEvent.VK_ENTER && obj.equals(tableSelected)){
                TongTien();
                if(!txtTienDua.getText().trim().equals("")){
                    double tienTraLai = convertMoneyToDouble(txtTienDua.getText()) - TongTien();
                    txtTraLai.setText(df.format(tienTraLai));
                }
            }
        }
        
    }
    
    private class actionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if(obj.equals(btnThanhToan) && validateData()){
                try {
                    
                    hoaDonDao.updateHoaDon(hoaDon,GioHat,TongTienPhong(),TongTien(),TongTienDichVu());
                    
                    List<ChiTietHoaDon> dsCTHoaDon = hoaDon.getDsChiTietHoaDon();
                    for (int i = 0 ; i< tableSelected.getRowCount(); i++){
                        ChiTietHoaDon ctHoaDon = dsCTHoaDon.get(i);
                        int soluongCu = ctHoaDon.getSoLuong();
                        int soLuongMoi = Integer.parseInt(tableSelected.getValueAt(i,1).toString());
                        if(soLuongMoi > soluongCu){
                            nhaCungCapVaNhapHang_DAO.updateSLMatHang(ctHoaDon.getMatHang().getMaMatHang(), soLuongMoi - soluongCu,"decrease");
                        }else{
                            nhaCungCapVaNhapHang_DAO.updateSLMatHang(ctHoaDon.getMatHang().getMaMatHang(), soluongCu - soLuongMoi,"increase");
                        }
                        dsCTHoaDon.get(i).setSoLuong(soLuongMoi);
                        hoaDonDao.updateCTHoaDon(dsCTHoaDon.get(i));
                    }
                    nhaCungCapVaNhapHang_DAO.updatePhong(phong.getMaPhong(), TrangThaiPhong.BAN);
                    dispose();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
     
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
