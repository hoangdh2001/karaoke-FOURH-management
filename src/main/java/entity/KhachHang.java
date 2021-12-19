package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "KhachHang")
@NamedQueries({
    @NamedQuery(name = "getDSKhachHang", query = "select kh from KhachHang kh")
})
public class KhachHang {

    @Id
    private String maKhachHang;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenKhachHang;
    @Column(columnDefinition = "char(12)", name = "cccd", nullable = false, unique = true)
    private String canCuocCD;
    @Column(columnDefinition = "char(10)", name = "sdt")
    private String soDienThoai;

    /**
     * @param maKhachHang
     * @param tenKhachHang
     * @param canCuocCD
     * @param soDienThoai
     */
    public KhachHang(String maKhachHang, String tenKhachHang, String canCuocCD, String soDienThoai) {
        this.maKhachHang = maKhachHang;
        this.tenKhachHang = tenKhachHang;
        this.canCuocCD = canCuocCD;
        this.soDienThoai = soDienThoai;
    }

    /**
     *
     */
    public KhachHang() {

    }

    /**
     * @return the maKhachHang
     */
    public String getMaKhachHang() {
        return maKhachHang;
    }

    /**
     * @param maKhachHang the maKhachHang to set
     */
    public void setMaKhachHang(String maKhachHang) {
        this.maKhachHang = maKhachHang;
    }

    /**
     * @return the tenKhachHang
     */
    public String getTenKhachHang() {
        return tenKhachHang;
    }

    /**
     * @param tenKhachHang the tenKhachHang to set
     * @throws java.lang.Exception
     */
    public void setTenKhachHang(String tenKhachHang) throws Exception {
        if(tenKhachHang.length() > 0) {
            this.tenKhachHang = tenKhachHang;
        } else {
            throw new Exception("Tên khách hàng không được rỗng");
        }
    }

    /**
     * @return the canCuocCD
     */
    public String getCanCuocCD() {
        return canCuocCD;
    }

    /**
     * @param canCuocCD the canCuocCD to set
     * @throws java.lang.Exception
     */
    public void setCanCuocCD(String canCuocCD) throws Exception {
        if(canCuocCD.length() > 0) {
            this.canCuocCD = canCuocCD;
        } else {
            throw new Exception("Căn cước công dân không được rỗng");
        }
    }

    /**
     * @return the soDienThoai
     */
    public String getSoDienThoai() {
        return soDienThoai;
    }

    /**
     * @param soDienThoai the soDienThoai to set
     * @throws java.lang.Exception
     */
    public void setSoDienThoai(String soDienThoai) throws Exception {
        if(soDienThoai.length() > 0) {
            this.soDienThoai = soDienThoai;
        } else {
            throw new Exception("Số điện thoại không được rỗng");
        }
    }

    @Override
    public String toString() {
        return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", canCuocCD=" + canCuocCD
                + ", soDienThoai=" + soDienThoai + "]";
    }
}
