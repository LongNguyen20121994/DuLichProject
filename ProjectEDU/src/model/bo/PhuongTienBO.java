package model.bo;

import java.util.HashMap;
import java.util.List;

import model.bean.DLPhuongTien;
import model.dao.PhuongTienDAO;

public class PhuongTienBO {

	public boolean insertPhuongTien(DLPhuongTien mt) {
		return new PhuongTienDAO().insertPhuongTien(mt);
	}

	public HashMap<String, String> getAllSelect() {
		return new PhuongTienDAO().getAllSelect();
	}

	public boolean updatePhuongTien(DLPhuongTien mt) {
		return new PhuongTienDAO().updatePhuongTien(mt);
	}

	public List<DLPhuongTien> getAll() {
		return new PhuongTienDAO().getAll();
	}

	public boolean deleteListPhuongTien(List<String> listMaMT) {
		return new PhuongTienDAO().deleteListPhuongTien(listMaMT);
	}
	
	public String getMaxRecord() {
		return new PhuongTienDAO().getMaxRecord();
	}
}
