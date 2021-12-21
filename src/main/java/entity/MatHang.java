package entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
/**
 * @author Bùi Quang Hữu
 */
@Entity
@Table(name = "MatHang")
@NamedQueries({
    @NamedQuery(name = "getDsMatHang", query = "select mh from MatHang mh")
})
public class MatHang {

    @Id
    private String maMatHang;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenMatHang;
    @ManyToOne
    @JoinColumn(name = "maLoaiDichVu", nullable = false)
    private LoaiDichVu loaiDichVu;
    private int sLTonKho;
    @Column(columnDefinition = "money", nullable = false)
    private double donGia;

    /**
     * @param maMatHang
     * @param tenMatHang
     * @param loaiDichVu
     * @param sLTonKho
     * @param donGia
     */
    public MatHang(String maMatHang, String tenMatHang, LoaiDichVu loaiDichVu, int sLTonKho, double donGia) {
        this.maMatHang = maMatHang;
        this.tenMatHang = tenMatHang;
        this.loaiDichVu = loaiDichVu;
        this.sLTonKho = sLTonKho;
        this.donGia = donGia;
    }

    /**
     *
     */
    public MatHang() {
    }

    /**
     * @return the maMatHang
     */
    public String getMaMatHang() {
        return maMatHang;
    }

    /**
     * @param maMatHang the maMatHang to set
     */
    public void setMaMatHang(String maMatHang) {
        this.maMatHang = maMatHang;
    }

    /**
     * @return the tenMatHang
     */
    public String getTenMatHang() {
        return tenMatHang;
    }

    /**
     * @param tenMatHang the tenMatHang to set
     * @throws java.lang.Exception
     */
    public void setTenMatHang(String tenMatHang) throws Exception {
        if(tenMatHang.length() > 0) {
            this.tenMatHang = tenMatHang;
        } else {
            throw new Exception("Tên mặt hàng không được rỗng");
        }
    }

    /**
     * @return the loaiDichVu
     */
    public LoaiDichVu getLoaiDichVu() {
        return loaiDichVu;
    }

    /**
     * @param loaiDichVu the loaiDichVu to set
     * @throws java.lang.Exception
     */
    public void setLoaiDichVu(LoaiDichVu loaiDichVu) throws Exception {
        if(loaiDichVu != null) {
            this.loaiDichVu = loaiDichVu;
        } else {
            throw new Exception("Loại dịch vụ không được rỗng");
        }
    }

    /**
     * @return the sLTonKho
     */
    public int getsLTonKho() {
        return sLTonKho;
    }

    /**
     * @param sLTonKho the sLTonKho to set
     * @throws java.lang.Exception
     */
    public void setsLTonKho(int sLTonKho) throws Exception {
        if (sLTonKho >= 0) {
            this.sLTonKho = sLTonKho;
        } else {
            throw new Exception("Số lượng tồn kho phải lớn hơn hoặc = 0");
        }
    }

    /**
     * @return the donGia
     */
    public double getDonGia() {
        return donGia;
    }

    /**
     * @param donGia the donGia to set
     * @throws java.lang.Exception
     */
    public void setDonGia(double donGia) throws Exception {
        if(donGia > 0) {
            this.donGia = donGia;
        } else {
            throw new Exception("Đơn giá phải lớn hơn 0");
        }
    }

    @Override
    public String toString() {
        return "MatHang [maMatHang=" + maMatHang + ", tenMatHang=" + tenMatHang + ", loaiDichVu=" + loaiDichVu
                + ", sLTonKho=" + sLTonKho + ", donGia=" + donGia + "]";
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.maMatHang);
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
        final MatHang other = (MatHang) obj;
        if (!Objects.equals(this.maMatHang, other.maMatHang)) {
            return false;
        }
        return true;
    }
}
