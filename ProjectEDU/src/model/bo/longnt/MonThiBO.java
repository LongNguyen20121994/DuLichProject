package model.bo.longnt;

import java.util.HashMap;

import model.dao.longnt.MonThiDAO;

public class MonThiBO {

	public HashMap<String, String> getAllSelect() {
		return new MonThiDAO().getAllSelect();
	}

}
