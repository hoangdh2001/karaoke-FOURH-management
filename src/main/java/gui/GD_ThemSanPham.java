/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import gui.component.PanelTenSanPham;
import gui.swing.table.SpinnerEditor;
import gui.swing.table2.MyTable;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author 84975
 */
public class GD_ThemSanPham extends javax.swing.JPanel {
    
    
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
    }

    public void initFrom(){
        JPanel pnlForm = new PanelShadow();
        pnlForm.setBackground(Color.WHITE);
        pnlForm.setLayout(new MigLayout("","20[]20"));
        
        JPanel pnlNhaCungCap = new JPanel();
        pnlNhaCungCap.setOpaque(false);
        pnlNhaCungCap.setLayout(new MigLayout("","10[]5[]5[]","20[]20"));
        
        JLabel lblTimKiem = new JLabel("Nhà cung cấp :");
        lblTimKiem.setFont(new Font(fontName, fontPlain, font14));
     
        MyComboBox<String> cbNhaCungCap = new MyComboBox<>(new String[] {"cái j đó"});
        cbNhaCungCap.setFont(new Font(fontName, fontPlain, font12));
        cbNhaCungCap.setBorderLine(true);
        cbNhaCungCap.setBorderRadius(10);

        Button btnThemNCC = new Button("Thêm nhà cung cấp");
        btnThemNCC.setFont(new Font(fontName, fontPlain, font14));
        btnThemNCC.setBackground(colorBtn);
        btnThemNCC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GD_ThemNhaCungCap().setVisible(true);
            }
        });
        
        pnlNhaCungCap.add(lblTimKiem);
        pnlNhaCungCap.add(cbNhaCungCap,"w 100:300:500, h 36!");
        pnlNhaCungCap.add(btnThemNCC,"w 200,h 36!");

        pnlForm.add(pnlNhaCungCap,"w 100%,wrap");
        
        JSeparator spr = new JSeparator(SwingConstants.HORIZONTAL);
        spr.setPreferredSize(new Dimension(10000, 20));
        pnlForm.add(spr,"w 100%,wrap");
        
//        sanPham
        
        JPanel pnlSanPham = new JPanel();
        pnlSanPham.setBackground(Color.WHITE);
        pnlSanPham.setLayout(new MigLayout("", "[center][center] 10 [center][center]", "[]10[center]10[center]"));
        
        JCheckBox spMoi = new JCheckBox("Sản phẩm mới");
        pnlSanPham.add(spMoi,"align left,h 36!,wrap");
        
        JLabel lblLoaiSP = new JLabel("Loại sản phẩm :");
        lblLoaiSP.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblLoaiSP, "align right");
        
        MyComboBox<String> cbLoaiSP = new MyComboBox<>(new String[] {"loại j đó"});
        cbLoaiSP.setFont(new Font(fontName, fontPlain, font12));
        cbLoaiSP.setBorderLine(true);
        cbLoaiSP.setBorderRadius(10);
        pnlSanPham.add(cbLoaiSP, "w 80%, h 36!");
        
        JLabel lblTenSP = new JLabel("Tên sản phẩm :");
        lblTenSP.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblTenSP, "align right");
        
        PanelTenSanPham pnlSPMoi = new PanelTenSanPham();
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
        
        MyTextField txtSoLuong = new MyTextField();
        txtSoLuong.setFont(new Font(fontName, fontPlain, font14));
        txtSoLuong.setBorderLine(true);
        pnlSanPham.add(txtSoLuong, "w 80%, h 36!");
        
        JLabel lblGiaNhap = new JLabel("Giá nhập :");
        lblGiaNhap.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblGiaNhap, "align right");
        
        MyTextField txtGiaNhap = new MyTextField();
        txtGiaNhap.setFont(new Font(fontName, fontPlain, font14));
        txtGiaNhap.setBorderLine(true);
        pnlSanPham.add(txtGiaNhap, "w 80%, h 36!, wrap");
        
        JLabel lblGiaban = new JLabel("Giá Bán :");
        lblGiaban.setFont(new Font(fontName, fontPlain, font14));
        
        pnlSanPham.add(lblGiaban, "align right");
        
        MyTextField txtGiaban = new MyTextField();
        txtGiaban.setFont(new Font(fontName, fontPlain, font14));
        txtGiaban.setBorderLine(true);
        pnlSanPham.add(txtGiaban, "w 80%, h 36!, wrap");
        
        Button btnThem = new Button("Thêm");
        btnThem.setFont(new Font(fontName, fontPlain, font14));
        btnThem.setBackground(colorBtn);
        
        
        Button btnXoa = new Button("Xóa rỗng");
        btnXoa.setFont(new Font(fontName, fontPlain, font14));
        btnXoa.setBackground(colorBtn);
       
        pnlSanPham.add(btnThem, "w 100!, h 36!,pos 0.98al 0.95al n n");
        pnlSanPham.add(btnXoa, "w 100!, h 36!,pos 0.8al 0.95al n n");
        
        pnlForm.add(pnlSanPham,"w 100%");
        
        this.add(pnlForm,"w 100%,wrap");
    }
    
    public void initTable(){
        JPanel pnlTable = new PanelShadow();
        pnlTable.setLayout(new MigLayout("fill"));
        
        JLabel lblTTSanPhamMoi = new JLabel("Thông tin sản phẩm");
        lblTTSanPhamMoi.setFont(new Font(fontName, fontPlain, font16));
        lblTTSanPhamMoi.setForeground(colorLabel);
        pnlTable.add(lblTTSanPhamMoi, "span, w 100%, h 30!, wrap");
        
        pnlTable.setBackground(Color.WHITE);
        Object data[][] = { 
                {  "Tran Van Minh","a", "60","60000","60000","60000"}, 
                {  "Phan Van Tai","a", "80","60000","60000","60000"}, 
                {  "Do Cao Hoc","a", "70","60000","60000","60000"},             
        };
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
