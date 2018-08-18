package controller.dulich;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bo.DLNhanVienBO;

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
		/*list.put("1", "ThÃ­ sinh Ä‘Äƒng kÃ½ dá»± thi");
		list.put("2", "TrÆ°á»?ng trung há»?c phá»• thÃ´ng");
		list.put("3", "TrÆ°á»?ng Ä‘áº¡i há»?c - cao Ä‘áº³ng");
		list.put("4", "Cá»¥m thi - Ä?á»‹a Ä‘iá»ƒm thi");
		list.put("5", "Quáº£n trá»‹ viÃªn");*/
		list.put("1", "Admin trip");
		list.put("2", "Nhân viên");
		list.put("3", "Khách hàng");
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		if(account!= null){
			boolean check = false;
			/*if (account.equals("1")) {
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
			}*/
			if (account.equals("1")) {
				check = new DLNhanVienBO().quenMatKhau(soCMND,email);
			}
			if(check){
				info = new Info("Thông báo", "Đổi mật khẩu thành công.<br/><small>"
						+ "Vui lòng kiểm tra email đăng ký để lấy thông tin đăng nhập. </small>");
			} else {
				info = new Info("Thông báo", "Tài khoản hoặc email cung cấp không đúng. "
						+ "<br/><small>vui lòng kiểm tra lại hoặc liên hệ với chúng tôi qua email.</small>");
			}
			return "info";
		} else {
			return INPUT;
		}
	}
	
}
