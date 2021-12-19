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
     * @throws java.lang.Exception
     */
    public void setTenNCC(String tenNCC) throws Exception {
        if(tenNCC.length() > 0) {
            this.tenNCC = tenNCC;
        } else {
            throw new Exception("Tên nhà cung cấp không được rỗng");
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

    /**
     * @return the diaChi
     */
    public DiaChi getDiaChi() {
        return diaChi;
    }

    /**
     * @param diaChi the diaChi to set
     * @throws java.lang.Exception
     */
    public void setDiaChi(DiaChi diaChi) throws Exception {
        if(diaChi != null) {
            this.diaChi = diaChi;
        } else {
            throw new Exception("Địa chỉ không được rỗng");
        }
    }

    @Override
    public String toString() {
        return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", soDienThoai=" + soDienThoai + ", diaChi="
                + diaChi + "]";
    }
}
