package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
/**
 * @author Bùi Quang Hữu
 */
@Entity
@IdClass(ChiTietNhapHang_PK.class)
public class ChiTietNhapHang {

    @Id
    @ManyToOne
    @JoinColumn(name = "maLoHang", nullable = false)
    private LoHang loHang;
    @Id
    @ManyToOne
    @JoinColumn(name = "maMatHang", nullable = false)
    private MatHang matHang;
    @Column(nullable = false)
    private int soLuongNhap;
    @Column(columnDefinition = "money", nullable = false)
    private double giaNhap;
    @Column(columnDefinition = "money")
    private double thanhTien;

    /**
     * @param loHang
     * @param matHang
     * @param soLuongNhap
     * @param giaNhap
     */
    public ChiTietNhapHang(LoHang loHang, MatHang matHang, int soLuongNhap, double giaNhap) {
        this.loHang = loHang;
        this.matHang = matHang;
        this.soLuongNhap = soLuongNhap;
        this.giaNhap = giaNhap;
    }

    /**
     *
     */
    public ChiTietNhapHang() {
    }

    /**
     * @return the loHang
     */
    public LoHang getLoHang() {
        return loHang;
    }

    /**
     * @param loHang the loHang to set
     */
    public void setLoHang(LoHang loHang) {
        this.loHang = loHang;
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
     * @return the soLuongNhap
     */
    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    /**
     * @param soLuongNhap the soLuongNhap to set
     * @throws java.lang.Exception
     */
    public void setSoLuongNhap(int soLuongNhap) throws Exception {
        if(soLuongNhap > 0) {
            this.soLuongNhap = soLuongNhap;
        } else {
            throw new Exception("Số lượng nhập phải lớn hơn 0");
        }
    }

    /**
     * @return the giaNhap
     */
    public double getGiaNhap() {
        return giaNhap;
    }

    /**
     * @param giaNhap the giaNhap to set
     * @throws java.lang.Exception
     */
    public void setGiaNhap(double giaNhap) throws Exception {
        if(giaNhap > 0) {
            this.giaNhap = giaNhap;
        } else {
            throw new Exception("Giá nhập phải lớn hơn 0");
        }
    }

    /**
     * @return the thanhTien
     */
    public double getThanhTien() {
        return giaNhap * soLuongNhap;
    }

    @Override
    public String toString() {
        return "ChiTietNhapHang [loHang=" + loHang + ", matHang=" + matHang + ", soLuongNhap=" + soLuongNhap
                + ", giaNhap=" + giaNhap + ", thanhTien=" + thanhTien + "]";
    }
}
