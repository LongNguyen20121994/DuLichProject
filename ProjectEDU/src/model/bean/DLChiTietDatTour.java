package model.bean;

import java.sql.Date;

public class DLChiTietDatTour {
	public String maChiTietDatTour;
	public String maChiTietTour;
	public String hoTen;
	private Date ngaySinh;
	private boolean gioiTinh;
	public String getMaChiTietDatTour() {
		return maChiTietDatTour;
	}
	public void setMaChiTietDatTour(String maChiTietDatTour) {
		this.maChiTietDatTour = maChiTietDatTour;
	}
	public String getMaChiTietTour() {
		return maChiTietTour;
	}
	public void setMaChiTietTour(String maChiTietTour) {
		this.maChiTietTour = maChiTietTour;
	}
	public String getHoTen() {
		return hoTen;
	}
	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}
	public Date getNgaySinh() {
		return ngaySinh;
	}
	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public boolean isGioiTinh() {
		return gioiTinh;
	}
	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
}
