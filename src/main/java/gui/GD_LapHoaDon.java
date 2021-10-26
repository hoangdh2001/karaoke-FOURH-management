/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import gui.swing.panel.PanelShadow;
import gui.swing.button.Button;
import gui.swing.table.SpinnerEditor;
import gui.swing.table.TableCustom;
import gui.swing.table.TableCustomCheckBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author 84975
 */
public class GD_LapHoaDon extends javax.swing.JDialog {
    private JPanel pnlInfoTop;
    private JPanel pnlInfoBottom;
    private JPanel pnlThanhToan;
    private JPanel pnlInfoRoom;
    private JPanel pnlDanhSachDichVu;
    private String fontName = "sansserif";
    private int fontStyle = Font.PLAIN;
    private int fontSize = 16;
    Color colorBtn = new Color(184, 238, 241);
    
    public GD_LapHoaDon() {
        super();
        setModal(true);
        initComponents();
        setSize(new Dimension(1000,720));
        setLocation(300, 10);
        initForm();
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(GD_LapHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GD_LapHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GD_LapHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GD_LapHoaDon.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GD_LapHoaDon dialog = new GD_LapHoaDon();
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
    
    public void initService(){
        pnlDanhSachDichVu = new JPanel();
        pnlDanhSachDichVu.setOpaque(false);
        pnlDanhSachDichVu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                "Dịch vụ",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("sansserif", Font.PLAIN, 18),
                Color.gray)
        );
        pnlDanhSachDichVu.setLayout(new MigLayout("","[]","10[]10"));
            pnlDanhSachDichVu.setBackground(Color.WHITE); 

        Object dataSelected[][] = { 
                {  "Tran Van Minh",0, "6000","0"}, 
                {  "Phan Van Tai",0, "8000","0"}, 
                {  "Do Cao Hoc",0, "7000","0"},             
        };
        
        String colSelected[] = {"Tên","Số lượng","Giá","Tổng"};
        DefaultTableModel modelSelected = new DefaultTableModel(colSelected,0);
        modelSelected.setDataVector(dataSelected, colSelected);
        TableCustom tableSelected = new TableCustom(modelSelected);
        
        TableColumnModel tcm = tableSelected.getColumnModel();
        TableColumn tc = tcm.getColumn(1);
        tc.setCellEditor(new SpinnerEditor());
        
        JScrollPane spSelected = new JScrollPane(tableSelected);
        tableSelected.fixTable(spSelected);
        
        JPanel tong =  new JPanel();
        tong.setLayout(new MigLayout("","push[]5[]10","push[]"));
        tong.setBackground(Color.WHITE);
        
        JLabel lblTong = new JLabel("Tổng tiền dịch vụ :");
        lblTong.setFont(new Font(fontName, fontStyle, fontSize));
        tong.add(lblTong, "align right");

        JTextField txtTong = new MyTextField();
        txtTong.setFont(new Font(fontName, fontStyle, fontSize));
        txtTong.setEnabled(false);
        tong.add(txtTong, "w 100:200:260, h 36! , wrap");
        
        pnlDanhSachDichVu.add(spSelected,"w 100%,h 90%,wrap");
        pnlDanhSachDichVu.add(tong,"w 100%,h 10%");
        pnlInfoTop.add(pnlDanhSachDichVu,"w 50%,h 100%");
    }
    
    public void initInfoRoom(){
        JPanel pnlInfoRightWrap = new JPanel();
        pnlInfoRightWrap.setLayout(new MigLayout("","[]","10[center]18"));
        pnlInfoRightWrap.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                "Thông tin khách hàng",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("sansserif", Font.PLAIN, 18),
                Color.GRAY)
        );
        pnlInfoRightWrap.setBackground(Color.WHITE);
        
        
        pnlInfoRoom = new JPanel();
        pnlInfoRoom.setLayout(new MigLayout("","28[][]10","10[]5"));
        
        
        JLabel lblNgay= new JLabel("Ngày: ");
        lblNgay.setFont(new Font(fontName, fontStyle, fontSize));
        pnlInfoRoom.add(lblNgay, "align right");

        JTextField txtNgay = new MyTextField();
        txtNgay.setFont(new Font(fontName, fontStyle, fontSize));
        txtNgay.setEnabled(false);
        pnlInfoRoom.add(txtNgay, "w 100%, h 36! , wrap");
        
        JLabel lblTenPhong= new JLabel("Tên phòng :");
        lblTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlInfoRoom.add(lblTenPhong, "align right");

        JTextField txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenPhong.setEnabled(false);
        pnlInfoRoom.add(txtTenPhong, "w 100%, h 36! , wrap");
        
        JLabel lblLoai= new JLabel("Loại phòng :");
        lblLoai.setFont(new Font(fontName, fontStyle, fontSize));
        pnlInfoRoom.add(lblLoai, "align right");

        JTextField txtLoai = new MyTextField();
        txtLoai.setFont(new Font(fontName, fontStyle, fontSize));
        txtLoai.setEnabled(false);
        pnlInfoRoom.add(txtLoai, "w 100%, h 36! , wrap");
        
        JLabel lblGia= new JLabel("Giá phòng/giờ :");
        lblGia.setFont(new Font(fontName, fontStyle, fontSize));
        pnlInfoRoom.add(lblGia, "align right");

        JTextField txtGia = new MyTextField();
        txtGia.setFont(new Font(fontName, fontStyle, fontSize));
        txtGia.setEnabled(false);
        pnlInfoRoom.add(txtGia, "w 100%, h 36! , wrap");
        
        JLabel lblNhanVien= new JLabel("Nhân viên :");
        lblNhanVien.setFont(new Font(fontName, fontStyle, fontSize));
        pnlInfoRoom.add(lblNhanVien, "align right");

        JTextField txtNhanVien = new MyTextField();
        txtNhanVien.setFont(new Font(fontName, fontStyle, fontSize));
        txtNhanVien.setEnabled(false);
        pnlInfoRoom.add(txtNhanVien, "w 100%, h 36! , wrap");
        
        JLabel lblStart = new JLabel("Giờ Bắt đầu :");
        lblStart.setFont(new Font(fontName, fontStyle, fontSize));
        pnlInfoRoom.add(lblStart, "align right");

        JTextField txtStart = new MyTextField();
        txtStart.setFont(new Font(fontName, fontStyle, fontSize));
        txtStart.setEnabled(false);
        pnlInfoRoom.add(txtStart, "w 100%, h 36! , wrap");
        
        JLabel lblEnd = new JLabel("Giờ kết thúc :");
        lblEnd.setFont(new Font(fontName, fontStyle, fontSize));
        pnlInfoRoom.add(lblEnd, "align right");

        JTextField txtEnd = new MyTextField();
        txtEnd.setFont(new Font(fontName, fontStyle, fontSize));
        txtEnd.setEnabled(false);
        pnlInfoRoom.add(txtEnd, "w 100%, h 36! , wrap");
        pnlInfoRoom.setBackground(Color.WHITE);
        
        JPanel tongTienPhong =  new JPanel();
        tongTienPhong.setLayout(new MigLayout("fill,insets 0","push[]5[]15"));
        tongTienPhong.setBackground(Color.WHITE);
        
        JLabel lblTongTien = new JLabel("Tổng tiền phòng :");
        lblTongTien.setFont(new Font(fontName, fontStyle, fontSize));
        tongTienPhong.add(lblTongTien, "align right");

        JTextField txtTongTien = new MyTextField();
        txtTongTien.setFont(new Font(fontName, fontStyle, fontSize));
        txtTongTien.setEnabled(false);
        tongTienPhong.add(txtTongTien, "w 100:200:260, h 36!");
        
        pnlInfoRightWrap.add(pnlInfoRoom,"w 100%,h 90%,wrap");
        pnlInfoRightWrap.add(tongTienPhong,"w 100%,h 10%");
        
        pnlInfoTop.add(pnlInfoRightWrap,"w 50%,h 100%");
        
    }
    
    public void initAction(){
        pnlThanhToan = new JPanel();
        pnlThanhToan.setBackground(Color.WHITE);
        pnlThanhToan.setLayout(new MigLayout("","28[][]","10[]5"));
        
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setLayout(new MigLayout("","[][]20","0[]10"));
        
        JLabel lblTongTien = new JLabel("Tổng tiền :");
        lblTongTien.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblTongTien, "align right");

        JTextField txtTongTien = new MyTextField();
        txtTongTien.setFont(new Font(fontName, fontStyle, fontSize));
        txtTongTien.setEnabled(false);
        pnlThongTin.add(txtTongTien, "w 100:260:350, h 36! , wrap");
        
        JLabel lblTienDua = new JLabel("Tiền khách đưa :");
        lblTienDua.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblTienDua, "align right");

        JTextField txtTienDua = new MyTextField();
        txtTienDua.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(txtTienDua, "w 100:260:350, h 36! , wrap");
        
        JLabel lblTraLai = new JLabel("Tiền trả lại :");
        lblTraLai.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblTraLai, "align right");

        JTextField txtTraLai= new MyTextField();
        txtTraLai.setFont(new Font(fontName, fontStyle, fontSize));
        txtTraLai.setEnabled(false);
        pnlThongTin.add(txtTraLai, "w 100:260:350, h 36! , wrap");
        
        pnlThongTin.setBackground(Color.WHITE);
           
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new MigLayout("","push[]10[]20","push[]20"));
        pnlButton.setBackground(Color.WHITE);
        
        Button btnHuy = new Button("Hủy xem");
        btnHuy.setFont(new Font(fontName, fontStyle, fontSize));
        btnHuy.setBackground(colorBtn);
        
        Button btnThanhToan = new Button("Thanh toán");
        btnThanhToan.setFont(new Font(fontName, fontStyle, fontSize));
        btnThanhToan.setBackground(colorBtn);
        
        pnlButton.add(btnHuy,"h 36!");
        pnlButton.add(btnThanhToan,"h 36!");
        
        pnlInfoBottom.add(pnlThongTin,"w 50%,h 100%");
        pnlInfoBottom.add(pnlButton,"w 50%,h 100%");
        
    }

     public void initForm(){
        mainPanel.setLayout(new MigLayout("","20[center]20"));
//        MainPanel.setBackground(Color.WHITE);
        
        pnlInfoTop = new PanelShadow();
        pnlInfoTop.setLayout(new MigLayout("", "20[center] 20 [center]20", "20[]20"));
        pnlInfoBottom = new PanelShadow();
        pnlInfoBottom.setLayout(new MigLayout("", "20[center][center]20", "20[]"));
        
        initService();
        initAction();
        initInfoRoom();
        
        pnlInfoTop.setBackground(Color.WHITE);
        pnlInfoBottom.setBackground(Color.WHITE);
        
        mainPanel.add(pnlInfoTop,"w 100%,h 70%,wrap");
        mainPanel.add(pnlInfoBottom,"w 100%,h 30%");
    }
     
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
