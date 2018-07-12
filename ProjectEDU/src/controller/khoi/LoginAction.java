package controller.khoi;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.DLNhanVien;
import model.bean.GiangVien;
import model.bean.GiaoVien;
import model.bean.Info;
import model.bean.QuanLyCumThi;
import model.bean.QuanTriVien;
import model.bean.ThiSinh;
import model.bo.DLNhanVienBO;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.GiaoVienBO;
import model.bo.khoi.QuanLyCumThiBO;
import model.bo.khoi.QuanTriVienBO;
import model.bo.khoi.ThiSinhBO;

@SuppressWarnings("serial")
public class LoginAction extends ActionSupport {
	private String account;
	private String soCMND;
	private String matKhau;
	private boolean logined;
	private HashMap<String,String> list;
	private Info info;

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public boolean isLogined() {
		return logined;
	}

	public void setLogined(boolean logined) {
		this.logined = logined;
	}

	public HashMap<String, String> getList() {
		return list;
	}

	public void setList(HashMap<String, String> list) {
		this.list = list;
	}

	@Override
	public String execute() throws Exception {
		if (account.equals("1")) {
			return loginThiSinh();
		}
		if (account.equals("2")) {
			return loginGiaoVien();
		}
		if (account.equals("3")) {
			return loginGiangVien();
		}
		if (account.equals("4")) {
			return loginCumThi();
		}
		if (account.equals("5")) {
			return loginQuanTriVien();
		}
		if (account.equals("6")) {
			return loginAdminTrip();
		}
		return INPUT;
	}
	public String showLogin(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get("soCMND") == null){
			list = new HashMap<String,String>();
			list.put("1", "Thí sinh đăng ký dự thi");
			list.put("2", "Trường trung học phổ thông");
			list.put("3", "Trường đại học - cao đẳng");
			list.put("4", "Cụm thi - Địa điểm thi");
			list.put("5", "Quản trị viên");
			list.put("6", "Admin trip");
			return SUCCESS;
		} else {
			info = new Info("Thông báo","Tài khoản của bạn đã được đăng nhập!.");
			return ERROR;
		}
	}
	public String loginThiSinh() {
		ThiSinhBO login = new ThiSinhBO();
		ThiSinh tmp = login.getInfo(soCMND);
		if(tmp != null){
			if(tmp.isTrangThai()){
				if(tmp.isLogined()){
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						Map<String, Object> session = ActionContext.getContext().getSession();
						session.put("soCMND", tmp.getSoCMND());
						session.put("hoTen", tmp.getHoTen());
						session.put("hinhAnh", tmp.getHinhAnh());
						session.put("account","1");
						return "success";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				} else {
					if(tmp.getMatKhau().equals(matKhau)){
						logined = true;
						return "doiMK";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			} else {
				if(tmp.getMatKhau().equals(matKhau)){
					info = new Info("Đăng nhập","Tài khoản của bạn chưa được kích hoạt."
							+ " Liên hệ với quản trị viên để biết thêm thông tin!");
					return "info";
				} else {
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						info = new Info("Đăng nhập","Tài khoản của bạn đang bị khóa."
								+ " Liên hệ với quản trị viên để biết thêm thông tin!");
						return "info";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			}
		} else {
			info = new Info("Đăng nhập","Tài khoản không tồn tại. Vui lòng kiểm tra lại!");
			return "info";
		}
	}

	public String loginGiaoVien() {
		GiaoVienBO login = new GiaoVienBO();
		GiaoVien tmp = login.getInfo(soCMND);
		if(tmp != null){
			if(tmp.isTrangThai()){
				if(tmp.isLogined()){
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						Map<String, Object> session = ActionContext.getContext().getSession();
						session.put("soCMND", tmp.getSoCMND());
						session.put("hoTen", tmp.getHoTen());
						session.put("hinhAnh", tmp.getHinhAnh());
						session.put("account","2");
						return "success";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				} else {
					if(tmp.getMatKhau().equals(matKhau)){
						logined = true;
						return "doiMK";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			} else {
				if(tmp.getMatKhau().equals(matKhau)){
					info = new Info("Đăng nhập","Tài khoản của bạn chưa được kích hoạt."
							+ " Liên hệ với quản trị viên để biết thêm thông tin!");
					return "info";
				} else {
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						info = new Info("Đăng nhập","Tài khoản của bạn đang bị khóa."
								+ " Liên hệ với quản trị viên để biết thêm thông tin!");
						return "info";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			}
		} else {
			info = new Info("Đăng nhập","Tài khoản không tồn tại. Vui lòng kiểm tra lại!");
			return "info";
		}
	}

	public String loginGiangVien() {
		GiangVienBO login = new GiangVienBO();
		GiangVien tmp = login.getInfo(soCMND);
		if(tmp != null){
			if(tmp.isTrangThai()){
				if(tmp.isLogined()){
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						Map<String, Object> session = ActionContext.getContext().getSession();
						session.put("soCMND", tmp.getSoCMND());
						session.put("hoTen", tmp.getHoTen());
						session.put("hinhAnh", tmp.getHinhAnh());
						session.put("account","3");
						return "success";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				} else {
					if(tmp.getMatKhau().equals(matKhau)){
						logined = true;
						return "doiMK";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			} else {
				if(tmp.getMatKhau().equals(matKhau)){
					info = new Info("Đăng nhập","Tài khoản của bạn chưa được kích hoạt."
							+ " Liên hệ với quản trị viên để biết thêm thông tin!");
					return "info";
				} else {
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						info = new Info("Đăng nhập","Tài khoản của bạn đang bị khóa."
								+ " Liên hệ với quản trị viên để biết thêm thông tin!");
						return "info";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			}
		} else {
			info = new Info("Đăng nhập","Tài khoản không tồn tại. Vui lòng kiểm tra lại!");
			return "info";
		}
	}

	public String loginCumThi() {
		QuanLyCumThiBO login = new QuanLyCumThiBO();
		QuanLyCumThi tmp = login.getInfo(soCMND);
		if(tmp != null){
			if(tmp.isTrangThai()){
				if(tmp.isLogined()){
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						Map<String, Object> session = ActionContext.getContext().getSession();
						session.put("soCMND", tmp.getSoCMND());
						session.put("hoTen", tmp.getHoTen());
						session.put("hinhAnh", tmp.getHinhAnh());
						session.put("account","4");
						return "success";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				} else {
					if(tmp.getMatKhau().equals(matKhau)){
						logined = true;
						return "doiMK";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			} else {
				if(tmp.getMatKhau().equals(matKhau)){
					info = new Info("Đăng nhập","Tài khoản của bạn chưa được kích hoạt."
							+ " Liên hệ với quản trị viên để biết thêm thông tin!");
					return "info";
				} else {
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						info = new Info("Đăng nhập","Tài khoản của bạn đang bị khóa."
								+ " Liên hệ với quản trị viên để biết thêm thông tin!");
						return "info";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			}
		} else {
			info = new Info("Đăng nhập","Tài khoản không tồn tại. Vui lòng kiểm tra lại!");
			return "info";
		}
	}

	public String loginQuanTriVien() {
		QuanTriVienBO login = new QuanTriVienBO();
		QuanTriVien tmp = login.getInfo(soCMND);
		if(tmp != null){
			//if(tmp.isTrangThai()){
				//if(tmp.isLogined()){
					if(tmp.getMatKhau().equals(matKhau)){
						Map<String, Object> session = ActionContext.getContext().getSession();
						session.put("soCMND", tmp.getSoCMND());
						session.put("hoTen", tmp.getHoTen());
						session.put("hinhAnh", tmp.getHinhAnh());
						session.put("account","5");
						return "success";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				/*} else {
					if(tmp.getMatKhau().equals(matKhau)){
						logined = true;
						return "doiMK";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}*/
			/*} else {
				if(tmp.getMatKhau().equals(matKhau)){
					info = new Info("Đăng nhập","Tài khoản của bạn chưa được kích hoạt."
							+ " Liên hệ với quản trị viên để biết thêm thông tin!");
					return "info";
				} else {
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						info = new Info("Đăng nhập","Tài khoản của bạn đang bị khóa."
								+ " Liên hệ với quản trị viên để biết thêm thông tin!");
						return "info";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			}*/
		} else {
			info = new Info("Đăng nhập","Tài khoản không tồn tại. Vui lòng kiểm tra lại!");
			return "info";
		}
	}

	public String loginAdminTrip() {
		DLNhanVienBO login = new DLNhanVienBO();
		//QuanTriVienBO login = new QuanTriVienBO();
		DLNhanVien tmp = login.getInfo(soCMND);
		//QuanTriVien tmp = login.getInfo(soCMND);
		if(tmp != null){
			//if(tmp.isTrangThai()){
				//if(tmp.isLogined()){
					if(tmp.getMatKhau().equals(matKhau)){
						Map<String, Object> session = ActionContext.getContext().getSession();
						session.put("soCMND", tmp.getSoCMND());
						session.put("hoTen", tmp.getHoTen());
						session.put("hinhAnh", tmp.getHinhAnh());
						session.put("account","6");
						return "success";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				/*} else {
					if(tmp.getMatKhau().equals(matKhau)){
						logined = true;
						return "doiMK";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}*/
			/*} else {
				if(tmp.getMatKhau().equals(matKhau)){
					info = new Info("Đăng nhập","Tài khoản của bạn chưa được kích hoạt."
							+ " Liên hệ với quản trị viên để biết thêm thông tin!");
					return "info";
				} else {
					if(tmp.getMatKhau().equals(Library.md5(matKhau))){
						info = new Info("Đăng nhập","Tài khoản của bạn đang bị khóa."
								+ " Liên hệ với quản trị viên để biết thêm thông tin!");
						return "info";
					} else {
						info = new Info("Đăng nhập","Mật khẩu đăng nhập không đúng. Vui lòng kiểm tra lại!");
						return "info";
					}
				}
			}*/
		} else {
			info = new Info("Đăng nhập","Tài khoản không tồn tại. Vui lòng kiểm tra lại!");
			return "info";
		}
	}
	public Info checkLogin(String acc) {
		Info i = null;
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("soCMND") != null) {
			if(!acc.equals(session.get("account"))){
				String str = "6".equals(acc)?"Admin trip":"5".equals(acc)?"quản trị viên":"4".equals(acc)?"quản lý cụm thi":"3".equals(acc)
						?"giảng viên":"2".equals(acc)?"giáo viên":"1".equals(acc)?"thí sinh":"";
				i = new Info("Yêu cầu cấp quyền", "Thao tác chỉ dành cho " + str + ". Vui lòng chọn thao tác khác!");
				return i;
			} else {
				return null;
			}
		} else {
			i = new Info(null,"");
			return i;
		}
	}
}
