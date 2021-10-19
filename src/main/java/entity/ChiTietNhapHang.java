package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(ChiTietNhapHang_PK.class)
public class ChiTietNhapHang {
	@Id
	@ManyToOne
	@JoinColumn(name = "maLoHang", nullable = false)
	private LoHang loHang;
	@Id
	@ManyToOne
	@JoinColumn(name = "maMatHang", nullable = false)
	private MatHang matHang;
	@Column(nullable = false)
	private int soLuongNhap;
	@Column(columnDefinition = "money", nullable = false)
	private double giaNhap;
	@Column(columnDefinition = "money")
	private double thanhTien;
	/**
	 * @param loHang
	 * @param matHang
	 * @param soLuongNhap
	 * @param giaNhap
	 */
	public ChiTietNhapHang(LoHang loHang, MatHang matHang, int soLuongNhap, double giaNhap) {
		this.loHang = loHang;
		this.matHang = matHang;
		this.soLuongNhap = soLuongNhap;
		this.giaNhap = giaNhap;
	}
	/**
	 * 
	 */
	public ChiTietNhapHang() {
	}
	/**
	 * @return the loHang
	 */
	public LoHang getLoHang() {
		return loHang;
	}
	/**
	 * @param loHang the loHang to set
	 */
	public void setLoHang(LoHang loHang) {
		this.loHang = loHang;
	}
	/**
	 * @return the matHang
	 */
	public MatHang getMatHang() {
		return matHang;
	}
	/**
	 * @param matHang the matHang to set
	 */
	public void setMatHang(MatHang matHang) {
		this.matHang = matHang;
	}
	/**
	 * @return the soLuongNhap
	 */
	public int getSoLuongNhap() {
		return soLuongNhap;
	}
	/**
	 * @param soLuongNhap the soLuongNhap to set
	 */
	public void setSoLuongNhap(int soLuongNhap) {
		this.soLuongNhap = soLuongNhap;
	}
	/**
	 * @return the giaNhap
	 */
	public double getGiaNhap() {
		return giaNhap;
	}
	/**
	 * @param giaNhap the giaNhap to set
	 */
	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}
	/**
	 * @return the thanhTien
	 */
	public double getThanhTien() {
		return giaNhap * soLuongNhap;
	}
	@Override
	public String toString() {
		return "ChiTietNhapHang [loHang=" + loHang + ", matHang=" + matHang + ", soLuongNhap=" + soLuongNhap
				+ ", giaNhap=" + giaNhap + ", thanhTien=" + thanhTien + "]";
	}
}
