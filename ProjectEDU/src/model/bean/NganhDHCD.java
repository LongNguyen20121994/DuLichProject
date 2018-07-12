package model.bean;

public class NganhDHCD {
	private String maNganh;
	private String maTruong;
	private String daoTao;
	private int chiTieu;
	private String ghiChu;
	
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

	public int getChiTieu() {
		return chiTieu;
	}

	public void setChiTieu(int chiTieu) {
		this.chiTieu = chiTieu;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "NganhDHCD [maNganh=" + maNganh + ", maTruong=" + maTruong + ", daoTao=" + daoTao + ", chiTieu="
				+ chiTieu + ", ghiChu=" + ghiChu + "]";
	}
	
}
