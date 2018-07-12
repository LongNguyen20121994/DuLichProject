package controller.khoi;

import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bean.MonThi;
import model.bo.khoi.MonThiBO;

@SuppressWarnings("serial")
public class CapNhatMonThiAction extends ActionSupport {
	private MonThi mt;
	private Info info;
	private List<MonThi> list;
	private List<String> listMaMT;
	private String maMT;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;

	public List<MonThi> getList() {
		return list;
	}

	public void setList(List<MonThi> list) {
		this.list = list;
	}

	public List<String> getListMaMT() {
		return listMaMT;
	}

	public void setListMaMT(List<String> listMaMT) {
		this.listMaMT = listMaMT;
	}

	public String getMaMT() {
		return maMT;
	}

	public void setMaMT(String maMT) {
		this.maMT = maMT;
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

	public String getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(String btnDelete) {
		this.btnDelete = btnDelete;
	}

	public MonThi getMt() {
		return mt;
	}

	public void setMt(MonThi mt) {
		this.mt = mt;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
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
			if(new MonThiBO().updateMonThi(mt)){
				info = new Info("","<font style='color:blue;'>"+ mt.getTenMonThi() +" đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ mt.getTenMonThi() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				if(new MonThiBO().insertMonThi(mt)){
					info = new Info("","<font style='color:blue;'>"+ mt.getTenMonThi() +" đã thêm thành công!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ mt.getTenMonThi() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}
	public String showThemMonThi(){
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
		list = new MonThiBO().getAll();
		if(maMT != null) {
			List<MonThi> listMT = list.stream().filter(p -> p.getMaMonThi().equals(maMT)).collect(Collectors.toList());
			if(listMT != null && listMT.size() > 0){
				mt = listMT.get(0);
			}
			if(mt == null) {
				maMT = null;
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListMonThi(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnAddNew == null){
			if(listMaMT == null || listMaMT.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn cụm thi trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaMT.size() == 1) {
						maMT = listMaMT.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng sửa chỉ áp dụng cho 1 cụm thi!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new MonThiBO().deleteListMonThi(listMaMT)){
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
