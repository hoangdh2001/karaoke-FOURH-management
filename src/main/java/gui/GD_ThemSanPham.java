/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import entity.NhaCungCap;
import java.util.List;
import dao.NhaCungCapVaNhapHang_DAO;
import entity.LoaiDichVu;
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
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
    
    private JCheckBox spMoi;
    
    private MyTable table;

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
    public GD_ThemSanPham() {
        initComponents();
        this.setLayout(new MigLayout("","10[]10","10[]10"));
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
        
        spMoi = new JCheckBox("Sản phẩm mới");
        pnlSanPham.add(spMoi,"align left,h 36!,wrap");
        
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
        
        
        spMoi.addItemListener(new ItemListener() {
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
        btnLuu.setBackground(colorBtn);
        
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
    
    public void initDao(){
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();

        List<NhaCungCap> listNCC = nhaCungCapVaNhapHang_DAO.getNhaCungCap();
        for (int i = 0; i < listNCC.size(); i++) {
            NhaCungCap ncc = listNCC.get(i);
            cbNhaCungCap.addItem(new ObjectComboBox(ncc.getTenNCC(),ncc.getMaNCC()));  
        }
        
        List<LoaiDichVu> listDV = nhaCungCapVaNhapHang_DAO.getLoaiDichVu();
        for (int i = 0; i < listDV.size(); i++) {
            LoaiDichVu dv = listDV.get(i);
            cbLoaiSP.addItem(new ObjectComboBox(dv.getTenLoaiDichVu(),dv.getMaLoaiDichVu()));  
        }
        
        
    }
    
    public void initAction(){     
        btnThemNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GD_ThemNhaCungCap(null).setVisible(true);
            }
        });
        
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
                }
            }
        });
        
        btnLuu.addActionListener(new createActionListenner());
        btnXemThongTin.addActionListener(new createActionListenner());
    };
    
    public class createActionListenner implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            if(o.equals(btnLuu)){
//                insert lo hang
                String maLoHang = "LH0005";
                ngayNhap = new Date();
                double tongTien = 500;
                String maNhanVien = "NV0007"; 
                ObjectComboBox maNCC = (ObjectComboBox)cbNhaCungCap.getSelectedItem();
//                insert mat hang
                String giaNhap = txtGiaNhap.getText();
                String soLuong = txtSoLuong.getText();
                String giaBan = txtGiaban.getText();

                if(spMoi.isSelected()){
                    ObjectComboBox loaiSP = (ObjectComboBox)cbLoaiSP.getSelectedItem();
                    String maLoaiDichVu = loaiSP.getMa();
                    String tenMatHang = pnlSPMoi.getTenSanPhamMoi();
                    String maMatHang = "MH0050";
                    
                    System.out.println(
                    "insert Lo hang: "+ maLoHang+ " " + ngayNhap +" "+tongTien +" "+ maNhanVien+" " +maNCC.getMa()+"\n"+
                    "insert"+ maMatHang+" " + giaBan+" " +soLuong+" " +tenMatHang+" " + maLoaiDichVu+" "
                    );
                }else{
                    String maMatHang = pnlSPMoi.getMaSanPhamCu();
                    
                    System.out.println(
                    "insert Lo hang: "+ maLoHang+ " " + ngayNhap +" "+tongTien +" "+ maNhanVien+" " +maNCC.getMa()+"\n"+
                    "update Mat Hang "+maMatHang+"set slTonKho = "+"slTonKho"+soLuong+" dongia =" + giaBan
                    );
                }
            }else if(o.equals(btnXemThongTin)){
                if(cbNhaCungCap.getSelectedIndex() !=0){
                    ObjectComboBox maNCC = (ObjectComboBox)cbNhaCungCap.getSelectedItem();
                    NhaCungCap ncc = nhaCungCapVaNhapHang_DAO.getNhaCungCapById(maNCC.getMa());
                    new GD_ThemNhaCungCap(ncc).setVisible(true);
                }
            }
        }
        
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
