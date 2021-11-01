/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import dao.NhaCungCapVaNhapHang_DAO;
import entity.CaLam;
import entity.KhachHang;
import entity.LoaiNhanVien;
import entity.LoaiPhong;
import entity.NhanVien;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.swing.panel.PanelShadow;
import gui.swing.button.Button;
import gui.swing.table.SpinnerEditor;
import gui.swing.table.TableCustom;
import gui.swing.table.TableCustomCheckBox;
import gui.swing.table.TableCustomRadio;
import gui.swing.table2.MyTable;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author 84975
 */
public class GD_TiepNhanDatPhong extends javax.swing.JDialog {

    private JPanel pnlDanhSachPhieu;
    private JPanel pnlDanhSachDichVu;
    private JPanel pnlInfoTop;
    private JPanel pnlInfoBottom;
    private JPanel pnlKhachHang;
    private JPanel pnlThongTinKhachHang;
    
    private MyTable tableDichVu;
    private MyTable tableDichVuDaChon;
    private MyTable tablePhieuDatPhong;
    
    private MyTextField txtTenPhong;
    private MyTextField txtLoaiPhong;
    private MyTextField txtGia;
    private MyTextField txtNhanVien;
    private MyTextField txtSdt;
    private MyTextField txtTenKhachHang;
    private MyTextField txtCCCD ;
    
    private Button btnHuy;
    private Button btnThoat;
    
    private DecimalFormat df;
    
    private String fontName = "sansserif";
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    private int font12 = 12;
    private Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);
    
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhaphang_DAO;
    
    private Phong phong;
    private NhanVien nhanVien;
    
    public GD_TiepNhanDatPhong(Phong phong,NhanVien nhanVien) {
        super();
        setTitle("Giao phòng");
        setModal(true);
        setResizable(false);
        initComponents();
        initForm();
        this.phong = new Phong("maphong", "phong 001", TrangThaiPhong.DANG_HAT, new LoaiPhong("loai phong", "ten loai phong", 5000.0), 1);  
        this.nhanVien = nhanVien;
        
        initData();
        addAction();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
            java.util.logging.Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                test phong
                Phong phong = new Phong("maphong", "phong 001", TrangThaiPhong.DANG_HAT, new LoaiPhong("loai phong", "ten loai phong", 5000.0), 1);
                LoaiNhanVien lnv = new LoaiNhanVien("1", "loai nhan vien");
                NhanVien nhanVien = new NhanVien("Nhan vieen 001", "name", lnv, new CaLam("1", "sdf", "fsdf"), "66556651", true, new Date( System.currentTimeMillis()),null, null, null, null);
                
                GD_TiepNhanDatPhong dialog = new GD_TiepNhanDatPhong(phong,nhanVien);
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
    
    public void initSetTheRoom(){
        pnlDanhSachPhieu = new JPanel();
        pnlDanhSachPhieu.setOpaque(false);
        pnlDanhSachPhieu.setLayout(new MigLayout("","[]","10[]10"));
        pnlDanhSachPhieu.setBackground(Color.WHITE); 
            
        JLabel lblDanhSachPhieu = new JLabel("Thông tin phiếu");
        lblDanhSachPhieu.setFont(new Font(fontName, fontPlain, font16));
        lblDanhSachPhieu.setForeground(colorLabel);
        pnlDanhSachPhieu.add(lblDanhSachPhieu, "span, w 100%, h 30!, wrap");
        
        Object dataPhieu[][] = { 
                { "101","Do Cao Hoc", "7:30am"}, 
                { "101","Do Cao Hoc", "11:30am"}, 
                { "101", "Tran Van Minh", "1:30pm"}, 
                { "102", "Phan Van Tai", "5:30pm"}, 
                { "101","Do Cao Hoc","9:30pm"},              
        };
 
        String col[] = {"Mã phiếu","Khách hàng","Giờ"};
        DefaultTableModel model = new DefaultTableModel(
            dataPhieu,
            col
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false,true
            }; 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        tablePhieuDatPhong = new MyTable(){
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
    
        tablePhieuDatPhong.setModel(model);
        
        JScrollPane sp = new JScrollPane(tablePhieuDatPhong);

        tablePhieuDatPhong.fixTable(sp);
   
        pnlDanhSachPhieu.add(sp,"w 100%,h 100%");
        
        pnlInfoTop.add(pnlDanhSachPhieu,"w 35%,h 100%");
        
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(20, 230));
        pnlInfoTop.add(spr);
    }
    
    public void initService(){
        pnlDanhSachDichVu = new JPanel();
        pnlDanhSachDichVu.setOpaque(false);
        
        pnlDanhSachDichVu.setLayout(new MigLayout("","[]","10[]10"));
        pnlDanhSachDichVu.setBackground(Color.WHITE); 
        
        JLabel lblDanhSachDV = new JLabel("Dịch vụ");
        lblDanhSachDV.setFont(new Font(fontName, fontPlain, font16));
        lblDanhSachDV.setForeground(colorLabel);
        pnlDanhSachDichVu.add(lblDanhSachDV, "span, w 100%, h 30!, wrap");
//chua chon
        Object dataDicVu[][] = { 
                { "101", "Tran Van Minh", "6000",false}, 
                { "102", "Phan Van Tai", "8000",false}, 
                { "101","Do Cao Hoc", "7000",false},
                { "101", "Do Cao Hoc", "7000",false},
                { "101", "Do Cao Hoc", "7000",false},
                { "101","Do Cao Hoc", "7000",false},
                { "101","Do Cao Hoc", "7000",false},
                { "101","Do Cao Hoc", "7000",false},              
        };
 
        String col[] = {"Tên","Số lượng","Giá","Chọn"};
        DefaultTableModel model = new DefaultTableModel(
            dataDicVu,
            col
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false,true
            }; 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        tableDichVu = new MyTable(){
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return Boolean.class;
				}
				
			}
        };
        
        tableDichVu.setModel(model);
        tableDichVu.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableDichVu.getColumnModel().getColumn(3).setPreferredWidth(40);
        tableDichVu.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableDichVu.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableDichVu.getColumnModel().getColumn(0).setPreferredWidth(246);
        
        tableDichVu.getColumnModel().getColumn(3).setPreferredWidth(40);
        tableDichVu.getColumnModel().getColumn(2).setPreferredWidth(80);
        tableDichVu.getColumnModel().getColumn(1).setPreferredWidth(100);
        tableDichVu.getColumnModel().getColumn(0).setPreferredWidth(246);
        
        
        JScrollPane sp = new JScrollPane(tableDichVu);

        tableDichVu.fixTable(sp);
//Da chon sp
        Object dataSelected[][] = { 
                {  "Tran Van Minh",0}, 
                {  "Phan Van Tai",0}, 
                {  "Do Cao Hoc",0},  
                {  "Tran Van Minh",0}, 
                {  "Phan Van Tai",0}, 
                {  "Do Cao Hoc",0},
        };
        String colSelected[] = {"Tên","Số lượng"};
        
        DefaultTableModel modelSelected = new DefaultTableModel(
            dataSelected,
            colSelected
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false
            }; 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        tableDichVuDaChon = new MyTable(){
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				default:
					return String.class;
				}
			}
        };
    
        tableDichVuDaChon.setModel(modelSelected);
        tableDichVuDaChon.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tableDichVuDaChon.getColumnModel().getColumn(0).setPreferredWidth(165);
        tableDichVuDaChon.getColumnModel().getColumn(1).setPreferredWidth(70);
        
        TableColumnModel tcm = tableDichVuDaChon.getColumnModel();
        TableColumn tc = tcm.getColumn(1);
        tc.setCellEditor(new SpinnerEditor(5));
        
        JScrollPane spSelected = new JScrollPane(tableDichVuDaChon);
        tableDichVuDaChon.fixTable(spSelected);
        
        pnlDanhSachDichVu.add(spSelected,"w 35%,h 100%");
        pnlDanhSachDichVu.add(sp,"w 65%,h 100%");
        
        pnlInfoTop.add(pnlDanhSachDichVu,"w 65%,h 100%");
    }
    
    public void initRoom(){
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setLayout(new MigLayout("","10[][]20","20[]10[]10[]10[]10[]10"));

        JLabel lblTTPhong = new JLabel("Thông tin phòng");
        lblTTPhong.setFont(new Font(fontName, fontPlain, font16));
        lblTTPhong.setForeground(colorLabel);
        
        pnlThongTin.add(lblTTPhong, "span, w 100%, h 30!, wrap");        
        
        JLabel lblTenPhong = new JLabel("Tên phòng :");
        lblTenPhong.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblTenPhong, "align right");

        txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontPlain, font14));
        txtTenPhong.setEnabled(false);
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setBorderRadius(5);
        pnlThongTin.add(txtTenPhong, "w 100:260:350, h 36! , wrap");
        
        JLabel lblLoaiPhong = new JLabel("Loại Phòng :");
        lblLoaiPhong.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblLoaiPhong, "align right");

        txtLoaiPhong = new MyTextField();
        txtLoaiPhong.setFont(new Font(fontName, fontPlain, font14));
        txtLoaiPhong.setEnabled(false);
        txtLoaiPhong.setBorderLine(true);
        txtLoaiPhong.setBorderRadius(5);
        pnlThongTin.add(txtLoaiPhong, "w 100:260:350, h 36! , wrap");
        
        JLabel lblGia = new JLabel("Giá phòng/giờ :");
        lblGia.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblGia, "align right");

        txtGia = new MyTextField();
        txtGia.setFont(new Font(fontName, fontPlain, font14));
        txtGia.setEnabled(false);
        txtGia.setBorderRadius(5);
        txtGia.setBorderLine(true);
        
        pnlThongTin.add(txtGia, "w 100:260:350, h 36! , wrap");
        
        JLabel lblNhanVien = new JLabel("Nhân viên :");
        lblNhanVien.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblNhanVien, "align right");

        txtNhanVien= new MyTextField();
        txtNhanVien.setFont(new Font(fontName, fontPlain, font14));
        txtNhanVien.setEnabled(false);
        txtNhanVien.setBorderLine(true);
        txtNhanVien.setBorderRadius(5);
        pnlThongTin.add(txtNhanVien, "w 100:260:350, h 36! , wrap");
        
        pnlThongTin.setBackground(Color.WHITE);
        pnlInfoBottom.add(pnlThongTin,"w 35%,h 100%");
        
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(20, 230));
        pnlInfoBottom.add(spr); 

    }
    
    public void initCustomer(){
        
        pnlKhachHang = new JPanel();
        pnlKhachHang.setBackground(Color.WHITE);
        pnlKhachHang.setLayout(new MigLayout("","10[][]","20[]10[]10[]10[]"));
        
        JLabel lblTTKhachHang = new JLabel("Thông tin khách hàng");
        lblTTKhachHang.setFont(new Font(fontName, fontPlain, font16));
        lblTTKhachHang.setForeground(colorLabel);
        pnlKhachHang.add(lblTTKhachHang, "span, w 100%, h 30!, wrap");
        
        JLabel lblSdt= new JLabel("Số điện thoại :");
        lblSdt.setFont(new Font(fontName, fontPlain, font14));
        pnlKhachHang.add(lblSdt, "align right");

        txtSdt = new MyTextField();
        txtSdt.setFont(new Font(fontName, fontPlain, font14));
        txtSdt.setBorderLine(true);
        txtSdt.setBorderRadius(5);
        pnlKhachHang.add(txtSdt, "w 100:260:300, h 36! , wrap");
        
        JLabel lblTenKhachHang = new JLabel("Tên khách hàng :");
        lblTenKhachHang.setFont(new Font(fontName, fontPlain, font14));
        pnlKhachHang.add(lblTenKhachHang, "align right");

        txtTenKhachHang = new MyTextField();
        txtTenKhachHang.setFont(new Font(fontName, fontPlain, font14));
        txtTenKhachHang.setBorderLine(true);
        txtTenKhachHang.setBorderRadius(5);
        pnlKhachHang.add(txtTenKhachHang, "w 100:260:300, h 36! , wrap");
        
        JLabel lblCCCD = new JLabel("Số thẻ căn cước :");
        lblCCCD.setFont(new Font(fontName, fontPlain, font14));
        pnlKhachHang.add(lblCCCD, "align right");

        txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font(fontName, fontPlain, font14));
        txtCCCD.setBorderLine(true);
        txtCCCD.setBorderRadius(5);
        pnlKhachHang.add(txtCCCD, "w 100:260:300, h 36! , wrap");

        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new MigLayout("","push[]10[]","push[][]"));
        pnlButton.setBackground(Color.WHITE);
        
        btnHuy = new Button("Giao phòng");
        btnHuy.setFont(new Font(fontName, fontPlain, font14));
        btnHuy.setBackground(colorBtn);
        btnHuy.setBorderRadius(5);
        
        btnThoat = new Button("Thoát");
        btnThoat.setFont(new Font(fontName, fontPlain, font14));
        btnThoat.setBackground(colorBtn);
        btnThoat.setBorderRadius(5);
        
        pnlButton.add(btnHuy,"h 36!");
        pnlButton.add(btnThoat,"h 36!");
        
        pnlKhachHang.add(pnlButton,"span, w 100%,h 100%, wrap");
      
        pnlInfoBottom.add(pnlKhachHang,"w 65%,h 100%");
        
    }
    
    public void initForm(){
        nhaCungCapVaNhaphang_DAO = new NhaCungCapVaNhapHang_DAO();
        df = new DecimalFormat("#,##0.00");
        setSize(new Dimension(1300,650));
        setLocation(150, 150);
        mainPanel.setLayout(new MigLayout("","20[center]20"));
//        MainPanel.setBackground(Color.WHITE);
        
//        setResizable(false);
        pnlInfoTop = new PanelShadow();
        pnlInfoTop.setLayout(new MigLayout("", "20[center] 20 [center]20", "20[]20"));
        pnlInfoBottom = new PanelShadow();
        pnlInfoBottom.setLayout(new MigLayout("", "20[center] 20 [center]20", "20[]"));
        
        initSetTheRoom();
        initService();
        initRoom();
        initCustomer();

        pnlInfoTop.setBackground(Color.WHITE);
        pnlInfoBottom.setBackground(Color.WHITE);
        
        mainPanel.add(pnlInfoTop,"w 100%,h 50%,wrap");
        mainPanel.add(pnlInfoBottom,"w 100%,h 50%");
    }
    
    public void initData(){
        txtTenPhong.setText(phong.getTenPhong());
        txtLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
        txtGia.setText(df.format(phong.getLoaiPhong().getGiaPhong()));
        txtNhanVien.setText(nhanVien.getTenNhanVien());
    }
    
    public void addAction(){
        tablePhieuDatPhong.addMouseListener(new createMouseListener());
        tableDichVuDaChon.addMouseListener(new createMouseListener());
        txtSdt.addKeyListener(new createKeyListener());
    }
    
    public void initKhachHang(String sdt){
        KhachHang kh = nhaCungCapVaNhaphang_DAO.getKhachHangBySDT(sdt);
        txtTenKhachHang.setText(kh.getTenKhachHang());
        txtCCCD.setText(kh.getCanCuocCD());
    }
    
    private class createKeyListener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
           Object obj = e.getSource();
           if(e.getKeyChar() == KeyEvent.VK_ENTER && obj.equals(txtSdt)){
               String sdt = txtSdt.getText().trim();
               if(sdt != null){
                   initKhachHang(sdt);
               }
           }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
        
    }
    
    private class createMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Object obj = e.getSource();
            if(obj.equals(tablePhieuDatPhong)){
                String ma = tablePhieuDatPhong.getModel().getValueAt(tablePhieuDatPhong.getSelectedRow(), 0).toString();
                System.out.println(ma);
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
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
