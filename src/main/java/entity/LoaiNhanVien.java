package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@NamedQueries({
    @NamedQuery(name = "getLoaiNhanViens", query = "select lnv from LoaiNhanVien lnv")
})
public class LoaiNhanVien {
	@Id
	private String maLoaiNV;
	@Column(columnDefinition = "nvarchar(255)", nullable = false)
	private String tenLoaiNV;
	/**
	 * @param maLoaiNV
	 * @param tenLoaiNV
	 */
	public LoaiNhanVien(String maLoaiNV, String tenLoaiNV) {
		this.maLoaiNV = maLoaiNV;
		this.tenLoaiNV = tenLoaiNV;
	}
	/**
	 * 
	 */
	public LoaiNhanVien() {
	}
	/**
	 * @return the maLoaiNV
	 */
	public String getMaLoaiNV() {
		return maLoaiNV;
	}
	/**
	 * @param maLoaiNV the maLoaiNV to set
	 */
	public void setMaLoaiNV(String maLoaiNV) {
		this.maLoaiNV = maLoaiNV;
	}
	/**
	 * @return the tenLoaiNV
	 */
	public String getTenLoaiNV() {
		return tenLoaiNV;
	}
	/**
	 * @param tenLoaiNV the tenLoaiNV to set
	 */
	public void setTenLoaiNV(String tenLoaiNV) {
		this.tenLoaiNV = tenLoaiNV;
	}
	@Override
	public String toString() {
		return tenLoaiNV;
	}
}
