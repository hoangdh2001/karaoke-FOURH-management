package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Phong {
	@Id
	private String maPhong;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String tenPhong;
        @Enumerated(EnumType.STRING)
	private TrangThaiPhong trangThai;
	@ManyToOne
	@JoinColumn(name = "maLoaiPhong", nullable = false)
	private LoaiPhong loaiPhong;
	/**
	 * @param maPhong
	 * @param tenPhong
	 * @param trangThai
	 * @param loaiPhong
	 */
	public Phong(String maPhong, String tenPhong, TrangThaiPhong trangThai, LoaiPhong loaiPhong) {
		this.maPhong = maPhong;
		this.tenPhong = tenPhong;
		this.trangThai = trangThai;
		this.loaiPhong = loaiPhong;
	}
	/**
	 * 
	 */
	public Phong() {
	}
	/**
	 * @return the maPhong
	 */
	public String getMaPhong() {
		return maPhong;
	}
	/**
	 * @param maPhong the maPhong to set
	 */
	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}
	/**
	 * @return the tenPhong
	 */
	public String getTenPhong() {
		return tenPhong;
	}
	/**
	 * @param tenPhong the tenPhong to set
	 */
	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}
	/**
	 * @return the trangThai
	 */
	public TrangThaiPhong getTrangThai() {
		return trangThai;
	}
	/**
	 * @param trangThai the trangThai to set
	 */
	public void setTrangThai(TrangThaiPhong trangThai) {
		this.trangThai = trangThai;
	}
	/**
	 * @return the loaiPhong
	 */
	public LoaiPhong getLoaiPhong() {
		return loaiPhong;
	}
	/**
	 * @param loaiPhong the loaiPhong to set
	 */
	public void setLoaiPhong(LoaiPhong loaiPhong) {
		this.loaiPhong = loaiPhong;
	}
	@Override
	public String toString() {
		return "Phong [maPhong=" + maPhong + ", tenPhong=" + tenPhong + ", trangThai=" + trangThai + ", loaiPhong="
				+ loaiPhong + "]";
	}
}
