package service;

import java.util.List;

import entity.KhachHang;

public interface KhachHangService {
    public boolean themKhachHang(KhachHang khachHang);
    public boolean capNhatKhachHang(KhachHang khachHang);
    public boolean xoaKhachHang(String maKhachHang);
    public KhachHang getKhachHang(String id);
    public List<KhachHang> getDSKhachHang();
    public List<KhachHang> layDSKhachHang(String tuKhoa);
    public List<KhachHang> layDSKhachHang1(String tuKhoa);
    public List<KhachHang> getDsKhachHangLimit(String tuKhoa);
}
