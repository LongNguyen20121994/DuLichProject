package model.bean;

public class DLTourTrangChu {
	private String maTour;
	private String tieuDe;
	private String hinhAnh;
	private String maLoai;
	private String soNgayDem;
	private String giaVe;
	private double giaTien;
	private String ngayKhoiHanh;
	private String vanChuyen;
	private String khachSan;
	private int soNguoiLon;
	private int soTreEm;
	private int soTreNho;
	private int soSoSinh;
	private String maKH;
	private String diaDiem;
	private boolean ptThanhToan;
	public boolean getPtThanhToan() {
		return ptThanhToan;
	}
	public void setPtThanhToan(boolean ptThanhToan) {
		this.ptThanhToan = ptThanhToan;
	}
	public String getDiaDiem() {
		return diaDiem;
	}
	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}
	public double getGiaTien() {
		return giaTien;
	}
	public void setGiaTien(double giaTien) {
		this.giaTien = Double.parseDouble(giaVe.substring(0,giaVe.length()-4));
	}
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
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getSoNgayDem() {
		return soNgayDem;
	}
	public void setSoNgayDem(String soNgayDem) {
		this.soNgayDem = soNgayDem;
	}
	public String getGiaVe() {
		return giaVe;
	}
	public void setGiaVe(String giaVe) {
		this.giaVe = giaVe;
	}
	public String getNgayKhoiHanh() {
		return ngayKhoiHanh;
	}
	public void setNgayKhoiHanh(String ngayKhoiHanh) {
		this.ngayKhoiHanh = ngayKhoiHanh;
	}
	public String getVanChuyen() {
		return vanChuyen;
	}
	public void setVanChuyen(String vanChuyen) {
		this.vanChuyen = vanChuyen;
	}
	public String getKhachSan() {
		return khachSan;
	}
	public void setKhachSan(String khachSan) {
		this.khachSan = khachSan;
	}
	public int getSoNguoiLon() {
		return soNguoiLon;
	}
	public void setSoNguoiLon(int soNguoiLon) {
		this.soNguoiLon = soNguoiLon;
	}
	public int getSoTreEm() {
		return soTreEm;
	}
	public void setSoTreEm(int soTreEm) {
		this.soTreEm = soTreEm;
	}
	public int getSoTreNho() {
		return soTreNho;
	}
	public void setSoTreNho(int soTreNho) {
		this.soTreNho = soTreNho;
	}
	public int getSoSoSinh() {
		return soSoSinh;
	}
	public void setSoSoSinh(int soSoSinh) {
		this.soSoSinh = soSoSinh;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
}
