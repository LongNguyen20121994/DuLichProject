package model.bo.khoi;

import java.util.List;

import model.bean.CumThi;
import model.dao.khoi.CumThiDAO;

public class CumThiBO {

	public List<CumThi> getAll() {
		return new CumThiDAO().getAll();
	}

	public CumThi getInfo(String maCT) {
		return new CumThiDAO().getInfo(maCT);
	}

	public boolean insertCumThi(CumThi ct) {
		return new CumThiDAO().insertCumThi(ct);
	}

	public boolean updateCumThi(CumThi ct) {
		return new CumThiDAO().updateCumThi(ct);
	}

	public boolean deleteListCumThi(List<String> listMaCT) {
		return new CumThiDAO().deleteListCumThi(listMaCT);
	}

}
