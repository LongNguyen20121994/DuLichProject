package model.bo.quang;

import java.util.ArrayList;

import model.bean.DangKyDuThi;
import model.dao.quang.DangKyDuThiDAO;

public class DangKyDuThiBO {

	private DangKyDuThiDAO dkdtDAO = new DangKyDuThiDAO();
	
	public DangKyDuThi getInfo(String soCMND, int namTS) {
		// TODO Auto-generated method stub
		return dkdtDAO.getInfo(soCMND,namTS);
	}

	public boolean insertDangKy(DangKyDuThi dkdt) {
		// TODO Auto-generated method stub
		return dkdtDAO.insertDangKy(dkdt);
	}

	public boolean validCumThi(String soCMND, String maCumThi, int namTS) {
		// TODO Auto-generated method stub
		return dkdtDAO.validCumThi(soCMND,maCumThi,namTS);
	}

	public String insertListDangKy(ArrayList<DangKyDuThi> listDangKyDT) {
		// TODO Auto-generated method stub
		return dkdtDAO.insertListDangKy(listDangKyDT);
	}

	public String updateListDangKy(ArrayList<DangKyDuThi> listDangKyDT) {
		// TODO Auto-generated method stub
		return dkdtDAO.updateListDangKy(listDangKyDT);
	}

}
