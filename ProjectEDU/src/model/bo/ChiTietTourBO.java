package model.bo;

import model.bean.DLChiTietTour;
import model.dao.ChiTietTourDAO;

public class ChiTietTourBO {
	public boolean insertChiTietTour(DLChiTietTour ctTour) {
		// TODO Auto-generated method stub
		return new ChiTietTourDAO().insertChiTietTour(ctTour);
	}
}
