package model.bo;

import model.bean.DLKhachHang;
import model.dao.KhachHangDAO;

public class KhachHangBO {

	public String getMaxRecord() {
		return new KhachHangDAO().getMaxRecord();
	}

	public boolean insertKhachHang(DLKhachHang kh) {
		// TODO Auto-generated method stub
		return new KhachHangDAO().insertKhachHang(kh);
	}
	/*public HashMap<String, String> getAllSelect() {
		return new DLTinhDAO().getAllSelect();
	}

	public boolean addListTinh(List<DLTinh> listTinh) {
		// TODO Auto-generated method stub
		return new DLTinhDAO().addListTinh(listTinh);
	}*/
}
