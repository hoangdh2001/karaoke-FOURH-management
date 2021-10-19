package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class ChiTietNhapHang_PK implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String loHang;
	private String matHang;
	/**
	 * 
	 */
	public ChiTietNhapHang_PK() {
		
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((loHang == null) ? 0 : loHang.hashCode());
		result = prime * result + ((matHang == null) ? 0 : matHang.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChiTietNhapHang_PK other = (ChiTietNhapHang_PK) obj;
		if (loHang == null) {
			if (other.loHang != null)
				return false;
		} else if (!loHang.equals(other.loHang))
			return false;
		if (matHang == null) {
			if (other.matHang != null)
				return false;
		} else if (!matHang.equals(other.matHang))
			return false;
		return true;
	}
}
