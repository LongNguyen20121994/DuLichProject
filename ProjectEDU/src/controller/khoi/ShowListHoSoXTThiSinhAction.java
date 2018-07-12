package controller.khoi;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.HoSoXetTuyen;
import model.bean.Info;
import model.bo.khoi.HoSoXetTuyenBO;

@SuppressWarnings("serial")
public class ShowListHoSoXTThiSinhAction extends ActionSupport {
	private List<HoSoXetTuyen> list;
	private Info info;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<HoSoXetTuyen> getList() {
		return list;
	}

	public void setList(List<HoSoXetTuyen> list) {
		this.list = list;
	}

	public String showListHoSoXTThiSinh(){
		Map<String, Object> session = ActionContext.getContext().getSession();
		info = new LoginAction().checkLogin("1");
		if(info != null) {
			if(info.getTieuDe() == null){
				return "login";
			} else {
				return "info";
			}
		}
		list = new HoSoXetTuyenBO().getAllBySoCMND((String) session.get("soCMND"));
		return SUCCESS;
	}
	
	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
}
