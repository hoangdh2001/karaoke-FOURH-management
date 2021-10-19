package entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DiaChi {
	private String soNha;
	@Column(columnDefinition = "nvarchar(255)")
	private String tenDuong;
	@Column(columnDefinition = "nvarchar(255)")
	private String xaPhuong;
	@Column(columnDefinition = "nvarchar(255)")
	private String quanHuyen;
	@Column(columnDefinition = "nvarchar(255)")
	private String tinhThanh;
	/**
	 * @param soNha
	 * @param tenDuong
	 * @param xaPhuong
	 * @param quanHuyen
	 * @param tinhThanh
	 */
	public DiaChi(String soNha, String tenDuong, String xaPhuong, String quanHuyen, String tinhThanh) {
		this.soNha = soNha;
		this.tenDuong = tenDuong;
		this.xaPhuong = xaPhuong;
		this.quanHuyen = quanHuyen;
		this.tinhThanh = tinhThanh;
	}
	/**
	 * 
	 */
	public DiaChi() {
	}
	/**
	 * @return the soNha
	 */
	public String getSoNha() {
		return soNha;
	}
	/**
	 * @param soNha the soNha to set
	 */
	public void setSoNha(String soNha) {
		this.soNha = soNha;
	}
	/**
	 * @return the tenDuong
	 */
	public String getTenDuong() {
		return tenDuong;
	}
	/**
	 * @param tenDuong the tenDuong to set
	 */
	public void setTenDuong(String tenDuong) {
		this.tenDuong = tenDuong;
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
		return "DiaChi [soNha=" + soNha + ", tenDuong=" + tenDuong + ", xaPhuong=" + xaPhuong + ", quanHuyen="
				+ quanHuyen + ", tinhThanh=" + tinhThanh + "]";
	}
}
