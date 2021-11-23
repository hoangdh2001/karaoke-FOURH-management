package gui;

import dao.Phong_DAO;
import entity.LoaiPhong;
import entity.Phong;
import gui.dialog.DL_TaoPhong;
import gui.swing.button.ToggleButton;

import gui.swing.table2.EventAction;
import gui.swing.model.ModelAction;
import gui.swing.panel.slideshow.EventPagination;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class GD_DanhSachPhong extends JPanel {

    private final Phong_DAO phong_DAO;
    private EventAction eventAction;

    public GD_DanhSachPhong() {
        phong_DAO = new Phong_DAO();
        initComponents();
        buildGD();

    }

    private void buildGD() {
        createPanelTop();
        createTable();
        createPanelBottom();
    }

    private void createPanelTop() {
        pnlTop.setLayout(new MigLayout("fill", "push[center]10[center]20[center]10[]push", "60[center]20[center]20[]push"));

        /**
         * Begin: group thông tin phòng
         */
        // Mã phòng
        JLabel lblTenPhong = new JLabel("Tên phòng:");
        lblTenPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlTop.add(lblTenPhong);

        JTextField txtMaPhong = new JTextField();
        txtMaPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlTop.add(txtMaPhong, "w 20%, h 30!");

        //Tên phòng
        JLabel lblLoaiPhong = new JLabel("Loại phòng");
        lblLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlTop.add(lblLoaiPhong);

        JComboBox<String> cmbLoaiPhong = new JComboBox<>(new String[]{"--Tất cả--", "Phòng trống", "Phòng đang hát", "Phòng đặt trước"});
        cmbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlTop.add(cmbLoaiPhong, "w 20%, h 30!");

        /**
         * end: group thông tin phòng hát
         */
        pnlTop.add(createPanelTitle(), "pos 0al 0al 100% n, h 40!");
    }

    private void createTable() {
        table.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 14));
        LoaiPhong loaiPhong = new LoaiPhong();
        loaiPhong.setTenLoaiPhong("Phòng thường");
        eventAction = new EventAction() {
            @Override
            public void delete(Object obj) {
                String maPhong = String.valueOf(((DefaultTableModel) table.getModel()).getValueAt(table.getSelectedRow(), 1));
                Phong phong = phong_DAO.getPhong(maPhong);
                if (JOptionPane.showConfirmDialog(GD_DanhSachPhong.this, "Bạn có muốn xóa mã " + phong.getMaPhong(), "Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String s = phong_DAO.deletePhong(phong.getMaPhong()) == true ? "Xóa thành công mã " + phong.getMaPhong() : "Xóa thất bại mã " + phong.getMaPhong();
                    JOptionPane.showMessageDialog(GD_DanhSachPhong.this, s);
                    DefaultTableModel df = (DefaultTableModel) table.getModel();
                    df.setRowCount(0);
                    table.clearSelection();
                    loadData(pnlPage.getCurrentIndex());
                }
            }

            @Override
            public void update(ModelAction action) {
                Phong phong = (Phong) action.getObj();
                if (JOptionPane.showConfirmDialog(GD_DanhSachPhong.this, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    phong.setTenPhong(table.getValueAt(table.getSelectedRow(), 2).toString());
                    String s = phong_DAO.updatePhong(phong) == true ? "Cập nhật thành công" : "Cập nhật thất bại";
                    JOptionPane.showMessageDialog(GD_DanhSachPhong.this, s);
                    DefaultTableModel df = (DefaultTableModel) table.getModel();
                    df.getDataVector().removeAllElements();
                    table.clearSelection();
                    loadData(pnlPage.getCurrentIndex());
                }
            }
        };
        loadData(pnlPage.getCurrentIndex());
    }

    private void loadData(int numPage) {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        List<Phong> dsPhong = phong_DAO.getDsPhong(numPage);
        if (dsPhong != null) {
            dsPhong.forEach((phong) -> {
                ((DefaultTableModel) table.getModel()).addRow(phong.convertToRowTable(eventAction));
            });
        }
        table.repaint();
        table.revalidate();
    }

    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Danh sách phòng hát");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        ToggleButton open = new ToggleButton();
        open.setIcon(new ImageIcon(getClass().getResource("/icon/more_15px.png")));

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem menuItem = new JMenuItem("Thêm phòng");
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new DL_TaoPhong(GD_Chinh.FRAME, true).setVisible(true);
                loadData(pnlPage.getCurrentIndex());
            }
        });
        popupMenu.add(menuItem);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                popupMenu.show(open, 0, open.getHeight());
                popupMenu.setPopupSize(popupMenu.getWidth(), 30);
            }
        });

        pnlTitle.add(open, "pos 1al 0al n n, w 30!, h 30!");
        pnlTitle.add(lblTitle);
        return pnlTitle;
    }

    private void createPanelBottom() {
        pnlPage.addEventPagination(new EventPagination() {
            @Override
            public void onClick(int pageClick) {
                loadData(pageClick);
            }
        });
        int soLuongPhong = phong_DAO.getSoLuongPhong();
        pnlPage.init(soLuongPhong % 20 == 0 ? soLuongPhong / 20 : (soLuongPhong / 20) + 1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        sp = new javax.swing.JScrollPane();
        table = new gui.swing.table2.MyTableFlatlaf();
        jPanel1 = new javax.swing.JPanel();
        pnlPage = new gui.swing.table2.PanelPage();

        setOpaque(false);

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(2);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 91, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 3, 3, 3));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(2);
        pnlBottom.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlBottom.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã phòng", "Tên phòng", "Tầng", "Trạng thái", "Giá phòng", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        table.setRowHeight(40);
        table.setSelectionBackground(new java.awt.Color(239, 244, 255));
        table.setSelectionForeground(new java.awt.Color(51, 51, 51));
        table.setShowGrid(true);
        table.setShowVerticalLines(false);
        sp.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(60);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(300);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(200);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
            table.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        pnlBottom.add(sp, java.awt.BorderLayout.CENTER);

        jPanel1.setOpaque(false);

        pnlPage.setOpaque(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(pnlPage, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 776, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlPage, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
        );

        pnlBottom.add(jPanel1, java.awt.BorderLayout.PAGE_END);

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
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.table2.PanelPage pnlPage;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane sp;
    private gui.swing.table2.MyTableFlatlaf table;
    // End of variables declaration//GEN-END:variables
}
