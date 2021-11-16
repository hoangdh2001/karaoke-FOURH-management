/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author NGUYE
 */
public class PanelTabSuaThongTinNV extends javax.swing.JPanel {

    private NhanVien nhanVien;
    private CaLam_DAO caLam_DAO;
    private LoaiNhanVien_DAO loaiNhanVien_DAO;
    private DiaChiMau_DAO diaChiMau_DAO;
    private NhanVien_DAO nhanVien_DAO;

    private List<CaLam> caLams;
    private List<LoaiNhanVien> loaiNhanViens;

    private SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");

    public PanelTabSuaThongTinNV(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
        initComponents();
        setPropertiesForm();
        caLam_DAO = new CaLam_DAO();
        loaiNhanVien_DAO = new LoaiNhanVien_DAO();
        diaChiMau_DAO = new DiaChiMau_DAO();
        nhanVien_DAO = new NhanVien_DAO();
        comboBoxHandler();

        HandlerData();
//        clearForm();
    }

    private void HandlerData() {
//        lblValueMaNhanVien.setText(nhanVien.getMaNhanVien());
//
//        String gioiTinh = nhanVien.isGioiTinh() == true ? "Nữ" : "Nam";
//        lblValueGioiTinh.setText(gioiTinh);
//
//        System.out.println();
//        String[] temp = nhanVien.getNgaySinh().toString().split("-");
//        String ngaySinh = temp[2] + "/" + temp[1] + "/" + temp[0];
//
//        lblValueNgaySinh.setText(ngaySinh);
//
//        lblValueSDT.setText(nhanVien.getSoDienThoai());
//        lblValueEmail.setText(nhanVien.getEmail());
//
//        String caLam = nhanVien.getCaLam().getGioBatDau() + "-" + nhanVien.getCaLam().getGioKetThuc();
//        lblValueCCCD.setText(caLam);
//
//        lblValueCaLam.setText(nhanVien.getCanCuocCD());
//        lblValueLoaiNhanVien.setText(nhanVien.getLoaiNhanVien().getTenLoaiNV());
//
//        lblValueSoNha.setText(nhanVien.getDiaChi().getSoNha() + ", " + nhanVien.getDiaChi().getTenDuong());
//        lblValueXaPhuong.setText(nhanVien.getDiaChi().getXaPhuong());
//        lblValueQuanHuyen.setText(nhanVien.getDiaChi().getQuanHuyen());
//        lblValueTinhThanhPho.setText(nhanVien.getDiaChi().getTinhThanh());
        txtMaNV.setText(nhanVien.getMaNhanVien());

        txtTenNV.setText(nhanVien.getTenNhanVien());
        String gioiTinh = nhanVien.isGioiTinh() == true ? "Nữ" : "Nam";
        cmbGioiTinh.setSelectedItem(gioiTinh);

        jDateChooser1.setDateFormatString("dd-MM-yyyy");
        jDateChooser1.setDate(nhanVien.getNgaySinh());
        
        System.out.println(nhanVien.getNgaySinh());

        txtSDT.setText(nhanVien.getSoDienThoai());
        txtEmail.setText(nhanVien.getEmail());
        txtCCCD.setText(nhanVien.getCanCuocCD());

        cmbCaLam.setSelectedItem(nhanVien.getCaLam().getGioBatDau() + "-" + nhanVien.getCaLam().getGioKetThuc());
        cmbLoaiNV.setSelectedItem(nhanVien.getLoaiNhanVien().getTenLoaiNV());
        txtSoNha.setText(nhanVien.getDiaChi().getSoNha());
        txtTenDuong.setText(nhanVien.getDiaChi().getTenDuong());

        cmbTinhTP.setSelectedItem(nhanVien.getDiaChi().getTinhThanh());
        cmbQuanHuyen.setSelectedItem(nhanVien.getDiaChi().getQuanHuyen());
        cmbXaPhuong.setSelectedItem(nhanVien.getDiaChi().getXaPhuong());
//        txtMatKhau.setText(Arrays.toString(nhanVien.getMatKhau()));
//       System.out.println(Byte.toString((byte[])nhanVien.getMatKhau()));
       System.out.println("string " + new String(nhanVien.getMatKhau(),StandardCharsets.UTF_8));
    }

    private void setPropertiesForm() {
        int txtRadius = 10;
        int cmbRadius = 10;
        int btnRadius = 10;
        Color colorBtn = new Color(184, 238, 241);

        txtMaNV.setBorderLine(true);
        txtMaNV.setBorderRadius(txtRadius);
        txtMaNV.setEditable(false);

        txtTenNV.setBorderLine(true);
        txtTenNV.setBorderRadius(txtRadius);

        cmbGioiTinh.setBorderLine(true);
        cmbGioiTinh.setBorderRadius(cmbRadius);
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");

        //jDateChooser1.setBorderLine(true);
        //txtMaNV.setBorderRadius();
        jDateChooser1.setDateFormatString("dd-MM-yyy");
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

//        txtMatKhau.setBorderLine(true);
//        txtMatKhau.setBorderRadius(txtRadius);

        btnLamMoi.setBorderline(true);
        btnLamMoi.setBorderRadius(btnRadius);
        btnLamMoi.setBackground(colorBtn);

        btnThem.setBorderline(true);
        btnThem.setBorderRadius(btnRadius);
        btnThem.setBackground(colorBtn);
    }

    private void comboBoxHandler() {
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

//        cmbLoaiNV.addItemListener(new ItemListener() {
//            @Override
//            public void itemStateChanged(ItemEvent e) {
//                String lnv = cmbLoaiNV.getSelectedItem().toString();
//
//                if (lnv.equals("Kĩ thuật") || lnv.equals("Phục vụ") || lnv.equals("Bảo vệ")) {
//                    txtMatKhau.setVisible(false);
//                    lblMatKhau.setVisible(false);
//                } else {
//                    txtMatKhau.setVisible(true);
//                    lblMatKhau.setVisible(true);
//                }
//            }
//        });
    }

    private void clearForm() {

        txtMaNV.setText("");

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
//        txtMatKhau.setText("Mk#123");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
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
        btnThem = new gui.swing.button.Button();
        btnLamMoi = new gui.swing.button.Button();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

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

        btnThem.setText("Sửa");
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
                                    .addComponent(cmbLoaiNV, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbCaLam, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(lblMaNV)
                                .addGap(47, 47, 47)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTenNV)
                                .addGap(45, 45, 45)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblTinhTP)
                                .addGap(37, 37, 37)
                                .addComponent(cmbTinhTP, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(txtSoNha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCCCD)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lblTenDuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(lblXaPhuong, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblQuanHuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbXaPhuong, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                                    .addComponent(cmbQuanHuyen, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
                                    .addComponent(txtTenDuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
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
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
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
                    .addComponent(lblTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenDuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenDuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
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
    private gui.swing.textfield.MyTextField txtSDT;
    private gui.swing.textfield.MyTextField txtSoNha;
    private gui.swing.textfield.MyTextField txtTenDuong;
    private gui.swing.textfield.MyTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
