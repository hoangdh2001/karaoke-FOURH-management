package entity;

import gui.swing.table2.EventAction;
import gui.swing.table2.ModelAction;
import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PhieuDatPhong {

    @Id
    private String maPhieuDat;
    @ManyToOne
    @JoinColumn(name = "maKhachHang", nullable = false)
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "maPhong", nullable = false)
    private Phong phong;
    @Column(nullable = false)
    private Date ngayDat;
    private Date ngayTao;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "nvarchar(255)")
    private TrangThaiPhieuDat trangThai;
    @Column(columnDefinition = "money")
    private double tienCoc;

    /**
     * @param maPhieuDat
     * @param khachHang
     * @param phong
     * @param ngayDat
     * @param ngayTao
     * @param trangThai
     * @param tienCoc
     */
    public PhieuDatPhong(String maPhieuDat, KhachHang khachHang, Phong phong, Date ngayDat, Date ngayTao, TrangThaiPhieuDat trangThai,
            double tienCoc) {
        this.maPhieuDat = maPhieuDat;
        this.khachHang = khachHang;
        this.phong = phong;
        this.ngayDat = ngayDat;
        this.ngayTao = ngayTao;
        this.trangThai = trangThai;
        this.tienCoc = tienCoc;
    }

    /**
     * @param maPhieuDat
     * @param khachHang
     * @param phong
     * @param ngayDat
     * @param trangThai
     * @param tienCoc
     */
    public PhieuDatPhong(String maPhieuDat, KhachHang khachHang, Phong phong, Date ngayDat, TrangThaiPhieuDat trangThai, double tienCoc) {
        this.maPhieuDat = maPhieuDat;
        this.khachHang = khachHang;
        this.phong = phong;
        this.tienCoc = tienCoc;
        this.ngayDat = ngayDat;
        this.trangThai = trangThai;
        this.ngayTao = Date.valueOf(LocalDate.now());
    }

    /**
     *
     */
    public PhieuDatPhong() {
    }

    public PhieuDatPhong(String string, KhachHang khachHang, Phong phong, Date valueOf, Date valueOf0, TrangThaiPhieuDat trangThaiPhieuDat, int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
     */
    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    /**
     * @return the phong
     */
    public Phong getPhong() {
        return phong;
    }

    /**
     * @param phong the phong to set
     */
    public void setPhong(Phong phong) {
        this.phong = phong;
    }

    /**
     * @return the ngayDat
     */
    public Date getNgayDat() {
        return ngayDat;
    }

    /**
     * @param ngayDat the ngayDat to set
     */
    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }
    
    /**
     * @return the ngayTao
     */
    public Date getNgayTao() {
        return ngayTao;
    }
    /**
     * @param ngayTao 
     */
    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }
    
    /**
     * @return the tienCoc
     */
    public double getTienCoc() {
        return tienCoc;
    }

    /**
     * @param tienCoc the tienCoc to set
     */
    public void setTienCoc(double tienCoc) {
        this.tienCoc = tienCoc;
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

    @Override
    public String toString() {
        return "PhieuDatPhong{" + "maPhieuDat=" + maPhieuDat + ", khachHang=" + khachHang + ", phong=" + phong + ", ngayDat=" + ngayDat + ", ngayTao=" + ngayTao + ", trangThai=" + trangThai + ", tienCoc=" + tienCoc + '}';
    }
    
    public Object[] convertToRowTable(EventAction event) {
        return new Object[]{"", maPhieuDat, ngayTao, khachHang.getTenKhachHang(), phong.getTenPhong(), ngayDat, trangThai, tienCoc, new ModelAction(this, event)};
    }
}
