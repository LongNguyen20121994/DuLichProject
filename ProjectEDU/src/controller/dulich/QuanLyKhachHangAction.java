package controller.dulich;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLKhachHang;
import model.bean.Info;
import model.bo.KhachHangBO;

@SuppressWarnings("serial")
public class QuanLyKhachHangAction extends ActionSupport {
	private DLKhachHang kh;
	private Info info;
	private List<DLKhachHang> list;
	private List<String> listMaKH;
	private String maKH;
	private String classInput;
	private String classList;
	private String btnUpdate;
	private String btnDelete;
	private String btnSend;
		
	public String getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(String btnDelete) {
		this.btnDelete = btnDelete;
	}

	public String getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(String btnUpdate) {
		this.btnUpdate = btnUpdate;
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
	
	public String getBtnSend() {
		return btnSend;
	}

	public void setBtnSend(String btnSend) {
		this.btnSend = btnSend;
	}

	public DLKhachHang getKh() {
		return kh;
	}

	public void setKh(DLKhachHang kh) {
		this.kh = kh;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<DLKhachHang> getList() {
		return list;
	}

	public void setList(List<DLKhachHang> list) {
		this.list = list;
	}

	public List<String> getListMaKH() {
		return listMaKH;
	}

	public void setListMaKH(List<String> listMaKH) {
		this.listMaKH = listMaKH;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String showCapNhatKhachHang(){
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
		list = new KhachHangBO().getAll();
		if(classList == null) {
			classInput="active";
		}
		if(maKH != null) {
			kh = new KhachHangBO().getInfo(maKH);
			if(kh == null) {
				maKH = null;
			}
		}
		return SUCCESS;
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
			if(new KhachHangBO().updateKhachHang(kh)){
				info = new Info("","<font style='color:blue;'>"+ kh.getHoTen() +" Đã được cập nhật!</font><br/>");
			} else {
				info = new Info("","<font style='color:red;'>"+ kh.getHoTen() +" cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		}
		return SUCCESS;
	}
	
	public String capNhatListKhachHang(){
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
		if(listMaKH == null || listMaKH.size() == 0) {
			info = new Info("","<font style='color:red;'>Vui lòng chọn Khách Hàng trước khi thao tác!</font><br/>");
			classList = "active";
		} else {
			if(btnUpdate != null){
				if(listMaKH.size() == 1) {
					maKH = listMaKH.get(0);
					classInput = "active";
				} else {
					info = new Info("","<font style='color:red;'>Chức năng này chỉ dành cho 1 Khách Hàng!</font><br/>");
					classList = "active";
				}
			} else if( btnSend != null) {
				if(listMaKH.size() == 1) {
					KhachHangBO khbo = new KhachHangBO();
					kh = khbo.getInfo(listMaKH.get(0));
					if(kh != null) {
						SendMailXacNhanAction sendMail = new SendMailXacNhanAction();
						try {
							sendMail.nhanVienGuiMail(kh);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						info = new Info("","<font style='color:blue;'>Đã gửi mail cho Khách Hàng!</font><br/>");
						classList = "active";
					} else {
						info = new Info("","<font style='color:red;'>Bạn chưa chọn Khách Hàng!</font><br/>");
					}
				} else {
					info = new Info("","<font style='color:red;'>Chức năng này chỉ dành cho 1 Khách Hàng!</font><br/>");
					classList = "active";
				}
			} else {
				if(btnDelete != null) {
					if(new KhachHangBO().deleteListKhachHang(listMaKH)){
						info = new Info("","<font style='color:red;'>Xóa thành công!</font><br/>");
					}
					classList = "active";
				}
			}
		}
		return SUCCESS;
	}
	
}
