/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui.dialog;

import gui.swing.button.Button;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hao
 */
public class DL_DatPhong extends javax.swing.JDialog {

    /**
     * Creates new form NewJDialog
     */
    public DL_DatPhong(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        buildGD();
    }
    
     public void buildGD(){
        
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setPreferredSize(new Dimension(1119, 550));
        pnlTop.setLayout(new MigLayout());
        
        /**
         * End: group lưa chọn phòng hát
         */
        JPanel pnlChonPhong = new JPanel();
        pnlChonPhong.setOpaque(false);
        pnlChonPhong.setLayout(new MigLayout());
        pnlTop.add(pnlChonPhong, "w 100%,h 10%, wrap");
        
        //Ngày giờ đặt
        JLabel lblNgayGio = new JLabel("Ngày giờ đặt:");
        lblNgayGio.setFont(new Font(fontName, fontStyle, fontSize));
        pnlChonPhong.add(lblNgayGio, "align right");
        
        JTextField txtNgayGio = new MyTextField();
        txtNgayGio.setFont(new Font(fontName, fontStyle, fontSize));
        pnlChonPhong.add(txtNgayGio, "w 40%, h 36!");
        
        //Loại phòng hát
        JLabel lblLoaiPhong = new JLabel("Loại phòng:");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlChonPhong.add(lblLoaiPhong, "align right");
        
        JComboBox<String> cmbLoaiPhong = new JComboBox<>();
        cmbLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        cmbLoaiPhong.addItem("Thường nhỏ");
        pnlChonPhong.add(cmbLoaiPhong, "w 40%, h 36!");
        
        //Bảng thông tin phòng đặt
        JPanel pnlBang = new JPanel();
        pnlBang.setOpaque(false);
        pnlBang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Bảng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlBang.setLayout(new MigLayout());
        pnlTop.add(pnlBang, "w 100%, h 90%");
        /**
         * End: group chọn phòng đặt
         */
        
       
        pnlBottom.setPreferredSize(new Dimension(1119, 350));
        pnlBottom.setLayout(new MigLayout("", "3[center] 20 [center]3", "6[center]5"));
        
        /**
         * Begin: group thông tin khách hàng
         */
        JPanel pnlThongTinKH = new JPanel();
        pnlThongTinKH.setOpaque(false);
        pnlThongTinKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlThongTinKH.setLayout(new MigLayout("", "10[center]10[center] 10 [center][center]10", "[center]10[center]10[center]10[center]10[center] 20[center]"));
        pnlBottom.add(pnlThongTinKH, "w 50%, h 280!");
        
        //Số điện thoại của khách hàng
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblSDT, "align right");
        
        JTextField txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtSDT, "w 80%, h 36!, wrap");
        
        //Tên khách hàng
        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblTenKH, "align right");
        
        JTextField txtTenKH = new MyTextField();
        txtTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtTenKH, "w 80%, h 36!, wrap");
        
        //Căn cước công dân
        JLabel lblCCCD = new JLabel("Căn cước công dân:");
        lblCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblCCCD, "align right");
        
        JTextField txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtCCCD, "w 80%, h 36!, wrap");
        
        //Địa chỉ của khách hàng
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblDiaChi, "align right");
        
        JTextField txtDiaChi = new MyTextField();
        txtDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtDiaChi, "w 80%, h 36!, wrap");
        /**
         * End: Group thông tin khách hàng
         */
        
        
        /**
         * Begin: thông tin Phòng đặt
         */
        JPanel pnlThongTinPD = new JPanel();
        pnlThongTinPD.setOpaque(false);
        pnlThongTinPD.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Thông tin phòng đặt", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlThongTinPD.setLayout(new MigLayout("", "10[center]10[center] 10 [center][center]10", "[center]10[center]10[center]10[center]10[center] 20[center]"));
        pnlBottom.add(pnlThongTinPD, "w 50%, h 280!, wrap");
        
        //Tên phòng
        JLabel lbltenPhong = new JLabel("Tên phòng:");
        lbltenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lbltenPhong, "align right");
        
        JTextField txttenPhong = new MyTextField();
        txttenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        txttenPhong.setEnabled(false);
        txttenPhong.setBackground(new Color(245,245,245));
        pnlThongTinPD.add(txttenPhong, "w 80%, h 36!, wrap");
        
        //Loại phòng
        JLabel lblLoaiPhongTT = new JLabel("Loại phòng: ");
        lblLoaiPhongTT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblLoaiPhongTT, "align right");
        
        JTextField txtLoaiPhongTT = new MyTextField();
        txtLoaiPhongTT.setFont(new Font(fontName, fontStyle, fontSize));
        txtLoaiPhongTT.setEnabled(false);
        txtLoaiPhongTT.setBackground(new Color(245,245,245));
        pnlThongTinPD.add(txtLoaiPhongTT, "w 80%, h 36!, wrap");
        
        //Giá phòng
        JLabel lblGia = new JLabel("Giá:");
        lblGia.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblGia, "align right");
        
        JTextField txtGia = new MyTextField();
        txtGia.setFont(new Font(fontName, fontStyle, fontSize));
        txtGia.setEnabled(false);
        txtGia.setBackground(new Color(245,245,245));
        pnlThongTinPD.add(txtGia, "w 80%, h 36!, wrap");
        
        //Số giờ dự tính sử dụng của khách hàng
        JLabel lblGioSuDungDuTinh = new JLabel("Giờ sử dụng dự tính:");
        lblGioSuDungDuTinh.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblGioSuDungDuTinh, "align right");
        
        JTextField txtGioSuDungDuTinh = new MyTextField();
        txtGioSuDungDuTinh.setFont(new Font(fontName, fontStyle, fontSize));
        txtGioSuDungDuTinh.setEnabled(false);
        txtGioSuDungDuTinh.setBackground(new Color(245,245,245));
        pnlThongTinPD.add(txtGioSuDungDuTinh, "w 80%, h 36!, wrap");
        /**
         * end: group thông tin phòng đặt
         */
        
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]20[]20[]20[]0", "push[]push"));
        pnlBottom.add(pnlButton, "span , w 100%, h 36!");

        // Nút Hủy
        Button btnHuy = new Button("Hủy");
        btnHuy.setFont(new Font(fontName, fontStyle, fontSize));
        btnHuy.setBackground(colorBtn);
        pnlButton.add(btnHuy, "w 100!, h 36!,growx");

        // Nút Đặt phòng
        Button btnDat = new Button("Đặt Phòng");
        btnDat.setFont(new Font(fontName, fontStyle, fontSize));
        btnDat.setBackground(colorBtn);
        pnlButton.add(btnDat, "w 100!, h 36!");
        
        setPreferredSize(new Dimension(1119, 950));
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
            .addGap(0, 217, Short.MAX_VALUE)
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
            .addGap(0, 340, Short.MAX_VALUE)
        );

        lblTitle.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(4, 72, 210));
        lblTitle.setText("Cửa Sổ Đặt Phòng");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 966, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                DL_DatPhong dialog = new DL_DatPhong(new javax.swing.JFrame(), true);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private gui.panel.PanelShadow pnlBottom;
    private gui.panel.PanelShadow pnlTop;
    // End of variables declaration//GEN-END:variables
}
