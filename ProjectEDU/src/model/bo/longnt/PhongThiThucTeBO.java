package model.bo.longnt;

import java.util.List;

import model.bean.PhongThiThucTe;
import model.dao.longnt.PhongThiThucTeDAO;

import org.apache.commons.lang3.StringUtils;

public class PhongThiThucTeBO {

	public List<PhongThiThucTe> getAll() {
		return new PhongThiThucTeDAO().getAll();
	}

	public PhongThiThucTe getInfo(String iDPhong) {
		return new PhongThiThucTeDAO().getInfo(iDPhong);
	}

	public boolean updatePhongThiThucTe(PhongThiThucTe pt) {
		return new PhongThiThucTeDAO().updatePhongThiThucTe(pt);
	}

	public boolean insertPhongThiThucTe(PhongThiThucTe pt) {
		if(StringUtils.isBlank(pt.getIDPhong()))
			return false;
		else
			return new PhongThiThucTeDAO().insertPhongThiThucTe(pt);
	}

	public boolean deleteListPhongThiThucTe(List<String> listIDPhong) {
		return new PhongThiThucTeDAO().deleteListPhongThiThucTe(listIDPhong);
	}

}
