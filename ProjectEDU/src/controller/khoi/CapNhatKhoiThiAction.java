package controller.khoi;

import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bean.KhoiThi;
import model.bo.khoi.KhoiThiBO;

@SuppressWarnings("serial")
public class CapNhatKhoiThiAction extends ActionSupport {
	private KhoiThi kt;
	private Info info;
	private List<KhoiThi> list;
	private List<String> listMaKT;
	private String maKT;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;
	
	public List<KhoiThi> getList() {
		return list;
	}

	public void setList(List<KhoiThi> list) {
		this.list = list;
	}

	public List<String> getListMaKT() {
		return listMaKT;
	}

	public void setListMaKT(List<String> listMaKT) {
		this.listMaKT = listMaKT;
	}

	public String getMaKT() {
		return maKT;
	}

	public void setMaKT(String maKT) {
		this.maKT = maKT;
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

	public KhoiThi getKt() {
		return kt;
	}

	public void setKt(KhoiThi kt) {
		this.kt = kt;
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
			if(new KhoiThiBO().updateKhoiThi(kt)){
				info = new Info("","<font style='color:blue;'>"+ kt.getTenKhoi() +" đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ kt.getTenKhoi() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				if(new KhoiThiBO().insertKhoiThi(kt)){
					info = new Info("","<font style='color:blue;'>"+ kt.getTenKhoi() +" đã thêm thành công!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ kt.getTenKhoi() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}

	public String showThemKhoiThi() {
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
		list = new KhoiThiBO().getAll();
		if(maKT != null) {
			List<KhoiThi> listKT = list.stream().filter(p -> p.getMaKhoi().equals(maKT)).collect(Collectors.toList());
			if(listKT != null && listKT.size() > 0){
				kt = listKT.get(0);
			}
			if(kt == null) {
				maKT = null;
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListKhoiThi(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnAddNew == null){
			if(listMaKT == null || listMaKT.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn cụm thi trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaKT.size() == 1) {
						maKT = listMaKT.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng sửa chỉ áp dụng cho 1 cụm thi!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new KhoiThiBO().deleteListKhoiThi(listMaKT)){
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
