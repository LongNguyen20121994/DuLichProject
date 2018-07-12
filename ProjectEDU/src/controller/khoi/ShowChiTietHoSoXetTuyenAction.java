package controller.khoi;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.HoSoXetTuyen;
import model.bean.Info;
import model.bean.Nganh;
import model.bo.khoi.ChiTietHoSoBO;
import model.bo.khoi.HoSoXetTuyenBO;

@SuppressWarnings("serial")
public class ShowChiTietHoSoXetTuyenAction extends ActionSupport {
	private HoSoXetTuyen hs;
	private Info info;
	private String maHS;
	private List<Nganh> listNganh;

	public List<Nganh> getListNganh() {
		return listNganh;
	}

	public void setListNganh(List<Nganh> listNganh) {
		this.listNganh = listNganh;
	}

	public String getMaHS() {
		return maHS;
	}

	public void setMaHS(String maHS) {
		this.maHS = maHS;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public HoSoXetTuyen getHs() {
		return hs;
	}

	public void setHs(HoSoXetTuyen hs) {
		this.hs = hs;
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
		if(!StringUtils.isBlank(maHS)) {
			List<HoSoXetTuyen> list = new HoSoXetTuyenBO().getAllBySoCMND((String) session.get("soCMND"));
			list = list.stream().filter(p -> p.getMaHS().equals(maHS)).collect(Collectors.toList());
			if(list.size() > 0) {
				hs = list.get(0);
				listNganh = new ChiTietHoSoBO().getAllNganhByMaHS(maHS);
				return SUCCESS;
			} else {
				
			}
		} else {
			
		}
		return "info";
	}
}
