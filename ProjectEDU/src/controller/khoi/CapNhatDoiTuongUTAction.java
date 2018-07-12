package controller.khoi;

import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DoiTuongUT;
import model.bean.Info;
import model.bo.khoi.DoiTuongUTBO;

@SuppressWarnings("serial")
public class CapNhatDoiTuongUTAction extends ActionSupport {
	private DoiTuongUT dt;
	private List<DoiTuongUT> list;
	private List<String> listMaDT;
	private String maDT;
	private Info info;
	private String classInput;
	private String classList;
	private String btnUpdate;
	private String btnAddNew;
	private String btnDelete;

	public DoiTuongUT getDt() {
		return dt;
	}

	public void setDt(DoiTuongUT dt) {
		this.dt = dt;
	}

	public List<DoiTuongUT> getList() {
		return list;
	}

	public void setList(List<DoiTuongUT> list) {
		this.list = list;
	}

	public List<String> getListMaDT() {
		return listMaDT;
	}

	public void setListMaDT(List<String> listMaDT) {
		this.listMaDT = listMaDT;
	}

	public String getMaDT() {
		return maDT;
	}

	public void setMaDT(String maDT) {
		this.maDT = maDT;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
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

	public String getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(String btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public String getBtnAddNew() {
		return btnAddNew;
	}

	public void setBtnAddNew(String btnAddNew) {
		this.btnAddNew = btnAddNew;
	}

	public String getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(String btnDelete) {
		this.btnDelete = btnDelete;
	}

	public String showCapNhatDoiTuongUT(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		DoiTuongUTBO dtbo = new DoiTuongUTBO();
		if(classList == null){
			classInput = "active";
		}
		list = dtbo.getAll();
		if(maDT != null){
			List<DoiTuongUT> listDT = list.stream().filter(p -> p.getMaDT().equals(maDT)).collect(Collectors.toList());
			if(listDT != null && listDT.size() > 0){
				dt = listDT.get(0);
			}
		}
		return SUCCESS;
	}

	@Override
	public String execute() {
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		try{
			if(btnUpdate != null){
				if(new DoiTuongUTBO().updateDoiTuongUT(dt)){
					info = new Info("","<font style='color:blue;'>"+ dt.getTenDT() +" đã được cập nhật!</font><br/>");
				} else {
					info = new Info("","<font style='color:red;'>"+ dt.getTenDT() +" cập nhật không thành công!</font><br/>");
				}
				classList = "active";
			} else {
				if(btnAddNew != null){
					if(new DoiTuongUTBO().insertDoiTuongUT(dt)){
						info = new Info("","<font style='color:blue;'>"+ dt.getTenDT() +" đã thêm thành công!</font><br/>");
						classList = "active";
					} else {
						info = new Info("","<font style='color:red;'>"+ dt.getTenDT() +" thêm không thành công!</font><br/>");
						classInput = "active";
					}
				}
			}
		} catch (Exception e) {
			info = new Info("","<font style='color:red;'>Lỗi : "+ e.getMessage() +"</font>");
		}
		return SUCCESS;
	}
	
	public String capNhatListDoiTuongUT(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnAddNew == null){
			if(listMaDT == null || listMaDT.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn khu vực ưu tiên trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaDT.size() == 1) {
						maDT = listMaDT.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng sửa chỉ áp dụng cho 1 khu vực!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new DoiTuongUTBO().deleteListDoiTuongUT(listMaDT)){
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
