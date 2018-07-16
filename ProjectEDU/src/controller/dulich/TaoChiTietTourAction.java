package controller.dulich;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLChiTietTour;
import model.bean.Info;
import model.bo.ChiTietTourBO;
import model.bo.DLTinhBO;
import model.bo.KhachSanBO;

@SuppressWarnings("serial")
public class TaoChiTietTourAction extends ActionSupport {
	private DLChiTietTour ctTour;
	private HashMap<String,String> listTour;
	private HashMap<String,String> listTinh;
	private HashMap<String,String> listKhachSan;
	private Info info;
	private String maTour;
	
	public DLChiTietTour getCtTour() {
		return ctTour;
	}
	public void setCtTour(DLChiTietTour ctTour) {
		this.ctTour = ctTour;
	}
	public HashMap<String, String> getListTour() {
		return listTour;
	}
	public void setListTour(HashMap<String, String> listTour) {
		this.listTour = listTour;
	}
	public HashMap<String, String> getListTinh() {
		return listTinh;
	}
	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}
	public HashMap<String, String> getListKhachSan() {
		return listKhachSan;
	}
	public void setListKhachSan(HashMap<String, String> listKhachSan) {
		this.listKhachSan = listKhachSan;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String getMaTour() {
		return maTour;
	}
	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}

	@Override
	public String execute() throws Exception {
		if(maTour == null) {
			ChiTietTourBO ctTourBo = new ChiTietTourBO();
			if(ctTourBo.insertChiTietTour(ctTour)) {
				info = new Info("Tạo chi tiết Tour","Tạo chi tiết Tour thành công!");
				return "info";
			} else {
				info = new Info("Tạo chi tiết Tour","Tạo chi tiết Tour không thành công!");
				return "info";
			}
		}else {
			info = new Info("Tạo chi tiết Tour","Tạo chi tiết Tour không thành công!");
			return "info";
		}
	}

	public String showTaoChiTietTour(){
		listTinh = new DLTinhBO().getAllSelect();
		listKhachSan = new KhachSanBO().getAllSelect();
		return SUCCESS;
	}
}
