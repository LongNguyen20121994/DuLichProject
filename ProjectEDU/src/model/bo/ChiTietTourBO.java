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

	public String getMaxRecord() {
		return new ChiTietTourDAO().getMaxRecord();
	}
	
	public boolean deleteListLichTrinh(List<String> listMaCTTour) {
		return new ChiTietTourDAO().deleteListLichTrinh(listMaCTTour);
	}
	
	public List<DLChiTietTour> getAll() {
		return new ChiTietTourDAO().getAll();
	}
}
