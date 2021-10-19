package service;

import java.util.List;

import entity.KhachHang;

public interface KhachHangService {
	public boolean themKhachHang(KhachHang khachHang);
	public boolean capNhatKhachHang(KhachHang khachHang);
	public boolean xoaKhachHang(String id);
	public KhachHang getKhachHang(String id);
	public List<KhachHang> getDSKhachHang();
}
