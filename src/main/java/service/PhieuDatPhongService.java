/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.PhieuDatPhong;
import entity.TrangThaiPhieuDat;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Hao
 */
public interface PhieuDatPhongService {
    public List<PhieuDatPhong> getDsPhieuDatPhong();
    public List<String> getDSTrangThaiPhieu();
    public PhieuDatPhong getPhieuDatPhong(String maPhieuDat);
    public List<PhieuDatPhong> timDSPhieuDatPhongByAllProperty(String tenPhong, String tenKhachHang, TrangThaiPhieuDat trangThai, Date ngayDat);
    public boolean capNhatTrangThaiPhieu(String maPhieu);
    public boolean capNhatPhieuDatPhong(PhieuDatPhong phieuDatPhong);
    public boolean xoaPhieuDatPhong(String maPhieuDat);
}
