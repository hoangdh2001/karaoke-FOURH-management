package entity;

import java.awt.Color;

public enum TrangThaiPhieuDat {
    DANG_DOI ("Đang đợi", new Color(0, 166, 90)),
    DA_TIEP_NHAN ("Đã tiếp nhận", Color.BLUE),
    DA_HUY ("Đã hủy", new Color(253, 187, 65)),
    HET_HAN ("Hết hạn", Color.ORANGE);
    
    private final String trangThai;
    private final Color statusColor;
    
    private TrangThaiPhieuDat(String trangThai, Color statusColor) {    
        this.trangThai = trangThai;
        this.statusColor = statusColor;
    }

    public String getTrangThai() {
        return trangThai;
    }  

    public Color getStatusColor() {
        return statusColor;
    }

    @Override
    public String toString() {
        return trangThai;
    }
}
