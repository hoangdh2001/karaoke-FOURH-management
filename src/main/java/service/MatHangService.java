package service;

import entity.MatHang;
import java.util.List;

public interface MatHangService {
    public boolean addMatHang(MatHang matHang);
    public boolean updateMatHang(MatHang matHang);
    public boolean deleteMatHang(String id);
    public MatHang getMatHang(String id);
    public List<MatHang> getDsMatHang();
    public List<MatHang> layDsMatHangTheoTen(String tenMatHang);
    
//    Huu
    public List<MatHang> getDanhSachMatHangByLoaiDichVu(String id);
    public List<MatHang> getDanhSachMatHang();
    
    public boolean updateSLMatHang(String maMH,int sl,String type);
    public String getLastMatHang();
    public boolean insertMatHang(MatHang matHang);
    public boolean updateDonGiaMatHang(MatHang matHang);
    public List<MatHang> findMatHang(String textFind,int type);
}
