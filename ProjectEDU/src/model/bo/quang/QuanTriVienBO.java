package model.bo.quang;

import java.util.Map;

import model.bean.QuanTriVien;
import model.dao.quang.QuanTriVienDAO;

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

	public Map<String, Object> getInfoHienThi(String soCMND) {
		// TODO Auto-generated method stub
		return new QuanTriVienDAO().getInfoHienThi(soCMND);
	}

}
