package model.bo.longnt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.bean.ChiTietDKDT;
import model.bean.MonThi;
import model.dao.longnt.ChiTietDKDTDAO;

public class ChiTietDKDTBO {

	ChiTietDKDTDAO chiTietDKDTDAO = new ChiTietDKDTDAO();
	
	public boolean validMonThi(String soCMND, int namTS, String maMon) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.validMonThi(soCMND,namTS,maMon);
	}

	public ArrayList<MonThi> getAllMonThi(String soCMND, int namTS) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.getAllMonThi(soCMND,namTS);
	}

	public boolean deleteListMonDKDT(String soCMND, int namTS, String[] listMT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.deleteListMonDKDT(soCMND,namTS,listMT);
	}

	public boolean insertChiTietDKDT(ChiTietDKDT ctDKDT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.insertChiTietDKDT(ctDKDT);
	}
	
	public boolean updateChiTietDKDT(ChiTietDKDT ctDKDT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.updateChiTietDKDT(ctDKDT);
	}

	public String themListChiTietDKDT(ArrayList<ChiTietDKDT> list) {
		ArrayList<ChiTietDKDT> insert = new ArrayList<ChiTietDKDT>();
		for (ChiTietDKDT i : list) {
			if (i.getSoCMND() != null && !i.getSoCMND().isEmpty())
				insert.add(i);
		}

		return chiTietDKDTDAO.themListChiTietDKDT(insert);
	}
	
	public HashMap<String, String> getAllSelect() {
		return new ChiTietDKDTDAO().getAllSelect();
	}
	
	public String updateListCTDKDT(ArrayList<ChiTietDKDT> listCTDKDT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.updateListCTDKDT(listCTDKDT);
	}
	
	public String themListCTDKDT(ArrayList<ChiTietDKDT> listCTDKDT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.themListCTDKDT(listCTDKDT);
	}

	public List<ChiTietDKDT> getListInfoChiTietDKDT(String soCMND) {
		return chiTietDKDTDAO.getListInfoChiTietDKDT(soCMND);
	}
}
