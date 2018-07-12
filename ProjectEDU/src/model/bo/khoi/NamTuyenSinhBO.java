package model.bo.khoi;

import model.bean.NamTuyenSinh;
import model.dao.khoi.NamTuyenSinhDAO;

public class NamTuyenSinhBO {

	public int getNamTuyenSinh() {
		return new NamTuyenSinhDAO().getNamTuyenSinh();
	}

	public boolean updateNamTuyenSinh(NamTuyenSinh namTS) {
		return new NamTuyenSinhDAO().updateNamTuyenSinh(namTS);
	}

}
