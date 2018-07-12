package model.bo.longnt;

import java.util.HashMap;
import java.util.List;

import model.bean.TinhThanhPho;
import model.dao.longnt.TinhThanhPhoDAO;

public class TinhThanhPhoBO {

	public HashMap<String, String> getAllSelect() {
		return new TinhThanhPhoDAO().getAllSelect();
	}

	public boolean addListTinhTP(List<TinhThanhPho> listTinh) {
		// TODO Auto-generated method stub
		return new TinhThanhPhoDAO().addListTinhTP(listTinh);
	}

}
