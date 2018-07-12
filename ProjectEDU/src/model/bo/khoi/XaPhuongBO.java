package model.bo.khoi;

import java.util.HashMap;
import java.util.List;

import model.bean.XaPhuong;
import model.dao.khoi.XaPhuongDAO;

public class XaPhuongBO {

	public HashMap<String, String> getListXaSelect(String maTinh, String maHuyen) {
		return new XaPhuongDAO().getListXaSelect(maTinh,maHuyen);
	}

	public boolean addListXaPhuong(List<XaPhuong> listXa) {
		return new XaPhuongDAO().addListXaPhuong(listXa);
	}

}
