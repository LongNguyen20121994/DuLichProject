package model.bo;

import java.util.HashMap;

import model.bean.DLKhachSan;
import model.dao.KhachSanDAO;

public class KhachSanBO {

	public HashMap<String, String> getAllSelect() {
		return new KhachSanDAO().getAllSelect();
	}
	
	public HashMap<String, String> getAllByMaTinh(String maTinh) {
		return new KhachSanDAO().getAllByMaTinh(maTinh);
	}

	public DLKhachSan getInfo(String maKS) {
		return new KhachSanDAO().getInfo(maKS);
	}
	
	public boolean insertKhachSan(DLKhachSan ks) {
		return new KhachSanDAO().insertKhachSan(ks);
	}
	
	public boolean updateKhachSan(DLKhachSan ks) {
		return new KhachSanDAO().updateKhachSan(ks);
	}
	
	public boolean deleteKhachSan(String maKS) {
		return new KhachSanDAO().deleteKhachSan(maKS);
	}
	
	public boolean deleteListKhachSan(String[] listKS, String maTinh) {
		return new KhachSanDAO().deleteListKhachSan(listKS, maTinh);
	}
}
