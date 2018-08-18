package controller.khoi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import controller.dulich.LoginAction;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bean.XetTuyen;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.NamTuyenSinhBO;
import model.bo.khoi.NganhDHCDBO;
import model.bo.khoi.XetTuyenBO;

@SuppressWarnings("serial")
public class CapNhatXetTuyenNganhDHCDAction extends ActionSupport {
	private Info info;
	private HashMap<String, String> listNganh;
	private int namTS;
	private String maNganh;
	private String[] monKhoi;
	private String[] heSo;

	public String[] getMonKhoi() {
		return monKhoi;
	}

	public void setMonKhoi(String[] monKhoi) {
		this.monKhoi = monKhoi;
	}

	public String[] getHeSo() {
		return heSo;
	}

	public void setHeSo(String[] heSo) {
		this.heSo = heSo;
	}

	public String getMaNganh() {
		return maNganh;
	}

	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}

	public int getNamTS() {
		return namTS;
	}

	public void setNamTS(int namTS) {
		this.namTS = namTS;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public HashMap<String, String> getListNganh() {
		return listNganh;
	}

	public void setListNganh(HashMap<String, String> listNganh) {
		this.listNganh = listNganh;
	}

	public String showCapNhatXetTuyenNganhDHCD(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		listNganh = new NganhDHCDBO().getAllTruongSelect(new GiangVienBO()
				.getInfo((String) session.get("soCMND")).getMaTruong());
		namTS = new NamTuyenSinhBO().getNamTuyenSinh();
		HttpServletRequest request = ServletActionContext.getRequest();
		if(request.getAttribute("maNganh") != null){
			maNganh = (String) request.getAttribute("maNganh");
		}
		return SUCCESS;
	}

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("3");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		String maTruong = new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong();
		String[] tmp = maNganh.split("-");
		String maNganh = tmp[0];
		String daoTao = tmp[1];
		int namTuyenSinh = namTS;
		XetTuyen xt;
		if(heSo != null){
			List<XetTuyen> listXT = new ArrayList<XetTuyen>();
			for(int i=0;i<heSo.length;i++){
				if(!StringUtils.isBlank(heSo[i])){
					xt = new XetTuyen();
					xt.setMaTruong(maTruong);
					xt.setMaNganh(maNganh);
					xt.setDaoTao(daoTao);
					xt.setNamTuyenSinh(namTuyenSinh);
					xt.setHeSo(Integer.parseInt(heSo[i]));
					String[] tam = monKhoi[i].split("-");
					xt.setMaMonThi(tam[0]);
					xt.setMaKhoi(tam[1]);
					listXT.add(xt);
				}
			}
			if(new XetTuyenBO().updateListXetTuyenNganhDHCD(listXT)){
				return INPUT;
			} else {
				return INPUT;
			}
		}
		return INPUT;
	}
	
	
}
