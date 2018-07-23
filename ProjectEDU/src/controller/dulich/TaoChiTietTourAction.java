package controller.dulich;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLChiTietTour;
import model.bean.Info;
import model.bo.ChiTietTourBO;
import model.bo.KhachSanBO;

@SuppressWarnings("serial")
public class TaoChiTietTourAction extends ActionSupport {
	private DLChiTietTour ctTour;
	private HashMap<String,String> listTour;
	private HashMap<String,String> listKhachSan;
	private Info info;
	private String maTour;
	private String maTinh;
	
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
	public String getMaTinh() {
		return maTinh;
	}
	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	@Override
	public String execute() throws Exception {
		if(maTour != null) {
			ctTour.setMaTour(maTour);
			ChiTietTourBO ctTourBo = new ChiTietTourBO();
			int maxCt;
			String timMax = new ChiTietTourBO().getMaxRecord();
			if(timMax != null) {
				maxCt = catChuoi(timMax) + 1;
			}
			else {
				maxCt = 1;   
			}
			ctTour.setMaTour(taoMaTour(maxCt));
			if(ctTourBo.insertChiTietTour(ctTour)) {
				info = new Info("Tạo chi tiết Tour","Tạo chi tiết Tour thành công!");
				return "info";
			} else {
				info = new Info("Tạo chi tiết Tour","Tạo chi tiết Tour không thành công!");
				return "info";
			}
		}else {
			info = new Info("Tạo chi tiết Tour","Bạn phải đăng ký Tour trước!");
			return "info";
		}
	}

	public String showTaoChiTietTour(){
		listKhachSan = new KhachSanBO().getAllSelect();
		return SUCCESS;
	}

	public int catChuoi(String chuoi) {
		int so=Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}

	public String taoMaTour(int maxTour) {
		int i, n = maxTour;
		for(i = 1; n > 10; i++)
			n/= 10;
		StringBuilder ma = new StringBuilder();
		for (int j = 0; j < i; j++)
			ma.append("0");
		return "CTTUR"+ ma.append(""+maxTour);
	}
}
