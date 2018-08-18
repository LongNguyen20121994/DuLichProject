package controller.khoi;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import controller.dulich.LoginAction;
import model.bean.DotXetTuyen;
import model.bean.Info;
import model.bo.khoi.DotXetTuyenBO;

@SuppressWarnings("serial")
public class CapNhatDotXetTuyenAction extends ActionSupport {
	private DotXetTuyen dxt;
	private List<DotXetTuyen> listDotXT;
	private String maDotXT;
	private String btnXoa;
	private String btnMo;
	private String btnDong;
	private Info info;
	
	public String getMaDotXT() {
		return maDotXT;
	}

	public void setMaDotXT(String maDotXT) {
		this.maDotXT = maDotXT;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getBtnXoa() {
		return btnXoa;
	}

	public void setBtnXoa(String btnXoa) {
		this.btnXoa = btnXoa;
	}

	public String getBtnMo() {
		return btnMo;
	}

	public void setBtnMo(String btnMo) {
		this.btnMo = btnMo;
	}

	public String getBtnDong() {
		return btnDong;
	}

	public void setBtnDong(String btnDong) {
		this.btnDong = btnDong;
	}

	public DotXetTuyen getDxt() {
		return dxt;
	}

	public void setDxt(DotXetTuyen dxt) {
		this.dxt = dxt;
	}

	public List<DotXetTuyen> getListDotXT() {
		return listDotXT;
	}

	public void setListDotXT(List<DotXetTuyen> listDotXT) {
		this.listDotXT = listDotXT;
	}
	
	public String showCapNhatDotXetTuyen(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listDotXT = new DotXetTuyenBO().getListDotXT();
		for(DotXetTuyen d : listDotXT){
			if(d.isTrangThai()){
				maDotXT = d.getMaDotXT();
			}
		}
		dxt = new DotXetTuyen();
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
		if(new DotXetTuyenBO().insertDotXetTuyen(dxt)){
			info = new Info("Thông báo", "<font style='color:blue'>Thêm thành công!</font>");
			return INPUT;
		} else {
			info = new Info("Thông báo", "<font style='color:red'>Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!</font>");
			return INPUT;
		}
	}
	
	public String capNhatDotXT(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnDong != null){
			new DotXetTuyenBO().updateTrangThai(maDotXT, false);
		}
		if(btnMo != null){
			new DotXetTuyenBO().updateTrangThai(maDotXT, true);
		}
		if(btnXoa != null){
			new DotXetTuyenBO().deleteDotXetTuyen(maDotXT);
		}
		return INPUT;
	}
}
