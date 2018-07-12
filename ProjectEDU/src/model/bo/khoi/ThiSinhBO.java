package model.bo.khoi;

import java.util.List;

import model.bean.ThiSinh;
import model.dao.khoi.ThiSinhDAO;

public class ThiSinhBO {

	public ThiSinh getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new ThiSinhDAO().getInfo(soCMND);
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new ThiSinhDAO().doiMatKhau(soCMND,matKhau);
	}

	public List<ThiSinh> getListThiSinhByTrangThai(boolean trangThai, boolean logined) {
		// TODO Auto-generated method stub
		return new ThiSinhDAO().getListThiSinhByTrangThai(trangThai, logined);
	}

	public boolean kichHoatTaiKhoan(String[] listSoCMND, boolean trangThai, boolean logined) {
		// TODO Auto-generated method stub
		return new ThiSinhDAO().kichHoatTaiKhoan(listSoCMND, trangThai, logined);
	}

	public boolean xoaTaiKhoan(String[] listSoCMND) {
		// TODO Auto-generated method stub
		return new ThiSinhDAO().xoaTaiKhoan(listSoCMND);
	}

	public boolean quenMatKhau(String soCMND, String email) {
		// TODO Auto-generated method stub
		return new ThiSinhDAO().quenMatKhau(soCMND, email);
	}
}
