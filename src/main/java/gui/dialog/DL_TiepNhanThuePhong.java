package gui.dialog;

import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;
import dao.ChiTietHoaDon_DAO;
import dao.HoaDon_DAO;
import dao.KhachHang_DAO;
import dao.LoaiDichVu_DAO;
import dao.MatHang_DAO;
import dao.PhieuDatPhong_DAO;
import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.MatHang;
import entity.Phong;
import entity.NhanVien;
import entity.PhieuDatPhong;
import entity.TrangThaiPhieuDat;
import entity.TrangThaiPhong;
import gui.swing.event.EventAdd;
import gui.swing.event.EventMinus;
import gui.swing.image.WindowIcon;
import gui.swing.model.AutoID;
import gui.swing.model.ModelAdd;
import gui.swing.table.SpinnerEditor;
import gui.swing.textfield.PanelSearch;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import gui.swing.model.ModelObjectComboBox;
import java.awt.Frame;
import service.ChiTietHoaDonService;
import service.HoaDonService;
import service.KhachHangService;
import service.LoaiDichVuService;
import service.MatHangService;
import service.PhieuDatPhongService;

public class DL_TiepNhanThuePhong extends javax.swing.JDialog {
    
    private final DecimalFormat df = new DecimalFormat("#,##0");
    private final SimpleDateFormat formatterGio = new SimpleDateFormat("HH:mm");
    private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final MatHangService matHangService;
    private final HoaDonService hoaDonService;
    private PhieuDatPhongService phieuDatPhongService;
    private final KhachHangService khachHangService;
    private final ChiTietHoaDonService chiTietHoaDonService;
    private final LoaiDichVuService loaiDichVuService;
    private final HoaDon hoaDon;
    private PanelSearch search;
    private JPopupMenu menu;
    private Thread thread;
    private boolean start = true;
    private List<MatHang> dsMatHang;
    private EventAdd event;
    private List<PhieuDatPhong> dsPhieuDat;
    private PhieuDatPhong phieuDatPhong;

    public PhieuDatPhong getPhieuDatPhong() {
        return phieuDatPhong;
    }

    public void setPhieuDatPhong(PhieuDatPhong phieuDatPhong) {
        this.phieuDatPhong = phieuDatPhong;
        loadDataForm();
    }

    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public DL_TiepNhanThuePhong(Frame frame, Phong phong, NhanVien nv) {
        super(frame, true);
        this.hoaDonService = new HoaDon_DAO();
        this.matHangService = new MatHang_DAO();
        this.khachHangService = new KhachHang_DAO();
        this.chiTietHoaDonService = new ChiTietHoaDon_DAO();
        this.loaiDichVuService = new LoaiDichVu_DAO();
        this.phieuDatPhongService = new PhieuDatPhong_DAO();
        String maxID = hoaDonService.getMaxID();
        String maHoaDon;
        if (maxID == null) {
            maHoaDon = "HD0000001";
        } else {
            maHoaDon = AutoID.generateId(maxID, "HD");
        }
        this.hoaDon = new HoaDon(maHoaDon, phong, nv);
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        createTableMatHang();
        createTableCTHoaDon();
        createTablePhieuDatPhong();
        createTxtKhachHang();
        createDateChooseThoiGianBatDau();
        createDateChooseThoiGianKetThuc();
        loadDataForm();

    }

    private void createTableMatHang() {
        loadDataTableMatHang();
    }

    private void createTableCTHoaDon() {
        tableCTHoaDon.getColumnModel().getColumn(2).setCellEditor(new SpinnerEditor(200));
        loadDataTableCTHoaDon();
    }

    private void createTablePhieuDatPhong() {
        loadDataTablePhieuDatPhong();
    }

    private void createDateChooseThoiGianBatDau() {
        thoiGianBatDau.getDatePicker().getSettings().setAllowEmptyDates(false);
        thoiGianBatDau.getTimePicker().getSettings().setAllowEmptyTimes(false);
        thoiGianBatDau.getTimePicker().getSettings().setDisplayToggleTimeMenuButton(false);
        thoiGianBatDau.getTimePicker().getSettings().setDisplaySpinnerButtons(true);
        thoiGianBatDau.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TextFieldBackgroundDisabled, Color.WHITE);
        thoiGianBatDau.getTimePicker().getSettings().setColor(TimePickerSettings.TimeArea.TimePickerTextDisabled, Color.BLACK);
        thoiGianBatDau.getTimePicker().setEnabled(false);
        thoiGianBatDau.getTimePicker().getSettings().setFormatForDisplayTime("HH:mm");
        thoiGianBatDau.getDatePicker().setEnabled(false);
        thread = new Thread(() -> {
            while (start) {
                thoiGianBatDau.getTimePicker().setTime(LocalTime.now());
            }
        });
        thread.start();
        thoiGianBatDau.getDatePicker().getSettings().setLocale(new Locale("vi"));
        thoiGianBatDau.getDatePicker().getSettings().setFormatForDatesCommonEra("yyyy/MM/dd");
        thoiGianBatDau.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.TextFieldBackgroundDisabled, Color.WHITE);
        thoiGianBatDau.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.DatePickerTextDisabled, Color.BLACK);
        thoiGianBatDau.getDatePicker().getSettings().setFormatForDatesBeforeCommonEra("uuuu/MM/dd");
        thoiGianBatDau.addDateTimeChangeListener(new DateTimeChangeListener() {
            @Override
            public void dateOrTimeChanged(DateTimeChangeEvent event) {
                thoiGianDateOrTimeChanged(event);
            }
        });
    }

    private void createDateChooseThoiGianKetThuc() {
        thoiGianKetThuc.getDatePicker().getSettings().setAllowEmptyDates(false);
        thoiGianKetThuc.getTimePicker().getSettings().setAllowEmptyTimes(false);
        thoiGianKetThuc.getDatePicker().getSettings().setAllowEmptyDates(false);
        thoiGianKetThuc.getTimePicker().getSettings().setAllowEmptyTimes(false);
        thoiGianKetThuc.getTimePicker().setTimeToNow();
        thoiGianKetThuc.getTimePicker().getSettings().setVetoPolicy((LocalTime time) -> PickerUtilities.isLocalTimeInRange(time,
                LocalTime.now(),
                LocalTime.MAX, true));
        thoiGianKetThuc.getTimePicker().getSettings().setDisplaySpinnerButtons(true);
        thoiGianKetThuc.getTimePicker().getSettings().generatePotentialMenuTimes(TimePickerSettings.TimeIncrement.FifteenMinutes, null, null);
        thoiGianKetThuc.getTimePicker().getSettings().use24HourClockFormat();
        thoiGianKetThuc.getTimePicker().getSettings().setFormatForDisplayTime("HH:mm");
        thoiGianKetThuc.getTimePicker().getSettings().setFormatForMenuTimes("HH:mm");
        thoiGianKetThuc.getDatePicker().getSettings().setAllowKeyboardEditing(false);
        LocalDate today = LocalDate.now();
        thoiGianKetThuc.getDatePicker().getSettings().setDateRangeLimits(today, LocalDate.MAX);
        thoiGianKetThuc.getDatePicker().getSettings().setColor(DatePickerSettings.DateArea.CalendarBackgroundVetoedDates, Color.LIGHT_GRAY);
        thoiGianKetThuc.getDatePicker().getSettings().setLocale(new Locale("vi"));
        thoiGianKetThuc.getDatePicker().getSettings().setFormatForDatesCommonEra("yyyy/MM/dd");
        thoiGianKetThuc.getDatePicker().getSettings().setFormatForDatesBeforeCommonEra("uuuu/MM/dd");
        thoiGianKetThuc.addDateTimeChangeListener(new DateTimeChangeListener() {
            @Override
            public void dateOrTimeChanged(DateTimeChangeEvent event) {
                thoiGianDateOrTimeChanged(event);
            }
        });
    }

    private void loadDataForm() {
        if (hoaDon.getPhong() != null) {
            ((DefaultComboBoxModel) cbLoaiDichVu.getModel()).addAll(loaiDichVuService.getDsTenLoaiDichVu());
            txtMaHoaDon.setText(hoaDon.getMaHoaDon());
            txtTenPhong.setText(hoaDon.getPhong().getTenPhong());
            txtLoaiPhong.setText(hoaDon.getPhong().getLoaiPhong().getTenLoaiPhong());
            txtGiaPhong.setText(df.format(hoaDon.getPhong().getLoaiPhong().getGiaPhong()));
            txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
        }
        if (hoaDon.getNhanVien() != null) {
            lblNhanVien.setText(hoaDon.getNhanVien().getTenNhanVien());
            lblRole.setText(hoaDon.getNhanVien().getLoaiNhanVien().getTenLoaiNV());
        }
        if (phieuDatPhong != null) {
            txtTenKhachHang.setText(phieuDatPhong.getKhachHang().getTenKhachHang());
            txtSdt.setText(phieuDatPhong.getKhachHang().getSoDienThoai());
            txtCCCD.setText(phieuDatPhong.getKhachHang().getCanCuocCD());
            txtDaCoc.setText(df.format(phieuDatPhong.getTienCoc()));
            txtTenKhachHang.setEnabled(false);
            txtSdt.setEnabled(false);
            txtCCCD.setEnabled(false);
            btnExpand.setEnabled(false);
            phieuDatPhong.setTrangThai(TrangThaiPhieuDat.DA_TIEP_NHAN);
            hoaDon.setTienCoc(phieuDatPhong.getTienCoc());
            hoaDon.setKhachHang(phieuDatPhong.getKhachHang());
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
                    txtTienPhongDuKien.setText(df.format(hoaDon.getDonGiaPhong()));
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

    private void loadDataTableMatHangCa() {
        ((DefaultTableModel) tableMatHang.getModel()).setRowCount(0);
        dsMatHang.forEach(matHang -> {
            ((DefaultTableModel) tableMatHang.getModel()).addRow(new Object[]{new ModelObjectComboBox(matHang.getTenMatHang(), matHang.getMaMatHang()),
                matHang.getsLTonKho(),
                df.format(matHang.getDonGia()),
                new ModelAdd(matHang, event)});
        });
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
                    loadDataTableMatHangCa();
                    txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
                    txtTienPhongDuKien.setText(df.format(hoaDon.getDonGiaPhong()));
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
                    loadDataTableMatHangCa();
                    txtTongTienMatHang.setText(df.format(hoaDon.getTongTienMatHang()));
                    txtTienPhongDuKien.setText(df.format(hoaDon.getDonGiaPhong()));
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

    private void loadDataTablePhieuDatPhong() {
        dsPhieuDat = phieuDatPhongService.getPhieuHomNay(hoaDon.getPhong().getMaPhong());
        if (dsPhieuDat != null) {
            for (PhieuDatPhong phieuDatPhong : dsPhieuDat) {
                ((DefaultTableModel) tablePhieuDatPhong.getModel()).addRow(new Object[]{phieuDatPhong.getMaPhieuDat(),
                    phieuDatPhong.getKhachHang().getTenKhachHang(),
                    formatterGio.format(phieuDatPhong.getNgayDat().getTime())});
            }
        }
    }

    private void createTxtKhachHang() {
        menu = new JPopupMenu();
        search = new PanelSearch("tenKhachHang");
        menu.setBorder(BorderFactory.createLineBorder(new Color(164, 164, 164)));
        menu.add(search);
        menu.setFocusable(false);
        search.addEventClick((Object obj) -> {
            KhachHang khachHang = (KhachHang) obj;
            hoaDon.setKhachHang(khachHang);
            menu.setVisible(false);
            txtTenKhachHang.setText(khachHang.getTenKhachHang());
            txtSdt.setText(khachHang.getSoDienThoai());
            txtCCCD.setText(khachHang.getCanCuocCD());
        });
    }

    private void searchKhachHang(JTextField textField) {
        try {
            String text = textField.getText().trim().toLowerCase();
            List<KhachHang> dsKhachHang = khachHangService.getDsKhachHangLimit(text);
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
                String soDienThoai = txtSdt.getText().trim();
                String cccd = txtCCCD.getText().trim();
                KhachHang khachHang = new KhachHang(maKhachHang, tenKhachHang, cccd, soDienThoai);
                hoaDon.setKhachHang(khachHang);
            }

        } catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex) {
            Logger.getLogger(DL_TiepNhanThuePhong.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadDataTTKH() {
        KhachHang khachHang = (KhachHang) search.getSelectedRow();
        hoaDon.setKhachHang(khachHang);
        setData();
        hoaDon.setTienCoc(0);
        menu.setVisible(false);
    }

    private void setData() {
        txtTenKhachHang.setText(hoaDon.getKhachHang().getTenKhachHang());
        txtCCCD.setText(hoaDon.getKhachHang().getCanCuocCD());
        txtSdt.setText(hoaDon.getKhachHang().getSoDienThoai());
    }

    private void thoiGianDateOrTimeChanged(DateTimeChangeEvent event) {
        Object obj = event.getSource();
        if (obj.equals(thoiGianKetThuc)) {
            try {
                Date date;
                date = sdf.parse(event.getNewDateTimePermissive().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                hoaDon.setThoiGianKetThuc(date);
                txtTienPhongDuKien.setText(df.format(hoaDon.getDonGiaPhong()));
            } catch (ParseException ex) {
                Logger.getLogger(DL_TiepNhanThuePhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                Date date;
                date = sdf.parse(event.getNewDateTimePermissive().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
                hoaDon.setThoiGianBatDau(date);
                txtTienPhongDuKien.setText(df.format(hoaDon.getDonGiaPhong()));
            } catch (ParseException ex) {
                Logger.getLogger(DL_TiepNhanThuePhong.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void searchMatHang() {
        ((DefaultTableModel) tableMatHang.getModel()).setRowCount(0);
        String tenMatHang = txtSearch.getText().trim();
        String tenLoaiDichVu = String.valueOf(cbLoaiDichVu.getSelectedItem());
        if (!tenLoaiDichVu.equals("Tất cả") && tenMatHang.length() > 0) {
            dsMatHang.stream().filter(matHang -> ((matHang.getTenMatHang().toLowerCase().contains(tenMatHang.toLowerCase())) && (matHang.getLoaiDichVu().getTenLoaiDichVu().equals(tenLoaiDichVu)))).forEachOrdered(matHang -> {
                ((DefaultTableModel) tableMatHang.getModel()).addRow(new Object[]{new ModelObjectComboBox(matHang.getTenMatHang(), matHang.getMaMatHang()),
                    matHang.getsLTonKho(),
                    df.format(matHang.getDonGia()),
                    new ModelAdd(matHang, event)});
            });
        } else if (!tenLoaiDichVu.equals("Tất cả") || tenMatHang.length() > 0) {
            dsMatHang.forEach(matHang -> {
                if (!tenLoaiDichVu.equals("Tất cả")) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateTimePicker1 = new com.github.lgooddatepicker.components.DateTimePicker();
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
        tableMatHang = new gui.swing.table.MyTableFlatlaf();
        pnlCenter = new javax.swing.JPanel();
        pnlTGThuePhong = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        thoiGianBatDau = new com.github.lgooddatepicker.components.DateTimePicker();
        thoiGianKetThuc = new com.github.lgooddatepicker.components.DateTimePicker();
        txtTienPhongDuKien = new gui.swing.textfield.MyTextFieldPerUnit();
        lblTienPhongDuKien = new javax.swing.JLabel();
        pnlTTHD = new javax.swing.JPanel();
        lblDaCoc = new javax.swing.JLabel();
        txtDaCoc = new gui.swing.textfield.MyTextFieldPerUnit();
        txtMaHoaDon = new javax.swing.JTextField();
        lblMaHoaDon = new javax.swing.JLabel();
        btnExpand = new javax.swing.JToggleButton();
        pnlTTKH = new javax.swing.JPanel();
        lblTenKhachHang = new javax.swing.JLabel();
        txtTenKhachHang = new javax.swing.JTextField();
        lblCCCD = new javax.swing.JLabel();
        txtCCCD = new javax.swing.JTextField();
        lblSdt = new javax.swing.JLabel();
        txtSdt = new javax.swing.JTextField();
        pnlTTPhong = new javax.swing.JPanel();
        txtTenPhong = new javax.swing.JTextField();
        lblTenPhong = new javax.swing.JLabel();
        txtLoaiPhong = new javax.swing.JTextField();
        lblLoaiPhong = new javax.swing.JLabel();
        txtGiaPhong = new gui.swing.textfield.MyTextFieldPerUnit();
        lblGiaPhong = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableCTHoaDon = new gui.swing.table.MyTableFlatlaf();
        txtTongTienMatHang = new gui.swing.textfield.MyTextFieldPerUnit();
        pnlExpand = new javax.swing.JPanel();
        spPhieuDatPhong = new javax.swing.JScrollPane();
        tablePhieuDatPhong = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("TIếp nhận thuê phòng");
        setModal(true);
        setResizable(false);

        bg.setLayout(new java.awt.BorderLayout());

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));
        pnlMain.setLayout(new java.awt.BorderLayout());

        pnlBottomBar.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottomBar.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(204, 204, 204)));

        btnGiaoPhong.setText("Giao phòng");
        btnGiaoPhong.setBackground(new java.awt.Color(54, 88, 153));
        btnGiaoPhong.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnGiaoPhong.setForeground(new java.awt.Color(255, 255, 255));
        btnGiaoPhong.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGiaoPhongActionPerformed(evt);
            }
        });

        btnHuy.setText("Hủy");
        btnHuy.setBackground(new java.awt.Color(54, 88, 153));
        btnHuy.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        lblNhanVien.setText("Đỗ Huy Hoàng");
        lblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        lblRole.setText("Quản lý");
        lblRole.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        javax.swing.GroupLayout pnlBottomBarLayout = new javax.swing.GroupLayout(pnlBottomBar);
        pnlBottomBar.setLayout(pnlBottomBarLayout);
        pnlBottomBarLayout.setHorizontalGroup(
            pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomBarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlBottomBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblRole))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 530, Short.MAX_VALUE)
                .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGiaoPhong)
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
                    .addComponent(btnGiaoPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 405, Short.MAX_VALUE)
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
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 437, Short.MAX_VALUE))
        );

        jSplitPane1.setLeftComponent(pnlMatHang);

        pnlCenter.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 1, 0, 1, new java.awt.Color(204, 204, 204)));
        pnlCenter.setOpaque(false);

        pnlTGThuePhong.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thời gian thuê phòng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTGThuePhong.setOpaque(false);

        jLabel10.setText("Nhận");
        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel11.setText("Hẹn trả");
        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTienPhongDuKien.setEnabled(false);
        txtTienPhongDuKien.setUnit("VND");

        lblTienPhongDuKien.setText("Tiền phòng dự kiến");
        lblTienPhongDuKien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout pnlTGThuePhongLayout = new javax.swing.GroupLayout(pnlTGThuePhong);
        pnlTGThuePhong.setLayout(pnlTGThuePhongLayout);
        pnlTGThuePhongLayout.setHorizontalGroup(
            pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTGThuePhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                        .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(thoiGianBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(thoiGianKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lblTienPhongDuKien)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTienPhongDuKien, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTGThuePhongLayout.setVerticalGroup(
            pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTGThuePhongLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(thoiGianBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(thoiGianKetThuc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTGThuePhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienPhongDuKien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTienPhongDuKien))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTTHD.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thông tin hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTTHD.setOpaque(false);

        lblDaCoc.setText("Đã cọc");
        lblDaCoc.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtDaCoc.setEnabled(false);
        txtDaCoc.setUnit("VND");

        txtMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtMaHoaDon.setEnabled(false);

        lblMaHoaDon.setText("Mã hóa đơn");
        lblMaHoaDon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout pnlTTHDLayout = new javax.swing.GroupLayout(pnlTTHD);
        pnlTTHD.setLayout(pnlTTHDLayout);
        pnlTTHDLayout.setHorizontalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTHDLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTHDLayout.createSequentialGroup()
                        .addComponent(lblDaCoc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtDaCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTHDLayout.createSequentialGroup()
                        .addComponent(lblMaHoaDon)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMaHoaDon, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlTTHDLayout.setVerticalGroup(
            pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTHDLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaHoaDon)
                    .addComponent(lblMaHoaDon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTTHDLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtDaCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDaCoc))
                .addGap(41, 41, 41))
        );

        btnExpand.setText("Phiếu đặt phòng");
        btnExpand.setBackground(new java.awt.Color(54, 88, 153));
        btnExpand.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        btnExpand.setForeground(new java.awt.Color(255, 255, 255));
        btnExpand.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                btnExpandItemStateChanged(evt);
            }
        });

        pnlTTKH.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thông tin khách hàng", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTTKH.setOpaque(false);

        lblTenKhachHang.setText("Tên khách hàng");
        lblTenKhachHang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTenKhachHang.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
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

        lblCCCD.setText("CCCD");
        lblCCCD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtCCCD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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

        lblSdt.setText("Số điện thoại");
        lblSdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtSdt.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtSdt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSdtFocusGained(evt);
            }
        });
        txtSdt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSdtKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtSdtKeyReleased(evt);
            }
        });

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
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlTTKHLayout.setVerticalGroup(
            pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTKHLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenKhachHang))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCCCD))
                .addGap(10, 10, 10)
                .addGroup(pnlTTKHLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSdt))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlTTPhong.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(204, 204, 204), 1, true), "Thông tin hóa đơn", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N
        pnlTTPhong.setOpaque(false);

        txtTenPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtTenPhong.setText("Phòng 001");
        txtTenPhong.setEnabled(false);

        lblTenPhong.setText("Tên phòng");
        lblTenPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtLoaiPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLoaiPhong.setText("Phòng thường");
        txtLoaiPhong.setEnabled(false);

        lblLoaiPhong.setText("Loại phòng");
        lblLoaiPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtGiaPhong.setEnabled(false);
        txtGiaPhong.setUnit("đ/giờ");

        lblGiaPhong.setText("Giá phòng");
        lblGiaPhong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        javax.swing.GroupLayout pnlTTPhongLayout = new javax.swing.GroupLayout(pnlTTPhong);
        pnlTTPhong.setLayout(pnlTTPhongLayout);
        pnlTTPhongLayout.setHorizontalGroup(
            pnlTTPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTPhongLayout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addGroup(pnlTTPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTPhongLayout.createSequentialGroup()
                        .addComponent(lblTenPhong)
                        .addGap(6, 6, 6))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTTPhongLayout.createSequentialGroup()
                        .addGroup(pnlTTPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblGiaPhong)
                            .addComponent(lblLoaiPhong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(pnlTTPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtGiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                    .addComponent(txtTenPhong))
                .addContainerGap())
        );
        pnlTTPhongLayout.setVerticalGroup(
            pnlTTPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTTPhongLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlTTPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTTPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLoaiPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLoaiPhong))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlTTPhongLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGiaPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblGiaPhong))
                .addContainerGap())
        );

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
            .addComponent(jScrollPane2)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnExpand)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTongTienMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(pnlCenterLayout.createSequentialGroup()
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlCenterLayout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCenterLayout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(pnlTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(0, 0, 0)
                        .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pnlTTPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pnlTTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlCenterLayout.setVerticalGroup(
            pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCenterLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnExpand)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtTongTienMatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTTKH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTTPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlCenterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTGThuePhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlTTHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        tablePhieuDatPhong.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablePhieuDatPhongMousePressed(evt);
            }
        });
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlExpandLayout.createSequentialGroup()
                .addComponent(spPhieuDatPhong, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
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
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
        );

        setSize(new java.awt.Dimension(1018, 632));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        dispose();
    }//GEN-LAST:event_btnHuyActionPerformed

    private void btnExpandItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_btnExpandItemStateChanged
        int state = evt.getStateChange();
        if (state == ItemEvent.SELECTED) {
            setSize(new java.awt.Dimension(getPreferredSize()));
            setLocationRelativeTo(null);
        } else {
            setSize(new java.awt.Dimension(1018, 632));
            setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_btnExpandItemStateChanged

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
                loadDataTableMatHangCa();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Mặt hàng không đủ số lượng!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                loadDataTableCTHoaDon();
            }
        } else {

        }
    }//GEN-LAST:event_tableCTHoaDonKeyReleased

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

    private void txtTenKhachHangFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtTenKhachHangFocusGained
        search.setColumnName("tenKhachHang");
        if (search.getItemSize() > 0) {
            menu.show(txtTenKhachHang, 0, txtTenKhachHang.getHeight());
            search.clearSelected();
            searchKhachHang(txtTenKhachHang);
        }
    }//GEN-LAST:event_txtTenKhachHangFocusGained

    private void txtCCCDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCCCDFocusGained
        search.setColumnName("canCuocCD");
        if (search.getItemSize() > 0) {
            menu.show(txtCCCD, 0, txtCCCD.getHeight());
            search.clearSelected();
            searchKhachHang(txtCCCD);
        }
    }//GEN-LAST:event_txtCCCDFocusGained

    private void txtSdtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSdtFocusGained
        search.setColumnName("soDienThoai");
        if (search.getItemSize() > 0) {
            menu.show(txtSdt, 0, txtSdt.getHeight());
            search.clearSelected();
            searchKhachHang(txtSdt);
        }
    }//GEN-LAST:event_txtSdtFocusGained

    private void txtSdtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKeyReleased
        if (evt.getKeyCode() != KeyEvent.VK_UP && evt.getKeyCode() != KeyEvent.VK_DOWN && evt.getKeyCode() != KeyEvent.VK_ENTER) {
            searchKhachHang(txtSdt);
        }
    }//GEN-LAST:event_txtSdtKeyReleased

    private void txtSdtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSdtKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_UP) {
            search.keyUp();
        } else if (evt.getKeyCode() == KeyEvent.VK_DOWN) {
            search.keyDown();
        } else if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            loadDataTTKH();
        }
    }//GEN-LAST:event_txtSdtKeyPressed

    private void btnGiaoPhongActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGiaoPhongActionPerformed
        hoaDon.getPhong().setTrangThai(TrangThaiPhong.DANG_HAT);
        khachHangService.themKhachHang(hoaDon.getKhachHang());
        hoaDonService.addHoaDon(hoaDon);
        hoaDon.getDsChiTietHoaDon().forEach(chiTietHoaDon -> {
            chiTietHoaDonService.addChiTietHoaDon(chiTietHoaDon);
        });
        if (phieuDatPhong != null) {
            phieuDatPhongService.capNhatPhieuDatPhong(phieuDatPhong);
        }
        start = false;
        dispose();
    }//GEN-LAST:event_btnGiaoPhongActionPerformed

    private void txtSearchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchKeyReleased
        searchMatHang();
    }//GEN-LAST:event_txtSearchKeyReleased

    private void cbLoaiDichVuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiDichVuActionPerformed
        searchMatHang();
    }//GEN-LAST:event_cbLoaiDichVuActionPerformed

    private void tablePhieuDatPhongMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablePhieuDatPhongMousePressed
        int row = tablePhieuDatPhong.getSelectedRow();
        if (row != -1) {
            phieuDatPhong = dsPhieuDat.get(row);
            phieuDatPhong.setTrangThai(TrangThaiPhieuDat.DA_TIEP_NHAN);
            hoaDon.setKhachHang(phieuDatPhong.getKhachHang());
            hoaDon.setTienCoc(phieuDatPhong.getTienCoc());
            setData();
            txtDaCoc.setText(df.format(hoaDon.getTienCoc()));
            txtTenKhachHang.setEnabled(false);
            txtSdt.setEnabled(false);
            txtCCCD.setEnabled(false);
        }
    }//GEN-LAST:event_tablePhieuDatPhongMousePressed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JToggleButton btnExpand;
    private javax.swing.JButton btnGiaoPhong;
    private javax.swing.JButton btnHuy;
    private javax.swing.JComboBox<String> cbLoaiDichVu;
    private com.github.lgooddatepicker.components.DateTimePicker dateTimePicker1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblDaCoc;
    private javax.swing.JLabel lblGiaPhong;
    private javax.swing.JLabel lblLoaiDichVu;
    private javax.swing.JLabel lblLoaiPhong;
    private javax.swing.JLabel lblMaHoaDon;
    private javax.swing.JLabel lblNhanVien;
    private javax.swing.JLabel lblRole;
    private javax.swing.JLabel lblSdt;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JLabel lblTenPhong;
    private javax.swing.JLabel lblTienPhongDuKien;
    private javax.swing.JPanel pnlBottomBar;
    private javax.swing.JPanel pnlCenter;
    private javax.swing.JPanel pnlExpand;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlMatHang;
    private javax.swing.JPanel pnlTGThuePhong;
    private javax.swing.JPanel pnlTTHD;
    private javax.swing.JPanel pnlTTKH;
    private javax.swing.JPanel pnlTTPhong;
    private javax.swing.JScrollPane spPhieuDatPhong;
    private gui.swing.table.MyTableFlatlaf tableCTHoaDon;
    private gui.swing.table.MyTableFlatlaf tableMatHang;
    private javax.swing.JTable tablePhieuDatPhong;
    private com.github.lgooddatepicker.components.DateTimePicker thoiGianBatDau;
    private com.github.lgooddatepicker.components.DateTimePicker thoiGianKetThuc;
    private javax.swing.JTextField txtCCCD;
    private gui.swing.textfield.MyTextFieldPerUnit txtDaCoc;
    private gui.swing.textfield.MyTextFieldPerUnit txtGiaPhong;
    private javax.swing.JTextField txtLoaiPhong;
    private javax.swing.JTextField txtMaHoaDon;
    private javax.swing.JTextField txtSdt;
    private gui.swing.textfield.MyTextField txtSearch;
    private javax.swing.JTextField txtTenKhachHang;
    private javax.swing.JTextField txtTenPhong;
    private gui.swing.textfield.MyTextFieldPerUnit txtTienPhongDuKien;
    private gui.swing.textfield.MyTextFieldPerUnit txtTongTienMatHang;
    // End of variables declaration//GEN-END:variables

}
