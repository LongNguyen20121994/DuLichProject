package controller.longnt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.Info;
import model.bean.PhongThi;
import model.bo.longnt.DiaDiemThiBO;
import model.bo.longnt.PhongThiBO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CapNhatPhongThiAction extends ActionSupport{
	private PhongThi pt;
	private Info info;
	private List<PhongThi> list;
	private List<String> listMaPT;
	private String maPT;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;
	private HashMap<String, String> listDiaDiemThi;
	
	public String showCapNhatPhongThi(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("4".equals(account)) {
				if(classList == null) {
					classInput="active";
				}
				list = new PhongThiBO().getAll();
				listDiaDiemThi = new DiaDiemThiBO().getAllSelect();
				if(maPT != null) {
					pt = new PhongThiBO().getInfo(maPT);
					if(pt == null) {
						maPT = null;
					}
				}
				return SUCCESS;
			}else {
				info = new Info("Lỗi", "Bạn không đủ quyền vào trang này!");
				return ERROR;
			}
		}else{
			info = new Info("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}

	@Override
	public String execute() throws Exception {
		if(btnUpdate != null){
			if(new PhongThiBO().updatePhongThi(pt)){
				info = new Info("","<font style='color:blue;'>"+ pt.getTenPT() +" đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ pt.getTenPT() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				if(new PhongThiBO().insertPhongThi(pt)){
					info = new Info("","<font style='color:blue;'>"+ pt.getTenPT() +" đã được thêm vào danh sách!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ pt.getTenPT() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListPhongThi(){
		if(btnAddNew == null){
			if(listMaPT == null || listMaPT.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn phòng thi trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaPT.size() == 1) {
						maPT = listMaPT.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng sửa chỉ áp dụng cho 1 phòng thi!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new PhongThiBO().deleteListPhongThi(listMaPT)){
							info = new Info("","<font style='color:red;'>Đã xóa thành công!</font><br/>");
						}
						classList = "active";
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public PhongThi getPt() {
		return pt;
	}
	public void setPt(PhongThi pt) {
		this.pt = pt;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<PhongThi> getList() {
		return list;
	}
	public void setList(List<PhongThi> list) {
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
	public HashMap<String, String> getListDiaDiemThi() {
		return listDiaDiemThi;
	}
	public void setListPhongThi(HashMap<String, String> listDiaDiemThi) {
		this.listDiaDiemThi = listDiaDiemThi;
	}
}