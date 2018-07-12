package controller.dulich;

import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLTour;
import model.bo.DLTinhBO;
import model.bo.DLTourBO;

@SuppressWarnings("serial")
public class ShowListToursAction extends ActionSupport {
	private List<DLTour> listTours;
	private HashMap<String, String> listTinh;
	private String maTinh;

	public List<DLTour> getListTours() {
		return listTours;
	}
	public void setListTours(List<DLTour> listTours) {
		this.listTours = listTours;
	}
	public HashMap<String, String> getListTinh() {
		return listTinh;
	}
	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}
	public String getMaTinh() {
		return maTinh;
	}
	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}
	
	@Override
	public String execute() throws Exception {
		listTinh = new DLTinhBO().getAllSelect();
		if(maTinh == null) {
			maTinh = "-1";
		}
		listTours = new DLTourBO().getAllByMaTinh(maTinh);
		return SUCCESS;
	}

	public String ajaxShowListTour() {
		if(maTinh == null) {
			maTinh = "-1";
		}
		listTours = new DLTourBO().getAllByMaTinh(maTinh);
		return SUCCESS;
	}
}
