package model.bo.khoi;

import java.util.List;

import model.bean.HoSoXetTuyen;
import model.dao.khoi.HoSoXetTuyenDAO;

public class HoSoXetTuyenBO {

	public boolean insertHoSoXetTuyen(HoSoXetTuyen hs) {
		return new HoSoXetTuyenDAO().insertHoSoXetTuyen(hs);
	}

	public boolean deleteHoSoXetTuyen(HoSoXetTuyen hs) {
		return new HoSoXetTuyenDAO().deleteHoSoXetTuyen(hs);
	}

	public boolean isValidKhoiThi(String soCMND, String khoiThi) {
		return new HoSoXetTuyenDAO().isValidKhoiThi(soCMND,khoiThi);
	}

	public List<HoSoXetTuyen> getAllBySoCMND(String soCMND) {
		return new HoSoXetTuyenDAO().getAllBySoCMND(soCMND);
	}

}
