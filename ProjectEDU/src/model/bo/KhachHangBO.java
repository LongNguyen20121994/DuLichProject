package model.bo;

import model.bean.DLKhachHang;
import model.dao.KhachHangDAO;

public class KhachHangBO {

	public DLKhachHang getInfo(String makh) {
		return new KhachHangDAO().getInfo(makh);
	}
	
	public String getMaxRecord() {
		return new KhachHangDAO().getMaxRecord();
	}

	public boolean insertKhachHang(DLKhachHang kh) {
		return new KhachHangDAO().insertKhachHang(kh);
	}
	
	public boolean updateKhachHang(String maKH, String pass) {
		return new KhachHangDAO().updateKhachHang(maKH, pass);
	}
}
