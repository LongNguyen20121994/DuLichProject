package model.bean;

import java.sql.Date;

public class GiaoVien {
	private String soCMND;
	private String hoTen;
	private boolean gioiTinh;
	private Date ngaySinh;
	private String matKhau;
	private String maTinh, maXa, maHuyen;
	private String tenTinh, tenXa, tenHuyen;
	private String email;
	private String soDT;
	private String maTruongTHPT,tenTruongTHPT;
	private String maTinhTHPT,tenTinhTHPT;
	private String hinhAnh;
	private boolean logined;
	private boolean trangThai;

	public String getTenTruongTHPT() {
		return tenTruongTHPT;
	}

	public void setTenTruongTHPT(String tenTruongTHPT) {
		this.tenTruongTHPT = tenTruongTHPT;
	}

	public String getTenTinhTHPT() {
		return tenTinhTHPT;
	}

	public void setTenTinhTHPT(String tenTinhTHPT) {
		this.tenTinhTHPT = tenTinhTHPT;
	}

	public String getTenTinh() {
		return tenTinh;
	}

	public void setTenTinh(String tenTinh) {
		this.tenTinh = tenTinh;
	}

	public String getTenXa() {
		return tenXa;
	}

	public void setTenXa(String tenXa) {
		this.tenXa = tenXa;
	}

	public String getTenHuyen() {
		return tenHuyen;
	}

	public void setTenHuyen(String tenHuyen) {
		this.tenHuyen = tenHuyen;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public String getMaXa() {
		return maXa;
	}

	public void setMaXa(String maXa) {
		this.maXa = maXa;
	}

	public String getMaHuyen() {
		return maHuyen;
	}

	public void setMaHuyen(String maHuyen) {
		this.maHuyen = maHuyen;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDT() {
		return soDT;
	}

	public void setSoDT(String soDT) {
		this.soDT = soDT;
	}

	public String getMaTruongTHPT() {
		return maTruongTHPT;
	}

	public void setMaTruongTHPT(String maTruongTHPT) {
		this.maTruongTHPT = maTruongTHPT;
	}

	public String getMaTinhTHPT() {
		return maTinhTHPT;
	}

	public void setMaTinhTHPT(String maTinhTHPT) {
		this.maTinhTHPT = maTinhTHPT;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

}
