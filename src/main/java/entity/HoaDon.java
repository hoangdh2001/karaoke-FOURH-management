package entity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.transaction.Transactional;

@Entity
@Transactional
public class HoaDon {

    @Id
    private String maHoaDon;
    @ManyToOne
    @JoinColumn(name = "maKhachHang")
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "maPhong", nullable = false)
    private Phong phong;
    @ManyToOne
    @JoinColumn(name = "maNhanVien", nullable = false)
    private NhanVien nhanVien;
    @Column(columnDefinition = "datetime")
    private Date ngayLapHoaDon;
    @Column(columnDefinition = "datetime")
    private Date thoiGianBatDau;
    @Column(nullable = false,columnDefinition = "datetime")
    private Date thoiGianKetThuc;
    private float chietKhau;
    @Enumerated(EnumType.STRING)
    private TrangThaiHoaDon trangThai;
    @OneToMany(mappedBy = "hoaDon")
    private List<ChiTietHoaDon> dsChiTietHoaDon;
    private String gioHat;
    @Column(columnDefinition = "money")
    private double donGiaPhong;
    @Column(columnDefinition = "money")
    private double tongTienMatHang;
    @Column(columnDefinition = "money")
    private double tongHoaDon;
    @Column(columnDefinition = "money")
    private double tienKhachDua;
    @Column(columnDefinition = "money")
    private double tienThua;

    /**
     * @param maHoaDon
     * @param phong
     * @param nhanVien
     */
    public HoaDon(String maHoaDon, Phong phong, NhanVien nhanVien) {
        this.maHoaDon = maHoaDon;
        this.phong = phong;
        this.nhanVien = nhanVien;
        this.thoiGianBatDau = new Date();
        this.trangThai = TrangThaiHoaDon.DANG_XU_LY;
        this.dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
        this.gioHat = getGioHat();
        this.donGiaPhong = getDonGiaPhong();
        this.tongTienMatHang = getTongTienMatHang();
        this.tongHoaDon = getTongHoaDon();
        this.tienThua = getTienThua();
    }

    public HoaDon(String maHoaDon, KhachHang khachHang, Phong phong, NhanVien nhanVien) {
        this.maHoaDon = maHoaDon;
        this.khachHang = khachHang;
        this.phong = phong;
        this.nhanVien = nhanVien;
        this.thoiGianBatDau = new Date();
        this.trangThai = TrangThaiHoaDon.DANG_XU_LY;
        this.dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
        this.gioHat = getGioHat();
        this.donGiaPhong = getDonGiaPhong();
        this.tongTienMatHang = getTongTienMatHang();
        this.tongHoaDon = getTongHoaDon();
        this.tienThua = getTienThua();
    }

    /**
     *
     */
    public HoaDon() {
        this.thoiGianBatDau = new Date();
        this.trangThai = TrangThaiHoaDon.DANG_XU_LY;
        this.dsChiTietHoaDon = new ArrayList<ChiTietHoaDon>();
        this.gioHat = getGioHat();
        this.donGiaPhong = getDonGiaPhong();
        this.tongTienMatHang = getTongTienMatHang();
        this.tongHoaDon = getTongHoaDon();
        this.tienThua = getTienThua();
    }

    /**
     * @param matHang
     * @param soLuong
     */
    public void themCT_HoaDon(MatHang matHang, int soLuong) {
        ChiTietHoaDon newChiTietHoaDon = new ChiTietHoaDon(this, matHang, soLuong);
        if (dsChiTietHoaDon.contains(newChiTietHoaDon)) {
            ChiTietHoaDon chiTietHoaDon = dsChiTietHoaDon.get(dsChiTietHoaDon.indexOf(newChiTietHoaDon));
            chiTietHoaDon.setSoLuong(chiTietHoaDon.getSoLuong() + newChiTietHoaDon.getSoLuong());
        } else {
            dsChiTietHoaDon.add(newChiTietHoaDon);
        }
    }

    /**
     * @return the maHoaDon
     */
    public String getMaHoaDon() {
        return maHoaDon;
    }

    /**
     * @param maHoaDon the maHoaDon to set
     */
    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
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
     * @return the nhanVien
     */
    public NhanVien getNhanVien() {
        return nhanVien;
    }

    /**
     * @param nhanVien the nhanVien to set
     */
    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    /**
     * @return the ngayLapHoaDon
     */
    public Date getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    /**
     * @param ngayLapHoaDon the ngayLapHoaDon to set
     */
    public void setNgayLapHoaDon(Date ngayLapHoaDon) {
        this.ngayLapHoaDon = ngayLapHoaDon;
    }

    /**
     * @return the thoiGianKetThuc
     */
    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    /**
     * @param thoiGianKetThuc the thoiGianKetThuc to set
     */
    public void setThoiGianKetThuc(Date thoiGianKetThuc) {
        this.thoiGianKetThuc = thoiGianKetThuc;
    }

    /**
     * @return chietKhau
     */
    public float getChietKhau() {
        return chietKhau;
    }

    /**
     * @param chietKhau
     */
    public void setChietKhau(float chietKhau) {
        this.chietKhau = chietKhau;
    }

    /**
     * @return trangThai
     */
    public TrangThaiHoaDon getTrangThai() {
        return trangThai;
    }

    /**
     * @param trangThai
     */
    public void setTrangThai(TrangThaiHoaDon trangThai) {
        this.trangThai = trangThai;
    }

    /**
     * @return the dsChiTietHoaDon
     */
    public List<ChiTietHoaDon> getDsChiTietHoaDon() {
        return dsChiTietHoaDon;
    }

    /**
     * @param dsChiTietHoaDon the dsChiTietHoaDon to set
     */
    public void setDsChiTietHoaDon(List<ChiTietHoaDon> dsChiTietHoaDon) {
        this.dsChiTietHoaDon = dsChiTietHoaDon;
    }

    /**
     * @return the thoiGianBatDau
     */
    public Date getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Date thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    /**
     * @return the gioHat
     */
    public String getGioHat() {
        if (thoiGianKetThuc != null) {
            long diff = (thoiGianKetThuc.getTime() - thoiGianBatDau.getTime()) / 1000;
            int diffHours = (int) (diff / 3600);
            diff = diff % 3600;
            int mins = (int) (diff / 60);
            gioHat = String.format("%02d:%02d", diffHours, mins);
            return gioHat;
        }
        gioHat = "00:00";
        return gioHat;
    }

    /**
     * @return the donGiaPhong
     */
    public double getDonGiaPhong() {
        if (phong != null) {
            String[] time = getGioHat().split(":");
            donGiaPhong = (Double.parseDouble(time[0]) * phong.getLoaiPhong().getGiaPhong()) + (Double.parseDouble(time[1]) / 60 * phong.getLoaiPhong().getGiaPhong());
            return donGiaPhong;
        }
        return 0;
    }

    /**
     * @return the donGiaPhongCu
     */
    public double getDonGiaPhongCu() {
        return donGiaPhong;
    }

    /**
     * @return the tongTienMatHang
     */
    public double getTongTienMatHang() {
        double tongTien = 0.0;
        for (ChiTietHoaDon chiTietHoaDon : dsChiTietHoaDon) {
            tongTien += chiTietHoaDon.getThanhTien();
        }
        tongTienMatHang = tongTien;
        return tongTienMatHang;
    }

    /**
     * @return the tongHoaDon
     */
    public double getTongHoaDon() {
        return tongHoaDon = (getTongTienMatHang() + getDonGiaPhong()) * (1 - chietKhau);
    }

    /**
     * @return tienKhachDua
     */
    public double getTienKhachDua() {
        return tienKhachDua;
    }

    /**
     * @param tienKhachDua
     */
    public void setTienKhachDua(double tienKhachDua) {
        this.tienKhachDua = tienKhachDua;
    }

    /**
     * @return thoiLai
     */
    public double getTienThua() {
        return tienThua = getTienKhachDua() - getTongHoaDon();
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", khachHang=" + khachHang + ", phong=" + phong + ", nhanVien=" + nhanVien + ", ngayLapHoaDon=" + ngayLapHoaDon + ", thoiGianBatDau=" + thoiGianBatDau + ", thoiGianKetThuc=" + thoiGianKetThuc + ", chietKhau=" + chietKhau + ", trangThai=" + trangThai + ", dsChiTietHoaDon=" + dsChiTietHoaDon + ", gioHat=" + gioHat + ", donGiaPhong=" + donGiaPhong + ", tongTienMatHang=" + tongTienMatHang + ", tongHoaDon=" + tongHoaDon + ", tienKhachDua=" + tienKhachDua + ", tienThua=" + tienThua + '}';
    }

    public Object[] convertToRowTable(){
        DecimalFormat dcf = new DecimalFormat("#,###");
        SimpleDateFormat fm1 = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat fm2 = new SimpleDateFormat("HH:mm");
        return new Object[]{maHoaDon, khachHang.getTenKhachHang(), phong.getTenPhong(), gioHat, fm1.format(ngayLapHoaDon), fm2.format(thoiGianBatDau), dcf.format(tongTienMatHang), dcf.format(phong.getLoaiPhong().getGiaPhong()), dcf.format(tongHoaDon), nhanVien.getTenNhanVien()};
    }
    
    public Object[] convertToRowTableInGDThongKeDoanhThu() {
        DecimalFormat df;
        df = new DecimalFormat("#,##0.00");
        SimpleDateFormat gio = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String ngayLap = gio.format(ngayLapHoaDon);
        return new Object[]{maHoaDon, ngayLap, khachHang == null ? "Trống":khachHang.getTenKhachHang(), nhanVien.getTenNhanVien(), df.format(getTongHoaDon())};
    }
}
