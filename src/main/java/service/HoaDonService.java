package service;

import entity.ChiTietHoaDon;
import entity.HoaDon;
import entity.Phong;
import java.util.List;
import java.util.Map;

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
    public List<HoaDon> findHoaDon(String batDau, String ketThuc,String ma,int page);
    public List<HoaDon> findHoaDonByThangNam(int thangOrNam,String loaiPhong,Boolean thang,int year,int page);
    public String getMaxID();
    public HoaDon getHoaDonByIdPhong(String id);
    public Map<Integer, Double> getDoanhThuHoaDonTheoNam(int nam);
    public Map<Integer, Double> getDoanhThuHoaDonTheoThang(int thang, int nam);
    public int getNumOfRecord(int thangOrNam,String loaiPhong,Boolean thang,int year);
    public int getNumOfRecordByDate(String batDau, String ketThuc,String ma);
    public double getTotalOfRecord(int thangOrNam,String loaiPhong,Boolean thang,int year);
    public double getTotalOfRecordByDate(String batDau, String ketThuc,String ma);
}
