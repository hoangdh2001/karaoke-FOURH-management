package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LoaiDichVu")
@NamedQueries({
    @NamedQuery(name = "getDsLoaiDichVu", query = "select ldv from LoaiDichVu ldv"),
    @NamedQuery(name = "getDsTenLoaiDichVu", query = "select ldv.tenLoaiDichVu from LoaiDichVu ldv")
})
public class LoaiDichVu {

    @Id
    private String maLoaiDichVu;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenLoaiDichVu;

    /**
     * @param maLoaiDichVu
     * @param tenLoaiDichVu
     */
    public LoaiDichVu(String maLoaiDichVu, String tenLoaiDichVu) {
        this.maLoaiDichVu = maLoaiDichVu;
        this.tenLoaiDichVu = tenLoaiDichVu;
    }

    /**
     *
     */
    public LoaiDichVu() {
    }

    /**
     * @return the maLoaiDichVu
     */
    public String getMaLoaiDichVu() {
        return maLoaiDichVu;
    }

    /**
     * @param maLoaiDichVu the maLoaiDichVu to set
     */
    public void setMaLoaiDichVu(String maLoaiDichVu) {
        this.maLoaiDichVu = maLoaiDichVu;
    }

    /**
     * @return the tenLoaiDichVu
     */
    public String getTenLoaiDichVu() {
        return tenLoaiDichVu;
    }

    /**
     * @param tenLoaiDichVu the tenLoaiDichVu to set
     */
    public void setTenLoaiDichVu(String tenLoaiDichVu) {
        this.tenLoaiDichVu = tenLoaiDichVu;
    }

    @Override
    public String toString() {
        return "LoaiDichVu{" + "maLoaiDichVu=" + maLoaiDichVu + ", tenLoaiDichVu=" + tenLoaiDichVu + '}';
    }

}
