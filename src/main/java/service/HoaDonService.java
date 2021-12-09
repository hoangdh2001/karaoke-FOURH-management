/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;
import java.util.Date;
import entity.TrangThaiHoaDon;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hao
 */
public interface HoaDonService {
    public boolean addHoaDon(HoaDon hoaDon);
    public boolean finishHoaDon(HoaDon hoaDon);
    public List<HoaDon> getDsHoaDon(int numPage, String maNhanVien);
    public int getSoLuongHoaDon(String maNhanvien);
    public HoaDon getHoaDon(String maHoaDon);
    public List<HoaDon> getDSHoaDonByTieuChiKhac(int cot,String duLieu,int numPage, String maNhanVien);
    public int getSoLuongHoaDonByTieuChiKhac(int cot,String duLieu, String maNhanVien);
    public List<HoaDon> getDSHoaDonFromDateToDate(String from, String to,int numPage, String maNhanVien);
    public int getSoLuongHoaDonFromDateToDate(String from, String to, String maNhanVien);
    public String layNgayLapNhoNhat();
    public String layNgayLapLonNhat();
    public List<HoaDon> locHoaDonByThang_Quy_Nam(String from, String to, String thang, String quy, String nam,int numPage, String maNhanVien);
    public int getSoLuongHoaDonByAll(String from, String to, String thang, String quy, String nam, String maNhanVien);
    public List<Integer> getDSThangTheoNgayLap(int quy);
    public List<Integer> getDSNamTheoNgayLap();
    public List<Integer> getDSQuyTheoNgayLap();

//    Huu
    public boolean insertHoaDon(HoaDon hoaDon);
    public List<HoaDon> findHoaDon(String batDau, String ketThuc,String ma);
    public List<HoaDon> findHoaDonByThangNam(int thangOrNam,String loaiPhong,Boolean thang,int year);
    public boolean insertCTHoaDon(ChiTietHoaDon ctHoaDon);
    public boolean updateCTHoaDon(ChiTietHoaDon ctHoaDon);
    public HoaDon getHoaDon(Phong phong);
    public boolean updateHoaDon(HoaDon hoaDon,String gioHat,double tongTienPhong,double tongTien,double tongTienMatHang);
    public String getlastMaHoaDonTang();
    public boolean updateHoaDonDoiPhong(HoaDon hoaDon);
    public String getMaxID();
    public HoaDon getHoaDonByIdPhong(String id);
    public Map<Integer, Double> getDoanhThuHoaDonTheoNam(int nam);
    public Map<Integer, Double> getDoanhThuHoaDonTheoThang(int thang, int nam);
}
