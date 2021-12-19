/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.dialog;

import dao.DiaChi_DAO;
import dao.NhaCungCap_DAO;
import entity.DiaChi;
import entity.NhaCungCap;
import gui.swing.button.Button;
import gui.swing.model.AutoID;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import net.miginfocom.swing.MigLayout;
import service.NhaCungCapService;

/**
 *
 * @author Hao
 */
public class DL_ThemNhaCungCap extends javax.swing.JDialog {

    private static Object model;
    private JComboBox<String> cmbXaPhuong, cmbQuan_Huyen, cmbTinh;
    private JTextField txtTenNCC;
    private JTextField txtSDT;
    private JTextField txtSoNha_Duong;

    private JButton btnThemVaSua;
    private JButton btnHuy;
    private DiaChi_DAO diaChi_Dao;
    private NhaCungCapService nhaCungCapService;

    /**
     * Creates new form NewJDialog
     */
    public DL_ThemNhaCungCap(Frame frame, Object model) {
        super(frame, true);
        this.model = model;
        initComponents();
        setModal(true);
        buildGD();

        addAction();
    }

    public void buildGD() {
        this.setTitle("Thêm nhà cung cấp");

        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);

        pnlMain.setLayout(new MigLayout("", "10[center][center] 10 ", ""));
        pnlLeft.setLayout(new MigLayout("", "[]5[]", "20[]15[]15[]20[]20"));
        pnlRight.setLayout(new MigLayout("", "[]5[]", "20[]15[]15[]20[]20"));
        pnlMain.add(pnlLeft, " w 380!, h 100%");
        pnlMain.add(pnlRight, " w 380!,h 100%");

        //Tên nhà cung cấp
        JLabel lblTenNCC = new JLabel("Tên nhà cung cấp:");
        lblTenNCC.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(lblTenNCC, "align right");

        txtTenNCC = new JTextField();
        txtTenNCC.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(txtTenNCC, "w 90%, h 36!, wrap");

        //Số điện thoại
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(lblSDT, "align right");

        txtSDT = new JTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(txtSDT, "w 90%, h 36!, wrap");

        //Số nhà số đường
        JLabel lblSoNha_Duong = new JLabel("Số nhà-số đường:");
        lblSoNha_Duong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(lblSoNha_Duong, "align right");

        txtSoNha_Duong = new JTextField();
        txtSoNha_Duong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlLeft.add(txtSoNha_Duong, "w 90%, h 36!, wrap");

        //Tỉnh thành
        JLabel lblTinh = new JLabel("Tỉnh/Thành:");
        lblTinh.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(lblTinh, "align right");

        cmbTinh = new JComboBox<String>();
        cmbTinh.addItem("--chọn--");
        cmbTinh.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(cmbTinh, "w 250!,h 36!, wrap");

        //Quận huyện
        JLabel lblQuan_Huyen = new JLabel("Quận/Huyện:");
        lblQuan_Huyen.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(lblQuan_Huyen, "align right");

        cmbQuan_Huyen = new JComboBox<String>();
        cmbQuan_Huyen.addItem("--chọn--");
        cmbQuan_Huyen.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(cmbQuan_Huyen, "w 250!,h 36!, wrap");

        //Xã phường
        JLabel lblXaPhuong = new JLabel("Xã/Phường:");
        lblXaPhuong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(lblXaPhuong, "align right");

        cmbXaPhuong = new JComboBox<String>();
        cmbXaPhuong.addItem("--Chọn--");
        cmbXaPhuong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlRight.add(cmbXaPhuong, "w 250!, h 36!, wrap");

        //Nút hủy
        btnHuy = new JButton("Hủy");
        btnHuy.setFont(new Font("sansserif", Font.BOLD, 12));
        btnHuy.setBackground(new Color(54,88,153));
        btnHuy.setForeground(Color.WHITE);
        pnlRight.add(btnHuy, "w 90!, h 30!, skip 1, split 2, align right");

        // Nút Thêm
        btnThemVaSua = new JButton("Thêm");
        btnThemVaSua.setFont(new Font("sansserif", Font.BOLD, 12));
        btnThemVaSua.setBackground(new Color(54,88,153));
        btnThemVaSua.setForeground(Color.WHITE);
        pnlRight.add(btnThemVaSua, "w 90!, h 30!");

        setSize(800, 280);
        setResizable(false);
        final Toolkit toolkit = Toolkit.getDefaultToolkit();
        final Dimension screenSize = toolkit.getScreenSize();
        final int x = (screenSize.width - this.getWidth()) / 2;
        final int y = (screenSize.height - this.getHeight()) / 2;
        setLocation(x, y);

        initDao();
        initModel();

    }

    public void loadTinhThanh() {
        List<String> dsTinhThanh = diaChi_Dao.getDSTinhThanh();
        dsTinhThanh.forEach((p) -> {
            cmbTinh.addItem(p);
        });
    }

    public void loadQuanHuyen() {
        List<String> dsQuanHuyen = diaChi_Dao.getDSQuanHuyen();
        dsQuanHuyen.forEach((p) -> {
            cmbQuan_Huyen.addItem(p);

        });
    }

    public void loadQuanHuyenByTinh(String tinh) {
        List<String> dsQuanHuyen = diaChi_Dao.getDSQuanHuyenByTinh(tinh);
        dsQuanHuyen.forEach((p) -> {
            cmbQuan_Huyen.addItem(p);

        });
    }

    public void loadXaPhuong() {
        List<String> dsXaPhuong = diaChi_Dao.getDSXaPhuong();
        dsXaPhuong.forEach((p) -> {
            cmbXaPhuong.addItem(p);

        });
    }

    public void loadXaPhuongByTinhQuan(String tinh, String quanHuyen) {
        List<String> dsXaPhuong = diaChi_Dao.getDSXaPhuongByQuanHuyen(tinh, quanHuyen);
        dsXaPhuong.forEach((p) -> {
            cmbXaPhuong.addItem(p);

        });
    }

    private void initModel() {
        if (model != null) {
            this.setTitle("Xem chỉnh sửa thông tin phòng");
            btnThemVaSua.setText("Sửa");
            NhaCungCap ncc = (NhaCungCap) model;
            txtSDT.setText(ncc.getSoDienThoai());
            txtSoNha_Duong.setText(ncc.getDiaChi().getSoNha() + " - " + ncc.getDiaChi().getTenDuong());
            txtTenNCC.setText(ncc.getTenNCC());

            cmbTinh.setSelectedItem(ncc.getDiaChi().getTinhThanh());
            cmbQuan_Huyen.setSelectedItem(ncc.getDiaChi().getQuanHuyen());
            cmbXaPhuong.setSelectedItem(ncc.getDiaChi().getXaPhuong());
        }
    }

    public void initDao() {
        nhaCungCapService = new NhaCungCap_DAO();
        diaChi_Dao = new DiaChi_DAO();
        if (model == null) {
            loadTinhThanh();
            loadQuanHuyen();
            loadXaPhuong();
        } else {
            NhaCungCap ncc = (NhaCungCap) model;
            loadTinhThanh();
            cmbTinh.setSelectedItem(ncc.getDiaChi().getTinhThanh());
            loadQuanHuyenByTinh(cmbTinh.getSelectedItem().toString());
            cmbQuan_Huyen.setSelectedItem(ncc.getDiaChi().getQuanHuyen());
            loadXaPhuongByTinhQuan(cmbTinh.getSelectedItem().toString(), cmbQuan_Huyen.getSelectedItem().toString());
            cmbXaPhuong.setSelectedItem(ncc.getDiaChi().getXaPhuong());
        }
    }

    public void resetQuanHuyen() {
        cmbQuan_Huyen.removeAllItems();
        cmbQuan_Huyen.addItem("--chọn--");
        loadQuanHuyenByTinh(cmbTinh.getSelectedItem().toString());
        cmbXaPhuong.removeAllItems();
        cmbXaPhuong.addItem("--chọn--");
    }

    public void resetxaPhuong() {
        cmbXaPhuong.removeAllItems();
        cmbXaPhuong.addItem("--chọn--");
        if (cmbQuan_Huyen.getSelectedIndex() > 0) {
            loadXaPhuongByTinhQuan(cmbTinh.getSelectedItem().toString(), cmbQuan_Huyen.getSelectedItem().toString());
        }
    }

    private boolean valiDataNull() {
        String Sdt = txtSDT.getText().trim();
        String tenNCC = txtTenNCC.getText().trim();
        String soDuong = txtSoNha_Duong.getText().trim();

        if (tenNCC.equals("")) {
            showMsg("Nhập tên nhà cung cấp");
            txtTenNCC.requestFocus();
            return false;
        }
        if (Sdt.equals("")) {
            showMsg("Nhập số điện thoại liên lạc !");
            txtSDT.requestFocus();
            return false;
        }
        if (soDuong.equals("")) {
            showMsg("Nhập số nhà - tên đường !");
            txtSoNha_Duong.requestFocus();
            return false;
        }

        if (cmbTinh.getSelectedIndex() == 0) {
            showMsg("Chọn tỉnh/thành phố !");
            return false;
        }

        if (cmbQuan_Huyen.getSelectedIndex() == 0) {
            showMsg("Chọn quận/huyện !");
            return false;
        }

        if (cmbXaPhuong.getSelectedIndex() == 0) {
            showMsg("Chọn xã/phường !");
            return false;
        }

        return true;
    }

    private boolean valiDataSDT() {
        String sdt = txtSDT.getText().trim();
//        (028) 38222855
        if (!(sdt.matches("^(0|\\+84)(\\s|\\.)?((3[2-9])|(5[689])|(7[06-9])|(8[1-689])|(9[0-46-9]))(\\d)(\\s|\\.)?(\\d{3})(\\s|\\.)?(\\d{3})$"))) {
            showMsg("Số điện thoại của nhà cung cấp không hợp lệ");
            txtSDT.requestFocus();
            txtSDT.selectAll();
            return false;
        }
        return true;
    }

    private void showMsg(String msg) {
        JOptionPane.showMessageDialog(null, msg);
    }

    private void addAction() {
        btnThemVaSua.addActionListener(new createActionListener());
        cmbTinh.addActionListener(new createActionListener());
        cmbQuan_Huyen.addActionListener(new createActionListener());
        txtSDT.setFocusTraversalKeysEnabled(false);
        txtSDT.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_TAB || e.getKeyChar() == KeyEvent.VK_ENTER) {
                    valiDataSDT();
                    txtSoNha_Duong.requestFocus();
                }
            }
        });

    }

    private class createActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj.equals(btnThemVaSua) && valiDataNull()) {
                NhaCungCap nccMoi = new NhaCungCap();
                String maxID = nhaCungCapService.getMaxID();
                String maNhaCungCap;
                if (maxID == null) {
                    maNhaCungCap = "NCC001";
                } else {
                    maNhaCungCap = AutoID.generateId(maxID, "NCC");
                }
                nccMoi.setMaNCC(maNhaCungCap);
                String tinhThanh = cmbTinh.getSelectedItem().toString();
                String quanHuyen = cmbQuan_Huyen.getSelectedItem().toString();
                String xaPhuong = cmbXaPhuong.getSelectedItem().toString();

                String[] soNhaSoDuong = txtSoNha_Duong.getText().split("-");

                DiaChi diachi = new DiaChi(soNhaSoDuong[0], soNhaSoDuong[1], xaPhuong, quanHuyen, tinhThanh);

                nccMoi.setDiaChi(diachi);
                nccMoi.setSoDienThoai(txtSDT.getText().trim());
                nccMoi.setTenNCC(txtTenNCC.getText().trim());

                if (model != null) {
                    NhaCungCap nccCu = (NhaCungCap) model;
                    nccMoi.setMaNCC(nccCu.getMaNCC());
                    nhaCungCapService.updateNhaCungCap(nccMoi);
                    showMsg("Sửa thành công nhà cung cấp: " + nccMoi.getTenNCC());
                } else {
                    nhaCungCapService.addNhaCungCap(nccMoi);
                    showMsg("Thêm thành công nhà cung cấp: " + nccMoi.getTenNCC());
                }

                dispose();
            } else if (obj.equals(cmbTinh)) {
                resetQuanHuyen();
            } else if (obj.equals(cmbQuan_Huyen)) {
                resetxaPhuong();
            } else if (obj.equals(btnHuy)) {
                dispose();
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlMain = new javax.swing.JPanel();
        pnlLeft = new javax.swing.JPanel();
        pnlRight = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        pnlMain.setBackground(new java.awt.Color(255, 255, 255));

        pnlLeft.setOpaque(false);

        javax.swing.GroupLayout pnlLeftLayout = new javax.swing.GroupLayout(pnlLeft);
        pnlLeft.setLayout(pnlLeftLayout);
        pnlLeftLayout.setHorizontalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 210, Short.MAX_VALUE)
        );
        pnlLeftLayout.setVerticalGroup(
            pnlLeftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 243, Short.MAX_VALUE)
        );

        pnlRight.setOpaque(false);

        javax.swing.GroupLayout pnlRightLayout = new javax.swing.GroupLayout(pnlRight);
        pnlRight.setLayout(pnlRightLayout);
        pnlRightLayout.setHorizontalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );
        pnlRightLayout.setVerticalGroup(
            pnlRightLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout pnlMainLayout = new javax.swing.GroupLayout(pnlMain);
        pnlMain.setLayout(pnlMainLayout);
        pnlMainLayout.setHorizontalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlMainLayout.createSequentialGroup()
                .addComponent(pnlLeft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlRight, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlMainLayout.setVerticalGroup(
            pnlMainLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLeft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlRight, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlMain, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnlLeft;
    private javax.swing.JPanel pnlMain;
    private javax.swing.JPanel pnlRight;
    // End of variables declaration//GEN-END:variables

}
