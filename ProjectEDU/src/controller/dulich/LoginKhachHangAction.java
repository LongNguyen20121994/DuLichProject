package controller.dulich;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.DLKhachHang;
import model.bean.Info;
import model.bo.KhachHangBO;

@SuppressWarnings("serial")
public class LoginKhachHangAction extends ActionSupport {
	private String makh;
	private String matKhau;
	private Info info;

	public String getMakh() {
		return makh;
	}

	public void setMakh(String makh) {
		this.makh = makh;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String execute() throws Exception {
		return loginKhachHang();
	}

	public String showLogin(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get("makh") == null){
			return SUCCESS;
		} else {
			info = new Info("Thông báo","Tài khoản của bạn đã được đăng nhập!.");
			return ERROR;
		}
	}

	public String loginKhachHang() {
		KhachHangBO login = new KhachHangBO();
		DLKhachHang tmp = login.getInfo(makh);
		if(tmp != null){
			if(tmp.getMatKhau().equals(Library.md5(matKhau))){
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("makh", tmp.getMaKH());
				session.put("hoTen", tmp.getHoTen());
				session.put("email", tmp.getEmail());
				session.put("account","3");
				return "success";
			} else {
				info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
				return "info";
			}
		} else {
			info = new Info("Đăng nhập","Tài khoản không tồn tại. Vui lòng kiểm tra lại!");
			return "info";
		}
	}
}
