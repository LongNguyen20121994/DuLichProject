package controller.dulich;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLChiTietTour;
import model.bean.Info;
import model.bo.ChiTietTourBO;
import model.bo.DLTinhBO;
import model.bo.LoaiTourBO;

@SuppressWarnings("serial")
public class LenLichTourAction extends ActionSupport {
	private DLChiTietTour ct;
	private String maCt;
	private int maxTour;
	private HashMap<String, String> listLoaiTour;
	private String maLoai;
	/*private HashMap<String, String> listTour;*/
	private HashMap<String, String> listTinh;
	private String maTinh;
	/*private HashMap<String, String> listKS;*/
	private Info info;
	
	private List<DLChiTietTour> list;
	private List<String> listMaCTTour;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;

	public DLChiTietTour getCt() {
		return ct;
	}

	public void setCt(DLChiTietTour ct) {
		this.ct = ct;
	}

	public String getMaCt() {
		return maCt;
	}

	public void setMaCt(String maCt) {
		this.maCt = maCt;
	}

	public int getMaxTour() {
		return maxTour;
	}

	public void setMaxTour(int maxTour) {
		this.maxTour = maxTour;
	}

	/*public HashMap<String, String> getListTour() {
		return listTour;
	}

	public void setListTour(HashMap<String, String> listTour) {
		this.listTour = listTour;
	}

	public HashMap<String, String> getListKS() {
		return listKS;
	}

	public void setListKS(HashMap<String, String> listKS) {
		this.listKS = listKS;
	}*/

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
	
	public HashMap<String, String> getListLoaiTour() {
		return listLoaiTour;
	}

	public void setListLoaiTour(HashMap<String, String> listLoaiTour) {
		this.listLoaiTour = listLoaiTour;
	}

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}

	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
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

	public List<DLChiTietTour> getList() {
		return list;
	}

	public void setList(List<DLChiTietTour> list) {
		this.list = list;
	}

	public List<String> getListMaCTTour() {
		return listMaCTTour;
	}

	public void setListMaCTTour(List<String> listMaCTTour) {
		this.listMaCTTour = listMaCTTour;
	}
	

	@Override
	public String execute() {
		info = new LoginAction().checkLogin("1");
		if (info != null) {
			if (info.getTieuDe() == null) {
				return "login";
			} else {
				return "info";
			}
		}
		if(btnUpdate != null){
			if(new ChiTietTourBO().updateChiTietTour(ct)){
				info = new Info("","<font style='color:blue;'>"+ ct.getMaChiTietTour() +" Đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ ct.getMaChiTietTour() +" Cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				String timMax = new ChiTietTourBO().getMaxRecord();
				if (timMax != null) {
					maxTour = catChuoi(timMax) + 1;
				} else {
					maxTour = 1;
				}
				ct.setMaChiTietTour(taoMa(maxTour));
				ChiTietTourBO ctbo = new ChiTietTourBO();
				if (ctbo.insertChiTietTour(ct)) {
					info = new Info("","<font style='color:blue;'>"+ ct.getMaChiTietTour() +"Lên lịch Tour thành công.!");
					return "info";
				} else {
					info = new Info("","<font style='color:red;'>"+ ct.getMaChiTietTour() +"Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
					return "info";
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
		return "CTT" + ma.append(max);
	}

	public int catChuoi(String chuoi) {
		int so = Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}

	public String showLenLichTour() {
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
		listLoaiTour = new LoaiTourBO().getAllSelect();
		listTinh = new DLTinhBO().getAllSelect();
		/*listTour = new DLTourBO().getAllByMaLoaiTour(maLoai);
		listKS = new KhachSanBO().getAllByMaTinh(maTinh);*/
		list = new ChiTietTourBO().getAll();
		if(maCt != null) {
			List<DLChiTietTour> listCT = list.stream().filter(p -> p.getMaChiTietTour().equals(maCt)).collect(Collectors.toList());
			if(listCT != null && listCT.size() > 0){
				ct = listCT.get(0);
			}
			if(ct == null) {
				maCt = null;
			}
		}
		return SUCCESS;
	}
	
	public String capNhatListLichTour(){
		info = new LoginAction().checkLogin("1");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(btnAddNew == null){
			if(listMaCTTour == null || listMaCTTour.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn lịch trình trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaCTTour.size() == 1) {
						maCt = listMaCTTour.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng chỉ dành cho 1 lịch trình!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new ChiTietTourBO().deleteListLichTrinh(listMaCTTour)){
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
