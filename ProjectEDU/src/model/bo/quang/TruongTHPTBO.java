package model.bo.quang;

import java.util.HashMap;

import model.dao.quang.TruongTHPTDAO;

public class TruongTHPTBO {
 
	private TruongTHPTDAO truongTHPTDAO =new TruongTHPTDAO();
	
	public HashMap<String, String> getAllSelect() {
		// TODO Auto-generated method stub
		return truongTHPTDAO.getAllSelect() ;
	}

	public HashMap<String, String> getListTHPTSelect(String maTinh) {
		// TODO Auto-generated method stub
		return truongTHPTDAO.getListTHPTSelect(maTinh);
	}

}
