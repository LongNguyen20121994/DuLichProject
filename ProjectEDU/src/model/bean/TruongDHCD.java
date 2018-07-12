package model.bean;

public class TruongDHCD {
	private String maTruong;
	private String tenTruong;
	private String diaChi;
	private String ghiChu;

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

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "TruongDHCD [maTruong=" + maTruong + ", tenTruong=" + tenTruong + ", diaChi=" + diaChi + ", ghiChu="
				+ ghiChu + "]";
	}

}
