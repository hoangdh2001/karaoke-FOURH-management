package service;

import entity.ChiTietHoaDon;
import java.util.List;

public interface ChiTietHoaDonService {
    public boolean addChiTietHoaDon(ChiTietHoaDon chiTietHoaDon);
    public boolean updateChiTietHoaDon(ChiTietHoaDon chiTietHoaDon);
    public boolean deleteChiTietHoaDon(ChiTietHoaDon chiTietHoaDon);
    public List<ChiTietHoaDon> getDsChiTietHoaDonByMaHoaDon(String id);
}
