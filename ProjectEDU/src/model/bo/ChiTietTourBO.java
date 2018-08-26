package model.bo;

import java.util.List;

import model.bean.DLChiTietTour;
import model.dao.ChiTietTourDAO;

public class ChiTietTourBO {
	public boolean insertChiTietTour(DLChiTietTour ctTour) {
		// TODO Auto-generated method stub
		return new ChiTietTourDAO().insertChiTietTour(ctTour);
	}
	
	public boolean updateChiTietTour(DLChiTietTour ctTour) {
		// TODO Auto-generated method stub
		return new ChiTietTourDAO().updateChiTietTour(ctTour);
	}
	
	public boolean updateSoLuong(String mact, String soluong) {
		return new ChiTietTourDAO().updateSoLuong(mact, soluong);
	}

	public String getMaxRecord() {
		return new ChiTietTourDAO().getMaxRecord();
	}
	
	public boolean deleteListLichTrinh(List<String> listMaCTTour) {
		return new ChiTietTourDAO().deleteListLichTrinh(listMaCTTour);
	}
	
	public List<DLChiTietTour> getAll() {
		return new ChiTietTourDAO().getAll();
	}
	public List<DLChiTietTour> getTheoMaTour(String maTour) {
		return new ChiTietTourDAO().getTheoMaTour(maTour);
	}
	
	public DLChiTietTour getInfo(String maTour) {
		return new ChiTietTourDAO().getInfo(maTour);
	}
	public DLChiTietTour getInfoGanNhat(String maTour) {
		return new ChiTietTourDAO().getInfoGanNhat(maTour);
	}
}
