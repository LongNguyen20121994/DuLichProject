package controller.khoi;

import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import controller.dulich.LoginAction;
import model.bean.Info;
import model.bean.KhuVucUT;
import model.bo.khoi.KhuVucUTBO;

@SuppressWarnings("serial")
public class CapNhatKhuVucUTAction extends ActionSupport {
	private KhuVucUT kv;
	private List<KhuVucUT> list;
	private List<String> listMaKV;
	private String maKV;
	private Info info;
	private String classInput;
	private String classList;
	private String btnUpdate;
	private String btnAddNew;
	private String btnDelete;
			
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

	public List<String> getListMaKV() {
		return listMaKV;
	}

	public void setListMaKV(List<String> listMaKV) {
		this.listMaKV = listMaKV;
	}

	public String getClassList() {
		return classList;
	}

	public void setClassList(String classList) {
		this.classList = classList;
	}

	public String getClassInput() {
		return classInput;
	}

	public void setClassInput(String classInput) {
		this.classInput = classInput;
	}

	public String getMaKV() {
		return maKV;
	}

	public void setMaKV(String maKV) {
		this.maKV = maKV;
	}

	public List<KhuVucUT> getList() {
		return list;
	}

	public void setList(List<KhuVucUT> list) {
		this.list = list;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public KhuVucUT getKv() {
		return kv;
	}

	public void setKv(KhuVucUT kv) {
		this.kv = kv;
	}
	
	public String showCapNhatKhuVucUT(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		KhuVucUTBO kvbo = new KhuVucUTBO();
		if(classList == null){
			classInput = "active";
		}
		list = kvbo.getAllSelect();
		if(maKV != null){
			List<KhuVucUT> listKV = list.stream().filter(p -> p.getMaKV().equals(maKV)).collect(Collectors.toList());
			if(listKV != null && listKV.size() > 0){
				kv = listKV.get(0);
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
				if(new KhuVucUTBO().updateKhuVucUT(kv)){
					info = new Info("","<font style='color:blue;'>"+ kv.getTenKhuVuc() +" Ä‘Ã£ Ä‘Æ°á»£c cáº­p nháº­t!</font><br/>");
				} else {
					info = new Info("","<font style='color:red;'>"+ kv.getTenKhuVuc() +" cáº­p nháº­t khÃ´ng thÃ nh cÃ´ng!</font><br/>");
				}
				classList = "active";
			} else {
				if(btnAddNew != null){
					if(new KhuVucUTBO().insertKhuVucUT(kv)){
						info = new Info("","<font style='color:blue;'>"+ kv.getTenKhuVuc() +" Ä‘Ã£ thÃªm thÃ nh cÃ´ng!</font><br/>");
						classList = "active";
					} else {
						info = new Info("","<font style='color:red;'>"+ kv.getTenKhuVuc() +" thÃªm khÃ´ng thÃ nh cÃ´ng!</font><br/>");
						classInput = "active";
					}
				}
			}
		} catch (Exception e) {
			info = new Info("","<font style='color:red;'>Lá»—i : "+ e.getMessage() +"</font>");
		}
		return SUCCESS;
	}
	
	public String capNhatListKhuVucUT(){
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		
		if(btnAddNew == null){
			if(listMaKV == null || listMaKV.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lÃ²ng chá»�n khu vá»±c Æ°u tiÃªn trÆ°á»›c khi thao tÃ¡c!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaKV.size() == 1) {
						maKV = listMaKV.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chá»©c nÄƒng sá»­a chá»‰ Ã¡p dá»¥ng cho 1 khu vá»±c!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new KhuVucUTBO().deleteListKhuVucUT(listMaKV)){
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
