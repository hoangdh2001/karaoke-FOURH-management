package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "getLoaiNhanViens", query = "select lnv from LoaiNhanVien lnv")
})
public class LoaiNhanVien {

    @Id
    private String maLoaiNV;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenLoaiNV;

    /**
     * @param maLoaiNV
     * @param tenLoaiNV
     */
    public LoaiNhanVien(String maLoaiNV, String tenLoaiNV) {
        this.maLoaiNV = maLoaiNV;
        this.tenLoaiNV = tenLoaiNV;
    }

    /**
     *
     */
    public LoaiNhanVien() {
    }

    /**
     * @return the maLoaiNV
     */
    public String getMaLoaiNV() {
        return maLoaiNV;
    }

    /**
     * @param maLoaiNV the maLoaiNV to set
     * @throws java.lang.Exception
     */
    public void setMaLoaiNV(String maLoaiNV) throws Exception {
        if(maLoaiNV.length() > 0) {
            this.maLoaiNV = maLoaiNV;
        } else {
            throw new Exception("Mã loại nhân viên không được rỗng");
        }
    }

    /**
     * @return the tenLoaiNV
     */
    public String getTenLoaiNV() {
        return tenLoaiNV;
    }

    /**
     * @param tenLoaiNV the tenLoaiNV to set
     * @throws java.lang.Exception
     */
    public void setTenLoaiNV(String tenLoaiNV) throws Exception {
        if(tenLoaiNV.length() > 0) {
            this.tenLoaiNV = tenLoaiNV;
        } else {
            throw new Exception("Tên loại nhân viên không được rỗng");
        }
    }

    @Override
    public String toString() {
        return "LoaiNhanVien{" + "maLoaiNV=" + maLoaiNV + ", tenLoaiNV=" + tenLoaiNV + '}';
    }

}
