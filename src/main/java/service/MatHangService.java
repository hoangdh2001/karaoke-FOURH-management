package service;

import entity.MatHang;
import java.util.List;

public interface MatHangService {
    public boolean addMatHang(MatHang matHang);
    public boolean updateMatHang(MatHang matHang);
    public boolean deleteMatHang(String id);
    public MatHang getMatHang(String id);
    public List<MatHang> getDsMatHang();
}
