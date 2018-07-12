package model.bo.khoi;

import java.util.List;

import model.bean.GiaoVien;
import model.dao.khoi.GiaoVienDAO;

public class GiaoVienBO {

	public GiaoVien getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new GiaoVienDAO().getInfo(soCMND);
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new GiaoVienDAO().doiMatKhau(soCMND,matKhau);
	}

	public List<GiaoVien> getListGiaoVienByTrangThai(boolean trangThai, boolean logined) {
		// TODO Auto-generated method stub
		return new GiaoVienDAO().getListGiaoVienByTrangThai(trangThai, logined);
	}

	public boolean kichHoatTaiKhoan(String[] listSoCMND, boolean trangThai, boolean logined) {
		// TODO Auto-generated method stub
		return new GiaoVienDAO().kichHoatTaiKhoan(listSoCMND, trangThai, logined);
	}

	public boolean xoaTaiKhoan(String[] listSoCMND) {
		// TODO Auto-generated method stub
		return new GiaoVienDAO().xoaTaiKhoan(listSoCMND);
	}

	public boolean quenMatKhau(String soCMND, String email) {
		// TODO Auto-generated method stub
		return new GiaoVienDAO().quenMatKhau(soCMND, email);
	}

}
