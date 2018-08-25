package model.bo;

import model.bean.DLKhachHang;
import model.dao.KhachHangDAO;

public class KhachHangBO {

	public String getMaxRecord() {
		return new KhachHangDAO().getMaxRecord();
	}

	public boolean insertKhachHang(DLKhachHang kh) {
		return new KhachHangDAO().insertKhachHang(kh);
	}
}
