package controller.dulich;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLChiTietTour;
import model.bean.Info;
import model.bo.ChiTietTourBO;
import model.bo.DLTinhBO;
import model.bo.DLTourBO;
import model.bo.KhachSanBO;

@SuppressWarnings("serial")
public class ChiTietTourAction extends ActionSupport {
	private DLChiTietTour ct;
	private HashMap<String,String> listTinh;
	private HashMap<String, String> listTour;
	private HashMap<String, String> listKhachSan;
	private String maTinh;
	private Info info;
	
	public DLChiTietTour getCt() {
		return ct;
	}
	public void setCt(DLChiTietTour ct) {
		this.ct = ct;
	}
	public HashMap<String, String> getListTinh() {
		return listTinh;
	}
	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
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
	public String getMaTinh() {
		return maTinh;
	}
	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
	@Override
	public String execute() throws Exception {
		if(ct.getMaTour() != null) {
			String timMax = new ChiTietTourBO().getMaxRecord();
			int maxTour = 0;
			if(timMax != null) {
				maxTour = catChuoi(timMax) + 1;
			} else {
				maxTour = 1;   
			}
			ct.setMaChiTietTour(taoMa(maxTour));
			ChiTietTourBO ctTourBo = new ChiTietTourBO();
			if(ctTourBo.insertChiTietTour(ct)){
				info = new Info("Tạo chi tiết Tour","Tạo chi tiết tTourour thành công!");
				return "info";
			} else {
				info = new Info("Tạo chi tiết Tour","Tạo chi tiết Tour không thành công!");
				return "info";
			}
		} else {
			info = new Info("Tạo chi tiết Tour","Bạn phải chọn Tour!");
			return "info";
		}
	}
	
	public String showChiTietTour(){
		listTinh = new DLTinhBO().getAllSelect();
		listTour = new DLTourBO().getAllByMaTinh(maTinh);
		listKhachSan = new KhachSanBO().getAllByMaTinh(maTinh);
		return SUCCESS;
	}
	
	public int catChuoi(String chuoi) {
		int so=Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}
	
	public String taoMa(int max) {
		int i, n = max;
		for(i = 1; n > 10; i++)
			n/= 10;
		StringBuilder ma = new StringBuilder();		
		for (int j = 0; j < 10 - i; j++)
			ma.append("0");
		return "CTT"+ ma.append(max);
	}
	
	public String showCapNhatChiTietTour(){
		listTinh = new DLTinhBO().getAllSelect();
		listTour = new DLTourBO().getAllByMaTinh(maTinh);
		listKhachSan = new KhachSanBO().getAllByMaTinh(maTinh);
		return SUCCESS;
	}
}
