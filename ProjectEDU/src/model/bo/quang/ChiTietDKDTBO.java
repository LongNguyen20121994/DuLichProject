package model.bo.quang;

import java.util.ArrayList;

import model.bean.ChiTietDKDT;
import model.bean.DangKyDuThi;
import model.bean.MonThi;
import model.dao.quang.ChiTietDKDTDAO;

public class ChiTietDKDTBO {

	ChiTietDKDTDAO chiTietDKDTDAO = new ChiTietDKDTDAO();

	public boolean validMonThi(String soCMND, int namTS, String maMon) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.validMonThi(soCMND, namTS, maMon);
	}

	public ArrayList<MonThi> getAllMonThi(String soCMND, int namTS) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.getAllMonThi(soCMND, namTS);
	}

	public boolean deleteListMonDKDT(String soCMND, int namTS, String[] listMT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.deleteListMonDKDT(soCMND, namTS, listMT);
	}

	public boolean insertChiTietDKDT(DangKyDuThi dkDT, ChiTietDKDT ctDKDT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.insertChiTietDKDT(dkDT, ctDKDT);
	}

	public String themListCTDKDT(ArrayList<ChiTietDKDT> listCTDKDT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.themListCTDKDT(listCTDKDT);
	}

	public String updateListCTDKDT(ArrayList<ChiTietDKDT> listCTDKDT) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.updateListCTDKDT(listCTDKDT);
	}

	public String updateListDiemThi(String soCMND, ArrayList<ChiTietDKDT> listThiSinhDiem) {
		// TODO Auto-generated method stub
		return chiTietDKDTDAO.updateListDiemThi(soCMND, listThiSinhDiem);
	}

	public ArrayList<ArrayList<ArrayList<String>>> getListDKDTByCumThi(String soCMND) {
		ArrayList<ArrayList<String>> list = chiTietDKDTDAO.getListDKDTByCumThi(soCMND);
		ArrayList<ArrayList<ArrayList<String>>> result = null;
		ArrayList<ArrayList<String>> item;
		ArrayList<String> temp = null;

		// filt mon thi
		if (list != null) {
			item = new ArrayList<ArrayList<String>>();
			result = new ArrayList<ArrayList<ArrayList<String>>>();
			int len = list.size() - 1;
			for (int i = 0; i < len; i++) {
				temp = list.get(i);
				if (temp.get(4).equals(list.get(i + 1).get(4))) {
					item.add(list.get(i));
				} else {
					item.add(list.get(i));
					result.add(item);
					item = new ArrayList<ArrayList<String>>();
				}
			}

			// check last list
			item.add(list.get(len));
			result.add(item);
			return result;
		}else{
			return null;
		}
	}
}
