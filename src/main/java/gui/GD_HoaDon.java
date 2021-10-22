/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author NGUYE
 */
public class GD_HoaDon extends javax.swing.JPanel {

    /**
     * Creates new form GD_HoaDon
     */
    public GD_HoaDon() {
        initComponents();
        build_GDHoaDon();
    }
    
    public void build_GDHoaDon(){
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);

        lblMenu.setFont(new Font(fontName, fontStyle, fontSize));

        panelForm.setPreferredSize(new Dimension(1119, 141));
        panelForm.setLayout(new MigLayout("","[][][]","[]"));
        
        /*
         * Begin: group Chọn thời gian 
         */
        JPanel pnlThoiGianHD = new JPanel();
        pnlThoiGianHD.setOpaque(false);
        pnlThoiGianHD.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Chọn thời gian", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));
        pnlThoiGianHD.setLayout(new MigLayout("", "10[center] 10 [center]10", "[center]10[center]"));
        panelForm.add(pnlThoiGianHD, "w 40%, h 135!");

        // Chọn thời gian bắt đầu
        JTextField txtBatDau = new MyTextField();
        txtBatDau.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThoiGianHD.add(txtBatDau, "w 50%, h 36!");
        
        // Chọn thời gian kết thúc
        JTextField txtKetThuc = new MyTextField();
        txtKetThuc.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThoiGianHD.add(txtKetThuc, "w 50%, h 36!, wrap");
        
        //Tùy chỉnh
        JComboBox<String> cmbTuyChinh = new JComboBox<>();
        cmbTuyChinh.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTuyChinh.addItem("Tùy chỉnh");
        pnlThoiGianHD.add(cmbTuyChinh,"span, w 100%, h 36!");
        /*
         * End: group Chọn thời gian bắt đầu
         */
        
        /*
         * Begin: group Tìm kiếm
         */
        JPanel pnlTimKiemHD = new JPanel();
        pnlTimKiemHD.setOpaque(false);
        pnlTimKiemHD.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Tìm kiếm", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));
        pnlTimKiemHD.setLayout(new MigLayout("", "10[center]10", "[center]10[center]"));
        panelForm.add(pnlTimKiemHD, "w 40%, h 135!");
        
        // Tìm kiếm
        JTextField txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTimKiemHD.add(txtTimKiem, "w 100%, h 36!, wrap");
        
        //Chọn cột cần tìm
        JComboBox<String> cmbCot = new JComboBox<>();
        cmbCot.setFont(new Font(fontName, fontStyle, fontSize));
        cmbCot.addItem("Chọn cột cần tìm");
        pnlTimKiemHD.add(cmbCot,"w 100%, h 36!");
        
        
        /*
         * End: group Tìm kiếm
         */
        
        /*
         * Begin: group Sắp xếp
         */
        JPanel pnlSapXepHD = new JPanel();
        pnlSapXepHD.setOpaque(false);
        pnlSapXepHD.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Sắp xếp", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 18), Color.gray));
        pnlSapXepHD.setLayout(new MigLayout("", "10[][]10", "[center]10[center]10"));
        panelForm.add(pnlSapXepHD, "w 20%, h 135!");
        
        
        //Chọn cột cần sắp xếp
        JComboBox<String> cmbSapXep = new JComboBox<>();
        cmbSapXep.setFont(new Font(fontName, fontStyle, fontSize));
        cmbSapXep.addItem("Tất cả");
        pnlSapXepHD.add(cmbSapXep,"span, w 100%, h 36!, wrap");
        
        //Sắp xếp từ bé đến lớn
        JPanel pnlSapXepThuTu = new JPanel();
        pnlSapXepThuTu.setOpaque(false);
        pnlSapXepThuTu.setLayout(new MigLayout("","push[]0[]0","[]"));
        
        JCheckBox chkSapXepThuTu = new JCheckBox();
        chkSapXepThuTu.setOpaque(false);
        pnlSapXepThuTu.add(chkSapXepThuTu);
        
        JLabel lblSapXepThuTu = new JLabel("Bé đến lớn");
        lblSapXepThuTu.setFont(new Font(fontName, fontStyle, fontSize));
        pnlSapXepThuTu.add(lblSapXepThuTu);
        
        pnlSapXepHD.add(pnlSapXepThuTu,"w 100%");
        
        
        /*
         * End: group Sắp xếp
         */
        
        
        setPreferredSize(new Dimension(getWidth(), 1500));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMenu = new javax.swing.JLabel();
        panelForm = new gui.panel.PanelShadow();
        panelCenter = new gui.component.PanelMap();

        lblMenu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(4, 72, 210));
        lblMenu.setText("Quản lý hóa đơn ");

        panelForm.setBackground(new java.awt.Color(255, 255, 255));
        panelForm.setShadowOpacity(0.3F);
        panelForm.setShadowSize(3);
        panelForm.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 236, Short.MAX_VALUE)
        );

        panelCenter.setBackground(new java.awt.Color(255, 255, 255));
        panelCenter.setShadowOpacity(0.3F);
        panelCenter.setShadowSize(3);
        panelCenter.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout panelCenterLayout = new javax.swing.GroupLayout(panelCenter);
        panelCenter.setLayout(panelCenterLayout);
        panelCenterLayout.setHorizontalGroup(
            panelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelCenterLayout.setVerticalGroup(
            panelCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMenu)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMenu;
    private gui.component.PanelMap panelCenter;
    private gui.panel.PanelShadow panelForm;
    // End of variables declaration//GEN-END:variables
}
