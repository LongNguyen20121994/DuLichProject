package model.bo.khoi;

import java.util.List;

import model.bean.GiangVien;
import model.dao.khoi.GiangVienDAO;

public class GiangVienBO {

	public GiangVien getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().getInfo(soCMND);
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().doiMatKhau(soCMND,matKhau);
	}

	public boolean insertGiangVien(GiangVien gv) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().insertGiangVien(gv);
	}

	public List<GiangVien> getListGiangVienByTrangThai(boolean trangThai, boolean logined) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().getListGiangVienByTrangThai(trangThai, logined);
	}

	public boolean kichHoatTaiKhoan(String[] listSoCMND, boolean trangThai, boolean logined) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().kichHoatTaiKhoan(listSoCMND, trangThai, logined);
	}

	public boolean xoaTaiKhoan(String[] listSoCMND) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().xoaTaiKhoan(listSoCMND);
	}

	public boolean quenMatKhau(String soCMND, String email) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().quenMatKhau(soCMND, email);
	}

	public boolean updateGiangVien(GiangVien gv) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().updateGiangVien(gv);
	}

}
