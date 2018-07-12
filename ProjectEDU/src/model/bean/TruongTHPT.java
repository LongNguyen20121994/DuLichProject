package model.bean;

public class TruongTHPT {
	private String maTruong;
	private String tenTruong;
	private String maTinh;
	private String khuVucUT;
	private String diaChi;

	public String getMaTruong() {
		return maTruong;
	}

	public void setMaTruong(String maTruong) {
		this.maTruong = maTruong;
	}

	public String getTenTruong() {
		return tenTruong;
	}

	public void setTenTruong(String tenTruong) {
		this.tenTruong = tenTruong;
	}

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public String getKhuVucUT() {
		return khuVucUT;
	}

	public void setKhuVucUT(String khuVucUT) {
		this.khuVucUT = khuVucUT;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	@Override
	public String toString() {
		return "TruongTHPT [maTruong=" + maTruong + ", tenTruong=" + tenTruong + ", maTinh=" + maTinh + ", khuVucUT="
				+ khuVucUT + ", diaChi=" + diaChi + "]";
	}
	
}
