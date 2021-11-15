/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package service;

import java.util.List;

/**
 *
 * @author Hao
 */
public interface DiaChiService {
    public List<String> getDSQuanHuyen();
    public List<String> getDSTinhThanh();
    public List<String> getDSXaPhuong();
    public List<String> getDSQuanHuyenByTinh(String tinh);
    public List<String> getDSXaPhuongByQuanHuyen(String tinh,String quanHuyen);
}
