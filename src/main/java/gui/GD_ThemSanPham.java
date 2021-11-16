/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import entity.NhaCungCap;
import java.util.List;
import dao.NhaCungCapVaNhapHang_DAO;
import entity.LoHang;
import entity.LoaiDichVu;
import entity.MatHang;
import entity.NhanVien;
import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import gui.component.PanelTenSanPham;
import gui.swing.table2.MyTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import objectcombobox.ObjectComboBox;

/**
 *
 * @author 84975
 */
public class GD_ThemSanPham extends javax.swing.JPanel {
    
    private MyComboBox<String> cbNhaCungCap;
    private MyComboBox<String> cbLoaiSP;
    
    private Button btnThemNCC;
    private Button btnThem;
    private Button btnXoa;
    private Button btnLuu;
    private Button btnXemThongTin;
    
    private MyTextField txtSoLuong;
    private MyTextField txtGiaNhap;
    private MyTextField txtGiaban;
    
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhapHang_DAO ;
    
    private PanelTenSanPham pnlSPMoi;
    
    private Date ngayNhap;
    
    private JCheckBox cbSpMoi;
    
    private MyTable table;
    
    private NhanVien nhanVien;
    
    private List<Boolean> isNew;
    
    private Map<MatHang,Boolean> isNewSP;
    
    private LoHang loHang;
    
    private DecimalFormat df;

    private String fontName = "sansserif";
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    private int font12 = 12;
    private Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);
    /**
     * Creates new form GD_ThemDichVu
     */
    public GD_ThemSanPham(NhanVien nhanVien) {
        initComponents();
        this.setLayout(new MigLayout("","10[]10","10[]10"));
        this.nhanVien = nhanVien;
        initFrom();
        initTable();
        initDao();
        initAction();
    }

    public void initFrom(){
        JPanel pnlForm = new PanelShadow();
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setLayout(new MigLayout("","20[]20"));
        
        JPanel pnlNhaCungCap = new JPanel();
        pnlNhaCungCap.setOpaque(false);
        pnlNhaCungCap.setLayout(new MigLayout("","10[]5[]5[]","20[]20"));
        
        JLabel lblNCC = new JLabel("Nhà cung cấp :");
        lblNCC.setFont(new Font(fontName, fontPlain, font14));
     
        cbNhaCungCap = new MyComboBox<>(new String[] {"Chọn nhà cung cấp"});
        cbNhaCungCap.setFont(new Font(fontName, fontPlain, font12));
        cbNhaCungCap.setBorderLine(true);
        cbNhaCungCap.setBorderRadius(10);

        btnThemNCC = new Button("Thêm nhà cung cấp");
        btnThemNCC.setFont(new Font(fontName, fontPlain, font14));
        btnThemNCC.setBackground(colorBtn);
        
        btnXemThongTin = new Button("Xem thông tin");
        btnXemThongTin.setFont(new Font(fontName, fontPlain, font14));
        btnXemThongTin.setBackground(colorBtn);
   
        pnlNhaCungCap.add(lblNCC);
        pnlNhaCungCap.add(cbNhaCungCap,"w 100:300:500, h 36!");
        pnlNhaCungCap.add(btnThemNCC,"w 200!,h 36!");
        pnlNhaCungCap.add(btnXemThongTin,"w 200!,h 36!");

        pnlForm.add(pnlNhaCungCap,"w 100%,wrap");
        
        JSeparator spr = new JSeparator(SwingConstants.HORIZONTAL);
        spr.setPreferredSize(new Dimension(10000, 20));
        pnlForm.add(spr,"w 100%,wrap");
        
//        sanPham
        
        JPanel pnlSanPham = new JPanel();
        pnlSanPham.setBackground(Color.WHITE);
        pnlSanPham.setLayout(new MigLayout("", "[center][center] 10 [center][center]", "[]10[center]10[center]"));
        
        cbSpMoi = new JCheckBox("Sản phẩm mới");
        pnlSanPham.add(cbSpMoi,"align left,h 36!,wrap");
        
        JLabel lblLoaiSP = new JLabel("Loại sản phẩm :");
        lblLoaiSP.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblLoaiSP, "align right");
        
        cbLoaiSP = new MyComboBox<>(new String[] {"Chọn loại sản phẩm"});
        cbLoaiSP.setFont(new Font(fontName, fontPlain, font12));
        cbLoaiSP.setBorderLine(true);
        cbLoaiSP.setBorderRadius(10);
        pnlSanPham.add(cbLoaiSP, "w 80%, h 36!");
        
        JLabel lblTenSP = new JLabel("Tên sản phẩm :");
        lblTenSP.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblTenSP, "align right");
        
        pnlSPMoi = new PanelTenSanPham();
        pnlSPMoi.setTypeSP(false);
        pnlSanPham.add(pnlSPMoi,"w 80%,h 36!,wrap");
        
        
        cbSpMoi.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange() == ItemEvent.SELECTED){
                    pnlSPMoi.setTypeSP(true);
                }else{
                    pnlSPMoi.setTypeSP(false);
                }
            }
        });

        JLabel lblSoLuong = new JLabel("Số lượng :");
        lblSoLuong.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblSoLuong, "align right");
        
        txtSoLuong = new MyTextField();
        txtSoLuong.setFont(new Font(fontName, fontPlain, font14));
        txtSoLuong.setBorderLine(true);
        pnlSanPham.add(txtSoLuong, "w 80%, h 36!");
        
        JLabel lblGiaNhap = new JLabel("Giá nhập :");
        lblGiaNhap.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblGiaNhap, "align right");
        
        txtGiaNhap = new MyTextField();
        txtGiaNhap.setFont(new Font(fontName, fontPlain, font14));
        txtGiaNhap.setBorderLine(true);
        pnlSanPham.add(txtGiaNhap, "w 80%, h 36!, wrap");
        
        JLabel lblGiaban = new JLabel("Giá Bán :");
        lblGiaban.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblGiaban, "align right");
        
        txtGiaban = new MyTextField();
        txtGiaban.setFont(new Font(fontName, fontPlain, font14));
        txtGiaban.setBorderLine(true);
        pnlSanPham.add(txtGiaban, "w 80%, h 36!, wrap");
        
        btnThem = new Button("Thêm");
        btnThem.setFont(new Font(fontName, fontPlain, font14));
        btnThem.setBackground(colorBtn);
        
        
        btnXoa = new Button("Xóa rỗng");
        btnXoa.setFont(new Font(fontName, fontPlain, font14));
        btnXoa.setBackground(colorBtn);
        
        btnLuu = new Button("Lưu");
        btnLuu.setFont(new Font(fontName, fontPlain, font14));
        btnLuu.setBackground(new Color(255,0,0));
        
        JPanel pnlbtn = new JPanel();
        pnlbtn.setOpaque(false);
        pnlbtn.setLayout(new MigLayout("","0[]10[]10[]0","0[]0"));
        
        pnlbtn.add(btnThem,"w 100%,h 36!");
        pnlbtn.add(btnXoa,"w 100%,h 36!");
        pnlbtn.add(btnLuu,"w 100%,h 36!");
        
        pnlSanPham.add(pnlbtn, "w 300!, h 36!,pos 1al 0.95al n n");
        
        pnlForm.add(pnlSanPham,"w 100%");
        
        this.add(pnlForm,"w 100%,wrap");
    }
    
    public void initTable(){
        JPanel pnlTable = new PanelShadow();
        pnlTable.setLayout(new MigLayout("fill","20[]"));
        
        JLabel lblTTSanPhamMoi = new JLabel("Thông tin sản phẩm");
        lblTTSanPhamMoi.setFont(new Font(fontName, fontPlain, font16));
        lblTTSanPhamMoi.setForeground(colorLabel);
        pnlTable.add(lblTTSanPhamMoi, "span, w 100%, h 30!, wrap");
        
        pnlTable.setBackground(Color.WHITE);
        Object data[][] = {};
        String col[] = {"Tên","loại sản phẩm","Số lượng","Giá nhập","Giá bán","Tổng"};
        
        DefaultTableModel model = new DefaultTableModel(
            data,
            col
        ) {
            boolean[] canEdit = new boolean [] {
                false, false,false, false,false,false
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
					return Integer.class;
                                case 4:
					return String.class;
                                case 5:
					return String.class;
				default:
					return Boolean.class;
				}
			}
        };
    
        table.setModel(model);
        
        JScrollPane sp = new JScrollPane(table);
        table.fixTable(sp);
        
        pnlTable.add(sp,"w 100%,h 100%");
        this.add(pnlTable,"w 100%,h 100%");
    }
    
    public void loadNCC(){
        List<NhaCungCap> listNCC = nhaCungCapVaNhapHang_DAO.getNhaCungCap();
        for (int i = 0; i < listNCC.size(); i++) {
            NhaCungCap ncc = listNCC.get(i);
            cbNhaCungCap.addItem(new ObjectComboBox(ncc.getTenNCC(),ncc.getMaNCC()));  
        }
    }
    
    public void initDao(){
        loHang = new LoHang();
        isNewSP = new HashMap<MatHang,Boolean>();
        df = new DecimalFormat("#,##0.00");
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
        isNew = new ArrayList<Boolean>();
        
        loadNCC();
        
        List<LoaiDichVu> listDV = nhaCungCapVaNhapHang_DAO.getLoaiDichVu();
        for (int i = 0; i < listDV.size(); i++) {
            LoaiDichVu dv = listDV.get(i);
            cbLoaiSP.addItem(new ObjectComboBox(dv.getTenLoaiDichVu(),dv.getMaLoaiDichVu()));  
        }
        
        
    }
    
    public void initAction(){     
        btnThemNCC.addActionListener(new createActionListenner());
        
        cbNhaCungCap.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                    if(cbNhaCungCap.getSelectedIndex() != 0){
                    ObjectComboBox ncc = (ObjectComboBox)cbNhaCungCap.getSelectedItem();
                    System.out.println(ncc.getMa());
                }
            }
        });
        
        cbLoaiSP.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                if(cbLoaiSP.getSelectedIndex() != 0){
                    ObjectComboBox dv = (ObjectComboBox)cbLoaiSP.getSelectedItem();
                    pnlSPMoi.setComboboxItem(dv.getMa());
                }else{
                    pnlSPMoi.setComboboxItem("");
                }
            }
        });
        
        btnThem.addActionListener(new createActionListenner());
        btnLuu.addActionListener(new createActionListenner());
        btnXemThongTin.addActionListener(new createActionListenner());
                
        btnXoa.addActionListener(new createActionListenner());
        
        txtSoLuong.setFocusTraversalKeysEnabled(false);
        txtSoLuong.addKeyListener(new createKetlistener());

        txtGiaNhap.setFocusTraversalKeysEnabled(false);
        txtGiaNhap.addKeyListener(new createKetlistener());
        
        txtGiaban.setFocusTraversalKeysEnabled(false);
        txtGiaban.addKeyListener(new createKetlistener());
    };
    
    public void resetData(){
        DefaultTableModel df = (DefaultTableModel)table.getModel();
        df.setRowCount(0);
        loHang = new LoHang();
        isNewSP = new HashMap<MatHang,Boolean>();
    }
    
    public void xoaRong(){
        txtGiaNhap.setText("");
        txtGiaban.setText("");
        txtSoLuong.setText("");
        cbLoaiSP.setSelectedIndex(0);
        pnlSPMoi.setComboboxItem("");
    }
    
    private double convertMoneyToDouble(String money){
        String[] text = money.trim().split("\\,")[0].split("\\.");
        String tienTra = "";
        for(int i=0;i< text.length;i++){
            tienTra+=text[i];
        }
        return Double.parseDouble(tienTra);
    }
    
    private boolean valiDataSL(){
        int soLuong;
        try{
                    soLuong = Integer.parseInt(txtSoLuong.getText().trim());
                }catch (Exception e){
                    showMsg("Số lượng phải là số");
                    txtSoLuong.requestFocus();
                    txtSoLuong.selectAll();
                    return false;
                }
                
                if(soLuong <= 0){
                    showMsg("Số lượng phải lớn hơn 1");
                    txtSoLuong.requestFocus();
                    txtSoLuong.selectAll();
                    return false;
                }
        return true;
    }
    
    private boolean valiDataGiaNhap(){
        
        String giaNhap = txtGiaNhap.getText().trim();
        double tien = 0;
        try{
            tien = Double.parseDouble(giaNhap);
        }catch (Exception e){
            showMsg("Giá nhập phải là số");
                    txtGiaNhap.requestFocus();
                    txtGiaNhap.selectAll();
                    return false;
        }
        
        if(tien <= 0 ){
            showMsg("Giá nhập phải lớn hơn 0");
                    txtGiaNhap.requestFocus();
                    txtGiaNhap.selectAll();
                    return false;
        }
        txtGiaNhap.setText(df.format(tien));
        return true;
    }
    
    private boolean valiDataGiaban(){
        String giaban = txtGiaban.getText().trim();
        double giaNhap = convertMoneyToDouble(txtGiaNhap.getText().trim());
        double tien = 0;
        try{
            tien = Double.parseDouble(giaban);
        }catch (Exception e){
            showMsg("Giá nhập phải là số");
                    txtGiaban.requestFocus();
                    txtGiaban.selectAll();
                    return false;
        }
        
        if(tien <= giaNhap ){
            showMsg("Giá bán phải lớn hơn giá nhập");
                    txtGiaban.requestFocus();
                    txtGiaban.selectAll();
                    return false;
        }
        txtGiaban.setText(df.format(tien));
        return true;
    }
    
    private boolean valiSP(){
        int loaiSp = cbLoaiSP.getSelectedIndex();
		if (loaiSp == 0) {
                    showMsg("Chọn loại sản phẩm !");  
                    return false;
		}
        int SP = pnlSPMoi.getSelectedIndex();
        if(cbSpMoi.isSelected()){
                    if(pnlSPMoi.getTenSanPhamMoi().equals("")){
                        showMsg("Nhập tên sản phẩm mới !");  
                        pnlSPMoi.requestFocus();
                        return false;
                    }
                }else{
                    if (SP == 0) {
                    showMsg("Chọn sản phẩm !");  
                    return false;
                    }
                }
        return valiDataNull();
    }
    
    private boolean valiData() {
                int ncc = cbNhaCungCap.getSelectedIndex();
                String sl = txtSoLuong.getText().trim();
                String giaNhap = txtGiaNhap.getText().trim();
                String giaBan = txtGiaban.getText().trim();
                
                if(table.getRowCount() == 0){
                    showMsg("Không có sản phẩm nào được thêm !");  
                    return false;
                }
                
                if (ncc == 0) {
                    showMsg("Chọn nhà cung vấp !");  
                    return false;
		}
                
                if(!(sl.equals("") ||giaNhap.equals("") ||giaBan.equals(""))){
                    showMsg("Còn dữ liệu chưa được thêm. Hãy thêm dữ liệu trước khi lưu!");  
                    return false;
                }
                
		return true;
	}
    
    private boolean valiDataNull(){
        String sl = txtSoLuong.getText().trim();
        String giaNhap = txtGiaNhap.getText().trim();
        String giaBan = txtGiaban.getText().trim();
        
        if(sl.equals("")){
            showMsg("Nhập số lượng");  
            txtSoLuong.requestFocus();
            return false;
        }else if(giaNhap.equals("")){
            showMsg("Nhập giá nhập !");  
            txtGiaNhap.requestFocus();
            return false;
        } else if(giaBan.equals("")){
            showMsg("Nhập giá bán !");  
            txtGiaban.requestFocus();
            return false;
        }
        
        return true;
    }
    
    private void showMsg(String msg) {
	JOptionPane.showMessageDialog(null, msg);
    }
    
    public class createActionListenner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            if(o.equals(btnLuu) && valiData()){
//insert lo hang
                String maLoHang = nhaCungCapVaNhapHang_DAO.getLastLoHang();
                ObjectComboBox cb = (ObjectComboBox)cbNhaCungCap.getSelectedItem();
                NhaCungCap ncc = nhaCungCapVaNhapHang_DAO.getNhaCungCapById(cb.getMa());
                
                loHang.setMaLoHang(maLoHang);
                loHang.setNguoiNhap(nhanVien);
                loHang.setNhaCungCap(ncc);

                nhaCungCapVaNhapHang_DAO.insertLohang(loHang);

////insert and update sp
                isNewSP.forEach((sp,isInsert) ->{
                    if(isInsert){
                        nhaCungCapVaNhapHang_DAO.insertMatHang(sp);
                    }else{
                        nhaCungCapVaNhapHang_DAO.updateMatHang(sp);
                    }
                });
//insert ct nhap
                loHang.getDsChiTietNhapHang().forEach(ct -> {
                    nhaCungCapVaNhapHang_DAO.insertCTNhapHang(ct,loHang.getMaLoHang());
                });
           
                resetData();
                showMsg("Lưu thành công");
            }else if(o.equals(btnXemThongTin)){
                if(cbNhaCungCap.getSelectedIndex() !=0){
                    ObjectComboBox maNCC = (ObjectComboBox)cbNhaCungCap.getSelectedItem();
                    NhaCungCap ncc = nhaCungCapVaNhapHang_DAO.getNhaCungCapById(maNCC.getMa());
                    new GD_ThemNhaCungCap(ncc).setVisible(true);
                }
            }else if(o.equals(btnThem) && valiSP()){
                int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
                double giaNhap = convertMoneyToDouble(txtGiaNhap.getText().trim());
                double giaBan = convertMoneyToDouble(txtGiaban.getText().trim());
                ObjectComboBox cb = (ObjectComboBox)cbLoaiSP.getSelectedItem();
                LoaiDichVu loaiDichVu = nhaCungCapVaNhapHang_DAO.getLoaiDichVuByMa(cb.getMa());
                String tenMatHang ="";
                MatHang matHang;
                
                if(cbSpMoi.isSelected()){
                    String maMatHang = nhaCungCapVaNhapHang_DAO.getLastMatHang();
                    tenMatHang  = pnlSPMoi.getTenSanPhamMoi();
                    matHang = new MatHang(maMatHang, pnlSPMoi.getTenSanPhamMoi(), loaiDichVu, soLuong, giaBan);
                    isNewSP.put(matHang,true);
                }else{
                    tenMatHang = pnlSPMoi.getTenSanPhamCu();
                    String maMatHang = pnlSPMoi.getMaSanPhamCu();
                    matHang = new MatHang(maMatHang,tenMatHang, loaiDichVu, soLuong, giaBan);
                    isNewSP.put(matHang,false);
                }
                
                loHang.themCT_NhapHang(matHang, soLuong, giaNhap);
                
                table.addRow(new Object[]{ new ObjectComboBox(matHang.getTenMatHang(),matHang.getMaMatHang()),
                        cb.toString(),soLuong,
                        df.format(giaNhap), df.format(giaBan),df.format(giaNhap*soLuong)});
                xoaRong();
            }else if(o.equals(btnXoa)){
                xoaRong();
            }else if(o.equals(btnThemNCC)){
                new GD_ThemNhaCungCap(null).setVisible(true);
                loadNCC();
            }
        }
    }
    
    public class createKetlistener implements KeyListener{

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Object obj = e.getSource();
            if(obj.equals(txtSoLuong) && (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER)){
                if(valiDataSL()){
                    txtGiaNhap.requestFocus();
                }
            }else if(obj.equals(txtGiaNhap) && (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER)){
                if(valiDataGiaNhap()){
                    txtGiaban.requestFocus(); 
                }
            }else if(obj.equals(txtGiaban) && (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER)){
                valiDataGiaban();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
