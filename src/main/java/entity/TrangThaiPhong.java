package entity;

import java.awt.Color;

public enum TrangThaiPhong {
    
    TRONG ("Trống", new Color(0, 166, 90)),
    DANG_HAT ("Đang hát", new Color(253, 187, 65)),
    DAT_TRUOC ("Đặt trước", new Color(60, 141, 188)),
    BAN ("Bẩn", new Color(241, 202, 53)),
    DANG_DON ("Đang dọn", new Color(100, 100, 109)),
    DANG_SUA ("Đang sửa", Color.GRAY);
    
    private final String trangThai;
    private final Color color;
    private TrangThaiPhong(String trangThai, Color color) {    
        this.trangThai = trangThai;
        this.color = color;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public Color getColor() {
        return color;
    }

//    @Override
//    public String toString() {
//        return trangThai;
//    }
}
