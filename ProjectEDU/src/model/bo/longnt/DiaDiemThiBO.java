package model.bo.longnt;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import model.bean.DiaDiemThi;
import model.dao.longnt.DiaDiemThiDAO;

public class DiaDiemThiBO {
	public DiaDiemThi getInfo(String maDDT) {
		return new DiaDiemThiDAO().getInfo(maDDT);
	}

	public boolean insertDiaDiemThi(DiaDiemThi ddt) {
		if(StringUtils.isBlank(ddt.getMaDDT()))
			return false;
		else
			return new DiaDiemThiDAO().insertDiaDiemThi(ddt);
	}

	public List<DiaDiemThi> getAll() {
		return new DiaDiemThiDAO().getAll();
	}

	public boolean updateDiaDiemThi(DiaDiemThi ddt) {
		return new DiaDiemThiDAO().updateDiaDiemThi(ddt);
	}

	public boolean deleteListDiaDiemThi(List<String> listMaDDT) {
		return new DiaDiemThiDAO().deleteListDiaDiemThi(listMaDDT);
	}

	public HashMap<String, String> getAllSelect() {
		return new DiaDiemThiDAO().getAllSelect();
	}
}
