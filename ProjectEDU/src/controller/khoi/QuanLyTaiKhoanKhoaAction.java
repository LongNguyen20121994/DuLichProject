 package controller.khoi;

import java.util.HashMap;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import controller.dulich.LoginAction;
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
public class QuanLyTaiKhoanKhoaAction extends ActionSupport {
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
					new ThiSinhBO().kichHoatTaiKhoan(listSoCMND, true, true);
				}
				if (account.equals("2")) {
					new GiaoVienBO().kichHoatTaiKhoan(listSoCMND, true, true);
				}
				if (account.equals("3")) {
					new GiangVienBO().kichHoatTaiKhoan(listSoCMND, true, true);
				}
				if (account.equals("4")) {
					new QuanLyCumThiBO().kichHoatTaiKhoan(listSoCMND, true, true);
				}
				if (account.equals("5")) {
					new QuanTriVienBO().kichHoatTaiKhoan(listSoCMND, true, true);
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
	
	public String showQuanLyTaiKhoanKhoa(){
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
			listThiSinh = new ThiSinhBO().getListThiSinhByTrangThai(false, true);
		}
		if (account.equals("2")) {
			listGiaoVien = new GiaoVienBO().getListGiaoVienByTrangThai(false, true);
		}
		if (account.equals("3")) {
			listGiangVien = new GiangVienBO().getListGiangVienByTrangThai(false, true);
		}
		if (account.equals("4")) {
			listQuanLyCumThi = new QuanLyCumThiBO().getListQuanLyCumThiByTrangThai(false, true);
		}
		if (account.equals("5")) {
			listQuanTriVien = new QuanTriVienBO().getListQuanTriVienByTrangThai(false,true);
		}
		list = new HashMap<String,String>();
		list.put("1", "ThÃ­ sinh Ä‘Äƒng kÃ½ dá»± thi");
		list.put("2", "TrÆ°á»�ng trung há»�c phá»• thÃ´ng");
		list.put("3", "TrÆ°á»�ng Ä‘áº¡i há»�c - cao Ä‘áº³ng");
		list.put("4", "Cá»¥m thi - Ä�á»‹a Ä‘iá»ƒm thi");
		list.put("5", "Quáº£n trá»‹ viÃªn");
		return SUCCESS;
	}
	
	public String ajaxTaiKhoanKhoa(){
		if (account.equals("1")) {
			listThiSinh = new ThiSinhBO().getListThiSinhByTrangThai(false, true);
		}
		if (account.equals("2")) {
			listGiaoVien = new GiaoVienBO().getListGiaoVienByTrangThai(false, true);
		}
		if (account.equals("3")) {
			listGiangVien = new GiangVienBO().getListGiangVienByTrangThai(false, true);
		}
		if (account.equals("4")) {
			listQuanLyCumThi = new QuanLyCumThiBO().getListQuanLyCumThiByTrangThai(false, true);
		}
		if (account.equals("5")) {
			listQuanTriVien = new QuanTriVienBO().getListQuanTriVienByTrangThai(false, true);
		}
		return INPUT;
	}
}