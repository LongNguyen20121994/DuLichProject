package model.bo.khoi;

import java.util.ArrayList;
import java.util.List;

import model.bean.KhoiThiNganhDHCD;
import model.bean.XetTuyen;
import model.dao.khoi.XetTuyenDAO;

public class XetTuyenBO {

	public boolean addListXetTuyen(List<KhoiThiNganhDHCD> listKTN) {
		// TODO Auto-generated method stub
		return new XetTuyenDAO().addListXetTuyen(listKTN);
	}

	public ArrayList<XetTuyen> getAllMonThiNganh(KhoiThiNganhDHCD ktn) {
		// TODO Auto-generated method stub
		return new XetTuyenDAO().getAllMonThiNganh(ktn);
	}

	public boolean updateListXetTuyenNganhDHCD(List<XetTuyen> listXT) {
		// TODO Auto-generated method stub
		return new XetTuyenDAO().updateListXetTuyenNganhDHCD(listXT);
	}

}
