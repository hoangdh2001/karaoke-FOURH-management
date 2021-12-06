package gui;

import com.toedter.calendar.JDateChooser;
import dao.PhieuDatPhong_DAO;
import entity.PhieuDatPhong;
import entity.TrangThaiPhieuDat;
import gui.swing.graphics.ShadowType;
import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.table2.EventAction;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

import gui.swing.event.EventSelectedRow;
import gui.swing.model.ModelAction;
import gui.swing.panel.slideshow.EventPagination;
import gui.swing.textfield.MyTextFieldFlatlaf;
import java.util.Date;
import javax.swing.JComboBox;

/**
 *
 * @author Hao
 */

public class GD_QLDatPhong extends javax.swing.JPanel implements ActionListener, KeyListener {

    private PhieuDatPhong_DAO phieuDatPhong_Dao;
    private List<PhieuDatPhong> dsPhieu = new ArrayList<PhieuDatPhong>();
    private MyTextFieldFlatlaf txtTimKiemKhachHang, txtTimKiemPhong;
    private Button btnLamMoi;
    private JDateChooser dcsNgayDatTK;
    JComboBox<String> cmbTrangThaiTK;

    private EventSelectedRow eventSelectedRow;
    private PanelShadow panelHidden;
    private EventAction event;

    /**
     * Creates new form GD_QLDatPhong
     */
    public GD_QLDatPhong() {

        initComponents();
        phieuDatPhong_Dao = new PhieuDatPhong_DAO();
        buildGD_QLDatPhong();

    }

    public void addEvent(EventSelectedRow event) {
        this.eventSelectedRow = event;
    }

    private void buildGD_QLDatPhong() {
        createPanelForm();
        createTable();
        createPanelBottom();
        createPanelHidden();
        add(panelHidden);
    }

    private void createPanelHidden() {
        panelHidden = new PanelShadow();
        panelHidden.setShadowType(ShadowType.CENTER);
        panelHidden.setShadowOpacity(0.3f);
    }

    private void createPanelForm() {
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font14 = 14;
        Color colorBtn = new Color(184, 238, 241);

        pnlTop.setLayout(new MigLayout("fill", "push[center]10[center]20[center]20[center]5[center]30[center]10[center]push", "60[center]20[center]20[]push"));

        txtTimKiemPhong = new MyTextFieldFlatlaf();
        txtTimKiemPhong.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiemPhong.setHint("Nhập tên phòng");
        pnlTop.add(txtTimKiemPhong, "w 15%, h 30!");

        txtTimKiemKhachHang = new MyTextFieldFlatlaf();
        txtTimKiemKhachHang.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiemKhachHang.setHint("Nhập tên khách hàng");
        pnlTop.add(txtTimKiemKhachHang, "w 15%, h 30!");

        cmbTrangThaiTK = new JComboBox<>();//cbModel
        cmbTrangThaiTK.setFont(new Font(fontName, fontPlain, font14));
        cmbTrangThaiTK.addItem("Lọc theo trạng thái");
        pnlTop.add(cmbTrangThaiTK, "w 15%, h 30!");

        // Tìm kiếm Ngày đặt
        JLabel lblNgayDaLabelTK = new JLabel("Tìm theo ngày đặt");
        lblNgayDaLabelTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTop.add(lblNgayDaLabelTK);

        dcsNgayDatTK = new JDateChooser();
        dcsNgayDatTK.setFont(new Font(fontName, fontPlain, font14));
        dcsNgayDatTK.setOpaque(false);
        pnlTop.add(dcsNgayDatTK, "w 15%, h 30!");

        // Nút Làm mới
        btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontPlain, font14));
        btnLamMoi.setBackground(colorBtn);
        btnLamMoi.setBorderRadius(5);
        btnLamMoi.setBorderline(true);
        pnlTop.add(btnLamMoi, "w 100!, h 36!");

        pnlTop.add(createPanelTitle(), "pos 0al 0al 100% n, h 40!");

        setOpaque(false);
        loadDataToCombobox();
        xuLyTimKiem();
    }

    private void xuLyTimKiem() {
        cmbTrangThaiTK.addActionListener(this);
        btnLamMoi.addActionListener(this);
        txtTimKiemPhong.addKeyListener(this);
        txtTimKiemKhachHang.addKeyListener(this);
        dcsNgayDatTK.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent arg0) {
                Date ngayDat = dcsNgayDatTK.getDate();
                String tenPhong = txtTimKiemPhong.getText().trim();
                String tenKhachHang = txtTimKiemKhachHang.getText().trim();
                String trangThai = kiemTraTrangThai(cmbTrangThaiTK.getSelectedItem().toString());
                if (dcsNgayDatTK.getDate() != null) {
                    dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat, pnlPage.getCurrentIndex());
                    xoaDuLieu();
                    loadPage();
                    taiLaiDuLieu(dsPhieu);
                }
            }
        });

        tblPhieuDatPhong.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Nếu click chuột trái 2 lần
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {
                    int row = tblPhieuDatPhong.getSelectedRow();
                    String maPhieu = tblPhieuDatPhong.getValueAt(row, 1).toString();
                    eventSelectedRow.selectedRow(phieuDatPhong_Dao.getPhieuDatPhong(maPhieu));
                }
            }
        });
    }

    private void loadDataToCombobox() {
        for (TrangThaiPhieuDat trangThaiPhieuDat : TrangThaiPhieuDat.values()) {
            cmbTrangThaiTK.addItem(trangThaiPhieuDat.getTrangThai());
        }
    }

    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Quản lý đặt phòng");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return pnlTitle;
    }

    private void createTable() {
        tblPhieuDatPhong.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 14));
        event = new EventAction() {
            @Override
            public void delete(Object obj) {
                int row = tblPhieuDatPhong.getSelectedRow();
                String maPhieu = String.valueOf(((DefaultTableModel) tblPhieuDatPhong.getModel()).getValueAt(row, 1));
                PhieuDatPhong phieu = phieuDatPhong_Dao.getPhieuDatPhong(maPhieu);
                if (JOptionPane.showConfirmDialog(GD_QLDatPhong.this, "Bạn có chắc muốn hủy phiếu " + phieu.getMaPhieuDat() + " không?", "Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    if (phieuDatPhong_Dao.capNhatTrangThaiPhieu(phieu.getMaPhieuDat())) {
                        JOptionPane.showMessageDialog(GD_QLDatPhong.this, "Bạn đã hủy thành công phiếu " + phieu.getMaPhieuDat());
                        dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong(pnlPage.getCurrentIndex());
                        xoaDuLieu();
                        taiLaiDuLieu(dsPhieu);
                    } else {
                        JOptionPane.showMessageDialog(GD_QLDatPhong.this, "Phiếu " + phieu.getMaPhieuDat() + " không thể hủy.");
                    }
                }
            }

            @Override
            public void update(ModelAction action) {
                System.out.println(".update()");
                int row = tblPhieuDatPhong.getSelectedRow();
                String maPhieu = tblPhieuDatPhong.getValueAt(row, 1).toString();
            }
        };
        loadData(pnlPage.getCurrentIndex());
    }

    public void xoaDuLieu() {
        DefaultTableModel df = (DefaultTableModel) tblPhieuDatPhong.getModel();
        df.setRowCount(0);
        tblPhieuDatPhong.clearSelection();
    }

    public void taiLaiDuLieu(List<PhieuDatPhong> dsPhieu) {
        ((DefaultTableModel) tblPhieuDatPhong.getModel()).setRowCount(0);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (dsPhieu != null) {
                    dsPhieu.forEach((phieu) -> {
                        ((DefaultTableModel) tblPhieuDatPhong.getModel()).addRow(phieu.convertToRowTable(event));
                    });
                }
                tblPhieuDatPhong.repaint();
                tblPhieuDatPhong.revalidate();
            }
        }).start();
    }

    private void loadData(int numPage) {
        ((DefaultTableModel) tblPhieuDatPhong.getModel()).setRowCount(0);
        dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong(numPage);
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (dsPhieu != null) {
                    for (PhieuDatPhong phieu : dsPhieu) {
                        ((DefaultTableModel) tblPhieuDatPhong.getModel()).addRow(phieu.convertToRowTable(event));
                    }
                }
                tblPhieuDatPhong.repaint();
                tblPhieuDatPhong.revalidate();
         }
        }).start();
    }
    
    private void loadPage() {
        Date ngayDat = dcsNgayDatTK.getDate();
        String tenPhong = txtTimKiemPhong.getText().trim();
        String tenKhachHang = txtTimKiemKhachHang.getText().trim();
        String trangThai = kiemTraTrangThai(cmbTrangThaiTK.getSelectedItem().toString());
        int soLuongPhieu= phieuDatPhong_Dao.getSoLuongPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat);
        pnlPage.init(soLuongPhieu% 20 == 0 ? soLuongPhieu / 20 : (soLuongPhieu / 20) + 1);
    }

    private String kiemTraTrangThai(String trangThaiPhieu) {
        TrangThaiPhieuDat trangThaiPhieuDat = TrangThaiPhieuDat.getTrangThaiPhieuDatByTrangThai(trangThaiPhieu);
        if (trangThaiPhieuDat == null) {
            return "";
        }
        return String.valueOf(trangThaiPhieuDat);

    }

    private void createPanelBottom() {
        pnlPage.addEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                loadData(pageClick);
            }
        });
        loadPage();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        srcPhieuDatPhong = new javax.swing.JScrollPane();
        tblPhieuDatPhong = new gui.swing.table2.MyTableFlatlaf();
        pnlBottom_Page = new javax.swing.JPanel();
        pnlPage = new gui.swing.table2.PanelPage();

        setOpaque(false);

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 106, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setLayout(new java.awt.BorderLayout());

        tblPhieuDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã phiếu đặt", "Ngày lập phiếu", "Khách hàng", "Phòng", "Ngày đặt", "Trạng thái", "Tiền cọc", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieuDatPhong.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        tblPhieuDatPhong.setRowHeight(40);
        tblPhieuDatPhong.setSelectionBackground(new java.awt.Color(239, 244, 255));
        tblPhieuDatPhong.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblPhieuDatPhong.setShowGrid(true);
        tblPhieuDatPhong.setShowVerticalLines(false);
        srcPhieuDatPhong.setViewportView(tblPhieuDatPhong);
        if (tblPhieuDatPhong.getColumnModel().getColumnCount() > 0) {
            tblPhieuDatPhong.getColumnModel().getColumn(0).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblPhieuDatPhong.getColumnModel().getColumn(1).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(1).setPreferredWidth(60);
            tblPhieuDatPhong.getColumnModel().getColumn(2).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(2).setPreferredWidth(100);
            tblPhieuDatPhong.getColumnModel().getColumn(3).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(3).setPreferredWidth(150);
            tblPhieuDatPhong.getColumnModel().getColumn(4).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(5).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblPhieuDatPhong.getColumnModel().getColumn(6).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(7).setResizable(false);
            tblPhieuDatPhong.getColumnModel().getColumn(8).setResizable(false);
        }

        pnlBottom.add(srcPhieuDatPhong, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout pnlBottom_PageLayout = new javax.swing.GroupLayout(pnlBottom_Page);
        pnlBottom_Page.setLayout(pnlBottom_PageLayout);
        pnlBottom_PageLayout.setHorizontalGroup(
            pnlBottom_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottom_PageLayout.createSequentialGroup()
                .addComponent(pnlPage, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 640, Short.MAX_VALUE))
        );
        pnlBottom_PageLayout.setVerticalGroup(
            pnlBottom_PageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPage, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        pnlBottom.add(pnlBottom_Page, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow pnlBottom;
    private javax.swing.JPanel pnlBottom_Page;
    private gui.swing.table2.PanelPage pnlPage;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane srcPhieuDatPhong;
    private gui.swing.table2.MyTableFlatlaf tblPhieuDatPhong;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj.equals(cmbTrangThaiTK)) {
            Date ngayDat = dcsNgayDatTK.getDate();
            String tenPhong = txtTimKiemPhong.getText().trim();
            String tenKhachHang = txtTimKiemKhachHang.getText().trim();
            String trangThai = kiemTraTrangThai(cmbTrangThaiTK.getSelectedItem().toString());
            System.out.println(trangThai);
            dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat, pnlPage.getCurrentIndex());
            xoaDuLieu();
            loadPage();
            taiLaiDuLieu(dsPhieu);
        }
        if (obj.equals(btnLamMoi)) {
            txtTimKiemPhong.setText("");
            txtTimKiemKhachHang.setText("");
            String tk = (String) cmbTrangThaiTK.getItemAt(0);
            cmbTrangThaiTK.setSelectedItem(tk);
            dcsNgayDatTK.setDate(null);
            dsPhieu = phieuDatPhong_Dao.getDsPhieuDatPhong(pnlPage.getCurrentIndex());
            xoaDuLieu();
            loadPage();
            taiLaiDuLieu(dsPhieu);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        Object obj = e.getSource();
        if (obj.equals(txtTimKiemPhong)) {
            Date ngayDat = dcsNgayDatTK.getDate();
            String tenPhong = txtTimKiemPhong.getText().trim();
            String tenKhachHang = txtTimKiemKhachHang.getText().trim();
            String trangThai = kiemTraTrangThai(cmbTrangThaiTK.getSelectedItem().toString());
            dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat, pnlPage.getCurrentIndex());
            xoaDuLieu();
            loadPage();
            taiLaiDuLieu(dsPhieu);
        }

        if (obj.equals(txtTimKiemKhachHang)) {
            Date ngayDat = dcsNgayDatTK.getDate();
            String tenPhong = txtTimKiemPhong.getText().trim();
            String tenKhachHang = txtTimKiemKhachHang.getText().trim();
            String trangThai = kiemTraTrangThai(cmbTrangThaiTK.getSelectedItem().toString());
            dsPhieu = phieuDatPhong_Dao.timDSPhieuDatPhongByAllProperty(tenPhong, tenKhachHang, trangThai, ngayDat, pnlPage.getCurrentIndex());
            xoaDuLieu();
            loadPage();
            taiLaiDuLieu(dsPhieu);

        }

    }

}
