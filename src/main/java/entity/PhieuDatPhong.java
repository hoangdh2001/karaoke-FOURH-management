package entity;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@Column(columnDefinition = "money")
	private double tienCoc;
	
	/**
	 * @param maPhieuDat
	 * @param khachHang
	 * @param phong
	 * @param ngayDat
	 * @param ngayTao
	 * @param tienCoc
	 */
	public PhieuDatPhong(String maPhieuDat, KhachHang khachHang, Phong phong, Date ngayDat, Date ngayTao,
			double tienCoc) {
		this.maPhieuDat = maPhieuDat;
		this.khachHang = khachHang;
		this.phong = phong;
		this.ngayDat = ngayDat;
		this.ngayTao = ngayTao;
		this.tienCoc = tienCoc;
	}
	/**
	 * @param maPhieuDat
	 * @param khachHang
	 * @param phong
	 * @param ngayDat
	 * @param tienCoc
	 */
	public PhieuDatPhong(String maPhieuDat, KhachHang khachHang, Phong phong, Date ngayDat, double tienCoc) {
		this.maPhieuDat = maPhieuDat;
		this.khachHang = khachHang;
		this.phong = phong;
		this.tienCoc = tienCoc;
		this.ngayDat = ngayDat;
		this.ngayTao = Date.valueOf(LocalDate.now());
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
	 * @return the ngayTao
	 */
	public Date getNgayTao() {
		return ngayTao;
	}
	/**
	 * @param ngayDat the ngayDat to set
	 */
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
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
	@Override
	public String toString() {
		return "PhieuDatPhong [maPhieuDat=" + maPhieuDat + ", khachHang=" + khachHang + ", phong=" + phong
				+ ", ngayDat=" + ngayDat + ", tienCoc=" + tienCoc + "]";
	}
}
