package gui.dialog;

import dao.HoaDon_DAO;
import dao.MatHang_DAO;
import entity.HoaDon;
import entity.MatHang;
import entity.Phong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import gui.swing.table.SpinnerEditor;
import java.awt.event.ItemEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import service.HoaDonService;
import service.MatHangService;
import service.PhieuDatPhongService;

public class DL_TiepNhanDatPhong extends javax.swing.JDialog {

    private MatHangService matHangService;
    private HoaDonService hoaDonService;
    private PhieuDatPhongService phieuDatPhongService;
    private Phong phong;
    private NhanVien nhanVien;
    private HoaDon hoaDon;

    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    public DL_TiepNhanDatPhong(Phong phong, NhanVien nv) {
        this.hoaDonService = new HoaDon_DAO();
        this.matHangService = new MatHang_DAO();
        this.phong = phong;
        this.nhanVien = nv;
        this.hoaDon = new HoaDon();
        initComponents();
        setModal(true);
        setResizable(false);
        setTitle("Tiếp nhận đặt phòng");
        buildDisplay();
    }

    private void buildDisplay() {
        loadDataForm();
        createTableMatHang();
        createTableCTHoaDon();
    }

    private void createTableMatHang() {
        loadDataTableMatHang();
    }

    private void createTableCTHoaDon() {
        tableCTHoaDon.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor(200));
        loadDataTableCTHoaDon();
    }

    private void loadDataForm() {
        if (phong != null | nhanVien != null) {
            txtTenPhong.setText(phong.getTenPhong());
            txtLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
            lblNhanVien.setText("Nhân viên: " + nhanVien.getTenNhanVien());
            lblRole.setText(nhanVien.getLoaiNhanVien().getTenLoaiNV());
        }
    }

    private void loadDataTableMatHang() {
        List<MatHang> dsMatHang = matHangService.getDsMatHang();
        if (dsMatHang != null) {
            for (MatHang matHang : dsMatHang) {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(matHang.convertToRowTableInGDTiepNhanDatPhong());
            }
        }
    }

    private void loadDataTableCTHoaDon() {

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
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
        spTbAddMatHang = new javax.swing.JScrollPane();
        tableCTHoaDon = new javax.swing.JTable();
        pnlTGThuePhong = new javax.swing.JPanel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jTextField6 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        pnlDatTruoc = new javax.swing.JPanel();
        txtDaCoc = new javax.swing.JTextField();
        lblDaCoc = new javax.swing.JLabel();
        btnExpand = new javax.swing.JToggleButton();
        pnlTTKH = new javax.swing.JPanel();
        lblTenKhachHang = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        lblCCCD = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        lblSdt = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        pnlTTHD = new javax.swing.JPanel();
        txtGiamGia = new javax.swing.JTextField();
        lblGiamGia = new javax.swing.JLabel();
        txtMaHoaDon = new javax.swing.JTextField();
        lblMaHoaDon = new javax.swing.JLabel();
        txtChietKhau = new javax.swing.JTextField();
        lblChietKhau = new javax.swing.JLabel();
        pnlExpand = new javax.swing.JPanel();
        spPhieuDatPhong = new javax.swing.JScrollPane();
        tablePhieuDatPhong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bg.setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlBottomBar.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottomBar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        btnGiaoPhong.setText("Giao phòng");

        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblNhanVien.setText("Nhân viên: Đỗ Huy Hoàng");

        lblRole.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblRole.setForeground(new java.awt.Color(153, 153, 153));
        lblRole.setText("Quản lý");

        javax.swing.GroupLayout pnlBottomBarLayout = new javax.swing.GroupLayout(pnlBottomBar);
        pnlBottomBar.setLayout(pnlBottomBarLayout);
        pnlBottomBarLayout.setHorizontalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNhanVien)
                    .addComponent(lblRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 521, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGiaoPhong)
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

        cbLoaiDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đồ ăn", "Đồ uống" }));

        lblLoaiDichVu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLoaiDichVu.setText("Loại dịch vụ");

        tableMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên mặt hàng", "Tồn kho", "Giá bán", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableMatHang.setColumnSelectionAllowed(true);
        tableMatHang.setFillsViewportHeight(true);
        tableMatHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableMatHang.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tableMatHang);
        tableMatHang.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (tableMatHang.getColumnModel().getColumnCount() > 0) {
            tableMatHang.getColumnModel().getColumn(1).setResizable(false);
            tableMatHang.getColumnModel().getColumn(2).setResizable(false);
            tableMatHang.getColumnModel().getColumn(3).setResizable(false);
        }

        javax.swing.GroupLayout pnlMatHangLayout = new javax.swing.GroupLayout(pnlMatHang);
        pnlMatHang.setLayout(pnlMatHangLayout);
        pnlMatHangLayout.setHorizontalGroup(
            pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMatHangLayout.createSequentialGroup()
                        .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlMatHangLayout.createSequentialGroup()
                        .addComponent(lblLoaiDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLoaiDichVu, 0, 89, Short.MAX_VALUE)
                        .addGap(106, 106, 106))))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnlMatHangLayout.setVerticalGroup(
            pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiDichVu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(pnlMatHang);

        pnlCenter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(204, 204, 204)));
        pnlCenter.setOpaque(false);

        lblTenPhong.setText("Tên phòng");

        txtTenPhong.setText("Phòng 001");
        txtTenPhong.setEnabled(false);

        lblLoaiPhong.setText("Loại phòng");

        txtLoaiPhong.setText("Phòng thường");
        txtLoaiPhong.setEnabled(false);

        tableCTHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableCTHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Tên mặt hàng", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCTHoaDon.setFillsViewportHeight(true);
        spTbAddMatHang.setViewportView(tableCTHoaDon);
        if (tableCTHoaDon.getColumnModel().getColumnCount() > 0) {
            tableCTHoaDon.getColumnModel().getColumn(0).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(1).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(2).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(3).setResizable(false);
            tableCTHoaDon.getColumnModel().getColumn(4).setResizable(false);
        }

        pnlTGThuePhong.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thời gian thuê phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTGThuePhong.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Từ");

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/icons8_advance_20px_3.png"))); // NOI18N

        jTextField6.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Tiền phòng dự kiến");

        javax.swing.GroupLayout pnlTGThuePhongLayout = new javax.swing.GroupLayout(pnlTGThuePhong);
        pnlTGThuePhong.setLayout(pnlTGThuePhongLayout);
        pnlTGThuePhongLayout.setHorizontalGroup(
            pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTGThuePhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 76, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTGThuePhongLayout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField6)))
                .addContainerGap())
        );
        pnlTGThuePhongLayout.setVerticalGroup(
            pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel10)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlDatTruoc.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Đặt trước", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlDatTruoc.setOpaque(false);

        txtDaCoc.setEnabled(false);

        lblDaCoc.setText("Đã cọc");

        javax.swing.GroupLayout pnlDatTruocLayout = new javax.swing.GroupLayout(pnlDatTruoc);
        pnlDatTruoc.setLayout(pnlDatTruocLayout);
        pnlDatTruocLayout.setHorizontalGroup(
            pnlDatTruocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatTruocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblDaCoc)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDaCoc)
                .addContainerGap())
        );
        pnlDatTruocLayout.setVerticalGroup(
            pnlDatTruocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlDatTruocLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlDatTruocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDaCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDaCoc))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        btnExpand.setText("Phiếu đặt phòng");
        btnExpand.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnExpandItemStateChanged(evt);
            }
        });

        pnlTTKH.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTTKH.setOpaque(false);

        lblTenKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTenKhachHang.setText("Tên khách hàng");

        lblCCCD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCCCD.setText("CCCD");

        lblSdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblSdt.setText("Số điện thoại");

        javax.swing.GroupLayout pnlTTKHLayout = new javax.swing.GroupLayout(pnlTTKH);
        pnlTTKH.setLayout(pnlTTKHLayout);
        pnlTTKHLayout.setHorizontalGroup(
            pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTenKhachHang)
                    .addComponent(lblCCCD)
                    .addComponent(lblSdt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtSdt, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
                    .addComponent(txtCCCD)
                    .addComponent(txtTenKhachHang))
                .addContainerGap())
        );
        pnlTTKHLayout.setVerticalGroup(
            pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenKhachHang))
                .addGap(18, 18, 18)
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCCCD))
                .addGap(18, 18, 18)
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSdt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTTHD.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thông tin hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTTHD.setOpaque(false);

        lblGiamGia.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblGiamGia.setText("Giảm giá");

        lblMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblMaHoaDon.setText("Mã hóa đơn");

        lblChietKhau.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblChietKhau.setText("Chiết khấu");

        javax.swing.GroupLayout pnlTTHDLayout = new javax.swing.GroupLayout(pnlTTHD);
        pnlTTHD.setLayout(pnlTTHDLayout);
        pnlTTHDLayout.setHorizontalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTHDLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMaHoaDon)
                    .addComponent(lblGiamGia)
                    .addComponent(lblChietKhau))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtGiamGia)
                    .addComponent(txtMaHoaDon)
                    .addComponent(txtChietKhau))
                .addContainerGap())
        );
        pnlTTHDLayout.setVerticalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGiamGia)
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaHoaDon)
                    .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblChietKhau)
                    .addComponent(txtChietKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlCenterLayout = new javax.swing.GroupLayout(pnlCenter);
        pnlCenter.setLayout(pnlCenterLayout);
        pnlCenterLayout.setHorizontalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDatTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(pnlTTKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTenPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 79, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblLoaiPhong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnExpand, javax.swing.GroupLayout.DEFAULT_SIZE, 138, Short.MAX_VALUE))
            .addComponent(spTbAddMatHang, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenPhong)
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiPhong)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExpand))
                .addGap(8, 8, 8)
                .addComponent(spTbAddMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pnlDatTruoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jSplitPane1.setRightComponent(pnlCenter);

        pnlMain.add(jSplitPane1, java.awt.BorderLayout.CENTER);

        bg.add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlExpand.setBackground(new java.awt.Color(255, 255, 255));
        pnlExpand.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)), "Phiếu đặt phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        tablePhieuDatPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã phiếu", "Khách hàng", "Giờ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tablePhieuDatPhong.setFillsViewportHeight(true);
        spPhieuDatPhong.setViewportView(tablePhieuDatPhong);
        if (tablePhieuDatPhong.getColumnModel().getColumnCount() > 0) {
            tablePhieuDatPhong.getColumnModel().getColumn(0).setResizable(false);
            tablePhieuDatPhong.getColumnModel().getColumn(1).setResizable(false);
            tablePhieuDatPhong.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlExpandLayout = new javax.swing.GroupLayout(pnlExpand);
        pnlExpand.setLayout(pnlExpandLayout);
        pnlExpandLayout.setHorizontalGroup(
            pnlExpandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExpandLayout.createSequentialGroup()
                .addComponent(spPhieuDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        pnlExpandLayout.setVerticalGroup(
            pnlExpandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExpandLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(spPhieuDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 503, Short.MAX_VALUE)
                .addContainerGap())
        );

        bg.add(pnlExpand, java.awt.BorderLayout.LINE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(942, 595));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnExpandItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnExpandItemStateChanged
        int state = evt.getStateChange();

        if (state == ItemEvent.SELECTED) {
            setSize(new java.awt.Dimension(getPreferredSize()));
        } else {
            setSize(new java.awt.Dimension(926, 631));
        }
    }//GEN-LAST:event_btnExpandItemStateChanged

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JToggleButton btnExpand;
    private javax.swing.JButton btnGiaoPhong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<String> cbLoaiDichVu;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblChietKhau;
    private javax.swing.JLabel lblDaCoc;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblLoaiDichVu;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JPanel pnlBottomBar;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlDatTruoc;
    private javax.swing.JPanel pnlExpand;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMatHang;
    private javax.swing.JPanel pnlTGThuePhong;
    private javax.swing.JPanel pnlTTHD;
    private javax.swing.JPanel pnlTTKH;
    private javax.swing.JScrollPane spPhieuDatPhong;
    private javax.swing.JScrollPane spTbAddMatHang;
    private javax.swing.JTable tableCTHoaDon;
    private gui.swing.table2.MyTableFlatlaf tableMatHang;
    private javax.swing.JTable tablePhieuDatPhong;
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtChietKhau;
    private javax.swing.JTextField txtDaCoc;
    private javax.swing.JTextField txtGiamGia;
    private javax.swing.JTextField txtLoaiPhong;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSdt;
    private gui.swing.textfield.MyTextField txtSearch;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenPhong;
    // End of variables declaration//GEN-END:variables
}
