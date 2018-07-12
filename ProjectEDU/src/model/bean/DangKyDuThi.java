package model.bean;

import java.sql.Date;

public class DangKyDuThi {
	private String maDKDT;
	private String soCMND;
	private String maCumThi;
	private String soBD;
	private Date ngayDK;
	private int namTS;

	public int getNamTS() {
		return namTS;
	}

	public void setNamTS(int namTS) {
		this.namTS = namTS;
	}

	public String getMaDKDT() {
		return maDKDT;
	}

	public void setMaDKDT(String maDKDT) {
		this.maDKDT = maDKDT;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getMaCumThi() {
		return maCumThi;
	}

	public void setMaCumThi(String maCumThi) {
		this.maCumThi = maCumThi;
	}

	public String getSoBD() {
		return soBD;
	}

	public void setSoBD(String soBD) {
		this.soBD = soBD;
	}

	public Date getNgayDK() {
		return ngayDK;
	}

	public void setNgayDK(Date ngayDK) {
		this.ngayDK = ngayDK;
	}

}
