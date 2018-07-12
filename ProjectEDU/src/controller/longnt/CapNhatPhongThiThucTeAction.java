package controller.longnt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.Info;
import model.bean.PhongThiThucTe;
import model.bo.longnt.MonThiBO;
import model.bo.longnt.PhongThiBO;
import model.bo.longnt.PhongThiThucTeBO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CapNhatPhongThiThucTeAction extends ActionSupport{
	private PhongThiThucTe pt;
	private Info info;
	private List<PhongThiThucTe> list;
	private List<String> listIDPhong;
	private String iDPhong;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;
	private HashMap<String, String> listMonThi;
	private HashMap<String, String> listPhongThi;
	
	public String showCapNhatPhongThiThucTe(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("4".equals(account)) {
				if(classList == null) {
					classInput="active";
				}
				list = new PhongThiThucTeBO().getAll();
				listMonThi = new MonThiBO().getAllSelect();
				listPhongThi = new PhongThiBO().getAllSelect();
				if(iDPhong != null) {
					pt = new PhongThiThucTeBO().getInfo(iDPhong);
					if(pt == null) {
						iDPhong = null;
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
			if(new PhongThiThucTeBO().updatePhongThiThucTe(pt)){
				info = new Info("","<font style='color:blue;'>"+ pt.getIDPhong() +" đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ pt.getIDPhong() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				if(new PhongThiThucTeBO().insertPhongThiThucTe(pt)){
					info = new Info("","<font style='color:blue;'>"+ pt.getIDPhong() +" đã được thêm vào danh sách!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ pt.getIDPhong() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListPhongThiThucTe(){
		if(btnAddNew == null){
			if(listIDPhong == null || listIDPhong.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn phòng thi trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listIDPhong.size() == 1) {
						iDPhong = listIDPhong.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng sửa chỉ áp dụng cho 1 phòng thi!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new PhongThiThucTeBO().deleteListPhongThiThucTe(listIDPhong)){
							info = new Info("","<font style='color:red;'>Đã xóa thành công!</font><br/>");
						}
						classList = "active";
					}
				}
			}
		}
		return SUCCESS;
	}

	public PhongThiThucTe getPt() {
		return pt;
	}

	public void setPt(PhongThiThucTe pt) {
		this.pt = pt;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<PhongThiThucTe> getList() {
		return list;
	}

	public void setList(List<PhongThiThucTe> list) {
		this.list = list;
	}

	public List<String> getListIDPhong() {
		return listIDPhong;
	}

	public void setListIDPhong(List<String> listIDPhong) {
		this.listIDPhong = listIDPhong;
	}

	public String getIDPhong() {
		return iDPhong;
	}

	public void setIDPhong(String iDPhong) {
		this.iDPhong = iDPhong;
	}

	public HashMap<String, String> getListMonThi() {
		return listMonThi;
	}

	public void setListMonThi(HashMap<String, String> listMonThi) {
		this.listMonThi = listMonThi;
	}

	public HashMap<String, String> getListPhongThi() {
		return listPhongThi;
	}

	public void setListPhongThi(HashMap<String, String> listPhongThi) {
		this.listPhongThi = listPhongThi;
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
}
