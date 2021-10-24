/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import gui.swing.button.Button;
import gui.swing.table.TableCustom;
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
        
        JTextField txtMaPhong = new MyTextField();
        txtMaPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(txtMaPhong, "w 30%, h 36!");
        
        //Loại phòng
        JLabel lblLoaiPhong = new JLabel("Loại phòng:");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblLoaiPhong, "align right");
        
        JComboBox<String> cmbLoaiPhong = new JComboBox<>();
        cmbLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        cmbLoaiPhong.addItem("Loại phòng");
        pnlThongTin.add(cmbLoaiPhong, "w 30%, h 36!, wrap");
        
        //Tên phòng
        JLabel lblTenPhong = new JLabel("Tên phòng:");
        lblTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblTenPhong, "align right");
        
        JTextField txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(txtTenPhong, "w 30%, h 36!, wrap");
        
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
        pnlBottom.setLayout(new MigLayout());
        pnlBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2), "Danh sách phòng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlBottom.setPreferredSize(new Dimension(1119, 1110));
        /*End: group danh sách Phòng */
        
        
        Object data[][]={
            { "PH0001", "Phòng Vip", "Sẵn sàng","LPV001", "500,000" },
            { "PH0002", "Phòng Thường", "Đang hát","LPTH001", "300,000" },
            { "PH0003", "Phòng Tiệc", "Đang sửa chữa","LPT001", "400,000" },
            { "PH0004", "Phòng Thường", "Phòng đang dọn","LPTH002", "200,000" },
            { "PH0005", "Phòng Vip", "Sẵn sàng","LPV002", "500,000" },
            { "PH0006", "Phòng Thường", "Đang hát","LPTH003", "300,000" },
            { "PH0007", "Phòng Tiệc", "Đang sửa chữa","LPT002", "400,000" },
            { "PH0008", "Phòng Thường", "Phòng đang dọn","LPTH004", "200,000" },
            { "PH0009", "Phòng Thường", "Đang hát","LPTH005", "300,000" },
            { "PH0010", "Phòng Tiệc", "Đang sửa chữa","LPT003", "400,000" },
            { "PH0011", "Phòng Thường", "Phòng đang dọn","LPTH006", "200,000" },
            { "PH0012", "Phòng Vip", "Sẵn sàng","LPV003", "500,000" },
            { "PH0013", "Phòng Thường", "Đang hát","LPTH007", "300,000" },
            { "PH0014", "Phòng Tiệc", "Đang sửa chữa","LPT004", "400,000" },
            { "PH0015", "Phòng Thường", "Phòng đang dọn","LPTH008", "200,000" },
            { "PH0016", "Phòng Thường", "Đang hát","LPTH009", "300,000" },
            { "PH0017", "Phòng Tiệc", "Đang sửa chữa","LPT005", "400,000" },
            { "PH0018", "Phòng Thường", "Phòng đang dọn","LPTH010", "200,000" },
            { "PH0019", "Phòng Vip", "Sẵn sàng","LPV004", "500,000" },
            { "PH0020", "Phòng Thườngg", "Đang hát","LPTH011", "300,000" },
            { "PH0021", "Phòng Tiệc", "Đang sửa chữa","LPT006", "400,000" },
            { "PH0022", "Phòng Thường", "Phòng đang dọn","LPTH012", "200,000" },
            { "PH0023", "Phòng Thường", "Đang hát","LPTH013", "300,000" },
            { "PH0024", "Phòng Tiệc", "Đang sửa chữa","LPT007", "400,000" },
            { "PH0025", "Phòng Thường", "Phòng đang dọn","LPTH014", "200,000" },
            { "PH0026", "Phòng Vip", "Sẵn sàng","LPV005", "500,000" },
            { "PH0027", "Phòng Thường", "Đang hát","LPTH015", "300,000" },
            { "PH0028", "Phòng Tiệc", "Đang sửa chữa","LPT008", "400,000" },
            { "PH0029", "Phòng Thường", "Phòng đang dọn","LPTH016", "200,000" },
            { "PH0030", "Phòng Thường", "Đang hát","LPTH017", "300,000" },
            { "PH0031", "Phòng Tiệcg", "Đang sửa chữa","LPT009", "400,000" },
            { "PH0032", "Phòng Thường", "Phòng đang dọn","LPTH018", "200,000" },
            { "PH0033", "Phòng Vip", "Sẵn sàng","LPV006", "500,000" },
            { "PH0034", "Phòng Thường", "Đang hát","LPTH019", "300,000" },
            { "PH0035", "Phòng Tiệc", "Đang sửa chữa","LPT010", "400,000" },
            { "PH0036", "Phòng Thường", "Phòng đang dọn","LPTH020", "200,000" },
            { "PH0037", "Phòng Thường", "Phòng đang dọn","LPTH014", "200,000" },
            { "PH0038", "Phòng Vip", "Sẵn sàng","LPV005", "500,000" },
            { "PH0039", "Phòng Thường", "Đang hát","LPTH015", "300,000" },
            { "PH0040", "Phòng Tiệc", "Đang sửa chữa","LPT008", "400,000" },
            { "PH0041", "Phòng Thường", "Phòng đang dọn","LPTH016", "200,000" },
            { "PH0042", "Phòng Thường", "Đang hát","LPTH017", "300,000" },
            { "PH0043", "Phòng Tiệcg", "Đang sửa chữa","LPT009", "400,000" },
            { "PH0044", "Phòng Thường", "Phòng đang dọn","LPTH018", "200,000" },
            { "PH0045", "Phòng Vip", "Sẵn sàng","LPV006", "500,000" },
            { "PH0046", "Phòng Thường", "Đang hát","LPTH019", "300,000" },
            { "PH0047", "Phòng Tiệc", "Đang sửa chữa","LPT010", "400,000" },
            { "PH0048", "Phòng Thường", "Phòng đang dọn","LPTH020", "200,000" }
        };
        
        
         String[] tieuDePhong = { "Mã Phòng", "Tên Phòng", "Trạng Thái","Loại Phòng", "Giá Phòng" };
         DefaultTableModel modelPhong = new DefaultTableModel(tieuDePhong, 0);
        modelPhong.setDataVector(data, tieuDePhong);
        TableCustom tblPhong = new TableCustom(modelPhong);
        JScrollPane scrPhong = new JScrollPane(tblPhong);
        tblPhong.fixTable(scrPhong);
        pnlBottom.add(scrPhong,"w 100%, h 100%");
        
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

        pnlTop = new gui.panel.PanelShadow();
        pnlBottom = new gui.panel.PanelShadow();
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

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 348, Short.MAX_VALUE)
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
                .addGap(0, 1019, Short.MAX_VALUE))
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
    private gui.panel.PanelShadow pnlBottom;
    private gui.panel.PanelShadow pnlTop;
    // End of variables declaration//GEN-END:variables
}
