package model.bo.quang;

import java.util.HashMap;

import model.bean.MonThi;
import model.dao.quang.MonThiDAO;

public class MonThiBO {

	public boolean insertMonThi(MonThi mt) {
		// TODO Auto-generated method stub
		return new MonThiDAO().insertMonThi(mt);
	}

	public HashMap<String, String> getAllSelect() {
		// TODO Auto-generated method stub
		return new MonThiDAO().getAllSelect();
	}

}
