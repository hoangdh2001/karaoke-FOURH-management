package gui;

import gui.dropshadow.ShadowType;
import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTarget;
import org.jdesktop.animation.timing.TimingTargetAdapter;

public class GD_NhanVien extends JPanel {

    private MigLayout layout;
    private PanelShadow panelHidden;
    private Animator animator;
    private boolean show;
    private final DecimalFormat df = new DecimalFormat("##0.000");
    private MyTextField txtTimKiem;
    private MyComboBox<Object> cmbCot;
    private MyComboBox<Object> cmbGioiTinhTK;
    private MyComboBox<Object> cmbLoaiNVTK;
    private MyComboBox<Object> cmbCaLamTK;
    private Button btnTimKiem;

    public GD_NhanVien() {
        initComponents();
        setPreferredSize(new Dimension(getWidth(), 1000));
        myTable2.fixTable(jScrollPane2);
        buildGD();
    }

    private void buildGD() {
        pnlTop.setLayout(new MigLayout("", "[][]", "[]"));
        pnlTop.setPreferredSize(new Dimension(getWidth(), 130));
        pnlTop.add(createPanelTitle(), "w 100%, h 36!, wrap");
        createPanelSearch();

        createPanelHidden();
        add(panelHidden);
        
        TimingTarget target = new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                float size = fraction * 400;
                if (show) {
                    size = -size;
                } else {
                    size -= 400;
                }
                layout.setComponentConstraints(panelHidden, "pos " + (int) size + " 0 n n, h 100%, w 400!");
                revalidate();
            }

            @Override
            public void end() {
                show = !show;
                if (!show) {
                    panelHidden.setVisible(false);
                }
            }
        };
        animator = new Animator(400, target);
        animator.setResolution(0);
        animator.setDeceleration(0.5f);

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
        String fontName = "sansserif";
        int fontPlain = Font.PLAIN;
        int font16 = 16;
        int font14 = 14;
        Color colorBtn = new Color(184, 238, 241);
        Color colorLabel = new Color(47, 72, 210);

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

//        SwingUtilities.isLeftMouseButton(Event e)&& e.getClickcount()==2
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        pnlTop = new gui.swing.panel.PanelShadow();
        pnlCenter = new gui.swing.panel.PanelShadow();
        lblTitleBang = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        myTable2 = new gui.swing.table2.MyTable();

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

        myTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Mã nhân viên", "Tên nhân viên", "Giới tính", "Ngày sinh", "Số điện thoại", "Ca làm"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(myTable2);

        pnlCenter.add(jScrollPane2, java.awt.BorderLayout.CENTER);

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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTitleBang;
    private gui.swing.table2.MyTable myTable2;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.panel.PanelShadow pnlTop;
    // End of variables declaration//GEN-END:variables
}
