/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import entity.LoHang;

/**
 *
 * @author 84975
 */
public interface LoHangService {
    public String getLastLoHang();
    public boolean insertLohang(LoHang loHang);
}
