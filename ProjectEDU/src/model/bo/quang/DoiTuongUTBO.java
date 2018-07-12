package model.bo.quang;

import java.util.HashMap;

import model.dao.quang.DoiTuongUTDAO;

public class DoiTuongUTBO {

	private DoiTuongUTDAO doiTuongUTDAO= new DoiTuongUTDAO();
	
	public HashMap<String, String> getAllSelect() {
		// TODO Auto-generated method stub
		return doiTuongUTDAO.getAllSelectLoad();
	}

}
