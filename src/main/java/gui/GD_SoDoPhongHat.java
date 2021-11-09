package gui;

import dao.Phong_DAO;
import entity.TrangThaiPhong;
import gui.component.PanelMap;
import gui.component.PanelStatus;
import gui.component.Slide1;
import gui.component.Slide2;
import gui.component.Slide3;
import gui.dropshadow.ShadowType;
import gui.event.EventShowInfoOver;
import gui.swing.button.Button;
import gui.swing.panel.slideshow.Slideshow;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelListener;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class GD_SoDoPhongHat extends javax.swing.JPanel {
    
    private PanelMap panelMap;
    private Phong_DAO phong_DAO;
    
    public void addEvent(EventShowInfoOver event) {
        panelMap.addEvent(event);
    }
    
    public void addEventSp(MouseWheelListener event) {
        panelMap.addEventSp(event);
    }

    public PanelMap getPanelMap() {
        return panelMap;
    }
    
    public GD_SoDoPhongHat() {
        phong_DAO = new Phong_DAO();
        initComponents();
        buildGD();
        panelMap();
    }

    private void buildGD() {
        pnlTop.setLayout(new MigLayout("fill, insets 0, wrap", "0[fill]0", "0[fill]0[fill]0[fill]0"));

        pnlTop.add(createPanelTitle(), "h 40!, span 2");
        
        pnlTop.add(createPanelForm(), "split 2");
        
        pnlTop.add(createSlideshow(), "w 500!");
        
        pnlTop.add(createPaneStatus(), "h 50!, span 2");
        
    }
    
    private JPanel createPanelForm() {
        JPanel pnlForm = new JPanel();
        pnlForm.setOpaque(false);
        pnlForm.setLayout(new MigLayout("fill", "push[center]10[center]20[center]10[]push", "10[center]20[center]20[]5"));
        
        JLabel lbSdt = new JLabel("Số điện thoại");
        lbSdt.setFont(new Font("sansserif", Font.PLAIN, 12));
        pnlForm.add(lbSdt);

        MyTextField txtSdt = new MyTextField();
        txtSdt.setFont(new Font("sansserif", Font.PLAIN, 12));
        txtSdt.setBorderLine(true);
        txtSdt.setBorderRadius(5);
        pnlForm.add(txtSdt, "w 25%");

        JLabel lbTenPhong = new JLabel("Tên phòng:");
        lbTenPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        pnlForm.add(lbTenPhong);

        MyTextField txtTenPhong = new MyTextField();
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        txtTenPhong.setBorderRadius(5);
        pnlForm.add(txtTenPhong, "w 25%, wrap");

        JLabel lbLoaiPhong = new JLabel("Loại phòng:");
        lbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        pnlForm.add(lbLoaiPhong);

        MyComboBox<String> cbLoaiPhong = new MyComboBox<>(new String[]{"--Tất cả--", "Phòng thường", "Phòng tiệc", "Phòng vip"});
        cbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        cbLoaiPhong.setBorderLine(true);
        cbLoaiPhong.setBorderRadius(10);
        pnlForm.add(cbLoaiPhong, "w 25%, h 30!");

        JLabel lbTrangThai = new JLabel("Trạng thái");
        lbTrangThai.setFont(new Font("sansserif", Font.PLAIN, 12));
        pnlForm.add(lbTrangThai);

        MyComboBox<String> cbTrangThai = new MyComboBox<>();
        cbTrangThai.addItem("--Tất cả--");
        for (TrangThaiPhong value : TrangThaiPhong.values()) {
            cbTrangThai.addItem(value.getTrangThai());
        }
        cbTrangThai.setFont(new Font("sansserif", Font.PLAIN, 12));
        cbTrangThai.setBorderLine(true);
        cbTrangThai.setBorderRadius(10);
        pnlForm.add(cbTrangThai, "w 25%, h 30!, wrap");

        Button timKiemBtn = new Button("Tìm kiếm");
        timKiemBtn.setFont(new Font("sansserif", Font.PLAIN, 12));
        timKiemBtn.setBorderline(true);
        timKiemBtn.setBorderRadius(5);
        timKiemBtn.setBackground(new Color(184, 238, 241));
        timKiemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });
        pnlForm.add(timKiemBtn, "cell 3 2, align right, w 80!, h 36!");
        return pnlForm;
    }
    
    private Slideshow createSlideshow() {
        Slideshow slideshow = new Slideshow();
        slideshow.setOpaque(false);
        slideshow.initSlideshow(new Slide1(), new Slide2(), new Slide3());
        return slideshow;
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
        return pnlTitle;
    }

    private JPanel createPaneStatus() {
        JPanel pnlStatus = new JPanel();
        pnlStatus.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0, 0, 0, 0.1f)));
        pnlStatus.setOpaque(false);
        pnlStatus.setLayout(new MigLayout("fill", "", ""));
        
        PanelStatus pnlTrong = new PanelStatus(TrangThaiPhong.TRONG);
        pnlTrong.setText(phong_DAO.getSoLuongPhongTheoTrangThai(TrangThaiPhong.TRONG));
        pnlStatus.add(pnlTrong);
        
        PanelStatus pnlDangHat = new PanelStatus(TrangThaiPhong.DANG_HAT);
        pnlDangHat.setText(phong_DAO.getSoLuongPhongTheoTrangThai(TrangThaiPhong.DANG_HAT));
        pnlStatus.add(pnlDangHat);
        
        PanelStatus pnlDatTruoc = new PanelStatus(TrangThaiPhong.DAT_TRUOC);
        pnlDatTruoc.setText(phong_DAO.getSoLuongPhongTheoTrangThai(TrangThaiPhong.DAT_TRUOC));
        pnlStatus.add(pnlDatTruoc);
        
        PanelStatus pnlBan = new PanelStatus(TrangThaiPhong.BAN);
        pnlBan.setText(phong_DAO.getSoLuongPhongTheoTrangThai(TrangThaiPhong.BAN));
        pnlStatus.add(pnlBan);
        
        PanelStatus pnlDangDon = new PanelStatus(TrangThaiPhong.DANG_DON);
        pnlDangDon.setText(phong_DAO.getSoLuongPhongTheoTrangThai(TrangThaiPhong.DANG_DON));
        pnlStatus.add(pnlDangDon);
        
        PanelStatus pnlDangSua = new PanelStatus(TrangThaiPhong.DANG_SUA);
        pnlDangSua.setText(phong_DAO.getSoLuongPhongTheoTrangThai(TrangThaiPhong.DANG_SUA));
        pnlStatus.add(pnlDangSua);
        
        return pnlStatus;
    }

    private void panelMap() {
        panelMap = new PanelMap();
        panelMap.setBackground(Color.WHITE);
        panelMap.setShadowOpacity(0.3f);
        panelMap.setShadowSize(2);
        
        panelMap.setShadowType(ShadowType.TOP);
        add(panelMap, BorderLayout.CENTER);
        panelMap.setPreferredSize(new Dimension(getWidth(), getHeight() + 800));
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(2);
        pnlTop.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1225, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 134, Short.MAX_VALUE)
        );

        add(pnlTop, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow pnlTop;
    // End of variables declaration//GEN-END:variables
}
