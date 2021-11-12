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
    public List<HoaDon> getDsHoaDon();
    public HoaDon getHoaDon(String maHoaDon);
    public List<HoaDon> getDSHoaDonByTenKhachHang(String tenKhachHang);
    public List<HoaDon> getDSHoaDonByTenPhong(String tenPhong);
    public List<HoaDon> getDSHoaDonByTieuChiKhac(String tieuChiKhac, String duLieu);
    public List<HoaDon> getDSHoaDonFromDateToDate(String from, String to);
    public List<HoaDon> getDSHoaDonBefore_Date(String ngay);
    public List<HoaDon> getDSHoaDonAfter_Date(String ngay);
    public List<HoaDon> sapXepTheo(String tieuChi, String thuTu);
}
