package model.bo.quang;

import java.util.HashMap;

import model.bean.KhoiThi;
import model.dao.quang.KhoiThiDAO;

public class KhoiThiBO {

	public boolean insertKhoiThi(KhoiThi kt) {
		// TODO Auto-generated method stub
		return new KhoiThiDAO().insertKhoiThi(kt);
	}

	public HashMap<String, String> getAllSelect() {
		// TODO Auto-generated method stub
		return new KhoiThiDAO().getAllSelect();
	}

}
