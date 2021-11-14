package gui;

import dao.Phong_DAO;
import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import gui.swing.button.Button;
import gui.swing.table.TableCustom;

import gui.swing.table2.EventAction;
import gui.swing.table2.ModelAction;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;

public class GD_DanhSachPhong extends JPanel {

    private Phong_DAO phong_DAO;
    private EventAction eventAction;

    public GD_DanhSachPhong() {
        phong_DAO = new Phong_DAO();
        initComponents();
        buildGD();
        initData();
        table.fixTable(sp);
    }

    private void buildGD() {
        String fontName = "sansserif";
        int fontStyle = Font.PLAIN;
        int fontSize = 16;
        Color colorBtn = new Color(184, 238, 241);

        pnlTop.setLayout(new MigLayout("fill", "push[center]10[center]20[center]10[]push", "60[center]20[center]20[]push"));

        /**
         * Begin: group thông tin phòng
         */
        // Mã phòng
        JLabel lblTenPhong = new JLabel("Tên phòng:");
        lblTenPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        pnlTop.add(lblTenPhong);

        MyTextField txtMaPhong = new MyTextField();
        txtMaPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        txtMaPhong.setBorderLine(true);
        pnlTop.add(txtMaPhong, "w 20%");

        //Tên phòng
        JLabel lblLoaiPhong = new JLabel("Loại phòng");
        lblLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        pnlTop.add(lblLoaiPhong);

        MyComboBox<String> cmbLoaiPhong = new MyComboBox<>(new String[]{"--Tất cả--", "Phòng trống", "Phòng đang hát", "Phòng đặt trước"});
        cmbLoaiPhong.setFont(new Font("sansserif", Font.PLAIN, 12));
        cmbLoaiPhong.setBorderLine(true);
        cmbLoaiPhong.setBorderRadius(10);
        pnlTop.add(cmbLoaiPhong, "w 20%, h 30!");

        /**
         * end: group thông tin phòng hát
         */
        /*Begin: group danh sách Phòng hát*/
 /*End: group danh sách Phòng */
        pnlTop.add(createPanelTitle(), "pos 0al 0al 100% n, h 40!");

    }

    private void initData() {
        LoaiPhong loaiPhong = new LoaiPhong();
        loaiPhong.setTenLoaiPhong("Phòng thường");
        eventAction = new EventAction() {
            @Override
            public void delete(Object obj) {
                Phong phong = (Phong) obj;
                if (JOptionPane.showConfirmDialog(GD_DanhSachPhong.this, "Bạn có muốn xóa mã " + phong.getMaPhong(), "Delete", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    String s = phong_DAO.deletePhong(phong.getMaPhong()) == true ? "Xóa thành công mã " + phong.getMaPhong() : "Xóa thất bại mã " + phong.getMaPhong();
                    JOptionPane.showMessageDialog(GD_DanhSachPhong.this, s);
                    DefaultTableModel df = (DefaultTableModel) table.getModel();
                    df.getDataVector().removeAllElements();
                    table.clearSelection();
                    loadData();
                }
            }

            @Override
            public void update(ModelAction action) {
                Phong phong = (Phong) action.getObj();
                if (JOptionPane.showConfirmDialog(GD_DanhSachPhong.this, "Bạn có muốn cập nhật không?", "Cập nhật", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                    phong.setTenPhong(table.getValueAt(table.getSelectedRow(), 2).toString());
                    String s = phong_DAO.updatePhong(phong) == true ? "Cập nhật thành công" : "Cập nhật thất bại";
                    JOptionPane.showMessageDialog(GD_DanhSachPhong.this, s);
                    DefaultTableModel df = (DefaultTableModel) table.getModel();
                    df.getDataVector().removeAllElements();
                    table.clearSelection();
                    loadData();
                }
            }
        };
        loadData();
    }

    private void loadData() {
        List<Phong> dsPhong = phong_DAO.getDsPhong();
        dsPhong.forEach((phong) -> {
            table.addRow(phong.convertToRowTable(eventAction));
        });
    }

    private JPanel createPanelTitle() {
        JPanel pnlTitle = new JPanel();
        pnlTitle.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, new Color(0, 0, 0, 0.1f)));
        pnlTitle.setOpaque(false);
        pnlTitle.setLayout(new MigLayout("fill", "", ""));
        JLabel lblTitle = new JLabel();
        lblTitle.setText("Danh sách phòng hát");
        lblTitle.setFont(new Font("sansserif", Font.PLAIN, 16));
        lblTitle.setForeground(new Color(68, 68, 68));
        pnlTitle.add(lblTitle);
        return pnlTitle;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new gui.swing.panel.PanelShadow();
        pnlBottom = new gui.swing.panel.PanelShadow();
        sp = new javax.swing.JScrollPane();
        table = new gui.swing.table2.MyTable();
        jLabel1 = new javax.swing.JLabel();

        setOpaque(false);

        pnlTop.setBackground(new java.awt.Color(255, 255, 255));
        pnlTop.setShadowOpacity(0.3F);
        pnlTop.setShadowSize(2);
        pnlTop.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlTopLayout = new javax.swing.GroupLayout(pnlTop);
        pnlTop.setLayout(pnlTopLayout);
        pnlTopLayout.setHorizontalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        pnlTopLayout.setVerticalGroup(
            pnlTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        pnlBottom.setBackground(new java.awt.Color(255, 255, 255));
        pnlBottom.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 5, 5, 5));
        pnlBottom.setShadowOpacity(0.3F);
        pnlBottom.setShadowSize(2);
        pnlBottom.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlBottom.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Mã phòng", "Tên phòng", "Tầng", "Trạng thái", "Giá phòng", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        sp.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
            table.getColumnModel().getColumn(4).setResizable(false);
            table.getColumnModel().getColumn(5).setResizable(false);
            table.getColumnModel().getColumn(6).setResizable(false);
        }

        pnlBottom.add(sp, java.awt.BorderLayout.CENTER);

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(4, 72, 210));
        jLabel1.setText("Danh sách phòng");
        pnlBottom.add(jLabel1, java.awt.BorderLayout.PAGE_START);

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
                .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, 461, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private gui.swing.panel.PanelShadow pnlBottom;
    private gui.swing.panel.PanelShadow pnlTop;
    private javax.swing.JScrollPane sp;
    private gui.swing.table2.MyTable table;
    // End of variables declaration//GEN-END:variables
}
