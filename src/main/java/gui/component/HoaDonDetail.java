/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import entity.HoaDon;
import gui.swing.event.EventTabSelected;
import java.awt.Component;

/**
 *
 * @author Hao
 */
public class HoaDonDetail extends javax.swing.JPanel {

    private HoaDonDetail hoaDonDetail;
    private HoaDon hoaDon;
    private PanelThongTinHoaDon pnlThongTinHoaDon;
    
    /**
     * Creates new form HoaDonDetail
     */
    public HoaDonDetail(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
        initComponents();
        buildDisplay();
       
    }
    
    private void buildDisplay() {
        pnlThongTinHoaDon = new PanelThongTinHoaDon(hoaDon);
        createTabButton();
    }
    
    private void createTabButton() {
        tabButton.setEvent(new EventTabSelected() {
            @Override
            public boolean selected(int index, boolean selectedTab) {
                if(index == 0) {
                    showTab(pnlThongTinHoaDon);
                }
                else if(index == 1) {
                    showTab(new PanelSuaHoaDon());
                }
                tabButton.check();
                return true;
            }
        });
        tabButton.addTabButtonItem("Thông tin");
        tabButton.addTabButtonItem("Sửa");
        pnlBottom.add(pnlThongTinHoaDon);
    }    
    
    private void showTab(Component com) {
        pnlBottom.removeAll();
        pnlBottom.add(com);
        pnlBottom.repaint();
        pnlBottom.revalidate();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        lblTen = new javax.swing.JLabel();
        tabButton = new gui.swing.panel.TabButton();
        pnlBottom = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));

        lblTen.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTen.setText("Nguyễn Thị Hảo");

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblTen, javax.swing.GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
            .addComponent(tabButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTopLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(lblTen, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(tabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        add(pnlTop, java.awt.BorderLayout.PAGE_START);

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setLayout(new java.awt.BorderLayout());
        add(pnlBottom, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTen;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlTop;
    private gui.swing.panel.TabButton tabButton;
    // End of variables declaration//GEN-END:variables
}