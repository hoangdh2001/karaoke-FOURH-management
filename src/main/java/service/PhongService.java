package service;

import entity.LoaiPhong;
import entity.Phong;
import entity.TrangThaiPhong;
import java.util.List;

public interface PhongService {
    public boolean addPhong(Phong phong);
    public boolean updatePhong(Phong phong);
    public boolean deletePhong(String maPhong);
    public Phong getPhong(String maPhong);
    public List<Phong> getDsPhong(int numPage, String tenPhong, String loaiPhong);
    public int getSoLuongPhongTheoTrangThai(TrangThaiPhong trangThai);
    public List<Phong> getPhongByAttributes(int tang, String tenPhong, LoaiPhong loaiPhong, TrangThaiPhong trangThai);
    public List<Phong> getDsPhongBySDTOrTen(String sdt, int tang);
    public int getTang();
    public List<Phong> getDsPhongByTang(int tang, String tenPhong, LoaiPhong loaiPhong);
    public void updatePhongByPhieu();
    
//    Huu
    public List<Phong> getDSPhongByTrangThai(TrangThaiPhong trangThai);
    public List<Phong> getDSPhongChuaDat(String date,String maLoaiPhong);
    public String getMaxId();
    public int getSoLuongPhong(String tenPhong, String loaiPhong);
}
