package controller.quang;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bo.quang.ThiSinhBO;

@SuppressWarnings("serial")
public class DeleteListThiSinhGVAction extends ActionSupport {

	private List<String> listSoCMND = null;
	private int namTS;
	private Info info;

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("2".equals(account)){

				String error = new ThiSinhBO().deleteListThiSinhDKDTGV(listSoCMND, namTS);
				if (error == null || error.isEmpty()) {
					info = new Info("Thao tác thành công", "Đã xóa các thí sinh! ");
					return SUCCESS;
				} else {
					info = new Info("Thao tác thất bại", error);
					return ERROR;
				}
			} else {
				info = new Info("Lỗi", "Bạn không đủ quyền vào trang này!");
				return ERROR;
			}
		} else {
			info = new Info("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}

	public int getNamTS() {
		return namTS;
	}

	public void setNamTS(int namTS) {
		this.namTS = namTS;
	}

	public List<String> getListSoCMND() {
		return listSoCMND;
	}

	public void setListSoCMND(List<String> listSoCMND) {
		this.listSoCMND = listSoCMND;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}
