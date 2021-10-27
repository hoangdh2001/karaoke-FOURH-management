package service;

import entity.Phong;
import entity.TrangThaiPhong;
import java.util.List;

public interface PhongService {
    public boolean addPhong(Phong phong);
    public boolean updatePhong(Phong phong);
    public boolean deletePhong(String maPhong);
    public Phong getPhong(String maPhong);
    public List<Phong> getDsPhong();
    public List<Integer> getDsTang();
    public int getSoLuongPhongTheoTrangThai(TrangThaiPhong trangThai);
}
