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
    public List<PhieuDatPhong> getDsPhieuDatPhong();
    public List<String> getDSTrangThaiPhieu();
    public List<PhieuDatPhong> timDSPhieuDatPhongByName(String tuKhoa);
    public List<PhieuDatPhong> timDSPhieuDatPhongByTrangThai(String trangThai);
    public List<PhieuDatPhong> timDSPhieuDatPhongByName_TrangThai(String tuKhoa, String trangThai);
    public List<PhieuDatPhong> timDSPhieuDatPhongNgay(int ngay, int thang, int nam);
    public List<PhieuDatPhong> timDSPhieuDatPhongByName_Ngay(String tuKhoa, int nam, int thang, int ngay);
    public List<PhieuDatPhong> timDSPhieuDatPhongByTrangThai_Ngay(String trangThai, int nam, int thang, int ngay);
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tuKhoa, String trangThai, int nam, int thang, int ngay);
    public boolean capNhatTrangThaiPhieu(String maPhieu);
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public boolean xoaPhieuDatPhong(String maPhieuDat);
    
}
