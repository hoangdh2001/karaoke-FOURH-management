package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Nguyễn Hưng
 */
@Entity
@Table(name = "Phong")
public class Phong {

    @Id
    private String maPhong;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenPhong;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "nvarchar(255)")
    private TrangThaiPhong trangThai;
    @ManyToOne
    @JoinColumn(name = "maLoaiPhong", nullable = false)
    private LoaiPhong loaiPhong;
    private int tang;
    @Column(columnDefinition = "image")
    private byte[] anhPhong;

    /**
     * @param maPhong
     * @param tenPhong
     * @param trangThai
     * @param loaiPhong
     * @param tang
     * @param anhPhong
     */
    public Phong(String maPhong, String tenPhong, TrangThaiPhong trangThai, LoaiPhong loaiPhong, int tang, byte[] anhPhong) {
        this.maPhong = maPhong;
        this.tenPhong = tenPhong;
        this.trangThai = trangThai;
        this.loaiPhong = loaiPhong;
        this.tang = tang;
        this.anhPhong = anhPhong;
    }
    
    /**
     * @param maPhong
     */
    public Phong(String maPhong) {
        this.maPhong = maPhong;
        this.trangThai = TrangThaiPhong.TRONG;
    }

    /**
     *
     */
    public Phong() {
        this.trangThai = TrangThaiPhong.TRONG;
    }

    /**
     * @return the maPhong
     */
    public String getMaPhong() {
        return maPhong;
    }

    /**
     * @param maPhong the maPhong to set
     */
    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    /**
     * @return the tenPhong
     */
    public String getTenPhong() {
        return tenPhong;
    }

    /**
     * @param tenPhong the tenPhong to set
     * @throws java.lang.Exception
     */
    public void setTenPhong(String tenPhong) throws Exception {
        if (tenPhong.length() > 0) {
            this.tenPhong = tenPhong;
        } else {
            throw new Exception("Tên phòng không được rỗng");
        }
    }

    /**
     * @return the trangThai
     */
    public TrangThaiPhong getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai the trangThai to set
     * @throws java.lang.Exception
     */
    public void setTrangThai(TrangThaiPhong trangThai) throws Exception {
        if (trangThai != null) {
            this.trangThai = trangThai;
        } else {
            throw new Exception("Trạng thái không được rỗng");
        }
    }

    /**
     * @return the loaiPhong
     */
    public LoaiPhong getLoaiPhong() {
        return loaiPhong;
    }

    /**
     * @param loaiPhong the loaiPhong to set
     * @throws java.lang.Exception
     */
    public void setLoaiPhong(LoaiPhong loaiPhong) throws Exception {
        if (loaiPhong != null) {
            this.loaiPhong = loaiPhong;
        } else {
            throw new Exception("Loại phòng không được rỗng");
        }
    }

    /**
     * @return the tang
     */
    public int getTang() {
        return tang;
    }

    /**
     * @param tang the tang to set
     */
    public void setTang(int tang) {
        this.tang = tang;
    }

    /**
     * @return the anhPhong
     */
    public byte[] getAnhPhong() {
        return anhPhong;
    }

    /**
     * @param anhPhong
     */
    public void setAnhPhong(byte[] anhPhong) {
        this.anhPhong = anhPhong;
    }

    @Override
    public String toString() {
        return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", trangThai=" + trangThai + ", loaiPhong="
                + loaiPhong + "]";
    }
}
