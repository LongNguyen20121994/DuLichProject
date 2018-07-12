package model.bean;

public class DLTinh {
	private String maTinh;
	private String tenTinh;

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public String getTenTinh() {
		return tenTinh;
	}

	public void setTenTinh(String tenTinh) {
		this.tenTinh = tenTinh;
	}

	@Override
	public String toString() {
		return "TinhThanhPho [maTinh=" + maTinh + ", tenTinh=" + tenTinh + "]";
	}
}
