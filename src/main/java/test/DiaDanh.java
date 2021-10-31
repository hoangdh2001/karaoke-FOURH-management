package test;

public class DiaDanh {
	private String maDiaDanh;
	private String tenDiaDanh;
	private String thuocTinh;
	private byte[] anhDiaDanh;
	/**
	 * @return the maDiaDanh
	 */
	public String getMaDiaDanh() {
		return maDiaDanh;
	}
	/**
	 * @param maDiaDanh the maDiaDanh to set
	 */
	public void setMaDiaDanh(String maDiaDanh) {
		this.maDiaDanh = maDiaDanh;
	}
	/**
	 * @return the tenDiaDanh
	 */
	public String getTenDiaDanh() {
		return tenDiaDanh;
	}
	/**
	 * @param tenDiaDanh the tenDiaDanh to set
	 */
	public void setTenDiaDanh(String tenDiaDanh) {
		this.tenDiaDanh = tenDiaDanh;
	}
	/**
	 * @return the thuocTinh
	 */
	public String getThuocTinh() {
		return thuocTinh;
	}
	/**
	 * @param thuocTinh the thuocTinh to set
	 */
	public void setThuocTinh(String thuocTinh) {
		this.thuocTinh = thuocTinh;
	}
	/**
	 * @return the anhDiaDanh
	 */
	public byte[] getAnhDiaDanh() {
		return anhDiaDanh;
	}
	/**
	 * @param anhDiaDanh the anhDiaDanh to set
	 */
	public void setAnhDiaDanh(byte[] anhDiaDanh) {
		this.anhDiaDanh = anhDiaDanh;
	}
	/**
	 * @param maDiaDanh
	 * @param tenDiaDanh
	 * @param thuocTinh
	 * @param anhDiaDanh
	 */
	public DiaDanh(String maDiaDanh, String tenDiaDanh, String thuocTinh, byte[] anhDiaDanh) {
		super();
		setMaDiaDanh(maDiaDanh);
		setTenDiaDanh(tenDiaDanh);
		setThuocTinh(thuocTinh);
		setAnhDiaDanh(anhDiaDanh);
	}
	public DiaDanh() {
		// TODO Auto-generated constructor stub
	}
	
}
