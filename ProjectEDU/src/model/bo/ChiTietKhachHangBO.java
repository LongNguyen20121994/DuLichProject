package model.bo;

import model.bean.DLChiTietKhachHang;
import model.dao.ChiTietKhachHangDAO;

public class ChiTietKhachHangBO {

	public String getMaxRecord() {
		return new ChiTietKhachHangDAO().getMaxRecord();
	}
	public boolean insertKhachHang(DLChiTietKhachHang kh) {
		return new ChiTietKhachHangDAO().insertKhachHang(kh);
	}
}
