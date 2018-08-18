package controller.dulich;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLThongKe;
import model.bean.DLTour;
import model.bean.Info;
import model.bo.DLTourBO;

@SuppressWarnings("serial")
public class ShowListToursAction extends ActionSupport {
	private List<DLTour> listTours;
	private HashMap<String, String> listLoaiTour;
	private String maLoai;
	private List<DLThongKe> listTourThongKe;
	private Date txtTo;
	private Date txtFrom;
	private String btDaDat;
	private String btChuaDat;
	private Info info;

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

	public Date getTxtTo() {
		return txtTo;
	}

	public void setTxtTo(Date txtTo) {
		this.txtTo = txtTo;
	}

	public Date getTxtFrom() {
		return txtFrom;
	}

	public void setTxtFrom(Date txtFrom) {
		this.txtFrom = txtFrom;
	}

	public String getBtDaDat() {
		return btDaDat;
	}

	public void setBtDaDat(String btDaDat) {
		this.btDaDat = btDaDat;
	}

	public String getBtChuaDat() {
		return btChuaDat;
	}

	public void setBtChuaDat(String btChuaDat) {
		this.btChuaDat = btChuaDat;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<DLThongKe> getListTourThongKe() {
		return listTourThongKe;
	}

	public void setListTourThongKe(List<DLThongKe> listTourThongKe) {
		this.listTourThongKe = listTourThongKe;
	}

	@Override
	public String execute() throws Exception {
		info = new LoginAction().checkLogin("1");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btDaDat != null){
			listTourThongKe = new DLTourBO().getAllDaDat(txtFrom, txtTo);
		}
		if(btChuaDat != null){
			listTourThongKe = new DLTourBO().getAllChuaDat(txtFrom, txtTo);
		}
		listTourThongKe = new DLTourBO().getAllThongKe();
		return SUCCESS;
	}
}
