package model.bean;

public class KhoiThiNganhDHCD {
	private String maNganh;
	private String maTruong;
	private String daoTao;
	private String maKhoi;
	private int NamTuyenSinh;
	private double diemChuan;

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

	public String getMaKhoi() {
		return maKhoi;
	}

	public void setMaKhoi(String maKhoi) {
		this.maKhoi = maKhoi;
	}

	public int getNamTuyenSinh() {
		return NamTuyenSinh;
	}

	public void setNamTuyenSinh(int namTuyenSinh) {
		NamTuyenSinh = namTuyenSinh;
	}

	public double getDiemChuan() {
		return diemChuan;
	}

	public void setDiemChuan(double diemChuan) {
		this.diemChuan = diemChuan;
	}

	@Override
	public String toString() {
		return "KhoiThiNganhDHCD [maNganh=" + maNganh + ", maTruong=" + maTruong + ", daoTao=" + daoTao + ", maKhoi="
				+ maKhoi + ", NamTuyenSinh=" + NamTuyenSinh + ", diemChuan=" + diemChuan + "]";
	}
	
}
