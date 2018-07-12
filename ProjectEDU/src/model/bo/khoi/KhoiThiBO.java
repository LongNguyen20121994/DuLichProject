package model.bo.khoi;

import java.util.HashMap;
import java.util.List;

import model.bean.KhoiThi;
import model.dao.khoi.KhoiThiDAO;

public class KhoiThiBO {

	public boolean insertKhoiThi(KhoiThi kt) {
		return new KhoiThiDAO().insertKhoiThi(kt);
	}

	public HashMap<String, String> getAllSelect() {
		return new KhoiThiDAO().getAllSelect();
	}

	public boolean updateKhoiThi(KhoiThi kt) {
		return new KhoiThiDAO().updateKhoiThi(kt);
	}

	public List<KhoiThi> getAll() {
		return new KhoiThiDAO().getAll();
	}

	public boolean deleteListKhoiThi(List<String> listMaKT) {
		return new KhoiThiDAO().deleteListKhoiThi(listMaKT);
	}
}
