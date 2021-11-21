package gui.swing.event;

import entity.HoaDon;
import entity.Phong;

public interface EventRoom {
    public HoaDon addBtnThueEvent(Phong phong);
    public void addBtnThemDichVuEvent(HoaDon hoaDon);
    public void addBtnThanhToanEvent(HoaDon hoaDon);
    public void addBtnDonXongEvent(Phong phong); 
    public void addBtnSuaPhongEvent(Phong phong);
    public void addBtnDonPhongEvent(Phong phong);
    public void addBtnSuaXongEvent(Phong phong);
}
