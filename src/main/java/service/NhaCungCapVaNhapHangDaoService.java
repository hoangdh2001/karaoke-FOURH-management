/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.ChiTietHoaDon;
import entity.ChiTietNhapHang;
import entity.HoaDon;
import entity.LoaiDichVu;
import entity.MatHang;
import entity.NhaCungCap;
import entity.KhachHang;
import entity.LoHang;
import entity.LoaiPhong;
import entity.PhieuDatPhong;
import entity.NhanVien;
import entity.Phong;
import entity.TrangThaiPhong;
import java.util.List;

/**
 *
 * @author 84975
 */
public interface NhaCungCapVaNhapHangDaoService {
//  Nha Cung Cap
    public boolean addNhaCungCap(NhaCungCap ncc);
    public boolean updateNhaCungCap(NhaCungCap ncc);
    public List<NhaCungCap> getNhaCungCap();
    public NhaCungCap getNhaCungCapById(String id);
    public String getlastNhaCungCap();
    public boolean insertCTNhapHang(ChiTietNhapHang ctNhaphang,String maLoHang);
    public String getLastNhaCungCap();
    public boolean insertNhaCungCap(NhaCungCap ncc);
    public int getSLNhapByDate(String maMatHang,String batDau, String ketThuc);
    public int getSLNhap(String maMatHang,int thangOrNam,Boolean thang,int year);
    public List<Integer> getAllYearExist();
}
