package model.bean;

public class ChiTietDKDT {
	private String maDKDT;
	private String iDPhong;
	private String maMonThi;
	private double diemThi;
	private int namTS;
	private String soCMND;
	
	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

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

	public String getIDPhong() {
		return iDPhong;
	}

	public void setIDPhong(String iDPhong) {
		this.iDPhong = iDPhong;
	}

	public String getMaMonThi() {
		return maMonThi;
	}

	public void setMaMonThi(String maMonThi) {
		this.maMonThi = maMonThi;
	}

	public double getDiemThi() {
		return diemThi;
	}

	public void setDiemThi(double diemThi) {
		this.diemThi = diemThi;
	}
}
