package model.bo.khoi;

import java.util.HashMap;
import java.util.List;

import model.bean.Nganh;
import model.dao.khoi.NganhDAO;

public class NganhBO {

	public boolean addListNganh(List<Nganh> list) {
		// TODO Auto-generated method stub
		return new NganhDAO().addListNganh(list);
	}

	public boolean insertNganh(Nganh nganh) {
		// TODO Auto-generated method stub
		return new NganhDAO().insertNganh(nganh);
	}

	public HashMap<String, String> getAllSelect() {
		// TODO Auto-generated method stub
		return new NganhDAO().getAllSelect();
	}

}
