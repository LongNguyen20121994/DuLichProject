package model.bo.quang;

import java.util.Map;

import model.bean.GiangVien;
import model.dao.quang.GiangVienDAO;

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

	public Map<String, Object> getInfoHienThi(String soCMND) {
		// TODO Auto-generated method stub
		return new GiangVienDAO().getInfoHienThi(soCMND);
	}

}
