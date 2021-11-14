package gui.dialog;

import dao.NhaCungCapVaNhapHang_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.MatHang;
import entity.Phong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.TrangThaiPhong;
import gui.GD_TiepNhanDatPhong;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import objectcombobox.ObjectComboBox;
import service.NhaCungCapVaNhapHangDaoService;

public class DL_TiepNhanDatPhong extends javax.swing.JDialog {

    private NhaCungCapVaNhapHangDaoService nhaCungCapVaNhapHangDaoService;
    private Phong phong;
    private NhanVien nhanVien;
    
    private ArrayList<ObjectComboBox> listDaChon;
    private ArrayList<ObjectComboBox> listDaChonSoLuong;

    public DL_TiepNhanDatPhong(Phong phong, NhanVien nv) {
        nhaCungCapVaNhapHangDaoService = new NhaCungCapVaNhapHang_DAO();
        this.phong = phong;
        this.nhanVien = nv;
        initComponents();
        setModal(true);
        setResizable(false);
        setTitle("Tiếp nhận đặt phòng");
        buildDisplay();
        openTablePhieuDatPhong();
    }

    private void buildDisplay() {
        initData();
    }

    public void initData() {
        if (phong != null | nhanVien != null) {
            txtTenPhong.setText(phong.getTenPhong());
            txtLoaiPhong.setText(phong.getLoaiPhong().getTenLoaiPhong());
//            txtGia.setText(df.format(phong.getLoaiPhong().getGiaPhong()));
            lblNhanVien.setText("Nhân viên: " + nhanVien.getTenNhanVien());
            List<MatHang> dsMatHang = nhaCungCapVaNhapHangDaoService.getDanhSachMatHang();
            for (MatHang matHang : dsMatHang) {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(matHang.convertToRowTableInGDTiepNhanDatPhong());
            }
//            List<MatHang> dsMatHang = nhaCungCapVaNhapHangDaoService.getDanhSachMatHang();
//            dsMatHang.forEach(matHang -> tableDichVu.addRow(matHang.convertToRowTableInGDTiepNhanDatPhong()));
        }
    }

    private void openTablePhieuDatPhong() {

        btnExpand.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent arg0) {
                int state = arg0.getStateChange();

                if (state == ItemEvent.SELECTED) {
                    setSize(new java.awt.Dimension(getPreferredSize()));
                } else {
                    setSize(new java.awt.Dimension(916, 631));
                }
            }
        });
    }

    public void addAction() {
        tablePhieuDatPhong.addMouseListener(new createMouseListener());
        tableMatHang.addMouseListener(new createMouseListener());
        tableAddMatHang.addKeyListener(new createKeyListener());
        txtSdt.addKeyListener(new createKeyListener());
        txtTenKhachHang.addKeyListener(new createKeyListener());
        txtCCCD.addKeyListener(new createKeyListener());
        btnGiaoPhong.addActionListener(new createActionListenner());
    }

    public void initKhachHang(String sdt) {
        KhachHang kh = nhaCungCapVaNhapHangDaoService.getKhachHangBySDT(sdt);
        if (kh != null) {
            txtTenKhachHang.setText(kh.getTenKhachHang());
            txtCCCD.setText(kh.getCanCuocCD());
        }
    }

    private class createKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Object obj = e.getSource();
            if (e.getKeyChar() == KeyEvent.VK_ENTER && obj.equals(txtSdt)) {
                String sdt = txtSdt.getText().trim();
                if (!sdt.equals("")) {
                    initKhachHang(sdt);
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            Object obj = e.getSource();
            if (e.getKeyChar() == KeyEvent.VK_BACK_SPACE) {
                if (obj.equals(txtSdt) || obj.equals(txtTenKhachHang) || obj.equals(txtCCCD)) {
                    if ((txtSdt.getText().trim() + txtTenKhachHang.getText().trim() + txtCCCD.getText().trim()).equals("")) {
                        tablePhieuDatPhong.clearSelection();
                    }
                }
            } else if (e.getKeyChar() == KeyEvent.VK_ENTER && obj.equals(tableAddMatHang)) {
                int idx = (int) tableAddMatHang.getSelectedRow();
                ObjectComboBox cb = (ObjectComboBox) tableAddMatHang.getValueAt(idx, 0);
                int soLuong = 0;
                for (int i = 0; i < tableMatHang.getRowCount(); i++) {
                    if (tableMatHang.getValueAt(i, 0).equals(cb)) {
                        soLuong = (int) tableMatHang.getValueAt(i, 1);
                        listDaChonSoLuong.get(i).setMa(String.valueOf(tableAddMatHang.getValueAt(idx, 1)));
                        break;
                    }
                }
                if ((int) tableAddMatHang.getValueAt(idx, 1) > soLuong) {
                    JOptionPane.showMessageDialog(DL_TiepNhanDatPhong.this, cb.toString() + " số lượng chỉ còn " + soLuong);
                    tableAddMatHang.editCellAt(idx, 1);
                }
            }
        }

    }

    private class createMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            Object obj = e.getSource();
            if (obj.equals(tableMatHang)) {
                CountDownLatch lt = new CountDownLatch(1);
                Thread thread = new Thread(() -> {
                    System.out.println(tableMatHang.getValueAt(tableMatHang.getSelectedRow(), 3));
                    for (int i = 0; i < tableMatHang.getRowCount(); i++) {
                        ObjectComboBox cb = (ObjectComboBox) tableMatHang.getModel().getValueAt(i, 0);
                        ObjectComboBox sl = new ObjectComboBox(cb.getMa(), "1");
                        if ((Boolean) tableMatHang.getValueAt(i, 3)) {
                            if (!listDaChon.contains(cb)) {
                                listDaChon.add(cb);
                                listDaChonSoLuong.add(sl);
                            }
                        } else {
                            listDaChon.remove(cb);
                            listDaChonSoLuong.remove(sl);
                        }
                    }

                    DefaultTableModel model = (DefaultTableModel) tableAddMatHang.getModel();
                    model.setRowCount(0);

                    for (int i = 0; i < listDaChon.size(); i++) {
                        ObjectComboBox cb = (ObjectComboBox) listDaChon.get(i);
                        ((DefaultTableModel)tableAddMatHang.getModel()).addRow(new Object[]{cb, Integer.parseInt(listDaChonSoLuong.get(i).getMa())});
                    }
                    lt.countDown();
                });

                thread.start();

                try {
                    lt.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GD_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
            Object obj = e.getSource();
            if (obj.equals(tablePhieuDatPhong)) {
                String ma = tablePhieuDatPhong.getModel().getValueAt(tablePhieuDatPhong.getSelectedRow(), 0).toString();
                PhieuDatPhong phieuDatPhong = nhaCungCapVaNhapHangDaoService.getPhieuById(ma);
                txtSdt.setText(phieuDatPhong.getKhachHang().getSoDienThoai());
                txtTenKhachHang.setText(phieuDatPhong.getKhachHang().getTenKhachHang());
                txtCCCD.setText(phieuDatPhong.getKhachHang().getCanCuocCD());
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    private boolean validateData() {
        String sdt = txtSdt.getText().trim();
        String hoTen = txtTenKhachHang.getText().trim();
        String CCCD = txtCCCD.getText().trim();

        if (sdt.trim().equals("")) {
            showMsg("Số điện thoại khách hàng trống !");
            txtSdt.selectAll();
            txtSdt.requestFocus();
            return false;
        } else {
            if (!(sdt.matches("^[0-9]{10}$"))) {
                showMsg("Số điện thoại khách hàng không hợp lệ");
                txtSdt.requestFocus();
                txtSdt.selectAll();
                return false;
            }
        }

        if (hoTen.trim().equals("")) {
            showMsg("Họ tên khách hàng trống !");
            txtTenKhachHang.selectAll();
            txtTenKhachHang.requestFocus();
            return false;
        } else {

            if ((hoTen.matches(
                    "^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
                showMsg("tên khách hàng không hợp lệ");
                txtTenKhachHang.requestFocus();
                txtTenKhachHang.selectAll();
                return false;
            }
        }

        if (CCCD.trim().equals("")) {
            showMsg("Địa chỉ không được trống !");
            txtCCCD.selectAll();
            txtCCCD.requestFocus();
            return false;
        } else {
            if (!(CCCD.matches("^(0)[0-9]{11}$"))) {
                showMsg("Căn cước công dân không hợp lệ");
                txtCCCD.requestFocus();
                txtCCCD.selectAll();
                return false;
            }
        }
        return true;
    }

    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private class createActionListenner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj.equals(btnGiaoPhong) && validateData()) {
                KhachHang kh = nhaCungCapVaNhapHangDaoService.getKhachHangBySDT(txtSdt.getText());
                if (kh == null) {
                    String maKhachhang = nhaCungCapVaNhapHangDaoService.getlastKhachHangTang();
                    kh = new KhachHang(maKhachhang,
                             txtTenKhachHang.getText(),
                             txtCCCD.getText(),
                             txtSdt.getText());
                    nhaCungCapVaNhapHangDaoService.addKhachHang(kh);
                }
                if (tablePhieuDatPhong.getSelectedRow() != -1) {
                    String maPhieuDatPhong = tablePhieuDatPhong.getValueAt(tablePhieuDatPhong.getSelectedRow(), 0).toString();
                    nhaCungCapVaNhapHangDaoService.updatePhieuDatHang(maPhieuDatPhong);
                }
                nhaCungCapVaNhapHangDaoService.updatePhong(phong.getMaPhong(), TrangThaiPhong.DANG_HAT);

                String maHoaDon = nhaCungCapVaNhapHangDaoService.getlastMaHoaDonTang();

                HoaDon hoaDon = new HoaDon(maHoaDon, kh, phong, nhanVien);
                nhaCungCapVaNhapHangDaoService.insertHoaDon(hoaDon);
//insert chi tiet hoa don
                if (tableAddMatHang.getRowCount() != 0) {
                    for (int i = 0; i < tableAddMatHang.getRowCount(); i++) {
                        ObjectComboBox cb = (ObjectComboBox) tableAddMatHang.getValueAt(i, 0);
                        int soluong = Integer.parseInt(tableAddMatHang.getValueAt(i, 1).toString());
                        System.out.println(soluong);
                        if (soluong > 0) {
                            MatHang matHang = nhaCungCapVaNhapHangDaoService.getMatHang(cb.getMa());
                            ChiTietHoaDon ctHoaDon = new ChiTietHoaDon(hoaDon, matHang, soluong, 0.0f);
                            nhaCungCapVaNhapHangDaoService.insertCTHoaDon(ctHoaDon);
                            nhaCungCapVaNhapHangDaoService.updateSLMatHang(cb.getMa(), soluong, "decrease");
                        }
                    }
                }
                dispose();
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        pnlMain = new javax.swing.JPanel();
        pnlMatHang = new javax.swing.JPanel();
        txtSearch = new gui.swing.textfield.MyTextField();
        lblTitleM = new javax.swing.JLabel();
        spe5 = new javax.swing.JSeparator();
        spe6 = new javax.swing.JSeparator();
        cbLoaiDichVu = new javax.swing.JComboBox<>();
        lblLoaiDichVu = new javax.swing.JLabel();
        spTbMatHang = new javax.swing.JScrollPane();
        tableMatHang = new javax.swing.JTable();
        pnlCenter = new javax.swing.JPanel();
        lblTenPhong = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        lblLoaiPhong = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        spTbAddMatHang = new javax.swing.JScrollPane();
        tableAddMatHang = new javax.swing.JTable();
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
        pnlBottomBar = new javax.swing.JPanel();
        btnGiaoPhong = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        lblNhanVien = new javax.swing.JLabel();
        pnlExpand = new javax.swing.JPanel();
        spPhieuDatPhong = new javax.swing.JScrollPane();
        tablePhieuDatPhong = new javax.swing.JTable();
        spe3 = new javax.swing.JSeparator();
        spe4 = new javax.swing.JSeparator();
        lblTitleP = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        bg.setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlMatHang.setOpaque(false);

        txtSearch.setBackgroundColor(new java.awt.Color(255, 255, 255));
        txtSearch.setBorderLine(true);
        txtSearch.setHint("Tìm kiếm...");
        txtSearch.setPrefixIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search_25px.png"))); // NOI18N

        lblTitleM.setText("Cập nhật mặt hàng");

        cbLoaiDichVu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tất cả", "Đồ ăn", "Đồ uống" }));

        lblLoaiDichVu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblLoaiDichVu.setText("Loại dịch vụ");

        tableMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên mặt hàng", "Tồn kho", "Giá bán", "Thêm"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
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
        tableMatHang.setFillsViewportHeight(true);
        spTbMatHang.setViewportView(tableMatHang);
        if (tableMatHang.getColumnModel().getColumnCount() > 0) {
            tableMatHang.getColumnModel().getColumn(0).setResizable(false);
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
                    .addComponent(txtSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnlMatHangLayout.createSequentialGroup()
                        .addComponent(spe6, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblTitleM)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spe5)))
                .addContainerGap())
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlMatHangLayout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addComponent(lblLoaiDichVu)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(spTbMatHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 321, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlMatHangLayout.setVerticalGroup(
            pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMatHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblTitleM)
                    .addComponent(spe5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spe6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlMatHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbLoaiDichVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiDichVu))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spTbMatHang, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
        );

        pnlMain.add(pnlMatHang, java.awt.BorderLayout.LINE_START);

        pnlCenter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(204, 204, 204)));
        pnlCenter.setOpaque(false);

        lblTenPhong.setText("Tên phòng");

        txtTenPhong.setText("Phòng 001");
        txtTenPhong.setEnabled(false);

        lblLoaiPhong.setText("Loại phòng");

        txtLoaiPhong.setText("Phòng thường");
        txtLoaiPhong.setEnabled(false);

        tableAddMatHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tableAddMatHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã mặt hàng", "Tên mặt hàng", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableAddMatHang.setFillsViewportHeight(true);
        spTbAddMatHang.setViewportView(tableAddMatHang);
        if (tableAddMatHang.getColumnModel().getColumnCount() > 0) {
            tableAddMatHang.getColumnModel().getColumn(0).setResizable(false);
            tableAddMatHang.getColumnModel().getColumn(1).setResizable(false);
            tableAddMatHang.getColumnModel().getColumn(2).setResizable(false);
            tableAddMatHang.getColumnModel().getColumn(3).setResizable(false);
            tableAddMatHang.getColumnModel().getColumn(4).setResizable(false);
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
                        .addComponent(jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
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
                    .addComponent(txtSdt)
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
                    .addComponent(txtGiamGia, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
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
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlDatTruoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(lblTenPhong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(46, 46, 46)
                .addComponent(lblLoaiPhong)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExpand)
                .addGap(18, 18, 18))
            .addComponent(spTbAddMatHang)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addComponent(pnlTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTTHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addContainerGap(52, Short.MAX_VALUE))
        );

        pnlMain.add(pnlCenter, java.awt.BorderLayout.CENTER);

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

        javax.swing.GroupLayout pnlBottomBarLayout = new javax.swing.GroupLayout(pnlBottomBar);
        pnlBottomBar.setLayout(pnlBottomBarLayout);
        pnlBottomBarLayout.setHorizontalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNhanVien)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 488, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnGiaoPhong)
                .addGap(22, 22, 22))
        );
        pnlBottomBarLayout.setVerticalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap(16, Short.MAX_VALUE)
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGiaoPhong)
                    .addComponent(btnHuy)
                    .addComponent(lblNhanVien))
                .addGap(12, 12, 12))
        );

        pnlMain.add(pnlBottomBar, java.awt.BorderLayout.SOUTH);

        bg.add(pnlMain, java.awt.BorderLayout.CENTER);

        pnlExpand.setBackground(new java.awt.Color(255, 255, 255));

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

        lblTitleP.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblTitleP.setText("Phiếu đặt phòng");

        javax.swing.GroupLayout pnlExpandLayout = new javax.swing.GroupLayout(pnlExpand);
        pnlExpand.setLayout(pnlExpandLayout);
        pnlExpandLayout.setHorizontalGroup(
            pnlExpandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExpandLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(spe3, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblTitleP)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spe4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(spPhieuDatPhong, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        pnlExpandLayout.setVerticalGroup(
            pnlExpandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlExpandLayout.createSequentialGroup()
                .addGroup(pnlExpandLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(spe4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTitleP)
                    .addComponent(spe3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spPhieuDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
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
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(916, 631));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

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
    private javax.swing.JTextField jTextField6;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblChietKhau;
    private javax.swing.JLabel lblDaCoc;
    private javax.swing.JLabel lblGiamGia;
    private javax.swing.JLabel lblLoaiDichVu;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JLabel lblTitleM;
    private javax.swing.JLabel lblTitleP;
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
    private javax.swing.JScrollPane spTbMatHang;
    private javax.swing.JSeparator spe3;
    private javax.swing.JSeparator spe4;
    private javax.swing.JSeparator spe5;
    private javax.swing.JSeparator spe6;
    private javax.swing.JTable tableAddMatHang;
    private javax.swing.JTable tableMatHang;
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
