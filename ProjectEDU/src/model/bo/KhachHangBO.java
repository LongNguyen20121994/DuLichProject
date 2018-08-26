package model.bo;

import java.util.List;

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
	
	public List<DLKhachHang> getAll() {
		return new KhachHangDAO().getAll();
	}
	
	public boolean updateKhachHang(DLKhachHang kh) {
		return new KhachHangDAO().updateKhachHang(kh);
	}
	
	public boolean deleteListKhachHang(List<String> listMaKH) {
		return new KhachHangDAO().deleteListKhachHang(listMaKH);
	}
}
