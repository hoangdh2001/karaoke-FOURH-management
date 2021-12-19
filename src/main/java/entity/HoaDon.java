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
    @Column(nullable = false, columnDefinition = "datetime")
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
    @Column(columnDefinition = "money")
    private double donGiaPhongCu;
    @Column(columnDefinition = "money", nullable = true)
    private double tienCoc;

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
        this.dsChiTietHoaDon = new ArrayList<>();
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
    public void themCT_HoaDon(MatHang matHang, int soLuong) throws Exception {
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
     * @throws java.lang.Exception
     */
    public void setPhong(Phong phong) throws Exception {
        if (phong != null) {
            this.phong = phong;
        } else {
            throw new Exception("Phòng không được rỗng");
        }
    }

    /**
     * @return the nhanVien
     */
    public NhanVien getNhanVien() {
        return nhanVien;
    }

    /**
     * @param nhanVien the nhanVien to set
     * @throws java.lang.Exception
     */
    public void setNhanVien(NhanVien nhanVien) throws Exception {
        if (nhanVien != null) {
            this.nhanVien = nhanVien;
        } else {
            throw new Exception("Nhân viên không được rỗng");
        }
    }

    /**
     * @return the ngayLapHoaDon
     */
    public Date getNgayLapHoaDon() {
        return ngayLapHoaDon;
    }

    /**
     * @param ngayLapHoaDon the ngayLapHoaDon to set
     * @throws java.lang.Exception
     */
    public void setNgayLapHoaDon(Date ngayLapHoaDon) throws Exception {
        if (ngayLapHoaDon != null) {
            this.ngayLapHoaDon = ngayLapHoaDon;
        } else {
            throw new Exception("Ngày lập hóa đơn không được rỗng");
        }
    }

    /**
     * @return the thoiGianKetThuc
     */
    public Date getThoiGianKetThuc() {
        return thoiGianKetThuc;
    }

    /**
     * @param thoiGianKetThuc the thoiGianKetThuc to set
     * @throws java.lang.Exception
     */
    public void setThoiGianKetThuc(Date thoiGianKetThuc) throws Exception {
        if (thoiGianKetThuc.after(thoiGianBatDau)) {
            this.thoiGianKetThuc = thoiGianKetThuc;
        } else {
            throw new Exception("Thời gian kết thúc phải sau ngày hiện tại");
        }
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
     * @throws java.lang.Exception
     */
    public void setTrangThai(TrangThaiHoaDon trangThai) throws Exception {
        if (trangThai != null) {
            this.trangThai = trangThai;
        } else {
            throw new Exception("Trạng thái không được rỗng");
        }
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

    /**
     * @param thoiGianBatDau
     * @throws Exception
     */
    public void setThoiGianBatDau(Date thoiGianBatDau) throws Exception {
        if (thoiGianBatDau != null) {
            this.thoiGianBatDau = thoiGianBatDau;
        } else {
            throw new Exception("Thời gian bắt đầu không được rỗng");
        }
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
        return donGiaPhongCu;
    }

    /**
     * @param donGiaPhongCu
     */
    public void setDonGiaPhongCu(double donGiaPhongCu) {
        this.donGiaPhongCu = donGiaPhongCu;
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
        return tongHoaDon = (getTongTienMatHang() + getDonGiaPhong() + getDonGiaPhongCu()) * (1 - chietKhau);
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

    /**
     * @return tienCoc
     */
    public double getTienCoc() {
        return tienCoc;
    }

    /**
     * @param tienCoc
     * @throws Exception
     */
    public void setTienCoc(double tienCoc) throws Exception {
        if (tienCoc > 0) {
            this.tienCoc = tienCoc;
        } else {
            throw new Exception("Tiền cọc phải lớn hơn 0");
        }
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHoaDon=" + maHoaDon + ", khachHang=" + khachHang + ", phong=" + phong + ", nhanVien=" + nhanVien + ", ngayLapHoaDon=" + ngayLapHoaDon + ", thoiGianBatDau=" + thoiGianBatDau + ", thoiGianKetThuc=" + thoiGianKetThuc + ", chietKhau=" + chietKhau + ", trangThai=" + trangThai + ", dsChiTietHoaDon=" + dsChiTietHoaDon + ", gioHat=" + gioHat + ", donGiaPhong=" + donGiaPhong + ", tongTienMatHang=" + tongTienMatHang + ", tongHoaDon=" + tongHoaDon + ", tienKhachDua=" + tienKhachDua + ", tienThua=" + tienThua + '}';
    }
}
