package model.bean;

import java.sql.Date;

public class DLChiTietTour {
	private String maTour;
	private Date ngayKhoiHanh;
	private String dacDiem;
	private double giaVe;
	private int soCho;
	private int soChoDaDat;
	
	public String getMaTour() {
		return maTour;
	}
	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}
	public Date getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}
	public void setNgayKhoiHanh(Date ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}
	public String getDacDiem() {
		return dacDiem;
	}
	public void setDacDiem(String dacDiem) {
		this.dacDiem = dacDiem;
	}
	public double getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(double giaVe) {
		this.giaVe = giaVe;
	}
	public int getSoCho() {
		return soCho;
	}
	public void setSoCho(int soCho) {
		this.soCho = soCho;
	}
	public int getSoChoDaDat() {
		return soChoDaDat;
	}
	public void setSoChoDaDat(int soChoDaDat) {
		this.soChoDaDat = soChoDaDat;
	}
}
