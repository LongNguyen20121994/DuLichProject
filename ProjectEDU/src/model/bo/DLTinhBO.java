package model.bo;

import java.util.HashMap;
import java.util.List;

import model.bean.DLTinh;
import model.dao.DLTinhDAO;

public class DLTinhBO {
	public HashMap<String, String> getAllSelect() {
		return new DLTinhDAO().getAllSelect();
	}

	public boolean addListTinh(List<DLTinh> listTinh) {
		// TODO Auto-generated method stub
		return new DLTinhDAO().addListTinh(listTinh);
	}
}
