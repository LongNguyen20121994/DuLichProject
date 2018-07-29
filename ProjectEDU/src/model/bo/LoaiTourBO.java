package model.bo;

import java.util.HashMap;

import model.dao.LoaiTourDAO;

public class LoaiTourBO {

	public HashMap<String, String> getAllSelect() {
		return new LoaiTourDAO().getAllSelect();
	}

	/*public boolean addListTinh(List<DLLoaiTour> listLoaiTour) {
		// TODO Auto-generated method stub
		return new LoaiTourDAO().addListLoaiTour(listLoaiTour);
	}*/
}
