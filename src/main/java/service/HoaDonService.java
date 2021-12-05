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
    public List<HoaDon> getDsHoaDon();
    public HoaDon getHoaDon(String maHoaDon);
    public List<HoaDon> getDSHoaDonByTenKhachHang(String tenKhachHang);
    public List<HoaDon> getDSHoaDonByTenPhong(String tenPhong);
    public List<HoaDon> getDSHoaDonByTieuChiKhac(String tieuChiKhac, String duLieu);
    public List<HoaDon> getDSHoaDonFromDateToDate(String from, String to);
    public String layNgayLapNhoNhat();
    public String layNgayLapLonNhat();
    public List<HoaDon> sapXepHoaDonByThang(String from, String to, int thang);
    public List<HoaDon> sapXepHoaDonByNam(String from, String to, int nam);
    public List<HoaDon> sapXepHoaDonByQuy(String from, String to, int quy);
    public List<HoaDon> sapXepHoaDonByThang_Quy(String from, String to, int thang, int quy);
    public List<HoaDon> sapXepHoaDonByThang_Nam(String from, String to, int thang, int nam);
    public List<HoaDon> sapXepHoaDonByQuy_Nam(String from, String to, int quy, int nam);
    public List<HoaDon> sapXepHoaDonByThang_Quy_Nam(String from, String to, int thang, int quy, int nam);
    public List<Integer> getDSThangTheoNgayLap();
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
    public boolean updateHoaDonDoiPhong(HoaDon hoaDon,double tongTienPhong,String maPhongMoi);
    public String getMaxID();
    public HoaDon getHoaDonByIdPhong(String id, TrangThaiHoaDon trangThai );
    public Map<Integer, Double> getDoanhThuHoaDonTheoNam(int nam);
    public Map<Integer, Double> getDoanhThuHoaDonTheoThang(int thang);
}
