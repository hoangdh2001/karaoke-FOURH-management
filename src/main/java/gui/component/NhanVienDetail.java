/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import com.toedter.calendar.JDateChooser;
import dao.Phong_DAO;
import gui.event.EventTabSelected;
import gui.swing.layout.WrapLayout;
import gui.swing.panel.TabButton;
import gui.swing.scrollbar.ScrollBarCustom;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Admin
 */
public class NhanVienDetail extends javax.swing.JPanel {

    private MyTextField txtMaNV;
    private MyTextField txtTenNV;
    private MyComboBox<Object> cmbGioiTinh;
    private JDateChooser dscNgaySinh;
    private MyTextField txtSDT;
    private MyTextField txtEmail;
    private MyTextField txtDiaChi;
    private MyComboBox<Object> cmbLoaiNV;
    private MyComboBox<Object> cmbCaLam;
    private MyTextField txtCCCD;

    private TabButton tabPane;
    private JPanel pane;
    private List<JPanel> panels = new ArrayList<>();
    private JScrollPane scrTab;
    private JPanel pnlTabInfo;

    /**
     * Creates new form NhanVienDetail
     */
    public NhanVienDetail() {
        initComponents();
        buildNhanVienDetail();
    }

    private void buildNhanVienDetail() {
        this.setLayout(new BorderLayout());
        this.add(createTabPane(), BorderLayout.NORTH);
        this.add(createPane(), BorderLayout.CENTER);
    }

    private JScrollPane createPane() {
        scrTab = new JScrollPane();
        pane = new JPanel();
        pane.setLayout(new BorderLayout());
        pane.setOpaque(false);
        pane.add(createTabInfo());
        scrTab.getViewport().setBackground(Color.WHITE);
        scrTab.setVerticalScrollBar(new ScrollBarCustom());
        
        JPanel p = new JPanel();
        p.setOpaque(false);
        scrTab.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
        scrTab.setViewportView(pane);
        scrTab.getVerticalScrollBar().setUnitIncrement(50);
        scrTab.setBorder(null);
        return scrTab;
    }

    private JPanel createTabPane() {
        tabPane = new TabButton();
        tabPane.setEvent(new EventTabSelected() {
            @Override
            public boolean selected(int index, boolean selectedTab) {
                showTabPane(panels.get(index));
                scrTab.getVerticalScrollBar().setValue(0);
                tabPane.check();
                return true;
            }
        });
        tabPane.setBackground(Color.WHITE);

        return tabPane;
    }

    private void showTabPane(Component component) {
        pane.removeAll();
        pane.add(component);
        pane.repaint();
        pane.revalidate();
    }

    private JPanel createTabInfo() {
        /*Begin: Khởi tạo*/
        pnlTabInfo = new JPanel();
        pnlTabInfo.setOpaque(false);
        pnlTabInfo.setLayout(new BorderLayout());
        panels.add(pnlTabInfo);
        tabPane.addTabButtonItem("Thông tin");
        /*End: Khởi tạo*/

        
        
        /*Begin: Thành phần con - hiển thị thông tin*/
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font14 = 14;

        JPanel pnlCenter = new JPanel();
        pnlCenter.setBorder(BorderFactory.createLineBorder(Color.yellow));
        pnlCenter.setLayout(new MigLayout("", "[]", "[][][][][][][][][]"));

        // Mã nhân viên
        JLabel lblMaNV = new JLabel("Mã nhân viên:");
        lblMaNV.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblMaNV, "align right");

        txtMaNV = new MyTextField();
        txtMaNV.setFont(new Font(fontName, fontPlain, font14));
        txtMaNV.setBorderLine(true);
        txtMaNV.setBorderRadius(5);
        txtMaNV.setEditable(false);
        pnlCenter.add(txtMaNV, "w 80%, h 36!, wrap");

        //Tên nhân viên
        JLabel lblTenNV = new JLabel("Tên nhân viên:");
        lblTenNV.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblTenNV, "align right");

        txtTenNV = new MyTextField();
        txtTenNV.setFont(new Font(fontName, fontPlain, font14));
        txtTenNV.setBorderLine(true);
        txtTenNV.setBorderRadius(5);
        pnlCenter.add(txtTenNV, "w 80%, h 36!, wrap");

        //Giới tính
        JLabel lblGioiTinh = new JLabel("Giới tính:");
        lblGioiTinh.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblGioiTinh, "align right");

        cmbGioiTinh = new MyComboBox<>();
        cmbGioiTinh.setFont(new Font(fontName, fontPlain, font14));
        cmbGioiTinh.setBorderLine(true);
        cmbGioiTinh.setBorderRadius(10);
        cmbGioiTinh.addItem("Nam");
        cmbGioiTinh.addItem("Nữ");
        pnlCenter.add(cmbGioiTinh, "w 80%, h 36!, wrap");

        //Ngày sinh
        JLabel lblNgaySinh = new JLabel("Ngày sinh:");
        lblNgaySinh.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblNgaySinh, "align right");

        dscNgaySinh = new JDateChooser();
        dscNgaySinh.setFont(new Font(fontName, fontPlain, font14));
        dscNgaySinh.setDateFormatString("dd-MM-yyyy");
        pnlCenter.add(dscNgaySinh, "w 80%, h 36!, wrap");

        //Số điện thoại
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblSDT, "align right");

        txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontPlain, font14));
        txtSDT.setBorderLine(true);
        txtSDT.setBorderRadius(5);
        pnlCenter.add(txtSDT, "w 80%, h 36!, wrap");

        //Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblEmail, "align right");

        txtEmail = new MyTextField();
        txtEmail.setFont(new Font(fontName, fontPlain, font14));
        txtEmail.setBorderLine(true);
        txtEmail.setBorderRadius(5);
        pnlCenter.add(txtEmail, "w 80%, h 36!, wrap");

        //Địa chỉ
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblDiaChi, "align right");

        txtDiaChi = new MyTextField();
        txtDiaChi.setFont(new Font(fontName, fontPlain, font14));
        txtDiaChi.setBorderLine(true);
        txtDiaChi.setBorderRadius(5);
        pnlCenter.add(txtDiaChi, "w 80%, h 36!, wrap");

        //Loại nhân viên
        JLabel lblLoaiNV = new JLabel("Loại nhân viên:");
        lblLoaiNV.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblLoaiNV, "align right");

        cmbLoaiNV = new MyComboBox<>();
        cmbLoaiNV.setFont(new Font(fontName, fontPlain, font14));
        cmbLoaiNV.setBorderLine(true);
        cmbLoaiNV.setBorderRadius(10);
        pnlCenter.add(cmbLoaiNV, "w 80%, h 36!, wrap");

        //Ca làm
        JLabel lblCaLam = new JLabel("Ca làm:");
        lblCaLam.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblCaLam, "align right");

        cmbCaLam = new MyComboBox<>();
        cmbCaLam.setFont(new Font(fontName, fontPlain, font14));
        cmbCaLam.setBorderLine(true);
        cmbCaLam.setBorderRadius(10);
        pnlCenter.add(cmbCaLam, "w 80%, h 36!, wrap");

        //Căn cước công dân
        JLabel lblCCCD = new JLabel("CCCD:");
        lblCCCD.setFont(new Font(fontName, fontPlain, font14));
        pnlCenter.add(lblCCCD, "align right");

        txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font(fontName, fontPlain, font14));
        txtCCCD.setBorderLine(true);
        txtCCCD.setBorderRadius(5);
        pnlCenter.add(txtCCCD, "w 80%, h 36!, wrap");
        /*End: Thành phần con - hiển thị thông tin*/
        
        pnlTabInfo.add(pnlCenter, BorderLayout.CENTER);
        
        return pnlTabInfo;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(153, 255, 153));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 953, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 578, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
