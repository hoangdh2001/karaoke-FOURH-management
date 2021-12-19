package gui;

import dao.LoaiPhong_DAO;
import dao.Phong_DAO;
import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.dialog.DL_TaoPhong;
import gui.swing.button.Button;

import gui.swing.table.EventAction;
import gui.swing.model.ModelAction;
import gui.swing.event.EventPagination;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.miginfocom.swing.MigLayout;

public class GD_DanhSachPhong extends JPanel {

    private final Phong_DAO phong_DAO;
    private final LoaiPhong_DAO loaiPhong_DAO;
    private EventAction eventAction;
    private JTextField txtTenPhong;
    private DefaultComboBoxModel<Object> loaiphongModel;
    private JComboBox<Object> cmbLoaiPhong;
    private List<LoaiPhong> dsLoaiPhong;

    public GD_DanhSachPhong() {
        phong_DAO = new Phong_DAO();
        loaiPhong_DAO = new LoaiPhong_DAO();
        initComponents();
        buildGD();

    }

    private void buildGD() {
        createPanelTop();
        createPanelBottom();
        createTable();
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

        txtTenPhong = new JTextField();
        txtTenPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlTop.add(txtTenPhong, "w 20%, h 30!");

        //Tên phòng
        JLabel lblLoaiPhong = new JLabel("Loại phòng");
        lblLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlTop.add(lblLoaiPhong);

        loaiphongModel = new DefaultComboBoxModel<>();
        cmbLoaiPhong = new JComboBox<>(loaiphongModel);
        cmbLoaiPhong.addItem("--Tất cả--");
        cmbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        pnlTop.add(cmbLoaiPhong, "w 20%, h 30!");

        /**
         * end: group thông tin phòng hát
         */
        pnlTop.add(createPanelTitle(), "pos 0al 0al 100% n, h 40!");
        actionGroup();
        loadDataForm();
    }

    private void loadDataForm() {
        dsLoaiPhong = loaiPhong_DAO.getDsLoaiPhong();
        for (LoaiPhong loaiPhong : dsLoaiPhong) {
            loaiphongModel.addElement(loaiPhong.getTenLoaiPhong());
        }
    }

    private void actionGroup() {
        txtTenPhong.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                loadData(0);
                loadPage();
            }
        });

        cmbLoaiPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                loadData(0);
                loadPage();
            }
        });
    }

    private void createTable() {
        table.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 14));
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
                    try {
                        phong.setTenPhong(table.getValueAt(table.getSelectedRow(), 2).toString());
                        String s = phong_DAO.updatePhong(phong) == true ? "Cập nhật thành công" : "Cập nhật thất bại";
                        JOptionPane.showMessageDialog(GD_DanhSachPhong.this, s);
                        DefaultTableModel df = (DefaultTableModel) table.getModel();
                        df.getDataVector().removeAllElements();
                        table.clearSelection();
                        loadData(pnlPage.getCurrentIndex());
                    } catch (Exception ex) {
                        Logger.getLogger(GD_DanhSachPhong.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        loadData(pnlPage.getCurrentIndex());
        TableColumnModel tableColumnModel = table.getColumnModel();
        DefaultComboBoxModel<String> clLoaiPhongModel = new DefaultComboBoxModel<>();
        for (LoaiPhong loaiPhong : loaiPhong_DAO.getDsLoaiPhong()) {
            clLoaiPhongModel.addElement(loaiPhong.getTenLoaiPhong());
        }
        JComboBox<String> cmbColumnLoaiPhong = new JComboBox<>(clLoaiPhongModel);
        cmbColumnLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 14));
        cmbColumnLoaiPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                if (table.getSelectedRow() != -1) {
                    String maPhong = table.getValueAt(table.getSelectedRow(), 1).toString();
                    Phong phong = phong_DAO.getPhong(maPhong);

                    LoaiPhong loaiPhong = loaiPhong_DAO.getLoaiPhongByName(cmbColumnLoaiPhong.getSelectedItem().toString());
                    if (!phong.getLoaiPhong().getMaLoaiPhong().equals(loaiPhong.getMaLoaiPhong())) {
                        if (phong.getTrangThai() != TrangThaiPhong.DANG_HAT) {
                            try {
                                phong.setLoaiPhong(loaiPhong);
                                phong_DAO.updatePhong(phong);
                            } catch (Exception ex) {
                                Logger.getLogger(GD_DanhSachPhong.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Phòng đang hát cập nhật sau!");

                        }
                        loadData(pnlPage.getCurrentIndex());
                    }
                }
            }
        });
        tableColumnModel.getColumn(5).setCellEditor(new DefaultCellEditor(cmbColumnLoaiPhong));
    }

    private void loadData(int numPage) {
        ((DefaultTableModel) table.getModel()).setRowCount(0);
        phong_DAO.updatePhongByPhieu();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String tenPhong = txtTenPhong.getText().trim();
                String loaiPhong = "";
                if (!loaiphongModel.getSelectedItem().toString().equals("--Tất cả--")) {
                    loaiPhong = dsLoaiPhong.get(cmbLoaiPhong.getSelectedIndex() - 1).getMaLoaiPhong();
                }
                List<Phong> dsPhong = phong_DAO.getDsPhong(numPage, tenPhong, loaiPhong);
                if (dsPhong != null) {
                    for (Phong phong : dsPhong) {
                        ((DefaultTableModel) table.getModel()).addRow(new Object[]{new JCheckBox(),
                            phong.getMaPhong(),
                            phong.getTenPhong(),
                            phong.getTang(),
                            phong.getTrangThai(),
                            phong.getLoaiPhong().getTenLoaiPhong(),
                            new ModelAction(phong, eventAction)});
                    }
                }
                table.repaint();
                table.revalidate();
            }
        }).start();

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

        Button open = new Button();
        open.setToolTipText("Mở rộng");
        open.setIcon(new ImageIcon(getClass().getResource("/icon/more_15px.png")));

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem mniTaoPhong = new JMenuItem("Tạo phòng");

        mniTaoPhong.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                new DL_TaoPhong(GD_Chinh.FRAME, true).setVisible(true);
                loadData(pnlPage.getCurrentIndex());
            }
        });
        popupMenu.add(mniTaoPhong);

        JMenuItem mniLamMoi = new JMenuItem("Làm mới");
        mniLamMoi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                txtTenPhong.setText("");
                txtTenPhong.requestFocus();
                cmbLoaiPhong.setSelectedIndex(0);
            }
        });
        popupMenu.add(mniLamMoi);

        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                popupMenu.show(open, 0, open.getHeight());
                popupMenu.setPopupSize(popupMenu.getWidth(), 60);
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
        loadPage();
    }

    private void loadPage() {
        String tenPhong = txtTenPhong.getText().trim();
        String loaiPhong = loaiphongModel.getSelectedItem() instanceof LoaiPhong ? ((LoaiPhong) loaiphongModel.getSelectedItem()).getMaLoaiPhong() : "";
        int soLuongPhong = phong_DAO.getSoLuongPhong(tenPhong, loaiPhong);
        pnlPage.init(soLuongPhong % 20 == 0 ? soLuongPhong / 20 : (soLuongPhong / 20) + 1);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        sp = new javax.swing.JScrollPane();
        table = new gui.swing.table.MyTableFlatlaf();
        jPanel1 = new javax.swing.JPanel();
        pnlPage = new gui.swing.table.PanelPage();

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
                "", "Mã phòng", "Tên phòng", "Tầng", "Trạng thái", "Loại phòng", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

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
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(0).setPreferredWidth(20);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(1).setPreferredWidth(60);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(2).setPreferredWidth(200);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(4).setPreferredWidth(150);
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
    private gui.swing.table.PanelPage pnlPage;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane sp;
    private gui.swing.table.MyTableFlatlaf table;
    // End of variables declaration//GEN-END:variables
}
