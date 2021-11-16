package entity;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.swing.JCheckBox;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "getDSNhanVien", query = "select nv from NhanVien nv")
})
public class NhanVien {

    @Id
    private String maNhanVien;
    @Column(columnDefinition = "nvarchar(255)", nullable = false)
    private String tenNhanVien;
    @ManyToOne
    @JoinColumn(name = "maLoaiNhanVien", nullable = false)
    private LoaiNhanVien loaiNhanVien;
    @ManyToOne
    @JoinColumn(name = "maCa")
    private CaLam caLam;
    @Column(columnDefinition = "char(12)", name = "cccd", unique = true)
    private String canCuocCD;
    private boolean gioiTinh;
    private Date ngaySinh;
    @Column(columnDefinition = "char(10)", name = "sdt", nullable = false, unique = true)
    private String soDienThoai;
    @Column(nullable = false, unique = true)
    private String email;
    @Embedded
    private DiaChi diaChi;
    @Column(nullable = true)
    private byte[] matKhau;

    /**
     * @param maNhanVien
     * @param tenNhanVien
     * @param loaiNhanVien
     * @param caLam
     * @param canCuocCD
     * @param gioiTinh
     * @param ngaySinh
     * @param soDienThoai
     * @param email
     * @param diaChi
     * @param matKhau
     */
    public NhanVien(String maNhanVien, String tenNhanVien, LoaiNhanVien loaiNhanVien, CaLam caLam, String canCuocCD, boolean gioiTinh,
            Date ngaySinh, String soDienThoai, String email, DiaChi diaChi, byte[] matKhau) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.loaiNhanVien = loaiNhanVien;
        this.caLam = caLam;
        this.canCuocCD = canCuocCD;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
        this.matKhau = matKhau;
//        this.dsNhanVien = new ArrayList<NhanVien>();
    }

    /**
     *
     */
    public NhanVien() {
    }

    public NhanVien(String maNhanVien, String tenNhanVien, LoaiNhanVien loaiNhanVien, CaLam caLam, String canCuocCD, boolean gioiTinh, Date ngaySinh, String soDienThoai, String email, DiaChi diaChi) {
        this.maNhanVien = maNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.loaiNhanVien = loaiNhanVien;
        this.caLam = caLam;
        this.canCuocCD = canCuocCD;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.diaChi = diaChi;
//        this.dsNhanVien = new ArrayList<NhanVien>();
    }

    /**
     * @return the maNhanVien
     */
    public String getMaNhanVien() {
        return maNhanVien;
    }

    /**
     * @param maNhanVien the maNhanVien to set
     */
    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    /**
     * @return the tenNhanVien
     */
    public String getTenNhanVien() {
        return tenNhanVien;
    }

    /**
     * @param tenNhanVien the tenNhanVien to set
     */
    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    /**
     * @return the loaiNhanVien
     */
    public LoaiNhanVien getLoaiNhanVien() {
        return loaiNhanVien;
    }

    /**
     * @param loaiNhanVien the loaiNhanVien to set
     */
    public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    /**
     * @return the caLam
     */
    public CaLam getCaLam() {
        return caLam;
    }

    /**
     * @param caLam the caLam to set
     */
    public void setCaLam(CaLam caLam) {
        this.caLam = caLam;
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
     * @return the gioiTinh
     */
    public boolean isGioiTinh() {
        return gioiTinh;
    }

    /**
     * @param gioiTinh the gioiTinh to set
     */
    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    /**
     * @return the ngaySinh
     */
    public Date getNgaySinh() {
        return ngaySinh;
    }

    /**
     * @param ngaySinh the ngaySinh to set
     */
    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

    /**
     * @return the matKhau
     */
    public byte[] getMatKhau() {
        return matKhau;
    }

    /**
     * @param matKhau the matKhau to set
     */
    public void setMatKhau(byte[] matKhau) {
        this.matKhau = matKhau;
    }

    @Override
    public String toString() {
        return "NhanVien [maNhanVien=" + maNhanVien + ", tenNhanVien=" + tenNhanVien + ", loaiNhanVien=" + loaiNhanVien
                + ", caLam=" + caLam + ", canCuocCD=" + canCuocCD + ", ngaySinh=" + ngaySinh + ", soDienThoai="
                + soDienThoai + ", email=" + email + ", diaChi=" + diaChi + ", matKhau=" + Arrays.toString(matKhau)
                + "]";
    }

    public Object[] convertToRowTable() {
        String caLamString = caLam.getGioBatDau() + "-" + caLam.getGioKetThuc();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        
        return new Object[]{JCheckBox.class, maNhanVien, tenNhanVien, gioiTinh == true ? "Nữ" : "Nam", df.format(ngaySinh), soDienThoai, canCuocCD,caLamString, loaiNhanVien.getTenLoaiNV()};
    }
}
