package controller.dulich;

import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import controller.khoi.LoginAction;
import model.bean.GiangVien;
import model.bean.GiaoVien;
import model.bean.Info;
import model.bean.QuanLyCumThi;
import model.bean.QuanTriVien;
import model.bean.ThiSinh;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.GiaoVienBO;
import model.bo.khoi.QuanLyCumThiBO;
import model.bo.khoi.QuanTriVienBO;
import model.bo.khoi.ThiSinhBO;

@SuppressWarnings("serial")
public class KichHoatTaiKhoanAction extends ActionSupport {
	private String account;
	private HashMap<String,String> list;
	private List<ThiSinh> listThiSinh;
	private List<GiaoVien> listGiaoVien;
	private List<GiangVien> listGiangVien;
	private List<QuanLyCumThi> listQuanLyCumThi;
	private List<QuanTriVien> listQuanTriVien;
	private String[] listSoCMND;
	private String btnSua;
	private String btnXoa;
	private Info info;
	
	public String getBtnSua() {
		return btnSua;
	}

	public void setBtnSua(String btnSua) {
		this.btnSua = btnSua;
	}

	public String getBtnXoa() {
		return btnXoa;
	}

	public void setBtnXoa(String btnXoa) {
		this.btnXoa = btnXoa;
	}

	public String[] getListSoCMND() {
		return listSoCMND;
	}

	public void setListSoCMND(String[] listSoCMND) {
		this.listSoCMND = listSoCMND;
	}

	public List<GiangVien> getListGiangVien() {
		return listGiangVien;
	}

	public void setListGiangVien(List<GiangVien> listGiangVien) {
		this.listGiangVien = listGiangVien;
	}

	public List<QuanLyCumThi> getListQuanLyCumThi() {
		return listQuanLyCumThi;
	}

	public void setListQuanLyCumThi(List<QuanLyCumThi> listQuanLyCumThi) {
		this.listQuanLyCumThi = listQuanLyCumThi;
	}

	public List<QuanTriVien> getListQuanTriVien() {
		return listQuanTriVien;
	}

	public void setListQuanTriVien(List<QuanTriVien> listQuanTriVien) {
		this.listQuanTriVien = listQuanTriVien;
	}

	public List<GiaoVien> getListGiaoVien() {
		return listGiaoVien;
	}

	public void setListGiaoVien(List<GiaoVien> listGiaoVien) {
		this.listGiaoVien = listGiaoVien;
	}

	public List<ThiSinh> getListThiSinh() {
		return listThiSinh;
	}

	public void setListThiSinh(List<ThiSinh> listThiSinh) {
		this.listThiSinh = listThiSinh;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public HashMap<String, String> getList() {
		return list;
	}

	public void setList(HashMap<String, String> list) {
		this.list = list;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String execute() throws Exception {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(listSoCMND != null && listSoCMND.length > 0){
			if(btnSua != null){
				btnSua = null;
				if (account.equals("1")) {
					new ThiSinhBO().kichHoatTaiKhoan(listSoCMND, true, false);
				}
				if (account.equals("2")) {
					new GiaoVienBO().kichHoatTaiKhoan(listSoCMND, true, false);
				}
				if (account.equals("3")) {
					new GiangVienBO().kichHoatTaiKhoan(listSoCMND, true, false);
				}
				if (account.equals("4")) {
					new QuanLyCumThiBO().kichHoatTaiKhoan(listSoCMND, true, false);
				}
				if (account.equals("5")) {
					new QuanTriVienBO().kichHoatTaiKhoan(listSoCMND, true, false);
				}
			} else {
				if(btnXoa != null){
					btnXoa = null;
					if (account.equals("1")) {
						new ThiSinhBO().xoaTaiKhoan(listSoCMND);
					}
					if (account.equals("2")) {
						new GiaoVienBO().xoaTaiKhoan(listSoCMND);
					}
					if (account.equals("3")) {
						new GiangVienBO().xoaTaiKhoan(listSoCMND);
					}
					if (account.equals("4")) {
						new QuanLyCumThiBO().xoaTaiKhoan(listSoCMND);
					}
					if (account.equals("5")) {
						new QuanTriVienBO().xoaTaiKhoan(listSoCMND);
					}
				}
			}
		}
		return INPUT;
	}
	
	public String showKichHoatTaiKhoan1(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if (account == null) {
			account = "1";
		}
		if (account.equals("1")) {
			listThiSinh = new ThiSinhBO().getListThiSinhByTrangThai(false, false);
		}
		if (account.equals("2")) {
			listGiaoVien = new GiaoVienBO().getListGiaoVienByTrangThai(false, false);
		}
		if (account.equals("3")) {
			listGiangVien = new GiangVienBO().getListGiangVienByTrangThai(false, false);
		}
		if (account.equals("4")) {
			listQuanLyCumThi = new QuanLyCumThiBO().getListQuanLyCumThiByTrangThai(false, false);
		}
		if (account.equals("5")) {
			listQuanTriVien = new QuanTriVienBO().getListQuanTriVienByTrangThai(false, false);
		}
		list = new HashMap<String,String>();
		list.put("1", "Thí sinh đăng ký dự thi");
		list.put("2", "Trư�?ng trung h�?c phổ thông");
		list.put("3", "Trư�?ng đại h�?c - cao đẳng");
		list.put("4", "Cụm thi - �?ịa điểm thi");
		list.put("5", "Quản trị viên");
		return SUCCESS;
	}
	
	public String ajaxKichHoat1(){
		if (account.equals("1")) {
			listThiSinh = new ThiSinhBO().getListThiSinhByTrangThai(false, false);
		}
		if (account.equals("2")) {
			listGiaoVien = new GiaoVienBO().getListGiaoVienByTrangThai(false, false);
		}
		if (account.equals("3")) {
			listGiangVien = new GiangVienBO().getListGiangVienByTrangThai(false, false);
		}
		if (account.equals("4")) {
			listQuanLyCumThi = new QuanLyCumThiBO().getListQuanLyCumThiByTrangThai(false, false);
		}
		if (account.equals("5")) {
			listQuanTriVien = new QuanTriVienBO().getListQuanTriVienByTrangThai(false, false);
		}
		return INPUT;
	}
}
