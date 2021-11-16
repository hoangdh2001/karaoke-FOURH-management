/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.component;

import entity.PhieuDatPhong;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Hao
 */
public class PanelSuaPDP extends javax.swing.JPanel {
    private PhieuDatPhong phieu;

    /**
     * Creates new form PanelSuaPDP
     */
    public PanelSuaPDP(PhieuDatPhong phieu) {
        this.phieu = phieu;
        initComponents();
        xuLyDuLieu();
    }

     private void xuLyDuLieu(){
        DecimalFormat dcf = new DecimalFormat("#,###   VNĐ  ");
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        txtMaPhieu.setText(phieu.getMaPhieuDat());
        txtTenKhachHang.setText(phieu.getKhachHang().getTenKhachHang());
        //.setText(phieu.getKhachHang().getCanCuocCD());
        txtPhong.setText(phieu.getPhong().getTenPhong());
        //setText(Integer.toString(phieu.getPhong().getTang()));
        //.setText(phieu.getPhong().getLoaiPhong().getTenLoaiPhong());
        //.setText(dcf.format(phieu.getPhong().getLoaiPhong().getGiaPhong()));
        txtNgayLap.setText(fm.format(phieu.getNgayTao()));
        txtNgayDat.setText(fm.format(phieu.getNgayDat()));
        txtTrangThai.setText(phieu.getTrangThai().getTrangThai());
        txtTienCoc.setText(dcf.format(phieu.getTienCoc()));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSuaPhieuDP = new javax.swing.JPanel();
        lblMaPhieu = new javax.swing.JLabel();
        lblNgayLap = new javax.swing.JLabel();
        lblKhachHang = new javax.swing.JLabel();
        lblPhong = new javax.swing.JLabel();
        lblNgayDat = new javax.swing.JLabel();
        lblTrangThai = new javax.swing.JLabel();
        lblTienCoc = new javax.swing.JLabel();
        txtMaPhieu = new gui.swing.textfield.MyTextField();
        txtNgayLap = new gui.swing.textfield.MyTextField();
        txtTenKhachHang = new gui.swing.textfield.MyTextField();
        txtPhong = new gui.swing.textfield.MyTextField();
        txtNgayDat = new gui.swing.textfield.MyTextField();
        txtTrangThai = new gui.swing.textfield.MyTextField();
        txtTienCoc = new gui.swing.textfield.MyTextField();
        btnXacNhan = new gui.swing.button.Button();

        pnlSuaPhieuDP.setBackground(new java.awt.Color(255, 255, 255));

        lblMaPhieu.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblMaPhieu.setText("Mã phiếu đặt: ");

        lblNgayLap.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblNgayLap.setText("Ngày lập phiếu: ");

        lblKhachHang.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblKhachHang.setText("Tên khách hàng: ");

        lblPhong.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblPhong.setText("Phòng: ");

        lblNgayDat.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblNgayDat.setText("Ngày đặt: ");

        lblTrangThai.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTrangThai.setText("Trạng thái:");

        lblTienCoc.setFont(new java.awt.Font("SansSerif", 1, 16)); // NOI18N
        lblTienCoc.setText("Tiền cọc: ");

        txtMaPhieu.setText("myTextField1");
        txtMaPhieu.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtNgayLap.setText("myTextField1");
        txtNgayLap.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtTenKhachHang.setText("myTextField1");
        txtTenKhachHang.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtPhong.setText("myTextField1");
        txtPhong.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtNgayDat.setText("myTextField1");
        txtNgayDat.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtTrangThai.setText("myTextField1");
        txtTrangThai.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        txtTienCoc.setText("myTextField1");
        txtTienCoc.setFont(new java.awt.Font("sansserif", 0, 16)); // NOI18N

        btnXacNhan.setText("Xác Nhận");
        btnXacNhan.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N

        javax.swing.GroupLayout pnlSuaPhieuDPLayout = new javax.swing.GroupLayout(pnlSuaPhieuDP);
        pnlSuaPhieuDP.setLayout(pnlSuaPhieuDPLayout);
        pnlSuaPhieuDPLayout.setHorizontalGroup(
            pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuaPhieuDPLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSuaPhieuDPLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlSuaPhieuDPLayout.createSequentialGroup()
                        .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(lblTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblMaPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(lblTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtTienCoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMaPhieu, javax.swing.GroupLayout.DEFAULT_SIZE, 381, Short.MAX_VALUE)
                            .addComponent(txtNgayLap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtPhong, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayDat, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtTrangThai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        pnlSuaPhieuDPLayout.setVerticalGroup(
            pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSuaPhieuDPLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaPhieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblPhong, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgayDat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(pnlSuaPhieuDPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienCoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSuaPhieuDP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSuaPhieuDP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.button.Button btnXacNhan;
    private javax.swing.JLabel lblKhachHang;
    private javax.swing.JLabel lblMaPhieu;
    private javax.swing.JLabel lblNgayDat;
    private javax.swing.JLabel lblNgayLap;
    private javax.swing.JLabel lblPhong;
    private javax.swing.JLabel lblTienCoc;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JPanel pnlSuaPhieuDP;
    private gui.swing.textfield.MyTextField txtMaPhieu;
    private gui.swing.textfield.MyTextField txtNgayDat;
    private gui.swing.textfield.MyTextField txtNgayLap;
    private gui.swing.textfield.MyTextField txtPhong;
    private gui.swing.textfield.MyTextField txtTenKhachHang;
    private gui.swing.textfield.MyTextField txtTienCoc;
    private gui.swing.textfield.MyTextField txtTrangThai;
    // End of variables declaration//GEN-END:variables
}
