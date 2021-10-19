package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class LoaiPhong {
	@Id
	private String maLoaiPhong;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String tenLoaiPhong;
	@Column(columnDefinition = "money", nullable = false)
	private double giaPhong;
	/**
	 * @param maLoaiPhong
	 * @param tenLoaiPhong
	 * @param giaPhong
	 */
	public LoaiPhong(String maLoaiPhong, String tenLoaiPhong, Double giaPhong) {
		this.maLoaiPhong = maLoaiPhong;
		this.tenLoaiPhong = tenLoaiPhong;
		this.giaPhong = giaPhong;
	}
	/**
	 * 
	 */
	public LoaiPhong() {
	}
	/**
	 * @return the maLoaiPhong
	 */
	public String getMaLoaiPhong() {
		return maLoaiPhong;
	}
	/**
	 * @param maLoaiPhong the maLoaiPhong to set
	 */
	public void setMaLoaiPhong(String maLoaiPhong) {
		this.maLoaiPhong = maLoaiPhong;
	}
	/**
	 * @return the tenLoaiPhong
	 */
	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}
	/**
	 * @param tenLoaiPhong the tenLoaiPhong to set
	 */
	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}
	/**
	 * @return the giaPhong
	 */
	public Double getGiaPhong() {
		return giaPhong;
	}
	/**
	 * @param giaPhong the giaPhong to set
	 */
	public void setGiaPhong(Double giaPhong) {
		this.giaPhong = giaPhong;
	}
	@Override
	public String toString() {
		return "LoaiPhong [maLoaiPhong=" + maLoaiPhong + ", tenLoaiPhong=" + tenLoaiPhong + ", giaPhong=" + giaPhong
				+ "]";
	}
}
