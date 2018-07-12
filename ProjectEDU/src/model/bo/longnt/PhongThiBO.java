package model.bo.longnt;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import model.bean.PhongThi;
import model.dao.longnt.PhongThiDAO;

public class PhongThiBO {
	public PhongThi getInfo(String maPT) {
		return new PhongThiDAO().getInfo(maPT);
	}

	public boolean insertPhongThi(PhongThi pt) {
		if(StringUtils.isBlank(pt.getMaPT()))
			return false;
		else
			return new PhongThiDAO().insertPhongThi(pt);
	}
	public HashMap<String, String> getAllSelect() {
		return new PhongThiDAO().getAllSelect();
	}

	public List<PhongThi> getAll() {
		return new PhongThiDAO().getAll();
	}

	public boolean updatePhongThi(PhongThi pt) {
		return new PhongThiDAO().updatePhongThi(pt);
	}

	public boolean deleteListPhongThi(List<String> listMaPT) {
		return new PhongThiDAO().deleteListPhongThi(listMaPT);
	}
}
