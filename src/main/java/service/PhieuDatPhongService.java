package service;

import entity.PhieuDatPhong;
import java.util.List;

public interface PhieuDatPhongService {
    public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public List<PhieuDatPhong> getDsPhieuDatPhong(int numPage);
    public List<String> getDSTrangThaiPhieu();
    public PhieuDatPhong getPhieuDatPhong(String maPhieuDat);
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat, int numPage);
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public String getMaxID();
    public PhieuDatPhong getPhieuDatPhongByIDPhong(String maPhong);
    public List<PhieuDatPhong> getDsPhieuDatByPhong(String maPhong);
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong);
    public int getSoLuongPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat);
}
