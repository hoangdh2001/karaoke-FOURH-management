package gui;

import dao.KhachHang_DAO;
import entity.KhachHang;
import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.table2.EventAction;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import gui.swing.event.EventSelectedRow;
import javax.swing.JCheckBox;
import javax.swing.JTextField;

/**
 *
 * @author Hao
 */
public class GD_KhachHang extends javax.swing.JPanel implements ActionListener, KeyListener{
    List<KhachHang> dsKhachHang = new ArrayList<KhachHang>();
    private KhachHang_DAO khachHang_Dao;
    private JTextField txtTimKiem;
    private Button btnLamMoi;
    private EventAction eventAction;
    private PanelShadow panelHidden;
    private EventSelectedRow eventSelectedRow;
    private Button btnSua;
    private Object evt;
    /**
     * Creates new form GD_KhachHang
     */
    public GD_KhachHang() {
        initComponents();
        buildGD();
        
    }
    
    private void buildGD(){
        khachHang_Dao = new KhachHang_DAO();

        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 14;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setLayout(new MigLayout("", "push[center]5[center] 20[center]10[center]push", "60[center]10"));
        pnlTop.add(createPanelTitle(), "span,pos 0al 0al 100% n, h 40!");
      
        JLabel lblKhachHang = new JLabel("Nhập tên/ số điện thoại (các số cuối)");
        lblKhachHang.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTop.add(lblKhachHang);
        
        /*Ô nhập thông tin tìm kiếm*/
        txtTimKiem = new JTextField();
        txtTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
//        txtTimKiem.setBorderLine(true);
//        txtTimKiem.setBorderRadius(5);
        pnlTop.add(txtTimKiem, "w 40%, h 36!");
        
        btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontStyle, fontSize));
        btnLamMoi.setBackground(colorBtn);
        btnLamMoi.setBorderline(true);
        btnLamMoi.setBorderRadius(5);
        pnlTop.add(btnLamMoi, "w 100!, h 36!");
        
        btnSua = new Button("Sửa");
        btnSua.setFont(new Font(fontName, fontStyle, fontSize));
        btnSua.setBackground(colorBtn);
        btnSua.setBorderline(true);
        btnSua.setBorderRadius(5);
        pnlTop.add(btnSua, "w 100!, h 36!");
        
        btnLamMoi.addActionListener(this);
        txtTimKiem.addKeyListener(this);
        btnSua.addActionListener(this);
        tblKhachHang.addKeyListener(this);
        //xuLySuKien();
        createTable();
        setOpaque(false);
//        setPreferredSize(new Dimension(getWidth(), 950));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKhachHang = new gui.swing.table2.MyTableFlatlaf();

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(2);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1032, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 247, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(2);
        pnlBottom.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlBottom.setLayout(new java.awt.BorderLayout());

        tblKhachHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã khách hàng", "Tên khách hàng", "CCCD", "Số điện thoại"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tblKhachHang.setRowHeight(40);
        tblKhachHang.setSelectionBackground(new java.awt.Color(239, 244, 255));
        tblKhachHang.setSelectionForeground(new java.awt.Color(51, 51, 51));
        tblKhachHang.setShowGrid(true);
        tblKhachHang.setShowVerticalLines(false);
        jScrollPane1.setViewportView(tblKhachHang);
        if (tblKhachHang.getColumnModel().getColumnCount() > 0) {
            tblKhachHang.getColumnModel().getColumn(0).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(0).setPreferredWidth(20);
            tblKhachHang.getColumnModel().getColumn(1).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(2).setPreferredWidth(250);
            tblKhachHang.getColumnModel().getColumn(3).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(3).setPreferredWidth(200);
            tblKhachHang.getColumnModel().getColumn(4).setResizable(false);
            tblKhachHang.getColumnModel().getColumn(4).setPreferredWidth(200);
        }

        pnlBottom.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents
    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Quản lý khách hàng");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return  pnlTitle;
    }
    
    private void loadData() {
        dsKhachHang = khachHang_Dao.getDSKhachHang();
        xoaDuLieu();
        taiLaiDuLieu(dsKhachHang);
    }
    
    
    public void xoaDuLieu(){
        DefaultTableModel df = (DefaultTableModel) tblKhachHang.getModel();
        df.setRowCount(0);
    }
    
    public void taiLaiDuLieu(List<KhachHang> dsKhachHang){
        for(KhachHang kh: dsKhachHang){
            tblKhachHang.addRow(new Object[] {JCheckBox.class,kh.getMaKhachHang(), kh.getTenKhachHang(), kh.getCanCuocCD(), kh.getSoDienThoai()});
        }
    }
    
    private void createTable() {
//        tblKhachHang.fixTable(scrKhachHang);
        tblKhachHang.getTableHeader().setFont(new Font("Sansserif", Font.BOLD, 14));
        loadData();
        
    }

    private boolean valiDataSDT(){
        int row = tblKhachHang.getSelectedRow();
        String sdt = tblKhachHang.getValueAt(row, 4).toString().trim();
        if (!(sdt.matches("^(09|03|07|08|05)([0-9]{8})"))) {
            JOptionPane.showMessageDialog(this,"Số điện thoại của khách hàng không hợp lệ");
            return false;
        }
        return true;
    }    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlTop;
    private gui.swing.table2.MyTableFlatlaf tblKhachHang;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if(obj.equals(btnLamMoi)){
           txtTimKiem.setText("");
           dsKhachHang = khachHang_Dao.getDSKhachHang();
           xoaDuLieu();
           taiLaiDuLieu(dsKhachHang);
        }
        if(obj.equals(btnSua)){
            KhachHang khachHang = new KhachHang();
            int row = tblKhachHang.getSelectedRow();
            if(row==-1){
                JOptionPane.showMessageDialog(GD_KhachHang.this, "Bạn chưa chọn đối tượng khách hàng muốn cập nhật.");
            }else{
                if(valiDataSDT()){
                    String soDienThoai = tblKhachHang.getValueAt(row, 4).toString();
                    String maKhachHang = tblKhachHang.getValueAt(row, 1).toString();
                    khachHang.setSoDienThoai(soDienThoai);
                    if (khachHang_Dao.capNhatKhachHang(maKhachHang, soDienThoai)) {
                        JOptionPane.showMessageDialog(GD_KhachHang.this, "Cập nhật số điện thoại khách hàng thành công.");
                        xoaDuLieu();
                        dsKhachHang = khachHang_Dao.getDSKhachHang();
                        taiLaiDuLieu(dsKhachHang);
                    }else{
                        JOptionPane.showMessageDialog(GD_KhachHang.this, "Cập nhật số điện thoại khách hàng không thành công");
                    }
                }  
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
    }

    @Override
    public void keyReleased(KeyEvent arg0) {
        Object obj = arg0.getSource();
        if(obj.equals(txtTimKiem)){
            List<KhachHang> dsKhachHang = khachHang_Dao.layDSKhachHang(txtTimKiem.getText().trim());
            xoaDuLieu();
            taiLaiDuLieu(dsKhachHang);
        }
    }

    
}
