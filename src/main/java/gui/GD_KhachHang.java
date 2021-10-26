/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import gui.swing.button.Button;
import gui.swing.table.TableCustom;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hao
 */
public class GD_KhachHang extends javax.swing.JPanel {

    private DefaultTableModel modelKhanhHang;

    /**
     * Creates new form GD_KhachHang
     */
    public GD_KhachHang() {
        initComponents();
        buildGD();
        tblKhachHang.fixTable(scrKhachHang);
    }

    private void buildGD(){
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setLayout(new MigLayout("", "3[center] 20 [center]3", "6[center]5"));
        pnlTop.setPreferredSize(new Dimension(1119,250 ));
        
       /**
        * Begin: group Thông tin khách hàng
        */        
        JPanel pnlThongTinKH = new JPanel();
        pnlThongTinKH.setOpaque(false);
        pnlThongTinKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16),  new Color(4, 72, 210)));
        pnlThongTinKH.setLayout(new MigLayout("", "10[center]10[center] 10 [center][center]10", "[center]10[center]10[center]15[center]"));
        pnlTop.add(pnlThongTinKH, "w 65%, h 240!");
        
        //Mã khách hàng
        JLabel lblMaKH = new JLabel("Mã khách hàng:");
        lblMaKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblMaKH, "align right");
        
        MyTextField txtMaKH = new MyTextField();
        txtMaKH.setFont(new Font(fontName, fontStyle, fontSize));
        txtMaKH.setBorderLine(true);
        pnlThongTinKH.add(txtMaKH, "w 80%, h 36!");
       
        
        //Tên khách hàng
        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblTenKH, "align right");
        
        MyTextField txtTenKH = new MyTextField();
        txtTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenKH.setBorderLine(true);
        pnlThongTinKH.add(txtTenKH, "w 80%, h 36!, wrap");
        
        //Căn cước công dân của khách hàng
        JLabel lblCCCD = new JLabel("CCCD:");
        lblCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblCCCD, "align right");
        
        MyTextField txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        txtCCCD.setBorderLine(true);
        pnlThongTinKH.add(txtCCCD, "w 80%, h 36!");
        
        //Số điện thoại của khách hàng
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblSDT, "align right");
        
        MyTextField txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        txtSDT.setBorderLine(true);
        pnlThongTinKH.add(txtSDT, "w 80%, h 36!, wrap");
        
        //Địa chỉ của khách hàng
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblDiaChi, "align right");
        
        MyTextField txtDiaChi = new MyTextField();
        txtDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        txtDiaChi.setBorderLine(true);
        pnlThongTinKH.add(txtDiaChi, "w 80%, h 36!, wrap");
        
        //Panel nút chức năng
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]20[]20[]20[]0", "push[]push"));
        pnlThongTinKH.add(pnlButton, "span , w 100%, h 36!");

        // Nút Xóa
        Button btnXoaKH = new Button("Xóa");
        btnXoaKH.setFont(new Font(fontName, fontStyle, fontSize));
        btnXoaKH.setBackground(colorBtn);
        pnlButton.add(btnXoaKH, "w 100!, h 36!,growx");

        // Nút Sửa
        Button btnSuaKH = new Button("Sửa");
        btnSuaKH.setFont(new Font(fontName, fontStyle, fontSize));
        btnSuaKH.setBackground(colorBtn);
        pnlButton.add(btnSuaKH, "w 100!, h 36!");

        // Nút Làm mới
        Button btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontStyle, fontSize));
        btnLamMoi.setBackground(colorBtn);
        pnlButton.add(btnLamMoi, "w 100!, h 36!");
        /**
         * End: group thông tin khách hàng
         */
        
        /**
         * Begin: Tìm kiếm khách hàng
         */
        JPanel pnlTimKiemKH = new JPanel();
        pnlTimKiemKH.setOpaque(false);
        pnlTimKiemKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Tìm kiếm", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16),  new Color(4, 72, 210)));
        pnlTimKiemKH.setLayout(new MigLayout("", "[center][center]", ""));//[]10[]10[]10[]10[]20[]
        pnlTop.add(pnlTimKiemKH, "w 35%, h 240!");
      
        /*Ô nhập thông tin tìm kiếm*/
        MyTextField txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        txtTimKiem.setBorderLine(true);
        pnlTimKiemKH.add(txtTimKiem, "w 50%, h 36!");
       
        /*Chọn thông thêm tiêu chí để lọc thông tin*/
        MyComboBox<String> cmbTimKiem = new MyComboBox<>(new String[] {"--Chọn--", "Tên khách hàng", "Số điện thoại", "Địa chỉ", "Giới tính"});
        cmbTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTimKiem.setBorderLine(true);
        cmbTimKiem.setBorderRadius(10);
        pnlTimKiemKH.add(cmbTimKiem, "w 40%,h 36!, wrap");
        
        /*Click vào button để tìm kiếm*/
        Button btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        btnTimKiem.setBackground(colorBtn);
        pnlTimKiemKH.add(btnTimKiem,"pos 0.9al 0.75al n n,w 100!, h 36!");//pos 0.9al 0.75al n n
        
        pnlBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2), "Danh sách Khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16),  new Color(4, 72, 210)));
        pnlBottom.setPreferredSize(new Dimension(1119, 1000));
        
        tblKhachHang.addRow(new Object[]{"", "KH0001", "Nguyễn Thị Lan", "Nữ","077025222155", "0585534785", "Vũng Tàu" });
        tblKhachHang.addRow(new Object[]{"", "KH0002", "Hoàng Văn Nam", "Nam","077256461235", "0358188554", "Kiên Giang" });
        tblKhachHang.addRow(new Object[]{"", "KH0003", "Nguyễn Thị Lan", "Nữ","077795222555", "0585487785", "Vũng Tàu" });
        tblKhachHang.addRow(new Object[]{"", "KH0004", "Hoàng Văn Nam", "Nam","077256255235", "0350152224", "Kiên Giang" });
        tblKhachHang.addRow(new Object[]{"", "KH0005", "Nguyễn Thị Lan", "Nữ","077250581152", "0501155530", "Vũng Tàu" });
        tblKhachHang.addRow(new Object[]{"", "KH0006", "Hoàng Văn Nam", "Nam","077255885555", "0758152954", "Kiên Giang" });
        tblKhachHang.addRow(new Object[]{"", "KH0007", "Nguyễn Thị Lan", "Nữ","077252441533", "0356221225", "Vũng Tàu" });
        tblKhachHang.addRow(new Object[]{"", "KH0008", "Hoàng Văn Nam", "Nam","077253356815", "0350884664", "Kiên Giang" });
        tblKhachHang.addRow(new Object[]{"", "KH0009", "Nguyễn Thị Lan", "Nữ","077255698711", "0580552385", "Vũng Tàu" });
        tblKhachHang.addRow(new Object[]{"", "KH0010", "Hoàng Văn Nam", "Nam","077255563489", "0350552254", "Kiên Giang" });
        tblKhachHang.addRow(new Object[]{"", "KH0011", "Nguyễn Thị Lan", "Nữ","077257896574", "0580554385", "Vũng Tàu" });
         
          
          setOpaque(false);
        setPreferredSize(new Dimension(getWidth(), 1300));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        scrKhachHang = new javax.swing.JScrollPane();
        tblKhachHang = new gui.swing.table2.MyTable();

        lblTitle.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(4, 72, 210));
        lblTitle.setText("Quản Lý Khách Hàng");

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);
        pnlTop.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setShadowType(gui.dropshadow.ShadowType.TOP);

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã khách hàng", "Tên khách hàng", "Giới tính", "Căn cước công dân", "Số điện thoại", "Địa chỉ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrKhachHang.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(4).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(5).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(6).setResizable(false);
        }

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrKhachHang)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(0, 956, Short.MAX_VALUE))
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane scrKhachHang;
    private gui.swing.table2.MyTable tblKhachHang;
    // End of variables declaration//GEN-END:variables
}
