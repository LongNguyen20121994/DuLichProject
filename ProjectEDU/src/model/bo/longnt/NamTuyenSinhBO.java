package model.bo.longnt;

import model.dao.longnt.NamTuyenSinhDAO;

public class NamTuyenSinhBO {

	private NamTuyenSinhDAO namTSDAO = new NamTuyenSinhDAO();
	
	public int getNamTuyenSinh() {
		return namTSDAO.getNamTuyenSinh();
	}

}
