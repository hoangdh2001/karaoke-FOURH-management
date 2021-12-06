package gui;

import dao.HoaDon_DAO;
import dao.NhaCungCapVaNhapHang_DAO;
import entity.HoaDon;
import entity.NhanVien;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.swing.panel.PanelShadow;
import gui.swing.button.Button;
import gui.swing.table2.MyTable;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import net.miginfocom.swing.MigLayout;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objectcombobox.ObjectComboBox;

public class GD_DoiPhong extends javax.swing.JDialog {
    private JPanel pnlLoc;
    private JPanel pnlDanhSachPhong;
    private JPanel pnlInfo;
    private JPanel pnlHieuChinh;
    private String fontName = "sansserif";
    
    private MyTextField txtTenPhong;
    private MyTextField txtLoaiPhong;
    private MyTextField txtGiaPhong;
    private MyTextField txtGioDaHat;
    private MyTextField txtTongTienCu;
    
    private MyTextField txtKhachHang;
    private MyTextField txtLoaiPhongMoi;
    private MyTextField txtGioBatDau;
    
    private MyTable table;
    
    private Phong phong;
    private HoaDon hoaDon;
    private NhanVien nhanVien;
    
    private Button btnHuy;
    private Button btnDoiPhong;
    
    private DecimalFormat df;
    
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhapHang_DAO;
    
    private HoaDon_DAO hoaDonDao;
    
    private String gioHat;
    
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    private int font12 = 12;
    private Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);

    /**
     * Creates new form GD_CuaSoDatPhong2
     */
    public GD_DoiPhong(Phong phong, HoaDon hoaDon) {
        setModal(true);
        initComponents();
        this.hoaDon = hoaDon;
        this.phong = phong;
        this.nhanVien = GD_Chinh.NHAN_VIEN;
        setTitle("Đổi phòng");
        initForm();
        initData();
        addAction();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        MainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        MainPanel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout MainPanelLayout = new javax.swing.GroupLayout(MainPanel);
        MainPanel.setLayout(MainPanelLayout);
        MainPanelLayout.setHorizontalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        MainPanelLayout.setVerticalGroup(
            MainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(MainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void initOldRoomInfo(){
        pnlLoc = new JPanel();
        pnlLoc.setLayout(new MigLayout("","33[][]","10[]10"));
        
        JLabel lblPhongCu = new JLabel("Phòng cũ");
        lblPhongCu.setFont(new Font(fontName, fontPlain, font16));
        lblPhongCu.setForeground(colorLabel);
        pnlLoc.add(lblPhongCu, "span, w 100%, h 30!, wrap");
        
        JLabel lblTenPhong = new JLabel("Tên phòng :");
        lblTenPhong.setFont(new Font(fontName, fontPlain, font14));
        pnlLoc.add(lblTenPhong, "align right");

        txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontPlain, font14));
        txtTenPhong.setEnabled(false);
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setBorderRadius(5);
        
        pnlLoc.add(txtTenPhong, "w 100:260:350, h 36! , wrap");
        
        JLabel lblLoaiPhong = new JLabel("Loại phòng :");
        lblLoaiPhong.setFont(new Font(fontName, fontPlain, font14));
        
        pnlLoc.add(lblLoaiPhong, "align right");

        txtLoaiPhong = new MyTextField();
        txtLoaiPhong.setFont(new Font(fontName, fontPlain, font14));
        txtLoaiPhong.setEnabled(false);
        txtLoaiPhong.setBorderLine(true);
        txtLoaiPhong.setBorderRadius(5);
        
        pnlLoc.add(txtLoaiPhong, "w 100:260:350, h 36! , wrap");
        
        JLabel lblGiaPhong = new JLabel("Giá phòng :");
        lblGiaPhong.setFont(new Font(fontName, fontPlain, font14));
        
        pnlLoc.add(lblGiaPhong, "align right");

        txtGiaPhong = new MyTextField();
        txtGiaPhong.setFont(new Font(fontName, fontPlain, font14));
        txtGiaPhong.setEnabled(false);
        txtGiaPhong.setBorderLine(true);
        txtGiaPhong.setBorderRadius(5);
        
        pnlLoc.add(txtGiaPhong, "w 100:260:350, h 36! , wrap");
        
        JLabel lblGioDaHat = new JLabel("Giờ đã hát :");
        lblGioDaHat.setFont(new Font(fontName, fontPlain, font14));
        pnlLoc.add(lblGioDaHat, "align right");

        txtGioDaHat = new MyTextField();
        txtGioDaHat.setFont(new Font(fontName, fontPlain, font14));
        txtGioDaHat.setEnabled(false);
        txtGioDaHat.setBorderLine(true);
        txtGioDaHat.setBorderRadius(5);
        
        pnlLoc.add(txtGioDaHat, "w 100:260:350, h 36! , wrap");
        
        JLabel lblTongTienCu = new JLabel("Tổng tiền phòng :");
        lblTongTienCu.setFont(new Font(fontName, fontPlain, font14));
        pnlLoc.add(lblTongTienCu, "align right");

        txtTongTienCu = new MyTextField();
        txtTongTienCu.setFont(new Font(fontName, fontPlain, font14));
        txtTongTienCu.setEnabled(false);
        txtTongTienCu.setBorderLine(true);
        txtTongTienCu.setBorderRadius(5);
        
        pnlLoc.add(txtTongTienCu, "w 100:260:300, h 36! , wrap");
        pnlInfo.add(pnlLoc,"w 35%,h 100%");
        pnlLoc.setBackground(Color.WHITE);  
        
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(20, 250));
        pnlInfo.add(spr);

    }
    
    public void initNewRoom(){
        pnlDanhSachPhong = new JPanel();
        pnlDanhSachPhong.setOpaque(false);
        
        pnlDanhSachPhong.setLayout(new MigLayout("","[]","40[][]16"));
            pnlDanhSachPhong.setBackground(Color.WHITE);    
            
        JLabel lblPhongMoi = new JLabel("Thông tin các phòng có thể đổi");
        lblPhongMoi.setFont(new Font(fontName, fontPlain, font16));
        lblPhongMoi.setForeground(colorLabel);
        pnlDanhSachPhong.add(lblPhongMoi, "span, w 100%, h 30!, wrap");
 
        DefaultTableModel model = new DefaultTableModel(
            new String [] {
            "Tên phòng","Loại phòng","Tầng","Giá phòng/Giờ"
            },0
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false,false
            }; 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        table = new MyTable(){
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
                                case 3:
					return String.class;
				default:
					return Boolean.class;
				}
			}
        };
    
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(1).setPreferredWidth(100);
        table.getColumnModel().getColumn(0).setPreferredWidth(100);
        
        
        JScrollPane sp = new JScrollPane(table);

        table.fixTable(sp);
        pnlDanhSachPhong.add(sp,"w 100%,h 100%");
        pnlInfo.add(pnlDanhSachPhong,"w 65%,h 100%");
    }
    
    public void initNewRoomInfo(){

        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setLayout(new MigLayout("","40[][]","20[]10[]10[]"));
        
        JLabel lblKhachHang = new JLabel("Khách Hàng :");
        lblKhachHang.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblKhachHang, "align right");

        txtKhachHang = new MyTextField();
        txtKhachHang.setFont(new Font(fontName, fontPlain, font14));
        txtKhachHang.setEnabled(false);
        txtKhachHang.setBorderLine(true);
        txtKhachHang.setBorderRadius(5);
        
        pnlThongTin.add(txtKhachHang, "w 100:260:350, h 36! , wrap");
        
        JLabel lblLoaiPhong = new JLabel("Loại phòng mới :");
        lblLoaiPhong.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblLoaiPhong, "align right");

        txtLoaiPhongMoi = new MyTextField();
        txtLoaiPhongMoi.setFont(new Font(fontName, fontPlain, font14));
        txtLoaiPhongMoi.setEnabled(false);
        txtLoaiPhongMoi.setBorderLine(true);
        txtLoaiPhongMoi.setBorderRadius(5);
        
        pnlThongTin.add(txtLoaiPhongMoi, "w 100:260:350, h 36! , wrap");
        
        JLabel lblGioDatDau = new JLabel("Giờ bắt đầu :");
        lblGioDatDau.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblGioDatDau, "align right");

        txtGioBatDau = new MyTextField();
        txtGioBatDau.setFont(new Font(fontName, fontPlain, font14));
        txtGioBatDau.setBorderLine(true);
        txtGioBatDau.setBorderRadius(5);
        txtGioBatDau.setEnabled(false);
        
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        String strDate = formatter.format(date);
        strDate = formatter.format(date);
        
        txtGioBatDau.setText(strDate);
        
        pnlThongTin.add(txtGioBatDau, "w 100:260:350, h 36! , wrap");
        
        pnlThongTin.setBackground(Color.WHITE);
           
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new MigLayout("","push[]10[]20","push[]10"));
        pnlButton.setBackground(Color.WHITE);
        
        btnHuy = new Button("Hủy đổi phòng");
        btnHuy.setFont(new Font(fontName, fontPlain, font14));
        btnHuy.setBackground(colorBtn);
        btnHuy.setBorderRadius(5);
        
        btnDoiPhong = new Button("đổi phòng");
        btnDoiPhong.setFont(new Font(fontName, fontPlain, font14));
        btnDoiPhong.setBackground(colorBtn);
        btnDoiPhong.setBorderRadius(5);
        
        pnlButton.add(btnHuy,"h 36!");
        pnlButton.add(btnDoiPhong,"h 36!");
        
        pnlHieuChinh.add(pnlThongTin,"w 40%");
        pnlHieuChinh.add(pnlButton,"w 60%,h 80%");
    }
    
    public void initForm(){
        
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
        df = new DecimalFormat("#,##0.00");
        
        setSize(new Dimension(1350,560));
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
        setResizable(false);
        MainPanel.setLayout(new MigLayout("","20[center]10"));
        
        pnlInfo = new PanelShadow();
        pnlInfo.setLayout(new MigLayout("", "20[center][center]20", "20[]10"));
        pnlInfo.setBackground(Color.WHITE);
        
        pnlHieuChinh = new PanelShadow();
        pnlHieuChinh.setLayout(new MigLayout("", "20[]20[]","10[]"));
        pnlHieuChinh.setBackground(Color.WHITE);
        
        
        initOldRoomInfo();
        initNewRoom();
        initNewRoomInfo();
        
    
        MainPanel.add(pnlInfo,"w 100%,h 30%,wrap");
        MainPanel.add(pnlHieuChinh,"w 100%,h 240");
    }
    
    public void initData(){
        txtTenPhong.setText(phong.getTenPhong());
        txtLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        txtGiaPhong.setText(df.format(phong.getLoaiPhong().getGiaPhong()));
        try {
            TongTienPhongCu();
        } catch (ParseException ex) {
            Logger.getLogger(GD_DoiPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtGioDaHat.setText(gioHat);
        txtKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        
        List<Phong> dsPhong  = nhaCungCapVaNhapHang_DAO.getDSPhongByTrangThai(TrangThaiPhong.TRONG);
        dsPhong.forEach(phong -> table.addRow(phong.convertToRowTableInGDoiPhong()));
    }
    
    public double TongTienPhongCu() throws ParseException{
        SimpleDateFormat formatterGio = new SimpleDateFormat("dd-MM-yyyy HH:mm");
        Date date = new Date(System.currentTimeMillis());
        
        String txtgioBatDau = formatterGio.format(hoaDon.getThoiGianBatDau());
        String txtgioKetThuc = formatterGio.format(date);
        
        SimpleDateFormat gio = new SimpleDateFormat("dd-MM-yyyy HH:mm");

        Date dateGioBatDau = (Date) gio.parse(txtgioBatDau);
        Date dateGioKetThuc = (Date) gio.parse(txtgioKetThuc);
        
        long ngayDaSuDung = ChronoUnit.DAYS.between(dateGioBatDau.toInstant(), dateGioKetThuc.toInstant()) - 1;
        
        long millisBatDau = dateGioBatDau.getTime();
        long millisKetThuc = dateGioKetThuc.getTime();
        long tongThoiGian = 0;
        int hours = 0;
        int minutes = 0;
        double tienPhong = 0;
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
            tienPhong =(ngayDaSuDung-1)*24*giaPhong + hours*giaPhong + (Double.parseDouble(String.valueOf(minutes))/60)*giaPhong;
            gioHat = String.valueOf((ngayDaSuDung - 1)*24+hours) +":"+String.valueOf(minutes);
        }else{
            tongThoiGian = millisKetThuc - millisBatDau;
            minutes = (int) ((tongThoiGian / (1000*60)) % 60);
            hours   = (int) ((tongThoiGian / (1000*60*60)) % 24);
            tienPhong = hours*giaPhong + (Double.parseDouble(String.valueOf(minutes))/60)*giaPhong;
            System.out.println(tongThoiGian);
            gioHat = String.valueOf(hours) +":"+String.valueOf(minutes);
        }
        System.out.println(gioHat);
        txtTongTienCu.setText(df.format(tienPhong));
        return tienPhong;
    }
    
    public void addAction(){
        table.addMouseListener(new actionMouseListener());
        btnHuy.addMouseListener(new actionMouseListener());
        btnDoiPhong.addMouseListener(new actionMouseListener());
    }
    
    private void showMsg(String msg) {
	JOptionPane.showMessageDialog(null, msg);
    }
    
    private class actionMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Object obj = e.getSource();
            if(obj.equals(table)){
                ObjectComboBox cb = (ObjectComboBox)table.getValueAt(table.getSelectedRow(), 0);
                txtLoaiPhongMoi.setText(cb.toString() +" - "+ table.getValueAt(table.getSelectedRow(), 1));
            }else if (obj.equals(btnHuy)){
                dispose();
            }else if (obj.equals(btnDoiPhong)){
                if(table.getSelectedRow() != -1){
                    double tienPhongCu = 0;
                    try {
                        tienPhongCu = TongTienPhongCu();
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                    ObjectComboBox cb = (ObjectComboBox)table.getValueAt(table.getSelectedRow(), 0);
                    hoaDonDao.updateHoaDonDoiPhong(hoaDon,tienPhongCu, cb.getMa());
                    nhaCungCapVaNhapHang_DAO.updatePhong(cb.getMa(),TrangThaiPhong.DANG_HAT);
                    nhaCungCapVaNhapHang_DAO.updatePhong(phong.getMaPhong(), TrangThaiPhong.BAN);
                    
                    showMsg("Đổi phòng thành công");
                }
            }
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel MainPanel;
    // End of variables declaration//GEN-END:variables
}
