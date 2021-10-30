/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialog;

import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hao
 */
public class DL_DatPhong extends javax.swing.JDialog {

    /**
     * Creates new form DL_DatPhong
     */
    public DL_DatPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildGD();
        tblPhieu.fixTable(scrBang);
    }
    
    public void buildGD(){
         String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 14;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setPreferredSize(new Dimension(1200, 500));
        pnlTop.setLayout(new MigLayout());
        pnlTop.add(pnlLoc, " w 60%, h 10%, wrap");
        pnlTop.add(pnlBang, " w 100%, h 90%");
        
        /**
         * End: group lưa chọn phòng hát
//         */
//        JPanel pnlChonPhong = new JPanel();
          pnlLoc.setOpaque(false);
          pnlLoc.setLayout(new MigLayout("","[center][center]30[center][center]",""));
//        pnlLoc.add(pnlChonPhong, "w 100%,h 10%, wrap");
        
        //Ngày giờ đặt
        JLabel lblNgayGio = new JLabel("Ngày giờ đặt:");
        lblNgayGio.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLoc.add(lblNgayGio, "align right");
        
        MyTextField txtNgayGio = new MyTextField();
        txtNgayGio.setFont(new Font(fontName, fontStyle, fontSize));
        txtNgayGio.setBorderLine(true);
        txtNgayGio.setBorderRadius(5);
        pnlLoc.add(txtNgayGio, "w 40%, h 36!");
        
        
        //Loại phòng hát
        JLabel lblLoaiPhong = new JLabel("Loại phòng:");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLoc.add(lblLoaiPhong, "align right");
        
        MyComboBox<String> cmbLoaiPhong = new MyComboBox<>();
        cmbLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        cmbLoaiPhong.addItem("--Chọn--");
        cmbLoaiPhong.setBorderLine(true);
        cmbLoaiPhong.setBorderRadius(10);
        pnlLoc.add(cmbLoaiPhong, "w 40%,h 30!");
        
        
        //Bảng thông tin phòng đặt
          pnlBang.setOpaque(false);
          pnlBang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Bảng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));
        
        
        //TableCustom tblPhongDat = new TableCustom(modelPhongDat);
        // JScrollPane scrPhongDat = new JScrollPane(tblPhongDat);
        //tblPhongDat.fixTable(scrPhongDat);
        /**
         * End: group chọn phòng đặt
         */


        
       
        pnlBottom.setPreferredSize(new Dimension(1200, 350));
        pnlBottom.setLayout(new MigLayout("", "3[center] 20 [center]3", "10[center]10[center]10"));
        
        /**
         * Begin: group thông tin khách hàng
         */
        JPanel pnlThongTinKH = new JPanel();
        pnlThongTinKH.setOpaque(false);
        pnlThongTinKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));
        pnlThongTinKH.setLayout(new MigLayout("", "[center][center]", "10[center]10[center]10[center]10[center]10"));
        pnlBottom.add(pnlThongTinKH, "w 50%, h 80%");
        
        //Số điện thoại của khách hàng
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblSDT, "align right");
        
        MyTextField txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        txtSDT.setBorderLine(true);
        txtSDT.setBorderRadius(5);
        pnlThongTinKH.add(txtSDT, "w 80%, h 36!, wrap");
        
        //Tên khách hàng
        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblTenKH, "align right");
        
        MyTextField txtTenKH = new MyTextField();
        txtTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenKH.setBorderLine(true);
        txtTenKH.setBorderRadius(5);
        pnlThongTinKH.add(txtTenKH, "w 80%, h 36!, wrap");       

        
        //Căn cước công dân
        JLabel lblCCCD = new JLabel("Căn cước công dân:");
        lblCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblCCCD, "align right");
        
        MyTextField txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        txtCCCD.setBorderLine(true);
        txtCCCD.setBorderRadius(5);
        pnlThongTinKH.add(txtCCCD, "w 80%, h 36!, wrap");  
        
        
        //Địa chỉ của khách hàng
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblDiaChi, "align right");
        
        MyTextField txtDiaChi = new MyTextField();
        txtDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        txtDiaChi.setBorderLine(true);
        txtDiaChi.setBorderRadius(5);
        pnlThongTinKH.add(txtDiaChi, "w 80%, h 36!, wrap");
        
        /**
         * End: Group thông tin khách hàng
         */
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(2, 200));
        pnlBottom.add(spr);
        
        /**
         * Begin: thông tin Phòng đặt
         */
        JPanel pnlThongTinPD = new JPanel();
        pnlThongTinPD.setOpaque(false);
        pnlThongTinPD.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Thông tin phòng đặt", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));
        pnlThongTinPD.setLayout(new MigLayout("", "[center][center]", "10[center]15[center]15[center]15[center]"));
        pnlBottom.add(pnlThongTinPD, "w 50%, h 80%, wrap");
        
        //Tên phòng
        JLabel lblTenPhong = new JLabel("Tên phòng:");
        lblTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblTenPhong, "align right");
        
        MyTextField txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setEnabled(false);
        txtTenPhong.setEditable(false);
        txtTenPhong.setBorderRadius(5);
        pnlThongTinPD.add(txtTenPhong, "w 80%, h 36!, wrap");
       
        
        //Loại phòng
        JLabel lblLoaiPhongTT = new JLabel("Loại phòng: ");
        lblLoaiPhongTT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblLoaiPhongTT, "align right");
        
        MyTextField txtLoaiPhongTT = new MyTextField();
        txtLoaiPhongTT.setFont(new Font(fontName, fontStyle, fontSize));
        txtLoaiPhongTT.setBorderLine(true);
        txtLoaiPhongTT.setEnabled(false);
        txtLoaiPhongTT.setEditable(false);
        txtLoaiPhongTT.setBorderRadius(5);
        pnlThongTinPD.add(txtLoaiPhongTT, "w 80%, h 36!, wrap");
        
        //Giá phòng
        JLabel lblGia = new JLabel("Giá:");
        lblGia.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblGia, "align right");
        
        MyTextField txtGia = new MyTextField();
        txtGia.setFont(new Font(fontName, fontStyle, fontSize));
        txtGia.setBorderLine(true);
        txtGia.setEnabled(false);
        txtGia.setEditable(false);
        txtGia.setBorderRadius(5);
        pnlThongTinPD.add(txtGia, "w 80%, h 36!, wrap");
        
        
        //Số giờ dự tính sử dụng của khách hàng
        JLabel lblGioSuDungDuTinh = new JLabel("Giờ sử dụng dự tính:");
        lblGioSuDungDuTinh.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblGioSuDungDuTinh, "align right");
        
        MyTextField txtGioSuDungDuTinh = new MyTextField();
        txtGioSuDungDuTinh.setFont(new Font(fontName, fontStyle, fontSize));
        txtGioSuDungDuTinh.setBorderLine(true);
        txtGioSuDungDuTinh.setEnabled(false);
        txtGioSuDungDuTinh.setEditable(false);
        txtGioSuDungDuTinh.setBorderRadius(5);
        pnlThongTinPD.add(txtGioSuDungDuTinh, "w 80%, h 36!, wrap");
        
        /**
         * end: group thông tin phòng đặt
         */
        
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        //pnlButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Button", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlButton.setLayout(new MigLayout("", "75%[center]20[center]", "[center]"));
        pnlBottom.add(pnlButton, "span,w 100%, h 20%");

        // Nút Hủy
        Button btnHuy = new Button("Hủy");
        btnHuy.setFont(new Font(fontName, fontStyle, fontSize));
        btnHuy.setBackground(colorBtn);
        btnHuy.setBorderline(true);
        btnHuy.setBorderRadius(5);
        pnlButton.add(btnHuy, "w 100!, h 36!");

        // Nút Đặt phòng
        Button btnDat = new Button("Đặt Phòng");
        btnDat.setFont(new Font(fontName, fontStyle, fontSize));
        btnDat.setBackground(colorBtn);
        btnDat.setBorderline(true);
        btnDat.setBorderRadius(5);
        pnlButton.add(btnDat, "align right, w 100!, h 36!");
        
 
        pnlLarge.setSize(1200, 900);
        setSize(1200, 900);
        setTitle("CỬA SỔ PHÒNG ĐẶT");
        setResizable(false);
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLarge = new gui.swing.panel.PanelShadow();
        pnlTop = new gui.swing.panel.PanelShadow();
        pnlLoc = new gui.swing.panel.PanelShadow();
        pnlBang = new gui.swing.panel.PanelShadow();
        scrBang = new javax.swing.JScrollPane();
        tblPhieu = new gui.swing.table2.MyTable();
        pnlBottom = new gui.swing.panel.PanelShadow();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnlLarge.setBackground(new java.awt.Color(255, 255, 255));
        pnlLarge.setShadowOpacity(0.0F);
        pnlLarge.setShadowSize(1);

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);
        pnlTop.setShadowType(gui.dropshadow.ShadowType.TOP);

        pnlLoc.setBackground(new java.awt.Color(255, 255, 255));
        pnlLoc.setShadowOpacity(0.0F);
        pnlLoc.setShadowSize(3);

        javax.swing.GroupLayout pnlLocLayout = new javax.swing.GroupLayout(pnlLoc);
        pnlLoc.setLayout(pnlLocLayout);
        pnlLocLayout.setHorizontalGroup(
            pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlLocLayout.setVerticalGroup(
            pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 131, Short.MAX_VALUE)
        );

        pnlBang.setBackground(new java.awt.Color(255, 255, 255));
        pnlBang.setShadowOpacity(0.0F);
        pnlBang.setShadowSize(3);

        tblPhieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Tên Phòng", "Loại Phòng", "Giá Phòng/ Giờ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        scrBang.setViewportView(tblPhieu);
        if (tblPhieu.getColumnModel().getColumnCount() > 0) {
            tblPhieu.getColumnModel().getColumn(0).setResizable(false);
            tblPhieu.getColumnModel().getColumn(1).setResizable(false);
            tblPhieu.getColumnModel().getColumn(2).setResizable(false);
            tblPhieu.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout pnlBangLayout = new javax.swing.GroupLayout(pnlBang);
        pnlBang.setLayout(pnlBangLayout);
        pnlBangLayout.setHorizontalGroup(
            pnlBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrBang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 982, Short.MAX_VALUE)
        );
        pnlBangLayout.setVerticalGroup(
            pnlBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBangLayout.createSequentialGroup()
                .addComponent(scrBang, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(pnlLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 982, Short.MAX_VALUE)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlLargeLayout = new javax.swing.GroupLayout(pnlLarge);
        pnlLarge.setLayout(pnlLargeLayout);
        pnlLargeLayout.setHorizontalGroup(
            pnlLargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBottom, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pnlLargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlLargeLayout.setVerticalGroup(
            pnlLargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLargeLayout.createSequentialGroup()
                .addGap(0, 404, Short.MAX_VALUE)
                .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(pnlLargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(pnlLargeLayout.createSequentialGroup()
                    .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 171, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLarge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLarge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DL_DatPhong dialog = new DL_DatPhong(new javax.swing.JFrame(), true);
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow pnlBang;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlLarge;
    private gui.swing.panel.PanelShadow pnlLoc;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane scrBang;
    private gui.swing.table2.MyTable tblPhieu;
    // End of variables declaration//GEN-END:variables

   
}
