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
	
	public List<DLTour> getAllByMaTinhObject(String maTinh) {
		return new DLTourDAO().getAllByMaTinhObject(maTinh);
	}
	
	public HashMap<String, String> getAllByMaTinh(String maTinh) {
		return new DLTourDAO().getAllByMaTinh(maTinh);
	}

	public DLTour getInfo(String maTour) {
		return new DLTourDAO().getInfo(maTour);
	}
}
