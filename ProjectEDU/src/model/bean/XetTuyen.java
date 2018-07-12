package model.bean;

public class XetTuyen {
	private String maNganh;
	private String maTruong;
	private String daoTao;
	private String maMonThi;
	private String maKhoi;
	private int heSo;
	private int namTuyenSinh;
	
	public int getNamTuyenSinh() {
		return namTuyenSinh;
	}

	public void setNamTuyenSinh(int namTuyenSinh) {
		this.namTuyenSinh = namTuyenSinh;
	}

	public String getMaNganh() {
		return maNganh;
	}

	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}

	public String getMaTruong() {
		return maTruong;
	}

	public void setMaTruong(String maTruong) {
		this.maTruong = maTruong;
	}

	public String getDaoTao() {
		return daoTao;
	}

	public void setDaoTao(String daoTao) {
		this.daoTao = daoTao;
	}

	public String getMaMonThi() {
		return maMonThi;
	}

	public void setMaMonThi(String maMonThi) {
		this.maMonThi = maMonThi;
	}

	public String getMaKhoi() {
		return maKhoi;
	}

	public void setMaKhoi(String maKhoi) {
		this.maKhoi = maKhoi;
	}

	public int getHeSo() {
		return heSo;
	}

	public void setHeSo(int heSo) {
		this.heSo = heSo;
	}

	@Override
	public String toString() {
		return "XetTuyen [maNganh=" + maNganh + ", maTruong=" + maTruong + ", daoTao=" + daoTao + ", maMonThi="
				+ maMonThi + ", maKhoi=" + maKhoi + ", heSo=" + heSo + ", namTuyenSinh=" + namTuyenSinh + "]";
	}

}
