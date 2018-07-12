package model.bean;

public class HuyenQuan {
	private String maTinh;
	private String maHuyen;
	private String tenHuyen;

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public String getMaHuyen() {
		return maHuyen;
	}

	public void setMaHuyen(String maHuyen) {
		this.maHuyen = maHuyen;
	}

	public String getTenHuyen() {
		return tenHuyen;
	}

	public void setTenHuyen(String tenHuyen) {
		this.tenHuyen = tenHuyen;
	}

	@Override
	public String toString() {
		return "HuyenQuan [maTinh=" + maTinh + ", maHuyen=" + maHuyen + ", tenHuyen=" + tenHuyen + "]";
	}

}
