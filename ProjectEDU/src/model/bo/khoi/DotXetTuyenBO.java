package model.bo.khoi;

import java.util.HashMap;
import java.util.List;

import model.bean.DotXetTuyen;
import model.dao.khoi.DotXetTuyenDAO;

public class DotXetTuyenBO {

	public HashMap<String, String> getAllSelect() {
		return new DotXetTuyenDAO().getAllSelect();
	}

	public List<DotXetTuyen> getListDotXT() {
		return new DotXetTuyenDAO().getListDotXT();
	}

	public boolean insertDotXetTuyen(DotXetTuyen dxt) {
		return new DotXetTuyenDAO().insertDotXetTuyen(dxt);
	}

	public boolean updateTrangThai(String maDotXT, boolean b) {
		return new DotXetTuyenDAO().updateTrangThai(maDotXT,b);
	}

	public boolean deleteDotXetTuyen(String maDotXT) {
		return new DotXetTuyenDAO().deleteDotXetTuyen(maDotXT);
	}
	
}
