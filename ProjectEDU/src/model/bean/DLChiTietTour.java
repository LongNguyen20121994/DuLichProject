package model.bean;

import java.sql.Date;

public class DLChiTietTour {
	private String maChiTietTour;
	private String maTour;
	private String maKS;
	private Date ngayKhoiHanh;
	private String dacDiem;
	private double giaVeNguoiLon;
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
	public String getGiaVeNguoiLon() {
		String stringGiaVe = giaVeNguoiLon + "";
		return stringGiaVe.substring(0,stringGiaVe.length()-2) + " VND";
	}
	public void setGiaVeNguoiLon(double giaVeNguoiLon) {
		this.giaVeNguoiLon = giaVeNguoiLon;
	}
	public String getGiaVeTreEm() {
		return Math.round((this.giaVeNguoiLon * 0.7)*10)/10 + " VND";
	}
	public String getGiaVeTreNho() {
		return Math.round((this.giaVeNguoiLon * 0.5)*10)/10 + " VND";
	}
	public String getGiaVeSoSinh() {
		return "200000" + " VND";
	}
}
