package model.bo.khoi;

import java.util.List;

import model.bean.QuanTriVien;
import model.dao.khoi.QuanTriVienDAO;

public class QuanTriVienBO {

	public QuanTriVien getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new QuanTriVienDAO().getInfo(soCMND);
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new QuanTriVienDAO().doiMatKhau(soCMND,matKhau);
	}

	public boolean insertQuanTriVien(QuanTriVien qtv) {
		// TODO Auto-generated method stub
		return new QuanTriVienDAO().insertQuanTriVien(qtv);
	}

	public List<QuanTriVien> getListQuanTriVienByTrangThai(boolean trangThai, boolean logined) {
		// TODO Auto-generated method stub
		return new QuanTriVienDAO().getListQuanTriVienByTrangThai(trangThai, logined);
	}

	public boolean kichHoatTaiKhoan(String[] listSoCMND, boolean trangThai, boolean logined) {
		return new QuanTriVienDAO().kichHoatTaiKhoan(listSoCMND, trangThai, logined);
	}

	public boolean xoaTaiKhoan(String[] listSoCMND) {
		return new QuanTriVienDAO().xoaTaiKhoan(listSoCMND);
	}

	public boolean quenMatKhau(String soCMND, String email) {
		// TODO Auto-generated method stub
		return new QuanTriVienDAO().quenMatKhau(soCMND, email);
	}

	public boolean updateQuanTriVien(QuanTriVien qtv) {
		// TODO Auto-generated method stub
		return new QuanTriVienDAO().updateQuanTriVien(qtv);
	}

}
