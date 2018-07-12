package model.bo;

import model.bean.DLNhanVien;
import model.dao.DLNhanVienDAO;

public class DLNhanVienBO {
	
	public DLNhanVien getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new DLNhanVienDAO().getInfo(soCMND);
	}
	
	public boolean insertNhanVien(DLNhanVien nv) {
		// TODO Auto-generated method stub
		return new DLNhanVienDAO().insertNhanVien(nv);
	}
	
	public boolean updateNhanVien(DLNhanVien nv) {
		// TODO Auto-generated method stub
		return new DLNhanVienDAO().updateNhanVien(nv);
	}
}
