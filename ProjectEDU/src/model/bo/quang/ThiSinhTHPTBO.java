package model.bo.quang;

import java.util.ArrayList;

import model.bean.ThiSinhTHPT;
import model.dao.quang.ThiSinhTHPTDAO;

public class ThiSinhTHPTBO {
	private ThiSinhTHPTDAO thiSinhTHPTDAO = new ThiSinhTHPTDAO();

	public ArrayList<ThiSinhTHPT> getListUpdate(String soCMND) {
		// TODO Auto-generated method stub
		return thiSinhTHPTDAO.getListUpdate(soCMND);
	}

	public boolean updateInfo(ArrayList<ThiSinhTHPT> listTSTHPT) {
		// TODO Auto-generated method stub
		return thiSinhTHPTDAO.updateInfo(listTSTHPT);
	}

	public boolean isComplete(String soCMND) {
		return thiSinhTHPTDAO.isComplete(soCMND);
	}

	public String themListThiSinhTHPT(ArrayList<ThiSinhTHPT> list) {
		// TODO Auto-generated method stub
		return thiSinhTHPTDAO.themListThiSinhTHPT(list);
	}

}
