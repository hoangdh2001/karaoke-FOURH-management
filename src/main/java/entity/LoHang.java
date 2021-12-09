package entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class LoHang {

    @Id
    private String maLoHang;
    @ManyToOne
    @JoinColumn(name = "maNCC", nullable = false)
    private NhaCungCap nhaCungCap;
    private Date ngayNhap;
    @ManyToOne
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien nguoiNhap;
    @OneToMany(mappedBy = "loHang")
    private List<ChiTietNhapHang> dsChiTietNhapHang;
    @Column(columnDefinition = "money")
    private double tongTien;

    /**
     * @param maLoHang
     * @param nhaCungCap
     * @param ngayNhap
     * @param nguoiNhap
     */
    public LoHang() {
        this.dsChiTietNhapHang = new ArrayList<ChiTietNhapHang>();
    }

    public LoHang(String maLoHang, NhaCungCap nhaCungCap, Date ngayNhap, NhanVien nguoiNhap) {
        this.maLoHang = maLoHang;
        this.nhaCungCap = nhaCungCap;
        this.ngayNhap = ngayNhap;
        this.nguoiNhap = nguoiNhap;
        this.dsChiTietNhapHang = new ArrayList<ChiTietNhapHang>();
        this.tongTien = getTongTien();
    }

    /**
     * @param maLoHang
     * @param nhaCungCap
     * @param nguoiNhap
     */
    public LoHang(String maLoHang, NhaCungCap nhaCungCap, NhanVien nguoiNhap) {
        this.maLoHang = maLoHang;
        this.nhaCungCap = nhaCungCap;
        this.nguoiNhap = nguoiNhap;
        this.ngayNhap = Date.valueOf(LocalDate.now());
        this.dsChiTietNhapHang = new ArrayList<ChiTietNhapHang>();
        this.tongTien = getTongTien();
    }

    /**
     *
     */
    public void themCT_NhapHang(MatHang matHang, int soLuongNhap, double giaNhap) {
        ChiTietNhapHang chiTietNhapHang = new ChiTietNhapHang(this, matHang, soLuongNhap, giaNhap);
        dsChiTietNhapHang.add(chiTietNhapHang);
    }

    /**
     * @return the maLoHang
     */
    public String getMaLoHang() {
        return maLoHang;
    }

    /**
     * @param maLoHang the maLoHang to set
     */
    public void setMaLoHang(String maLoHang) {
        this.maLoHang = maLoHang;
    }

    /**
     * @return the nhaCungCap
     */
    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    /**
     * @param nhaCungCap the nhaCungCap to set
     */
    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    /**
     * @return the ngayNhap
     */
    public Date getNgayNhap() {
        return ngayNhap;
    }

    /**
     * @param ngayNhap the ngayNhap to set
     */
    public void setNgayNhap(Date ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    /**
     * @return the nguoiNhap
     */
    public NhanVien getNguoiNhap() {
        return nguoiNhap;
    }

    /**
     * @param nguoiNhap the nguoiNhap to set
     */
    public void setNguoiNhap(NhanVien nguoiNhap) {
        this.nguoiNhap = nguoiNhap;
    }

    /**
     * @return the dsChiTietNhapHang
     */
    public List<ChiTietNhapHang> getDsChiTietNhapHang() {
        return dsChiTietNhapHang;
    }

    /**
     * @param dsChiTietNhapHang the dsChiTietNhapHang to set
     */
    public void setDsChiTietNhapHang(List<ChiTietNhapHang> dsChiTietNhapHang) {
        this.dsChiTietNhapHang = dsChiTietNhapHang;
    }

    /**
     * @return the tongTien
     */
    public double getTongTien() {
        double tong = 0.0;
        for (ChiTietNhapHang chiTietNhapHang : dsChiTietNhapHang) {
            tong += chiTietNhapHang.getThanhTien();
        }
        return tong;
    }

    @Override
    public String toString() {
        return "LoHang [maLoHang=" + maLoHang + ", nhaCungCap=" + nhaCungCap + ", ngayNhap=" + ngayNhap + ", nguoiNhap="
                + nguoiNhap + ", dsChiTietNhapHang=" + dsChiTietNhapHang + ", tongTien=" + tongTien + "]";
    }
}
