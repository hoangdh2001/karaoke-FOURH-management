/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dao.DiaChi_DAO;
import dao.NhaCungCapVaNhapHang_DAO;
import entity.DiaChi;
import entity.NhaCungCap;
import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hao
 */
public class GD_ThemNhaCungCap extends javax.swing.JDialog{
    
    private static Object model;
    private MyComboBox<String> cmbXaPhuong, cmbQuan_Huyen, cmbTinh;
    private MyTextField txtTenNCC;
    private MyTextField txtSDT;
    private MyTextField txtSoNha_Duong;
    
    private Button btnThemVaSua;
    private Button btnHuy;
    private DiaChi_DAO diaChi_Dao;
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
        
        addAction();
    }

    public void buildGD(){
        this.setTitle("Thêm nhà cung cấp");
        
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlMain.setLayout(new MigLayout("", "10[center][center] 10 ", ""));
        pnlLeft.setLayout(new MigLayout("", "[center]5[center]", "20[center]15[center]15[center]20[center]20"));
        pnlRight.setLayout(new MigLayout("", "[center]5[center]", "20[center]15[center]15[center]20[center]20"));
        pnlMain.add(pnlLeft, " w 380!, h 100%");
        pnlMain.add(pnlRight, " w 380!,h 100%");
        
        //Tên nhà cung cấp
        JLabel lblTenNCC = new JLabel("Tên nhà cung cấp:");
        lblTenNCC.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(lblTenNCC, "align right");
        
        txtTenNCC = new MyTextField();
        txtTenNCC.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenNCC.setBorderLine(true);
        pnlLeft.add(txtTenNCC, "w 90%, h 36!, wrap");
        
         //Số điện thoại
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(lblSDT, "align right");
        
        txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        txtSDT.setBorderLine(true);
        pnlLeft.add(txtSDT, "w 90%, h 36!, wrap");
        
        //Số nhà số đường
        JLabel lblSoNha_Duong = new JLabel("Số nhà-số đường:");
        lblSoNha_Duong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(lblSoNha_Duong, "align right");
        
        txtSoNha_Duong = new MyTextField();
        txtSoNha_Duong.setFont(new Font(fontName, fontStyle, fontSize));
        txtSoNha_Duong.setBorderLine(true);
        pnlLeft.add(txtSoNha_Duong, "w 90%, h 36!, wrap");
        
        //Tỉnh thành
        JLabel lblTinh = new JLabel("Tỉnh/Thành:");
        lblTinh.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(lblTinh, "align right");
        
        cmbTinh = new MyComboBox<String>();
        cmbTinh.addItem("--chọn--");
        cmbTinh.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTinh.setBorderLine(true);
        cmbTinh.setBorderRadius(10);
        pnlRight.add(cmbTinh, "w 250!,h 36!, wrap");
        
        //Quận huyện
        JLabel lblQuan_Huyen = new JLabel("Quận/Huyện:");
        lblQuan_Huyen.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(lblQuan_Huyen, "align right");
        
        cmbQuan_Huyen = new MyComboBox<String>();
        cmbQuan_Huyen.addItem("--chọn--");
        cmbQuan_Huyen.setFont(new Font(fontName, fontStyle, fontSize));
        cmbQuan_Huyen.setBorderLine(true);
        cmbQuan_Huyen.setBorderRadius(10);
        pnlRight.add(cmbQuan_Huyen, "w 250!,h 36!, wrap");
        
        //Xã phường
        JLabel lblXaPhuong = new JLabel("Xã/Phường:");
        lblXaPhuong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(lblXaPhuong, "align right");
        
        cmbXaPhuong = new MyComboBox<String>();
        cmbXaPhuong.addItem("--Chọn--");
        cmbXaPhuong.setFont(new Font(fontName, fontStyle, fontSize));
        cmbXaPhuong.setBorderLine(true);
        cmbXaPhuong.setBorderRadius(10);
        pnlRight.add(cmbXaPhuong, "w 250!, h 36!, wrap");
        
        //Nút hủy
        btnHuy = new Button("Hủy");
        btnHuy.setFont(new Font(fontName, fontStyle, fontSize));
        btnHuy.setBackground(colorBtn);
        pnlRight.add(btnHuy, "w 100!, h 40!");

        // Nút Thêm
        btnThemVaSua = new Button("Thêm");
        btnThemVaSua.setFont(new Font(fontName, fontStyle, fontSize));
        btnThemVaSua.setBackground(colorBtn);
        pnlRight.add(btnThemVaSua, "w 100!, h 40!, align right");
        
        setSize(800, 320);
        setResizable(false);
        setSize(new Dimension(1200,780));
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
        
        
        initDao();
        initModel();
        
    }
    
    public void loadTinhThanh(){
        List<String> dsTinhThanh = diaChi_Dao.getDSTinhThanh();
        dsTinhThanh.forEach((p)->{
            cmbTinh.addItem(p);
        });
    }
    
    public void loadQuanHuyen(){
        List<String> dsQuanHuyen = diaChi_Dao.getDSQuanHuyen();
        dsQuanHuyen.forEach((p)->{
            cmbQuan_Huyen.addItem(p);
            
        });
    }
    public void loadQuanHuyenByTinh(String tinh){
        List<String> dsQuanHuyen = diaChi_Dao.getDSQuanHuyenByTinh(tinh);
        dsQuanHuyen.forEach((p)->{
            cmbQuan_Huyen.addItem(p);
            
        });
    }
    public void loadXaPhuong(){
        List<String> dsXaPhuong = diaChi_Dao.getDSXaPhuong();
        dsXaPhuong.forEach((p)->{
            cmbXaPhuong.addItem(p);
            
        });
    }
    
    public void loadXaPhuongByTinhQuan(String tinh,String quanHuyen){
        List<String> dsXaPhuong = diaChi_Dao.getDSXaPhuongByQuanHuyen(tinh, quanHuyen);
        dsXaPhuong.forEach((p)->{
            cmbXaPhuong.addItem(p);
            
        });
    }
    
    private void initModel() {
        if(model != null){
            btnThemVaSua.setText("Sửa");
            NhaCungCap ncc = (NhaCungCap) model;
            txtSDT.setText(ncc.getSoDienThoai());
            txtSoNha_Duong.setText(ncc.getDiaChi().getSoNha()+" - "+ncc.getDiaChi().getTenDuong());
            txtTenNCC.setText(ncc.getTenNCC());
            
            cmbTinh.setSelectedItem(ncc.getDiaChi().getTinhThanh());
            cmbQuan_Huyen.setSelectedItem(ncc.getDiaChi().getQuanHuyen());
            cmbXaPhuong.setSelectedItem(ncc.getDiaChi().getXaPhuong());
        }
    }
    
    public void initDao(){
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
        diaChi_Dao = new DiaChi_DAO();
        if(model == null){
            loadTinhThanh();
            loadQuanHuyen();
            loadXaPhuong();
        }else {
            NhaCungCap ncc = (NhaCungCap) model;
            loadTinhThanh();
            cmbTinh.setSelectedItem(ncc.getDiaChi().getTinhThanh());
            loadQuanHuyenByTinh(cmbTinh.getSelectedItem().toString());
            cmbQuan_Huyen.setSelectedItem(ncc.getDiaChi().getQuanHuyen());
            loadXaPhuongByTinhQuan(cmbTinh.getSelectedItem().toString(), cmbQuan_Huyen.getSelectedItem().toString());
            cmbXaPhuong.setSelectedItem(ncc.getDiaChi().getXaPhuong());
        }
    }
    
    public void resetQuanHuyen(){
        cmbQuan_Huyen.removeAllItems();
        cmbQuan_Huyen.addItem("--chọn--");
        loadQuanHuyenByTinh(cmbTinh.getSelectedItem().toString());
        cmbXaPhuong.removeAllItems();
        cmbXaPhuong.addItem("--chọn--");
    }
    public void resetxaPhuong(){
        cmbXaPhuong.removeAllItems();
        cmbXaPhuong.addItem("--chọn--");
        if(cmbQuan_Huyen.getSelectedIndex() > 0){
            loadXaPhuongByTinhQuan(cmbTinh.getSelectedItem().toString(), cmbQuan_Huyen.getSelectedItem().toString());
        }
    }
    
    private boolean valiDataNull(){
        String Sdt = txtSDT.getText().trim();
        String tenNCC = txtTenNCC.getText().trim();
        String soDuong = txtSoNha_Duong.getText().trim();
        
        if(tenNCC.equals("")){
            showMsg("Nhập tên nhà cung cấp");  
            txtTenNCC.requestFocus();
            return false;
        }
        if(Sdt.equals("")){
            showMsg("Nhập số điện thoại liên lạc !");  
            txtSDT.requestFocus();
            return false;
        } 
        if(soDuong.equals("")){
            showMsg("Nhập số nhà - tên đường !");  
            txtSoNha_Duong.requestFocus();
            return false;
        }
        
        if(cmbTinh.getSelectedIndex() == 0){
            showMsg("Chọn tỉnh/thành phố !");  
            return false;
        }
        
        if(cmbQuan_Huyen.getSelectedIndex() == 0){
            showMsg("Chọn quận/huyện !");  
            return false;
        }
        
        if(cmbXaPhuong.getSelectedIndex() == 0){
            showMsg("Chọn xã/phường !");  
            return false;
        }
        
        return true;
    }
    
    private boolean valiDataSDT(){
        String sdt = txtSDT.getText().trim();
//        (028) 38222855
        if (!(sdt.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$"))) {
				showMsg("Số điện thoại của nhà cung cấp không hợp lệ");
				txtSDT.requestFocus();
				txtSDT.selectAll();
				return false;
			}
        return true;
    }
    
    private void showMsg(String msg) {
	JOptionPane.showMessageDialog(null, msg);
    }
    
    private void addAction(){
        btnThemVaSua.addActionListener(new createActionListener());
        cmbTinh.addActionListener(new createActionListener());
        cmbQuan_Huyen.addActionListener(new createActionListener());
        txtSDT.setFocusTraversalKeysEnabled(false);
        txtSDT.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER){
                    valiDataSDT();
                    txtSoNha_Duong.requestFocus();
                }
            }
        });
        
    }
    
    private class createActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if(obj.equals(btnThemVaSua) && valiDataNull()){
                    NhaCungCap nccMoi = new NhaCungCap();
                
                    String tinhThanh = cmbTinh.getSelectedItem().toString();
                    String quanHuyen = cmbQuan_Huyen.getSelectedItem().toString();
                    String xaPhuong = cmbXaPhuong.getSelectedItem().toString();
                    
                    String[] soNhaSoDuong = txtSoNha_Duong.getText().split("-");
                    
                    DiaChi diachi = new DiaChi(soNhaSoDuong[0],soNhaSoDuong[1],xaPhuong,quanHuyen,tinhThanh);
                    
                    nccMoi.setDiaChi(diachi);
                    nccMoi.setSoDienThoai(txtSDT.getText().trim());
                    nccMoi.setTenNCC(txtTenNCC.getText().trim());
                    
                    if(model != null){
                        NhaCungCap nccCu = (NhaCungCap) model;
                        nccMoi.setMaNCC(nccCu.getMaNCC());
                        nhaCungCapVaNhapHang_DAO.updateNhaCungCap(nccMoi);
                        showMsg("Sửa thành công nhà cung cấp: "+ nccMoi.getTenNCC());
                    }else{
                        nhaCungCapVaNhapHang_DAO.insertNhaCungCap(nccMoi);
                        showMsg("Thêm thành công nhà cung cấp: "+ nccMoi.getTenNCC());
                    }
                    
                    dispose();
            }else if(obj.equals(cmbTinh)){
                resetQuanHuyen();
            }else if(obj.equals(cmbQuan_Huyen)){
                resetxaPhuong();
            }else if(obj.equals(btnHuy)){
                dispose();
            }
        }
    
    }
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new gui.swing.panel.PanelShadow();
        pnlLeft = new gui.swing.panel.PanelShadow();
        pnlRight = new gui.swing.panel.PanelShadow();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setShadowOpacity(0.3F);
        pnlMain.setShadowSize(3);
        pnlMain.setShadowType(gui.swing.graphics.ShadowType.TOP);

        pnlLeft.setBackground(new java.awt.Color(255, 255, 255));
        pnlLeft.setShadowColor(new java.awt.Color(255, 255, 255));
        pnlLeft.setShadowOpacity(0.0F);
        pnlLeft.setShadowSize(1);

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 203, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        pnlRight.setBackground(new java.awt.Color(255, 255, 255));
        pnlRight.setShadowColor(new java.awt.Color(255, 255, 255));
        pnlRight.setShadowOpacity(0.0F);
        pnlRight.setShadowSize(1);

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 216, Short.MAX_VALUE)
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlRight, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    private gui.swing.panel.PanelShadow pnlLeft;
    private gui.swing.panel.PanelShadow pnlMain;
    private gui.swing.panel.PanelShadow pnlRight;
    // End of variables declaration//GEN-END:variables

   
        
    }
 

