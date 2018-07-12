package model.bo.quang;

import java.util.HashMap;

import model.dao.quang.CumThiDAO;

public class CumThiBO {

	private CumThiDAO ctDAO = new CumThiDAO();
	
	public HashMap<String, String> getAllSelect() {
		// TODO Auto-generated method stub
		return ctDAO.getAllSelect();
	}
}
