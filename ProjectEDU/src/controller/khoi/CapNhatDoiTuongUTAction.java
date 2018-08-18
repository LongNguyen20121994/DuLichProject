package controller.khoi;

import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import controller.dulich.LoginAction;
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
					info = new Info("","<font style='color:blue;'>"+ dt.getTenDT() +" Ä‘Ã£ Ä‘Æ°á»£c cáº­p nháº­t!</font><br/>");
				} else {
					info = new Info("","<font style='color:red;'>"+ dt.getTenDT() +" cáº­p nháº­t khÃ´ng thÃ nh cÃ´ng!</font><br/>");
				}
				classList = "active";
			} else {
				if(btnAddNew != null){
					if(new DoiTuongUTBO().insertDoiTuongUT(dt)){
						info = new Info("","<font style='color:blue;'>"+ dt.getTenDT() +" Ä‘Ã£ thÃªm thÃ nh cÃ´ng!</font><br/>");
						classList = "active";
					} else {
						info = new Info("","<font style='color:red;'>"+ dt.getTenDT() +" thÃªm khÃ´ng thÃ nh cÃ´ng!</font><br/>");
						classInput = "active";
					}
				}
			}
		} catch (Exception e) {
			info = new Info("","<font style='color:red;'>Lá»—i : "+ e.getMessage() +"</font>");
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
				info = new Info("","<font style='color:red;'>Vui lÃ²ng chá»�n khu vá»±c Æ°u tiÃªn trÆ°á»›c khi thao tÃ¡c!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaDT.size() == 1) {
						maDT = listMaDT.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chá»©c nÄƒng sá»­a chá»‰ Ã¡p dá»¥ng cho 1 khu vá»±c!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new DoiTuongUTBO().deleteListDoiTuongUT(listMaDT)){
							info = new Info("","<font style='color:red;'>Ä�Ã£ xÃ³a thÃ nh cÃ´ng!</font><br/>");
						}
						classList = "active";
					}
				}
			}
		}
		return SUCCESS;
	}
}
