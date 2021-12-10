package service;

import entity.LoHang;

public interface LoHangService {
    public boolean insertLohang(LoHang loHang);
    public String getMaxID();
}
