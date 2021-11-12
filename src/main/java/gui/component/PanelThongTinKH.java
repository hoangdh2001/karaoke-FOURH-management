package gui.component;

import entity.KhachHang;

public class PanelThongTinKH extends javax.swing.JPanel {
    private KhachHang khachHang;

    public PanelThongTinKH(KhachHang khachHang) {
        this.khachHang = khachHang;
        initComponents();
        xuLyDuLieu();
    }

    private void xuLyDuLieu(){
        lblTTMaKhachHang.setText(khachHang.getMaKhachHang());
        lblTTTenKhachHang.setText(khachHang.getTenKhachHang());
        lblTTCanCuocCongDan.setText(khachHang.getCanCuocCD());
        lblTTSoDienThoai.setText(khachHang.getSoDienThoai());
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlThongTinKhachHang = new javax.swing.JPanel();
        lblMaKhachHang = new javax.swing.JLabel();
        lblTenKhachHang = new javax.swing.JLabel();
        lblCanCuocCongDan = new javax.swing.JLabel();
        lblSoDienThoai = new javax.swing.JLabel();
        lblTTMaKhachHang = new javax.swing.JLabel();
        lblTTTenKhachHang = new javax.swing.JLabel();
        lblTTCanCuocCongDan = new javax.swing.JLabel();
        lblTTSoDienThoai = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        pnlThongTinKhachHang.setBackground(new java.awt.Color(204, 204, 255));
        pnlThongTinKhachHang.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N

        lblMaKhachHang.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblMaKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblMaKhachHang.setText("MÃ KHÁCH HÀNG: ");

        lblTenKhachHang.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblTenKhachHang.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTenKhachHang.setText("TÊN KHÁCH HÀNG:");

        lblCanCuocCongDan.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblCanCuocCongDan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCanCuocCongDan.setText("CĂN CƯỚC CÔNG DÂN: ");

        lblSoDienThoai.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        lblSoDienThoai.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSoDienThoai.setText("SỐ ĐIỆN THOẠI: ");

        lblTTMaKhachHang.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblTTMaKhachHang.setText("KH1111111");

        lblTTTenKhachHang.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblTTTenKhachHang.setText("Nguyễn Thị Hảo");

        lblTTCanCuocCongDan.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblTTCanCuocCongDan.setText("261517679");

        lblTTSoDienThoai.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblTTSoDienThoai.setText("0824601014");

        javax.swing.GroupLayout pnlThongTinKhachHangLayout = new javax.swing.GroupLayout(pnlThongTinKhachHang);
        pnlThongTinKhachHang.setLayout(pnlThongTinKhachHangLayout);
        pnlThongTinKhachHangLayout.setHorizontalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlThongTinKhachHangLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblCanCuocCongDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTTMaKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblTTTenKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                            .addComponent(lblTTCanCuocCongDan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblTTSoDienThoai, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnlThongTinKhachHangLayout.setVerticalGroup(
            pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlThongTinKhachHangLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTMaKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTTTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblCanCuocCongDan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTCanCuocCongDan, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40)
                .addGroup(pnlThongTinKhachHangLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblTTSoDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(172, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongTinKhachHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlThongTinKhachHang, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCanCuocCongDan;
    private javax.swing.JLabel lblMaKhachHang;
    private javax.swing.JLabel lblSoDienThoai;
    private javax.swing.JLabel lblTTCanCuocCongDan;
    private javax.swing.JLabel lblTTMaKhachHang;
    private javax.swing.JLabel lblTTSoDienThoai;
    private javax.swing.JLabel lblTTTenKhachHang;
    private javax.swing.JLabel lblTenKhachHang;
    private javax.swing.JPanel pnlThongTinKhachHang;
    // End of variables declaration//GEN-END:variables
}
