package model.bo;

import model.bean.DLHopDong;
import model.dao.HopDongDAO;

public class HopDongBO {
	
	public boolean insertHopDong(DLHopDong hd) {
		return new HopDongDAO().insertHopDong(hd);
	}

	public String getMaxRecord() {
		return new HopDongDAO().getMaxRecord();
	}
}
