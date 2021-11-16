/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.HoaDon;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hao
 */
public interface HoaDonService {
    public boolean addHoaDon(HoaDon hoaDon);
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
    public String getMaxID();
}
