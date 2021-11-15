package entity;

import gui.swing.table2.EventAction;
import gui.swing.model.ModelAction;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.swing.JCheckBox;

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
     * @param diaChi
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
     */
    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    /**
     * @return the canCuocCD
     */
    public String getCanCuocCD() {
        return canCuocCD;
    }

    /**
     * @param canCuocCD the canCuocCD to set
     */
    public void setCanCuocCD(String canCuocCD) {
        this.canCuocCD = canCuocCD;
    }

    /**
     * @return the soDienThoai
     */
    public String getSoDienThoai() {
        return soDienThoai;
    }

    /**
     * @param soDienThoai the soDienThoai to set
     */
    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @Override
    public String toString() {
        return "KhachHang [maKhachHang=" + maKhachHang + ", tenKhachHang=" + tenKhachHang + ", canCuocCD=" + canCuocCD
                + ", soDienThoai=" + soDienThoai + "]";
    }

    public Object[] convertToRowTable(EventAction eventAction) {
        return new Object[]{JCheckBox.class, maKhachHang, tenKhachHang, canCuocCD, soDienThoai, new ModelAction(this, eventAction)};
    }
}
