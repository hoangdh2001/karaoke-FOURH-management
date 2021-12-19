package gui;

import gui.dialog.DL_ThemNhaCungCap;
import dao.ChiTietNhapHang_DAO;
import dao.LoHang_DAO;
import dao.LoaiDichVu_DAO;
import dao.MatHang_DAO;
import dao.NhaCungCap_DAO;
import entity.LoHang;
import entity.LoaiDichVu;
import entity.MatHang;
import entity.NhaCungCap;
import gui.component.PanelTenSanPham;
import gui.swing.button.Button;
import gui.swing.model.AutoID;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import gui.swing.model.ModelObjectComboBox;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.ChiTietNhapHangService;
import service.LoHangService;
import service.LoaiDichVuService;
import service.MatHangService;
import service.NhaCungCapService;

public class GD_NhapHangHoa extends javax.swing.JPanel {

    private JComboBox<Object> cbNhaCungCap;
    private JComboBox<Object> cbLoaiSP;

    private Button btnThemNCC;
    private Button btnThem;
    private Button btnXoa;
    private Button btnLuu;
    private Button btnXemThongTin;

    private JTextField txtSoLuong;
    private JTextField txtGiaNhap;
    private JTextField txtGiaban;

    private NhaCungCapService nhaCungCapDaoService;
    private ChiTietNhapHangService chiTietNhapHangService;
    private MatHangService matHangService;
    private LoaiDichVuService loaiDichVuService;
    private LoHangService loHangService;
    private PanelTenSanPham pnlSPMoi;

    private JCheckBox cbSpMoi;

    private List<Boolean> isNew;

    private Map<MatHang, Boolean> isNewSP;

    private LoHang loHang;

    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    private final String fontName = "sansserif";
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    private int font12 = 12;
    private Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);

    public GD_NhapHangHoa() {
        loHangService = new LoHang_DAO();
        matHangService = new MatHang_DAO();
        loaiDichVuService = new LoaiDichVu_DAO();
        nhaCungCapDaoService = new NhaCungCap_DAO();
        chiTietNhapHangService = new ChiTietNhapHang_DAO();
        String maxID = loHangService.getMaxID();
        String maLoHang;
        if (maxID == null) {
            maLoHang = "LH0001";
        } else {
            maLoHang = AutoID.generateId(maxID, "LH");
        }
        loHang = new LoHang(maLoHang, GD_Chinh.NHAN_VIEN);
        initComponents();
        buildDisplay();
    }

    private void buildDisplay() {
        initForm();
        createTable();
        initDao();
        initAction();
    }

    private void initForm() {
        pnlForm.setLayout(new MigLayout("fillx, wrap, insets 0", "[fill]", "[]"));

        pnlForm.add(createPanelTitle(), "h 50!");

        JPanel pnlNhaCungCap = new JPanel();
        pnlNhaCungCap.setOpaque(false);
        pnlNhaCungCap.setLayout(new MigLayout("insets 5", "10[]5[]5[]", "[]"));

        JLabel lblNCC = new JLabel("Nhà cung cấp :");
        lblNCC.setFont(new Font(fontName, fontPlain, font14));

        cbNhaCungCap = new JComboBox<>(new String[]{"Chọn nhà cung cấp"});
        cbNhaCungCap.setFont(new Font(fontName, fontPlain, font12));

        btnThemNCC = new Button("Thêm nhà cung cấp");
        btnThemNCC.setFont(new Font("sansserif", Font.BOLD, 12));
        btnThemNCC.setForeground(Color.WHITE);
        btnThemNCC.setBorderRadius(5);
        btnThemNCC.setBackground(new Color(54, 88, 153));

        btnXemThongTin = new Button("Xem thông tin");
        btnXemThongTin.setFont(new Font("sansserif", Font.BOLD, 12));
        btnXemThongTin.setForeground(Color.WHITE);
        btnXemThongTin.setBorderRadius(5);
        btnXemThongTin.setBackground(new Color(54, 88, 153));

        pnlNhaCungCap.add(lblNCC);
        pnlNhaCungCap.add(cbNhaCungCap, "w 100:300:500, h 30!");
        pnlNhaCungCap.add(btnThemNCC, "w 150!,h 30!");
        pnlNhaCungCap.add(btnXemThongTin, "w 120!,h 30!");

        pnlForm.add(pnlNhaCungCap);

//        JSeparator spr = new JSeparator(SwingConstants.HORIZONTAL);
//        spr.setPreferredSize(new Dimension(10000, 20));
//        pnlForm.add(spr, "w 100%,wrap");
//        sanPham
        JPanel pnlSanPham = new JPanel();
        pnlSanPham.setOpaque(false);
        pnlSanPham.setLayout(new MigLayout("insets 0", "10[]5[fill]10[]5[fill]10", "[]10"));

        cbSpMoi = new JCheckBox("Sản phẩm mới");
        pnlSanPham.add(cbSpMoi, "h 30!, wrap");

        JLabel lblLoaiSP = new JLabel("Loại sản phẩm :");
        lblLoaiSP.setFont(new Font(fontName, fontPlain, font14));

        pnlSanPham.add(lblLoaiSP);

        cbLoaiSP = new JComboBox<>(new String[]{"Chọn loại sản phẩm"});
        cbLoaiSP.setFont(new Font(fontName, fontPlain, font12));
        pnlSanPham.add(cbLoaiSP, "w 100%, h 30!");

        JLabel lblTenSP = new JLabel("Tên sản phẩm :");
        lblTenSP.setFont(new Font(fontName, fontPlain, font14));

        pnlSanPham.add(lblTenSP);

        pnlSPMoi = new PanelTenSanPham();
        pnlSPMoi.setTypeSP(false);
        pnlSanPham.add(pnlSPMoi, "w 100%, h 30!, wrap");

        cbSpMoi.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    pnlSPMoi.setTypeSP(true);
                } else {
                    pnlSPMoi.setTypeSP(false);
                }
            }
        });

        JLabel lblSoLuong = new JLabel("Số lượng :");
        lblSoLuong.setFont(new Font(fontName, fontPlain, font14));

        pnlSanPham.add(lblSoLuong, "align right");

        txtSoLuong = new JTextField();
        txtSoLuong.setFont(new Font(fontName, fontPlain, font14));
        pnlSanPham.add(txtSoLuong, "h 30!, w 100%");

        JLabel lblGiaNhap = new JLabel("Giá nhập :");
        lblGiaNhap.setFont(new Font(fontName, fontPlain, font14));

        pnlSanPham.add(lblGiaNhap, "align right");

        txtGiaNhap = new JTextField();
        txtGiaNhap.setFont(new Font(fontName, fontPlain, font14));
        pnlSanPham.add(txtGiaNhap, "h 30!, wrap");

        JLabel lblGiaban = new JLabel("Giá Bán :");
        lblGiaban.setFont(new Font(fontName, fontPlain, font14));

        pnlSanPham.add(lblGiaban, "align right");

        txtGiaban = new JTextField();
        txtGiaban.setFont(new Font(fontName, fontPlain, font14));
        pnlSanPham.add(txtGiaban, "h 30!, w 100%");

        btnThem = new Button("Thêm");
        btnThem.setFont(new Font("sansserif", Font.BOLD, 12));
        btnThem.setForeground(Color.WHITE);
        btnThem.setBorderRadius(5);
        btnThem.setBackground(new Color(54, 88, 153));

        btnXoa = new Button("Xóa rỗng");
        btnXoa.setFont(new Font("sansserif", Font.BOLD, 12));
        btnXoa.setForeground(Color.WHITE);
        btnXoa.setBorderRadius(5);
        btnXoa.setBackground(new Color(54, 88, 153));

        btnLuu = new Button("Lưu");
        btnLuu.setFont(new Font("sansserif", Font.BOLD, 12));
        btnLuu.setForeground(Color.WHITE);
        btnLuu.setBorderRadius(5);
        btnLuu.setBackground(new Color(255, 0, 0));

        JPanel pnlbtn = new JPanel();
        pnlbtn.setOpaque(false);
        pnlbtn.setLayout(new MigLayout("", "0[fill]10[fill]10[fill]0", "0[]0"));

        pnlbtn.add(btnThem, "w 90!, h 30!");
        pnlbtn.add(btnXoa, "w 90!, h 30!");
        pnlbtn.add(btnLuu, "w 90!, h 30!");

        pnlSanPham.add(pnlbtn, "align right, span 2");

        pnlForm.add(pnlSanPham, "w 100%");

    }

    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Quản lý hàng hóa");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return pnlTitle;
    }

    private void createTable() {
        table.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, font14));
    }

    public void loadNCC() {
        List<NhaCungCap> listNCC = nhaCungCapDaoService.getNhaCungCap();
        for (int i = 0; i < listNCC.size(); i++) {
            NhaCungCap ncc = listNCC.get(i);
            cbNhaCungCap.addItem(new ModelObjectComboBox(ncc.getTenNCC(), ncc.getMaNCC()));
        }
    }

    public void initDao() {
        isNewSP = new HashMap<>();

        isNew = new ArrayList<>();

        loadNCC();

        List<LoaiDichVu> listDV = loaiDichVuService.getDsLoaiDichVu();
        for (int i = 0; i < listDV.size(); i++) {
            LoaiDichVu dv = listDV.get(i);
            cbLoaiSP.addItem(new ModelObjectComboBox(dv.getTenLoaiDichVu(), dv.getMaLoaiDichVu()));
        }

    }

    public void initAction() {
        btnThemNCC.addActionListener(new createActionListenner());

        cbNhaCungCap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (cbNhaCungCap.getSelectedIndex() != 0) {
                    ModelObjectComboBox ncc = (ModelObjectComboBox) cbNhaCungCap.getSelectedItem();
                    System.out.println(ncc.getMa());
                }
            }
        });

        cbLoaiSP.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (cbLoaiSP.getSelectedIndex() != 0) {
                    ModelObjectComboBox dv = (ModelObjectComboBox) cbLoaiSP.getSelectedItem();
                    pnlSPMoi.setComboboxItem(dv.getMa());
                } else {
                    pnlSPMoi.setComboboxItem("");
                }
            }
        });

        btnThem.addActionListener(new createActionListenner());
        btnLuu.addActionListener(new createActionListenner());
        btnXemThongTin.addActionListener(new createActionListenner());

        btnXoa.addActionListener(new createActionListenner());

        txtSoLuong.setFocusTraversalKeysEnabled(false);
        txtSoLuong.addKeyListener(new createKetlistener());

        txtGiaNhap.setFocusTraversalKeysEnabled(false);
        txtGiaNhap.addKeyListener(new createKetlistener());

        txtGiaban.setFocusTraversalKeysEnabled(false);
        txtGiaban.addKeyListener(new createKetlistener());
    }

    ;
    
    public void resetData() {
        DefaultTableModel df = (DefaultTableModel) table.getModel();
        df.setRowCount(0);
        loHang = new LoHang();
        isNewSP = new HashMap<MatHang, Boolean>();
    }

    public void xoaRong() {
        txtGiaNhap.setText("");
        txtGiaban.setText("");
        txtSoLuong.setText("");
        cbLoaiSP.setSelectedIndex(0);
        pnlSPMoi.setComboboxItem("");
    }

    private double convertMoneyToDouble(String money) {
        String[] text = money.trim().split("\\,")[0].split("\\.");
        String tienTra = "";
        for (int i = 0; i < text.length; i++) {
            tienTra += text[i];
        }
        return Double.parseDouble(tienTra);
    }

    private boolean valiDataSL() {
        int soLuong;
        try {
            soLuong = Integer.parseInt(txtSoLuong.getText().trim());
        } catch (Exception e) {
            showMsg("Số lượng phải là số");
            txtSoLuong.requestFocus();
            txtSoLuong.selectAll();
            return false;
        }

        if (soLuong <= 0) {
            showMsg("Số lượng phải lớn hơn 1");
            txtSoLuong.requestFocus();
            txtSoLuong.selectAll();
            return false;
        }
        return true;
    }

    private boolean valiDataGiaNhap() {

        String giaNhap = txtGiaNhap.getText().trim();
        double tien = 0;
        try {
            tien = Double.parseDouble(giaNhap);
        } catch (Exception e) {
            showMsg("Giá nhập phải là số");
            txtGiaNhap.requestFocus();
            txtGiaNhap.selectAll();
            return false;
        }

        if (tien <= 0) {
            showMsg("Giá nhập phải lớn hơn 0");
            txtGiaNhap.requestFocus();
            txtGiaNhap.selectAll();
            return false;
        }
        txtGiaNhap.setText(df.format(tien));
        return true;
    }

    private boolean valiDataGiaban() {
        String giaban = txtGiaban.getText().trim();
        double giaNhap = convertMoneyToDouble(txtGiaNhap.getText().trim());
        double tien = 0;
        try {
            tien = Double.parseDouble(giaban);
        } catch (Exception e) {
            showMsg("Giá nhập phải là số");
            txtGiaban.requestFocus();
            txtGiaban.selectAll();
            return false;
        }

        if (tien <= giaNhap) {
            showMsg("Giá bán phải lớn hơn giá nhập");
            txtGiaban.requestFocus();
            txtGiaban.selectAll();
            return false;
        }
        txtGiaban.setText(df.format(tien));
        return true;
    }

    private boolean valiSP() {
        int loaiSp = cbLoaiSP.getSelectedIndex();
        if (loaiSp == 0) {
            showMsg("Chọn loại sản phẩm !");
            return false;
        }
        int SP = pnlSPMoi.getSelectedIndex();
        if (cbSpMoi.isSelected()) {
            if (pnlSPMoi.getTenSanPhamMoi().equals("")) {
                showMsg("Nhập tên sản phẩm mới !");
                pnlSPMoi.requestFocus();
                return false;
            }
        } else {
            if (SP == 0) {
                showMsg("Chọn sản phẩm !");
                return false;
            }
        }
        return valiDataNull();
    }

    private boolean valiData() {
        int ncc = cbNhaCungCap.getSelectedIndex();
        String sl = txtSoLuong.getText().trim();
        String giaNhap = txtGiaNhap.getText().trim();
        String giaBan = txtGiaban.getText().trim();

        if (table.getRowCount() == 0) {
            showMsg("Không có sản phẩm nào được thêm !");
            return false;
        }

        if (ncc == 0) {
            showMsg("Chọn nhà cung vấp !");
            return false;
        }

        if (!(sl.equals("") || giaNhap.equals("") || giaBan.equals(""))) {
            showMsg("Còn dữ liệu chưa được thêm. Hãy thêm dữ liệu trước khi lưu!");
            return false;
        }

        return true;
    }

    private boolean valiDataNull() {
        String sl = txtSoLuong.getText().trim();
        String giaNhap = txtGiaNhap.getText().trim();
        String giaBan = txtGiaban.getText().trim();

        if (sl.equals("")) {
            showMsg("Nhập số lượng");
            txtSoLuong.requestFocus();
            return false;
        } else if (giaNhap.equals("")) {
            showMsg("Nhập giá nhập !");
            txtGiaNhap.requestFocus();
            return false;
        } else if (giaBan.equals("")) {
            showMsg("Nhập giá bán !");
            txtGiaban.requestFocus();
            return false;
        }

        return true;
    }

    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    public class createActionListenner implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
            if (o.equals(btnLuu) && valiData()) {
                try {
                    //insert lo hang
                    ModelObjectComboBox cb = (ModelObjectComboBox) cbNhaCungCap.getSelectedItem();
                    NhaCungCap ncc = nhaCungCapDaoService.getNhaCungCapById(cb.getMa());
                    loHang.setNhaCungCap(ncc);

                    loHangService.insertLohang(loHang);

////insert and update sp
                    isNewSP.forEach((sp, isInsert) -> {
                        if (isInsert) {
                            matHangService.addMatHang(sp);
                        } else {
                            matHangService.updateMatHang(sp);
                        }
                    });
//insert ct nhap
                    loHang.getDsChiTietNhapHang().forEach(ct -> {
                        chiTietNhapHangService.addChiTietNhapHang(ct);
                    });

                    resetData();
                    showMsg("Lưu thành công");
                } catch (Exception ex) {
                    Logger.getLogger(GD_NhapHangHoa.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (o.equals(btnXemThongTin)) {
                if (cbNhaCungCap.getSelectedIndex() != 0) {
                    ModelObjectComboBox maNCC = (ModelObjectComboBox) cbNhaCungCap.getSelectedItem();
                    NhaCungCap ncc = nhaCungCapDaoService.getNhaCungCapById(maNCC.getMa());
                    new DL_ThemNhaCungCap(Application.login, ncc).setVisible(true);
                }
            } else if (o.equals(btnThem) && valiSP()) {
                int soLuong = Integer.parseInt(txtSoLuong.getText().trim());
                double giaNhap = convertMoneyToDouble(txtGiaNhap.getText().trim());
                double giaBan = convertMoneyToDouble(txtGiaban.getText().trim());
                ModelObjectComboBox cb = (ModelObjectComboBox) cbLoaiSP.getSelectedItem();
                LoaiDichVu loaiDichVu = loaiDichVuService.getLoaiDichVuByMa(cb.getMa());
                String tenMatHang = "";
                MatHang matHang;

                if (cbSpMoi.isSelected()) {
                    String maMatHang = matHangService.getLastMatHang();
                    tenMatHang = pnlSPMoi.getTenSanPhamMoi();
                    matHang = new MatHang(maMatHang, pnlSPMoi.getTenSanPhamMoi(), loaiDichVu, soLuong, giaBan);
                    isNewSP.put(matHang, true);
                } else {
                    tenMatHang = pnlSPMoi.getTenSanPhamCu();
                    String maMatHang = pnlSPMoi.getMaSanPhamCu();
                    matHang = new MatHang(maMatHang, tenMatHang, loaiDichVu, soLuong, giaBan);
                    isNewSP.put(matHang, false);
                }

                loHang.themCT_NhapHang(matHang, soLuong, giaNhap);

                table.addRow(new Object[]{new ModelObjectComboBox(matHang.getTenMatHang(), matHang.getMaMatHang()),
                    cb.toString(), soLuong,
                    df.format(giaNhap), df.format(giaBan), df.format(giaNhap * soLuong)});
                xoaRong();
            } else if (o.equals(btnXoa)) {
                xoaRong();
            } else if (o.equals(btnThemNCC)) {
                new DL_ThemNhaCungCap(Application.login, null).setVisible(true);
                loadNCC();
            }
        }
    }

    public class createKetlistener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Object obj = e.getSource();
            if (obj.equals(txtSoLuong) && (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER)) {
                if (valiDataSL()) {
                    txtGiaNhap.requestFocus();
                }
            } else if (obj.equals(txtGiaNhap) && (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER)) {
                if (valiDataGiaNhap()) {
                    txtGiaban.requestFocus();
                }
            } else if (obj.equals(txtGiaban) && (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER)) {
                valiDataGiaban();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlForm = new gui.swing.panel.PanelShadow();
        pnlCenter = new gui.swing.panel.PanelShadow();
        jScrollPane2 = new javax.swing.JScrollPane();
        table = new gui.swing.table.MyTableFlatlaf();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout(0, 5));

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setShadowOpacity(0.3F);
        pnlForm.setShadowSize(2);
        pnlForm.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlFormLayout = new javax.swing.GroupLayout(pnlForm);
        pnlForm.setLayout(pnlFormLayout);
        pnlFormLayout.setHorizontalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 929, Short.MAX_VALUE)
        );
        pnlFormLayout.setVerticalGroup(
            pnlFormLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        add(pnlForm, java.awt.BorderLayout.PAGE_START);

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlCenter.setShadowOpacity(0.3F);
        pnlCenter.setShadowSize(2);
        pnlCenter.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlCenter.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên mặt hàng", "Loại mặt hàng", "Số lượng", "Giá nhập", "Giá bán", "Tổng"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setRowHeight(40);
        table.setSelectionBackground(new java.awt.Color(239, 244, 255));
        table.setSelectionForeground(new java.awt.Color(51, 51, 51));
        table.setShowGrid(true);
        table.setShowVerticalLines(false);
        jScrollPane2.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
        }

        pnlCenter.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        add(pnlCenter, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.panel.PanelShadow pnlForm;
    private gui.swing.table.MyTableFlatlaf table;
    // End of variables declaration//GEN-END:variables
}
