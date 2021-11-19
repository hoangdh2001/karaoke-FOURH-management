package gui.dialog;

import dao.ChiTietHoaDon_DAO;
import dao.LoaiDichVu_DAO;
import dao.MatHang_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.MatHang;
import entity.NhanVien;
import gui.swing.event.EventAdd;
import gui.swing.event.EventMinus;
import gui.swing.image.WindowIcon;
import gui.swing.model.ModelAdd;
import gui.swing.table2.SpinnerEditor;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import service.ChiTietHoaDonService;
import service.LoaiDichVuService;
import service.MatHangService;

public class DL_DichVu extends javax.swing.JDialog {

    private final MatHangService matHangService;
    private final ChiTietHoaDonService chiTietHoaDonService;
    private final LoaiDichVuService loaiDichVuService;
    private final HoaDon hoaDon;
    private final DecimalFormat df = new DecimalFormat("#,##0");
    private List<MatHang> dsMatHang;
    private EventAdd event;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public DL_DichVu(HoaDon hoaDon, NhanVien nv) {
        this.matHangService = new MatHang_DAO();
        this.chiTietHoaDonService = new ChiTietHoaDon_DAO();
        this.loaiDichVuService = new LoaiDichVu_DAO();
        this.hoaDon = hoaDon;
        WindowIcon.addWindowIcon(this);
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        createTableMatHang();
        createTableCTHoaDon();
        loadDataForm();

    }

    private void createTableMatHang() {
        loadDataTableMatHang();
    }

    private void createTableCTHoaDon() {
        tableCTHoaDon.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor(200));
        loadDataTableCTHoaDon();
    }

    public LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private void loadDataForm() {
        
        if (hoaDon.getPhong() != null) {
            ((DefaultComboBoxModel) cbLoaiDichVu.getModel()).addAll(loaiDichVuService.getDsTenLoaiDichVu());
            txtTenPhong.setText(hoaDon.getPhong().getTenPhong());
            txtLoaiPhong.setText(hoaDon.getPhong().getLoaiPhong().getTenLoaiPhong());
            txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
        }
        if (hoaDon.getNhanVien() != null) {
            lblNhanVien.setText("Nhân viên: " + hoaDon.getNhanVien().getTenNhanVien());
            lblRole.setText(hoaDon.getNhanVien().getLoaiNhanVien().getTenLoaiNV());
        }
        
    }

    private void loadDataTableMatHang() {
        dsMatHang = matHangService.getDsMatHang();
        if (dsMatHang != null) {
            event = (Object obj) -> {
                MatHang matHang = (MatHang) obj;
                try {
                    matHang.setsLTonKho(matHang.getsLTonKho() - 1);
                    hoaDon.themCT_HoaDon(matHang, 1, 0);
                    loadDataTableCTHoaDon();
                    ((DefaultTableModel) tableMatHang.getModel()).setValueAt(matHang.getsLTonKho(), tableMatHang.getSelectedRow(), 1);
                    txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Mặt hàng này đã hết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            };

            dsMatHang.forEach(matHang -> {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(matHang.convertToRowTableInGDTiepNhanDatPhong(event));
            });
        }
    }

    private void loadDataTableCTHoaDon() {
        ((DefaultTableModel) tableCTHoaDon.getModel()).setRowCount(0);
        List<ChiTietHoaDon> dsChiTietHoaDon = hoaDon.getDsChiTietHoaDon();
        EventMinus eventMinus = new EventMinus() {
            @Override
            public void cancel() {
                try {
                    int row = tableCTHoaDon.getSelectedRow();
                    ChiTietHoaDon chiTietHoaDon = hoaDon.getDsChiTietHoaDon().get(row);
                    MatHang matHang = chiTietHoaDon.getMatHang();
                    matHang.setsLTonKho(matHang.getsLTonKho() + chiTietHoaDon.getSoLuong());
                    hoaDon.getDsChiTietHoaDon().remove(chiTietHoaDon);
                    loadDataTableCTHoaDon();
                    for (int i = 0; i < tableMatHang.getRowCount(); i++) {
                        ModelAdd data = (ModelAdd) ((DefaultTableModel) tableMatHang.getModel()).getValueAt(i, 3);
                        MatHang mh = (MatHang) data.getObj();
                        if (mh.equals(matHang)) {
                            mh.setsLTonKho(matHang.getsLTonKho());
                            ((DefaultTableModel) tableMatHang.getModel()).setValueAt(matHang.getsLTonKho(), i, 1);
                        }
                    }
                    txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
                } catch (Exception ex) {
                    Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void minus() {
                try {
                    int row = tableCTHoaDon.getSelectedRow();
                    ChiTietHoaDon chiTietHoaDon = hoaDon.getDsChiTietHoaDon().get(row);
                    MatHang matHang = chiTietHoaDon.getMatHang();
                    matHang.setsLTonKho(matHang.getsLTonKho() + 1);
                    if (chiTietHoaDon.getSoLuong() <= 1) {
                        hoaDon.getDsChiTietHoaDon().remove(chiTietHoaDon);
                    } else {
                        hoaDon.themCT_HoaDon(matHang, -1, 0);
                    }
                    loadDataTableCTHoaDon();
                    for (int i = 0; i < tableMatHang.getRowCount(); i++) {
                        ModelAdd data = (ModelAdd) ((DefaultTableModel) tableMatHang.getModel()).getValueAt(i, 3);
                        MatHang mh = (MatHang) data.getObj();
                        if (mh.equals(matHang)) {
                            mh.setsLTonKho(matHang.getsLTonKho());
                            ((DefaultTableModel) tableMatHang.getModel()).setValueAt(matHang.getsLTonKho(), i, 1);
                        }
                    }
                    txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
                } catch (Exception ex) {
                    Logger.getLogger(DL_DichVu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        dsChiTietHoaDon.forEach(chiTietHoaDon -> {
            ((DefaultTableModel) tableCTHoaDon.getModel()).addRow(chiTietHoaDon.convertToRowTableInTiepNhanHoaDon(eventMinus));
        });
    }

    private void searchMatHang() {
        ((DefaultTableModel) tableMatHang.getModel()).setRowCount(0);
        String tenMatHang = txtSearch.getText().trim();
        String tenLoaiDichVu = String.valueOf(cbLoaiDichVu.getSelectedItem());
        if (!tenLoaiDichVu.equals("Tất cả") && tenMatHang.length() > 0) {
            dsMatHang.stream().filter(matHang -> ((matHang.getTenMatHang().toLowerCase().contains(tenMatHang.toLowerCase())) && (matHang.getLoaiDichVu().getTenLoaiDichVu().equals(tenLoaiDichVu)))).forEachOrdered(matHang -> {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(matHang.convertToRowTableInGDTiepNhanDatPhong(event));
            });
        } else if (!tenLoaiDichVu.equals("Tất cả") || tenMatHang.length() > 0) {
            dsMatHang.forEach(matHang -> {
                if (!tenLoaiDichVu.equals("Tất cả")) {
                    System.out.println("ComboBox");
                    if (matHang.getLoaiDichVu().getTenLoaiDichVu().equals(tenLoaiDichVu)) {
                        ((DefaultTableModel) tableMatHang.getModel()).addRow(matHang.convertToRowTableInGDTiepNhanDatPhong(event));
                    }
                } else if (matHang.getTenMatHang().toLowerCase().contains(tenMatHang.toLowerCase())) {
                    System.out.println("text");
                    ((DefaultTableModel) tableMatHang.getModel()).addRow(matHang.convertToRowTableInGDTiepNhanDatPhong(event));
                }
            });
        } else {
            dsMatHang.forEach(matHang -> {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(matHang.convertToRowTableInGDTiepNhanDatPhong(event));
            });
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
        pnlMain = new javax.swing.JPanel();
        pnlBottomBar = new javax.swing.JPanel();
        btnGiaoPhong = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblNhanVien = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        pnlMatHang = new javax.swing.JPanel();
        txtSearch = new gui.swing.textfield.MyTextField();
        cbLoaiDichVu = new javax.swing.JComboBox<>();
        lblLoaiDichVu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMatHang = new gui.swing.table2.MyTableFlatlaf();
        pnlCenter = new javax.swing.JPanel();
        lblTenPhong = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        lblLoaiPhong = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHoaDon = new gui.swing.table2.MyTableFlatlaf();
        txtTongTienMatHang = new gui.swing.textfield.MyTextFieldPerUnit();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Cập nhật dịch vụ");
        setModal(true);
        setResizable(false);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlBottomBar.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottomBar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        btnGiaoPhong.setText("Lưu");
        btnGiaoPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnGiaoPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoPhongActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblNhanVien.setText("Nhân viên: Đỗ Huy Hoàng");
        lblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblRole.setText("Quản lý");
        lblRole.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRole.setForeground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout pnlBottomBarLayout = new javax.swing.GroupLayout(pnlBottomBar);
        pnlBottomBar.setLayout(pnlBottomBarLayout);
        pnlBottomBarLayout.setHorizontalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNhanVien)
                    .addComponent(lblRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 650, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnGiaoPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        pnlBottomBarLayout.setVerticalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGiaoPhong)
                    .addComponent(btnHuy))
                .addGap(12, 12, 12))
            .addGroup(pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblRole)
                .addGap(5, 5, 5))
        );

        pnlMain.add(pnlBottomBar, java.awt.BorderLayout.SOUTH);

        jSplitPane1.setBackground(new java.awt.Color(255, 255, 255));

        pnlMatHang.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)), "Cập nhật mặt hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlMatHang.setOpaque(false);

        txtSearch.setBackgroundColor(new java.awt.Color(255, 255, 255));
        txtSearch.setBorderLine(true);
        txtSearch.setHint("Tìm kiếm...");
        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N
        txtSearch.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSearchKeyReleased(evt);
            }
        });

        cbLoaiDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả" }));
        cbLoaiDichVu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiDichVuActionPerformed(evt);
            }
        });

        lblLoaiDichVu.setText("Loại dịch vụ");
        lblLoaiDichVu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tableMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên mặt hàng", "Tồn kho", "Giá bán", "Thêm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMatHang.setFillsViewportHeight(true);
        tableMatHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableMatHang.setRowHeight(30);
        tableMatHang.setShowGrid(true);
        tableMatHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableMatHang);
        if (tableMatHang.getColumnModel().getColumnCount() > 0) {
            tableMatHang.getColumnModel().getColumn(0).setPreferredWidth(200);
            tableMatHang.getColumnModel().getColumn(1).setResizable(false);
            tableMatHang.getColumnModel().getColumn(1).setPreferredWidth(60);
            tableMatHang.getColumnModel().getColumn(2).setResizable(false);
            tableMatHang.getColumnModel().getColumn(2).setPreferredWidth(100);
            tableMatHang.getColumnModel().getColumn(3).setResizable(false);
            tableMatHang.getColumnModel().getColumn(3).setPreferredWidth(60);
        }

        javax.swing.GroupLayout pnlMatHangLayout = new javax.swing.GroupLayout(pnlMatHang);
        pnlMatHang.setLayout(pnlMatHangLayout);
        pnlMatHangLayout.setHorizontalGroup(
            pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 409, Short.MAX_VALUE)
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMatHangLayout.createSequentialGroup()
                        .addComponent(lblLoaiDichVu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlMatHangLayout.setVerticalGroup(
            pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiDichVu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 447, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(pnlMatHang);

        pnlCenter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(204, 204, 204)));
        pnlCenter.setOpaque(false);

        lblTenPhong.setText("Tên phòng");
        lblTenPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTenPhong.setText("Phòng 001");
        txtTenPhong.setEnabled(false);

        lblLoaiPhong.setText("Loại phòng");
        lblLoaiPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtLoaiPhong.setText("Phòng thường");
        txtLoaiPhong.setEnabled(false);

        jLabel1.setText("Tổng tiền mặt hàng");
        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        tableCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tên mặt hàng", "Số lượng", "Đơn giá", "Thành tiền", "Trả lại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCTHoaDon.setFillsViewportHeight(true);
        tableCTHoaDon.setRowHeight(30);
        tableCTHoaDon.setShowGrid(true);
        tableCTHoaDon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tableCTHoaDonKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tableCTHoaDon);
        if (tableCTHoaDon.getColumnModel().getColumnCount() > 0) {
            tableCTHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(0).setPreferredWidth(40);
            tableCTHoaDon.getColumnModel().getColumn(1).setPreferredWidth(180);
            tableCTHoaDon.getColumnModel().getColumn(2).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(2).setPreferredWidth(60);
            tableCTHoaDon.getColumnModel().getColumn(3).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(3).setPreferredWidth(80);
            tableCTHoaDon.getColumnModel().getColumn(4).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(4).setPreferredWidth(80);
            tableCTHoaDon.getColumnModel().getColumn(5).setResizable(false);
        }

        txtTongTienMatHang.setEnabled(false);
        txtTongTienMatHang.setUnit("VND");

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 600, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                        .addGap(151, 151, 151)
                        .addComponent(lblTenPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPhong)
                        .addGap(18, 18, 18)
                        .addComponent(lblLoaiPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTongTienMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenPhong)
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiPhong)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTongTienMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );

        jSplitPane1.setRightComponent(pnlCenter);

        pnlMain.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1042, 647));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void tableCTHoaDonKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableCTHoaDonKeyReleased
        int row = tableCTHoaDon.getSelectedRow();
        if (evt.getKeyChar() == KeyEvent.VK_ENTER) {
            int sl = (int) ((DefaultTableModel) tableCTHoaDon.getModel()).getValueAt(row, 2);
            MatHang matHang = hoaDon.getDsChiTietHoaDon().get(row).getMatHang();
            try {
                matHang.setsLTonKho(matHangService.getMatHang(matHang.getMaMatHang()).getsLTonKho() - sl);
                hoaDon.getDsChiTietHoaDon().get(row).setSoLuong(0);
                if (sl <= 1) {
                    hoaDon.getDsChiTietHoaDon().remove(row);
                } else {
                    hoaDon.themCT_HoaDon(matHang, sl, 0);
                }
                loadDataTableCTHoaDon();
                for (int i = 0; i < tableMatHang.getRowCount(); i++) {
                    ModelAdd data = (ModelAdd) ((DefaultTableModel) tableMatHang.getModel()).getValueAt(i, 3);
                    MatHang mh = (MatHang) data.getObj();
                    if (mh.equals(matHang)) {
                        mh.setsLTonKho(matHang.getsLTonKho());
                        ((DefaultTableModel) tableMatHang.getModel()).setValueAt(matHang.getsLTonKho(), i, 1);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Mặt hàng không đủ số lượng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                loadDataTableCTHoaDon();
            }
        } else {

        }
    }//GEN-LAST:event_tableCTHoaDonKeyReleased

    private void btnGiaoPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoPhongActionPerformed
        List<ChiTietHoaDon> dsChiTietHoaDonTruocCapNhat = chiTietHoaDonService.getDsChiTietHoaDonByMaHoaDon(hoaDon.getMaHoaDon());;
        dsChiTietHoaDonTruocCapNhat.forEach(chiTietHoaDon -> {
            if (!hoaDon.getDsChiTietHoaDon().contains(chiTietHoaDon)) {
                chiTietHoaDonService.deleteChiTietHoaDon(chiTietHoaDon);
            }
        });
        for (ChiTietHoaDon chiTietHoaDon : hoaDon.getDsChiTietHoaDon()) {
            chiTietHoaDonService.updateChiTietHoaDon(chiTietHoaDon);
        }
        dispose();
    }//GEN-LAST:event_btnGiaoPhongActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchMatHang();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbLoaiDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiDichVuActionPerformed
        searchMatHang();
    }//GEN-LAST:event_cbLoaiDichVuActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGiaoPhong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<String> cbLoaiDichVu;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblLoaiDichVu;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JPanel pnlBottomBar;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMatHang;
    private gui.swing.table2.MyTableFlatlaf tableCTHoaDon;
    private gui.swing.table2.MyTableFlatlaf tableMatHang;
    private javax.swing.JTextField txtLoaiPhong;
    private gui.swing.textfield.MyTextField txtSearch;
    private javax.swing.JTextField txtTenPhong;
    private gui.swing.textfield.MyTextFieldPerUnit txtTongTienMatHang;
    // End of variables declaration//GEN-END:variables

}
