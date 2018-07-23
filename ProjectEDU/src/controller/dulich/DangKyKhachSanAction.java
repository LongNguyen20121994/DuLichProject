package controller.dulich;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.DLKhachSan;
import model.bean.Info;
import model.bo.DLTinhBO;
import model.bo.DLTourBO;
import model.bo.KhachSanBO;
import model.bo.khoi.TinhThanhPhoBO;

@SuppressWarnings("serial")
public class DangKyKhachSanAction extends ActionSupport {
	private DLKhachSan ks;
	private String hinhAnh;
	private HashMap<String,String> listTinh;
	private Info info;
	private String maKS;
	
	public DLKhachSan getKs() {
		return ks;
	}
	public void setKs(DLKhachSan ks) {
		this.ks = ks;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public HashMap<String, String> getListTinh() {
		return listTinh;
	}
	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String getMaKS() {
		return maKS;
	}
	public void setMaKS(String maKS) {
		this.maKS = maKS;
	}

	@Override
	public String execute() throws Exception {
		listTinh = new DLTinhBO().getAllSelect();
		String timMax = new DLTourBO().getMaxRecord();
		int max = 0;
		if(timMax != null) {
			max = catChuoi(timMax) + 1;
		}
		else {
			max = 1;   
		}
		ks.setMaKS(taoMa(max));
		if(hinhAnh != null){
			ks.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, ""+ taoMa(max)));
		} else {
			ks.setHinhAnh("images/default.jpg");
		}
		KhachSanBO ksbo = new KhachSanBO();
		if(ksbo.insertKhachSan(ks)){
			info = new Info("Tạo khách sạn","Tạo Khách sạn thành công.!");
			return "info";
		} else {
			info = new Info("Tạo khách sạn","Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}

	public String showDangKyKhachSan(){
		listTinh = new DLTinhBO().getAllSelect();
		return SUCCESS;
	}
	
	public String showCapNhatKhachSan(){
		ks = new KhachSanBO().getInfo(maKS);
		listTinh = new TinhThanhPhoBO().getAllSelect();
		return SUCCESS;
	}
	public String capNhatKhachSan(){
		/*info = new LoginAction().checkLogin("6");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}*/
		if(hinhAnh != null){
			ks.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, ks.getMaKS()));
		}
		KhachSanBO ksbo = new KhachSanBO();
		if(ksbo.updateKhachSan(ks)){
			info = new Info("Cập nhật khách sạn","Cập nhật thành công!");
			return "info";
		} else {
			info = new Info("Cập nhật khách sạn","Có lỗi trong quá trình thực hiện, Vui lòng kiểm tra lại!");
			return "info";
		}
	}
	
	public String taoMa(int max) {
		int i, n = max;
		for(i = 1; n > 10; i++)
			n/= 10;
		StringBuilder ma = new StringBuilder();		
		for (int j = 0; j < 10 - i; j++)
			ma.append("0");
		return "KHS"+ ma.append(max);
	}
	
	public int catChuoi(String chuoi) {
		int so=Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}
}
