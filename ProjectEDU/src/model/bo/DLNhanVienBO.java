package model.bo;

import java.util.List;

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

	public boolean deleteListNhanVien(List<String> listMaNV) {
		return new DLNhanVienDAO().deleteListNhanVien(listMaNV);
	}

	public List<DLNhanVien> getAll() {
		return new DLNhanVienDAO().getAll();
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new DLNhanVienDAO().doiMatKhau(soCMND,matKhau);
	}

	public boolean quenMatKhau(String soCMND, String email) {
		// TODO Auto-generated method stub
		return new DLNhanVienDAO().quenMatKhau(soCMND, email);
	}
}
