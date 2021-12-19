/**
 * Entity CaLam có các thuộc tính xác định khoảng thời gian làm việc của
 * NhanVien
 */
package entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "getCaLams", query = "select cl from CaLam cl")
})
public class CaLam {

    @Id
    private String maCa;
    private String gioBatDau;
    private String gioKetThuc;
    @OneToMany(mappedBy = "caLam")
    private List<NhanVien> dsNhanVien;

    /**
     * @param maCa
     * @param gioBatDau
     * @param gioKetThuc
     */
    public CaLam(String maCa, String gioBatDau, String gioKetThuc) {
        this.maCa = maCa;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }

    /**
     *
     */
    public CaLam() {
    }

    /**
     * @return the maCa
     */
    public String getMaCa() {
        return maCa;
    }

    /**
     * @param maCa the maCa to set
     */
    public void setMaCa(String maCa) {
        this.maCa = maCa;
    }

    /**
     * @return the gioBatDau
     */
    public String getGioBatDau() {
        return gioBatDau;
    }

    /**
     * @param gioBatDau the gioBatDau to set
     * @throws java.lang.Exception
     */
    public void setGioBatDau(String gioBatDau) throws Exception {
        if(gioBatDau.length() > 0) {
            this.gioBatDau = gioBatDau;
        } else {
            throw new Exception("Giờ bắt đầu không được rỗng");
        }
    }

    /**
     * @return the gioKetThuc
     */
    public String getGioKetThuc() {
        return gioKetThuc;
    }

    /**
     * @param gioKetThuc the gioKetThuc to set
     * @throws java.lang.Exception
     */
    public void setGioKetThuc(String gioKetThuc) throws Exception {
        if(gioKetThuc != null) {
            this.gioKetThuc = gioKetThuc;
        } else {
            throw new Exception("Giờ kết thúc không được rỗng");
        }
       
    }

    /**
     * @return the dsNhanVien
     */
    public List<NhanVien> getDsNhanVien() {
        return dsNhanVien;
    }

    /**
     * @param dsNhanVien the dsNhanVien to set
     */
    public void setDsNhanVien(List<NhanVien> dsNhanVien) {
        this.dsNhanVien = dsNhanVien;
    }

    @Override
    public String toString() {
        return gioBatDau + "-" + gioKetThuc;
    }
}
