package gui;

import com.formdev.flatlaf.FlatLightLaf;
import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.HoaDon;
import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.component.PanelMap;
import gui.component.PanelStatus;
import gui.component.Slide1;
import gui.component.Slide2;
import gui.dialog.DL_CapNhatDichVu;
import gui.dialog.DL_ThanhToan;
import gui.dialog.DL_TiepNhanDatPhong;
import gui.swing.graphics.ShadowType;
import gui.swing.button.Button;
import gui.swing.event.EventRoom;
import gui.swing.panel.slideshow.Slideshow;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import gui.swing.event.EventShowInfoOver;
import gui.swing.event.EventTabSelected;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class GD_SoDoPhongHat extends javax.swing.JPanel {

    private PanelMap panelMap;
    private Phong_DAO phong_DAO;
    private LoaiPhong_DAO loaiPhong_DAO;
    private DefaultComboBoxModel<LoaiPhong> cbLoaiPhongModel;
    private DefaultComboBoxModel<TrangThaiPhong> cbTrangThaiModel;
    private MyTextField txtSearch;
    private MyTextField txtTenPhong;
    private MyComboBox<String> cbLoaiPhong;

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
        loaiPhong_DAO = new LoaiPhong_DAO();
        initComponents();
        buildGD();

    }

    private void buildGD() {
        pnlTop.setLayout(new MigLayout("fill, insets 0, wrap", "0[fill]0", "0[fill]0[fill]0[fill]0"));

        pnlTop.add(createPanelTitle(), "h 40!, span 2");

        pnlTop.add(createPanelForm(), "split 2");

        pnlTop.add(createSlideshow(), "w 500!");

        pnlTop.add(createPaneStatus(), "h 50!, span 2");

        add(panelMap(), BorderLayout.CENTER);

    }

    private JPanel createPanelForm() {
        JPanel pnlForm = new JPanel();
        pnlForm.setOpaque(false);
        pnlForm.setLayout(new MigLayout("fill", "push[center]10[center]20[center]10[center]push", "40[center]10[center]30"));

        JLabel lbTenPhong = new JLabel("Tên phòng:");
        lbTenPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlForm.add(lbTenPhong);

        txtTenPhong = new MyTextField();
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        txtTenPhong.setBorderRadius(10);
        pnlForm.add(txtTenPhong, "w 25%, h 35!");

        JLabel lbLoaiPhong = new JLabel("Loại phòng:");
        lbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlForm.add(lbLoaiPhong);

        cbLoaiPhongModel = new DefaultComboBoxModel<>();
        cbLoaiPhong = new MyComboBox<>(cbLoaiPhongModel);
        cbLoaiPhong.addItem("--Tất cả--");
        cbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        cbLoaiPhong.setBorderLine(true);
        cbLoaiPhong.setBorderRadius(10);
        pnlForm.add(cbLoaiPhong, "w 25%, h 35!");

//        JLabel lbTrangThai = new JLabel("Trạng thái");
//        lbTrangThai.setFont(new Font("sansserif", Font.PLAIN, 14));
//        pnlForm.add(lbTrangThai);
//        cbTrangThaiModel = new DefaultComboBoxModel<>();
//        MyComboBox<String> cbTrangThai = new MyComboBox<>(cbTrangThaiModel);
//        cbTrangThai.addItem("--Tất cả--");
//        cbTrangThai.setFont(new Font("sansserif", Font.PLAIN, 14));
//        cbTrangThai.setBorderLine(true);
//        cbTrangThai.setBorderRadius(10);
//        pnlForm.add(cbTrangThai, "w 25%, h 30!, wrap");
        Button timKiemBtn = new Button("Tìm kiếm");
        timKiemBtn.setFont(new Font("sansserif", Font.PLAIN, 14));
        timKiemBtn.setBorderline(true);
        timKiemBtn.setBorderRadius(5);
        timKiemBtn.setBackground(new Color(184, 238, 241));
        timKiemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadMap(panelMap.getIndexShowing());
            }
            
        });
        pnlForm.add(timKiemBtn, "cell 3 1, align right, w 80!, h 30!");

        loadDataForm();

        return pnlForm;
    }

    private void loadDataForm() {
//        cbLoaiPhongModel.addAll(loaiPhong_DAO.getDsLoaiPhong());
        List<LoaiPhong> loaiPhongs = loaiPhong_DAO.getDsLoaiPhong();
        for (LoaiPhong lp : loaiPhongs) {
            cbLoaiPhongModel.addElement(lp);
        }

//        TrangThaiPhong[] trangThaiPhongs = TrangThaiPhong.values();
//        for (TrangThaiPhong trangThaiPhong : trangThaiPhongs) {
//            cbTrangThaiModel.addElement(trangThaiPhong);
//        }
    }

    private Slideshow createSlideshow() {
        Slideshow slideshow = new Slideshow();
        slideshow.setOpaque(false);
        slideshow.initSlideshow(new Slide1(), new Slide2());
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

    private PanelMap panelMap() {
        panelMap = new PanelMap();
        panelMap.setBackground(Color.WHITE);
        panelMap.setShadowOpacity(0.3f);
        panelMap.setShadowSize(2);
        panelMap.setShadowType(ShadowType.TOP);
        panelMap.addEventTabSelected(new EventTabSelected() {
            @Override
            public boolean selected(int index, boolean selectedTab) {
                panelMap.showTabPane(index);
                loadMap(index);
                panelMap.setIndexShowing(index);
                panelMap.checkTab();
                return true;
            }
        });
        panelMap.createTabFloor(phong_DAO.getTang());
        panelMap.addEventRoom(new EventRoom() {
            @Override
            public HoaDon addBtnThueEvent(Phong phong) {
                try {
                    LookAndFeel previousLF = UIManager.getLookAndFeel();
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    DL_TiepNhanDatPhong dlDialog = new DL_TiepNhanDatPhong(phong, GD_Chinh.NHAN_VIEN);
                    dlDialog.setVisible(true);
                    loadMap(panelMap.getIndexShowing());
                    UIManager.setLookAndFeel(previousLF);
                    return dlDialog.getHoaDon();
                } catch (UnsupportedLookAndFeelException e) {
                    System.err.println(e);
                }
                return null;
            }

            @Override
            public void addBtnThemDichVuEvent(HoaDon hoaDon) {
                try {
                    LookAndFeel previousLF = UIManager.getLookAndFeel();
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    DL_CapNhatDichVu dialog = new DL_CapNhatDichVu(hoaDon, GD_Chinh.NHAN_VIEN);
                    dialog.setVisible(true);
                    loadMap(panelMap.getIndexShowing());
                    UIManager.setLookAndFeel(previousLF);
                } catch (UnsupportedLookAndFeelException e) {
                    System.err.println(e);
                }
            }

            @Override
            public void addBtnThanhToanEvent(HoaDon hoaDon) {
                try {
                    LookAndFeel previousLF = UIManager.getLookAndFeel();
                    UIManager.setLookAndFeel(new FlatLightLaf());
                    DL_ThanhToan dialog = new DL_ThanhToan(hoaDon, GD_Chinh.NHAN_VIEN);
                    dialog.setVisible(true);
                    loadMap(panelMap.getIndexShowing());
                    UIManager.setLookAndFeel(previousLF);
                } catch (UnsupportedLookAndFeelException e) {
                    System.err.println(e);
                }
            }

            @Override
            public void addBtnDonXongEvent(Phong phong) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn đã dọn xong?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    phong.setTrangThai(TrangThaiPhong.TRONG);
                    phong_DAO.updatePhong(phong);
                    loadMap(panelMap.getIndexShowing());
                }
            }

            @Override
            public void addBtnSuaPhongEvent(Phong phong) {
                phong.setTrangThai(TrangThaiPhong.DANG_SUA);
                phong_DAO.updatePhong(phong);
                loadMap(panelMap.getIndexShowing());
            }

            @Override
            public void addBtnDonPhongEvent(Phong phong) {
                phong.setTrangThai(TrangThaiPhong.DANG_DON);
                phong_DAO.updatePhong(phong);
                loadMap(panelMap.getIndexShowing());
            }

            @Override
            public void addBtnSuaXongEvent(Phong phong) {
                phong.setTrangThai(TrangThaiPhong.TRONG);
                phong_DAO.updatePhong(phong);
                loadMap(panelMap.getIndexShowing());
            }

        });
        loadMap(0);
        txtSearch = new MyTextField();
        txtSearch.setBorderLine(true);
        txtSearch.setBorderRadius(30);
        txtSearch.setHint("Tìm kiếm...");
        txtSearch.setBackgroundColor(Color.WHITE);
        txtSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                txtTenPhong.setText("");
                cbLoaiPhong.setSelectedIndex(0);
                String search = txtSearch.getText().trim();
                List<Phong> dsPhong = phong_DAO.getDsPhongBySDTOrTen(search, panelMap.getIndexShowing());
                panelMap.loadMap(dsPhong, panelMap.getIndexShowing());
                txtSearch.setText("");
            }
        });
        txtSearch.setPrefixIcon(new ImageIcon(getClass().getResource("/icon/search_25px.png")));
        panelMap.getTabPane().add(txtSearch, "pos 0.98al 0.4al n n, w 25%, h 35!");
        panelMap.setPreferredSize(new Dimension(getWidth(), getHeight() + 800));
        return panelMap;
    }

    private void loadMap(int index) {
        String tenPhong = txtTenPhong.getText().trim();
        LoaiPhong loaiPhong = null;
        if (!cbLoaiPhongModel.getSelectedItem().toString().equals("--Tất cả--")) {
            loaiPhong = (LoaiPhong) cbLoaiPhongModel.getSelectedItem();
        }
        List<Phong> dsPhong = phong_DAO.getDsPhongByTang(index, tenPhong, loaiPhong);
        System.out.println(dsPhong);
        panelMap.loadMap(dsPhong, index);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 5));

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(2);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

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
