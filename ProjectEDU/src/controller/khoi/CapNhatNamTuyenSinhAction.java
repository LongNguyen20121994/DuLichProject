package controller.khoi;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.opensymphony.xwork2.ActionSupport;

import controller.dulich.LoginAction;
import model.bean.Info;
import model.bean.NamTuyenSinh;
import model.bo.khoi.NamTuyenSinhBO;

@SuppressWarnings("serial")
public class CapNhatNamTuyenSinhAction extends ActionSupport {
	private NamTuyenSinh namTS;
	private Info info;

	public NamTuyenSinh getNamTS() {
		return namTS;
	}

	public void setNamTS(NamTuyenSinh namTS) {
		this.namTS = namTS;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	
	public String showCapNhatNamTuyenSinh(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(namTS == null){
			namTS = new NamTuyenSinh();
			Date date = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy");
			String getDate = df.format(date);
			namTS.setNamTS(Integer.parseInt(getDate));
		}
		return SUCCESS;
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
		if(new NamTuyenSinhBO().updateNamTuyenSinh(namTS)){
			info = new Info("Thông báo", "<font style='color:blue'>Cập nhật thành công!</font>");
			return INPUT;
		} else {
			info = new Info("Thông báo", "<font style='color:red'>Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!</font>");
			return INPUT;
		}
	}
}
