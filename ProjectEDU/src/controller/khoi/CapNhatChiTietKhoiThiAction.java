package controller.khoi;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import controller.dulich.LoginAction;
import model.bean.ChiTietKhoiThi;
import model.bean.Info;
import model.bo.khoi.ChiTietKhoiThiBO;
import model.bo.khoi.KhoiThiBO;
import model.bo.khoi.MonThiBO;

@SuppressWarnings("serial")
public class CapNhatChiTietKhoiThiAction extends ActionSupport {
	private String[] listMT;
	private ChiTietKhoiThi ct;
	private Info info;
	private HashMap<String, String> listKhoi;
	private HashMap<String, String> listMon;

	public String[] getListMT() {
		return listMT;
	}

	public void setListMT(String[] listMT) {
		this.listMT = listMT;
	}

	public ChiTietKhoiThi getCt() {
		return ct;
	}

	public void setCt(ChiTietKhoiThi ct) {
		this.ct = ct;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public HashMap<String, String> getListKhoi() {
		return listKhoi;
	}

	public void setListKhoi(HashMap<String, String> listKhoi) {
		this.listKhoi = listKhoi;
	}

	public HashMap<String, String> getListMon() {
		return listMon;
	}

	public void setListMon(HashMap<String, String> listMon) {
		this.listMon = listMon;
	}

	@Override
	public String execute() throws Exception {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		ChiTietKhoiThiBO ctbo = new ChiTietKhoiThiBO();
		boolean checkList = false;
		if (listMT != null) {
			checkList = ctbo.deleteListMonThi(listMT, ct.getMaKhoi());
		}
		if (ctbo.insertChiTietKhoiThi(ct)) {
			return SUCCESS;
		} else {
			if (checkList) {
				return SUCCESS;
			}
		}
		return "info";
	}

	public String showCapNhatChiTietKhoiThi() {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listKhoi = new KhoiThiBO().getAllSelect();
		listMon = new MonThiBO().getAllSelect();
		return SUCCESS;
	}
}
