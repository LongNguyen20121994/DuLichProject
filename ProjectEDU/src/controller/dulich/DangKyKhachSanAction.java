package controller.dulich;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.DLKhachSan;
import model.bean.Info;
import model.bo.DLTinhBO;
import model.bo.KhachSanBO;

@SuppressWarnings("serial")
public class DangKyKhachSanAction extends ActionSupport {
	private DLKhachSan ks;
	private String hinhAnh;
	private HashMap<String,String> listTinh;
	private Info info;
	private String maKS;
	
	private List<DLKhachSan> list;
	private List<String> listMaKs;
	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;
	
	public DLKhachSan getKs() {
		return ks;
	}
	public void setKs(DLKhachSan ks) {
		this.ks = ks;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	public HashMap<String, String> getListTinh() {
		return listTinh;
	}
	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public String getMaKS() {
		return maKS;
	}
	public void setMaKS(String maKS) {
		this.maKS = maKS;
	}
	public List<DLKhachSan> getList() {
		return list;
	}
	public void setList(List<DLKhachSan> list) {
		this.list = list;
	}
	public List<String> getListMaKs() {
		return listMaKs;
	}
	public void setListMaKs(List<String> listMaKs) {
		this.listMaKs = listMaKs;
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
			if(hinhAnh != null){
				ks.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, ks.getMaKS()));
			}
			if(new KhachSanBO().updateKhachSan(ks)){
				info = new Info("","<font style='color:blue;'>"+ ks.getTenKS() +" Đã cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ ks.getTenKS() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if(btnAddNew != null){
				listTinh = new DLTinhBO().getAllSelect();
				String timMax = new KhachSanBO().getMaxRecord();
				int max = 0;
				if(timMax != null) {
					max = catChuoi(timMax) + 1;
				}
				else {
					max = 1;
				}
				ks.setMaKS(taoMa(max));
				if(hinhAnh != null){
					ks.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, ""+ taoMa(max)));
				} else {
					ks.setHinhAnh("images/default.jpg");
				}
				KhachSanBO ksbo = new KhachSanBO();
				if(ksbo.insertKhachSan(ks)){
					info = new Info("","<font style='color:blue;'>"+ ks.getTenKS() +" Đã thêm thành công!</font><br/>");
					classList = "active";
				} else {
					info = new Info("","<font style='color:red;'>"+ ks.getTenKS() +" thêm không thành công!</font><br/>");
					classInput = "active";
				}
				btnAddNew = null;
			}
		}
		return SUCCESS;
	}

	public String showDangKyKhachSan(){
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
		list = new KhachSanBO().getAll();
		if(maKS != null) {
			List<DLKhachSan> listCT = list.stream().filter(p -> p.getMaKS().equals(maKS)).collect(Collectors.toList());
			if(listCT != null && listCT.size() > 0){
				ks = listCT.get(0);
			}
			if(ks == null) {
				maKS = null;
			}
		}
		return SUCCESS;
	}
	
	/*public String showCapNhatKhachSan(){
		ks = new KhachSanBO().getInfo(maKS);
		listTinh = new TinhThanhPhoBO().getAllSelect();
		return SUCCESS;
	}*/
	public String capNhatKhachSan(){
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
		list = new KhachSanBO().getAll();
		if(btnAddNew == null){
			if(listMaKs == null || listMaKs.size() == 0) {
				info = new Info("","<font style='color:red;'>Vui lòng chọn Khách Sạn trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if(btnUpdate != null){
					if(listMaKs.size() == 1) {
						maKS = listMaKs.get(0);
						classInput = "active";
					} else {
						info = new Info("","<font style='color:red;'>Chức năng chỉ dành cho 1 Khách Sạn!</font><br/>");
						classList = "active";
					}
				} else {
					if(btnDelete != null) {
						if(new KhachSanBO().deleteKhachSan(listMaKs)) {
							info = new Info("","<font style='color:red;'>Xóa thành công!</font><br/>");
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
		return "KHS" + ma.append(max);
	}
	
	public int catChuoi(String chuoi) {
		int so=Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}
}
