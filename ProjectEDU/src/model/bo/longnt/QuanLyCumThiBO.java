package model.bo.longnt;


import java.util.HashMap;
import java.util.List;

import model.bean.QuanLyCumThi;
import model.dao.longnt.QuanLyCumThiDAO;

public class QuanLyCumThiBO {
	QuanLyCumThiDAO qlctDAO = new QuanLyCumThiDAO();
	
	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().doiMatKhau(soCMND,matKhau);
	}

	public QuanLyCumThi getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().getInfo(soCMND);
	}

	public boolean insertQuanLyCumThi(QuanLyCumThi qlct) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().insertQuanLyCumThi(qlct);
	}
	
	public HashMap<String, Object> getInfoHienThi(String soCMND) {
		// TODO Auto-generated method stub
		return new QuanLyCumThiDAO().getInfoHienThi(soCMND);
	}

	public boolean updateInfo(QuanLyCumThi qlct) {
		if ("-1".equals(qlct.getMaXa()))
			qlct.setMaXa(null);
		return qlctDAO.updateInfo(qlct);
	}

	public List<QuanLyCumThi> getListInfoQuanLyCumThi(String soCMND) {
		return qlctDAO.getInfoQuanLyCumThhi(soCMND);
	}

	public String deleteListQuanLyCumThi(List<String> listSoCMND) {
		if (listSoCMND != null) {
			return qlctDAO.deleteListQuanLyCumThi(listSoCMND);
		} else {
			return "fail";
		}
	}
}
