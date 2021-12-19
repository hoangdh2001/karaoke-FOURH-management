package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "LoaiPhong")
@NamedQueries({
    @NamedQuery(name = "getDsLoaiPhong", query = "select lp from LoaiPhong lp")
})
public class LoaiPhong {

    @Id
    private String maLoaiPhong;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenLoaiPhong;
    @Column(columnDefinition = "money", nullable = false)
    private double giaPhong;

    /**
     * @param maLoaiPhong
     * @param tenLoaiPhong
     * @param giaPhong
     */
    public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, Double giaPhong) {
        this.maLoaiPhong = maLoaiPhong;
        this.tenLoaiPhong = tenLoaiPhong;
        this.giaPhong = giaPhong;
    }

    /**
     *
     */
    public LoaiPhong() {
    }

    /**
     * @return the maLoaiPhong
     */
    public String getMaLoaiPhong() {
        return maLoaiPhong;
    }

    /**
     * @param maLoaiPhong the maLoaiPhong to set
     * @throws java.lang.Exception
     */
    public void setMaLoaiPhong(String maLoaiPhong) throws Exception {
        if(maLoaiPhong.length() > 0) {
            this.maLoaiPhong = maLoaiPhong;
        } else {
            throw new Exception("Mã loại phòng không được rỗng");
        }
    }

    /**
     * @return the tenLoaiPhong
     */
    public String getTenLoaiPhong() {
        return tenLoaiPhong;
    }

    /**
     * @param tenLoaiPhong the tenLoaiPhong to set
     * @throws java.lang.Exception
     */
    public void setTenLoaiPhong(String tenLoaiPhong) throws Exception {
        if(tenLoaiPhong.length() > 0) {
            this.tenLoaiPhong = tenLoaiPhong;
        } else {
            throw new Exception("Tên loại phòng không được rỗng");
        }
    }

    /**
     * @return the giaPhong
     */
    public Double getGiaPhong() {
        return giaPhong;
    }

    /**
     * @param giaPhong the giaPhong to set
     * @throws java.lang.Exception
     */
    public void setGiaPhong(Double giaPhong) throws Exception {
        if(giaPhong > 0) {
            this.giaPhong = giaPhong;
        } else {
            throw new Exception("Giá phòng phải lớn hơn 0");
        }
    }

    @Override
    public String toString() {
        return "LoaiPhong{" + "maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", giaPhong=" + giaPhong + '}';
    }

}
