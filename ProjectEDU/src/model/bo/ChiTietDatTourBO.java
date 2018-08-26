package model.bo;

import model.dao.ChiTietDatTourDAO;

public class ChiTietDatTourBO {
	
	public String getMaxRecord() {
		return new ChiTietDatTourDAO().getMaxRecord();
	}
}
