package controller.dulich;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.DLTour;
import model.bean.Info;
import model.bo.DLTourBO;
import model.bo.LoaiTourBO;

@SuppressWarnings("serial")
public class DangKyTourAction extends ActionSupport {
	private DLTour tour;
	private String maTour;
	private String hinhAnh;
	private int maxTour;
	private HashMap<String, String> listLoaiTour;
	private Info info;
	private List<DLTour> listGetName;
	private String name;

	private List<DLTour> list;
	private List<String> listMaTour;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;

	public DLTour getTour() {
		return tour;
	}
	public void setTour(DLTour tour) {
		this.tour = tour;
	}
	public String getMaTour() {
		return maTour;
	}
	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public int getMaxTour() {
		return maxTour;
	}
	public void setMaxTour(int maxTour) {
		this.maxTour = maxTour;
	}
	public HashMap<String, String> getListLoaiTour() {
		return listLoaiTour;
	}
	public void setListLoaiTour(HashMap<String, String> listLoaiTour) {
		this.listLoaiTour = listLoaiTour;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public List<DLTour> getList() {
		return list;
	}
	public void setList(List<DLTour> list) {
		this.list = list;
	}
	public List<String> getListMaTour() {
		return listMaTour;
	}
	public void setListMaTour(List<String> listMaTour) {
		this.listMaTour = listMaTour;
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
	public List<DLTour> getListGetName() {
		return listGetName;
	}
	public void setListGetName(List<DLTour> listGetName) {
		this.listGetName = listGetName;
	}	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String execute() {
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
			if (hinhAnh != null) {
				tour.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, tour.getMaTour()));
			}
			if(new DLTourBO().updateTour(tour)){
				info = new Info("","<font style='color:blue;'>"+ tour.getTieuDe() +" Đã cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ tour.getTieuDe() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				String timMax = new DLTourBO().getMaxRecord();
				if (timMax != null) {
					maxTour = catChuoi(timMax) + 1;
				} else {
					maxTour = 1;
				}
				tour.setMaTour(taoMa(maxTour));
				if (hinhAnh != null) {
					tour.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, "" + tour.getMaTour()));
				} else {
					tour.setHinhAnh("images/default.jpg");
				}
				DLTourBO tourbo = new DLTourBO();
				if (tourbo.insertTour(tour)) {
					info = new Info("","<font style='color:blue;'>"+ tour.getTieuDe() +" Đã thêm thành công!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ tour.getTieuDe() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
				btnAddNew = null;
			}
		}
		return SUCCESS;
	}

	public String taoMa(int max) {
		int i, n = max;
		for (i = 1; n >= 10; i++)
			n /= 10;
		StringBuilder ma = new StringBuilder();
		for (int j = 0; j < 7 - i; j++)
			ma.append("0");
		return "TUR" + ma.append(max);
	}

	public int catChuoi(String chuoi) {
		int so = Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}

	public String showDangKyTour() {
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
		list = new DLTourBO().getAll();
		if(maTour != null) {
			List<DLTour> listMT = list.stream().filter(p -> p.getMaTour().equals(maTour)).collect(Collectors.toList());
			if(listMT != null && listMT.size() > 0){
				tour = listMT.get(0);
			}
			if(tour == null) {
				maTour = null;
			}
		}
		listLoaiTour = new LoaiTourBO().getAllSelect();
		return SUCCESS;
	}

	public String ajaxGetListTour() {
		listGetName = new DLTourBO().getAllLikeName(name);
		return SUCCESS;
	}

	public String capNhatListTour() {
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
			if(listMaTour == null || listMaTour.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn Tour trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaTour.size() == 1) {
						maTour = listMaTour.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng chỉ dành cho 1 Tour!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new DLTourBO().deleteListTour(listMaTour)){
							info = new Info("","<font style='color:red;'>Xóa thành công!</font><br/>");
						}
						classList = "active";
					}
				}
			}
		}
		return SUCCESS;
	}
}
