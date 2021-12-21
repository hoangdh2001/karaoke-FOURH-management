package gui.dialog;

import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.PhieuDatPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.GD_Chinh;
import gui.swing.model.AutoID;
import gui.swing.textfield.PanelSearch;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import gui.swing.model.ModelObjectComboBox;
import service.KhachHangService;
import service.LoaiPhongService;
import service.PhieuDatPhongService;
import service.PhongService;

public class DL_TiepNhanDatPhong extends javax.swing.JDialog {

    private PhieuDatPhong phieuDatPhong;
    private final DecimalFormat df = new DecimalFormat("#,##0");
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private KhachHangService khachHangService;
    private PhieuDatPhongService phieuDatPhongService;
    private LoaiPhongService loaiPhongService;
    private PhongService phongService;
    private JPopupMenu menu;
    private PanelSearch search;

    public PhieuDatPhong getPhieuDatPhong() {
        return phieuDatPhong;
    }

    public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        this.phieuDatPhong = phieuDatPhong;
        initUpdate();
    }

    public void setPhong(Phong phong) throws Exception {
        phieuDatPhong.setPhong(phong);
        txtTenPhong.setText(phieuDatPhong.getPhong().getTenPhong());
        txtLoaiPhongTT.setText(phieuDatPhong.getPhong().getLoaiPhong().getTenLoaiPhong());
        txtGia.setText(df.format(phieuDatPhong.getPhong().getLoaiPhong().getGiaPhong()));
    }

    public DL_TiepNhanDatPhong(java.awt.Frame parent) {
        super(parent, true);
        khachHangService = new KhachHang_DAO();
        phieuDatPhongService = new PhieuDatPhong_DAO();
        loaiPhongService = new LoaiPhong_DAO();
        phongService = new Phong_DAO();
        String maxID = phieuDatPhongService.getMaxID();
        String maPhieu;
        if (maxID == null) {
            maPhieu = "PD0000001";
        } else {
            maPhieu = AutoID.generateId(maxID, "PD");
        }
        this.phieuDatPhong = new PhieuDatPhong(maPhieu, GD_Chinh.NHAN_VIEN);
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        initData();
        createDatePicker();
        createTxtKhachHang();
    }

    private void initData() {
        txtMaPhieu.setText(phieuDatPhong.getMaPhieuDat());
        loaiPhongService.getDsLoaiPhong().forEach(doc -> {
            cmbLoaiPhong.addItem(new ModelObjectComboBox(doc.getTenLoaiPhong(), doc.getMaLoaiPhong()));
        });
    }

    private void initUpdate() {
        if (phieuDatPhong != null) {
            btnDat.setText("Chỉnh sửa");
            setTitle("Sửa phiếu đặt phòng");
            txtMaPhieu.setText(phieuDatPhong.getMaPhieuDat());
            txtTenKhachHang.setText(phieuDatPhong.getKhachHang().getTenKhachHang());
            txtCCCD.setText(phieuDatPhong.getKhachHang().getCanCuocCD());
            txtSDT.setText(phieuDatPhong.getKhachHang().getSoDienThoai());
            txtTenPhong.setText(phieuDatPhong.getPhong().getTenPhong());
            txtLoaiPhongTT.setText(phieuDatPhong.getPhong().getLoaiPhong().getTenLoaiPhong());
            txtGia.setText(df.format(phieuDatPhong.getPhong().getLoaiPhong().getGiaPhong()));
            txtTienCoc.setText(df.format(phieuDatPhong.getTienCoc()));
            thoiGianBatDau.setDateTimePermissive(convertToLocalDateTimeViaMilisecond(phieuDatPhong.getNgayDat()));
            thoiGianBatDau.getDatePicker().setDate(phieuDatPhong.getNgayDat().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            cmbLoaiPhong.setSelectedItem(new ModelObjectComboBox(phieuDatPhong.getPhong().getLoaiPhong().getTenLoaiPhong(), phieuDatPhong.getPhong().getLoaiPhong().getMaLoaiPhong()));
            addDataToTable(thoiGianBatDau.getDatePicker().getText(), phieuDatPhong.getPhong().getLoaiPhong().getMaLoaiPhong());
        }
    }

    public LocalDateTime convertToLocalDateTimeViaMilisecond(Date dateToConvert) {
        return Instant.ofEpochMilli(dateToConvert.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private void createDatePicker() {
        thoiGianBatDau.setBackground(Color.WHITE);
//        date
        thoiGianBatDau.getDatePicker().getSettings().setAllowEmptyDates(false);
        thoiGianBatDau.getDatePicker().setBackground(Color.WHITE);
        thoiGianBatDau.getDatePicker().getSettings().setAllowKeyboardEditing(false);
        LocalDate today = LocalDate.now();
        thoiGianBatDau.getDatePicker().getSettings().setDateRangeLimits(today, LocalDate.MAX);
        thoiGianBatDau.getDatePicker().getSettings().setLocale(new Locale("vi"));
        thoiGianBatDau.getDatePicker().getSettings().setFormatForDatesCommonEra("yyyy-MM-dd");
        thoiGianBatDau.getDatePicker().getSettings().setFormatForDatesBeforeCommonEra("uuuu-MM-dd");

//        time
        thoiGianBatDau.getTimePicker().getSettings().setAllowEmptyTimes(false);
        thoiGianBatDau.getTimePicker().setBackground(Color.WHITE);
        if (thoiGianBatDau.getDatePicker().getDate().equals(LocalDate.now())) {
            thoiGianBatDau.getTimePicker().setTimeToNow();
            thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(time,
                    LocalTime.now(),
                    LocalTime.MAX, true));

        }
        thoiGianBatDau.getTimePicker().getSettings().generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, null, null);
        thoiGianBatDau.getTimePicker().getSettings().use24HourClockFormat();
        thoiGianBatDau.getTimePicker().getSettings().setFormatForDisplayTime("HH:mm");
        thoiGianBatDau.getTimePicker().getSettings().setFormatForMenuTimes("HH:mm");

        thoiGianBatDau.getDatePicker().addDateChangeListener((var event) -> {
            if (thoiGianBatDau.getDatePicker().getDate().equals(LocalDate.now())) {
                thoiGianBatDau.getTimePicker().setTimeToNow();
                thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(time,
                        LocalTime.now(),
                        LocalTime.MAX, true));
            } else {
                thoiGianBatDau.getTimePicker().setTime(LocalTime.of(6, 0));
                thoiGianBatDau.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(
                        time,
                        LocalTime.of(6, 0),
                        LocalTime.MAX, true));
            }
        });

        thoiGianBatDau.addDateTimeChangeListener((var event) -> {
            try {
                Date date;
                date = sdf.parse(event.getNewDateTimePermissive().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                phieuDatPhong.setNgayDat(date);
                timPhongPhuHop();
            } catch (ParseException ex) {
                Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void createTxtKhachHang() {
        menu = new JPopupMenu();
        search = new PanelSearch("tenKhachHang");
        menu.setBorder(BorderFactory.createLineBorder(new Color(164, 164, 164)));
        menu.add(search);
        menu.setFocusable(false);
        search.addEventClick((Object obj) -> {
            try {
                KhachHang khachHang = (KhachHang) obj;
                phieuDatPhong.setKhachHang(khachHang);
                menu.setVisible(false);
                txtTenKhachHang.setText(khachHang.getTenKhachHang());
                txtSDT.setText(khachHang.getSoDienThoai());
                txtCCCD.setText(khachHang.getCanCuocCD());
            } catch (Exception ex) {
                Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    private void searchKhachHang(JTextField textField) {
        try {
            String text = textField.getText().trim().toLowerCase();
            List<KhachHang> dsKhachHang = khachHangService.getDsKhachHangLimit(text);
            System.out.println(dsKhachHang);
            search.setData(dsKhachHang);
            if (search.getItemSize() > 0) {
                menu.show(textField, 0, textField.getHeight());
                menu.setPopupSize(menu.getWidth(), (search.getItemSize() * 30) + 2);
            } else {
                menu.setVisible(false);
                String maxID = khachHangService.getMaxID();
                String maKhachHang;
                if (maxID == null) {
                    maKhachHang = "KH0000001";
                } else {
                    maKhachHang = AutoID.generateId(khachHangService.getMaxID(), "KH");
                }
                String tenKhachHang = txtTenKhachHang.getText().trim();
                String soDienThoai = txtSDT.getText().trim();
                String cccd = txtCCCD.getText().trim();
                KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, cccd, soDienThoai);
                phieuDatPhong.setKhachHang(khachHang);
            }

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DL_TiepNhanThuePhong.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataTTKH() {
        try {
            KhachHang khachHang = (KhachHang) search.getSelectedRow();
            phieuDatPhong.setKhachHang(khachHang);
            txtTenKhachHang.setText(khachHang.getTenKhachHang());
            txtCCCD.setText(khachHang.getCanCuocCD());
            txtSDT.setText(khachHang.getSoDienThoai());
            menu.setVisible(false);
        } catch (Exception ex) {
            Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void deleteTableData() {
        DefaultTableModel model = (DefaultTableModel) tblPhong.getModel();
        model.setRowCount(0);
    }

    public void addDataToTable(String date, String maLoaiPhong) {
        deleteTableData();
        List<Phong> dsPhong = phongService.getDSPhongChuaDat(date, maLoaiPhong);
        for (Phong phong : dsPhong) {
            tblPhong.addRow(new Object[]{new ModelObjectComboBox(phong.getTenPhong(), phong.getMaPhong()),
                phong.getLoaiPhong().getTenLoaiPhong(),
                phong.getTang(),
                df.format(phong.getLoaiPhong().getGiaPhong())});
        }
    }

    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private boolean validateData() {
        String tienCoc = txtTienCoc.getText().trim();
        String hoTen = txtTenKhachHang.getText().trim();
        String CCCD = txtCCCD.getText().trim();
        String sdt = txtSDT.getText().trim();

        if (phieuDatPhong.getPhong() == null) {
            showMsg("Chọn phòng");
            return false;
        }

        if (tienCoc.trim().equals("")) {
            showMsg("Nhập số tiền đặt cọc !");
            txtTienCoc.requestFocus();
            return false;
        }

        if (sdt.trim().equals("")) {
            showMsg("Số điện thoại khách hàng trống !");
            txtSDT.requestFocus();
            return false;
        } else {
            if (!(sdt.matches("^[0-9]{10}$"))) {
                showMsg("Số điện thoại khách hàng không hợp lệ");
                txtSDT.requestFocus();
                txtSDT.selectAll();
                return false;
            }
        }

        if (hoTen.trim().equals("")) {
            showMsg("Họ tên khách hàng trống !");
            txtTenKhachHang.requestFocus();
            return false;
        }

        if (CCCD.trim().equals("")) {
            showMsg("Căn cước công dân không được trống !");
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

    public boolean validataTienCoc() {
        double tienCoc = 0;
        try {
            tienCoc = Double.parseDouble(txtTienCoc.getText().trim());
        } catch (Exception e) {
            tienCoc = Double.parseDouble(txtTienCoc.getText().trim().replace(",", ""));
        }

        double tienPhong = 0;

        tienPhong = Double.parseDouble(txtGia.getText().replace(",", ""));

        if (tienCoc < tienPhong / 2) {
            showMsg("Số tiền đặt cọc phải lớn hơn 50%(" + tienPhong / 2 + ") số tiền phòng̣");
            txtTienCoc.requestFocus();
            txtTienCoc.selectAll();
            return false;
        }
        return true;
    }

    private void timPhongPhuHop() {
        String date = thoiGianBatDau.getDatePicker().getText() + " " + thoiGianBatDau.getTimePicker().getText();
        if (!date.equals("")) {
            String maLoaiPhong = "";
            if (cmbLoaiPhong.getSelectedIndex() != -1) {
                ModelObjectComboBox cb = (ModelObjectComboBox) cmbLoaiPhong.getSelectedItem();
                maLoaiPhong = cb.getMa();
            }
            addDataToTable(date, maLoaiPhong);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        srcBang = new javax.swing.JScrollPane();
        tblPhong = new gui.swing.table.MyTableFlatlaf();
        thoiGianBatDau = new com.github.lgooddatepicker.components.DateTimePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cmbLoaiPhong = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        txtTenPhong = new javax.swing.JTextField();
        txtLoaiPhongTT = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtSDT = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnDat = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();
        txtGia = new gui.swing.textfield.MyTextFieldPerUnit();
        txtTienCoc = new gui.swing.textfield.MyTextFieldPerUnit();
        jLabel11 = new javax.swing.JLabel();
        txtMaPhieu = new javax.swing.JTextField();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Đặt phòng");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        tblPhong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên phòng", "Loại phòng", "Tầng", "Giá phòng/giờ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhong.setRowHeight(30);
        tblPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tblPhongMousePressed(evt);
            }
        });
        srcBang.setViewportView(tblPhong);
        if (tblPhong.getColumnModel().getColumnCount() > 0) {
            tblPhong.getColumnModel().getColumn(0).setResizable(false);
            tblPhong.getColumnModel().getColumn(1).setResizable(false);
            tblPhong.getColumnModel().getColumn(2).setResizable(false);
            tblPhong.getColumnModel().getColumn(3).setResizable(false);
        }

        jLabel2.setText("Ngày giờ đặt:");

        jLabel3.setText("Loại phòng:");

        cmbLoaiPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbLoaiPhongActionPerformed(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 0, 0, new java.awt.Color(0, 0, 0)), "Thông tin đặt phòng", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.DEFAULT_POSITION));
        jPanel2.setOpaque(false);

        jLabel4.setText("Tên phòng:");

        txtTenPhong.setEnabled(false);

        txtLoaiPhongTT.setEnabled(false);

        jLabel5.setText("Loại phòng:");

        jLabel6.setText("Giá phòng:");

        txtTenKhachHang.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtTenKhachHangFocusGained(evt);
            }
        });
        txtTenKhachHang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtTenKhachHangKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtTenKhachHangKeyReleased(evt);
            }
        });

        jLabel7.setText("Tên khách hàng:");

        txtSDT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSDTFocusGained(evt);
            }
        });
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSDTKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSDTKeyReleased(evt);
            }
        });

        jLabel8.setText("SDT:");

        txtCCCD.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCCCDFocusGained(evt);
            }
        });
        txtCCCD.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCCCDKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCCCDKeyReleased(evt);
            }
        });

        jLabel9.setText("CCCD:");

        jLabel10.setText("Tiền cọc:");

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnDat.setText("Đặt phòng");
        btnDat.setBackground(new java.awt.Color(54, 88, 153));
        btnDat.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnDat.setForeground(new java.awt.Color(255, 255, 255));
        btnDat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setBackground(new java.awt.Color(54, 88, 153));
        btnHuy.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        txtGia.setEnabled(false);
        txtGia.setUnit("VND");

        txtTienCoc.setUnit("VND");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel6)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtGia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel5)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtLoaiPhongTT, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel9)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel10)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHuy)
                        .addGap(18, 18, 18)
                        .addComponent(btnDat)))
                .addGap(18, 18, 18))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtLoaiPhongTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(jLabel9)
                            .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jLabel11.setText("Mã phiếu:");

        txtMaPhieu.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(srcBang)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(thoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cmbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(thoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cmbLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(srcBang, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txtTenKhachHangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKhachHangKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            searchKhachHang(txtTenKhachHang);
        }
    }//GEN-LAST:event_txtTenKhachHangKeyReleased

    private void txtTenKhachHangKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTenKhachHangKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            search.keyUp();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            search.keyDown();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadDataTTKH();
        }
    }//GEN-LAST:event_txtTenKhachHangKeyPressed

    private void txtTenKhachHangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKhachHangFocusGained
        search.setColumnName("tenKhachHang");
        if (search.getItemSize() > 0) {
            menu.show(txtTenKhachHang, 0, txtTenKhachHang.getHeight());
            search.clearSelected();
            searchKhachHang(txtTenKhachHang);
        }
    }//GEN-LAST:event_txtTenKhachHangFocusGained

    private void txtSDTKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            searchKhachHang(txtSDT);
        }
    }//GEN-LAST:event_txtSDTKeyReleased

    private void txtSDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            search.keyUp();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            search.keyDown();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadDataTTKH();
        }
    }//GEN-LAST:event_txtSDTKeyPressed

    private void txtSDTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSDTFocusGained
        search.setColumnName("soDienThoai");
        if (search.getItemSize() > 0) {
            menu.show(txtSDT, 0, txtSDT.getHeight());
            search.clearSelected();
            searchKhachHang(txtSDT);
        }
    }//GEN-LAST:event_txtSDTFocusGained

    private void txtCCCDKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCCDKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            searchKhachHang(txtCCCD);
        }
    }//GEN-LAST:event_txtCCCDKeyReleased

    private void txtCCCDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCCCDKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            search.keyUp();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            search.keyDown();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadDataTTKH();
        }
    }//GEN-LAST:event_txtCCCDKeyPressed

    private void txtCCCDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCCDFocusGained
        search.setColumnName("canCuocCD");
        if (search.getItemSize() > 0) {
            menu.show(txtCCCD, 0, txtCCCD.getHeight());
            search.clearSelected();
            searchKhachHang(txtCCCD);
        }
    }//GEN-LAST:event_txtCCCDFocusGained

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed

        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnDatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatActionPerformed
        if (validateData() && validataTienCoc()) {
            try {
                phieuDatPhong.setTienCoc(Double.parseDouble(txtTienCoc.getText().replace(",", "")));
                khachHangService.themKhachHang(phieuDatPhong.getKhachHang());
                if (phieuDatPhongService.addPhieuDatPhong(phieuDatPhong)) {
                    showMsg("Đặt thành công " + phieuDatPhong.getPhong().getTenPhong() + " vào lúc: " + sdf.format(phieuDatPhong.getNgayDat()));
                    long diff = (phieuDatPhong.getNgayDat().getTime() - new Date().getTime()) / 1000;
                    int diffHours = (int) (diff / 3600);
                    if (diffHours < 2) {
                        phieuDatPhong.getPhong().setTrangThai(TrangThaiPhong.DAT_TRUOC);
                        phongService.updatePhong(phieuDatPhong.getPhong());
                    }
                    dispose();
                } else {
                    showMsg("Chọn thời gian!");
                }
            } catch (Exception ex) {
                Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDatActionPerformed

    private void tblPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPhongMousePressed
        int row = tblPhong.getSelectedRow();
        if (row != -1) {
            try {
                ModelObjectComboBox cb = (ModelObjectComboBox) tblPhong.getValueAt(row, 0);
                txtTenPhong.setText(cb.toString());
                txtLoaiPhongTT.setText(tblPhong.getValueAt(row, 1).toString());
                txtGia.setText(tblPhong.getValueAt(row, 3).toString());
                phieuDatPhong.setPhong(phongService.getPhong(cb.getMa()));
            } catch (Exception ex) {
                Logger.getLogger(DL_TiepNhanDatPhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_tblPhongMousePressed

    private void cmbLoaiPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbLoaiPhongActionPerformed
        timPhongPhuHop();
    }//GEN-LAST:event_cmbLoaiPhongActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDat;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<Object> cmbLoaiPhong;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JScrollPane srcBang;
    private gui.swing.table.MyTableFlatlaf tblPhong;
    private com.github.lgooddatepicker.components.DateTimePicker thoiGianBatDau;
    private javax.swing.JTextField txtCCCD;
    private gui.swing.textfield.MyTextFieldPerUnit txtGia;
    private javax.swing.JTextField txtLoaiPhongTT;
    private javax.swing.JTextField txtMaPhieu;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenPhong;
    private gui.swing.textfield.MyTextFieldPerUnit txtTienCoc;
    // End of variables declaration//GEN-END:variables
}
