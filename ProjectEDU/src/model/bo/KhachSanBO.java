package model.bo;

import java.util.HashMap;

import model.dao.KhachSanDAO;

public class KhachSanBO {

	public HashMap<String, String> getAllSelect() {
		return new KhachSanDAO().getAllSelect();
	}
}
