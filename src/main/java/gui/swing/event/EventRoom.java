package gui.swing.event;

import entity.HoaDon;
import entity.PhieuDatPhong;
import entity.Phong;

public interface EventRoom {
    public HoaDon addBtnThueEvent(Phong phong);
    public void addBtnThemDichVuEvent(HoaDon hoaDon);
    public void addBtnThanhToanEvent(HoaDon hoaDon);
    public void addBtnDonXongEvent(Phong phong); 
    public void addBtnSuaPhongEvent(Phong phong);
    public void addBtnDonPhongEvent(Phong phong);
    public void addBtnSuaXongEvent(Phong phong);
    public void addBtnDoiPhongEvent(Phong phong, HoaDon hoaDon);
    public void addBtnHuyEvent(Phong phong, PhieuDatPhong phieuDatPhong);
    public HoaDon addBtnThueEvent(Phong phong, PhieuDatPhong phieuDatPhong);
    public void addBtnDoiPhongEvent(PhieuDatPhong phieuDatPhong);
    public void addBtnDatPhongEvent(Phong phong);
}
