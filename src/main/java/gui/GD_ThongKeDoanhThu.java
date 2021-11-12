/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

public class GD_ThongKeDoanhThu extends javax.swing.JPanel {

    public GD_ThongKeDoanhThu() {
        initComponents();
        buildGD();
        tblThongKe.fixTable(scrThongKe);
    }
    
    public void buildGD(){
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 14;
        Color colorBtn = new Color(184, 238, 241);
        
        //pnlTop.setPreferredSize(new Dimension(1119, 80));
        this.add(pnlTop,"w 100%, h 80%");
        pnlTop.setLayout(new MigLayout());
        
        pnlTop.add(createPanelTitle(), "span,pos 0al 0al 100% n, h 40!");
        pnlThongKeTheo.setOpaque(false);
        pnlThongKeTheo.setLayout(new MigLayout("", "10[center]10[center] 20 [center]10[center] 250 [center]10[center]10", "70[center] push"));
        pnlTop.add(pnlThongKeTheo, "w 100%, h 15%, wrap");
        
         //Loại thống kê
        JLabel lblLoaiTK = new JLabel("Loại thống kê:");
        lblLoaiTK.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(lblLoaiTK,"align right");
        
        MyComboBox<String> cmbLoaiTK = new MyComboBox<>(new String[] {"Doanh thu", "Hàng hóa"});
        cmbLoaiTK.setFont(new Font(fontName, fontStyle, fontSize));
        cmbLoaiTK.setBorderLine(true);
        cmbLoaiTK.setBorderRadius(10);
        pnlThongKeTheo.add(cmbLoaiTK, " w 60%, h 36!");
        
        //Thống kê theo
        JLabel lblTKTheo = new JLabel("Thống kê theo:");
        lblTKTheo.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(lblTKTheo,"align right");
        
        
        MyComboBox<String> cmbTKTheo = new MyComboBox<>(new String[] {"Ngày", "Tháng","Năm"});
        cmbTKTheo.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTKTheo.setBorderLine(true);
        cmbTKTheo.setBorderRadius(10);
        pnlThongKeTheo.add(cmbTKTheo, "w 60%, h 36!");
        
        //Khác
        JLabel lblKhac = new JLabel("Khác:");
        lblKhac.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(lblKhac,"align right");
        
        MyComboBox<String> cmbKhac = new MyComboBox<>(new String[] {"--Tất cả--", "Dịch vụ tiêu thụ nhiều nhất", "Dịch vụ tiêu thụ ít nhất"});
        cmbKhac.setFont(new Font(fontName, fontStyle, fontSize));
        cmbKhac.setBorderLine(true);
        cmbKhac.setBorderRadius(10);
        pnlThongKeTheo.add(cmbKhac, ",w 60%, h 36!");
       
        pnlBangThongKe.setOpaque(false);
        pnlBangThongKe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Bảng thống kê", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));
        pnlTop.add(pnlBangThongKe, "w 100%, h 75%, wrap");//
       
          
        pnlTinhTong.setOpaque(false);
        pnlTinhTong.setLayout(new MigLayout());
        pnlTop.add(pnlTinhTong, "align right,w 25%, h 10%");
        
        //Tính tổng
        JLabel lblTong = new JLabel("Tổng:");
        lblTong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTinhTong.add(lblTong, "align right");

        MyTextField txtTong = new MyTextField();
        txtTong.setFont(new Font(fontName, fontStyle, fontSize));
        txtTong.setBorderLine(true);
        txtTong.setBorderRadius(5);
        pnlTinhTong.add(txtTong, "w 80%, h 36!");
        
        this.add(pnlBottom,"w 100%, h 20%");
        pnlBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 1), "Xuất danh sách", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));
        pnlBottom.setLayout(new MigLayout("", "20[center] 50 [center] 20 [center]10", " [center] "));
        
        MyTextField txtDuongDan = new MyTextField();
        txtDuongDan.setFont(new Font(fontName, fontStyle, fontSize));
        txtDuongDan.setBorderLine(true);
        txtDuongDan.setBorderRadius(5);
        pnlBottom.add(txtDuongDan, "w 80%, h 36!");
        
        //Chọn đường dẫn
        Button btnChonDD = new Button("Chọn đường dẫn");
        btnChonDD.setFont(new Font(fontName, fontStyle, fontSize));
        btnChonDD.setBackground(colorBtn);
        btnChonDD.setBorderRadius(5);
        pnlBottom.add(btnChonDD, "w 200!, h 36!");

        // Xuất danh sách
        Button btnXuat = new Button("Xuất danh sách");
        btnXuat.setFont(new Font(fontName, fontStyle, fontSize));
        btnXuat.setBackground(colorBtn);
        btnXuat.setBorderRadius(5);
        pnlBottom.add(btnXuat, "w 200!, h 36!");
        
        setOpaque(false);
        setPreferredSize(new Dimension(getWidth(), 950));
    }

    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("QUẢN LÝ KHÁCH HÀNG");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return  pnlTitle;
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
        pnlThongKeTheo = new gui.swing.panel.PanelShadow();
        pnlBangThongKe = new gui.swing.panel.PanelShadow();
        scrThongKe = new javax.swing.JScrollPane();
        tblThongKe = new gui.swing.table2.MyTable();
        pnlTinhTong = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

        pnlThongKeTheo.setBackground(new java.awt.Color(255, 255, 255));
        pnlThongKeTheo.setShadowColor(new java.awt.Color(255, 255, 255));
        pnlThongKeTheo.setShadowOpacity(0.3F);
        pnlThongKeTheo.setShadowSize(3);
        pnlThongKeTheo.setShadowType(null);

        javax.swing.GroupLayout pnlThongKeTheoLayout = new javax.swing.GroupLayout(pnlThongKeTheo);
        pnlThongKeTheo.setLayout(pnlThongKeTheoLayout);
        pnlThongKeTheoLayout.setHorizontalGroup(
            pnlThongKeTheoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlThongKeTheoLayout.setVerticalGroup(
            pnlThongKeTheoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 94, Short.MAX_VALUE)
        );

        pnlBangThongKe.setBackground(new java.awt.Color(255, 255, 255));
        pnlBangThongKe.setShadowColor(new java.awt.Color(255, 255, 255));
        pnlBangThongKe.setShadowOpacity(0.3F);
        pnlBangThongKe.setShadowSize(3);

        tblThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã hóa đơn", "Ngày lập hóa đơn", "Tên khách hàng", "Nhân viên", "Tổng tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrThongKe.setViewportView(tblThongKe);
        if (tblThongKe.getColumnModel().getColumnCount() > 0) {
            tblThongKe.getColumnModel().getColumn(0).setResizable(false);
            tblThongKe.getColumnModel().getColumn(1).setResizable(false);
            tblThongKe.getColumnModel().getColumn(2).setResizable(false);
            tblThongKe.getColumnModel().getColumn(3).setResizable(false);
            tblThongKe.getColumnModel().getColumn(4).setResizable(false);
            tblThongKe.getColumnModel().getColumn(5).setResizable(false);
        }

        javax.swing.GroupLayout pnlBangThongKeLayout = new javax.swing.GroupLayout(pnlBangThongKe);
        pnlBangThongKe.setLayout(pnlBangThongKeLayout);
        pnlBangThongKeLayout.setHorizontalGroup(
            pnlBangThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1106, Short.MAX_VALUE)
        );
        pnlBangThongKeLayout.setVerticalGroup(
            pnlBangThongKeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE)
        );

        pnlTinhTong.setBackground(new java.awt.Color(255, 255, 255));
        pnlTinhTong.setShadowColor(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTinhTongLayout = new javax.swing.GroupLayout(pnlTinhTong);
        pnlTinhTong.setLayout(pnlTinhTongLayout);
        pnlTinhTongLayout.setHorizontalGroup(
            pnlTinhTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTinhTongLayout.setVerticalGroup(
            pnlTinhTongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 76, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongKeTheo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBangThongKe, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlTinhTong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(pnlThongKeTheo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBangThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTinhTong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow pnlBangThongKe;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlThongKeTheo;
    private gui.swing.panel.PanelShadow pnlTinhTong;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane scrThongKe;
    private gui.swing.table2.MyTable tblThongKe;
    // End of variables declaration//GEN-END:variables
}
