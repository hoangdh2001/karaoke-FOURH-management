package gui;

import dao.LoaiPhong_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.HoaDon;
import entity.LoaiPhong;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhieuDat;
import entity.TrangThaiPhong;
import gui.component.PanelMap;
import gui.component.PanelStatus;
import gui.dialog.DL_CapNhatDichVu;
import gui.dialog.DL_DatPhong;
import gui.dialog.DL_DoiPhong;
import gui.dialog.DL_ThanhToan;
import gui.dialog.DL_TiepNhanDatPhong;
import gui.swing.graphics.ShadowType;
import gui.swing.button.Button;
import gui.swing.event.EventRoom;
import gui.swing.textfield.MyTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseWheelListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import gui.swing.event.EventShowInfoOver;
import gui.swing.event.EventTabSelected;
import gui.swing.textfield.MyTextFieldFlatlaf;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import service.LoaiPhongService;
import service.PhongService;

public class GD_SoDoPhongHat extends javax.swing.JPanel {

    private PanelMap panelMap;
    private PhongService phongService;
    private LoaiPhongService loaiPhongService;
    private DefaultComboBoxModel<Object> cbLoaiPhongModel;
    private DefaultComboBoxModel<TrangThaiPhong> cbTrangThaiModel;
    private MyTextField txtSearch;
    private MyTextFieldFlatlaf txtTenPhong;
    private JComboBox<Object> cbLoaiPhong;
    private List<LoaiPhong> dsLoaiPhong;

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
        phongService = new Phong_DAO();
        loaiPhongService = new LoaiPhong_DAO();

        initComponents();
        buildGD();

    }

    private void buildGD() {
        pnlTop.setLayout(new MigLayout("fill, insets 0, wrap", "0[fill]0", "0[fill]0[fill]0[fill]0"));

        pnlTop.add(createPanelTitle(), "h 50!");

        pnlTop.add(createPanelForm());

        pnlTop.add(createPaneStatus(), "h 50!");

        add(panelMap(), BorderLayout.CENTER);

    }

    private JPanel createPanelForm() {
        JPanel pnlForm = new JPanel();
        pnlForm.setOpaque(false);
        pnlForm.setLayout(new MigLayout("fill", "push[center]20[center]push", "35[center]15[center]35"));
        txtTenPhong = new MyTextFieldFlatlaf();
        txtTenPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        txtTenPhong.setHint("Nhập tên phòng...");
        pnlForm.add(txtTenPhong, "w 25%, h 30!");

        cbLoaiPhongModel = new DefaultComboBoxModel<>();
        cbLoaiPhong = new JComboBox<>(cbLoaiPhongModel);
        cbLoaiPhong.addItem("--Tất cả--");
        cbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlForm.add(cbLoaiPhong, "w 25%, h 30!, wrap");

        JButton timKiemBtn = new JButton("Tìm kiếm");
        timKiemBtn.setFont(new Font("sansserif", Font.BOLD, 12));
        timKiemBtn.setForeground(Color.WHITE);
        timKiemBtn.setBackground(new Color(54, 88, 153));
        timKiemBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadMap(panelMap.getIndexShowing());
            }

        });
        pnlForm.add(timKiemBtn, "skip 1, w 90!, h 30!, right");

        loadDataForm();

        return pnlForm;
    }

    private void loadDataForm() {
        dsLoaiPhong = loaiPhongService.getDsLoaiPhong();
        for (LoaiPhong loaiPhong : dsLoaiPhong) {
            cbLoaiPhongModel.addElement(loaiPhong.getTenLoaiPhong());
        }
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
        Button open = new Button();
        open.setToolTipText("Mở rộng");
        open.setIcon(new ImageIcon(getClass().getResource("/icon/more_15px.png")));

        JPopupMenu popupMenu = new JPopupMenu();

        JMenuItem mniDatPhong = new JMenuItem("Đặt phòng");
        mniDatPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new DL_DatPhong(Application.login).setVisible(true);
                loadMap(panelMap.getIndexShowing());
            }
        });
        popupMenu.add(mniDatPhong);

        JMenuItem mniLamMoi = new JMenuItem("Làm mới");
        mniLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                txtTenPhong.setText("");
                txtTenPhong.requestFocus();
                cbLoaiPhong.setSelectedIndex(0);
                loadMap(panelMap.getIndexShowing());
            }
        });
        popupMenu.add(mniLamMoi);

        JMenuItem mniHuongDan = new JMenuItem("Hướng dẫn");
        popupMenu.add(mniHuongDan);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                popupMenu.show(open, 0, open.getHeight());
                popupMenu.setPopupSize(popupMenu.getWidth(), 90);
            }
        });

        pnlTitle.add(open, "pos 1al 0al n n, w 30!, h 30!");
        pnlTitle.add(lblTitle);
        return pnlTitle;
    }

    private JPanel createPaneStatus() {
        JPanel pnlStatus = new JPanel();
        pnlStatus.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(0, 0, 0, 0.1f)));
        pnlStatus.setOpaque(false);
        pnlStatus.setLayout(new MigLayout("fill", "", ""));

        PanelStatus pnlTrong = new PanelStatus(TrangThaiPhong.TRONG);
        pnlTrong.setText(phongService.getSoLuongPhongTheoTrangThai(TrangThaiPhong.TRONG));
        pnlStatus.add(pnlTrong);

        PanelStatus pnlDangHat = new PanelStatus(TrangThaiPhong.DANG_HAT);
        pnlDangHat.setText(phongService.getSoLuongPhongTheoTrangThai(TrangThaiPhong.DANG_HAT));
        pnlStatus.add(pnlDangHat);

        PanelStatus pnlDatTruoc = new PanelStatus(TrangThaiPhong.DAT_TRUOC);
        pnlDatTruoc.setText(phongService.getSoLuongPhongTheoTrangThai(TrangThaiPhong.DAT_TRUOC));
        pnlStatus.add(pnlDatTruoc);

        PanelStatus pnlBan = new PanelStatus(TrangThaiPhong.BAN);
        pnlBan.setText(phongService.getSoLuongPhongTheoTrangThai(TrangThaiPhong.BAN));
        pnlStatus.add(pnlBan);

        PanelStatus pnlDangDon = new PanelStatus(TrangThaiPhong.DANG_DON);
        pnlDangDon.setText(phongService.getSoLuongPhongTheoTrangThai(TrangThaiPhong.DANG_DON));
        pnlStatus.add(pnlDangDon);

        PanelStatus pnlDangSua = new PanelStatus(TrangThaiPhong.DANG_SUA);
        pnlDangSua.setText(phongService.getSoLuongPhongTheoTrangThai(TrangThaiPhong.DANG_SUA));
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
        panelMap.createTabFloor(phongService.getTang());
        panelMap.addEventRoom(new EventRoom() {
            @Override
            public HoaDon addBtnThueEvent(Phong phong) {
                List<PhieuDatPhong> dsPhieuDatPhong = new PhieuDatPhong_DAO().getDsPhieuDatByPhong(phong.getMaPhong());
                System.out.println(dsPhieuDatPhong.size());
                if (dsPhieuDatPhong.isEmpty()) {
                    DL_TiepNhanDatPhong dlDialog = new DL_TiepNhanDatPhong(Application.login, phong, GD_Chinh.NHAN_VIEN);
                    dlDialog.setVisible(true);
                    loadMap(panelMap.getIndexShowing());
                    return dlDialog.getHoaDon();
                } else {
                    if (JOptionPane.showConfirmDialog(null, "Còn khoảng hơn 2 tiếng sẽ có người đặt bạn có đồng ý cho thuê?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        DL_TiepNhanDatPhong dlDialog = new DL_TiepNhanDatPhong(Application.login, phong, GD_Chinh.NHAN_VIEN);
                        dlDialog.setVisible(true);
                        loadMap(panelMap.getIndexShowing());
                        return dlDialog.getHoaDon();
                    } else {
                        return null;
                    }
                }
            }

            @Override
            public void addBtnThemDichVuEvent(HoaDon hoaDon) {
                DL_CapNhatDichVu dialog = new DL_CapNhatDichVu(Application.login, hoaDon, GD_Chinh.NHAN_VIEN);
                dialog.setVisible(true);
                loadMap(panelMap.getIndexShowing());
            }

            @Override
            public void addBtnThanhToanEvent(HoaDon hoaDon) {
                DL_ThanhToan dialog = new DL_ThanhToan(Application.login, hoaDon, GD_Chinh.NHAN_VIEN);
                dialog.setVisible(true);
                loadMap(panelMap.getIndexShowing());
            }

            @Override
            public void addBtnDonXongEvent(Phong phong) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn đã dọn xong?", "Thông báo", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    phong.setTrangThai(TrangThaiPhong.TRONG);
                    phongService.updatePhong(phong);
                    loadMap(panelMap.getIndexShowing());
                }
            }

            @Override
            public void addBtnSuaPhongEvent(Phong phong) {
                phong.setTrangThai(TrangThaiPhong.DANG_SUA);
                phongService.updatePhong(phong);
                loadMap(panelMap.getIndexShowing());
            }

            @Override
            public void addBtnDonPhongEvent(Phong phong) {
                phong.setTrangThai(TrangThaiPhong.DANG_DON);
                phongService.updatePhong(phong);
                loadMap(panelMap.getIndexShowing());
            }

            @Override
            public void addBtnSuaXongEvent(Phong phong) {
                phong.setTrangThai(TrangThaiPhong.TRONG);
                phongService.updatePhong(phong);
                loadMap(panelMap.getIndexShowing());
            }

            @Override
            public void addBtnDoiPhongEvent(Phong phong, HoaDon hoaDon) {
                new DL_DoiPhong(Application.login, hoaDon).setVisible(true);
                loadMap(panelMap.getIndexShowing());
            }

            @Override
            public void addBtnHuyEvent(Phong phong, PhieuDatPhong phieuDatPhong) {
                if (JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn hủy phiếu " + phieuDatPhong.getMaPhieuDat() + " không?", "Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    phieuDatPhong.setTrangThai(TrangThaiPhieuDat.DA_HUY);
                    phong.setTrangThai(TrangThaiPhong.TRONG);
                    if (new PhieuDatPhong_DAO().capNhatPhieuDatPhong(phieuDatPhong) && phongService.updatePhong(phong)) {
                        JOptionPane.showMessageDialog(null, "Bạn đã hủy thành công phiếu " + phieuDatPhong.getMaPhieuDat());
                        loadMap(panelMap.getIndexShowing());
                    } else {
                        JOptionPane.showMessageDialog(null, "Phiếu " + phieuDatPhong.getMaPhieuDat() + " hủy thất bại.");
                    }
                }
            }

            @Override
            public HoaDon addBtnThueEvent(Phong phong, PhieuDatPhong phieuDatPhong) {
                DL_TiepNhanDatPhong dlDialog = new DL_TiepNhanDatPhong(Application.login, phong, GD_Chinh.NHAN_VIEN);
                dlDialog.setPhieuDatPhong(phieuDatPhong);
                dlDialog.setVisible(true);
                loadMap(panelMap.getIndexShowing());
                return dlDialog.getHoaDon();
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
                List<Phong> dsPhong = phongService.getDsPhongBySDTOrTen(search, panelMap.getIndexShowing());
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
        phongService.updatePhongByPhieu();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String tenPhong = txtTenPhong.getText().trim();
                LoaiPhong loaiPhong = null;
                if (!cbLoaiPhongModel.getSelectedItem().toString().equals("--Tất cả--")) {
                    loaiPhong = dsLoaiPhong.get(cbLoaiPhong.getSelectedIndex() - 1);
                }
                List<Phong> dsPhong = phongService.getDsPhongByTang(index, tenPhong, loaiPhong);
                panelMap.loadMap(dsPhong, index);
            }
        }).start();
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
