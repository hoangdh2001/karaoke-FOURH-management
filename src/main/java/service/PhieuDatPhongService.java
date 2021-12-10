/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.PhieuDatPhong;
import java.util.List;

/**
 *
 * @author Hao
 */
public interface PhieuDatPhongService {
    public boolean addPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public List<PhieuDatPhong> getDsPhieuDatPhong(int numPage);
    public List<String> getDSTrangThaiPhieu();
    public PhieuDatPhong getPhieuDatPhong(String maPhieuDat);
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat, int numPage);
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public String getMaxID();
    public PhieuDatPhong getPhieuDatPhongByIDPhong(String maPhong);
    public List<PhieuDatPhong> getDsPhieuDatByPhong(String maPhong);
//    public void capNhatTrangThaiHuy();
    
//    Huu
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong);
//    public PhieuDatPhong getPhieuById(String maPhieuDatPhong);
//    public double getTienCoc(String maPhieuDat);
//    public PhieuDatPhong getPhieuByIDKhachHang(String maKhachhang);
//    public int getSoLuongPhieuDatPhong();
    public int getSoLuongPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, String trangThai, String ngayDat);
}
