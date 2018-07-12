package controller.khoi;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.GiaoVienBO;
import model.bo.khoi.QuanLyCumThiBO;
import model.bo.khoi.QuanTriVienBO;
import model.bo.khoi.ThiSinhBO;

@SuppressWarnings("serial")
public class QuenMatKhauAction extends ActionSupport {
	private String account;
	private String soCMND;
	private String email;
	private Info info;
	private HashMap<String, String> list;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public HashMap<String, String> getList() {
		return list;
	}

	public void setList(HashMap<String, String> list) {
		this.list = list;
	}

	public String showQuenMatKhau() {
		list = new HashMap<String, String>();
		list.put("1", "Thí sinh đăng ký dự thi");
		list.put("2", "Trường trung học phổ thông");
		list.put("3", "Trường đại học - cao đẳng");
		list.put("4", "Cụm thi - Địa điểm thi");
		list.put("5", "Quản trị viên");
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		if(account!= null){
			boolean check = false;
			if (account.equals("1")) {
				check = new ThiSinhBO().quenMatKhau(soCMND,email);
			}
			if (account.equals("2")) {
				check = new GiaoVienBO().quenMatKhau(soCMND,email);
			}
			if (account.equals("3")) {
				check = new GiangVienBO().quenMatKhau(soCMND,email);
			}
			if (account.equals("4")) {
				check = new QuanLyCumThiBO().quenMatKhau(soCMND,email);
			}
			if (account.equals("5")) {
				check = new QuanTriVienBO().quenMatKhau(soCMND,email);
			}
			if(check){
				info = new Info("Thông báo", "Yêu cầu của bạn đã gửi thành công.<br/><small>"
						+ "Vui lòng kiểm tra email đăng ký để lấy thông tin khôi phục tài khoản. "
						+ "<br/>Nếu không nhận được thư bạn có thể kiểm tra lại thư mục spam hoặc gửi lại yêu cầu!</small>");
			} else {
				info = new Info("Thông báo", "Tài khoản hoặc email đăng ký của bạn cung cấp không đúng. "
						+ "<br/><small>vui lòng kiểm tra lại hoặc liên hệ với chúng tôi để được trợ giúp.</small>");
			}
			return "info";
		} else {
			return INPUT;
		}
	}
	
}
