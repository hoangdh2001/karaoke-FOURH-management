package service;

import entity.NhanVien;
import java.util.List;

public interface NhanVienService {
    public boolean checkConnect();
    public boolean addNhanVien(NhanVien nhanVien);
    public boolean updateNhanVien(NhanVien nhanVien);
    public boolean deleteNhanVien(String id);
    public NhanVien getNhanVien(String id);
    public List<NhanVien> getNhanViens();
    public NhanVien getLastNhanVien();
    public  List<NhanVien> searchNhanVien(String textSearch, String searchOption, int gioiTinh, String maLoaiNV, String maCaLam);
    public NhanVien getNhanVienByLogin(String sdt, byte[] matKhau);
    public NhanVien getNhanVienBySdtOrEmail(String sdtOrEmail);
}
