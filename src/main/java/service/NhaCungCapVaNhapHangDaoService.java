/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.LoaiDichVu;
import entity.MatHang;
import entity.NhaCungCap;
import entity.KhachHang;
import entity.PhieuDatPhong;
import entity.NhanVien;
import entity.Phong;
import entity.TrangThaiPhong;
import java.sql.Date;
import java.sql.Time;
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
    public List<MatHang> getDanhSachMatHang();
    public MatHang getMatHang(String maMatHang);
    public String getlastNhaCungCap();
    
    public KhachHang getKhachHangBySDT(String sdt);
    
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong);
    
    public PhieuDatPhong getPhieuById(String maPhieuDatPhong);
    
    public NhanVien getNhanVienByID(String maNhanVien);
    
    public boolean addKhachHang(KhachHang kh);
    public boolean updatePhieuDatHang(String maPhieu);
    public boolean updateSLMatHang(String maMH,int sl,String type);
    public boolean updatePhong(String maPhong,TrangThaiPhong trangThai);
    public boolean insertHoaDon(HoaDon hoasDon);
    public boolean insertCTHoaDon(ChiTietHoaDon ctHoaDon);
    public boolean updateCTHoaDon(ChiTietHoaDon ctHoaDon);
    public HoaDon getHoaDon(Phong phong);
    public boolean updateHoaDon(HoaDon hoaDon,double tongTienPhong,double tongTien,double tongTienMatHang,String date);
    
    public List<Phong> getDSPhongByTrangThai(TrangThaiPhong trangThai);
    
    public String getlastMaHoaDonTang();
    public String getlastKhachHangTang();
}
