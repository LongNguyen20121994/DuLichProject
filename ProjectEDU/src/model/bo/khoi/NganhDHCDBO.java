package model.bo.khoi;

import java.util.HashMap;
import java.util.List;

import model.bean.NganhDHCD;
import model.dao.khoi.NganhDHCDDAO;

public class NganhDHCDBO {

	public boolean addListNganhDHCD(List<NganhDHCD> list) {
		return new NganhDHCDDAO().addListNganhDHCD(list);
	}

	public boolean insertNganhDHCD(NganhDHCD nganh) {
		return new NganhDHCDDAO().insertNganhDHCD(nganh);
	}

	public HashMap<String, String> getAllTruongSelect(String maTruong) {
		// TODO Auto-generated method stub
		return new NganhDHCDDAO().getAllTruongSelect(maTruong);
	}

	public List<NganhDHCD> getAllByMaTruong(String maTruong) {
		// TODO Auto-generated method stub
		return new NganhDHCDDAO().getAllByMaTruong(maTruong);
	}

	public boolean deleteListNganhByMa(String[] listMaNganh, String maTruong) {
		// TODO Auto-generated method stub
		return new NganhDHCDDAO().deleteListNganhByMa(listMaNganh, maTruong);
	}

}
