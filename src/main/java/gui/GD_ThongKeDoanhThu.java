/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.swing.button.Button;
import gui.swing.table.TableCustom;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hao
 */
public class GD_ThongKeDoanhThu extends javax.swing.JPanel {

    /**
     * Creates new form GD_ThongKeDoanhThu
     */
    public GD_ThongKeDoanhThu() {
        initComponents();
        buildGD();
    }
    
    public void buildGD(){
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setPreferredSize(new Dimension(1119, 800));
        pnlTop.setLayout(new MigLayout());
        
        JPanel pnlThongKeTheo = new JPanel();
        pnlThongKeTheo.setOpaque(false);
        pnlThongKeTheo.setLayout(new MigLayout("", "10[center]10[center] 20 [center]10[center] 250 [center]10[center]10", "push [center] push"));
        pnlTop.add(pnlThongKeTheo, "w 100%, h 15%, wrap");
        
         //Loại thống kê
        JLabel lblLoaiTK = new JLabel("Loại thống kê:");
        lblLoaiTK.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(lblLoaiTK,"align right");
        
        
        JComboBox<String> cmbLoaiTK = new JComboBox<>();
        cmbLoaiTK.setFont(new Font(fontName, fontStyle, fontSize));
        cmbLoaiTK.addItem("Doanh thu");
        pnlThongKeTheo.add(cmbLoaiTK, " w 60%, h 36!");
       
        
        //Thống kê theo
        JLabel lblTKTheo = new JLabel("Thống kê theo:");
        lblTKTheo.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(lblTKTheo,"align right");
        
        
        JComboBox<String> cmbTKTheo = new JComboBox<>();
        cmbTKTheo.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTKTheo.addItem("Ngày");
        pnlThongKeTheo.add(cmbTKTheo, "w 60%, h 36!");
        
        
        //Khác
        JLabel lblKhac = new JLabel("Khác:");
        lblKhac.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongKeTheo.add(lblKhac,"align right");
        
        
        JComboBox<String> cmbKhac = new JComboBox<>();
        cmbKhac.setFont(new Font(fontName, fontStyle, fontSize));
        cmbKhac.addItem("Dịch vụ tiêu thụ nhiều");
        pnlThongKeTheo.add(cmbKhac, ",w 60%, h 36!");
       
        
        JPanel pnlBangThongKe = new JPanel();
        pnlBangThongKe.setOpaque(false);
        pnlBangThongKe.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Bảng thống kê", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlBangThongKe.setLayout(new MigLayout());
        pnlTop.add(pnlBangThongKe, "w 100%, h 75%, wrap");
        
        Object data[][]={
                { "HD0001", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0002", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0004", "30-09-2019", "Tên Khách Hàng","Bùi Quang Hữu", "560,000" },
                { "HD0005", "09-06-2018", "Tên Khách Hàng","Nguyễn Hưng", "570,000" },
                { "HD0006", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0007", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0008", "30-09-2019", "Tên Khách Hàng","Bùi Quang Hữu", "560,000" },
                { "HD0009", "09-06-2018", "Tên Khách Hàng","Nguyễn Hưng", "570,000" },
                { "HD0010", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0011", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0012", "30-09-2019", "Tên Khách Hàng","Bùi Quang Hữu", "560,000" },
                { "HD0013", "09-06-2018", "Tên Khách Hàng","Nguyễn Hưng", "570,000" },
                { "HD0014", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0015", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0016", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0017", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0018", "30-09-2019", "Tên Khách Hàng","Bùi Quang Hữu", "560,000" },
                { "HD0019", "09-06-2018", "Tên Khách Hàng","Nguyễn Hưng", "570,000" },
                { "HD0020", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0021", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0022", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0023", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0024", "30-09-2019", "Tên Khách Hàng","Bùi Quang Hữu", "560,000" },
                { "HD0025", "09-06-2018", "Tên Khách Hàng","Nguyễn Hưng", "570,000" },
                { "HD0026", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0027", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0028", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0029", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0030", "30-09-2019", "Tên Khách Hàng","Bùi Quang Hữu", "560,000" },
                { "HD0031", "09-06-2018", "Tên Khách Hàng","Nguyễn Hưng", "570,000" },
                { "HD0032", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0033", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0034", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0035", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0036", "30-09-2019", "Tên Khách Hàng","Bùi Quang Hữu", "560,000" },
                { "HD0037", "09-06-2018", "Tên Khách Hàng","Nguyễn Hưng", "570,000" },
                { "HD0038", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0039", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0040", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0041", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" },
                { "HD0042", "30-09-2019", "Tên Khách Hàng","Bùi Quang Hữu", "560,000" },
                { "HD0043", "09-06-2018", "Tên Khách Hàng","Nguyễn Hưng", "570,000" },
                { "HD0044", "13-06-2018", "Nguyễn Thị Lan","Nguyễn Thị Hảo", "500,000" },
                { "HD0045", "27-03-2020", "Hoàng Văn Nam","Đỗ Huy Hoàng", "400,000" }
                
        };
        
         String[] tieuDeThongKe = { "Mã Hóa Đơn", "Ngày Lập Hóa Đơn", "Tên Khách Hàng","Nhân Viên", "Tổng Tiền" };
         DefaultTableModel modelThongKe = new DefaultTableModel(tieuDeThongKe, 0);
        modelThongKe.setDataVector(data, tieuDeThongKe);
        TableCustom tblThongKe = new TableCustom(modelThongKe);
        JScrollPane scrThongKe = new JScrollPane(tblThongKe);
        tblThongKe.fixTable(scrThongKe);
        pnlBangThongKe.add(scrThongKe,"w 100%, h 100%");
        
        JPanel pnlTinhTong = new JPanel();
        pnlTinhTong.setOpaque(false);
        pnlTinhTong.setLayout(new MigLayout());//"", "50%[center]10[center]", " [center] "
        pnlTop.add(pnlTinhTong, "align right,w 25%, h 10%");
        
        //Tính tổng
        JLabel lblTong = new JLabel("Tổng:");
        lblTong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTinhTong.add(lblTong, "align right");

        JTextField txtTong = new MyTextField();
        txtTong.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTinhTong.add(txtTong, "w 80%, h 36!");
        
        pnlBottom.setPreferredSize(new Dimension(1119, 100));
        pnlBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Xuất danh sách", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlBottom.setLayout(new MigLayout("", "10[center] 50 [center] 20 [center]10", " [center] "));
        
        JTextField txtDuongDan = new MyTextField();
        txtDuongDan.setFont(new Font(fontName, fontStyle, fontSize));
        pnlBottom.add(txtDuongDan, "w 80%, h 36!");
        
        //Chọn đường dẫn
        Button btnChonDD = new Button("Chọn đường dẫn");
        btnChonDD.setFont(new Font(fontName, fontStyle, fontSize));
        btnChonDD.setBackground(colorBtn);
        pnlBottom.add(btnChonDD, "w 200!, h 36!");

        // Xuất danh sách
        Button btnXuat = new Button("Xuất danh sách");
        btnXuat.setFont(new Font(fontName, fontStyle, fontSize));
        btnXuat.setBackground(colorBtn);
        pnlBottom.add(btnXuat, "w 200!, h 36!");
        
        setPreferredSize(new Dimension(1119, 900));

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.panel.PanelShadow();
        lblTitle = new javax.swing.JLabel();
        pnlBottom = new gui.panel.PanelShadow();

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);
        pnlTop.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1075, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 526, Short.MAX_VALUE)
        );

        lblTitle.setBackground(new java.awt.Color(255, 255, 255));
        lblTitle.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(4, 72, 210));
        lblTitle.setText("Thống Kê Theo Danh Sách");

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(3);
        pnlBottom.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 119, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(lblTitle)
                .addGap(11, 11, 11)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private gui.panel.PanelShadow pnlBottom;
    private gui.panel.PanelShadow pnlTop;
    // End of variables declaration//GEN-END:variables
}
