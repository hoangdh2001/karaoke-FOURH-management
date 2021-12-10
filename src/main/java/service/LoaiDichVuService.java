package service;

import entity.LoaiDichVu;
import java.util.List;

public interface LoaiDichVuService {
    public LoaiDichVu getLoaiDichVu(String id);
    public List<LoaiDichVu> getDsLoaiDichVu();
    public List<String> getDsTenLoaiDichVu();
    public LoaiDichVu getLoaiDichVuByMa(String ma);
}
