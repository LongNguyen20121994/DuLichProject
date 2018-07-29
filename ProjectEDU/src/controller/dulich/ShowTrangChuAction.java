package controller.dulich;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLTour;
import model.bo.DLTourBO;

@SuppressWarnings("serial")
public class ShowTrangChuAction extends ActionSupport {
	private List<DLTour> listTours;

	public List<DLTour> getListTours() {
		return listTours;
	}
	public void setListTours(List<DLTour> listTours) {
		this.listTours = listTours;
	}
	
	@Override
	public String execute() throws Exception {
		listTours = new DLTourBO().getAll();
		return SUCCESS;
	}

	public String ajaxShowListTour() {
		listTours = new DLTourBO().getAll();
		return SUCCESS;
	}
}
