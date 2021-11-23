package gui.component;

import entity.Phong;
import gui.swing.table2.CellStatus;
import java.awt.Font;
import java.text.DecimalFormat;
import javax.swing.JLabel;
import net.miginfocom.swing.MigLayout;

public class PanelInfoOverBottom extends javax.swing.JPanel {
    private MigLayout layout;
    private Phong phong;
    private final Font font = new Font("Sansserif", Font.PLAIN, 14);
    private final DecimalFormat df = new DecimalFormat("#,### Đ");
    
    public PanelInfoOverBottom(Phong phong) {
        this.phong = phong;
        initComponents();
        bulidPanelInfoOverBottom();
    }
    
    private void bulidPanelInfoOverBottom() {
        layout = new MigLayout("debug, wrap", "", "10[]10");
        setLayout(layout);
        JLabel lblTT = new JLabel("Thông tin phòng hát");
        lblTT.setFont(new Font("sasserif", Font.BOLD, 16));
        add(lblTT);
        
        JLabel lblTenPhong = new JLabel(phong.getTenPhong());
        lblTenPhong.setFont(font);
        add(lblTenPhong, "split 2");
        
        JLabel lblTang = new JLabel("  Tầng " + phong.getTang());
        lblTang.setFont(font);
        add(lblTang);
        
        JLabel lblTrangThai = new JLabel("Trạng thái ");
        lblTrangThai.setFont(font);
        add(lblTrangThai, "split 2");
        CellStatus status = new CellStatus(phong.getTrangThai(), true);
        status.setOpaque(false);
        add(status, "w 100!, h 30!");
        
        JLabel lblLoaiPhong = new JLabel(phong.getLoaiPhong().getTenLoaiPhong());
        lblLoaiPhong.setFont(font);
        add(lblLoaiPhong);
        
        JLabel lblGiaPhong = new JLabel("Giá phòng " + df.format(phong.getLoaiPhong().getGiaPhong()));
        lblGiaPhong.setFont(font);
        add(lblGiaPhong);
        
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 391, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 281, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
