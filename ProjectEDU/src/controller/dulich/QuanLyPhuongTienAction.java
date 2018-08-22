package controller.dulich;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLPhuongTien;
import model.bean.Info;
import model.bo.DLTinhBO;
import model.bo.PhuongTienBO;

@SuppressWarnings("serial")
public class QuanLyPhuongTienAction extends ActionSupport {
	private DLPhuongTien pt;
	private Info info;
	private List<DLPhuongTien> list;
	private List<String> listMaPT;
	private HashMap<String,String> listTinh;
	private String maPT;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}
	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}
	public DLPhuongTien getPt() {
		return pt;
	}
	public void setPt(DLPhuongTien pt) {
		this.pt = pt;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<DLPhuongTien> getList() {
		return list;
	}
	public void setList(List<DLPhuongTien> list) {
		this.list = list;
	}
	public List<String> getListMaPT() {
		return listMaPT;
	}
	public void setListMaPT(List<String> listMaPT) {
		this.listMaPT = listMaPT;
	}
	public String getMaPT() {
		return maPT;
	}
	public void setMaPT(String maPT) {
		this.maPT = maPT;
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
	@Override
	public String execute() throws Exception {
		info = new LoginAction().checkLogin("1");
		if (info != null) {
			info = new LoginAction().checkLogin("2");
			if (info != null) {
				if (info.getTieuDe() == null) {
					return "login";
				} else {
					return "info";
				}
			}
		}
		if(btnUpdate != null){
			if(new PhuongTienBO().updatePhuongTien(pt)){
				info = new Info("","<font style='color:blue;'>"+ pt.getTenPT() +" Đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ pt.getTenPT() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				String timMax = new PhuongTienBO().getMaxRecord();
				int max = 0;
				if(timMax != null) {
					max = catChuoi(timMax) + 1;
				}
				else {
					max = 1;
				}
				pt.setMaPT(taoMa(max));
				if(new PhuongTienBO().insertPhuongTien(pt)){
					info = new Info("","<font style='color:blue;'>"+ pt.getTenPT() +" Đã thêm thành công!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ pt.getTenPT() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}
	public String quanLyPhuongTien(){
		info = new LoginAction().checkLogin("1");
		if (info != null) {
			info = new LoginAction().checkLogin("2");
			if (info != null) {
				if (info.getTieuDe() == null) {
					return "login";
				} else {
					return "info";
				}
			}
		}
		if(classList == null) {
			classInput="active";
		}
		listTinh = new DLTinhBO().getAllSelect();
		list = new PhuongTienBO().getAll();
		if(maPT != null) {
			List<DLPhuongTien> listPT = list.stream().filter(p -> p.getMaPT().equals(maPT)).collect(Collectors.toList());
			if(listPT != null && listPT.size() > 0){
				pt = listPT.get(0);
			}
			if(pt == null) {
				maPT = null;
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListPhuongTien(){
		info = new LoginAction().checkLogin("1");
		if (info != null) {
			info = new LoginAction().checkLogin("2");
			if (info != null) {
				if (info.getTieuDe() == null) {
					return "login";
				} else {
					return "info";
				}
			}
		}
		if(btnAddNew == null){
			if(listMaPT == null || listMaPT.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn Phương Tiện trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaPT.size() == 1) {
						maPT = listMaPT.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng này chỉ dành cho 1 Phương Tiện!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new PhuongTienBO().deleteListPhuongTien(listMaPT)){
							info = new Info("","<font style='color:red;'>Đã xóa thành công!</font><br/>");
						}
						classList = "active";
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public String taoMa(int max) {
		int i, n = max;
		for (i = 1; n > 10; i++)
			n /= 10;
		StringBuilder ma = new StringBuilder();
		for (int j = 0; j < 7 - i; j++)
			ma.append("0");
		return "PHT" + ma.append(max);
	}
	
	public int catChuoi(String chuoi) {
		int so=Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}
}
