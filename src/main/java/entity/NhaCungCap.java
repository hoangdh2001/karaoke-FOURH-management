package entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class NhaCungCap {

    @Id
    private String maNCC;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenNCC;
    @Column(name = "sdt", nullable = false, unique = true)
    private String soDienThoai;
    @Embedded
    private DiaChi diaChi;

    /**
     * @param maNCC
     * @param tenNCC
     * @param soDienThoai
     * @param diaChi
     */
    public NhaCungCap(String maNCC, String tenNCC, String soDienThoai, DiaChi diaChi) {
        this.maNCC = maNCC;
        this.tenNCC = tenNCC;
        this.soDienThoai = soDienThoai;
        this.diaChi = diaChi;
    }

    /**
     *
     */
    public NhaCungCap() {
    }

    /**
     * @return the maNCC
     */
    public String getMaNCC() {
        return maNCC;
    }

    /**
     * @param maNCC the maNCC to set
     */
    public void setMaNCC(String maNCC) {
        this.maNCC = maNCC;
    }

    /**
     * @return the tenNCC
     */
    public String getTenNCC() {
        return tenNCC;
    }

    /**
     * @param tenNCC the tenNCC to set
     */
    public void setTenNCC(String tenNCC) {
        this.tenNCC = tenNCC;
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

    /**
     * @return the diaChi
     */
    public DiaChi getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     */
    public void setDiaChi(DiaChi diaChi) {
        this.diaChi = diaChi;
    }

    @Override
    public String toString() {
        return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", soDienThoai=" + soDienThoai + ", diaChi="
                + diaChi + "]";
    }
}
