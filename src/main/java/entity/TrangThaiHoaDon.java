package entity;

import java.awt.Color;
        
public enum TrangThaiHoaDon {
    DANG_XU_LY("Đang xử lý", Color.BLUE),
    HOAN_THANH("Hoàn thành", Color.YELLOW),
    HUY("Hủy", Color.BLACK);
    
    
    private final String trangThai;
    private final Color statusColor;

    private TrangThaiHoaDon(String trangThai, Color statusColor) {
        this.trangThai = trangThai;
        this.statusColor = statusColor;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public Color getStatusColor() {
        return statusColor;
    }
}
