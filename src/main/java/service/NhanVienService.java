/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.NhanVien;
import java.util.List;

/**
 *
 * @author NGUYE
 */
public interface NhanVienService {
    public boolean addNhanVien(NhanVien nhanVien);
    public NhanVien getNhanVien(String maNhanVien);
    public List<NhanVien> getNhanViens();
    public NhanVien getLastNhanVien();
    public  List<NhanVien> searchNhanVien(String textSearch, String searchOption, int gioiTinh, String maLoaiNV, String maCaLam);
}
