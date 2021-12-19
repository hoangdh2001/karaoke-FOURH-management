/**
 * ChiTietHoaDon bao gồm các thông tin chi tiet hóa đơn: thông tin măt
 * hàng, số lượng sản phẩm, thành tiền.
 */
package entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

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
     * @throws java.lang.Exception
     */
    public void setMatHang(MatHang matHang) throws Exception {
        if(matHang != null) {
            this.matHang = matHang;
        } else {
            throw new Exception("Mặt hàng không được rỗng");
        }
    }

    /**
     * @return the soLuong
     */
    public int getSoLuong() {
        return soLuong;
    }

    /**
     * @param soLuong the soLuong to set
     * @throws java.lang.Exception
     */
    public void setSoLuong(int soLuong) throws Exception {
        if(soLuong > 0) {
            this.soLuong = soLuong;
        } else {
            throw new Exception("Phải có ít nhất 1 mặt hàng");
        }
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
