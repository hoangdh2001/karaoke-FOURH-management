package gui.component;

import entity.NhanVien;
import gui.swing.event.EventTabSelected;
import gui.swing.panel.TabButton;
import java.awt.BorderLayout;
import java.awt.Component;
import javax.swing.JPanel;

public class NhanVienDetail extends javax.swing.JPanel {

    private NhanVien nhanVien;
    private PanelTabThongTinNV pnlTabThongTinNV;
    private PanelTabSuaThongTinNV1 pnlTabSuaThongTinNV;

    public NhanVienDetail(NhanVien nv) {
        this.nhanVien = nv;
        System.out.println("Nhan vien dc chon:\n" + nhanVien);

        initComponents();
        buildDisplay();

    }

    private void buildDisplay() {
        pnlTabThongTinNV = new PanelTabThongTinNV(nhanVien);
        pnlTabSuaThongTinNV = new PanelTabSuaThongTinNV1(nhanVien);
        createTabButton();
    }

    private void createTabButton() {
        tab.setEvent(new EventTabSelected() {
            @Override
            public boolean selected(int index, boolean selectedTab) {
                if (index == 0) {

                    showTab(pnlTabThongTinNV);
                    lblTenNhanVien.setVisible(true);
                } else if (index == 1) {
                    
                    showTab(pnlTabSuaThongTinNV);
                    lblTenNhanVien.setVisible(false);
                }
                tab.check();
                return true;
            }
        });

        tab.addTabButtonItem("Thông tin");
        lblTenNhanVien.setText(nhanVien.getTenNhanVien());
        tab.addTabButtonItem("Sửa");
        //Hiện tab thông tin đầu tiên khi mở tab
        pane.add(pnlTabThongTinNV);
    }

    private void showTab(Component com) {
        pane.removeAll();
        pane.add(com);
        pane.repaint();
        pane.revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pane = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        lblTenNhanVien = new javax.swing.JLabel();
        tab = new gui.swing.panel.TabButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(248, 248, 242));
        setLayout(new java.awt.BorderLayout());

        pane.setBackground(new java.awt.Color(255, 255, 255));
        pane.setLayout(new java.awt.BorderLayout());
        add(pane, java.awt.BorderLayout.CENTER);

        jPanel2.setOpaque(false);

        lblTenNhanVien.setFont(new java.awt.Font("SansSerif", 1, 24)); // NOI18N
        lblTenNhanVien.setForeground(new java.awt.Color(68, 71, 90));
        lblTenNhanVien.setText("Some text");

        tab.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(lblTenNhanVien)
                .addContainerGap(565, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 18, Short.MAX_VALUE)
                .addComponent(lblTenNhanVien)
                .addGap(18, 18, 18)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(jPanel2, java.awt.BorderLayout.PAGE_START);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblTenNhanVien;
    private javax.swing.JPanel pane;
    private gui.swing.panel.TabButton tab;
    // End of variables declaration//GEN-END:variables
}
