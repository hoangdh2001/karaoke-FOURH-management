package entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
/**
 * @author Nguyễn Thị Hảo
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "getDSPhieuDatPhong", query = "select p from PhieuDatPhong p")
})
public class PhieuDatPhong {

    @Id
    private String maPhieuDat;
    @ManyToOne
    @JoinColumn(name = "maKhachHang", nullable = false)
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "maPhong", nullable = false)
    private Phong phong;
    @Column(nullable = false,columnDefinition = "datetime")
    private Date ngayDat;
    @Column(columnDefinition = "datetime")
    private Date ngayTao;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "nvarchar(255)")
    private TrangThaiPhieuDat trangThai;
    @Column(columnDefinition = "money")
    private double tienCoc;
    @ManyToOne
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien nhanVien;

    /**
     * @param maPhieuDat
     * @param khachHang
     * @param phong
     * @param ngayDat
     * @param ngayTao
     * @param trangThai
     * @param tienCoc
     * @param nhanVien
     */
    public PhieuDatPhong(String maPhieuDat, KhachHang khachHang, Phong phong, Date ngayDat, Date ngayTao, TrangThaiPhieuDat trangThai,
            double tienCoc, NhanVien nhanVien) {
        this.maPhieuDat = maPhieuDat;
        this.khachHang = khachHang;
        this.phong = phong;
        this.ngayDat = ngayDat;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.tienCoc = tienCoc;
        this.nhanVien = nhanVien;
    }

    /**
     * @param maPhieuDat
     * @param nhanVien
     */
    public PhieuDatPhong(String maPhieuDat, NhanVien nhanVien) {
        this.maPhieuDat = maPhieuDat;
        this.trangThai = TrangThaiPhieuDat.DANG_DOI;
        this.ngayTao = new Date();
        this.nhanVien = nhanVien;
    }

    /**
     *
     */
    public PhieuDatPhong() {
    }

    /**
     * @return the maPhieuDat
     */
    public String getMaPhieuDat() {
        return maPhieuDat;
    }

    /**
     * @param maPhieuDat the maPhieuDat to set
     */
    public void setMaPhieuDat(String maPhieuDat) {
        this.maPhieuDat = maPhieuDat;
    }

    /**
     * @return the khachHang
     */
    public KhachHang getKhachHang() {
        return khachHang;
    }

    /**
     * @param khachHang the khachHang to set
     * @throws java.lang.Exception
     */
    public void setKhachHang(KhachHang khachHang) throws Exception {
        if(khachHang != null) {
            this.khachHang = khachHang;
        } else {
            throw new Exception("Khách hàng không được rỗng");
        }
    }

    /**
     * @return the phong
     */
    public Phong getPhong() {
        return phong;
    }

    /**
     * @param phong the phong to set
     * @throws java.lang.Exception
     */
    public void setPhong(Phong phong) throws Exception {
        if(phong != null) {
            this.phong = phong;
        } else {
            throw new Exception("Phòng không được rỗng");
        }
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     * @throws java.lang.Exception
     */
    public void setNgayDat(Date ngayDat) throws Exception {
        if(ngayDat.after(new Date())) {
            this.ngayDat = ngayDat;
        } else {
            throw new Exception("Ngày đặt phải sau ngày hiện tại");
        }
    }
    
    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }
    /**
     * @param ngayTao 
     * @throws java.lang.Exception 
     */
    public void setNgayTao(Date ngayTao) throws Exception {
        if(ngayTao != null) {
            this.ngayTao = ngayTao;
        } else {
            throw new Exception("Ngày tạo không được rỗng");
        }
    }
    
    /**
     * @return the tienCoc
     */
    public double getTienCoc() {
        return tienCoc;
    }

    /**
     * @param tienCoc the tienCoc to set
     * @throws java.lang.Exception
     */
    public void setTienCoc(double tienCoc) throws Exception {
        if(tienCoc > 0) {
            this.tienCoc = tienCoc;
        } else {
            throw new Exception("Tiền cọc phải lớn hơn 0");
        }
    }
    
    /**
     * @return the trangThai
     */
    public TrangThaiPhieuDat getTrangThai() {
        return trangThai;
    }
    
    /**
     * @param trangThai the trangThai to set
     */
    public void setTrangThai(TrangThaiPhieuDat trangThai) {
        this.trangThai = trangThai;
    }
    /**
     * @return nhanVien
     */
    public NhanVien getNhanVien() {
        return nhanVien;
    }
    /**
     * @param nhanVien 
     */
    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
    

    @Override
    public String toString() {
        return "PhieuDatPhong{" + "maPhieuDat=" + maPhieuDat + ", khachHang=" + khachHang + ", phong=" + phong + ", ngayDat=" + ngayDat + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + ", tienCoc=" + tienCoc + '}';
    }
}
