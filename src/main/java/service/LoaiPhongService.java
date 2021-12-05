package service;

import entity.LoaiPhong;
import java.util.List;

public interface LoaiPhongService {
    public boolean addLoaiPhong(LoaiPhong loaiPhong);
    public boolean updateLoaiPhong(LoaiPhong loaiPhong);
    public boolean deleteLoaiPhong(String id);
    public LoaiPhong getLoaiPhong(String id);
    public LoaiPhong getLoaiPhongByName(String name);
    public List<LoaiPhong> getDsLoaiPhong();
}
