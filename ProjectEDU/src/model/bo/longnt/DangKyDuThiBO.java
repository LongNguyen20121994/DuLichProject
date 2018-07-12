package model.bo.longnt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.bean.DangKyDuThi;
import model.dao.longnt.DangKyDuThiDAO;

public class DangKyDuThiBO {

	private DangKyDuThiDAO dkdtDAO = new DangKyDuThiDAO();
	
	public String themListDangKyDuThi(ArrayList<DangKyDuThi> list) {
		ArrayList<DangKyDuThi> insert = new ArrayList<DangKyDuThi>();
		for (DangKyDuThi i : list) {
			if (i.getSoCMND() != null && !i.getSoCMND().isEmpty())
				insert.add(i);
		}

		return dkdtDAO.themListDangKyDuThi(insert);
	}
	
	public DangKyDuThi getInfo(String soCMND, int namTS) {
		// TODO Auto-generated method stub
		return dkdtDAO.getInfo(soCMND,namTS);
	}

	public boolean insertDangKy(DangKyDuThi dkdt) {
		// TODO Auto-generated method stub
		return dkdtDAO.insertDangKy(dkdt);
	}
	
	public Map<String, Object> getInfoHienThi(String soCMND) {
		Map<String, Object> hm = dkdtDAO.getInfoHienThi(soCMND);
		if (hm != null) {
			return hm;
		} else
			return null;
	}
	
	public boolean validCumThi(String soCMND, String maCumThi, int namTS) {
		// TODO Auto-generated method stub
		return dkdtDAO.validCumThi(soCMND,maCumThi,namTS);
	}
	
	public String updateListDangKy(ArrayList<DangKyDuThi> listDangKyDT) {
		// TODO Auto-generated method stub
		return dkdtDAO.updateListDangKy(listDangKyDT);
	}
	
	public String insertListDangKy(ArrayList<DangKyDuThi> listDangKyDT) {
		// TODO Auto-generated method stub
		return dkdtDAO.insertListDangKy(listDangKyDT);
	}
	
	public ArrayList<DangKyDuThi> getListInfoPassWord(String soCMNDQuanLyCumThi) {
		// TODO Auto-generated method stub
		return dkdtDAO.getListInfoPassWord(soCMNDQuanLyCumThi);
	}

	public ArrayList<ArrayList<String>> getListInfoDKDT(String soCMNDQuanLyCumThi) {
		// TODO Auto-generated method stub
		return dkdtDAO.getListInfoDKDT(soCMNDQuanLyCumThi);
	}

	public List<DangKyDuThi> getListInfoSoBaoDanh(String soCMND,int namTS) {
		return dkdtDAO.getListInfoSoBaoDanh(soCMND,namTS);
	}
	
	public String updateListSoBaoDanh(ArrayList<DangKyDuThi> listSoBaoDanh) {
		
		ArrayList<DangKyDuThi> listSBD = new ArrayList<DangKyDuThi>();
		if (listSoBaoDanh != null) {
			for (DangKyDuThi dkdt : listSBD) {
				if (dkdt.getSoCMND() != null && !dkdt.getSoCMND().isEmpty())
					listSBD.add(dkdt);
			}
			
			return dkdtDAO.updateListSoBaoDanh(listSBD);
		} else {
			return "fail";
		}
	}
	public boolean updateInfo(DangKyDuThi dk) {
		return dkdtDAO.updateInfo(dk);
	}

	public String deleteListSoBaoDanh(List<String> listSoCMND, int namTS) {
		if (listSoCMND != null) {
			return dkdtDAO.deleteListSoBaoDanh(listSoCMND,namTS);
		} else {
			return "fail";
		}
	}
}
