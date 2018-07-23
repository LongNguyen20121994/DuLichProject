package model.bo.khoi;

import java.util.ArrayList;

import model.bean.ChiTietKhoiThi;
import model.bean.MonThi;
import model.dao.khoi.ChiTietKhoiThiDAO;

public class ChiTietKhoiThiBO {

	public ArrayList<MonThi> getAllMonThi(String maKhoi) {
		return new ChiTietKhoiThiDAO().getAllMonThi(maKhoi);
	}

	public boolean validMonThi(String maKhoi, String maMon) {
		return new ChiTietKhoiThiDAO().validMonThi(maKhoi,maMon);
	}

	public boolean deleteListMonThi(String[] listMT, String maKhoi) {
		return new ChiTietKhoiThiDAO().deleteListMonThi(listMT,maKhoi);
	}

	public boolean insertChiTietKhoiThi(ChiTietKhoiThi ct) {
		return new ChiTietKhoiThiDAO().insertChiTietKhoiThi(ct);
	}

}
