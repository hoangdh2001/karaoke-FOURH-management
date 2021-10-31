/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.NhaCungCapVaNhapHang_DAO;
import entity.DiaChi;
import entity.NhaCungCap;
import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.View;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hao
 */
public class GD_ThemNhaCungCap extends javax.swing.JDialog {
    
    private static Object model;
    
    private MyTextField txtTenNCC;
    private MyTextField txtSDT;
    private MyTextField txtSoNha_Duong;
    
    private Button btnThemVaSua;
    
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhapHang_DAO;
    /**
     * Creates new form NewJDialog
     */
    public GD_ThemNhaCungCap(Object model) {
        super();
        this.model = model;
        initComponents();
        setModal(true);
        buildGD();
        initModel();
        initDao();
        addAction();
    }

    public void buildGD(){
        this.setTitle("Thêm nhà cung cấp");
        
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);
        
        //pnlMain.setPreferredSize(new Dimension(800, 300));
        pnlMain.setLayout(new MigLayout("", "20[center]5[center] 30 [center]5[center]20", "30[center]25 [center] 25 [center]30[center]20"));
        
        //Tên nhà cung cấp
        JLabel lblTenNCC = new JLabel("Tên nhà cung cấp:");
        lblTenNCC.setFont(new Font(fontName, fontStyle, fontSize));
        pnlMain.add(lblTenNCC, "align right");
        
        txtTenNCC = new MyTextField();
        txtTenNCC.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenNCC.setBorderLine(true);
        pnlMain.add(txtTenNCC, "w 70%, h 36!");
        
        //Xã phường
        JLabel lblXaPhuong = new JLabel("Xã/Phường:");
        lblXaPhuong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlMain.add(lblXaPhuong, "align right");
        
        MyComboBox<String> cmbXaPhuong = new MyComboBox<>(new String[] {"--Chọn--"});
        cmbXaPhuong.setFont(new Font(fontName, fontStyle, fontSize));
        cmbXaPhuong.setBorderLine(true);
        cmbXaPhuong.setBorderRadius(10);
        pnlMain.add(cmbXaPhuong, "w 70%,h 36!, wrap");
        
        
        
        //Số điện thoại
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlMain.add(lblSDT, "align right");
        
        txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        txtSDT.setBorderLine(true);
        pnlMain.add(txtSDT, "w 70%, h 36!");
        
        //Quận huyện
        JLabel lblQuan_Huyen = new JLabel("Quận/Huyện:");
        lblQuan_Huyen.setFont(new Font(fontName, fontStyle, fontSize));
        pnlMain.add(lblQuan_Huyen, "align right");
        
        MyComboBox<String> cmbQuan_Huyen = new MyComboBox<>(new String[] {"--Chọn--"});
        cmbQuan_Huyen.setFont(new Font(fontName, fontStyle, fontSize));
        cmbQuan_Huyen.setBorderLine(true);
        cmbQuan_Huyen.setBorderRadius(10);
        pnlMain.add(cmbQuan_Huyen, "w 70%,h 36!, wrap");
        
        
        //Số nhà số đường
        JLabel lblSoNha_Duong = new JLabel("Số nhà - Số đường:");
        lblSoNha_Duong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlMain.add(lblSoNha_Duong, "align right");
        
        txtSoNha_Duong = new MyTextField();
        txtSoNha_Duong.setFont(new Font(fontName, fontStyle, fontSize));
        txtSoNha_Duong.setBorderLine(true);
        pnlMain.add(txtSoNha_Duong, "w 70%, h 36!");
        
        //Tỉnh thành
        JLabel lblTinh = new JLabel("Tỉnh/Thành:");
        lblTinh.setFont(new Font(fontName, fontStyle, fontSize));
        pnlMain.add(lblTinh, "align right");
        
        MyComboBox<String> cmbTinh = new MyComboBox<>(new String[] {"--Chọn--"});
        cmbTinh.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTinh.setBorderLine(true);
        cmbTinh.setBorderRadius(10);
        pnlMain.add(cmbTinh, "w 70%,h 36!, wrap");
        
        
        //Nút hủy
        Button btnHuy = new Button("Hủy");
        btnHuy.setFont(new Font(fontName, fontStyle, fontSize));
        btnHuy.setBackground(colorBtn);
        pnlMain.add(btnHuy, "w 100!, h 36!,pos 0.8al 0.95al n n");

        // Nút Thêm
        btnThemVaSua = new Button("Thêm");
        btnThemVaSua.setFont(new Font(fontName, fontStyle, fontSize));
        btnThemVaSua.setBackground(colorBtn);
        pnlMain.add(btnThemVaSua, "w 100!, h 36!,pos 0.98al 0.95al n n");
        
        
        setSize(800, 320);
        setResizable(false);
        setLocationRelativeTo(null);
       
    }
    
    private void initModel() {
        if(model != null){
            btnThemVaSua.setText("Sửa");
            
            NhaCungCap ncc = (NhaCungCap) model;
            txtSDT.setText(ncc.getSoDienThoai());
            txtSoNha_Duong.setText(ncc.getDiaChi().getSoNha()+" - "+ncc.getDiaChi().getTenDuong());
            txtTenNCC.setText(ncc.getTenNCC());
        }
    }
    
    private void addAction(){
        if(model != null){
//          sua
            btnThemVaSua.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {   
                    NhaCungCap ncc = (NhaCungCap) model;
                    
                    String[] soNhaSoDuong = txtSoNha_Duong.getText().split("-");
                    
                    DiaChi diachi = new DiaChi("31/9A","Duong so 7","Hiep Binh Chanh","Thu Duc","TP Ho Chi Minh");
                    diachi.setSoNha(soNhaSoDuong[0]);
                    diachi.setTenDuong(soNhaSoDuong[1]);
                    ncc.setDiaChi(diachi);
                    
                    ncc.setSoDienThoai(txtSDT.getText());
                    
                    ncc.setTenNCC(txtTenNCC.getText());
                    
                    nhaCungCapVaNhapHang_DAO.updateNhaCungCap(ncc);
                }
            });
        }else{
//          them
            btnThemVaSua.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
//                    System.out.println("them");
//                    System.err.println(nhaCungCapVaNhapHang_DAO.getlastNhaCungCap());
                }
            });
        }
    }
    
    public void initDao(){
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new gui.swing.panel.PanelShadow();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setShadowOpacity(0.3F);
        pnlMain.setShadowSize(3);
        pnlMain.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(GD_ThemNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GD_ThemNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GD_ThemNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GD_ThemNhaCungCap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GD_ThemNhaCungCap dialog = new GD_ThemNhaCungCap(model);
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
    private gui.swing.panel.PanelShadow pnlMain;
    // End of variables declaration//GEN-END:variables
 
}
