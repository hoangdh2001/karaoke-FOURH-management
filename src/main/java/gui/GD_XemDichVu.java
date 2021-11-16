/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package gui;

import dao.NhaCungCapVaNhapHang_DAO;
import entity.MatHang;
import gui.swing.button.Button;
import gui.swing.panel.PanelShadow;
import gui.swing.table2.MyTable;
import gui.swing.textfield.MyComboBox;
import gui.swing.textfield.MyTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static java.awt.image.ImageObserver.WIDTH;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.miginfocom.swing.MigLayout;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author 84975
 */
public class GD_XemDichVu extends javax.swing.JPanel {
    
    private MyTable table;
    
    private MyTextField txtNhap;
    
    private MyComboBox<String> cmbLoaiTimKiem;
    
    private Button btnXuatFile;
    
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhapHang_DAO;
    
    private List<MatHang> listMatHang;
    
    private String fontName = "sansserif";
    private int fontPlain = Font.PLAIN;
    private int font16 = 16;
    private int font14 = 14;
    private int font12 = 12;
    private Color colorBtn = new Color(184, 238, 241);
    private Color colorLabel = new Color(47, 72, 210);
    
    /**
     * Creates new form GD_XemDichVu
     */
    public GD_XemDichVu() {
        initComponents();
        initSearch();
        initTable();
        initData();
        addAction();
    }
    
    public void initSearch(){
     
        pnlSearch.setLayout(new MigLayout("","20[]5[][]push[]20","20[]20"));
        pnlSearch.setBackground(Color.WHITE);
        JLabel lblTimKiem = new JLabel("Tìm Kiếm :");
        lblTimKiem.setFont(new Font(fontName, fontPlain, font14));
        
        txtNhap = new MyTextField();
        txtNhap.setFont(new Font(fontName, fontPlain, font14));
        txtNhap.setBorderLine(true);
        txtNhap.setBorderRadius(5);
        
        cmbLoaiTimKiem = new MyComboBox<>(new String[] {"--Tất cả--"});
        cmbLoaiTimKiem.setFont(new Font("sansserif", Font.PLAIN, 12));
        cmbLoaiTimKiem.setBorderLine(true);
        cmbLoaiTimKiem.setBorderRadius(10);
//        panelForm.add(cbLoaiPhong, "w 20%, h 30!");

        btnXuatFile = new Button("Xuất file");
        btnXuatFile.setFont(new Font(fontName, fontPlain, font14));
        btnXuatFile.setBackground(colorBtn);
        btnXuatFile.setBorderRadius(5);
        
        pnlSearch.add(lblTimKiem);
        pnlSearch.add(txtNhap,"w 100:300:500, h 36!");
        pnlSearch.add(cmbLoaiTimKiem,"w 200,h 36!");
        pnlSearch.add(btnXuatFile,"w 150,h 36!");
        
        this.add(pnlSearch,"w 100%,wrap");
    }
    
    public void initTable(){
        lbldsDichVu.setLayout(new MigLayout("","15[]15","20[][]20"));
        lbldsDichVu.setBackground(Color.WHITE);
        
        JLabel lblTTSanPham = new JLabel("Thông tin sản phẩm");
        lblTTSanPham.setFont(new Font(fontName, fontPlain, font16));
        lblTTSanPham.setForeground(colorLabel);
        lbldsDichVu.add(lblTTSanPham, "span, w 100%, h 30!, wrap");
        
        JPanel pnlTable = new PanelShadow();
        pnlTable.setLayout(new MigLayout("fill"));
        pnlTable.setBackground(Color.WHITE);
        String col[] = {"Tên sản phẩm","loại sản phẩm","Số lượng","Giá bán"};
        
        DefaultTableModel model = new DefaultTableModel(
            col,
            0
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false,false
            }; 
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        table = new MyTable(){
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return String.class;
				default:
					return String.class;
				}
			}
        };
    
        table.setModel(model);
        
        JScrollPane sp = new JScrollPane(table);
        table.fixTable(sp);
        
        pnlTable.add(sp,"w 100%,h 100%");
        lbldsDichVu.add(pnlTable,"w 100%,h 100%");
    }
    
    public void addAction(){
        btnXuatFile.addActionListener(new createActionListener());
        txtNhap.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                String text = txtNhap.getText().trim();
                int cmbSelected = cmbLoaiTimKiem.getSelectedIndex();
                if(!text.equals("")){
                    listMatHang = nhaCungCapVaNhapHang_DAO.findMatHang(txtNhap.getText().trim(), cmbSelected);
                    addDataToTable();
                }else{
                    listMatHang = nhaCungCapVaNhapHang_DAO.getDanhSachMatHang();
                    addDataToTable();
                }
            }
        });
        cmbLoaiTimKiem.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            String text = txtNhap.getText().trim();
                int cmbSelected = cmbLoaiTimKiem.getSelectedIndex();
                if(!text.equals("")){
                    listMatHang = nhaCungCapVaNhapHang_DAO.findMatHang(txtNhap.getText().trim(), cmbSelected);
                    addDataToTable();
                }else{
                    listMatHang = nhaCungCapVaNhapHang_DAO.getDanhSachMatHang();
                    addDataToTable();
                }
            }
        });
    }
    
    public void initData(){
        cmbLoaiTimKiem.addItem("Sản phẩm");
        cmbLoaiTimKiem.addItem("Loại sản phẩm");
        
        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
        listMatHang = nhaCungCapVaNhapHang_DAO.getDanhSachMatHang();
        addDataToTable();
    }
    
    public void addDataToTable(){
        DefaultTableModel df =(DefaultTableModel) table.getModel();
        df.setRowCount(0);
        listMatHang.forEach(doc -> {
            table.addRow(doc.convertToRowTableInGDXemDichVu());
        });
    }
    
    public void exportTableToExcel() throws FileNotFoundException{
        
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
                for(int headings = 0; headings < model.getColumnCount(); headings++){ 
                    headerRow.createCell(headings).setCellValue(model.getColumnName(headings));
                }

                for(int rows = 0; rows < model.getRowCount(); rows++){ 
                    for(int cols = 0; cols < table.getColumnCount(); cols++){ 
                        row.createCell(cols).setCellValue(model.getValueAt(rows, cols).toString()); 
                    }

                    row = sheet.createRow((rows + 3)); 
                }
                wb.write(new FileOutputStream(excelFileChooser.getSelectedFile()+ ".xlsx")); 
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
    
    private class createActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            Object obj = e.getSource();
            if(obj.equals(btnXuatFile)){
                try {
                    exportTableToExcel();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(GD_XemDichVu.class.getName()).log(Level.SEVERE, null, ex);
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
        lbldsDichVu = new gui.swing.panel.PanelShadow();

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

        javax.swing.GroupLayout lbldsDichVuLayout = new javax.swing.GroupLayout(lbldsDichVu);
        lbldsDichVu.setLayout(lbldsDichVuLayout);
        lbldsDichVuLayout.setHorizontalGroup(
            lbldsDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        lbldsDichVuLayout.setVerticalGroup(
            lbldsDichVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 325, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lbldsDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlSearch, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbldsDichVu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private gui.swing.panel.PanelShadow lbldsDichVu;
    private gui.swing.panel.PanelShadow pnlSearch;
    // End of variables declaration//GEN-END:variables
}
