package controller.khoi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.GiangVien;
import model.bean.Info;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.HuyenQuanBO;
import model.bo.khoi.TinhThanhPhoBO;
import model.bo.khoi.TruongDHCDBO;
import model.bo.khoi.XaPhuongBO;

@SuppressWarnings("serial")
public class DangKyGiangVienAction extends ActionSupport {
	private GiangVien gv;
	private String hinhAnh;
	private HashMap<String,String> listTinh;
	private HashMap<String,String> listDHCD;
	private HashMap<String,String> listHuyen;
	private HashMap<String,String> listXa;
	private Info info;
	private String xacNhan;
		
	public String getXacNhan() {
		return xacNhan;
	}

	public void setXacNhan(String xacNhan) {
		this.xacNhan = xacNhan;
	}

	public HashMap<String, String> getListHuyen() {
		return listHuyen;
	}

	public void setListHuyen(HashMap<String, String> listHuyen) {
		this.listHuyen = listHuyen;
	}

	public HashMap<String, String> getListXa() {
		return listXa;
	}

	public void setListXa(HashMap<String, String> listXa) {
		this.listXa = listXa;
	}

	public GiangVien getGv() {
		return gv;
	}

	public void setGv(GiangVien gv) {
		this.gv = gv;
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

	public HashMap<String, String> getListDHCD() {
		return listDHCD;
	}

	public void setListDHCD(HashMap<String, String> listDHCD) {
		this.listDHCD = listDHCD;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get("email") == null || xacNhan == null || gv == null || gv.getEmail() == null || 
														(!session.get("email").equals(gv.getEmail().trim() + "-" + xacNhan.trim()))) {
			info = new Info("Đăng ký Quản trị viên","Bạn chưa nhập mã xác nhận hoặc mã xác nhận không đúng. vui lòng kiểm tra lại!");
			return "info";
		}
		session.remove("email");
		Random random = new Random();
		gv.setMatKhau(String.valueOf(random.nextInt()));
		if(hinhAnh != null){
			gv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, gv.getSoCMND().trim()));
		} else {
			gv.setHinhAnh("images/default.jpg");
		}
		if("-1".equals(gv.getMaXa())){
			gv.setMaXa(null);
		}
		GiangVienBO gvbo = new GiangVienBO();
		if(gvbo.insertGiangVien(gv)){
			info = new Info("Đăng ký tài khoản Quản lý ĐH-CĐ","Đăng ký thông tin thành công.<br/>Mọi thông tin cần thiết sẽ được gửi về email của bạn trong thời gian sớm nhất!");
			return "info";
		} else {
			info = new Info("Đăng ký tài khoản Quản lý ĐH-CĐ","Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}
	public String showDangKyGiangVien(){
		listTinh = new TinhThanhPhoBO().getAllSelect();
		listDHCD = new TruongDHCDBO().getAllSelect();
		return SUCCESS;
	}
	
	public String showCapNhatGiangVien(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		gv = new GiangVienBO().getInfo((String)session.get("soCMND"));
		listTinh = new TinhThanhPhoBO().getAllSelect();
		listHuyen = new HuyenQuanBO().getListHuyenSelect(gv.getMaTinh());
		listXa = new XaPhuongBO().getListXaSelect(gv.getMaTinh(),gv.getMaHuyen());
		listDHCD = new TruongDHCDBO().getAllSelect();
		return SUCCESS;
	}
	public String capNhatGiangVien(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(hinhAnh != null){
			gv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, gv.getSoCMND()));
		} else {
			gv.setHinhAnh((String)session.get("hinhAnh"));
		}
		if("-1".equals(gv.getMaXa())){
			gv.setMaXa(null);
		}
		GiangVienBO gvbo = new GiangVienBO();
		if(gvbo.updateGiangVien(gv)){
			session.replace("hoTen", gv.getHoTen());
			session.replace("hinhAnh", gv.getHinhAnh());
			info = new Info("Cập nhật tài khoản Quản lý ĐH-CĐ","Cập nhật thành công!");
			return "info";
		} else {
			info = new Info("Cập nhật tài khoản Quản lý ĐH-CĐ","Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}
}
