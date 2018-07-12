package model.bo.quang;

import java.util.HashMap;

import model.dao.quang.DanTocDAO;

public class DanTocBO {

	private DanTocDAO danTocDAO = new DanTocDAO();
	
	public HashMap<String, String> getAllSelect() {
		// TODO Auto-generated method stub
		return danTocDAO.getAllSelectLoad();
	}

}
