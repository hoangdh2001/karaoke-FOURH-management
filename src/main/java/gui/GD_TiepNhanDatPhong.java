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
import gui.swing.table.TableCustomRadio;
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
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableColumn;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author 84975
 */
public class GD_TiepNhanDatPhong extends javax.swing.JDialog {

    private JPanel pnlDanhSachPhieu;
    private JPanel pnlDanhSachDichVu;
    private JPanel pnlInfoTop;
    private JPanel pnlInfoBottom;
    private JPanel pnlKhachHang;
    private JPanel pnlThongTinPhong;
    private String fontName = "sansserif";
    private int fontStyle = Font.PLAIN;
    private int fontSize = 16;
    Color colorBtn = new Color(184, 238, 241);
    public GD_TiepNhanDatPhong() {
        super();
        setModal(true);
        initComponents();
        setSize(new Dimension(1350,650));
        setLocation(150, 150);
        initForm();
        setTitle("Giao phòng");
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
            java.util.logging.Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                GD_TiepNhanDatPhong dialog = new GD_TiepNhanDatPhong();
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
    
    public void initSetTheRoom(){
        pnlDanhSachPhieu = new JPanel();
        pnlDanhSachPhieu.setOpaque(false);
        pnlDanhSachPhieu.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                "Phiếu đặt phòng",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("sansserif", Font.PLAIN, 18),
                Color.gray)
        );
        pnlDanhSachPhieu.setLayout(new MigLayout("","[]","10[]10"));
            pnlDanhSachPhieu.setBackground(Color.WHITE);    
//        Object data[][] = { 
//                { "101", "Tran Van Minh", "6000",new JRadioButton() }, 
//                { "102", "Phan Van Tai", "8000",new JRadioButton()  }, 
//                { "101", "Do Cao Hoc", "7000",new JRadioButton() },
//                { "101", "Do Cao Hoc", "7000",new JRadioButton() },
//                { "101", "Do Cao Hoc", "7000",new JRadioButton() },
//                { "101", "Do Cao Hoc", "7000",new JRadioButton() },
//                { "101", "Do Cao Hoc", "7000",new JRadioButton() },
//                { "101", "Do Cao Hoc", "7000",new JRadioButton() },              
//        };
        
        Object data[][] = { 
                { "101","Do Cao Hoc", "7000","7:30am"}, 
                { "101","Do Cao Hoc", "7000","11:30am"}, 
                { "101", "Tran Van Minh", "6000","1:30pm"}, 
                { "102", "Phan Van Tai", "8000","5:30pm"}, 
                { "101","Do Cao Hoc", "7000","9:30pm"},   
                
        };
 
        String col[] = {"Mã phiếu","Khách hàng","Ngày đặt","Giờ"};
        DefaultTableModel model = new DefaultTableModel(col,0);
        model.setDataVector(data, col);
        TableCustom table = new TableCustom(model);
        JScrollPane sp = new JScrollPane(table);
//test
//        table.addEvent(new ActionListener(){
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                System.out.println("1");
//            }  
//        });
//        System.err.println(table.getRowCount());
        table.fixTable(sp);
        
        table.addRow(new Object[]{ "them","Do Cao Hoc", "7000","7:30am"});
        pnlDanhSachPhieu.add(sp,"w 100%,h 100%");
        pnlInfoTop.add(pnlDanhSachPhieu,"w 35%,h 100%");
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
            
        Object data[][] = { 
                { "101", "Tran Van Minh", "6000",new JCheckBox() }, 
                { "102", "Phan Van Tai", "8000",new JCheckBox()  }, 
                { "101", "Do Cao Hoc", "7000",new JCheckBox() },
                { "101", "Do Cao Hoc", "7000",new JCheckBox() },
                { "101", "Do Cao Hoc", "7000",new JCheckBox() },
                { "101", "Do Cao Hoc", "7000",new JCheckBox() },
                { "101", "Do Cao Hoc", "7000",new JCheckBox() },
                { "101", "Do Cao Hoc", "7000",new JCheckBox() },              
        };
        
 
        String col[] = {"Tên","Số lượng","Giá","Chọn"};
        DefaultTableModel model = new DefaultTableModel(col,0);
        model.setDataVector(data, col);
        TableCustomCheckBox table = new TableCustomCheckBox(model);
        JScrollPane sp = new JScrollPane(table);
//test
        table.addEvent(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("1");
            }  
        });
        table.fixTable(sp);
        
        
        Object dataSelected[][] = { 
                {  "Tran Van Minh",0, "6000"}, 
                {  "Phan Van Tai",0, "8000"}, 
                {  "Do Cao Hoc",0, "7000"},             
        };
        
        String colSelected[] = {"Tên","Số lượng","Giá"};
        DefaultTableModel modelSelected = new DefaultTableModel(colSelected,0);
        modelSelected.setDataVector(dataSelected, colSelected);
        TableCustom tableSelected = new TableCustom(modelSelected);
        
        TableColumnModel tcm = tableSelected.getColumnModel();
        TableColumn tc = tcm.getColumn(1);
        tc.setCellEditor(new SpinnerEditor());
        
        JScrollPane spSelected = new JScrollPane(tableSelected);
        tableSelected.fixTable(spSelected);
        
        pnlDanhSachDichVu.add(spSelected,"w 60%,h 100%");
        pnlDanhSachDichVu.add(sp,"w 60%,h 100%");
        pnlInfoTop.add(pnlDanhSachDichVu,"w 65%,h 100%");
    }
    
    public void initCustomer(){
        pnlKhachHang = new JPanel();
        pnlKhachHang.setLayout(new MigLayout("","28[][]20","10[]5"));
        pnlKhachHang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                "Thông tin khách hàng",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("sansserif", Font.PLAIN, 18),
                Color.GRAY)
        );
        
        JLabel lblSdt= new JLabel("Số điện thoại :");
        lblSdt.setFont(new Font(fontName, fontStyle, fontSize));
        pnlKhachHang.add(lblSdt, "align right");

        JTextField txtSdt = new MyTextField();
        txtSdt.setFont(new Font(fontName, fontStyle, fontSize));
        pnlKhachHang.add(txtSdt, "w 100:260:300, h 36! , wrap");
        
        JLabel lblTenKhachHang = new JLabel("Tên khách hàng :");
        lblTenKhachHang.setFont(new Font(fontName, fontStyle, fontSize));
        pnlKhachHang.add(lblTenKhachHang, "align right");

        JTextField txtTenKhachHang = new MyTextField();
        txtTenKhachHang.setFont(new Font(fontName, fontStyle, fontSize));
        pnlKhachHang.add(txtTenKhachHang, "w 100:260:300, h 36! , wrap");
        
        JLabel lblCCCD = new JLabel("Số thẻ căn cước :");
        lblCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlKhachHang.add(lblCCCD, "align right");

        JTextField txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlKhachHang.add(txtCCCD, "w 100:260:300, h 36! , wrap");
        
        pnlInfoBottom.add(pnlKhachHang,"w 35%,h 100%");
        pnlKhachHang.setBackground(Color.WHITE);  

    }
    
    public void initRoom(){
        pnlThongTinPhong = new JPanel();
        pnlThongTinPhong.setBackground(Color.WHITE);
        pnlThongTinPhong.setLayout(new MigLayout("","28[][]","10[]5"));
        
        pnlThongTinPhong.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2),
                "Thông tin phòng",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("sansserif", Font.PLAIN, 18),
                Color.GRAY)
        );
        
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setLayout(new MigLayout("","[][]20","0[]10"));
        
        JLabel lblTenPhong = new JLabel("Tên phòng :");
        lblTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblTenPhong, "align right");

        JTextField txtKhachHang = new MyTextField();
        txtKhachHang.setFont(new Font(fontName, fontStyle, fontSize));
        txtKhachHang.setEnabled(false);
        pnlThongTin.add(txtKhachHang, "w 100:260:350, h 36! , wrap");
        
        JLabel lblLoaiPhong = new JLabel("Loại Phòng :");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblLoaiPhong, "align right");

        JTextField txtLoaiPhong = new MyTextField();
        txtLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        txtLoaiPhong.setEnabled(false);
        pnlThongTin.add(txtLoaiPhong, "w 100:260:350, h 36! , wrap");
        
        JLabel lblGia = new JLabel("Giá phòng/giờ :");
        lblGia.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblGia, "align right");

        JTextField txtGia = new MyTextField();
        txtGia.setFont(new Font(fontName, fontStyle, fontSize));
        txtGia.setEnabled(false);
        
        pnlThongTin.add(txtGia, "w 100:260:350, h 36! , wrap");
        
        JLabel lblNhanVien = new JLabel("Nhân viên :");
        lblNhanVien.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTin.add(lblNhanVien, "align right");

        JTextField txtNhanVien= new MyTextField();
        txtNhanVien.setFont(new Font(fontName, fontStyle, fontSize));
        txtNhanVien.setEnabled(false);
        pnlThongTin.add(txtNhanVien, "w 100:260:350, h 36! , wrap");
        
        pnlThongTin.setBackground(Color.WHITE);
           
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new MigLayout("","push[]10[]20","push[]20"));
        pnlButton.setBackground(Color.WHITE);
        Button btnHuy = new Button("Giao phòng");
        btnHuy.setFont(new Font(fontName, fontStyle, fontSize));
        btnHuy.setBackground(colorBtn);
        Button btnDoiPhong = new Button("Thoát");
        btnDoiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        btnDoiPhong.setBackground(colorBtn);
        
        pnlButton.add(btnHuy,"h 36!");
        pnlButton.add(btnDoiPhong,"h 36!");
        
        pnlThongTinPhong.add(pnlThongTin,"w 50%,h 100%");
        pnlThongTinPhong.add(pnlButton,"w 50%,h 100%");
        pnlInfoBottom.add(pnlThongTinPhong,"w 65%,h 100%");
    }
    
    public void initForm(){
        mainPanel.setLayout(new MigLayout("","20[center]20"));
//        MainPanel.setBackground(Color.WHITE);
        
        pnlInfoTop = new PanelShadow();
        pnlInfoTop.setLayout(new MigLayout("", "20[center] 20 [center]20", "20[]20"));
        pnlInfoBottom = new PanelShadow();
        pnlInfoBottom.setLayout(new MigLayout("", "20[center] 20 [center]20", "20[]20"));
        
        initSetTheRoom();
        initService();
        initCustomer();
        initRoom();
        
        pnlInfoTop.setBackground(Color.WHITE);
        pnlInfoBottom.setBackground(Color.WHITE);
        
        mainPanel.add(pnlInfoTop,"w 100%,h 50%,wrap");
        mainPanel.add(pnlInfoBottom,"w 100%,h 50%");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
