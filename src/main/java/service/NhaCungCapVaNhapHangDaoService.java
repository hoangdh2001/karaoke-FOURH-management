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
//  
    public boolean addNhaCungCap(NhaCungCap ncc);
    public boolean updateNhaCungCap(NhaCungCap ncc);
//  
    public List<NhaCungCap> getNhaCungCap();
    
    public List<MatHang> getDanhSachMatHangByLoaiDichVu(String id);
    
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
    public boolean updatePhieuDatPhong(String maPhieu);
    public boolean updateSLMatHang(String maMH,int sl,String type);
    public boolean updatePhong(String maPhong,TrangThaiPhong trangThai);
    public boolean insertHoaDon(HoaDon hoaDon);
    public boolean insertCTHoaDon(ChiTietHoaDon ctHoaDon);
    public boolean updateCTHoaDon(ChiTietHoaDon ctHoaDon);
    public HoaDon getHoaDon(Phong phong);
    public boolean updateHoaDon(HoaDon hoaDon,String gioHat,double tongTienPhong,double tongTien,double tongTienMatHang);
    
    public List<Phong> getDSPhongByTrangThai(TrangThaiPhong trangThai);
    
    public String getlastMaHoaDonTang();
    public String getlastKhachHangTang();
    public String getLastPhieuDatPhong();
    public String getLastMatHang();
    public String getLastLoHang();
    
    public boolean updateHoaDonDoiPhong(HoaDon hoaDon,double tongTienPhong,String maPhongMoi);
    
    public List<LoaiPhong> getDSLoaiPhong();
    
    public List<Phong> getDSPhongChuaDat(String date,String maLoaiPhong);
    
    public boolean addPhieuDatPhong(PhieuDatPhong phieu,String ngayDat);
    
    public double getTienCoc(String maPhieuDat);
    
    public LoaiDichVu getLoaiDichVuByMa(String ma);
    
    public boolean insertMatHang(MatHang matHang);
    public boolean updateMatHang(MatHang matHang);
    
    public boolean insertLohang(LoHang loHang);
    
    public boolean insertCTNhapHang(ChiTietNhapHang ctNhaphang,String maLoHang);
    public String getLastNhaCungCap();
    public boolean insertNhaCungCap(NhaCungCap ncc);
    
    public List<MatHang> findMatHang(String textFind,int type);
    
    public List<HoaDon> findHoaDon(String batDau, String ketThuc,String ma);
    
    public List<HoaDon> findHoaDonByThangNam(int thangOrNam,String loaiPhong,Boolean thang,int year);
    
    public PhieuDatPhong getPhieuCuaPhong(String maKhachhang);
}
