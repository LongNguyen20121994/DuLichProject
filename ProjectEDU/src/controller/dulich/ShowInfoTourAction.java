package controller.dulich;


import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLTour;
import model.bean.Info;
import model.bo.DLTourBO;

@SuppressWarnings("serial")
public class ShowInfoTourAction extends ActionSupport {
	private String maTour;
	private DLTour tour;
	private Info info;

	public String getMaTour() {
		return maTour;
	}
	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}
	public DLTour getTour() {
		return tour;
	}
	public void setTour(DLTour tour) {
		this.tour = tour;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}

	public String showInfoTour(){
		tour = new DLTourBO().getInfo(maTour);
		if (tour == null) {
			info = new Info("Lỗi load thông tin", "Lấy thông tin Tour không thành công!");
			return ERROR;
		} else {
			return SUCCESS;
		}
	}
}
