package controller.khoi;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import controller.dulich.LoginAction;
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
					info = new Info("Khá»‘i thi khÃ´ng há»£p lá»‡.", "KhÃ´ng tá»“n táº¡i tá»• há»£p mÃ´n thi cá»§a khá»‘i trong danh sÃ¡ch mÃ´n Ä‘Äƒng kÃ½ dá»± thi!");
					return "info";
				}
			}
			if (hoSoBO.insertHoSoXetTuyen(hs)) {
				ChiTietHoSoBO chiTietBO = new ChiTietHoSoBO();
				if (chiTietBO.updateChiTietHoSo(hs, ktNganh)) {
					info = new Info("ThÃ´ng bÃ¡o", "Cáº­p nháº­t thÃ nh cÃ´ng. Káº¿t quáº£ xÃ©t tuyá»ƒn sáº½ Ä‘Æ°á»£c gá»­i Ä‘áº¿n email cá»§a báº¡n!");
					return "info";
				} else {
					return "info";
				}
			}
		} else {
			ChiTietHoSoBO chiTietBO = new ChiTietHoSoBO();
			if (chiTietBO.updateChiTietHoSo(hs, ktNganh)) {
				if(hoSoBO.deleteHoSoXetTuyen(hs)){
					info = new Info("ThÃ´ng bÃ¡o", "Ä�Ã£ xÃ³a thÃ´ng tin Ä‘Äƒng kÃ½ xÃ©t tuyá»ƒn cá»§a trÆ°á»�ng " + hs.getMaTruong());
					return "info";
				} else {
					info = new Info("ThÃ´ng bÃ¡o", "Vui lÃ²ng chá»�n ngÃ nh Ä‘Äƒng kÃ½!");
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
			info = new Info("ThÃ´ng bÃ¡o", "Há»‡ thá»‘ng xÃ©t tuyá»ƒn chÆ°a Ä‘Æ°á»£c má»Ÿ. <br/>"
					+ "Vui lÃ²ng thá»­ láº¡i sau hoáº·c liÃªn há»‡ vá»›i ban quáº£n trá»‹ Ä‘á»ƒ Ä‘Æ°á»£c trá»£ giÃºp!");
			return "info";
		}
		return SUCCESS;
	}
}
