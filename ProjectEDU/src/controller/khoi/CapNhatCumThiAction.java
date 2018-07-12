package controller.khoi;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.CumThi;
import model.bean.Info;
import model.bo.khoi.CumThiBO;

@SuppressWarnings("serial")
public class CapNhatCumThiAction extends ActionSupport {
	private CumThi ct;
	private Info info;
	private List<CumThi> list;
	private List<String> listMaCT;
	private String maCT;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;
		
	public String getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(String btnDelete) {
		this.btnDelete = btnDelete;
	}

	public String getBtnAddNew() {
		return btnAddNew;
	}

	public void setBtnAddNew(String btnAddNew) {
		this.btnAddNew = btnAddNew;
	}

	public String getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(String btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public String getClassInput() {
		return classInput;
	}

	public void setClassInput(String classInput) {
		this.classInput = classInput;
	}

	public String getClassList() {
		return classList;
	}

	public void setClassList(String classList) {
		this.classList = classList;
	}

	public String getMaCT() {
		return maCT;
	}

	public void setMaCT(String maCT) {
		this.maCT = maCT;
	}

	public CumThi getCt() {
		return ct;
	}

	public void setCt(CumThi ct) {
		this.ct = ct;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<CumThi> getList() {
		return list;
	}

	public void setList(List<CumThi> list) {
		this.list = list;
	}

	public List<String> getListMaCT() {
		return listMaCT;
	}

	public void setListMaCT(List<String> listMaCT) {
		this.listMaCT = listMaCT;
	}
	
	public String showCapNhatCumThi(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(classList == null) {
			classInput="active";
		}
		list = new CumThiBO().getAll();
		if(maCT != null) {
			ct = new CumThiBO().getInfo(maCT);
			if(ct == null) {
				maCT = null;
			}
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
		if(btnUpdate != null){
			if(new CumThiBO().updateCumThi(ct)){
				info = new Info("","<font style='color:blue;'>"+ ct.getTenCumThi() +" đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ ct.getTenCumThi() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				if(new CumThiBO().insertCumThi(ct)){
					info = new Info("","<font style='color:blue;'>"+ ct.getTenCumThi() +" đã được thêm vào danh sách!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ ct.getTenCumThi() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListCumThi(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnAddNew == null){
			if(listMaCT == null || listMaCT.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn cụm thi trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaCT.size() == 1) {
						maCT = listMaCT.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng sửa chỉ áp dụng cho 1 cụm thi!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new CumThiBO().deleteListCumThi(listMaCT)){
							info = new Info("","<font style='color:red;'>Đã xóa thành công!</font><br/>");
						}
						classList = "active";
					}
				}
			}
		}
		return SUCCESS;
	}
	
}
