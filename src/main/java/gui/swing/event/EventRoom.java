package gui.swing.event;

import entity.HoaDon;
import entity.Phong;

public interface EventRoom {
    public HoaDon addBtnThueEvent(Phong phong);
    public void addBtnThemDichVuEvent(HoaDon hoaDon);
}
