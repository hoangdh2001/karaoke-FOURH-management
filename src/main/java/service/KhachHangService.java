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
    public List<KhachHang> getDSKhachHangByTuKhoa(String tuKhoa, int numPage);
    public int getSoLuongKhachHangByTuKhoa(String tuKhoa);
    public List<KhachHang> getDsKhachHangLimit(String tuKhoa);
    public String getMaxID();
//    public boolean checkKhachHang(String text);
    
//    Huu
//    public KhachHang getKhachHangBySDT(String sdt);
//    public boolean addKhachHang(KhachHang kh);
//    public String getlastKhachHangTang();
//    public int getSoLuongKhachHang();
}
