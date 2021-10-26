package gui;

import entity.TrangThaiPhong;
import gui.component.PanelStatus;
import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;


public class GD_SoDoPhongHat extends JPanel {

    public GD_SoDoPhongHat() {
        initComponents();
        buildGD();
    }
    
    private void buildGD() {
        
        panelForm.setPreferredSize(new Dimension(getWidth(), 265));
        panelForm.setLayout(new MigLayout("fill", "push[center]10[center]20[center]10[]push", "60[center]20[center]20[]push"));
        
        JLabel lbSdt = new JLabel("Số điện thoại");
        lbSdt.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(lbSdt);
        
        MyTextField txtSdt = new MyTextField();
        txtSdt.setFont(new Font("sansserif", Font.PLAIN, 12));
        txtSdt.setBorderLine(true);
//        txtSdt.setBorderRadius(10);
        panelForm.add(txtSdt, "w 20%");
        
        JLabel lbTenPhong = new JLabel("Tên phòng:");
        lbTenPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(lbTenPhong);
        
        MyTextField txtTenPhong = new MyTextField();
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
//        txtTenPhong.setBorderRadius(10);
        panelForm.add(txtTenPhong, "w 20%, wrap");
        
        JLabel lbLoaiPhong = new JLabel("Loại phòng:");
        lbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(lbLoaiPhong);
        
        
        MyComboBox<String> cbLoaiPhong = new MyComboBox<>(new String[] {"--Tất cả--", "Phòng thường", "Phòng tiệc", "Phòng vip"});
        cbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        cbLoaiPhong.setBorderLine(true);
        cbLoaiPhong.setBorderRadius(10);
        panelForm.add(cbLoaiPhong, "w 20%, h 30!");
        
        JLabel lbTrangThai = new JLabel("Trạng thái");
        lbTrangThai.setFont(new Font("sansserif", Font.PLAIN, 12));
        panelForm.add(lbTrangThai);
        
        MyComboBox<String> cbTrangThai = new MyComboBox<>(new String[] {"--Tất cả--", "Phòng trống", "Phòng đang hát", "Phòng đặt trước"});
        cbTrangThai.setFont(new Font("sansserif", Font.PLAIN, 12));
        cbTrangThai.setBorderLine(true);
        cbTrangThai.setBorderRadius(10);
        panelForm.add(cbTrangThai, "w 20%, h 30!, wrap");
        
        Button timKiemBtn = new Button("Tìm kiếm");
        timKiemBtn.setFont(new Font("sansserif", Font.PLAIN, 12));
        timKiemBtn.setBorderline(true);
        timKiemBtn.setBorderRadius(5);
        timKiemBtn.setBackground(new Color(184, 238, 241));
        panelForm.add(timKiemBtn, "cell 3 2, align right, w 80!, h 36!");
        
        panelForm.add(createPaneStatus(), "pos 0al 1al 100% n, h 50!");
        
        panelForm.add(createPanelTitle(), "pos 0al 0al 100% n, h 40!");
        
        setPreferredSize(new Dimension(getWidth(), 1500));
    }
    
    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Sơ đồ phòng hát");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return  pnlTitle;
    }
    
    private JPanel createPaneStatus() {
        JPanel pnlStatus = new JPanel();
        pnlStatus.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0, 0, 0, 0.1f)));
        pnlStatus.setOpaque(false);
        pnlStatus.setLayout(new MigLayout("fill", "", ""));
        TrangThaiPhong[] trangThai = TrangThaiPhong.values();
        for (TrangThaiPhong trangThai1 : trangThai) {
            PanelStatus pn = new PanelStatus(trangThai1);
            pnlStatus.add(pn);
        }
        return pnlStatus;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelForm = new gui.swing.panel.PanelShadow();
        panelMap = new gui.component.PanelMap();

        setOpaque(false);

        panelForm.setBackground(new java.awt.Color(255, 255, 255));
        panelForm.setShadowOpacity(0.3F);
        panelForm.setShadowSize(2);
        panelForm.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout panelFormLayout = new javax.swing.GroupLayout(panelForm);
        panelForm.setLayout(panelFormLayout);
        panelFormLayout.setHorizontalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelFormLayout.setVerticalGroup(
            panelFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 235, Short.MAX_VALUE)
        );

        panelMap.setBackground(new java.awt.Color(255, 255, 255));
        panelMap.setShadowOpacity(0.3F);
        panelMap.setShadowSize(2);
        panelMap.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelForm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(panelMap, javax.swing.GroupLayout.DEFAULT_SIZE, 1055, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelForm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelMap, javax.swing.GroupLayout.DEFAULT_SIZE, 373, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow panelForm;
    private gui.component.PanelMap panelMap;
    // End of variables declaration//GEN-END:variables
}
