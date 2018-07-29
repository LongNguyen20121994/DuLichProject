package model.bo;

import java.util.HashMap;
import java.util.List;

import model.bean.DLTour;
import model.dao.DLTourDAO;

public class DLTourBO {

	public String getMaxRecord() {
		return new DLTourDAO().getMaxRecord();
	}
	
	public boolean insertTour(DLTour tour) {
		// TODO Auto-generated method stub
		return new DLTourDAO().insertTour(tour);
	}
	
	public boolean updateTour(DLTour tour) {
		// TODO Auto-generated method stub
		return new DLTourDAO().updateTour(tour);
	}
	
	public List<DLTour> getAll() {
		return new DLTourDAO().getAll();
	}
	
	public List<DLTour> getAllByMaLoaiObject(String maLoai) {
		return new DLTourDAO().getAllByMaLoaiObject(maLoai);
	}
	
	public HashMap<String, String> getAllByMaLoaiTour(String maLoai) {
		return new DLTourDAO().getAllByMaLoaiTour(maLoai);
	}
	public HashMap<String, String> getAllBy() {
		return new DLTourDAO().getAllBy();
	}

	public DLTour getInfo(String maTour) {
		return new DLTourDAO().getInfo(maTour);
	}
}
