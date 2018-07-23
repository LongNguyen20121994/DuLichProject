package model.bo.quang;

import model.dao.quang.NamTuyenSinhDAO;

public class NamTuyenSinhBO {

	private NamTuyenSinhDAO namTSDAO = new NamTuyenSinhDAO();
	
	public int getNamTSHienTai() {
		return namTSDAO.getNamTSHienTai();
	}

}
