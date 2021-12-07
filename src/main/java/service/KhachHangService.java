package service;

import java.util.List;

import entity.KhachHang;

public interface KhachHangService {
    public boolean themKhachHang(KhachHang khachHang);
    public boolean xoaKhachHang(String maKhachHang);
    public boolean capNhatKhachHang(KhachHang khachHang);
    public KhachHang getKhachHang(String id);
    public List<KhachHang> getDSKhachHang(int numPage);
    public boolean capNhatKhachHang(String maKhachHang, String soDienThoaiMoi);
    public List<KhachHang> layDSKhachHang(String tuKhoa);
    public List<KhachHang> getDsKhachHangLimit(String tuKhoa);
    public String getMaxID();
    public boolean checkKhachHang(String text);
    public int getSoLuongKhachHang();
}
