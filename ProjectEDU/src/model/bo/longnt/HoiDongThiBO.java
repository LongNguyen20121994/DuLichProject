package model.bo.longnt;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import model.bean.HoiDongThi;
import model.dao.longnt.HoiDongThiDAO;

public class HoiDongThiBO {
	public HoiDongThi getInfo(String maHDT) {
		return new HoiDongThiDAO().getInfo(maHDT);
	}

	public boolean insertHoiDongThi(HoiDongThi hdt,String cumThi) {
		if(StringUtils.isBlank(hdt.getMaHDT()))
			return false;
		else
			return new HoiDongThiDAO().insertHoiDongThi(hdt,cumThi);
	}

	public List<HoiDongThi> getAll() {
		return new HoiDongThiDAO().getAll();
	}

	public boolean updateHoiDongThi(HoiDongThi hdt) {
		return new HoiDongThiDAO().updateHoiDongThi(hdt);
	}

	public boolean deleteListHoiDongThi(List<String> listMaHDT) {
		return new HoiDongThiDAO().deleteListHoiDongThi(listMaHDT);
	}

	public HashMap<String, String> getAllSelect() {
		return new HoiDongThiDAO().getAllSelect();
	}
}
