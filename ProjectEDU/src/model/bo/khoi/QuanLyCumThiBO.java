package model.bo.khoi;

import java.util.List;

import model.bean.QuanLyCumThi;
import model.dao.khoi.QuanLyCumThiDAO;

public class QuanLyCumThiBO {

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().doiMatKhau(soCMND,matKhau);
	}

	public QuanLyCumThi getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().getInfo(soCMND);
	}

	public List<QuanLyCumThi> getListQuanLyCumThiByTrangThai(boolean trangThai, boolean logined) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().getListQuanLyCumThiByTrangThai(trangThai, logined);
	}

	public boolean kichHoatTaiKhoan(String[] listSoCMND, boolean trangThai, boolean logined) {
		return new QuanLyCumThiDAO().kichHoatTaiKhoan(listSoCMND, trangThai, logined);
	}

	public boolean xoaTaiKhoan(String[] listSoCMND) {
		return new QuanLyCumThiDAO().xoaTaiKhoan(listSoCMND);
	}

	public boolean quenMatKhau(String soCMND, String email) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().quenMatKhau(soCMND, email);
	}

}
