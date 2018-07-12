package model.bo.khoi;

import java.util.HashMap;
import java.util.List;

import model.bean.HuyenQuan;
import model.dao.khoi.HuyenQuanDAO;

public class HuyenQuanBO {

	public HashMap<String, String> getListHuyenSelect(String maTinh) {
		// TODO Auto-generated method stub
		return new HuyenQuanDAO().getListHuyenSelect(maTinh);
	}

	public boolean addListHuyenQuan(List<HuyenQuan> listHuyen) {
		// TODO Auto-generated method stub
		return new HuyenQuanDAO().addListHuyenQuan(listHuyen);
	}

}
