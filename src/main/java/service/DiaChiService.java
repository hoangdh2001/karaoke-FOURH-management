package service;

import java.util.List;

public interface DiaChiService {
    public List<String> getDSQuanHuyen();
    public List<String> getDSTinhThanh();
    public List<String> getDSXaPhuong();
    public List<String> getDSQuanHuyenByTinh(String tinh);
    public List<String> getDSXaPhuongByQuanHuyen(String tinh,String quanHuyen);
}
