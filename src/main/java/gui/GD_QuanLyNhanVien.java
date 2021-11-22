package gui;

import dao.CaLam_DAO;
import dao.DiaChiMau_DAO;
import dao.LoaiNhanVien_DAO;
import dao.NhanVien_DAO;
import entity.CaLam;
import entity.LoaiNhanVien;
import entity.NhanVien;
import gui.swing.button.Button;
import gui.swing.event.EventAddNhanVien;
import gui.swing.event.EventSelectedRow;
import gui.swing.graphics.ShadowType;
import gui.swing.panel.PanelShadow;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class GD_QuanLyNhanVien extends javax.swing.JPanel {

    private String fontName = "sansserif";
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);

    private PanelShadow panelHidden;
    private final DecimalFormat df = new DecimalFormat("##0.000");
    private JTextField txtTimKiem;
    private JComboBox<Object> cmbCot;
    private JComboBox<Object> cmbGioiTinhTK;
    private JComboBox<Object> cmbLoaiNVTK;
    private JComboBox<Object> cmbCaLamTK;
    private Button btnTimKiem;
    private final NhanVien_DAO nhanVien_DAO;
    private LoaiNhanVien_DAO loaiNhanVien_DAO;
    private CaLam_DAO caLam_DAO;
    private DiaChiMau_DAO diaChiMau_DAO;

    private List<NhanVien> listNhanVien;
    private List<LoaiNhanVien> loaiNhanViens;
    private List<CaLam> caLams;

    private EventSelectedRow eventSelectedRow;
    private EventAddNhanVien eventAddNhanVien;

    private DefaultComboBoxModel<LoaiNhanVien> cmbModelLNV;
    private DefaultComboBoxModel<CaLam> cmbModelCaLam;
    
    public GD_QuanLyNhanVien() {
        initComponents();
        buildGD();

        nhanVien_DAO = new NhanVien_DAO();
        TableHandler();
        loaiNhanVien_DAO = new LoaiNhanVien_DAO();
        caLam_DAO = new CaLam_DAO();
        FormHandler();

        SearchHandler();
        AddHandler();
    }
    
    public void addEventSelectedRow(EventSelectedRow event) {
        this.eventSelectedRow = event;
    }

    public void addEventAddNhanVien(EventAddNhanVien event) {
        this.eventAddNhanVien = event;
    }

    private void buildGD() {
        pnlTop.setLayout(new MigLayout("fill, wrap", "0[fill]0", "[fill]"));
//        pnlTop.setPreferredSize(new Dimension(getWidth(), 130));
        pnlTop.add(createPanelTitle(), "h 40!");
        createPanelSearch();

        createPanelHidden();
        add(panelHidden);
    }

    /**
     * Tạo tab ẩn bên phải - hiển thị thông tin chi tiết của nhân viên
     */
    private void createPanelHidden() {
        panelHidden = new PanelShadow();
        panelHidden.setShadowType(ShadowType.TOP_LEFT);
        panelHidden.setShadowOpacity(0.3f);
        panelHidden.setShadowSize(3);
    }

    /**
     * Tạo tiêu đề có borderLine ở dưới
     *
     * @return pnlTitle
     */
    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Quản lý nhân viên");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return pnlTitle;
    }

    /**
     * Tạo các textField, combobox tìm kiếm
     */
    private void createPanelSearch() {

        /*Begin: group tìm nhân viên*/
        JPanel pnlTimKiemNV = new JPanel();
        pnlTimKiemNV.setOpaque(false);
        pnlTimKiemNV.setLayout(new MigLayout("fill", "10[center]10", "[][]"));
        pnlTop.add(pnlTimKiemNV);

        // Tìm kiếm
        txtTimKiem = new JTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font14));
//        txtTimKiem.setBorderLine(true);
//        txtTimKiem.setBorderRadius(5);
        pnlTimKiemNV.add(txtTimKiem, "w 20%, h 30!");

        //Cột cần tìm kiếm
        cmbCot = new JComboBox<>();
        cmbCot.setFont(new Font(fontName, fontPlain, font14));
//        cmbCot.setBorderLine(true);
//        cmbCot.setBorderRadius(5);
        cmbCot.addItem("Chọn cột cần tìm");
        cmbCot.addItem("Mã nhân viên");
        cmbCot.addItem("Tên nhân viên");
        cmbCot.addItem("Căn cước công dân");
        pnlTimKiemNV.add(cmbCot, "w 10%, h 30!");

        //Giới tính cần tìm
        JLabel lblGioiTinhTK = new JLabel("Giới tính:");
        lblGioiTinhTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemNV.add(lblGioiTinhTK);

        cmbGioiTinhTK = new JComboBox<>();
        cmbGioiTinhTK.setFont(new Font(fontName, fontPlain, font14));
//        cmbGioiTinhTK.setBorderLine(true);
//        cmbGioiTinhTK.setBorderRadius(5);
        cmbGioiTinhTK.addItem("Tất cả");
        cmbGioiTinhTK.addItem("Nam");
        cmbGioiTinhTK.addItem("Nữ");
        pnlTimKiemNV.add(cmbGioiTinhTK, "w 10%,h 30!");

        //Loại nhân viên cầm tìm
        JLabel lblLoaiNVTK = new JLabel("Loại nhân viên:");
        lblLoaiNVTK.setFont(new Font(fontName, fontPlain, font14));

        pnlTimKiemNV.add(lblLoaiNVTK);

//        cmbModelLNV = new DefaultComboBoxModel<>();
        cmbLoaiNVTK = new JComboBox<>();
        cmbLoaiNVTK.setFont(new Font(fontName, fontPlain, font14));
//        cmbLoaiNVTK.setBorderLine(true);
//        cmbLoaiNVTK.setBorderRadius(5);
        cmbLoaiNVTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbLoaiNVTK, "w 10%,h 30!");

        //Ca làm cần tìm
        JLabel lblCaLamTK = new JLabel("Ca làm:");
        lblCaLamTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemNV.add(lblCaLamTK, "align right");

//        cmbModelCaLam = new DefaultComboBoxModel<>();
        cmbCaLamTK = new JComboBox<>();
        cmbCaLamTK.setFont(new Font(fontName, fontPlain, font14));
//        cmbCaLamTK.setBorderLine(true);
//        cmbCaLamTK.setBorderRadius(5);
        cmbCaLamTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbCaLamTK, "w 10%,h 30!");

        //Button tìm kiếm
        btnTimKiem = new Button("Thêm");
        btnTimKiem.setFont(new Font(fontName, Font.BOLD, font16));
        btnTimKiem.setBackground(colorBtn);
        btnTimKiem.setBorderline(true);
        btnTimKiem.setBorderRadius(5);
        pnlTimKiemNV.add(btnTimKiem, "w 100!, h 36!");
        /* End: group tìm nhân viên*/

    }

    /**
     * set thuộc tính và xử lý dữ liệu của bảng
     */
    private void TableHandler() {
//        tblNhanVien.fixTable(scrTable);
        tblNhanVien.getTableHeader().setFont(new Font(fontName, Font.BOLD, 16));
    
        String html2 = "<html><head><style> body{margin: 0 ; padding: 0; background-color: #303841;} h3{color: white; padding: 0 16px;} </style></head>"
                + "<body><h3>Click chuột trái 2 lần để xem chi tiết</h3></body></html>";
        tblNhanVien.setToolTipText(html2);

        //Thêm cột có icon xóa, sửa
//        EventAction event = new EventAction() {
//            @Override
//            public void delete(Object obj) {
//                NhanVien nhanVien = (NhanVien) obj;
//                JOptionPane.showMessageDialog(null, "Delete" + nhanVien.getMaNhanVien());
//            }
//
//            @Override
//            public void update(ModelAction action) {
//                NhanVien nhanVien = (NhanVien) action.getObj();
//                action.setObj(nhanVien);
//            }
//
//        };
        loadDataNhanVien();
        tblNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //Nếu click chuột trái và click 2 lần
                if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 2) {

                    String maNhanVien = tblNhanVien.getValueAt(tblNhanVien.getSelectedRow(), 1).toString();
                    eventSelectedRow.selectedRow(nhanVien_DAO.getNhanVien(maNhanVien));
                }
            }
        });
    }

    public void loadDataNhanVien() {
        System.out.println("load");
        ((DefaultTableModel) tblNhanVien.getModel()).setRowCount(0);
        listNhanVien = nhanVien_DAO.getNhanViens();
        for (NhanVien i : listNhanVien) {
            tblNhanVien.addRow(new NhanVien(i.getMaNhanVien(), i.getTenNhanVien(), i.getLoaiNhanVien(),
                    i.getCaLam(), i.getCanCuocCD(), i.isGioiTinh(), i.getNgaySinh(), i.getSoDienThoai(),
                    i.getEmail(), i.getDiaChi(), i.getMatKhau()).convertToRowTable());
        }
    }

    /**
     * load dữ liệu lên các combobox của form tìm kiếm
     */
    private void FormHandler() {
        loaiNhanViens = loaiNhanVien_DAO.getLoaiNhanViens();
        for (LoaiNhanVien lnv : loaiNhanViens) {
            cmbLoaiNVTK.addItem(lnv.getTenLoaiNV());
        }

        caLams = caLam_DAO.getCaLams();
        for (CaLam cl : caLams) {
            cmbCaLamTK.addItem(cl.getGioBatDau() + "-" + cl.getGioKetThuc());
        }

    }

    private void SearchHandler() {
        DefaultTableModel defaultTableModel = (DefaultTableModel) tblNhanVien.getModel();
        txtTimKiem.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                defaultTableModel.setRowCount(0);
                SearchNhanVien();
            }
        });

        cmbGioiTinhTK.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                defaultTableModel.setRowCount(0);
                SearchNhanVien();
            }
        });

        cmbCaLamTK.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                defaultTableModel.setRowCount(0);
                SearchNhanVien();
            }
        });

        cmbLoaiNVTK.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                defaultTableModel.setRowCount(0);
                SearchNhanVien();
            }
        });

    }

    private void SearchNhanVien() {

        String searchOption = cmbCot.getSelectedItem().toString();
        if (searchOption.equals("Chọn cột")) {
            searchOption = "Tên nhân viên";
        }

        int gioiTinh = 2; // mặc định là lấy tất cả giới tính
        if (!cmbGioiTinhTK.getSelectedItem().equals("Tất cả")) {
            gioiTinh = cmbGioiTinhTK.getSelectedItem() == "Nam" ? 0 : 1;
        } else {
            gioiTinh = 2;
        }

        String maCaLam = "";
        if (!cmbCaLamTK.getSelectedItem().equals("Tất cả")) {
            for (CaLam cl : caLams) {
                String caLam = cl.getGioBatDau() + "-" + cl.getGioKetThuc();
                if (caLam.equals(cmbCaLamTK.getSelectedItem())) {
                    maCaLam = cl.getMaCa();
                }
            }
        }

        String maLoaiNV = "";
        System.out.println(cmbLoaiNVTK.getSelectedItem());
        if (!cmbLoaiNVTK.getSelectedItem().equals("Tất cả")) {
            for (LoaiNhanVien lnv : loaiNhanViens) {
                if (lnv.getTenLoaiNV().equals(cmbLoaiNVTK.getSelectedItem())) {
                    maLoaiNV = lnv.getMaLoaiNV();
                }
            }

        }

        List<NhanVien> nhanViens = nhanVien_DAO.searchNhanVien(txtTimKiem.getText().trim(), searchOption, gioiTinh, maLoaiNV, maCaLam);
        for (NhanVien i : nhanViens) {
            tblNhanVien.addRow(new NhanVien(i.getMaNhanVien(), i.getTenNhanVien(), i.getLoaiNhanVien(),
                    i.getCaLam(), i.getCanCuocCD(), i.isGioiTinh(), i.getNgaySinh(), i.getSoDienThoai(),
                    i.getEmail(), i.getDiaChi(), i.getMatKhau()).convertToRowTable());
        }
    }

    private void AddHandler() {
        btnTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                System.out.println("Thêm Nhân viên");
                eventAddNhanVien.AddNhanVien();

//                diaChiMau_DAO = new DiaChiMau_DAO();
//                diaChiMau_DAO.getAllTinhThanh().forEach(i -> {
//                    System.out.println(i);
//                });
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlCenter = new gui.swing.panel.PanelShadow();
        lblTitleBang = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new gui.swing.table2.MyTableFlatlaf();
        pnlTop = new gui.swing.panel.PanelShadow();

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnlCenter.setShadowOpacity(0.3F);
        pnlCenter.setShadowSize(2);
        pnlCenter.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlCenter.setLayout(new java.awt.BorderLayout());

        lblTitleBang.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTitleBang.setForeground(new java.awt.Color(4, 72, 210));
        lblTitleBang.setText("  Danh sách nhân viên");
        lblTitleBang.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        lblTitleBang.setPreferredSize(new java.awt.Dimension(130, 45));
        lblTitleBang.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pnlCenter.add(lblTitleBang, java.awt.BorderLayout.PAGE_START);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "CCCD", "Ca làm", "Loại nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblNhanVien.setRowHeight(40);
        tblNhanVien.setSelectionBackground(new java.awt.Color(239, 244, 255));
        tblNhanVien.setSelectionForeground(new java.awt.Color(0, 0, 0));
        tblNhanVien.setShowGrid(true);
        tblNhanVien.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(0).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblNhanVien.getColumnModel().getColumn(1).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(2).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(150);
            tblNhanVien.getColumnModel().getColumn(3).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(4).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(5).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(6).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(7).setResizable(false);
            tblNhanVien.getColumnModel().getColumn(8).setResizable(false);
        }

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

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
            .addGap(0, 186, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 1048, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 399, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblTitleBang;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.panel.PanelShadow pnlTop;
    private gui.swing.table2.MyTableFlatlaf tblNhanVien;
    // End of variables declaration//GEN-END:variables
}
