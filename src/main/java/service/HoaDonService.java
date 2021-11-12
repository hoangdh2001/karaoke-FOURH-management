/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.HoaDon;
import java.util.List;

/**
 *
 * @author Hao
 */
public interface HoaDonService {
    public List<HoaDon> getDsHoaDon();
    public HoaDon getHoaDon(String maHoaDon);
}
