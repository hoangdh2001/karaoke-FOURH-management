package entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author Đỗ Huy Hoàng
 */
@Embeddable
public class ChiTietHoaDon_PK implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String hoaDon;
    private String matHang;

    /**
     *
     */
    public ChiTietHoaDon_PK() {
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((hoaDon == null) ? 0 : hoaDon.hashCode());
        result = prime * result + ((matHang == null) ? 0 : matHang.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ChiTietHoaDon_PK other = (ChiTietHoaDon_PK) obj;
        if (hoaDon == null) {
            if (other.hoaDon != null) {
                return false;
            }
        } else if (!hoaDon.equals(other.hoaDon)) {
            return false;
        }
        if (matHang == null) {
            if (other.matHang != null) {
                return false;
            }
        } else if (!matHang.equals(other.matHang)) {
            return false;
        }
        return true;
    }
}
