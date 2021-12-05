package gui.component;

import dao.NhaCungCapVaNhapHang_DAO;
import entity.MatHang;
import java.util.List;
import objectcombobox.ObjectComboBox;

public class PanelTenSanPham extends javax.swing.JPanel {
    private NhaCungCapVaNhapHang_DAO nhaCungCapVaNhapHang_DAO ;

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

        nhaCungCapVaNhapHang_DAO = new NhaCungCapVaNhapHang_DAO();
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
            List<MatHang> listMH = nhaCungCapVaNhapHang_DAO.getDanhSachMatHangByLoaiDichVu(id);
            for (int i = 0; i < listMH.size(); i++) {
                MatHang dv = listMH.get(i);
                cbSPDaCo.addItem(new ObjectComboBox(dv.getTenMatHang(),dv.getMaMatHang()));  
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
        ObjectComboBox dv = (ObjectComboBox)cbSPDaCo.getSelectedItem();
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
