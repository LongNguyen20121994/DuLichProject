package model.bo;

import model.dao.ChiTietKhachHangDAO;

public class ChiTietKhachHangBO {

	public String getMaxRecord() {
		return new ChiTietKhachHangDAO().getMaxRecord();
	}
	
}
