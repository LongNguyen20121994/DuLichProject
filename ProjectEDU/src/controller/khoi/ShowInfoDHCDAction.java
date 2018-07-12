package controller.khoi;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bean.InfoNganhDHCD;
import model.bean.TruongDHCD;
import model.bo.khoi.TruongDHCDBO;

@SuppressWarnings("serial")
public class ShowInfoDHCDAction extends ActionSupport {
	private String maTruong;
	private TruongDHCD truongDHCD;
	private List<InfoNganhDHCD> listNganhDHCD;
	private Info info;

	public String getMaTruong() {
		return maTruong;
	}

	public void setMaTruong(String maTruong) {
		this.maTruong = maTruong;
	}

	public TruongDHCD getTruongDHCD() {
		return truongDHCD;
	}

	public void setTruongDHCD(TruongDHCD truongDHCD) {
		this.truongDHCD = truongDHCD;
	}

	public List<InfoNganhDHCD> getListNganhDHCD() {
		return listNganhDHCD;
	}

	public void setListNganhDHCD(List<InfoNganhDHCD> listNganhDHCD) {
		this.listNganhDHCD = listNganhDHCD;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String showInfoDHCD(){
		truongDHCD = new TruongDHCDBO().getInfo(maTruong);
		listNganhDHCD = new TruongDHCDBO().getListNganhDHCDByMaTruong(maTruong);
		return SUCCESS;
	}
}
