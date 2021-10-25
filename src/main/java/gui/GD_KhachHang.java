/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import gui.swing.button.Button;
import gui.swing.table.AbstractTableModel;
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
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Hao
 */
public class GD_KhachHang extends javax.swing.JPanel {

    private DefaultTableModel modelKhanhHang;

    /**
     * Creates new form GD_KhachHang
     */
    public GD_KhachHang() {
        initComponents();
        buildGD();
    }

     private void buildGD(){
        
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);
        
        pnlTop.setLayout(new MigLayout("", "3[center] 20 [center]3", "6[center]5"));
        pnlTop.setPreferredSize(new Dimension(1119,250 ));
        
       /**
        * Begin: group Thông tin khách hàng
        */        
        JPanel pnlThongTinKH = new JPanel();
        pnlThongTinKH.setOpaque(false);
        pnlThongTinKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Thông tin khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlThongTinKH.setLayout(new MigLayout("", "10[center]10[center] 10 [center][center]10", "[center]10[center]10[center]15[center]"));
        pnlTop.add(pnlThongTinKH, "w 65%, h 240!");
        
        //Mã khách hàng
        JLabel lblMaKH = new JLabel("Mã khách hàng:");
        lblMaKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblMaKH, "align right");
        
        JTextField txtMaKH = new MyTextField();
        txtMaKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtMaKH, "w 80%, h 36!");
        
        //Tên khách hàng
        JLabel lblTenKH = new JLabel("Tên khách hàng:");
        lblTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblTenKH, "align right");
        
        JTextField txtTenKH = new MyTextField();
        txtTenKH.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtTenKH, "w 80%, h 36!, wrap");
        
        //Căn cước công dân của khách hàng
        JLabel lblCCCD = new JLabel("CCCD:");
        lblCCCD.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblCCCD, "align right");
        
        JTextField txtCCCD = new MyTextField();
        txtCCCD.setFont(new Font("sansserif", Font.PLAIN, 12));
        pnlThongTinKH.add(txtCCCD, "w 80%, h 36!");
        
        //Số điện thoại của khách hàng
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblSDT, "align right");
        
        JTextField txtSDT = new MyTextField();
        txtSDT.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtSDT, "w 80%, h 36!, wrap");
        
        //Địa chỉ của khách hàng
        JLabel lblDiaChi = new JLabel("Địa chỉ:");
        lblDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(lblDiaChi, "align right");
        
        JTextField txtDiaChi = new MyTextField();
        txtDiaChi.setFont(new Font(fontName, fontStyle, fontSize));
        pnlThongTinKH.add(txtDiaChi, "w 80%, h 36!, wrap");
        
        //Panel nút chức năng
        JPanel pnlButton = new JPanel();
        pnlButton.setOpaque(false);
        pnlButton.setLayout(new MigLayout("", "push[]20[]20[]20[]0", "push[]push"));
        pnlThongTinKH.add(pnlButton, "span , w 100%, h 36!");

        // Nút Xóa
        Button btnXoaKH = new Button("Xóa");
        btnXoaKH.setFont(new Font(fontName, fontStyle, fontSize));
        btnXoaKH.setBackground(colorBtn);
        pnlButton.add(btnXoaKH, "w 100!, h 36!,growx");

        // Nút Sửa
        Button btnSuaKH = new Button("Sửa");
        btnSuaKH.setFont(new Font(fontName, fontStyle, fontSize));
        btnSuaKH.setBackground(colorBtn);
        pnlButton.add(btnSuaKH, "w 100!, h 36!");

        // Nút Làm mới
        Button btnLamMoi = new Button("Làm mới");
        btnLamMoi.setFont(new Font(fontName, fontStyle, fontSize));
        btnLamMoi.setBackground(colorBtn);
        pnlButton.add(btnLamMoi, "w 100!, h 36!");
        /**
         * End: group thông tin khách hàng
         */
        
        /**
         * Begin: Tìm kiếm khách hàng
         */
        JPanel pnlTimKiemKH = new JPanel();
        pnlTimKiemKH.setOpaque(false);
        pnlTimKiemKH.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY, 2), "Tìm kiếm", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlTimKiemKH.setLayout(new MigLayout("", "[center][center]", ""));//[]10[]10[]10[]10[]20[]
        pnlTop.add(pnlTimKiemKH, "w 35%, h 240!");
      
        /*Ô nhập thông tin tìm kiếm*/
        JTextField txtTimKiem = new MyTextField();
        txtTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        pnlTimKiemKH.add(txtTimKiem, "w 50%, h 36!");
       
        /*Chọn thông thêm tiêu chí để lọc thông tin*/
        JComboBox<String> cmbTimKiem = new JComboBox<>();
        cmbTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        cmbTimKiem.addItem("--Chọn--");
        pnlTimKiemKH.add(cmbTimKiem, "w 40%,h 36!, wrap");
        
        /*Click vào button để tìm kiếm*/
        Button btnTimKiem = new Button("Tìm kiếm");
        btnTimKiem.setFont(new Font(fontName, fontStyle, fontSize));
        btnTimKiem.setBackground(colorBtn);
        pnlTimKiemKH.add(btnTimKiem,"pos 0.9al 0.75al n n,w 100!, h 36!");//pos 0.9al 0.75al n n
        
        
        
        pnlBottom.setLayout(new MigLayout("","[center]",""));
        pnlBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.gray, 2), "Danh sách Khách hàng", TitledBorder.LEFT, TitledBorder.TOP, new Font("sansserif", Font.PLAIN, 16), Color.gray));
        pnlBottom.setPreferredSize(new Dimension(1119, 1000));
        
        
        Object data[][]={
            { "KH0001", "Nguyễn Thị Lan", "Nữ","077025222155", "0585534785", "Vũng Tàu" },
            { "KH0002", "Hoàng Văn Nam", "Nam","077256461235", "0358188554", "Kiên Giang" },
            { "KH0003", "Nguyễn Thị Lan", "Nữ","077795222555", "0585487785", "Vũng Tàu" },
            { "KH0004", "Hoàng Văn Nam", "Nam","077256255235", "0350152224", "Kiên Giang" },
            { "KH0005", "Nguyễn Thị Lan", "Nữ","077250581152", "0501155530", "Vũng Tàu" },
            { "KH0006", "Hoàng Văn Nam", "Nam","077255885555", "0758152954", "Kiên Giang" },
            { "KH0007", "Nguyễn Thị Lan", "Nữ","077252441533", "0356221225", "Vũng Tàu" },
            { "KH0008", "Hoàng Văn Nam", "Nam","077253356815", "0350884664", "Kiên Giang" },
            { "KH0009", "Nguyễn Thị Lan", "Nữ","077255698711", "0580552385", "Vũng Tàu" },
            { "KH0010", "Hoàng Văn Nam", "Nam","077255563489", "0350552254", "Kiên Giang" },
            { "KH0011", "Nguyễn Thị Lan", "Nữ","077257896574", "0580554385", "Vũng Tàu" },
            { "KH0012", "Hoàng Văn Nam", "Nam","077254563879", "0342222254", "Kiên Giang" },
            { "KH0013", "Nguyễn Thị Lan", "Nữ","077251245698", "0505552225", "Vũng Tàu" },
            { "KH0014", "Hoàng Văn Nam", "Nam","077250124365", "0351155954", "Kiên Giang" },
            { "KH0015", "Nguyễn Thị Lan", "Nữ","077250245596", "0753222225", "Vũng Tàu" },
            { "KH0016", "Hoàng Văn Nam", "Nam","077252455972", "0248522254", "Kiên Giang" },
            { "KH0017", "Nguyễn Thị Lan", "Nữ","077253459871", "093526+785", "Vũng Tàu" },
            { "KH0018", "Hoàng Văn Nam", "Nam","077251245963", "0759635954", "Kiên Giang" },
            { "KH0019", "Nguyễn Thị Lan", "Nữ","077257485563", "0854233285", "Vũng Tàu" },
            { "KH0020", "Hoàng Văn Nam", "Nam","077251254623", "0963321454", "Kiên Giang" },
            { "KH0021", "Nguyễn Thị Lan", "Nữ","077250124862", "0236854565", "Vũng Tàu" },
            { "KH0022", "Hoàng Văn Nam", "Nam","077252451632", "0835456544", "Kiên Giang" },
            { "KH0023", "Nguyễn Thị Lan", "Nữ","077251235489", "0756226335", "Vũng Tàu" },
            { "KH0024", "Hoàng Văn Nam", "Nam","077253254896", "0966882664", "Kiên Giang" },
            { "KH0025", "Nguyễn Thị Lan", "Nữ","077256551583", "0684765585", "Vũng Tàu" },
            { "KH0026", "Hoàng Văn Nam", "Nam","077250442667", "0255841254", "Kiên Giang" },
            { "KH0027", "Nguyễn Thị Lan", "Nữ","077250321558", "0965211785", "Vũng Tàu" },
            { "KH0028", "Hoàng Văn Nam", "Nam","077250124653", "0325778544", "Kiên Giang" },
            { "KH0029", "Nguyễn Thị Lan", "Nữ","077253551879", "0965422385", "Vũng Tàu" },
            { "KH0030", "Hoàng Văn Nam", "Nam","077252566853", "0856412254", "Kiên Giang" },
            { "KH0031", "Nguyễn Thị Lan", "Nữ","077252564135", "0835512995", "Vũng Tàu" },
            { "KH0032", "Hoàng Văn Nam", "Nam","077256462158", "0925411954", "Kiên Giang" },
            { "KH0033", "Nguyễn Thị Lan", "Nữ","077257894352", "0824562235", "Vũng Tàu" },
            { "KH0034", "Hoàng Văn Nam", "Nam","077259634587", "0966545524", "Kiên Giang" },
            { "KH0035", "Nguyễn Thị Lan", "Nữ","077253654289", "0384415255", "Vũng Tàu" },
            { "KH0036", "Hoàng Văn Nam", "Nam","077256541287", "0584662555", "Kiên Giang" },
            { "KH0037", "Nguyễn Thị Lan", "Nữ","077252564891", "0365145222", "Vũng Tàu" },
            { "KH0038", "Hoàng Văn Nam", "Nam","077253257891", "0258551254", "Kiên Giang" },
            { "KH0039", "Nguyễn Thị Lan", "Nữ","077253698521", "0254455222", "Vũng Tàu" },
            { "KH0040", "Hoàng Văn Nam", "Nam","077252478951", "0395452524", "Kiên Giang" },
            { "KH0041", "Nguyễn Thị Lan", "Nữ","077253546912", "0695552785", "Vũng Tàu" },
            { "KH0045", "Hoàng Văn Nam", "Nam","077258123765", "0358455954", "Kiên Giang" },
            { "KH0046", "Nguyễn Thị Lan", "Nữ","077259546327", "0856552285", "Vũng Tàu" },
            { "KH0047", "Hoàng Văn Nam", "Nam","077256541237", "0954552254", "Kiên Giang" }
            
            
        };
        String[] tieuDeKhachHang = { "Mã Khách Hàng", "Tên Khách Hàng", "Giới Tính","Căn Cước Công Dân", "Số Điện Thoại", "Địa Chỉ" };
        modelKhanhHang = new DefaultTableModel(tieuDeKhachHang, 0);
        modelKhanhHang.setDataVector(data, tieuDeKhachHang);
        TableCustom tblKhachHang = new TableCustom(modelKhanhHang);
        JScrollPane scrKhachHang = new JScrollPane(tblKhachHang);
        tblKhachHang.fixTable(scrKhachHang);
        pnlBottom.add(scrKhachHang,"w 100%, h 100%");
        
        setPreferredSize(new Dimension(getWidth(), 1300));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitle = new javax.swing.JLabel();
        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();

        lblTitle.setFont(new java.awt.Font("SansSerif", 0, 16)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(4, 72, 210));
        lblTitle.setText("Quản Lý Khách Hàng");

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(3);
        pnlTop.setShadowType(gui.dropshadow.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 212, Short.MAX_VALUE)
        );

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
            .addGap(0, 356, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lblTitle)
                .addGap(0, 956, Short.MAX_VALUE))
            .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblTitle;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlTop;
    // End of variables declaration//GEN-END:variables
}
