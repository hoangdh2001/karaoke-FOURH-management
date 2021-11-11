package gui;

import dao.CaLam_DAO;
import dao.LoaiNhanVien_DAO;
import dao.NhanVien_DAO;
import entity.CaLam;
import entity.LoaiNhanVien;
import entity.NhanVien;

import gui.dropshadow.ShadowType;
import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.table2.EventAction;
import gui.swing.table2.ModelAction;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.text.DecimalFormat;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import gui.event.EventSelectedRow;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.DefaultComboBoxModel;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.Keymap;

public class GD_NhanVien extends JPanel {

    private String fontName = "sansserif";
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);

    private PanelShadow panelHidden;
    private final DecimalFormat df = new DecimalFormat("##0.000");
    private MyTextField txtTimKiem;
    private MyComboBox<Object> cmbCot;
    private MyComboBox<Object> cmbGioiTinhTK;
    private MyComboBox<Object> cmbLoaiNVTK;
    private MyComboBox<Object> cmbCaLamTK;
    private Button btnTimKiem;
    private NhanVien_DAO nhanVien_DAO;
    private LoaiNhanVien_DAO loaiNhanVien_DAO;
    private CaLam_DAO caLam_DAO;

    private List<NhanVien> listNhanVien;
    private List<LoaiNhanVien> loaiNhanViens;
    private List<CaLam> caLams;

    private EventSelectedRow eventSelectedRow;
    private DefaultComboBoxModel<LoaiNhanVien> cmbModelLNV;
    private DefaultComboBoxModel<CaLam> cmbModelCaLam;

    public GD_NhanVien() {
        initComponents();
        setPreferredSize(new Dimension(getWidth(), 1000));
        buildGD();

        nhanVien_DAO = new NhanVien_DAO();
        TableHandler();
        loaiNhanVien_DAO = new LoaiNhanVien_DAO();
        caLam_DAO = new CaLam_DAO();
        FormHandler();

        SearchHandler();
    }

    public void addEvent(EventSelectedRow event) {
        this.eventSelectedRow = event;
    }

    private void buildGD() {
        pnlTop.setLayout(new MigLayout("", "[][]", "[]"));
        pnlTop.setPreferredSize(new Dimension(getWidth(), 130));
        pnlTop.add(createPanelTitle(), "w 100%, h 36!, wrap");
        createPanelSearch();

        createPanelHidden();
        add(panelHidden);
    }

    /**
     * Tạo tab ẩn bên phải - hiển thị thông tin chi tiết của nhân viên
     */
    private void createPanelHidden() {
        panelHidden = new PanelShadow();
        panelHidden.setShadowType(ShadowType.CENTER);
        panelHidden.setShadowOpacity(0.3f);
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
        lblTitle.setText("Tìm kiếm");
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
        pnlTimKiemNV.setLayout(new MigLayout("", "10[center]10[center]10[center][center]10[center][center]10[center][center]20[center]10", "[]"));
        pnlTop.add(pnlTimKiemNV, "w 100%, h 100%");

        // Tìm kiếm
        txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontPlain, font14));
        txtTimKiem.setBorderLine(true);
        txtTimKiem.setBorderRadius(5);
        pnlTimKiemNV.add(txtTimKiem, "w 30%, h 40!");

        //Cột cần tìm kiếm
        cmbCot = new MyComboBox<>();
        cmbCot.setFont(new Font(fontName, fontPlain, font14));
        cmbCot.setBorderLine(true);
        cmbCot.setBorderRadius(5);
        cmbCot.addItem("Chọn cột cần tìm");
        pnlTimKiemNV.add(cmbCot, "w 10%, h 40!");

        //Giới tính cần tìm
        JLabel lblGioiTinhTK = new JLabel("Giới tính:");
        lblGioiTinhTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemNV.add(lblGioiTinhTK);

        cmbGioiTinhTK = new MyComboBox<>();
        cmbGioiTinhTK.setFont(new Font(fontName, fontPlain, font14));
        cmbGioiTinhTK.setBorderLine(true);
        cmbGioiTinhTK.setBorderRadius(5);
        cmbGioiTinhTK.addItem("Tất cả");
        cmbGioiTinhTK.addItem("Nam");
        cmbGioiTinhTK.addItem("Nữ");
        pnlTimKiemNV.add(cmbGioiTinhTK, "w 10%,h 40!");

        //Loại nhân viên cầm tìm
        JLabel lblLoaiNVTK = new JLabel("Loại nhân viên:");
        lblLoaiNVTK.setFont(new Font(fontName, fontPlain, font14));

        pnlTimKiemNV.add(lblLoaiNVTK);

//        cmbModelLNV = new DefaultComboBoxModel<>();
        cmbLoaiNVTK = new MyComboBox<>();
        cmbLoaiNVTK.setFont(new Font(fontName, fontPlain, font14));
        cmbLoaiNVTK.setBorderLine(true);
        cmbLoaiNVTK.setBorderRadius(5);
        cmbLoaiNVTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbLoaiNVTK, "w 10%,h 40!");

        //Ca làm cần tìm
        JLabel lblCaLamTK = new JLabel("Ca làm:");
        lblCaLamTK.setFont(new Font(fontName, fontPlain, font14));
        pnlTimKiemNV.add(lblCaLamTK, "align right");

//        cmbModelCaLam = new DefaultComboBoxModel<>();
        cmbCaLamTK = new MyComboBox<>();
        cmbCaLamTK.setFont(new Font(fontName, fontPlain, font14));
        cmbCaLamTK.setBorderLine(true);
        cmbCaLamTK.setBorderRadius(5);
        cmbCaLamTK.addItem("Tất cả");
        pnlTimKiemNV.add(cmbCaLamTK, "w 10%,h 40!");

        //Button tìm kiếm
        btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setFont(new Font(fontName, fontPlain, font14));
        btnTimKiem.setBackground(colorBtn);
        btnTimKiem.setBorderline(true);
        btnTimKiem.setBorderRadius(5);
        pnlTimKiemNV.add(btnTimKiem, "align left, w 100!, h 40!");
        /* End: group tìm nhân viên*/

    }

    /**
     * set thuộc tính và xử lý dữ liệu của bảng
     */
    private void TableHandler() {
        tblNhanVien.fixTable(scrTable);
        tblNhanVien.setFont(new Font(fontName, fontPlain, font16));
        setSizeColumnTable();
        
        String html2 = "<html><head><style> body{margin: 0 ; padding: 0; background-color: #303841;} h3{color: white; padding: 0 16px;} </style></head>"
                + "<body><h3>Click chuột trái 2 lần để xem chi tiết</h3></body></html>";
        tblNhanVien.setToolTipText(html2);

        //Thêm cột có icon xóa, sửa
        EventAction event = new EventAction() {
            @Override
            public void delete(Object obj) {
                NhanVien nhanVien = (NhanVien) obj;
                JOptionPane.showMessageDialog(null, "Delete" + nhanVien.getMaNhanVien());
            }

            @Override
            public void update(ModelAction action) {
                NhanVien nhanVien = (NhanVien) action.getObj();
                action.setObj(nhanVien);
            }

        };

        //Load dữ liệu từ DB lên bảng 
        listNhanVien = nhanVien_DAO.getNhanViens();
        for (NhanVien i : listNhanVien) {
            tblNhanVien.addRow(new NhanVien(i.getMaNhanVien(), i.getTenNhanVien(), i.getLoaiNhanVien(),
                    i.getCaLam(), i.getCanCuocCD(), i.isGioiTinh(), i.getNgaySinh(), i.getSoDienThoai(),
                    i.getEmail(), i.getDiaChi(), i.getMatKhau()).convertToRowTable());
        }

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
                System.out.println(".keyReleased()");
                System.out.println(tblNhanVien.getModel().getRowCount());

                defaultTableModel.setRowCount(0);

                int code = e.getKeyCode();
                System.out.println(code);
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

        List<NhanVien> nhanViens = nhanVien_DAO.searchNhanVien(txtTimKiem.getText().trim(), gioiTinh, maLoaiNV, maCaLam);
        for (NhanVien i : nhanViens) {
            tblNhanVien.addRow(new NhanVien(i.getMaNhanVien(), i.getTenNhanVien(), i.getLoaiNhanVien(),
                    i.getCaLam(), i.getCanCuocCD(), i.isGioiTinh(), i.getNgaySinh(), i.getSoDienThoai(),
                    i.getEmail(), i.getDiaChi(), i.getMatKhau()).convertToRowTable());
        }
    }
    
    
     private void setSizeColumnTable() {
        //chọn
        tblNhanVien.getColumnModel().getColumn(0).setPreferredWidth(50);
        tblNhanVien.getColumnModel().getColumn(0).setMaxWidth(50);
        tblNhanVien.getColumnModel().getColumn(0).setMinWidth(50);
        //mã nhân viên
        tblNhanVien.getColumnModel().getColumn(1).setPreferredWidth(150);
        tblNhanVien.getColumnModel().getColumn(1).setMaxWidth(150);
        tblNhanVien.getColumnModel().getColumn(1).setMinWidth(150);
        //tên nhân viên
        tblNhanVien.getColumnModel().getColumn(2).setPreferredWidth(250);
        tblNhanVien.getColumnModel().getColumn(2).setMaxWidth(250);
        tblNhanVien.getColumnModel().getColumn(2).setMinWidth(220);
        //giới tính
        tblNhanVien.getColumnModel().getColumn(3).setPreferredWidth(120);
        tblNhanVien.getColumnModel().getColumn(3).setMaxWidth(120);
        tblNhanVien.getColumnModel().getColumn(3).setMinWidth(120);
        //ngày sinh
        tblNhanVien.getColumnModel().getColumn(4).setPreferredWidth(10);
        //số điện thoại
        tblNhanVien.getColumnModel().getColumn(5).setPreferredWidth(10);
        //căn cước CD
        tblNhanVien.getColumnModel().getColumn(6).setPreferredWidth(20);
        
        //ca làm
        tblNhanVien.getColumnModel().getColumn(7).setPreferredWidth(20);
        //loại nhân viên
        tblNhanVien.getColumnModel().getColumn(8).setPreferredWidth(20);
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        pnlTop = new gui.swing.panel.PanelShadow();
        pnlCenter = new gui.swing.panel.PanelShadow();
        lblTitleBang = new javax.swing.JLabel();
        scrTable = new javax.swing.JScrollPane();
        tblNhanVien = new gui.swing.table2.MyTable();

        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1225, 900));

        lblTitle.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(4, 72, 210));
        lblTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTitle.setText("QUẢN LÝ NHÂN VIÊN");
        lblTitle.setToolTipText("");
        lblTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 161, Short.MAX_VALUE)
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
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
                "", "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Căn cước công dân", "Ca làm", "Loại nhân viên"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        scrTable.setViewportView(tblNhanVien);

        pnlCenter.add(scrTable, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 1186, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleBang;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane scrTable;
    private gui.swing.table2.MyTable tblNhanVien;
    // End of variables declaration//GEN-END:variables
}
