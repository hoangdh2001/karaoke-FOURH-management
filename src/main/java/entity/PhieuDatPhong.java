package entity;

import gui.swing.table2.EventAction;
import gui.swing.model.ModelAction;
import java.text.DecimalFormat;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.swing.JCheckBox;

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
     * @param khachHang
     * @param phong
     * @param tienCoc
     */
    public PhieuDatPhong(String maPhieuDat, KhachHang khachHang, Phong phong, double tienCoc, NhanVien nhanVien) {
        this.maPhieuDat = maPhieuDat;
        this.khachHang = khachHang;
        this.phong = phong;
        this.tienCoc = tienCoc;
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
    
    public Object[] convertToRowTable(EventAction event) {
        DecimalFormat dcf = new DecimalFormat("#,###");
        SimpleDateFormat fm = new SimpleDateFormat("dd-MM-yyyy hh:mm");
        return new Object[]{JCheckBox.class, maPhieuDat, fm.format(ngayTao), khachHang.getTenKhachHang(), phong.getTenPhong(), fm.format(ngayDat), trangThai, dcf.format(tienCoc), new ModelAction(this, event)};
    }
    
    public Object[] convertToRowTableInGDTiepNhanDatPhong(){
        SimpleDateFormat formatterGio = new SimpleDateFormat("HH:mm");
        System.out.println(formatterGio.format(ngayDat.getTime()));
        return new Object[]{maPhieuDat,khachHang.getTenKhachHang(),formatterGio.format(ngayDat.getTime())};
    }
}
