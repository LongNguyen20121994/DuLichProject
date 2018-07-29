package controller.dulich;

import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLTour;
import model.bo.DLTourBO;
import model.bo.LoaiTourBO;

@SuppressWarnings("serial")
public class ShowListToursAction extends ActionSupport {
	private List<DLTour> listTours;
	private HashMap<String, String> listLoaiTour;
	private String maLoai;
	
	public List<DLTour> getListTours() {
		return listTours;
	}

	public void setListTours(List<DLTour> listTours) {
		this.listTours = listTours;
	}

	public HashMap<String, String> getListLoaiTour() {
		return listLoaiTour;
	}

	public void setListLoaiTour(HashMap<String, String> listLoaiTour) {
		this.listLoaiTour = listLoaiTour;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	@Override
	public String execute() throws Exception {
		listLoaiTour = new LoaiTourBO().getAllSelect();
		if(maLoai == null) {
			maLoai = "-1";
		}
		listTours = new DLTourBO().getAllByMaLoaiObject(maLoai);
		return SUCCESS;
	}

	public String ajaxShowListTour() {
		if(maLoai == null) {
			maLoai = "-1";
		}
		listTours = new DLTourBO().getAllByMaLoaiObject(maLoai);
		return SUCCESS;
	}
}
