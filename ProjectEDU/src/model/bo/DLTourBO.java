package model.bo;

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
	
	public List<DLTour> getAllByMaTinh(String maTinh) {
		return new DLTourDAO().getAllByMaTinh(maTinh);
	}

	public DLTour getInfo(String maTour) {
		return new DLTourDAO().getInfo(maTour);
	}
}
