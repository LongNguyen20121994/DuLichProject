package model.bo.khoi;

import java.util.ArrayList;
import java.util.List;

import model.bean.KhoiThi;
import model.bean.KhoiThiNganhDHCD;
import model.dao.khoi.KhoiThiNganhDHCDDAO;

public class KhoiThiNganhDHCDBO {

	public boolean addListKhoiThiNganhDHCD(List<KhoiThiNganhDHCD> list) {
		return new KhoiThiNganhDHCDDAO().addListKhoiThiNganhDHCD(list);
	}

	public boolean insertKhoiThiNganhDHCD(KhoiThiNganhDHCD ktn) {
		return new KhoiThiNganhDHCDDAO().insertKhoiThiNganhDHCD(ktn);
	}

	public ArrayList<KhoiThi> getAllKhoiThi(KhoiThiNganhDHCD ktn) {
		return new KhoiThiNganhDHCDDAO().getAllKhoiThi(ktn);
	}

	public boolean validKhoiThi(KhoiThiNganhDHCD ktn) {
		return new KhoiThiNganhDHCDDAO().validKhoiThi(ktn);
	}

	public boolean deleteListKhoiThi(String[] listKT, KhoiThiNganhDHCD ktn) {
		return new KhoiThiNganhDHCDDAO().deleteListKhoiThi(listKT, ktn);
	}

	public ArrayList<KhoiThiNganhDHCD> getAllKhoiThiNganhDHCD(String maTruong) {
		return new KhoiThiNganhDHCDDAO().getAllKhoiThiNganhDHCD(maTruong);
	}

	public boolean updateDiemChuan(List<KhoiThiNganhDHCD> listKTN) {
		return new KhoiThiNganhDHCDDAO().updateDiemChuan(listKTN);
	}

}
