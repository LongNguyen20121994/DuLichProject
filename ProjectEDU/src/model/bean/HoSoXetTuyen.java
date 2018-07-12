package model.bean;

import java.util.Date;

public class HoSoXetTuyen {
	private String maHS;
	private String soCMND;
	private int namTS;
	private String maDotXT;
	private String maTruong;
	private Date ngayNop;
	private boolean trangThai;

	public String getMaHS() {
		return maHS;
	}

	public void setMaHS(String maHS) {
		this.maHS = maHS;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public int getNamTS() {
		return namTS;
	}

	public void setNamTS(int namTS) {
		this.namTS = namTS;
	}

	public String getMaDotXT() {
		return maDotXT;
	}

	public void setMaDotXT(String maDotXT) {
		this.maDotXT = maDotXT;
	}

	public String getMaTruong() {
		return maTruong;
	}

	public void setMaTruong(String maTruong) {
		this.maTruong = maTruong;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public Date getNgayNop() {
		return ngayNop;
	}

	public void setNgayNop(Date ngayNop) {
		this.ngayNop = ngayNop;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public String toString() {
		return "HoSoXetTuyen [maHS=" + maHS + ", soCMND=" + soCMND + ", namTS=" + namTS + ", maDotXT=" + maDotXT
				+ ", maTruong=" + maTruong + ", ngayNop=" + ngayNop + "]";
	}
	
}
