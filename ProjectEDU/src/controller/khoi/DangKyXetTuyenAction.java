package controller.khoi;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.HoSoXetTuyen;
import model.bean.Info;
import model.bo.khoi.ChiTietHoSoBO;
import model.bo.khoi.DotXetTuyenBO;
import model.bo.khoi.HoSoXetTuyenBO;
import model.bo.khoi.NamTuyenSinhBO;
import model.bo.khoi.TinhThanhPhoBO;
import model.bo.khoi.TruongDHCDBO;

@SuppressWarnings("serial")
public class DangKyXetTuyenAction extends ActionSupport {
	private HoSoXetTuyen hs;
	private String[] ktNganh;
	private HashMap<String, String> listTinh;
	private HashMap<String, String> listDHCD;
	private HashMap<String, String> listDotXT;
	private Info info;

	public HoSoXetTuyen getHs() {
		return hs;
	}

	public void setHs(HoSoXetTuyen hs) {
		this.hs = hs;
	}

	public String[] getKtNganh() {
		return ktNganh;
	}

	public void setKtNganh(String[] ktNganh) {
		this.ktNganh = ktNganh;
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

	public HashMap<String, String> getListDotXT() {
		return listDotXT;
	}

	public void setListDotXT(HashMap<String, String> listDotXT) {
		this.listDotXT = listDotXT;
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
		info = new LoginAction().checkLogin("1");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		hs.setMaHS("");
		hs.setSoCMND((String) session.get("soCMND"));
		HoSoXetTuyenBO hoSoBO = new HoSoXetTuyenBO();
		if (ktNganh != null && ktNganh.length > 0) {
			for(String tmp : ktNganh){
				String khoiThi = tmp.split("-")[2];
				if(!hoSoBO.isValidKhoiThi((String) session.get("soCMND"),khoiThi)){
					info = new Info("Khối thi không hợp lệ.", "Không tồn tại tổ hợp môn thi của khối trong danh sách môn đăng ký dự thi!");
					return "info";
				}
			}
			if (hoSoBO.insertHoSoXetTuyen(hs)) {
				ChiTietHoSoBO chiTietBO = new ChiTietHoSoBO();
				if (chiTietBO.updateChiTietHoSo(hs, ktNganh)) {
					info = new Info("Thông báo", "Cập nhật thành công. Kết quả xét tuyển sẽ được gửi đến email của bạn!");
					return "info";
				} else {
					return "info";
				}
			}
		} else {
			ChiTietHoSoBO chiTietBO = new ChiTietHoSoBO();
			if (chiTietBO.updateChiTietHoSo(hs, ktNganh)) {
				if(hoSoBO.deleteHoSoXetTuyen(hs)){
					info = new Info("Thông báo", "Đã xóa thông tin đăng ký xét tuyển của trường " + hs.getMaTruong());
					return "info";
				} else {
					info = new Info("Thông báo", "Vui lòng chọn ngành đăng ký!");
					return "info";
				}
			} else {
				return "info";
			}
		}
		return "info";
	}

	public String showDangKyXetTuyen() {
		info = new LoginAction().checkLogin("1");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listDotXT = new DotXetTuyenBO().getAllSelect();
		if (listDotXT.size() > 0) {
			if (hs == null) {
				hs = new HoSoXetTuyen();
				hs.setNamTS(new NamTuyenSinhBO().getNamTuyenSinh());
			} else {
				hs.setNamTS(new NamTuyenSinhBO().getNamTuyenSinh());
			}
			listTinh = new TinhThanhPhoBO().getAllSelect();
			listDHCD = new TruongDHCDBO().getAllSelect();
		} else {
			info = new Info("Thông báo", "Hệ thống xét tuyển chưa được mở. <br/>"
					+ "Vui lòng thử lại sau hoặc liên hệ với ban quản trị để được trợ giúp!");
			return "info";
		}
		return SUCCESS;
	}
}
