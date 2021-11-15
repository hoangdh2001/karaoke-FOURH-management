package gui.component;

import dao.CaLam_DAO;
import dao.DiaChiMau_DAO;
import dao.LoaiNhanVien_DAO;
import dao.NhanVien_DAO;
import entity.CaLam;
import entity.LoaiNhanVien;
import entity.NhanVien;
import java.awt.Color;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

/**
 *
 * @author NGUYE
 */
public class PanelThemNhanVien extends javax.swing.JPanel {

    private CaLam_DAO caLam_DAO;
    private LoaiNhanVien_DAO loaiNhanVien_DAO;
    private DiaChiMau_DAO diaChiMau_DAO;
    private NhanVien_DAO nhanVien_DAO;
    
    private List<CaLam> caLams;
    private List<LoaiNhanVien> loaiNhanViens;

    public PanelThemNhanVien() {
        initComponents();
        buildDisplay();

        caLam_DAO = new CaLam_DAO();
        loaiNhanVien_DAO = new LoaiNhanVien_DAO();
        diaChiMau_DAO = new DiaChiMau_DAO();
        nhanVien_DAO = new NhanVien_DAO();
        ComboBoxHandler();

        ClearForm();
    }

    private void buildDisplay() {
        SetPropertiesForm();

    }

    private void SetPropertiesForm() {
        int txtRadius = 10;
        int cmbRadius = 10;
        int btnRadius = 10;
        Color colorBtn = new Color(184, 238, 241);

        txtMaNV.setBorderLine(true);
        txtMaNV.setBorderRadius(txtRadius);

        txtTenNV.setBorderLine(true);
        txtTenNV.setBorderRadius(txtRadius);

        cmbGioiTinh.setBorderLine(true);
        cmbGioiTinh.setBorderRadius(cmbRadius);
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");

        //jDateChooser1.setBorderLine(true);
        //txtMaNV.setBorderRadius();
        txtSDT.setBorderLine(true);
        txtSDT.setBorderRadius(txtRadius);

        txtEmail.setBorderLine(true);
        txtEmail.setBorderRadius(txtRadius);

        txtCCCD.setBorderLine(true);
        txtCCCD.setBorderRadius(txtRadius);

        cmbCaLam.setBorderLine(true);
        cmbCaLam.setBorderRadius(cmbRadius);
        cmbCaLam.addItem("Chọn");

        cmbLoaiNV.setBorderLine(true);
        cmbLoaiNV.setBorderRadius(cmbRadius);
        cmbLoaiNV.addItem("Chọn");

        txtSoNha.setBorderLine(true);
        txtSoNha.setBorderRadius(txtRadius);

        txtTenDuong.setBorderLine(true);
        txtTenDuong.setBorderRadius(txtRadius);

        cmbXaPhuong.setBorderLine(true);
        cmbXaPhuong.setBorderRadius(cmbRadius);
        cmbXaPhuong.addItem("Chọn");

        cmbQuanHuyen.setBorderLine(true);
        cmbQuanHuyen.setBorderRadius(cmbRadius);
        cmbQuanHuyen.addItem("Chọn");

        cmbTinhTP.setBorderLine(true);
        cmbTinhTP.setBorderRadius(cmbRadius);
        cmbTinhTP.addItem("Chọn");

        txtMatKhau.setBorderLine(true);
        txtMatKhau.setBorderRadius(txtRadius);

        btnLamMoi.setBorderline(true);
        btnLamMoi.setBorderRadius(btnRadius);
        btnLamMoi.setBackground(colorBtn);

        btnThem.setBorderline(true);
        btnThem.setBorderRadius(btnRadius);
        btnThem.setBackground(colorBtn);
    }

    private void ComboBoxHandler() {
        caLams = caLam_DAO.getCaLams();
        for (CaLam i : caLams) {
            cmbCaLam.addItem(i.getGioBatDau() + "-" + i.getGioKetThuc());
        }

        loaiNhanViens = loaiNhanVien_DAO.getLoaiNhanViens();
        for (LoaiNhanVien i : loaiNhanViens) {
            cmbLoaiNV.addItem(i.getTenLoaiNV());
        }

        diaChiMau_DAO.getAllTinhThanh().forEach(i -> {
            cmbTinhTP.addItem(i);
        });

        cmbTinhTP.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String tinhThanh = cmbTinhTP.getSelectedItem().toString();

                diaChiMau_DAO.getQuanHuyenTheoTinhThanh(tinhThanh).forEach(i -> {
                    cmbQuanHuyen.addItem(i);
                    System.out.println("them quan huyen");
                });
            }
        });

        cmbQuanHuyen.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String quanHuyen = cmbQuanHuyen.getSelectedItem().toString();
                String tinhThanh = cmbTinhTP.getSelectedItem().toString();

                diaChiMau_DAO.getPhuongXaTheoQHTH(quanHuyen, tinhThanh).forEach(i -> {
                    cmbXaPhuong.addItem(i);
                });
            }
        });

    }

    private void AddHandler() {
//        NhanVien nhanVien = new NhanVien("ma", "ten", loaiNhanVien, caLam, TOOL_TIP_TEXT_KEY, true, ngaySinh, TOOL_TIP_TEXT_KEY, TOOL_TIP_TEXT_KEY, diaChi, matKhau)
    
    }
    
    /**
     * Thuật toán tạo mã tăng tự động
     *
     * @param idCurrent mã hiện tại
     * @return idNew mã được tăng lên 1 Ví dụ: idCurrent= "NV0001" ->
     * idNew="NV0002"
     */
    private String GenerateId(String idCurrent) {
        String[] idSplit = idCurrent.split(""); // tách các chữ số trong id ra thành từng phần tử của mảng

        int i = 2; //vị trí chia mã nhân viên ra làm 2 phần, ví dụ NV0038 -> phần đầu: NV00 ; phần đuôi:38
        while (i != idSplit.length) {
            if (!idSplit[i].equals("0")) {
                break;
            }
            i++;
        }

        String head_id = "NV"; // phần đầu của mã nhân viên mới
        String tail_id = ""; // phần đuôi của mã nhân viên mới
        for (int j = 2; j < idSplit.length; j++) {

            if (j < i) {
                head_id += idSplit[j];
            } else {
                tail_id += idSplit[j];
            }
        }

        tail_id = Integer.toString(Integer.parseInt(tail_id) + 1); // tăng id lên 1
        return head_id + tail_id;
    }
    private void ClearForm() {
        btnLamMoi.addActionListener((e) -> {

            NhanVien nhanVien = nhanVien_DAO.getLastNhanVien();
            String newID = GenerateId(nhanVien.getMaNhanVien());
            txtMaNV.setText(newID);
            
            txtTenNV.setText("");
            cmbGioiTinh.setSelectedIndex(0);
            jDateChooser1.setDate(null);
            txtSDT.setText("");
            txtEmail.setText("");
            txtCCCD.setText("");
            cmbCaLam.setSelectedIndex(0);
            cmbLoaiNV.setSelectedIndex(0);
            txtSoNha.setText("");
            txtTenDuong.setText("");
            cmbXaPhuong.setSelectedIndex(0);
            cmbQuanHuyen.setSelectedIndex(0);
            cmbTinhTP.setSelectedIndex(0);
            txtMatKhau.setText("123456");
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lblMaNV = new javax.swing.JLabel();
        lblTenNV = new javax.swing.JLabel();
        lblGioiTinh = new javax.swing.JLabel();
        lblNgaySinh = new javax.swing.JLabel();
        lblSDT = new javax.swing.JLabel();
        lblEmail = new javax.swing.JLabel();
        lblCCCD = new javax.swing.JLabel();
        lblCaLam = new javax.swing.JLabel();
        lblLoaiNV = new javax.swing.JLabel();
        lblSoNha = new javax.swing.JLabel();
        lblTenDuong = new javax.swing.JLabel();
        lblXaPhuong = new javax.swing.JLabel();
        lblQuanHuyen = new javax.swing.JLabel();
        lblTinhTP = new javax.swing.JLabel();
        lblMatKhau = new javax.swing.JLabel();
        txtMaNV = new gui.swing.textfield.MyTextField();
        txtTenNV = new gui.swing.textfield.MyTextField();
        cmbGioiTinh = new gui.swing.textfield.MyComboBox();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        txtSDT = new gui.swing.textfield.MyTextField();
        txtEmail = new gui.swing.textfield.MyTextField();
        txtCCCD = new gui.swing.textfield.MyTextField();
        cmbCaLam = new gui.swing.textfield.MyComboBox();
        cmbLoaiNV = new gui.swing.textfield.MyComboBox();
        txtSoNha = new gui.swing.textfield.MyTextField();
        txtTenDuong = new gui.swing.textfield.MyTextField();
        cmbXaPhuong = new gui.swing.textfield.MyComboBox();
        cmbQuanHuyen = new gui.swing.textfield.MyComboBox();
        cmbTinhTP = new gui.swing.textfield.MyComboBox();
        txtMatKhau = new gui.swing.textfield.MyTextField();
        btnThem = new gui.swing.button.Button();
        btnLamMoi = new gui.swing.button.Button();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));

        lblMaNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMaNV.setForeground(new java.awt.Color(0, 0, 0));
        lblMaNV.setText("Mã nhân viên");

        lblTenNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTenNV.setForeground(new java.awt.Color(0, 0, 0));
        lblTenNV.setText("Tên nhân viên");

        lblGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblGioiTinh.setForeground(new java.awt.Color(0, 0, 0));
        lblGioiTinh.setText("Giới tính");

        lblNgaySinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblNgaySinh.setForeground(new java.awt.Color(0, 0, 0));
        lblNgaySinh.setText("Ngày sinh");

        lblSDT.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSDT.setForeground(new java.awt.Color(0, 0, 0));
        lblSDT.setText("SDT");

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblEmail.setForeground(new java.awt.Color(0, 0, 0));
        lblEmail.setText("Email");

        lblCCCD.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCCCD.setForeground(new java.awt.Color(0, 0, 0));
        lblCCCD.setText("Căn cước CD");

        lblCaLam.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCaLam.setForeground(new java.awt.Color(0, 0, 0));
        lblCaLam.setText("Ca làm");

        lblLoaiNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLoaiNV.setForeground(new java.awt.Color(0, 0, 0));
        lblLoaiNV.setText("Loại nhân viên");

        lblSoNha.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSoNha.setForeground(new java.awt.Color(0, 0, 0));
        lblSoNha.setText("Số nhà");

        lblTenDuong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTenDuong.setForeground(new java.awt.Color(0, 0, 0));
        lblTenDuong.setText("Tên đường");

        lblXaPhuong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXaPhuong.setForeground(new java.awt.Color(0, 0, 0));
        lblXaPhuong.setText("Xã/Phường");

        lblQuanHuyen.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuanHuyen.setForeground(new java.awt.Color(0, 0, 0));
        lblQuanHuyen.setText("Quận/Huyện");

        lblTinhTP.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTinhTP.setForeground(new java.awt.Color(0, 0, 0));
        lblTinhTP.setText("Tỉnh/Thành phố");

        lblMatKhau.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMatKhau.setForeground(new java.awt.Color(0, 0, 0));
        lblMatKhau.setText("Mật khẩu");

        txtMaNV.setText("myTextField1");
        txtMaNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtTenNV.setText("myTextField1");
        txtTenNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbGioiTinh.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        jDateChooser1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtSDT.setText("myTextField1");
        txtSDT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtEmail.setText("myTextField1");
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtCCCD.setText("myTextField1");
        txtCCCD.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbCaLam.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbLoaiNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtSoNha.setText("myTextField1");
        txtSoNha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtTenDuong.setText("myTextField5");
        txtTenDuong.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbXaPhuong.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbQuanHuyen.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbTinhTP.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtMatKhau.setText("myTextField1");
        txtMatKhau.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNgaySinh)
                                    .addComponent(lblGioiTinh)
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLoaiNV)
                                    .addComponent(lblCaLam))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtCCCD, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbGioiTinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbLoaiNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbCaLam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblMaNV)
                                .addGap(47, 47, 47)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTenNV)
                                .addGap(45, 45, 45)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblCCCD)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenDuong)
                                    .addComponent(lblXaPhuong)
                                    .addComponent(lblSoNha)
                                    .addComponent(lblQuanHuyen)
                                    .addComponent(lblTinhTP)
                                    .addComponent(lblMatKhau))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMatKhau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenDuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSoNha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbXaPhuong, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                    .addComponent(cmbQuanHuyen, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                    .addComponent(cmbTinhTP, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE))))))
                .addGap(70, 70, 70))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(13, 13, 13))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenDuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.button.Button btnLamMoi;
    private gui.swing.button.Button btnThem;
    private gui.swing.textfield.MyComboBox cmbCaLam;
    private gui.swing.textfield.MyComboBox cmbGioiTinh;
    private gui.swing.textfield.MyComboBox cmbLoaiNV;
    private gui.swing.textfield.MyComboBox cmbQuanHuyen;
    private gui.swing.textfield.MyComboBox cmbTinhTP;
    private gui.swing.textfield.MyComboBox cmbXaPhuong;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCCCD;
    private javax.swing.JLabel lblCaLam;
    private javax.swing.JLabel lblEmail;
    private javax.swing.JLabel lblGioiTinh;
    private javax.swing.JLabel lblLoaiNV;
    private javax.swing.JLabel lblMaNV;
    private javax.swing.JLabel lblMatKhau;
    private javax.swing.JLabel lblNgaySinh;
    private javax.swing.JLabel lblQuanHuyen;
    private javax.swing.JLabel lblSDT;
    private javax.swing.JLabel lblSoNha;
    private javax.swing.JLabel lblTenDuong;
    private javax.swing.JLabel lblTenNV;
    private javax.swing.JLabel lblTinhTP;
    private javax.swing.JLabel lblXaPhuong;
    private gui.swing.textfield.MyTextField txtCCCD;
    private gui.swing.textfield.MyTextField txtEmail;
    private gui.swing.textfield.MyTextField txtMaNV;
    private gui.swing.textfield.MyTextField txtMatKhau;
    private gui.swing.textfield.MyTextField txtSDT;
    private gui.swing.textfield.MyTextField txtSoNha;
    private gui.swing.textfield.MyTextField txtTenDuong;
    private gui.swing.textfield.MyTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
