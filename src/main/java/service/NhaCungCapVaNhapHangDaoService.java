/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.LoaiDichVu;
import entity.MatHang;
import entity.NhaCungCap;
import entity.KhachHang;
import java.util.List;

/**
 *
 * @author 84975
 */
public interface NhaCungCapVaNhapHangDaoService {
//  
    public boolean addNhaCungCap(NhaCungCap ncc);
    public boolean updateNhaCungCap(NhaCungCap ncc);
//  
    public List<NhaCungCap> getNhaCungCap();
    public NhaCungCap getNhaCungCapById(String id);
    
    public List<LoaiDichVu> getLoaiDichVu();
    public List<MatHang> getDanhSachMatHang(String id);
    
    public String getlastNhaCungCap();
    
    public KhachHang getKhachHangBySDT(String sdt);
}
