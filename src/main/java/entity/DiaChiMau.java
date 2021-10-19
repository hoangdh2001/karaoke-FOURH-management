package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DiaChiMau {
	@Id
	private String maDiaChi;
	@Column(columnDefinition = "nvarchar(255)")
	private String xaPhuong;
	@Column(columnDefinition = "nvarchar(255)")
	private String quanHuyen;
	@Column(columnDefinition = "nvarchar(255)")
	private String tinhThanh;
	/**
	 * @param maDiaChi
	 * @param xaPhuong
	 * @param quanHuyen
	 * @param tinhThanh
	 */
	public DiaChiMau(String maDiaChi, String xaPhuong, String quanHuyen, String tinhThanh) {
		this.maDiaChi = maDiaChi;
		this.xaPhuong = xaPhuong;
		this.quanHuyen = quanHuyen;
		this.tinhThanh = tinhThanh;
	}
	/**
	 * 
	 */
	public DiaChiMau() {
	}
	/**
	 * @return the maDiaChi
	 */
	public String getMaDiaChi() {
		return maDiaChi;
	}
	/**
	 * @param maDiaChi the maDiaChi to set
	 */
	public void setMaDiaChi(String maDiaChi) {
		this.maDiaChi = maDiaChi;
	}
	/**
	 * @return the xaPhuong
	 */
	public String getXaPhuong() {
		return xaPhuong;
	}
	/**
	 * @param xaPhuong the xaPhuong to set
	 */
	public void setXaPhuong(String xaPhuong) {
		this.xaPhuong = xaPhuong;
	}
	/**
	 * @return the quanHuyen
	 */
	public String getQuanHuyen() {
		return quanHuyen;
	}
	/**
	 * @param quanHuyen the quanHuyen to set
	 */
	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}
	/**
	 * @return the tinhThanh
	 */
	public String getTinhThanh() {
		return tinhThanh;
	}
	/**
	 * @param tinhThanh the tinhThanh to set
	 */
	public void setTinhThanh(String tinhThanh) {
		this.tinhThanh = tinhThanh;
	}
	@Override
	public String toString() {
		return "DiaChi [maDiaChi=" + maDiaChi + ", xaPhuong=" + xaPhuong + ", quanHuyen=" + quanHuyen + ", tinhThanh="
				+ tinhThanh + "]";
	}
}
