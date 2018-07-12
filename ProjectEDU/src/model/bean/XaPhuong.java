package model.bean;

public class XaPhuong {
	private String maXa;
	private String maHuyen;
	private String maTinh;
	private String tenXa;

	public String getMaXa() {
		return maXa;
	}

	public void setMaXa(String maXa) {
		this.maXa = maXa;
	}

	public String getMaHuyen() {
		return maHuyen;
	}

	public void setMaHuyen(String maHuyen) {
		this.maHuyen = maHuyen;
	}

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public String getTenXa() {
		return tenXa;
	}

	public void setTenXa(String tenXa) {
		this.tenXa = tenXa;
	}

	@Override
	public String toString() {
		return "XaPhuong [maXa=" + maXa + ", maHuyen=" + maHuyen + ", maTinh=" + maTinh + ", tenXa=" + tenXa + "]";
	}
	
}
