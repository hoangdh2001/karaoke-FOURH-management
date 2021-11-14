/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import dao.NhaCungCapVaNhapHang_DAO;
import dao.Phong_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.MatHang;
import entity.NhanVien;
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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import net.miginfocom.swing.MigLayout;
import entity.PhieuDatPhong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import objectcombobox.ObjectComboBox;

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
    private Button btnGiaoPhong;
    
    private ArrayList<ObjectComboBox> listDaChon;
    private ArrayList<ObjectComboBox> listDaChonSoLuong;
    
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
        this.phong = phong;
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public void initSetTheRoom(){
        pnlDanhSachPhieu = new JPanel();
        pnlDanhSachPhieu.setOpaque(false);
        pnlDanhSachPhieu.setLayout(new MigLayout("","[]","10[]10"));
        pnlDanhSachPhieu.setBackground(Color.WHITE); 
            
        JLabel lblDanhSachPhieu = new JLabel("Thông tin phiếu");
        lblDanhSachPhieu.setFont(new Font(fontName, fontPlain, font16));
        lblDanhSachPhieu.setForeground(colorLabel);
        pnlDanhSachPhieu.add(lblDanhSachPhieu, "span, w 100%, h 30!, wrap");
        
 
        String col[] = {"Mã phiếu","Khách hàng","Giờ"};
        DefaultTableModel model = new DefaultTableModel(
            col,
            0
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
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
				default:
					return String.class;
				
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
                { new ObjectComboBox("1002","ma"), "Tran Van Minh", "6000",false}             
        };
 
        String col[] = {"Tên","Số lượng","Giá","Chọn"};
        DefaultTableModel model = new DefaultTableModel(
            col,
            0
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
        
        
        
        JScrollPane sp = new JScrollPane(tableDichVu);

        tableDichVu.fixTable(sp);
//Da chon sp
        String colSelected[] = {"Tên","Số lượng"};
        
        DefaultTableModel modelSelected = new DefaultTableModel(
            colSelected,
            0
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
        tableDichVuDaChon.getColumnModel().getColumn(0).setPreferredWidth(160);
        tableDichVuDaChon.getColumnModel().getColumn(1).setPreferredWidth(75);
        tableDichVuDaChon.setCellSelectionEnabled(true);
        
        TableColumnModel tcm = tableDichVuDaChon.getColumnModel();
        TableColumn tc = tcm.getColumn(1);
        tc.setCellEditor(new SpinnerEditor(100));
        
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
        
        btnGiaoPhong= new Button("Giao phòng");
        btnGiaoPhong.setFont(new Font(fontName, fontPlain, font14));
        btnGiaoPhong.setBackground(colorBtn);
        btnGiaoPhong.setBorderRadius(5);
        
        btnHuy = new Button("Thoát");
        btnHuy.setFont(new Font(fontName, fontPlain, font14));
        btnHuy.setBackground(colorBtn);
        btnHuy.setBorderRadius(5);
        
        pnlButton.add(btnGiaoPhong,"h 36!");
        pnlButton.add(btnHuy,"h 36!");
        
        pnlKhachHang.add(pnlButton,"span, w 100%,h 100%, wrap");
      
        pnlInfoBottom.add(pnlKhachHang,"w 65%,h 100%");
        
    }
    
    public void initForm(){
        listDaChon = new ArrayList<ObjectComboBox>();
        listDaChonSoLuong = new ArrayList<ObjectComboBox>();
        nhaCungCapVaNhaphang_DAO = new NhaCungCapVaNhapHang_DAO();
        df = new DecimalFormat("#,##0.00");
        setSize(new Dimension(1300,650));
        mainPanel.setLayout(new MigLayout("","20[center]20"));
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
        if(phong != null | nhanVien != null) {
            txtTenPhong.setText(phong.getTenPhong());
            txtLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
            txtGia.setText(df.format(phong.getLoaiPhong().getGiaPhong()));
            txtNhanVien.setText(nhanVien.getTenNhanVien());
            List<PhieuDatPhong> dsPhieuDatPhong = nhaCungCapVaNhaphang_DAO.getPhieuHomNay(phong.getMaPhong());
            dsPhieuDatPhong.forEach(phieu -> 
                    tablePhieuDatPhong.addRow(phieu.convertToRowTableInGDTiepNhanDatPhong()));

            List<MatHang> dsMatHang = nhaCungCapVaNhaphang_DAO.getDanhSachMatHang();
            dsMatHang.forEach(matHang -> tableDichVu.addRow(matHang.convertToRowTableInGDTiepNhanDatPhong()));
        }
    }
    
    public void addAction(){
        tablePhieuDatPhong.addMouseListener(new createMouseListener());
        tableDichVu.addMouseListener(new createMouseListener());
        tableDichVuDaChon.addKeyListener(new createKeyListener());
        txtSdt.addKeyListener(new createKeyListener());
        txtTenKhachHang.addKeyListener(new createKeyListener());
        txtCCCD.addKeyListener(new createKeyListener());
        btnGiaoPhong.addActionListener(new createActionListenner());
    }
    
    public void initKhachHang(String sdt){
        KhachHang kh = nhaCungCapVaNhaphang_DAO.getKhachHangBySDT(sdt);
        if(kh != null){
            txtTenKhachHang.setText(kh.getTenKhachHang());
            txtCCCD.setText(kh.getCanCuocCD());
        }
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
               if(!sdt.equals("")){
                   initKhachHang(sdt);
               }
           }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Object obj = e.getSource();
            if (e.getKeyChar() == KeyEvent.VK_ENTER){
               if(obj.equals(txtSdt) || obj.equals(txtTenKhachHang) || obj.equals(txtCCCD)){
                    if((txtSdt.getText().trim() + txtTenKhachHang.getText().trim() +  txtCCCD.getText().trim()).equals("") ){
                        tablePhieuDatPhong.clearSelection();
                    }
                }
           }else if (e.getKeyChar() == KeyEvent.VK_ENTER && obj.equals(tableDichVuDaChon)){
               int idx = (int)tableDichVuDaChon.getSelectedRow();
               ObjectComboBox cb = (ObjectComboBox)tableDichVuDaChon.getValueAt(idx, 0);
               int soLuong = 0;
               for(int i = 0; i < tableDichVu.getRowCount(); i++) {
                    if(tableDichVu.getValueAt(i, 0).equals(cb)) {
                        soLuong = (int)tableDichVu.getValueAt(i, 1);
                        listDaChonSoLuong.get(i).setMa(String.valueOf(tableDichVuDaChon.getValueAt(idx, 1)));
                        break;
                    }
                }
               if((int)tableDichVuDaChon.getValueAt(idx, 1) > soLuong){
                   JOptionPane.showMessageDialog(GD_TiepNhanDatPhong.this,cb.toString()+ " số lượng chỉ còn " + soLuong);
                   tableDichVuDaChon.editCellAt(idx,1);
               }
           }
        }
        
    }
    
    private class createMouseListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            Object obj = e.getSource();
            if(obj.equals(tableDichVu)){
                CountDownLatch lt = new CountDownLatch(1);
                Thread thread = new Thread(() -> {
                    System.out.println(tableDichVu.getValueAt(tableDichVu.getSelectedRow(), 3));
                    for(int i = 0; i < tableDichVu.getRowCount(); i++) {
                        ObjectComboBox cb = (ObjectComboBox) tableDichVu.getModel().getValueAt(i, 0);
                        ObjectComboBox sl = new ObjectComboBox(cb.getMa(),"1");
			if((Boolean)tableDichVu.getValueAt(i, 3)) {
                            if(!listDaChon.contains(cb)) {
                                listDaChon.add(cb);
                                listDaChonSoLuong.add(sl);
                            }
			}else {
                            listDaChon.remove(cb);
                            listDaChonSoLuong.remove(sl);
			}
                    }
                    
                    DefaultTableModel model = (DefaultTableModel)tableDichVuDaChon.getModel();
                    model.setRowCount(0);
                    
                    for(int i = 0; i < listDaChon.size(); i++){
                        ObjectComboBox cb = (ObjectComboBox) listDaChon.get(i);
                        tableDichVuDaChon.addRow(new Object[]{cb,Integer.parseInt(listDaChonSoLuong.get(i).getMa())});
                        
                    }
                    repaint();
                    revalidate();
                    lt.countDown();
                });
                
                thread.start();
                
                try {
                    lt.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Object obj = e.getSource();
            if(obj.equals(tablePhieuDatPhong)){
                String ma = tablePhieuDatPhong.getModel().getValueAt(tablePhieuDatPhong.getSelectedRow(), 0).toString();
                PhieuDatPhong phieuDatPhong = nhaCungCapVaNhaphang_DAO.getPhieuById(ma);
                txtSdt.setText(phieuDatPhong.getKhachHang().getSoDienThoai());
                txtTenKhachHang.setText(phieuDatPhong.getKhachHang().getTenKhachHang());
                txtCCCD.setText(phieuDatPhong.getKhachHang().getCanCuocCD());
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
    
    private boolean validateData() {
		String sdt = txtSdt.getText().trim();
		String hoTen = txtTenKhachHang.getText().trim();
		String CCCD = txtCCCD.getText().trim();
                
		if (sdt.trim().equals("")) {
			showMsg("Số điện thoại khách hàng trống !");
			txtSdt.selectAll();
			txtSdt.requestFocus();
			return false;
		} else {
			if (!(sdt.matches("^[0-9]{10}$"))) {
				showMsg("Số điện thoại khách hàng không hợp lệ");
				txtSdt.requestFocus();
				txtSdt.selectAll();
				return false;
			}
		}
                
		if (hoTen.trim().equals("")) {
			showMsg("Họ tên khách hàng trống !");
			txtTenKhachHang.selectAll();
			txtTenKhachHang.requestFocus();
			return false;
		} else {

			if ((hoTen.matches(
					"^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
				showMsg("tên khách hàng không hợp lệ");
				txtTenKhachHang.requestFocus();
				txtTenKhachHang.selectAll();
				return false;
			}
		}

		if (CCCD.trim().equals("")) {
			showMsg("Căn cước công dân không được trống !");
			txtCCCD.selectAll();
			txtCCCD.requestFocus();
			return false;
		} else {
                            if (!(CCCD.matches("^(0)[0-9]{11}$"))) {
				showMsg("Căn cước công dân không hợp lệ");
				txtCCCD.requestFocus();
				txtCCCD.selectAll();
				return false;
                            }
		}
		return true;
	}
    
    private void showMsg(String msg) {
	JOptionPane.showMessageDialog(null, msg);
    }
    
    
    
    private class createActionListenner implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if(obj.equals(btnGiaoPhong) && validateData()){
                double tienCoc = 0;
                KhachHang kh = nhaCungCapVaNhaphang_DAO.getKhachHangBySDT(txtSdt.getText());
                if(kh == null){
                    String maKhachhang = nhaCungCapVaNhaphang_DAO.getlastKhachHangTang();
                    kh = new KhachHang(maKhachhang
                            ,txtTenKhachHang.getText()
                            ,txtCCCD.getText()
                            ,txtSdt.getText());
                    nhaCungCapVaNhaphang_DAO.addKhachHang(kh);
                }
                if(tablePhieuDatPhong.getSelectedRow() != -1){
                    String maPhieuDatPhong = tablePhieuDatPhong.getValueAt(tablePhieuDatPhong.getSelectedRow(), 0).toString();
                    nhaCungCapVaNhaphang_DAO.updatePhieuDatPhong(maPhieuDatPhong);
                    tienCoc = nhaCungCapVaNhaphang_DAO.getTienCoc(maPhieuDatPhong);
                }
                nhaCungCapVaNhaphang_DAO.updatePhong(phong.getMaPhong(), TrangThaiPhong.DANG_HAT);
                
                String maHoaDon = nhaCungCapVaNhaphang_DAO.getlastMaHoaDonTang();
                
                HoaDon hoaDon = new HoaDon(maHoaDon, kh, phong, nhanVien);
                nhaCungCapVaNhaphang_DAO.insertHoaDon(hoaDon,tienCoc);
//insert chi tiet hoa don
                if(tableDichVuDaChon.getRowCount() != 0){
                    for( int i = 0 ; i< tableDichVuDaChon.getRowCount(); i++){
                        ObjectComboBox cb = (ObjectComboBox)tableDichVuDaChon.getValueAt(i, 0);
                        int soluong = Integer.parseInt(tableDichVuDaChon.getValueAt(i, 1).toString());
                        System.out.println(soluong);
                        if(soluong > 0){
                            MatHang matHang = nhaCungCapVaNhaphang_DAO.getMatHang(cb.getMa());
                            ChiTietHoaDon ctHoaDon = new ChiTietHoaDon(hoaDon,matHang, soluong, 0.0f);
                            nhaCungCapVaNhaphang_DAO.insertCTHoaDon(ctHoaDon);
                            nhaCungCapVaNhaphang_DAO.updateSLMatHang(cb.getMa(),soluong,"decrease");
                        }
                    }  
                }
                dispose();
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
