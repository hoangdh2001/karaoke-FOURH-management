package gui.dialog;

import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.LoaiDichVu_DAO;
import dao.MatHang_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.MatHang;
import entity.NhanVien;
import entity.TrangThaiHoaDon;
import entity.TrangThaiPhong;
import gui.Application;
import gui.component.BillPrintable;
import gui.swing.event.EventAdd;
import gui.swing.event.EventMinus;
import gui.swing.model.ModelAdd;
import gui.swing.table.SpinnerEditor;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterJob;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import gui.swing.model.ModelObjectComboBox;
import java.awt.Frame;
import java.awt.print.PrinterException;
import service.ChiTietHoaDonService;
import service.HoaDonService;
import service.LoaiDichVuService;
import service.MatHangService;

public class DL_ThanhToan extends javax.swing.JDialog {

    private final MatHangService matHangService;
    private final HoaDonService hoaDonService;
    private final ChiTietHoaDonService chiTietHoaDonService;
    private final LoaiDichVuService loaiDichVuService;
    private final HoaDon hoaDon;
    private final DecimalFormat df = new DecimalFormat("#,##0");
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private List<MatHang> dsMatHang;
    private EventAdd event;
    private double bHeight = 0.0;

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public DL_ThanhToan(Frame frame, HoaDon hoaDon, NhanVien nhanVien) throws Exception {
        super(frame, true);
        this.hoaDonService = new HoaDon_DAO();
        this.matHangService = new MatHang_DAO();
        this.chiTietHoaDonService = new ChiTietHoaDon_DAO();
        this.loaiDichVuService = new LoaiDichVu_DAO();
        this.hoaDon = hoaDon;
        hoaDon.setNhanVien(nhanVien);
        hoaDon.setThoiGianKetThuc(new Date());
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

    private void loadDataForm() {
        if (hoaDon.getPhong() != null) {
            ((DefaultComboBoxModel) cbLoaiDichVu.getModel()).addAll(loaiDichVuService.getDsTenLoaiDichVu());
            txtMaHoaDon.setText(hoaDon.getMaHoaDon());
            txtTenPhong.setText(hoaDon.getPhong().getTenPhong());
            txtLoaiPhong.setText(hoaDon.getPhong().getLoaiPhong().getTenLoaiPhong());
            txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
            txtTienPhong.setText(df.format(hoaDon.getDonGiaPhong()));
            txtTongHoaDon.setText(df.format(hoaDon.getTongHoaDon()));
            txtGioHat.setText(hoaDon.getGioHat());
            txtGiaPhong.setText(df.format(hoaDon.getPhong().getLoaiPhong().getGiaPhong()));
            thoiGianNhanPhong.setValue(hoaDon.getThoiGianBatDau());
            txtThoiLai.setText(df.format(hoaDon.getTienThua()));
            txtTienPhongCu.setText(df.format(hoaDon.getDonGiaPhongCu()));
            txtDaCoc.setText(df.format(hoaDon.getTienCoc()));
        }
        if (hoaDon.getNhanVien() != null) {
            lblNhanVien.setText(hoaDon.getNhanVien().getTenNhanVien());
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
                    hoaDon.themCT_HoaDon(matHang, 1);
                    loadDataTableCTHoaDon();
                    ((DefaultTableModel) tableMatHang.getModel()).setValueAt(matHang.getsLTonKho(), tableMatHang.getSelectedRow(), 1);
                    txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
                    txtTongHoaDon.setText(df.format(hoaDon.getTongHoaDon()));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Mặt hàng này đã hết!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                }
            };

            dsMatHang.forEach(matHang -> {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(new Object[]{new ModelObjectComboBox(matHang.getTenMatHang(), matHang.getMaMatHang()),
                    matHang.getsLTonKho(),
                    df.format(matHang.getDonGia()),
                    new ModelAdd(matHang, event)});
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
                    txtTongHoaDon.setText(df.format(hoaDon.getTongHoaDon()));
                } catch (Exception ex) {
                    Logger.getLogger(DL_TiepNhanThuePhong.class.getName()).log(Level.SEVERE, null, ex);
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
                        hoaDon.themCT_HoaDon(matHang, -1);
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
                    txtTongHoaDon.setText(df.format(hoaDon.getTongHoaDon()));
                } catch (Exception ex) {
                    Logger.getLogger(DL_CapNhatDichVu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
            ((DefaultTableModel) tableCTHoaDon.getModel()).addRow(new Object[]{chiTietHoaDon.getMatHang().getMaMatHang(),
                chiTietHoaDon.getMatHang().getTenMatHang(),
                chiTietHoaDon.getSoLuong(),
                df.format(chiTietHoaDon.getMatHang().getDonGia()),
                df.format(chiTietHoaDon.getThanhTien()), eventMinus});
        }
    }

    private void searchMatHang() {
        ((DefaultTableModel) tableMatHang.getModel()).setRowCount(0);
        String tenMatHang = txtSearch.getText().trim();
        String tenLoaiDichVu = String.valueOf(cbLoaiDichVu.getSelectedItem());
        if (!tenLoaiDichVu.equals("Tất cả") && tenMatHang.length() > 0) {
            System.out.println("Cả hai");
            dsMatHang.stream().filter(matHang -> ((matHang.getTenMatHang().toLowerCase().contains(tenMatHang.toLowerCase())) && (matHang.getLoaiDichVu().getTenLoaiDichVu().equals(tenLoaiDichVu)))).forEachOrdered(matHang -> {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(new Object[]{new ModelObjectComboBox(matHang.getTenMatHang(), matHang.getMaMatHang()),
                    matHang.getsLTonKho(),
                    df.format(matHang.getDonGia()),
                    new ModelAdd(matHang, event)});
            });
        } else if (!tenLoaiDichVu.equals("Tất cả") || tenMatHang.length() > 0) {
            System.out.println("Một trong hai");
            dsMatHang.forEach(matHang -> {
                if (!tenLoaiDichVu.equals("Tất cả")) {
                    System.out.println("ComboBox");
                    if (matHang.getLoaiDichVu().getTenLoaiDichVu().equals(tenLoaiDichVu)) {
                        ((DefaultTableModel) tableMatHang.getModel()).addRow(new Object[]{new ModelObjectComboBox(matHang.getTenMatHang(), matHang.getMaMatHang()),
                            matHang.getsLTonKho(),
                            df.format(matHang.getDonGia()),
                            new ModelAdd(matHang, event)});
                    }
                } else if (matHang.getTenMatHang().toLowerCase().contains(tenMatHang.toLowerCase())) {
                    ((DefaultTableModel) tableMatHang.getModel()).addRow(new Object[]{new ModelObjectComboBox(matHang.getTenMatHang(), matHang.getMaMatHang()),
                        matHang.getsLTonKho(),
                        df.format(matHang.getDonGia()),
                        new ModelAdd(matHang, event)});
                }
            });
        } else {
            dsMatHang.forEach(matHang -> {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(new Object[]{new ModelObjectComboBox(matHang.getTenMatHang(), matHang.getMaMatHang()),
                    matHang.getsLTonKho(),
                    df.format(matHang.getDonGia()),
                    new ModelAdd(matHang, event)});
            });
        }
    }

    public PageFormat getPageFormat(PrinterJob pj) {
        PageFormat pf = pj.defaultPage();
        Paper paper = pf.getPaper();

        double bodyHeight = bHeight;
        double headerHeight = 5.0;
        double footerHeight = 6.0;
        double width = cm_to_pp(8);
        double height = cm_to_pp(headerHeight + bodyHeight + footerHeight);
        paper.setSize(width, height);
        paper.setImageableArea(0, 10, width, height);

        pf.setOrientation(PageFormat.PORTRAIT);
        pf.setPaper(paper);
        return pf;
    }

    protected static double cm_to_pp(double cm) {
        return toPPI(cm * 0.393600787);
    }

    protected static double toPPI(double inch) {
        return inch * 72d;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlBottomBar = new javax.swing.JPanel();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblNhanVien = new javax.swing.JLabel();
        lblRole = new javax.swing.JLabel();
        jSplitPane1 = new javax.swing.JSplitPane();
        pnlMatHang = new javax.swing.JPanel();
        txtSearch = new gui.swing.textfield.MyTextField();
        cbLoaiDichVu = new javax.swing.JComboBox<>();
        lblLoaiDichVu = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableMatHang = new gui.swing.table.MyTableFlatlaf();
        pnlCenter = new javax.swing.JPanel();
        pnlTGThuePhong = new javax.swing.JPanel();
        lblThoiGianNhanPhong = new javax.swing.JLabel();
        lblThoiGianTraPhong = new javax.swing.JLabel();
        lblTienPhongDuKien = new javax.swing.JLabel();
        txtTienPhong = new gui.swing.textfield.MyTextFieldPerUnit();
        lblGioHat = new javax.swing.JLabel();
        txtGioHat = new javax.swing.JTextField();
        lblGiaPhong = new javax.swing.JLabel();
        txtGiaPhong = new gui.swing.textfield.MyTextFieldPerUnit();
        thoiGianNhanPhong = new javax.swing.JSpinner();
        thoiGianTraPhong = new javax.swing.JSpinner();
        lblTienPhongCu = new javax.swing.JLabel();
        txtTienPhongCu = new gui.swing.textfield.MyTextFieldPerUnit();
        cbInHoaDon = new javax.swing.JCheckBox();
        lblTienDv = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHoaDon = new gui.swing.table.MyTableFlatlaf();
        txtTongTienMatHang = new gui.swing.textfield.MyTextFieldPerUnit();
        lblTenPhong = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        lblLoaiPhong = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        txtMaHoaDon = new javax.swing.JTextField();
        txtDaCoc = new gui.swing.textfield.MyTextFieldPerUnit();
        lblDaCoc = new javax.swing.JLabel();
        lblMaHoaDon = new javax.swing.JLabel();
        txtTienKhachHang = new gui.swing.textfield.MyTextFieldPerUnit();
        lblTienKhachHang = new javax.swing.JLabel();
        txtTongHoaDon = new gui.swing.textfield.MyTextFieldPerUnit();
        lblTongHoaDon = new javax.swing.JLabel();
        lblThoiLai = new javax.swing.JLabel();
        txtThoiLai = new gui.swing.textfield.MyTextFieldPerUnit();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel7 = new javax.swing.JLabel();
        txtChietKhau = new gui.swing.textfield.MyTextFieldPerUnit();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thanh toán");
        setModal(true);
        setResizable(false);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlBottomBar.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottomBar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        btnThanhToan.setBackground(new java.awt.Color(54, 88, 153));
        btnThanhToan.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(255, 255, 255));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(54, 88, 153));
        btnHuy.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNhanVien.setText("Đỗ Huy Hoàng");

        lblRole.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblRole.setText("Quản lý");

        javax.swing.GroupLayout pnlBottomBarLayout = new javax.swing.GroupLayout(pnlBottomBar);
        pnlBottomBar.setLayout(pnlBottomBarLayout);
        pnlBottomBarLayout.setHorizontalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblRole)
                    .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 623, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan)
                .addGap(22, 22, 22))
        );
        pnlBottomBarLayout.setVerticalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomBarLayout.createSequentialGroup()
                .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblRole)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
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

        lblLoaiDichVu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLoaiDichVu.setText("Loại dịch vụ");

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
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 437, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlMatHangLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 416, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlMatHangLayout.createSequentialGroup()
                                .addComponent(lblLoaiDichVu)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cbLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(0, 0, Short.MAX_VALUE))
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 452, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(pnlMatHang);

        pnlCenter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(204, 204, 204)));
        pnlCenter.setOpaque(false);

        pnlTGThuePhong.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)), "Thời gian thuê phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTGThuePhong.setOpaque(false);

        lblThoiGianNhanPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblThoiGianNhanPhong.setText("Thời gian nhận phòng");

        lblThoiGianTraPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblThoiGianTraPhong.setText("Thời gian trả phòng");

        lblTienPhongDuKien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTienPhongDuKien.setText("Tiền phòng");

        txtTienPhong.setEnabled(false);
        txtTienPhong.setUnit("VND");

        lblGioHat.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGioHat.setText("Giờ hát");

        txtGioHat.setEnabled(false);
        txtGioHat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGioHatActionPerformed(evt);
            }
        });

        lblGiaPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGiaPhong.setText("Giá phòng");

        txtGiaPhong.setEnabled(false);
        txtGiaPhong.setUnit("đ/giờ");

        thoiGianNhanPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        thoiGianNhanPhong.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(), null, java.util.Calendar.HOUR_OF_DAY));
        thoiGianNhanPhong.setEnabled(false);

        thoiGianTraPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        thoiGianTraPhong.setModel(new javax.swing.SpinnerDateModel(new java.util.Date(), new java.util.Date(), null, java.util.Calendar.HOUR_OF_DAY));
        thoiGianTraPhong.setEnabled(false);

        lblTienPhongCu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTienPhongCu.setText("Tiền phòng cũ");

        txtTienPhongCu.setEnabled(false);
        txtTienPhongCu.setUnit("VND");

        cbInHoaDon.setSelected(true);
        cbInHoaDon.setText("In hóa đơn");

        javax.swing.GroupLayout pnlTGThuePhongLayout = new javax.swing.GroupLayout(pnlTGThuePhong);
        pnlTGThuePhong.setLayout(pnlTGThuePhongLayout);
        pnlTGThuePhongLayout.setHorizontalGroup(
            pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblThoiGianTraPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(thoiGianTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8))
                    .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                        .addComponent(lblGioHat)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGioHat, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblGiaPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtGiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTGThuePhongLayout.createSequentialGroup()
                        .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblTienPhongCu)
                            .addComponent(lblTienPhongDuKien))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTienPhongCu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTGThuePhongLayout.createSequentialGroup()
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTGThuePhongLayout.createSequentialGroup()
                        .addComponent(lblThoiGianNhanPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(thoiGianNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlTGThuePhongLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(cbInHoaDon)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlTGThuePhongLayout.setVerticalGroup(
            pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGianNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(thoiGianNhanPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblThoiGianTraPhong)
                    .addComponent(thoiGianTraPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtGiaPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblGioHat)
                        .addComponent(lblGiaPhong))
                    .addComponent(txtGioHat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienPhongDuKien))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienPhongCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienPhongCu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(cbInHoaDon)
                .addContainerGap())
        );

        lblTienDv.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTienDv.setText("Tiền dịch vụ");

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

        lblTenPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTenPhong.setText("Tên phòng");

        txtTenPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenPhong.setText("Phòng 001");
        txtTenPhong.setEnabled(false);

        lblLoaiPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLoaiPhong.setText("Loại phòng");

        txtLoaiPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLoaiPhong.setText("Phòng thường");
        txtLoaiPhong.setEnabled(false);

        txtMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMaHoaDon.setEnabled(false);

        txtDaCoc.setEnabled(false);
        txtDaCoc.setUnit("VND");

        lblDaCoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDaCoc.setText("Đã cọc");

        lblMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaHoaDon.setText("Mã hóa đơn");

        txtTienKhachHang.setUnit("VND");
        txtTienKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTienKhachHangKeyReleased(evt);
            }
        });

        lblTienKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTienKhachHang.setText("Tiền khách đưa");

        txtTongHoaDon.setEnabled(false);
        txtTongHoaDon.setUnit("VND");

        lblTongHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTongHoaDon.setText("Tổng hóa đơn");

        lblThoiLai.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblThoiLai.setText("Thối lại");

        txtThoiLai.setEnabled(false);
        txtThoiLai.setUnit("VND");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Chiết khấu");

        txtChietKhau.setUnit("%");
        txtChietKhau.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtChietKhauKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblDaCoc)
                                    .addComponent(lblTienDv)
                                    .addComponent(jLabel7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtChietKhau, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                                    .addComponent(txtDaCoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTongTienMatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTienKhachHang)
                                    .addComponent(lblThoiLai)
                                    .addComponent(lblTongHoaDon))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTienKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(26, 26, 26))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addComponent(lblTenPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblLoaiPhong)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblMaHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(1, 1, 1))
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenPhong)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiPhong)
                    .addComponent(lblMaHoaDon)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongTienMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTienDv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDaCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDaCoc))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(10, 10, 10)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTongHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTongHoaDon))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTienKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblTienKhachHang))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtThoiLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblThoiLai)))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE)))))
        );

        jSplitPane1.setRightComponent(pnlCenter);

        pnlMain.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(pnlMain, java.awt.BorderLayout.CENTER);

        pack();
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
                    hoaDon.themCT_HoaDon(matHang, sl);
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

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        if (hoaDon.getTienThua() < 0) {
            if (JOptionPane.showConfirmDialog(rootPane, "Số tiền khách đưa chưa đủ bạn có muốn thanh toán không?", "Xác nhận", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                return;
            }
        }
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn trả phòng?\nLƯU Ý: Vui lòng kiểm tra và nhắc khách không để quên đồ", "Xác nhận", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            try {
                loadDataForm();
                hoaDon.setTrangThai(TrangThaiHoaDon.HOAN_THANH);
                hoaDon.getPhong().setTrangThai(TrangThaiPhong.BAN);
                hoaDon.setNgayLapHoaDon(new Date());
                List<ChiTietHoaDon> dsChiTietHoaDonTruocCapNhat = chiTietHoaDonService.getDsChiTietHoaDonByMaHoaDon(hoaDon.getMaHoaDon());
                dsChiTietHoaDonTruocCapNhat.forEach(chiTietHoaDon -> {
                    if (!hoaDon.getDsChiTietHoaDon().contains(chiTietHoaDon)) {
                        chiTietHoaDonService.deleteChiTietHoaDon(chiTietHoaDon);
                    }
                });
                hoaDon.getDsChiTietHoaDon().forEach(chiTietHoaDon -> {
                    chiTietHoaDonService.updateChiTietHoaDon(chiTietHoaDon);
                });
                if (hoaDonService.finishHoaDon(hoaDon)) {
                    if (cbInHoaDon.isSelected()) {
                        bHeight = Double.valueOf(hoaDon.getDsChiTietHoaDon().size());
                        
                        PrinterJob pj = PrinterJob.getPrinterJob();
                        pj.setJobName(hoaDon.getMaHoaDon());
                        pj.setPrintable(new BillPrintable(hoaDon), getPageFormat(pj));
                        boolean returningResult = pj.printDialog();
                        if (returningResult) {
                            try {
                                pj.print();
                                dispose();
                            } catch (PrinterException e) {
                                JOptionPane.showMessageDialog(this, "Print Error: " + e.getMessage(), "Thông báo", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    } else {
                        dispose();
                    }
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Thanh toán thất bại!");
                }
            } catch (Exception ex) {
                Logger.getLogger(DL_ThanhToan.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchMatHang();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbLoaiDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiDichVuActionPerformed
        searchMatHang();
    }//GEN-LAST:event_cbLoaiDichVuActionPerformed

    private void txtGioHatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGioHatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGioHatActionPerformed
    private void txtTienKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTienKhachHangKeyReleased
        try {
            hoaDon.setTienKhachDua(Double.parseDouble(txtTienKhachHang.getText().trim()));
            loadDataForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Chỉ nhập số!");
            txtTienKhachHang.setText("");
        }
    }//GEN-LAST:event_txtTienKhachHangKeyReleased

    private void txtChietKhauKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtChietKhauKeyReleased
        try {
            hoaDon.setChietKhau(Float.parseFloat(txtChietKhau.getText().trim()) / 100);
            loadDataForm();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Chỉ nhập số!");
            txtChietKhau.setText("");
        }
    }//GEN-LAST:event_txtChietKhauKeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JCheckBox cbInHoaDon;
    private javax.swing.JComboBox<String> cbLoaiDichVu;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblDaCoc;
    private javax.swing.JLabel lblGiaPhong;
    private javax.swing.JLabel lblGioHat;
    private javax.swing.JLabel lblLoaiDichVu;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JLabel lblThoiGianNhanPhong;
    private javax.swing.JLabel lblThoiGianTraPhong;
    private javax.swing.JLabel lblThoiLai;
    private javax.swing.JLabel lblTienDv;
    private javax.swing.JLabel lblTienKhachHang;
    private javax.swing.JLabel lblTienPhongCu;
    private javax.swing.JLabel lblTienPhongDuKien;
    private javax.swing.JLabel lblTongHoaDon;
    private javax.swing.JPanel pnlBottomBar;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMatHang;
    private javax.swing.JPanel pnlTGThuePhong;
    private gui.swing.table.MyTableFlatlaf tableCTHoaDon;
    private gui.swing.table.MyTableFlatlaf tableMatHang;
    private javax.swing.JSpinner thoiGianNhanPhong;
    private javax.swing.JSpinner thoiGianTraPhong;
    private gui.swing.textfield.MyTextFieldPerUnit txtChietKhau;
    private gui.swing.textfield.MyTextFieldPerUnit txtDaCoc;
    private gui.swing.textfield.MyTextFieldPerUnit txtGiaPhong;
    private javax.swing.JTextField txtGioHat;
    private javax.swing.JTextField txtLoaiPhong;
    private javax.swing.JTextField txtMaHoaDon;
    private gui.swing.textfield.MyTextField txtSearch;
    private javax.swing.JTextField txtTenPhong;
    private gui.swing.textfield.MyTextFieldPerUnit txtThoiLai;
    private gui.swing.textfield.MyTextFieldPerUnit txtTienKhachHang;
    private gui.swing.textfield.MyTextFieldPerUnit txtTienPhong;
    private gui.swing.textfield.MyTextFieldPerUnit txtTienPhongCu;
    private gui.swing.textfield.MyTextFieldPerUnit txtTongHoaDon;
    private gui.swing.textfield.MyTextFieldPerUnit txtTongTienMatHang;
    // End of variables declaration//GEN-END:variables

}
