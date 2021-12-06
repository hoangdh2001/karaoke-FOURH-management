package gui.component;

import dao.CaLam_DAO;
import dao.DiaChiMau_DAO;
import dao.LoaiNhanVien_DAO;
import dao.NhanVien_DAO;
import entity.CaLam;
import entity.DiaChi;
import entity.LoaiNhanVien;
import entity.NhanVien;
import gui.swing.event.EventAdd;
import gui.swing.model.AutoID;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
    private EventAdd event;

    private SimpleDateFormat fm1 = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat fm2 = new SimpleDateFormat("dd-MM-yyyy");

    public PanelThemNhanVien() {
        initComponents();
        setPropertiesForm();

        caLam_DAO = new CaLam_DAO();
        loaiNhanVien_DAO = new LoaiNhanVien_DAO();
        diaChiMau_DAO = new DiaChiMau_DAO();
        nhanVien_DAO = new NhanVien_DAO();
        comboBoxHandler();

        btnLamMoiHandler();
        btnThemHandler();
        clearForm();
    }


    private void setPropertiesForm() {
        int txtRadius = 10;
        int cmbRadius = 10;
        int btnRadius = 10;
        Color colorBtn = new Color(184, 238, 241);

//        txtMaNV.setBorderLine(true);
//        txtMaNV.setBorderRadius(txtRadius);

//        txtTenNV.setBorderLine(true);
//        txtTenNV.setBorderRadius(txtRadius);
//
//        cmbGioiTinh.setBorderLine(true);
//        cmbGioiTinh.setBorderRadius(cmbRadius);
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");

        //jDateChooser1.setBorderLine(true);
        //txtMaNV.setBorderRadius();
        jDateChooser1.setDateFormatString("dd-MM-yyyy");
//        txtSDT.setBorderLine(true);
//        txtSDT.setBorderRadius(txtRadius);
//
//        txtEmail.setBorderLine(true);
//        txtEmail.setBorderRadius(txtRadius);
//
//        txtCCCD.setBorderLine(true);
//        txtCCCD.setBorderRadius(txtRadius);
//
//        cmbCaLam.setBorderLine(true);
//        cmbCaLam.setBorderRadius(cmbRadius);
        cmbCaLam.addItem("Chọn");

//        cmbLoaiNV.setBorderLine(true);
//        cmbLoaiNV.setBorderRadius(cmbRadius);
        cmbLoaiNV.addItem("Chọn");

//        txtSoNha.setBorderLine(true);
//        txtSoNha.setBorderRadius(txtRadius);

//        txtTenDuong.setBorderLine(true);
//        txtTenDuong.setBorderRadius(txtRadius);

//        cmbXaPhuong.setBorderLine(true);
//        cmbXaPhuong.setBorderRadius(cmbRadius);
        cmbXaPhuong.addItem("Chọn");

//        cmbQuanHuyen.setBorderLine(true);
//        cmbQuanHuyen.setBorderRadius(cmbRadius);
        cmbQuanHuyen.addItem("Chọn");

//        cmbTinhTP.setBorderLine(true);
//        cmbTinhTP.setBorderRadius(cmbRadius);
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
    
    public void addThemEvent(EventAdd event) {
        this.event = event;
    }

    /**
     * Đưa dữ liệu lên comboBox
     */
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

        cmbLoaiNV.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String lnv = cmbLoaiNV.getSelectedItem().toString();

                if (lnv.equals("Kĩ thuật") || lnv.equals("Phục vụ") || lnv.equals("Bảo vệ")) {
                    txtMatKhau.setVisible(false);
                    lblMatKhau.setVisible(false);
                } else {
                    txtMatKhau.setVisible(true);
                    lblMatKhau.setVisible(true);
                }
            }
        });

    }

    /**
     * Xử lý sự kiện nút làm mới
     */
    private void btnLamMoiHandler() {
        btnLamMoi.addActionListener((e) -> {
            clearForm();
        });
    }

    /**
     * Xử lý sự kiện nút thêm
     */
    private void btnThemHandler() {
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (validForm() == true) {
                    String ma = txtMaNV.getText();
                    String ten = txtTenNV.getText().trim();

                    LoaiNhanVien lnv = new LoaiNhanVien();
                    for (LoaiNhanVien i : loaiNhanViens) {
                        if (i.getTenLoaiNV().equals(cmbLoaiNV.getSelectedItem().toString())) {
                            lnv = i;
                            break;
                        }
                    }

                    CaLam cl = new CaLam();
                    for (CaLam i : caLams) {
                        String temp = i.getGioBatDau() + "-" + i.getGioKetThuc();
                        if (temp.equals(cmbCaLam.getSelectedItem().toString())) {
                            cl = i;
                            break;
                        }
                    }

                    String cccd = txtCCCD.getText().trim();
                    boolean gioiTinh = "Nam".equals(cmbGioiTinh.getSelectedItem()) ? false : true;

                    Date ngaySinh = new Date(jDateChooser1.getDate().getTime());

                    String sdt = txtSDT.getText().trim();
                    String email = txtEmail.getText().trim();
                    DiaChi diaChi = new DiaChi(txtSoNha.getText(), txtTenDuong.getText(), cmbXaPhuong.getSelectedItem().toString(), cmbQuanHuyen.getSelectedItem().toString(), cmbTinhTP.getSelectedItem().toString());
                    byte[] matKhau = txtMatKhau.getText().getBytes();

                    //Kiểm tra trước khi thêm
                    if (nhanVien_DAO.checkSDT(sdt) == true) {
                        JOptionPane.showMessageDialog(null, "Trùng số điện thoại.");
                        txtSDT.requestFocus();
                        txtSDT.selectAll();
                    } else if (nhanVien_DAO.checkCCCD(cccd)) {
                        JOptionPane.showMessageDialog(null, "Trùng căn cước công dân.");
                        txtCCCD.requestFocus();
                        txtCCCD.selectAll();
                    } else {
                        String x = cmbLoaiNV.getSelectedItem().toString();

                        if (x.equals("Kĩ thuật") || x.equals("Phục vụ") || x.equals("Bảo vệ")) {
                            matKhau = null;
                        }
                        //Thêm nhân viên
                        NhanVien nhanVien = new NhanVien(ma, ten, lnv, cl, cccd, gioiTinh, ngaySinh, sdt, email, diaChi, matKhau);
                        System.out.println("Nhan vieen dc them \n " + nhanVien);
                        boolean result = nhanVien_DAO.addNhanVien(nhanVien);
                        event.add(result);
                        
                        if (result) {
                            clearForm();
                        }
                    }
                } else {
                    return;
                }

            }
        });
    }

    /**
     * Trả về mảng ngày tháng năm sinh kiểu int.
     *
     * @return date date[0] năm date[1] tháng date[2] ngày
     */
    private ArrayList<Integer> getNgaySinh() {
        ArrayList<Integer> date = new ArrayList<>();
        String[] d = fm1.format(jDateChooser1.getDate()).split("-");
        for (String string : d) {
            System.out.println(string);
        }
        date.add(Integer.parseInt(d[0])); //năm 
        date.add(Integer.parseInt(d[1])); // tháng
        date.add(Integer.parseInt(d[2])); //ngày
        System.out.println("ham" + date.get(0) + "/" + date.get(1) + "/" + date.get(2));
        return date;
    }
   

    private boolean validForm() {
        //Kiểm tra họ tên
        String hoTen = txtTenNV.getText().trim();
        if (!(hoTen.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập họ tên.");
            txtTenNV.requestFocus();
            return false;
        } else {
            if (!hoTen.matches("^([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*\\s)+([ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴA-Z]{1}[ắằẳẵặăấầẩẫậâáàãảạđếềểễệêéèẻẽẹíìỉĩịốồổỗộôớờởỡợơóòõỏọứừửữựưúùủũụýỳỷỹỵa-z]*)$")) {
                JOptionPane.showMessageDialog(null, "Chưa nhập đầy đủ họ tên. Họ tên bắt đầu bằng chữ in hoa.");
                txtTenNV.selectAll();
                txtTenNV.requestFocus();
                return false;
            }
        }

        if (jDateChooser1.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Chưa chọn ngày sinh.");
            return false;
        }

        //Kiểm tra ngày tháng năm sinh >= 18 tuổi
        ArrayList<Integer> d = getNgaySinh();
        int namSinh = d.get(0);
        int thangSinh = d.get(1);
        int ngaySinh = d.get(2);
        System.out.println("ngay sinh" + "/" + ngaySinh + "/" + thangSinh + "/" + namSinh);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();

        if ((now.getYear() - namSinh) <= 18) {

            if ((now.getYear() - namSinh) < 18) {
                JOptionPane.showMessageDialog(null, "Chưa đủ 18 tuổi.");
                return false;
            } else {//Nếu năm == 18

                if (now.getMonthValue() <= thangSinh) {

                    if (now.getMonthValue() < thangSinh) {
                        JOptionPane.showMessageDialog(null, "Chưa đủ 18 tuổi.");
                        return false;
                    } else if (now.getMonthValue() == thangSinh) {//Nếu tháng bằng nhau

                        if (now.getDayOfMonth() < ngaySinh) {
                            JOptionPane.showMessageDialog(null, "Chưa đủ 18 tuổi.");
                            return false;
                        }
                    }
                }
            }
        }
        //Kiểm tra số điện thoại hợp lệ
        String sdt = txtSDT.getText().trim();
        if (!(sdt.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số điện thoại.");
            txtSDT.requestFocus();
            return false;
        } else {
            if (!(sdt.matches("^0[0-9]{9}$"))) {
                JOptionPane.showMessageDialog(null,
                        "Nhập sai định dạng.\nSố điện thoại bao gồm 10 chữ số bắt đầu là số 0.");
                txtSDT.selectAll();
                txtSDT.requestFocus();
                return false;
            }
        }

        //Kiểm tra email hợp lệ
        String email = txtEmail.getText().trim();
        if (email.length() > 0) {

            if (!(email.matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+$"))) {
                JOptionPane.showMessageDialog(null, "Nhập sai định dạng email.");
                txtEmail.selectAll();
                txtEmail.requestFocus();
                return false;
            }
        }
        
        //Kiểm tra số căn cước công dân
        String cccd = txtCCCD.getText().trim();
        if (!(cccd.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số căn cước công dân.");
            txtCCCD.requestFocus();
            return false;
        } else {
            if (!(cccd.matches("^0[0-9]{11}$"))) {
                JOptionPane.showMessageDialog(null,
                        "Nhập sai định dạng.\nSố căn cước gồm 12 chữ số.");
                txtCCCD.selectAll();
                txtCCCD.requestFocus();
                return false;
            }
        }

        if (cmbCaLam.getSelectedItem().equals("Chọn")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn ca làm.");
            return false;
        }
        if (cmbLoaiNV.getSelectedItem().equals("Chọn")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn loại nhân viên.");
            return false;
        }
        if (cmbTinhTP.getSelectedItem().equals("Chọn")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn tỉnh/Thành phố.");
            return false;
        }
        if (cmbQuanHuyen.getSelectedItem().equals("Chọn")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn quận/huyện.");
            return false;
        }
        if (cmbXaPhuong.getSelectedItem().equals("Chọn")) {
            JOptionPane.showMessageDialog(null, "Chưa chọn phường/xã.");
            return false;
        }

        String soNha = txtSoNha.getText().trim();
        if (!(soNha.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập số nhà.");
            txtSoNha.requestFocus();
            return false;
        }

        String tenDuong = txtTenDuong.getText().trim();
        if (!(tenDuong.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập tên đường.");
            txtTenDuong.requestFocus();
            return false;
        }

        String matKhau = txtMatKhau.getText().trim();
        if (!(matKhau.length() > 0)) {
            JOptionPane.showMessageDialog(null, "Chưa nhập mật khẩu.");
            txtMatKhau.requestFocus();
            return false;
        } else {
            if (!(matKhau.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+-=]).{6,20}$"))) {
                JOptionPane.showMessageDialog(null,
                        "Mật khẩu từ 6 đến 20 kí tự, bao gồm:\n Có ít nhất 1 kí tự chữ thường"
                        + "\n Có ít nhất 1 kí tự chữ in hoa\n Có ít nhất 1 kí tự số"
                        + "\n Có ít nhất 1 kí tự đặc biệt !@#$%^&*()_+-=");
                txtMatKhau.selectAll();
                txtMatKhau.requestFocus();
                return false;
            }
        }

        return true;
    }

    private void clearForm() {
        NhanVien nhanVien = nhanVien_DAO.getLastNhanVien();
        String newID = AutoID.generateId(nhanVien.getMaNhanVien(), "NV");
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
        txtMatKhau.setText("Mk#123");
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
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        btnThem = new gui.swing.button.Button();
        btnLamMoi = new gui.swing.button.Button();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        cmbGioiTinh = new javax.swing.JComboBox<>();
        txtSDT = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtCCCD = new javax.swing.JTextField();
        cmbCaLam = new javax.swing.JComboBox<>();
        cmbLoaiNV = new javax.swing.JComboBox<>();
        cmbTinhTP = new javax.swing.JComboBox<>();
        cmbQuanHuyen = new javax.swing.JComboBox<>();
        cmbXaPhuong = new javax.swing.JComboBox<>();
        txtSoNha = new javax.swing.JTextField();
        txtTenDuong = new javax.swing.JTextField();
        txtMatKhau = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBackground(new java.awt.Color(153, 255, 204));
        jPanel1.setPreferredSize(new java.awt.Dimension(709, 812));

        lblMaNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMaNV.setText("Mã nhân viên");

        lblTenNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTenNV.setText("Tên nhân viên");

        lblGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblGioiTinh.setText("Giới tính");

        lblNgaySinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblNgaySinh.setText("Ngày sinh");

        lblSDT.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSDT.setText("SDT");

        lblEmail.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblEmail.setText("Email");

        lblCCCD.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCCCD.setText("Căn cước CD");

        lblCaLam.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblCaLam.setText("Ca làm");

        lblLoaiNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblLoaiNV.setText("Loại nhân viên");

        lblSoNha.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblSoNha.setText("Số nhà");

        lblTenDuong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTenDuong.setText("Tên đường");

        lblXaPhuong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblXaPhuong.setText("Xã/Phường");

        lblQuanHuyen.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblQuanHuyen.setText("Quận/Huyện");

        lblTinhTP.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblTinhTP.setText("Tỉnh/Thành phố");

        lblMatKhau.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        lblMatKhau.setText("Mật khẩu");

        jDateChooser1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        btnThem.setText("Thêm");
        btnThem.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        btnLamMoi.setText("Làm mới");
        btnLamMoi.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        txtMaNV.setBackground(new java.awt.Color(255, 255, 255));
        txtMaNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtTenNV.setBackground(new java.awt.Color(255, 255, 255));
        txtTenNV.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbGioiTinh.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        txtSDT.setBackground(new java.awt.Color(255, 255, 255));
        txtSDT.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtEmail.setBackground(new java.awt.Color(255, 255, 255));
        txtEmail.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtCCCD.setBackground(new java.awt.Color(255, 255, 255));
        txtCCCD.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        cmbCaLam.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmbLoaiNV.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmbTinhTP.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmbQuanHuyen.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        cmbXaPhuong.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N

        txtSoNha.setBackground(new java.awt.Color(255, 255, 255));
        txtSoNha.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtTenDuong.setBackground(new java.awt.Color(255, 255, 255));
        txtTenDuong.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        txtMatKhau.setBackground(new java.awt.Color(255, 255, 255));
        txtMatKhau.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

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
                                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblLoaiNV)
                                    .addComponent(lblCaLam)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblGioiTinh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSDT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE)))
                                .addGap(42, 42, 42)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                                    .addComponent(cmbGioiTinh, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtSDT)
                                    .addComponent(txtEmail)
                                    .addComponent(cmbCaLam, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbLoaiNV, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(68, 68, 68)
                                .addComponent(txtSoNha))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTenNV)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(3, 3, 3)
                                        .addComponent(lblMaNV))
                                    .addComponent(lblCCCD))
                                .addGap(45, 45, 45)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtMaNV)
                                    .addComponent(txtTenNV)
                                    .addComponent(txtCCCD)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(lblTenDuong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblXaPhuong, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lblQuanHuyen, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblMatKhau, javax.swing.GroupLayout.Alignment.LEADING))
                                    .addComponent(lblTinhTP))
                                .addGap(37, 37, 37)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cmbTinhTP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbQuanHuyen, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cmbXaPhuong, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTenDuong, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMatKhau))))))
                .addGap(70, 70, 70))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblNgaySinh, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, 40, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCCCD, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbCaLam, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbLoaiNV, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbTinhTP, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbQuanHuyen, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbXaPhuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSoNha, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTenDuong, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenDuong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
    private javax.swing.JComboBox<String> cmbCaLam;
    private javax.swing.JComboBox<String> cmbGioiTinh;
    private javax.swing.JComboBox<String> cmbLoaiNV;
    private javax.swing.JComboBox<String> cmbQuanHuyen;
    private javax.swing.JComboBox<String> cmbTinhTP;
    private javax.swing.JComboBox<String> cmbXaPhuong;
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
    private javax.swing.JTextField txtCCCD;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSoNha;
    private javax.swing.JTextField txtTenDuong;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
