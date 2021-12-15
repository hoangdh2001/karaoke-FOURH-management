/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.MatHang_DAO;
import dao.NhaCungCap_DAO;
import entity.MatHang;
import gui.swing.button.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.MatHangService;

/**
 *
 * @author 84975
 */
public class GD_XemMatHang extends javax.swing.JPanel {

    private JTextField txtNhap;

    private JComboBox<String> cmbLoaiTimKiem;

    private Button btnXuatFile;

    private NhaCungCap_DAO nhaCungCapVaNhapHang_DAO;
    private MatHangService matHangDao;
    private List<MatHang> listMatHang;

    private String fontName = "sansserif";
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    private int font12 = 12;
    private Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);
    private final DecimalFormat df = new DecimalFormat("#,##0.00");

    /**
     * Creates new form GD_XemDichVu
     */
    public GD_XemMatHang() {
        initComponents();
        initSearch();
        initTable();
        initData();
        addAction();
    }

    public void initSearch() {

        pnlSearch.setLayout(new MigLayout("fillx, insets 0", "20[]5[][]push[]20", "20[]20"));
        JLabel lblTimKiem = new JLabel("Tìm Kiếm :");
        lblTimKiem.setFont(new Font(fontName, fontPlain, font14));

        txtNhap = new JTextField();
        txtNhap.setFont(new Font(fontName, fontPlain, font14));

        cmbLoaiTimKiem = new JComboBox<>(new String[]{"--Tất cả--"});
        cmbLoaiTimKiem.setFont(new Font("sansserif", Font.PLAIN, 12));

        btnXuatFile = new Button("Xuất file");
        btnXuatFile.setFont(new Font("sansserif", Font.BOLD, 12));
        btnXuatFile.setForeground(Color.WHITE);
        btnXuatFile.setBackground(new Color(54, 88, 153));
        btnXuatFile.setBorderRadius(5);

        pnlSearch.add(lblTimKiem);
        pnlSearch.add(txtNhap, "w 100:300:500, h 30!");
        pnlSearch.add(cmbLoaiTimKiem, "w 200,h 30!");
        pnlSearch.add(btnXuatFile, "w 90! ,h 30!");

        this.add(pnlSearch, "wrap");
    }

    public void initTable() {
        table.getTableHeader().setFont(new Font("sansserif", Font.BOLD, 14));
    }

    public void addAction() {
        btnXuatFile.addActionListener(new createActionListener());
        txtNhap.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtNhap.getText().trim();
                int cmbSelected = cmbLoaiTimKiem.getSelectedIndex();
                if (!text.equals("")) {
                    listMatHang = matHangDao.findMatHang(txtNhap.getText().trim(), cmbSelected);
                    addDataToTable();
                } else {
                    listMatHang = matHangDao.getDsMatHang();
                    addDataToTable();
                }
            }
        });
        cmbLoaiTimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String text = txtNhap.getText().trim();
                int cmbSelected = cmbLoaiTimKiem.getSelectedIndex();
                if (!text.equals("")) {
                    listMatHang = matHangDao.findMatHang(txtNhap.getText().trim(), cmbSelected);
                    addDataToTable();
                } else {
                    listMatHang = matHangDao.getDsMatHang();
                    addDataToTable();
                }
            }
        });
    }

    public void initData() {
        cmbLoaiTimKiem.addItem("Sản phẩm");
        cmbLoaiTimKiem.addItem("Loại sản phẩm");
        matHangDao = new MatHang_DAO();
        nhaCungCapVaNhapHang_DAO = new NhaCungCap_DAO();
        listMatHang = matHangDao.getDsMatHang();
        addDataToTable();
    }

    public void addDataToTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (MatHang matHang : listMatHang) {
            table.addRow(new Object[]{matHang.getTenMatHang(),
                matHang.getLoaiDichVu().getTenLoaiDichVu(),
                matHang.getsLTonKho(),
                df.format(matHang.getDonGia())});
        }
    }

    public void exportTableToExcel() throws FileNotFoundException {

        FileOutputStream excelFos = null;
        Workbook excelJTableExport = null;
        try {

            JFileChooser excelFileChooser = new JFileChooser("D:\\");
            excelFileChooser.setDialogTitle("Save As ..");
            FileNameExtensionFilter fnef = new FileNameExtensionFilter("Files", "xls", "xlsx", "xlsm");
            excelFileChooser.setFileFilter(fnef);
            int chooser = excelFileChooser.showSaveDialog(null);

            Workbook wb = new XSSFWorkbook(); //Excell workbook
            Sheet sheet = wb.createSheet(); //WorkSheet
            Row row = sheet.createRow(2); //Row created at line 3
            TableModel model = table.getModel(); //Table model

            if (chooser == JFileChooser.APPROVE_OPTION) {
                Row headerRow = sheet.createRow(0);
                for (int headings = 0; headings < model.getColumnCount(); headings++) {
                    headerRow.createCell(headings).setCellValue(model.getColumnName(headings));
                }

                for (int rows = 0; rows < model.getRowCount(); rows++) {
                    for (int cols = 0; cols < table.getColumnCount(); cols++) {
                        row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString());
                    }

                    row = sheet.createRow((rows + 3));
                }
                wb.write(new FileOutputStream(excelFileChooser.getSelectedFile() + ".xlsx"));
            }

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        } finally {
            try {
                if (excelFos != null) {
                    excelFos.close();
                }
                if (excelJTableExport != null) {
                    excelJTableExport.close();
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    private class createActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if (obj.equals(btnXuatFile)) {
                try {
                    exportTableToExcel();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GD_XemMatHang.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlSearch = new gui.swing.panel.PanelShadow();
        pnlCenter = new gui.swing.panel.PanelShadow();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new gui.swing.table.MyTableFlatlaf();

        pnlSearch.setBackground(new java.awt.Color(255, 255, 255));
        pnlSearch.setShadowOpacity(0.3F);
        pnlSearch.setShadowSize(2);
        pnlSearch.setShadowType(gui.swing.graphics.ShadowType.TOP);

        javax.swing.GroupLayout pnlSearchLayout = new javax.swing.GroupLayout(pnlSearch);
        pnlSearch.setLayout(pnlSearchLayout);
        pnlSearchLayout.setHorizontalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        pnlSearchLayout.setVerticalGroup(
            pnlSearchLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 86, Short.MAX_VALUE)
        );

        pnlCenter.setBackground(new java.awt.Color(255, 255, 255));
        pnlCenter.setBorder(javax.swing.BorderFactory.createEmptyBorder(5, 5, 5, 5));
        pnlCenter.setShadowOpacity(0.3F);
        pnlCenter.setShadowSize(2);
        pnlCenter.setShadowType(gui.swing.graphics.ShadowType.TOP);
        pnlCenter.setLayout(new java.awt.BorderLayout());

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tên mặt hàng", "Loại mặt hàng", "Số lượng", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        table.setRowHeight(40);
        table.setShowGrid(true);
        table.setShowVerticalLines(false);
        jScrollPane1.setViewportView(table);
        if (table.getColumnModel().getColumnCount() > 0) {
            table.getColumnModel().getColumn(0).setResizable(false);
            table.getColumnModel().getColumn(1).setResizable(false);
            table.getColumnModel().getColumn(2).setResizable(false);
            table.getColumnModel().getColumn(3).setResizable(false);
        }

        pnlCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 811, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlCenter, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private gui.swing.panel.PanelShadow pnlCenter;
    private gui.swing.panel.PanelShadow pnlSearch;
    private gui.swing.table.MyTableFlatlaf table;
    // End of variables declaration//GEN-END:variables
}
