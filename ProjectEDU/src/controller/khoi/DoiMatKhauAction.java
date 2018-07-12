package controller.khoi;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.Info;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.GiaoVienBO;
import model.bo.khoi.QuanLyCumThiBO;
import model.bo.khoi.QuanTriVienBO;
import model.bo.khoi.ThiSinhBO;

@SuppressWarnings("serial")
public class DoiMatKhauAction extends ActionSupport {
	private String account;
	private String soCMND;
	private String matKhauMoi;
	private String matKhauRe;
	private boolean logined;
	private Info info;

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

	public String getMatKhauMoi() {
		return matKhauMoi;
	}

	public void setMatKhauMoi(String matKhauMoi) {
		this.matKhauMoi = matKhauMoi;
	}

	public String getMatKhauRe() {
		return matKhauRe;
	}

	public void setMatKhauRe(String matKhauRe) {
		this.matKhauRe = matKhauRe;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	@Override
	public String execute() throws Exception {
		boolean success = false;
		if(matKhauMoi.equals(matKhauRe)){
			if("1".equals(account)){
				success = new ThiSinhBO().doiMatKhau(soCMND,Library.md5(matKhauMoi));
			}
			if("2".equals(account)){
				success = new GiaoVienBO().doiMatKhau(soCMND,Library.md5(matKhauMoi));
			}
			if("3".equals(account)){
				success = new GiangVienBO().doiMatKhau(soCMND,Library.md5(matKhauMoi));
			}
			if("4".equals(account)){
				success = new QuanLyCumThiBO().doiMatKhau(soCMND,Library.md5(matKhauMoi));
			}
			if("5".equals(account)){
				success = new QuanTriVienBO().doiMatKhau(soCMND,Library.md5(matKhauMoi));
			}
		}
		if(success){
			Map<String, Object> session = ActionContext.getContext().getSession();
			session.remove("soCMND");
			session.remove("hoTen");
			session.remove("hinhAnh");
			session.remove("account");
			info = new Info("Đổi mật khẩu", "Mật khẩu của bạn đã được thay đổi. Vui lòng đăng nhập lại!");
		} else {
			info = new Info("Đổi mật khẩu", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
		}
		return SUCCESS;
	}
	public String showDoiMatKhau(){
		if (logined) {
			return SUCCESS;
		} else {
			Map<String, Object> session = ActionContext.getContext().getSession();
			if (session.get("soCMND") != null) {
				soCMND = (String) session.get("soCMND");
				account = (String) session.get("account");
				return SUCCESS;
			} else {
				info = new Info("Đổi mật khẩu.","Vui lòng đăng nhập trước khi thực hiện thao tác!");
				return ERROR;
			}
		}
	}

}
