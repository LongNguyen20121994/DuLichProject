package model.bo.khoi;

import java.util.HashMap;
import java.util.List;

import model.bean.MonThi;
import model.dao.khoi.MonThiDAO;

public class MonThiBO {

	public boolean insertMonThi(MonThi mt) {
		return new MonThiDAO().insertMonThi(mt);
	}

	public HashMap<String, String> getAllSelect() {
		return new MonThiDAO().getAllSelect();
	}

	public boolean updateMonThi(MonThi mt) {
		return new MonThiDAO().updateMonThi(mt);
	}

	public List<MonThi> getAll() {
		return new MonThiDAO().getAll();
	}

	public boolean deleteListMonThi(List<String> listMaMT) {
		return new MonThiDAO().deleteListMonThi(listMaMT);
	}
}
