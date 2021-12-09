package gui.component;

import dao.MatHang_DAO;
import dao.NhaCungCapVaNhapHang_DAO;
import entity.MatHang;
import java.util.List;
import gui.swing.model.ModelObjectComboBox;
import service.MatHangService;

public class PanelTenSanPham extends javax.swing.JPanel {
    private MatHangService matHangDao ;
    /**
     * Creates new form PanelTenSanPham
     */
    public PanelTenSanPham() {
        initComponents();
        config();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtSPChuaCo = new javax.swing.JTextField();
        cbSPDaCo = new javax.swing.JComboBox<>();

        setLayout(new java.awt.CardLayout());
        add(txtSPChuaCo, "card3");

        add(cbSPDaCo, "card3");
    }// </editor-fold>//GEN-END:initComponents
    
    public void config(){ 
        this.setOpaque(false);
        cbSPDaCo.addItem("Chọn sản phẩm");

        matHangDao = new MatHang_DAO();
    }
    
    public void setTypeSP(boolean b){
        if(b){
            cbSPDaCo.setVisible(false);
            txtSPChuaCo.setVisible(true);
        }else{
            txtSPChuaCo.setVisible(false);
            cbSPDaCo.setVisible(true);
        }
    }
    
    public void setComboboxItem(String id){
        cbSPDaCo.removeAllItems();
        cbSPDaCo.addItem("Chọn sản phẩm");
        if(!id.equalsIgnoreCase("")){
            List<MatHang> listMH = matHangDao.getDanhSachMatHangByLoaiDichVu(id);
            for (int i = 0; i < listMH.size(); i++) {
                MatHang dv = listMH.get(i);
                cbSPDaCo.addItem(new ModelObjectComboBox(dv.getTenMatHang(),dv.getMaMatHang()));  
            } 
        } 
    }
    
    public String getTenSanPhamMoi(){
        return txtSPChuaCo.getText();
    }
    
    public int getSelectedIndex(){
        return cbSPDaCo.getSelectedIndex();
    }
    public String getTenSanPhamCu(){
        return cbSPDaCo.getSelectedItem().toString();
    }
    public String getMaSanPhamCu(){
        ModelObjectComboBox dv = (ModelObjectComboBox)cbSPDaCo.getSelectedItem();
        return dv.getMa();
    }
    
    public void requestFocus(){
        txtSPChuaCo.requestFocus();
        txtSPChuaCo.selectAll();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<Object> cbSPDaCo;
    private javax.swing.JTextField txtSPChuaCo;
    // End of variables declaration//GEN-END:variables
}
