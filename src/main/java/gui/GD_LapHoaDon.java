/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package gui;

import gui.swing.panel.PanelShadow;
import gui.swing.button.Button;
import gui.swing.table.SpinnerEditor;
import gui.swing.table.TableCustom;
import gui.swing.table2.MyTable;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
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
    
    private MyTable tableSelected;
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    private int font12 = 12;
    private Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);
    
    public GD_LapHoaDon() {
        super();
        setModal(true);
        initComponents();
        setSize(new Dimension(1200,740));
        setLocation(150, 10);
        initForm();
        addAction();
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
        
        pnlDanhSachDichVu.setLayout(new MigLayout("","[]","10[]10"));
        pnlDanhSachDichVu.setBackground(Color.WHITE);
        
        JLabel lblDanhSachPhieu = new JLabel("Dịch vụ");
        lblDanhSachPhieu.setFont(new Font(fontName, fontPlain, font16));
        lblDanhSachPhieu.setForeground(colorLabel);
        pnlDanhSachDichVu.add(lblDanhSachPhieu, "span, w 100%, h 30!, wrap");

        Object dataSelected[][] = { 
                {  "Tran Van Minh",0, "6000","0"}, 
                {  "Phan Van Tai",0, "8000","0"}, 
                {  "Do Cao Hoc",0, "7000","0"},             
        };
        String colSelected[] = {"Tên","Số lượng","Giá","Tổng"};
        
        DefaultTableModel model = new DefaultTableModel(
            dataSelected,
            colSelected
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false,false
            }; 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        tableSelected = new MyTable(){
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				case 3:
					return String.class;
				default:
					return Boolean.class;
				}
			}
        };
    
        tableSelected.setModel(model);
        
        TableColumnModel tcm = tableSelected.getColumnModel();
        TableColumn tc = tcm.getColumn(1);
        tc.setCellEditor(new SpinnerEditor());
        
        JScrollPane spSelected = new JScrollPane(tableSelected);
        tableSelected.fixTable(spSelected);
        
        JPanel tong =  new JPanel();
        tong.setLayout(new MigLayout("","13[]5[]push","push[]"));
        tong.setBackground(Color.WHITE);
        
        JLabel lblTong = new JLabel("Tổng tiền dịch vụ :");
        lblTong.setFont(new Font(fontName, fontPlain, font14));
        tong.add(lblTong, "align right");

        MyTextField txtTong = new MyTextField();
        txtTong.setFont(new Font(fontName, fontPlain, font14));
        txtTong.setEnabled(false);
        txtTong.setBorderLine(true);
        txtTong.setBorderRadius(5);
        tong.add(txtTong, "w 100:200:260, h 36! , wrap");

        pnlDanhSachDichVu.add(spSelected,"w 100%,h 90%,wrap");
        pnlDanhSachDichVu.add(tong,"w 100%,h 10%");
        pnlInfoTop.add(pnlDanhSachDichVu,"w 50%,h 100%");
        
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(20, 440));
        pnlInfoTop.add(spr);
    }
    
    public void initInfoRoom(){
        JPanel pnlInfoRightWrap = new JPanel();
        pnlInfoRightWrap.setLayout(new MigLayout("","[]","10[center]10[center]18[center]18"));
        
        JLabel lblTTPhong = new JLabel("Thông tin sử dụng");
        lblTTPhong.setFont(new Font(fontName, fontPlain, font16));
        lblTTPhong.setForeground(colorLabel);
        pnlInfoRightWrap.add(lblTTPhong, "span, w 100%, h 30!, wrap");
        
        pnlInfoRightWrap.setBackground(Color.WHITE);
        
        
        pnlInfoRoom = new JPanel();
        pnlInfoRoom.setLayout(new MigLayout("","28[][]10","10[]5"));

        JLabel lblNgay= new JLabel("Ngày: ");
        lblNgay.setFont(new Font(fontName, fontPlain, font14));
        
        pnlInfoRoom.add(lblNgay, "align right");

        MyTextField txtNgay = new MyTextField();
        txtNgay.setFont(new Font(fontName, fontPlain, font14));
        txtNgay.setEnabled(false);
        txtNgay.setBorderLine(true);
        txtNgay.setBorderRadius(5);
        
        pnlInfoRoom.add(txtNgay, "w 100%, h 36! , wrap");
        
        JLabel lblTenPhong= new JLabel("Tên phòng :");
        lblTenPhong.setFont(new Font(fontName, fontPlain, font14));
        
        pnlInfoRoom.add(lblTenPhong, "align right");

        MyTextField txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontPlain, font14));
        txtTenPhong.setEnabled(false);
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setBorderRadius(5);
        
        pnlInfoRoom.add(txtTenPhong, "w 100%, h 36! , wrap");
        
        JLabel lblLoai= new JLabel("Loại phòng :");
        lblLoai.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblLoai, "align right");

        MyTextField txtLoai = new MyTextField();
        txtLoai.setFont(new Font(fontName, fontPlain, font14));
        txtLoai.setEnabled(false);
        txtLoai.setBorderLine(true);
        txtLoai.setBorderRadius(5);
        
        pnlInfoRoom.add(txtLoai, "w 100%, h 36! , wrap");
        
        JLabel lblGia= new JLabel("Giá phòng/giờ :");
        lblGia.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblGia, "align right");

        MyTextField txtGia = new MyTextField();
        txtGia.setFont(new Font(fontName, fontPlain, font14));
        txtGia.setEnabled(false);
        txtGia.setBorderLine(true);
        txtGia.setBorderRadius(5);
        
        pnlInfoRoom.add(txtGia, "w 100%, h 36! , wrap");
        
        JLabel lblNhanVien= new JLabel("Nhân viên :");
        lblNhanVien.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblNhanVien, "align right");

        MyTextField txtNhanVien = new MyTextField();
        txtNhanVien.setFont(new Font(fontName, fontPlain, font14));
        txtNhanVien.setEnabled(false);
        txtNhanVien.setBorderLine(true);
        txtNhanVien.setBorderRadius(5);
        
        pnlInfoRoom.add(txtNhanVien, "w 100%, h 36! , wrap");
        
        JLabel lblStart = new JLabel("Giờ Bắt đầu :");
        lblStart.setFont(new Font(fontName, fontPlain, font14));
        
        pnlInfoRoom.add(lblStart, "align right");

        MyTextField txtStart = new MyTextField();
        txtStart.setFont(new Font(fontName, fontPlain, font14));
        txtStart.setEnabled(false);
        txtStart.setBorderLine(true);
        txtStart.setBorderRadius(5);
        
        pnlInfoRoom.add(txtStart, "w 100%, h 36! , wrap");
        
        JLabel lblEnd = new JLabel("Giờ kết thúc :");
        lblEnd.setFont(new Font(fontName, fontPlain, font14));
        pnlInfoRoom.add(lblEnd, "align right");

        MyTextField txtEnd = new MyTextField();
        txtEnd.setFont(new Font(fontName, fontPlain, font14));
        txtEnd.setEnabled(false);
        txtEnd.setBorderLine(true);
        txtEnd.setBorderRadius(5);
        
        pnlInfoRoom.add(txtEnd, "w 100%, h 36! , wrap");
        pnlInfoRoom.setBackground(Color.WHITE);
        
        JPanel tongTienPhong =  new JPanel();
        tongTienPhong.setLayout(new MigLayout("fill,insets 0","13[]5[]push"));
        tongTienPhong.setBackground(Color.WHITE);
        
        JLabel lblTongTien = new JLabel("Tổng tiền phòng :");
        lblTongTien.setFont(new Font(fontName, fontPlain, font14));
        
        tongTienPhong.add(lblTongTien, "align left");

        MyTextField txtTongTien = new MyTextField();
        txtTongTien.setFont(new Font(fontName, fontPlain, font14));
        txtTongTien.setEnabled(false);
        txtTongTien.setBorderLine(true);
        txtTongTien.setBorderRadius(5);
        
        tongTienPhong.add(txtTongTien, "w 100:200:270, h 36!");
        
        pnlInfoRightWrap.add(pnlInfoRoom,"w 100%,h 90%,wrap");
        pnlInfoRightWrap.add(tongTienPhong,"w 100%,h 10%");
        
        pnlInfoTop.add(pnlInfoRightWrap,"w 50%,h 100%");
        
    }
    
    public void initAction(){
        pnlThanhToan = new JPanel();
        pnlThanhToan.setBackground(Color.WHITE);
        pnlThanhToan.setLayout(new MigLayout("","28[][]","10[]5"));
        
        JPanel pnlThongTin = new JPanel();
        pnlThongTin.setLayout(new MigLayout("","30[][]20","0[]10"));
        
        JLabel lblTongTien = new JLabel("Tổng tiền :");
        lblTongTien.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblTongTien, "align right");

        MyTextField txtTongTien = new MyTextField();
        txtTongTien.setFont(new Font(fontName, fontPlain, font14));
        txtTongTien.setEnabled(false);
        txtTongTien.setBorderLine(true);
        txtTongTien.setBorderRadius(5);
        
        pnlThongTin.add(txtTongTien, "w 100:200:270, h 36! , wrap");
        
        JLabel lblTienDua = new JLabel("Tiền khách đưa :");
        lblTienDua.setFont(new Font(fontName, fontPlain, font14));
        
        pnlThongTin.add(lblTienDua, "align right");

        MyTextField txtTienDua = new MyTextField();
        txtTienDua.setFont(new Font(fontName, fontPlain, font14));
        txtTienDua.setBorderLine(true);
        txtTienDua.setBorderRadius(5);
        
        pnlThongTin.add(txtTienDua, "w 100:200:270, h 36! , wrap");
        
        JLabel lblTraLai = new JLabel("Tiền trả lại :");
        lblTraLai.setFont(new Font(fontName, fontPlain, font14));
        pnlThongTin.add(lblTraLai, "align right");

        MyTextField txtTraLai= new MyTextField();
        txtTraLai.setFont(new Font(fontName, fontPlain, font14));
        txtTraLai.setEnabled(false);
        txtTraLai.setBorderLine(true);
        txtTraLai.setBorderRadius(5);
        
        pnlThongTin.add(txtTraLai, "w 100:200:270, h 36! , wrap");
        
        pnlThongTin.setBackground(Color.WHITE);
           
        JPanel pnlButton = new JPanel();
        pnlButton.setLayout(new MigLayout("","push[]10[]20","push[]20"));
        pnlButton.setBackground(Color.WHITE);
        
        Button btnHuy = new Button("Hủy xem");
        btnHuy.setFont(new Font(fontName, fontPlain, font14));
        btnHuy.setBackground(colorBtn);
        btnHuy.setBorderRadius(5);
        
        Button btnThanhToan = new Button("Thanh toán");
        btnThanhToan.setFont(new Font(fontName, fontPlain, font14));
        btnThanhToan.setBackground(colorBtn);
        btnThanhToan.setBorderRadius(5);
        
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
     
     public void addAction(){
        tableSelected.addMouseListener(new action());
    }
    
    private class action implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource().equals(tableSelected)){
                System.out.println(tableSelected.getModel().getValueAt(0,1));
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
    }
    
    private class actionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource().equals(tableSelected)){
                System.out.println(tableSelected.getModel().getValueAt(0,1));
            }
        }
        
    }
     
     
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel mainPanel;
    // End of variables declaration//GEN-END:variables
}
