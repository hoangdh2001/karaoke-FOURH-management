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
public class GD_DanhSachPhong extends JPanel {

    /**
     * Creates new form GD_DanhSachPhong
     */
     public GD_DanhSachPhong(){
         initComponents();
         buildGD();
         tblPhong.fixTable(scrPhong);
    }

    private void buildGD() {
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setPreferredSize(new Dimension(1119, 200));
        pnlTop.setLayout(new MigLayout());
        
        /**
         * Begin: group thông tin phòng
         */
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setOpaque(false);
        pnlThongTin.setLayout(new MigLayout("fill", "push[center]10[center]20[center]10[]push", "push[center]20[center]20[]push"));
        pnlTop.add(pnlThongTin,"w 100%,h 70%, wrap");
        
        //Mã phòng
        JLabel lblMaPhong = new JLabel("Mã phòng:");
        lblMaPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblMaPhong, "align right");
        
        
        MyTextField txtMaPhong = new MyTextField();
        txtMaPhong.setFont(new Font(fontName, fontStyle, fontSize));
        txtMaPhong.setBorderLine(true);
        pnlThongTin.add(txtMaPhong, "w 30%, h 36!");
        
        //Loại phòng
        JLabel lblLoaiPhong = new JLabel("Loại phòng:");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblLoaiPhong, "align right");
        
        MyComboBox<String> cmbLoaiPhong= new MyComboBox<>(new String[] {"--Loại phòng--", "Phòng thường", "Phòng tiệc", "Phòng vip"});
        cmbLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        cmbLoaiPhong.setBorderLine(true);
        cmbLoaiPhong.setBorderRadius(10);
        pnlThongTin.add(cmbLoaiPhong, "w 30%, h 36!, wrap");
        
        //Tên phòng
        JLabel lblTenPhong = new JLabel("Tên phòng:");
        lblTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblTenPhong, "align right");
        
        MyTextField txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenPhong.setBorderLine(true);
        pnlThongTin.add(txtTenPhong, "w 30%, h 36!");
        
        //   Panel nút chức năng
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout());
        pnlTop.add(pnlButton, "align right,w 50%, h 30%");

        // Nút Thêm
        Button btnThemPhong = new Button("Thêm");
        btnThemPhong .setFont(new Font(fontName, fontStyle, fontSize));
        btnThemPhong .setBackground(colorBtn);
        pnlButton.add(btnThemPhong , "w 100!, h 36!, gap 0 20px");//, growx

        // Nút Xóa
        Button btnXoaPhong  = new Button("Xóa");
        btnXoaPhong .setFont(new Font(fontName, fontStyle, fontSize));
        btnXoaPhong .setBackground(colorBtn);
        pnlButton.add(btnXoaPhong , "w 100!, h 36!, gap 0 20px");

        // Nút Sửa
        Button btnSuaPhong  = new Button("Sửa");
        btnSuaPhong .setFont(new Font(fontName, fontStyle, fontSize));
        btnSuaPhong .setBackground(colorBtn);
        pnlButton.add(btnSuaPhong , "w 100!, h 36!, gap 0 20px");

        // Nút Làm mới
        Button btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontStyle, fontSize));
        btnLamMoi.setBackground(colorBtn);
        pnlButton.add(btnLamMoi, "w 100!, h 36!");
        /**
         * end: group thông tin phòng hát
         */
         /*Begin: group danh sách Phòng hát*/
        //pnlBottom.setLayout(new MigLayout());
        pnlBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2), "Danh sách phòng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18),  new Color(4, 72, 210)));
        pnlBottom.setPreferredSize(new Dimension(1119, 1110));
        /*End: group danh sách Phòng */
        

        tblPhong.addRow(new Object[]{"", "PH0001", "Phòng Vip", "Sẵn sàng","Phòng Vip", "500,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0002", "Phòng Thường", "Đang hát","Phòng Thường", "300,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0003", "Phòng Tiệc", "Đang sửa chữa","Phòng Tiệc", "400,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0004", "Phòng Thường", "Phòng đang dọn","Phòng Thường", "200,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0005", "Phòng Vip", "Sẵn sàng","Phòng Vip", "500,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0006", "Phòng Thường", "Đang hát","Phòng Thường", "300,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0007", "Phòng Tiệc", "Đang sửa chữa","Phòng Tiệc", "400,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0008", "Phòng Thường", "Phòng đang dọn","Phòng Thường", "200,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0009", "Phòng Thường", "Đang hát","Phòng Thường", "300,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0010", "Phòng Tiệc", "Đang sửa chữa", "Phòng Tiệc", "400,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0011", "Phòng Thường", "Phòng đang dọn","Phòng Thường", "200,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0012", "Phòng Vip", "Sẵn sàng","Phòng Vip", "500,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0013", "Phòng Thường", "Đang hát","Phòng Thường", "300,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0014", "Phòng Tiệc", "Đang sửa chữa","Phòng Tiệc", "400,000" }); 
        tblPhong.addRow(new Object[]{"", "PH0015", "Phòng Thường", "Phòng đang dọn","Phòng Thường", "200,000" }); 
           
        setOpaque(false);
        setPreferredSize(new Dimension(1119, 1500));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        scrPhong = new javax.swing.JScrollPane();
        tblPhong = new gui.swing.table2.MyTable();
        lblTitle = new javax.swing.JLabel();

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
            .addGap(0, 214, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setShadowType(gui.dropshadow.ShadowType.TOP);

        tblPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã phòng", "Tên phòng", "Trạng thái", "Loại phòng", "Giá phòng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrPhong.setViewportView(tblPhong);
        if (tblPhong.getColumnModel().getColumnCount() > 0) {
            tblPhong.getColumnModel().getColumn(0).setResizable(false);
            tblPhong.getColumnModel().getColumn(1).setResizable(false);
            tblPhong.getColumnModel().getColumn(2).setResizable(false);
            tblPhong.getColumnModel().getColumn(3).setResizable(false);
            tblPhong.getColumnModel().getColumn(4).setResizable(false);
            tblPhong.getColumnModel().getColumn(5).setResizable(false);
        }
        tblPhong.getAccessibleContext().setAccessibleParent(pnlBottom);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrPhong, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 485, Short.MAX_VALUE)
        );

        lblTitle.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(4, 72, 210));
        lblTitle.setText("Danh Sách Phòng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(0, 827, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane scrPhong;
    private gui.swing.table2.MyTable tblPhong;
    // End of variables declaration//GEN-END:variables
}
