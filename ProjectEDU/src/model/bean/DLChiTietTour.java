package model.bean;

import java.sql.Date;

public class DLChiTietTour {
	private String maChiTietTour;
	private String maTour;
	private String maKS;
	private Date ngayKhoiHanh;
	private String dacDiem;
	private double giaVeNguoiLon;
	private double giaVeTreEm;
	private double giaVeTreNho;
	private double giaVeSoSinh;
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
	public String getMaChiTietTour() {
		return maChiTietTour;
	}
	public void setMaChiTietTour(String maChiTietTour) {
		this.maChiTietTour = maChiTietTour;
	}
	public String getMaKS() {
		return maKS;
	}
	public void setMaKS(String maKS) {
		this.maKS = maKS;
	}
	public double getGiaVeNguoiLon() {
		return giaVeNguoiLon;
	}
	public void setGiaVeNguoiLon(double giaVeNguoiLon) {
		this.giaVeNguoiLon = giaVeNguoiLon;
	}
	public double getGiaVeTreEm() {
		return this.giaVeNguoiLon * 0.7;
	}
	public double getGiaVeTreNho() {
		return this.giaVeNguoiLon * 0.5;
	}
	public double getGiaVeSoSinh() {
		return 200;
	}
}
