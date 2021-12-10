package entity;

import gui.swing.event.EventMinus;
import java.text.DecimalFormat;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import gui.swing.model.ModelObjectComboBox;

@Entity
@IdClass(ChiTietHoaDon_PK.class)
@NamedQueries({
    @NamedQuery(name = "getDsChiTietHoaDonByMaHoaDon", query = "select ct from ChiTietHoaDon ct where maHoaDon = :maHoaDon")
})
public class ChiTietHoaDon {

    @Id
    @ManyToOne
    @JoinColumn(name = "maHoaDon", nullable = false)
    private HoaDon hoaDon;
    @Id
    @ManyToOne
    @JoinColumn(name = "maMatHang", nullable = false)
    private MatHang matHang;
    @Column(nullable = false)
    private int soLuong;
    @Column(columnDefinition = "money")
    private double thanhTien;
    
    /**
     * @param hoaDon
     * @param matHang
     * @param soLuong
     * @param chietKhau 
     */
    public ChiTietHoaDon(HoaDon hoaDon, MatHang matHang, int soLuong) {
        this.hoaDon = hoaDon;
        this.matHang = matHang;
        this.soLuong = soLuong;
        this.thanhTien = getThanhTien();
    }

    /**
     *
     */
    public ChiTietHoaDon() {
    }

    /**
     * @return the hoaDon
     */
    public HoaDon getHoaDon() {
        return hoaDon;
    }

    /**
     * @param hoaDon the hoaDon to set
     */
    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    /**
     * @return the matHang
     */
    public MatHang getMatHang() {
        return matHang;
    }

    /**
     * @param matHang the matHang to set
     */
    public void setMatHang(MatHang matHang) {
        this.matHang = matHang;
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     */
    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    /**
     * @return the thanhTien
     */
    public double getThanhTien() {
        return soLuong * matHang.getDonGia();
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon [matHang=" + matHang + ", soLuong=" + soLuong
                + ", thanhTien=" + thanhTien + "]";
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.matHang);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietHoaDon other = (ChiTietHoaDon) obj;
        if (!Objects.equals(this.matHang, other.matHang)) {
            return false;
        }
        return true;
    }
}
