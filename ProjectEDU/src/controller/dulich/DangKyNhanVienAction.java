package controller.dulich;

import java.util.Map;
import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import controller.khoi.LoginAction;
import model.bean.DLNhanVien;
import model.bean.Info;
import model.bo.DLNhanVienBO;

@SuppressWarnings("serial")
public class DangKyNhanVienAction extends ActionSupport {
	private DLNhanVien nv;
	private String hinhAnh;
	private Info info;
	
	public DLNhanVien getNv() {
		return nv;
	}
	public void setNv(DLNhanVien nv) {
		this.nv = nv;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	
	@Override
	public String execute() throws Exception {
		Random random = new Random();
		nv.setMatKhau(String.valueOf(random.nextInt()));
		if(hinhAnh != null){
			nv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, nv.getSoCMND()));
		} else {
			nv.setHinhAnh("images/default.jpg");
		}
		DLNhanVienBO nvbo = new DLNhanVienBO();
		if(nvbo.insertNhanVien(nv)){
			info = new Info("Đăng ký tài khoản","Đăng ký thành công.<br/>Mật khẩu đã gửi tới email của bạn!");
			return "info";
		} else {
			info = new Info("Đăng ký tài khoản","Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}
	public String showDangKyNhanVien(){
		return SUCCESS;
	}
	
	public String showCapNhatNhanVien(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("6");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		nv = new DLNhanVienBO().getInfo((String)session.get("soCMND"));
		return SUCCESS;
	}
	public String capNhatNhanVien(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("6");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(hinhAnh != null){
			nv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, nv.getSoCMND()));
		} else {
			nv.setHinhAnh((String)session.get("hinhAnh"));
		}
		DLNhanVienBO nvbo = new DLNhanVienBO();
		if(nvbo.updateNhanVien(nv)){
			session.replace("hoTen", nv.getHoTen());
			session.replace("hinhAnh", nv.getHinhAnh());
			info = new Info("Cập nhật tài khoản Admin","Cập nhật thành công!");
			return "info";
		} else {
			info = new Info("Cập nhật tài khoản Admin","Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}
}
