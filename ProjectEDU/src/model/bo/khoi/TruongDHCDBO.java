package model.bo.khoi;

import java.util.HashMap;
import java.util.List;

import model.bean.InfoNganhDHCD;
import model.bean.TruongDHCD;
import model.dao.khoi.TruongDHCDDAO;

public class TruongDHCDBO {
	public boolean addListTruongDHCD(List<TruongDHCD> list) {
		return new TruongDHCDDAO().addListTruongDHCD(list);
	}

	public HashMap<String, String> getAllSelect() {
		return new TruongDHCDDAO().getAllSelect();
	}

	public HashMap<String, String> getListDHCDSelect(String maTinh) {
		return new TruongDHCDDAO().getListDHCDSelect(maTinh);
	}

	public boolean insertTruongDHCD(TruongDHCD truongDHCD) {
		return new TruongDHCDDAO().insertTruongDHCD(truongDHCD);
	}

	public TruongDHCD getInfo(String maTruong) {
		return new TruongDHCDDAO().getInfo(maTruong);
	}

	public List<InfoNganhDHCD> getListNganhDHCDByMaTruong(String maTruong) {
		return new TruongDHCDDAO().getListNganhDHCDByMaTruong(maTruong);
	}

	public List<TruongDHCD> getAllByMaTinh(String maTinh) {
		return new TruongDHCDDAO().getAllByMaTinh(maTinh);
	}
}
