package model.bo.longnt;

import java.util.HashMap;

import model.bean.CumThi;
import model.dao.longnt.CumThiDAO;

public class CumThiBO {
	public CumThi getInfo(String maCT) {
		return new CumThiDAO().getInfo(maCT);
	}
 
	public boolean insertCumThi(CumThi ct) {
		return new CumThiDAO().insertCumThi(ct);
	}

	public HashMap<String, String> getAllSelect() {
		return new CumThiDAO().getAllSelect();
	}

	public HashMap<String, String> getListCumThiSelect() {
		return new CumThiDAO().getListCumThiSelect();
	}

	public HashMap<String, String> getSelectCumThi(String soCMND) {
		return new CumThiDAO().getSelectCumThi(soCMND);
	}
}
