package controller.longnt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.bean.DiaDiemThi;
import model.bean.Info;
import model.bo.longnt.DiaDiemThiBO;
import model.bo.longnt.HoiDongThiBO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CapNhatDiaDiemThiAction extends ActionSupport{
	private DiaDiemThi ddt;
	private Info info;
	private List<DiaDiemThi> list;
	private List<String> listMaDDT;
	private String maDDT;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;
	private HashMap<String, String> listHoiDongThi;
	
	public String showCapNhatDiaDiemThi(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("4".equals(account)) {
				if(classList == null) {
					classInput="active";
				}
				list = new DiaDiemThiBO().getAll();
				listHoiDongThi = new HoiDongThiBO().getAllSelect();
				if(maDDT != null) {
					ddt = new DiaDiemThiBO().getInfo(maDDT);
					if(ddt == null) {
						maDDT = null;
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
			if(new DiaDiemThiBO().updateDiaDiemThi(ddt)){
				info = new Info("","<font style='color:blue;'>"+ ddt.getTenDDT() +" đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ ddt.getTenDDT() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				if(new DiaDiemThiBO().insertDiaDiemThi(ddt)){
					info = new Info("","<font style='color:blue;'>"+ ddt.getTenDDT() +" đã được thêm vào danh sách!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ ddt.getTenDDT() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListDiaDiemThi(){
		if(btnAddNew == null){
			if(listMaDDT == null || listMaDDT.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn địa điểm thi trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaDDT.size() == 1) {
						maDDT = listMaDDT.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng sửa chỉ áp dụng cho 1 địa điểm thi!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new DiaDiemThiBO().deleteListDiaDiemThi(listMaDDT)){
							info = new Info("","<font style='color:red;'>Đã xóa thành công!</font><br/>");
						}
						classList = "active";
					}
				}
			}
		}
		return SUCCESS;
	}
	
	public DiaDiemThi getDdt() {
		return ddt;
	}
	public void setDdt(DiaDiemThi ddt) {
		this.ddt = ddt;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<DiaDiemThi> getList() {
		return list;
	}
	public void setList(List<DiaDiemThi> list) {
		this.list = list;
	}
	public List<String> getListMaDDT() {
		return listMaDDT;
	}
	public void setListMaDDT(List<String> listMaDDT) {
		this.listMaDDT = listMaDDT;
	}
	public String getMaDDT() {
		return maDDT;
	}
	public void setMaDDT(String maDDT) {
		this.maDDT = maDDT;
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

	public HashMap<String, String> getListHoiDongThi() {
		return listHoiDongThi;
	}

	public void setListHoiDongThi(HashMap<String, String> listHoiDongThi) {
		this.listHoiDongThi = listHoiDongThi;
	}
	
}
