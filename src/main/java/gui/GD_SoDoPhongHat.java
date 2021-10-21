package gui;

import gui.swing.button.Button;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;


public class GD_SoDoPhongHat extends JPanel {

    public GD_SoDoPhongHat() {
        initComponents();
        buildGD();
    }
    
    private void buildGD() {
        panelForm.setPreferredSize(new Dimension(1119, 181));
        panelForm.setLayout(new MigLayout("fill", "push[center]10[center]20[center]10[]push", "push[center]20[center]20[]push"));
        
        JLabel lbLoaiPhong = new JLabel("Loại phòng:");
        lbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(lbLoaiPhong);
        
        JComboBox<String> cbLoaiPhong = new JComboBox<>();
        cbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(cbLoaiPhong, "w 20%, h 36!");
        
        JLabel lbTrangThai = new JLabel("Trạng thái");
        lbTrangThai.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(lbTrangThai);
        
        JComboBox<String> cbTrangThai = new JComboBox<>();
        cbTrangThai.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(cbTrangThai, "w 20%, h 36!, wrap");
        
        JLabel lbSdt = new JLabel("Số điện thoại");
        lbSdt.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(lbSdt);
        
        JTextField txtSdt = new MyTextField();
        txtSdt.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(txtSdt, "w 20%");
        
        JLabel lbTenPhong = new JLabel("Tên phòng:");
        lbTenPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(lbTenPhong);
        
        JTextField txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(txtTenPhong, "w 20%, wrap");
        
        Button timKiemBtn = new Button("Tìm kiếm");
        timKiemBtn.setFont(new Font("sansserif", Font.PLAIN, 12));
        timKiemBtn.setBackground(new Color(184, 238, 241));
        panelForm.add(timKiemBtn, "cell 3 2, align right, w 80!, h 36!");
        
        setPreferredSize(new Dimension(getWidth(), 1500));
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblMenu = new javax.swing.JLabel();
        panelForm = new gui.panel.PanelShadow();
        panelMap = new gui.component.PanelMap();

        setOpaque(false);

        lblMenu.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        lblMenu.setForeground(new java.awt.Color(4, 72, 210));
        lblMenu.setText("Quản lý phòng / Sơ đồ phòng hát");

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
            .addGap(0, 181, Short.MAX_VALUE)
        );

        panelMap.setBackground(new java.awt.Color(255, 255, 255));
        panelMap.setShadowOpacity(0.3F);
        panelMap.setShadowSize(3);
        panelMap.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout panelMapLayout = new javax.swing.GroupLayout(panelMap);
        panelMap.setLayout(panelMapLayout);
        panelMapLayout.setHorizontalGroup(
            panelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        panelMapLayout.setVerticalGroup(
            panelMapLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 390, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblMenu)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(panelMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addComponent(lblMenu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(panelMap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblMenu;
    private gui.panel.PanelShadow panelForm;
    private gui.component.PanelMap panelMap;
    // End of variables declaration//GEN-END:variables
}
