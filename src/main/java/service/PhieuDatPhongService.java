/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.PhieuDatPhong;
import entity.TrangThaiPhieuDat;
import java.util.List;

/**
 *
 * @author Hao
 */
public interface PhieuDatPhongService {
    public List<PhieuDatPhong> getDsPhieuDatPhong();
    public List<String> getDSTrangThaiPhieu();
    public PhieuDatPhong getPhieuDatPhong(String maPhieuDat);
    public List<PhieuDatPhong> timDSPhieuDatPhongByName(String tuKhoa);
    public List<PhieuDatPhong> timDSPhieuDatPhongByTrangThai(TrangThaiPhieuDat trangThai);
    public List<PhieuDatPhong> timDSPhieuDatPhongByName_TrangThai(String tuKhoa, TrangThaiPhieuDat trangThai);
    public List<PhieuDatPhong> timDSPhieuDatPhongNgay(int ngay, int thang, int nam);
    public List<PhieuDatPhong> timDSPhieuDatPhongByName_Ngay(String tuKhoa, int nam, int thang, int ngay);
    public List<PhieuDatPhong> timDSPhieuDatPhongByTrangThai_Ngay(TrangThaiPhieuDat trangThai, int nam, int thang, int ngay);
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tuKhoa, TrangThaiPhieuDat trangThai, int nam, int thang, int ngay);
    public boolean capNhatTrangThaiPhieu(String maPhieu);
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public boolean xoaPhieuDatPhong(String maPhieuDat);
    
//    Huu
    public boolean updatePhieuDatPhong(String maPhieu);
    public List<PhieuDatPhong> getPhieuHomNay(String maPhong);
    public String getLastPhieuDatPhong();
    public PhieuDatPhong getPhieuById(String maPhieuDatPhong);
    public boolean addPhieuDatPhong(PhieuDatPhong phieu,String ngayDat);
    public double getTienCoc(String maPhieuDat);
    public PhieuDatPhong getPhieuCuaPhong(String maKhachhang);
}
