package controller.khoi;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import controller.dulich.LoginAction;
import common.Library;
import model.bean.Info;
import model.bean.QuanTriVien;
import model.bo.khoi.HuyenQuanBO;
import model.bo.khoi.QuanTriVienBO;
import model.bo.khoi.TinhThanhPhoBO;
import model.bo.khoi.TruongDHCDBO;
import model.bo.khoi.XaPhuongBO;

@SuppressWarnings("serial")
public class DangKyQuanTriVienAction extends ActionSupport {
	private QuanTriVien qtv;
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

	public HashMap<String, String> getListDHCD() {
		return listDHCD;
	}

	public void setListDHCD(HashMap<String, String> listDHCD) {
		this.listDHCD = listDHCD;
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

	public QuanTriVien getQtv() {
		return qtv;
	}

	public void setQtv(QuanTriVien qtv) {
		this.qtv = qtv;
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

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if(session.get("email") == null || xacNhan == null || qtv == null || qtv.getEmail() == null || 
														(!session.get("email").equals(qtv.getEmail().trim() + "-" + xacNhan.trim()))) {
			info = new Info("Ä�Äƒng kÃ½ Quáº£n trá»‹ viÃªn","Báº¡n chÆ°a nháº­p mÃ£ xÃ¡c nháº­n hoáº·c mÃ£ xÃ¡c nháº­n khÃ´ng Ä‘Ãºng. vui lÃ²ng kiá»ƒm tra láº¡i!");
			return "info";
		}
		session.remove("email");
		Random random = new Random();
		qtv.setMatKhau(String.valueOf(random.nextInt()));
		if(hinhAnh != null){
			qtv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, qtv.getSoCMND().trim()));
		} else {
			qtv.setHinhAnh("images/default.jpg");
		}
		if("-1".equals(qtv.getMaXa())){
			qtv.setMaXa(null);
		}
		QuanTriVienBO qtvbo = new QuanTriVienBO();
		if(qtvbo.insertQuanTriVien(qtv)){
			info = new Info("Đăng ký Quản trị viên","Đăng ký thành công.<br/>Thông tin đăng nhập được gửi trong email của bạn!");
			return "info";
		} else {
			info = new Info("Đăng ký quản trị viên","Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}
	public String showDangKyQuanTriVien(){
		listTinh = new TinhThanhPhoBO().getAllSelect();
		return SUCCESS;
	}
	public String showCapNhatQuanTriVien(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		qtv = new QuanTriVienBO().getInfo((String)session.get("soCMND"));
		listTinh = new TinhThanhPhoBO().getAllSelect();
		listHuyen = new HuyenQuanBO().getListHuyenSelect(qtv.getMaTinh());
		listXa = new XaPhuongBO().getListXaSelect(qtv.getMaTinh(),qtv.getMaHuyen());
		listDHCD = new TruongDHCDBO().getAllSelect();
		return SUCCESS;
	}
	public String capNhatQuanTriVien(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("5");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		if(hinhAnh != null){
			qtv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, qtv.getSoCMND()));
		} else {
			qtv.setHinhAnh((String)session.get("hinhAnh"));
		}
		if("-1".equals(qtv.getMaXa())){
			qtv.setMaXa(null);
		}
		QuanTriVienBO qtvbo = new QuanTriVienBO();
		if(qtvbo.updateQuanTriVien(qtv)){
			session.replace("hoTen", qtv.getHoTen());
			session.replace("hinhAnh", qtv.getHinhAnh());
			info = new Info("Cáº­p nháº­t tÃ i khoáº£n quáº£n trá»‹ viÃªn","Cáº­p nháº­t thÃ nh cÃ´ng!");
			return "info";
		} else {
			info = new Info("Cáº­p nháº­t tÃ i khoáº£n quáº£n trá»‹ viÃªn","CÃ³ lá»—i trong quÃ¡ trÃ¬nh thá»±c hiá»‡n. Vui lÃ²ng kiá»ƒm tra láº¡i!");
			return "info";
		}
	}
}
