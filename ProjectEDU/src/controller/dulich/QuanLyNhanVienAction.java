package controller.dulich;

import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.DLNhanVien;
import model.bean.Info;
import model.bo.DLNhanVienBO;

@SuppressWarnings("serial")
public class QuanLyNhanVienAction extends ActionSupport {
	private DLNhanVien nv;
	private Info info;
	private List<DLNhanVien> list;
	private List<String> listMaNV;
	private String soCMND;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;
	private String hinhAnh;
	public DLNhanVien getNv() {
		return nv;
	}
	public void setNv(DLNhanVien nv) {
		this.nv = nv;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<DLNhanVien> getList() {
		return list;
	}
	public void setList(List<DLNhanVien> list) {
		this.list = list;
	}
	public List<String> getListMaNV() {
		return listMaNV;
	}
	public void setListMaNV(List<String> listMaNV) {
		this.listMaNV = listMaNV;
	}
	public String getSoCMND() {
		return soCMND;
	}
	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
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
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	@Override
	public String execute() throws Exception {
		info = new LoginAction().checkLogin("1");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnUpdate != null){
			if (hinhAnh != null) {
				nv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, nv.getSoCMND()));
			}
			if(new DLNhanVienBO().updateNhanVien(nv)){
				info = new Info("","<font style='color:blue;'>"+ nv.getHoTen() +" Đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ nv.getHoTen() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				if (hinhAnh != null) {
					nv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, nv.getSoCMND()));
				}
				if(new DLNhanVienBO().insertNhanVien(nv)){
					info = new Info("","<font style='color:blue;'>"+ nv.getHoTen() +" Đã thêm thành công!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ nv.getHoTen() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}
	public String quanLyNhanVien(){
		info = new LoginAction().checkLogin("1");
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
		list = new DLNhanVienBO().getAll();
		if(soCMND != null) {
			List<DLNhanVien> listPT = list.stream().filter(p -> p.getSoCMND().equals(soCMND)).collect(Collectors.toList());
			if(listPT != null && listPT.size() > 0){
				nv = listPT.get(0);
			}
			if(nv == null) {
				soCMND = null;
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListNhanVien(){
		info = new LoginAction().checkLogin("1");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnAddNew == null){
			if(listMaNV == null || listMaNV.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn Tài khoản trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaNV.size() == 1) {
						soCMND = listMaNV.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng này chỉ dành cho 1 Tài Khoản!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new DLNhanVienBO().deleteListNhanVien(listMaNV)){
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
