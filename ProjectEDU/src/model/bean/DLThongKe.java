package model.bean;

import java.sql.Date;

public class DLThongKe {
	private String maTour;
	private String tieuDe;
	private Date ngayKhoiHanh;
	public String getMaTour() {
		return maTour;
	}
	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}
	public String getTieuDe() {
		return tieuDe;
	}
	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}
	public Date getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}
	public void setNgayKhoiHanh(Date ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}
}
