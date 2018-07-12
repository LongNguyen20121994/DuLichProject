package controller.khoi;

import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.TruongDHCD;
import model.bo.khoi.TinhThanhPhoBO;
import model.bo.khoi.TruongDHCDBO;

@SuppressWarnings("serial")
public class ShowListDaiHocCaoDangAction extends ActionSupport {
	private List<TruongDHCD> listTruongDHCD;
	private HashMap<String, String> listTinh;
	private String maTinh;
	
	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}

	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}

	public List<TruongDHCD> getListTruongDHCD() {
		return listTruongDHCD;
	}

	public void setListTruongDHCD(List<TruongDHCD> listTruongDHCD) {
		this.listTruongDHCD = listTruongDHCD;
	}

	@Override
	public String execute() throws Exception {
		listTinh = new TinhThanhPhoBO().getAllSelect();
		if(maTinh == null) {
			maTinh = "-1";
		}
		listTruongDHCD = new TruongDHCDBO().getAllByMaTinh(maTinh);
		return SUCCESS;
	}
	
	public String ajaxShowListDHCD() {
		if(maTinh == null) {
			maTinh = "-1";
		}
		listTruongDHCD = new TruongDHCDBO().getAllByMaTinh(maTinh);
		return SUCCESS;
	}
}
