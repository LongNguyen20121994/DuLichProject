package model.bo;

import model.dao.ChiTietDatTourDAO;

public class ChiTietDatTourBO {
	
	public String getMaxRecord() {
		return new ChiTietDatTourDAO().getMaxRecord();
	}

	/*public HashMap<String, String> getAllSelect() {
		return new KhachSanDAO().getAllSelect();
	}
	
	public List<DLKhachSan> getAll() {
		return new KhachSanDAO().getAll();
	}
	
	public HashMap<String, String> getAllByMaTinh(String maTinh) {
		return new KhachSanDAO().getAllByMaTinh(maTinh);
	}

	public DLKhachSan getInfo(String maKS) {
		return new KhachSanDAO().getInfo(maKS);
	}*/
	
	/*public boolean insertTour(DLChiTietDatTour ks) {
		return new ChiTietDatTourDAO().insertTour(ks);
	}*/
	/*
	public boolean updateKhachSan(DLKhachSan ks) {
		return new KhachSanDAO().updateKhachSan(ks);
	}
	
	public boolean deleteKhachSan(List<String> listMaKS) {
		return new KhachSanDAO().deleteKhachSan(listMaKS);
	}
	
	public boolean deleteListKhachSan(String[] listKS, String maTinh) {
		return new KhachSanDAO().deleteListKhachSan(listKS, maTinh);
	}*/
}
