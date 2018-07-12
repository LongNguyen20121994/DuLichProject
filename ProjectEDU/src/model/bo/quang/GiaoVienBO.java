package model.bo.quang;

import model.bean.GiaoVien;
import model.dao.quang.GiaoVienDAO;

public class GiaoVienBO {
	GiaoVienDAO gvDAO = new GiaoVienDAO();

	public GiaoVien getInfo(String soCMND) {
		// TODO Auto-generated method stub
		return new GiaoVienDAO().getInfo(soCMND);
	}

	public boolean doiMatKhau(String soCMND, String matKhau) {
		// TODO Auto-generated method stub
		return new GiaoVienDAO().doiMatKhau(soCMND, matKhau);
	}

	public boolean insertGiaoVien(GiaoVien gv) {
		if ("-1".equals(gv.getMaXa()))
			gv.setMaXa(null);
		if (gv.getSoCMND() == null || gv.getSoCMND().isEmpty() || gv.getMaTinhTHPT() == null
				|| gv.getMaTinhTHPT().isEmpty() || gv.getMaTruongTHPT() == null || gv.getMaTruongTHPT().isEmpty())
			return false;
		else
			return gvDAO.insertGiaoVien(gv);
	}

	public GiaoVien getInfoHienThi(String soCMND) {

		return gvDAO.getInfoHienThi(soCMND);
	}

	public boolean updateInfo(GiaoVien gv) {
		if ("-1".equals(gv.getMaXa()))
			gv.setMaXa(null);
		return gvDAO.updateInfo(gv);
	}

	public boolean isSoCMND(String soCMND) {
		// TODO Auto-generated method stub
		return gvDAO.isSoCMND(soCMND);
	}

}
