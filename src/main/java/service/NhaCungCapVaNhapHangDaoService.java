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
    
//  Mat Hang
    public List<MatHang> getDanhSachMatHangByLoaiDichVu(String id);
    public List<MatHang> getDanhSachMatHang();
    public MatHang getMatHang(String maMatHang);
    public boolean updatePhieuDatPhong(String maPhieu);
    public boolean updateSLMatHang(String maMH,int sl,String type);
    public String getLastMatHang();
    public boolean insertMatHang(MatHang matHang);
    public boolean updateMatHang(MatHang matHang);
    public List<MatHang> findMatHang(String textFind,int type);
//  LoaiDichVu
    public List<LoaiDichVu> getLoaiDichVu();
    public LoaiDichVu getLoaiDichVuByMa(String ma);
    
//  KhachHang
    public KhachHang getKhachHangBySDT(String sdt);
    public boolean addKhachHang(KhachHang kh);
    public String getlastKhachHangTang();
    
//  PhieuDatPhong
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong);
    public String getLastPhieuDatPhong();
    public PhieuDatPhong getPhieuById(String maPhieuDatPhong);
    public boolean addPhieuDatPhong(PhieuDatPhong phieu,String ngayDat);
    public double getTienCoc(String maPhieuDat);
    public PhieuDatPhong getPhieuCuaPhong(String maKhachhang);
//  NhanVien
    public NhanVien getNhanVienByID(String maNhanVien);
    
//    Phong 
    public boolean updatePhong(String maPhong,TrangThaiPhong trangThai);
    public List<Phong> getDSPhongByTrangThai(TrangThaiPhong trangThai);
    public List<Phong> getDSPhongChuaDat(String date,String maLoaiPhong);
//    loai phong
    public List<LoaiPhong> getDSLoaiPhong();
//  Hoa Don
    
//  Lo Hang
    
//    
    
    
    
    
    
    
    
    
    
    
    
    
}
