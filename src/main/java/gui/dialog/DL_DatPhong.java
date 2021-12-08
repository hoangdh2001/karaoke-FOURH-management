package gui.dialog;

import com.github.lgooddatepicker.components.DateTimePicker;
import com.github.lgooddatepicker.components.TimePickerSettings;
import com.github.lgooddatepicker.optionalusertools.DateTimeChangeListener;
import com.github.lgooddatepicker.optionalusertools.PickerUtilities;
import com.github.lgooddatepicker.zinternaltools.DateTimeChangeEvent;
import dao.KhachHang_DAO;
import dao.LoaiPhong_DAO;
import dao.NhaCungCapVaNhapHang_DAO;
import dao.NhanVien_DAO;
import dao.PhieuDatPhong_DAO;
import dao.Phong_DAO;
import entity.KhachHang;
import entity.NhanVien;
import entity.Phong;
import entity.PhieuDatPhong;
import gui.GD_Chinh;
import gui.swing.button.Button;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import objectcombobox.ObjectComboBox;
import service.KhachHangService;
import service.LoaiPhongService;
import service.PhieuDatPhongService;
import service.PhongService;

public class DL_DatPhong extends javax.swing.JDialog {

    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhaphang_DAO;
    private PhieuDatPhongService phieuDatPhongDao;
    private PhongService phongDao;
    private Phong_DAO phongDAO;
    private LoaiPhongService loaiPhongDao;
    private KhachHangService khachHangDao;
    private MyTextField txtSDT;
    private MyTextField txtTenKH;
    private MyTextField txtCCCD;
    private MyTextField txtTenPhong;
    private MyTextField txtLoaiPhongTT;
    private MyTextField txtGia;
    private MyTextField txtTienCoc;
    private DateTimePicker thoiGianBatDau;
    private MyComboBox cmbLoaiPhong;
    
    private SimpleDateFormat gio;

    private Button btnHuy;
    private Button btnDat;
    private NhanVien nhanVien;
    private PhieuDatPhong phieuDatPhong;
    
    private DecimalFormat df;
    
    public DL_DatPhong(PhieuDatPhong phieuDatPhong) {
        super();
        initComponents();
        buildGD();
        tblPhieu.fixTable(scrBang);
        this.nhanVien = GD_Chinh.NHAN_VIEN;
        this.phieuDatPhong = phieuDatPhong;
        initData();
        createDatePicker();
        addAction();
    }

    public void buildGD() {
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 14;
        Color colorBtn = new Color(184, 238, 241);

        pnlTop.setPreferredSize(new Dimension(900, 300));
        pnlTop.setLayout(new MigLayout());
        pnlTop.add(pnlLoc, " w 70%, h 50!, wrap");
        pnlTop.add(pnlBang, " w 100%, h 250!");

        /**
         * End: group lưa chọn phòng hát //
         */
//        JPanel pnlChonPhong = new JPanel();
        pnlLoc.setOpaque(false);
        pnlLoc.setLayout(new MigLayout("", "[center][center]30[center][center]", ""));
//        pnlLoc.add(pnlChonPhong, "w 100%,h 10%, wrap");

        //Ngày giờ đặt
        JLabel lblNgayGio = new JLabel("Ngày giờ đặt:");
        lblNgayGio.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLoc.add(lblNgayGio, "align right");

        thoiGianBatDau = new DateTimePicker();
        pnlLoc.add(thoiGianBatDau, "w 50%, h 36!");

        //Loại phòng hát
        JLabel lblLoaiPhong = new JLabel("Loại phòng:");
        lblLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLoc.add(lblLoaiPhong, "align right");

        cmbLoaiPhong = new MyComboBox<>();
        cmbLoaiPhong.setFont(new Font(fontName, fontStyle, fontSize));
        cmbLoaiPhong.addItem("--Chọn--");
        cmbLoaiPhong.setBorderLine(true);
        cmbLoaiPhong.setBorderRadius(10);
        pnlLoc.add(cmbLoaiPhong, "w 40%,h 30!");

        //Bảng thông tin phòng đặt
        pnlBang.setOpaque(false);
        pnlBang.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Bảng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));

        pnlBottom.setPreferredSize(new Dimension(900, 350));
        pnlBottom.setLayout(new MigLayout("", "3[center] 20 [center]3", "10[center]10[center]10"));
        /**
         * Begin: thông tin Phòng đặt
         */
        JPanel pnlThongTinPD = new JPanel();
        pnlThongTinPD.setOpaque(false);
        pnlThongTinPD.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Thông tin phòng đặt   ", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));
        pnlThongTinPD.setLayout(new MigLayout("", "[center][center]", "10[center]15[center]15[center]15[center]0"));
        pnlBottom.add(pnlThongTinPD, "w 50%, h 80%");

        //Tên phòng
        JLabel lblTenPhong = new JLabel("Tên phòng:");
        lblTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblTenPhong, "align right");

        txtTenPhong = new MyTextField();
        txtTenPhong.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenPhong.setBorderLine(true);
        txtTenPhong.setEnabled(false);
        txtTenPhong.setEditable(false);
        txtTenPhong.setBorderRadius(5);
        pnlThongTinPD.add(txtTenPhong, "w 80%, h 36!, wrap");

        //Loại phòng
        JLabel lblLoaiPhongTT = new JLabel("Loại phòng: ");
        lblLoaiPhongTT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblLoaiPhongTT, "align right");

        txtLoaiPhongTT = new MyTextField();
        txtLoaiPhongTT.setFont(new Font(fontName, fontStyle, fontSize));
        txtLoaiPhongTT.setBorderLine(true);
        txtLoaiPhongTT.setEnabled(false);
        txtLoaiPhongTT.setEditable(false);
        txtLoaiPhongTT.setBorderRadius(5);
        pnlThongTinPD.add(txtLoaiPhongTT, "w 80%, h 36!, wrap");

        //Giá phòng
        JLabel lblGia = new JLabel("Giá:");
        lblGia.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblGia, "align right");

        txtGia = new MyTextField();
        txtGia.setFont(new Font(fontName, fontStyle, fontSize));
        txtGia.setBorderLine(true);
        txtGia.setEnabled(false);
        txtGia.setEditable(false);
        txtGia.setBorderRadius(5);
        pnlThongTinPD.add(txtGia, "w 80%, h 36!, wrap");

        //Số giờ dự tính sử dụng của khách hàng
        JLabel lblGioSuDungDuTinh = new JLabel("Tiền cọc:");
        lblGioSuDungDuTinh.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinPD.add(lblGioSuDungDuTinh, "align right");

        txtTienCoc = new MyTextField();
        txtTienCoc.setFont(new Font(fontName, fontStyle, fontSize));
        txtTienCoc.setBorderLine(true);
        txtTienCoc.setBorderRadius(5);
        pnlThongTinPD.add(txtTienCoc, "w 80%, h 36!");

        /**
         * end: group thông tin phòng đặt
         */
        JSeparator spr = new JSeparator(SwingConstants.VERTICAL);
        spr.setPreferredSize(new Dimension(2, 200));
        pnlBottom.add(spr);

        /**
         * Begin: group thông tin khách hàng
         */
        JPanel pnlThongTinKH = new JPanel();
        pnlThongTinKH.setOpaque(false);
        pnlThongTinKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.WHITE, 2), "Khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), new Color(4, 72, 210)));
        pnlThongTinKH.setLayout(new MigLayout("", "[center][center]", "10[center]15[center]15[center]15[center]10"));
        pnlBottom.add(pnlThongTinKH, "w 50%, h 80%, wrap");

        //Số điện thoại của khách hàng
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblSDT, "align right");

        txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        txtSDT.setBorderLine(true);
        txtSDT.setBorderRadius(5);
        pnlThongTinKH.add(txtSDT, "w 80%, h 36!, wrap");

        //Tên khách hàng
        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblTenKH, "align right");

        txtTenKH = new MyTextField();
        txtTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        txtTenKH.setBorderLine(true);
        txtTenKH.setBorderRadius(5);
        pnlThongTinKH.add(txtTenKH, "w 80%, h 36!, wrap");

        //Căn cước công dân
        JLabel lblCCCD = new JLabel("Căn cước công dân:");
        lblCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblCCCD, "align right");

        txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        txtCCCD.setBorderLine(true);
        txtCCCD.setBorderRadius(5);
        pnlThongTinKH.add(txtCCCD, "w 80%, h 36!, wrap");

        /**
         * End: Group thông tin khách hàng
         */
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        //pnlButton.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2), "Button", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlButton.setLayout(new MigLayout("", "push[center]20[center]13", "0[center]"));
        pnlBottom.add(pnlButton, "span,w 100%, h 40!");

        // Nút Hủy
        btnHuy = new Button("Hủy");
        btnHuy.setFont(new Font(fontName, fontStyle, fontSize));
        btnHuy.setBackground(colorBtn);
        btnHuy.setBorderline(true);
        btnHuy.setBorderRadius(5);
        pnlButton.add(btnHuy, "w 100!, h 36!");

        // Nút Đặt phòng
        btnDat = new Button("Đặt Phòng");
        btnDat.setFont(new Font(fontName, fontStyle, fontSize));
        btnDat.setBackground(colorBtn);
        btnDat.setBorderline(true);
        btnDat.setBorderRadius(5);
        pnlButton.add(btnDat, "align right, w 100!, h 36!");

        pnlLarge.setPreferredSize(new Dimension(1000, 680));
        setSize(1000, 680);
        setTitle("CỬA SỔ PHÒNG ĐẶT");
        setResizable(false);
        setSize(new Dimension(1200, 650));
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLarge = new gui.swing.panel.PanelShadow();
        pnlTop = new gui.swing.panel.PanelShadow();
        pnlLoc = new gui.swing.panel.PanelShadow();
        pnlBang = new gui.swing.panel.PanelShadow();
        scrBang = new javax.swing.JScrollPane();
        tblPhieu = new gui.swing.table2.MyTable();
        pnlBottom = new gui.swing.panel.PanelShadow();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        pnlLarge.setBackground(new java.awt.Color(255, 255, 255));
        pnlLarge.setShadowOpacity(0.0F);
        pnlLarge.setShadowSize(1);

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

        pnlLoc.setBackground(new java.awt.Color(255, 255, 255));
        pnlLoc.setShadowOpacity(0.0F);
        pnlLoc.setShadowSize(3);

        javax.swing.GroupLayout pnlLocLayout = new javax.swing.GroupLayout(pnlLoc);
        pnlLoc.setLayout(pnlLocLayout);
        pnlLocLayout.setHorizontalGroup(
            pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlLocLayout.setVerticalGroup(
            pnlLocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 68, Short.MAX_VALUE)
        );

        pnlBang.setBackground(new java.awt.Color(255, 255, 255));
        pnlBang.setShadowColor(new java.awt.Color(255, 255, 255));
        pnlBang.setShadowOpacity(0.0F);
        pnlBang.setShadowSize(3);

        tblPhieu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên Phòng", "Loại Phòng", "Giá Phòng/ Giờ"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblPhieu.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        scrBang.setViewportView(tblPhieu);
        if (tblPhieu.getColumnModel().getColumnCount() > 0) {
            tblPhieu.getColumnModel().getColumn(0).setResizable(false);
            tblPhieu.getColumnModel().getColumn(1).setResizable(false);
            tblPhieu.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout pnlBangLayout = new javax.swing.GroupLayout(pnlBang);
        pnlBang.setLayout(pnlBangLayout);
        pnlBangLayout.setHorizontalGroup(
            pnlBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrBang, javax.swing.GroupLayout.DEFAULT_SIZE, 1010, Short.MAX_VALUE)
        );
        pnlBangLayout.setVerticalGroup(
            pnlBangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrBang, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlLoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addComponent(pnlLoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlLargeLayout = new javax.swing.GroupLayout(pnlLarge);
        pnlLarge.setLayout(pnlLargeLayout);
        pnlLargeLayout.setHorizontalGroup(
            pnlLargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlLargeLayout.setVerticalGroup(
            pnlLargeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLargeLayout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(274, 274, 274))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLarge, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLarge, javax.swing.GroupLayout.PREFERRED_SIZE, 508, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DL_DatPhong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PhieuDatPhong pdp = new PhieuDatPhong_DAO().getPhieuById("PD0000001");
                DL_DatPhong dialog = new DL_DatPhong(null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    public void initData() {
        this.nhanVien = new NhanVien_DAO().getNhanVien("NV0001");
        df = new DecimalFormat("#,###");
        khachHangDao = new KhachHang_DAO();
        phieuDatPhongDao = new PhieuDatPhong_DAO();
        loaiPhongDao = new LoaiPhong_DAO();
        phongDao = new Phong_DAO();
        nhaCungCapVaNhaphang_DAO = new NhaCungCapVaNhapHang_DAO();
        phongDAO = new Phong_DAO();
        loaiPhongDao.getDsLoaiPhong().forEach(doc -> {
            cmbLoaiPhong.addItem(new ObjectComboBox(doc.getTenLoaiPhong(), doc.getMaLoaiPhong()));
        });
        initUpdate();
    }
    
    public void initUpdate(){
        if(phieuDatPhong !=null){
            btnDat.setText("Chỉnh sửa");
            txtTenKH.setText(phieuDatPhong.getKhachHang().getTenKhachHang());
            txtCCCD.setText(phieuDatPhong.getKhachHang().getCanCuocCD());
            txtSDT.setText(phieuDatPhong.getKhachHang().getSoDienThoai());
            txtTenPhong.setText(phieuDatPhong.getPhong().getTenPhong());
            txtLoaiPhongTT.setText(phieuDatPhong.getPhong().getLoaiPhong().getTenLoaiPhong());
            txtGia.setText(df.format(phieuDatPhong.getPhong().getLoaiPhong().getGiaPhong()));
            txtTienCoc.setText(df.format(phieuDatPhong.getTienCoc()));
            thoiGianBatDau.getDatePicker().setDate(phieuDatPhong.getNgayDat().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            cmbLoaiPhong.setSelectedItem(new ObjectComboBox(phieuDatPhong.getPhong().getLoaiPhong().getTenLoaiPhong(), phieuDatPhong.getPhong().getLoaiPhong().getMaLoaiPhong()));
            addDataToTable(thoiGianBatDau.getDatePicker().getText(), phieuDatPhong.getPhong().getLoaiPhong().getMaLoaiPhong());
        }
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

            System.out.println("ok 0");
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
            timPhongPhuHop();
        });
    }

    public void addAction() {
        txtSDT.addKeyListener(new createKeyListenner());
        btnDat.addActionListener(new createActionListener());
        cmbLoaiPhong.addActionListener(new createActionListener());
        tblPhieu.addMouseListener(new createMouseListener());
        txtTienCoc.addKeyListener(new createKeyListenner());
    }

    public void initKhachHang(String sdt) {
        KhachHang kh = khachHangDao.getKhachHangBySDT(sdt);
        if (kh != null) {
            txtTenKH.setText(kh.getTenKhachHang());
            txtCCCD.setText(kh.getCanCuocCD());
        }
    }

    public void deleteTableData() {
        DefaultTableModel model = (DefaultTableModel) tblPhieu.getModel();
        model.setRowCount(0);
    }

    public void addDataToTable(String date, String maLoaiPhong) {
        deleteTableData();
        phongDao.getDSPhongChuaDat(date, maLoaiPhong).forEach(doc -> {
            tblPhieu.addRow(doc.convertToRowTableInGDDatPhong());
        });
    }

    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }


    private boolean validateData() {
        String tienCoc = txtTienCoc.getText().trim();
        String hoTen = txtTenKH.getText().trim();
        String CCCD = txtCCCD.getText().trim();
        String sdt = txtSDT.getText().trim();

        if (tblPhieu.getSelectedRow() == -1) {
            showMsg("Chọn phòng");
            return false;
        }

        if (tienCoc.trim().equals("")) {
            showMsg("Nhập số tiền đặt cọc !");
            txtTienCoc.selectAll();
            txtTienCoc.requestFocus();
            return false;
        }

        if (sdt.trim().equals("")) {
            showMsg("Số điện thoại khách hàng trống !");
            txtSDT.selectAll();
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
            txtTenKH.selectAll();
            txtTenKH.requestFocus();
            return false;
        } else {

            if ((hoTen.matches(
                    "^[a-zA-Z ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂ ưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]"))) {
                showMsg("tên khách hàng không hợp lệ");
                txtTenKH.requestFocus();
                txtTenKH.selectAll();
                return false;
            }
        }

        if (CCCD.trim().equals("")) {
            showMsg("Căn cước công dân không được trống !");
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

    public boolean validataTienCoc() {
        
        double tienCoc = 0;
        try {
            tienCoc = Double.parseDouble(txtTienCoc.getText().trim());
        } catch (Exception e) {
            tienCoc = Double.parseDouble(txtTienCoc.getText().trim().replace(",", ""));
        }

        double tienPhong = 0;
        if (tblPhieu.getSelectedRow() != -1) {
            tienPhong = Double.parseDouble(tblPhieu.getValueAt(tblPhieu.getSelectedRow(), 2).toString().replace(",", ""));
        }

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
            if (cmbLoaiPhong.getSelectedIndex() != 0) {
                ObjectComboBox cb = (ObjectComboBox) cmbLoaiPhong.getSelectedItem();
                maLoaiPhong = cb.getMa();
            }
            addDataToTable(date, maLoaiPhong);
        }
    }

    class createKeyListenner implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Object obj = e.getSource();
            if (e.getKeyChar() == KeyEvent.VK_ENTER && obj.equals(txtSDT)) {
                String sdt = txtSDT.getText().trim();
                if (!sdt.equals("")) {
                    initKhachHang(sdt);
                }
            } else if (e.getKeyChar() == KeyEvent.VK_ENTER && obj.equals(txtTienCoc)) {
                try {
                    DecimalFormat df;
                    df = new DecimalFormat("#,###");
                    txtTienCoc.setText(df.format(Double.parseDouble(txtTienCoc.getText())));
                    validataTienCoc();
                } catch (Exception e2) {
                    showMsg("Tiền cọc không hợp lệ");
                    txtTienCoc.requestFocus();
                    txtTienCoc.selectAll();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    class createActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String date = thoiGianBatDau.getDatePicker().getText() + " " + thoiGianBatDau.getTimePicker().getText();
            if (e.getSource().equals(btnDat) && validateData() && validataTienCoc()) {
                if(phieuDatPhong != null){
                    KhachHang kh = phieuDatPhong.getKhachHang();
                    kh.setCanCuocCD(txtCCCD.getText().trim());
                    kh.setSoDienThoai(txtSDT.getText().trim());
                    kh.setTenKhachHang(txtTenKH.getText().trim());
                      
                    
                    phieuDatPhong.setTienCoc(Double.parseDouble(txtTienCoc.getText().replace(",", "")));
                    SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");  
                    try {  
                        Date dateFormat =formatter.parse(date);
                        phieuDatPhong.setNgayDat(dateFormat);
                    } catch (ParseException ex) {
                        Logger.getLogger(DL_DatPhong.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    ObjectComboBox cb = (ObjectComboBox) tblPhieu.getValueAt(tblPhieu.getSelectedRow(), 0);
                    Phong phong = phongDAO.getPhong(cb.getMa());
                    
                    phieuDatPhong.setPhong(phong);
                    
//                    phieuDatPhong.setNgayDat();
                    
                    phieuDatPhongDao.updatePhieuDatPhong(phieuDatPhong);
                    
                    showMsg("Chỉnh sửa thông tin thành công");
                    dispose();
                }else{
                    KhachHang kh = khachHangDao.getKhachHangBySDT(txtSDT.getText());
                    if (kh == null) {
                        String maKhachhang = khachHangDao.getlastKhachHangTang();
                        kh = new KhachHang(maKhachhang,
                                txtTenKH.getText(),
                                txtCCCD.getText(),
                                txtSDT.getText());
                        khachHangDao.addKhachHang(kh);
                    }

                    ObjectComboBox cb = (ObjectComboBox) tblPhieu.getValueAt(tblPhieu.getSelectedRow(), 0);
                    Phong phong = phongDAO.getPhong(cb.getMa());
                    String maPhieuDat = phieuDatPhongDao.getLastPhieuDatPhong();

                    PhieuDatPhong phieu = new PhieuDatPhong(maPhieuDat, kh, phong, Double.parseDouble(txtTienCoc.getText().replace(",", "")),nhanVien);

                    try {
                        phieuDatPhongDao.addPhieuDatPhong(phieu, date);
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    showMsg("Đặt thành công " + phong.getTenPhong() + " vào lúc: " + date);
                    dispose();
                }

            } else if (e.getSource().equals(btnHuy)) {
                dispose();
            } else if (e.getSource().equals(cmbLoaiPhong)) {
                timPhongPhuHop();
            }
        }
    }

    class createMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getSource().equals(tblPhieu)) {
                int row = tblPhieu.getSelectedRow();
                if (row != -1) {
                    ObjectComboBox cb = (ObjectComboBox) tblPhieu.getValueAt(row, 0);
                    txtTenPhong.setText(cb.toString());
                    txtLoaiPhongTT.setText(tblPhieu.getValueAt(row, 1).toString());
                    txtGia.setText(tblPhieu.getValueAt(row, 2).toString());
                }
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow pnlBang;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlLarge;
    private gui.swing.panel.PanelShadow pnlLoc;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane scrBang;
    private gui.swing.table2.MyTable tblPhieu;
    // End of variables declaration//GEN-END:variables

}
