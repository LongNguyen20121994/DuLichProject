package model.bo.khoi;

import java.util.ArrayList;
import java.util.List;

import model.bean.HoSoXetTuyen;
import model.bean.KhoiThiNganhDHCD;
import model.bean.Nganh;
import model.dao.khoi.ChiTietHoSoDAO;

public class ChiTietHoSoBO {
	
	public boolean updateChiTietHoSo(HoSoXetTuyen hs, String[] ktNganh) {
		return new ChiTietHoSoDAO().updateChiTietHoSo(hs, ktNganh);
	}

	public ArrayList<String> getAllHoSo(HoSoXetTuyen ct) {
		return new ChiTietHoSoDAO().getAllHoSo(ct);
	}

	public boolean sendMailTrungTuyen(List<KhoiThiNganhDHCD> listKTNganh) {
		return new ChiTietHoSoDAO().sendMailTrungTuyen(listKTNganh);
	}

	public List<Nganh> getAllNganhByMaHS(String maHS) {
		return new ChiTietHoSoDAO().getAllNganhByMaHS(maHS);
	}
}
