package controller.longnt;

import java.util.List;
import java.util.Map;

import model.bean.Info;
import model.bo.longnt.QuanLyCumThiBO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class DeleteListQuanLyCumThiAction extends ActionSupport {

	private List<String> listSoCMND = null;
	private Info info;

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			int account = Integer.parseInt((String)session.get("account"));
			if (account == 4) {

				String error = new QuanLyCumThiBO().deleteListQuanLyCumThi(listSoCMND);
				if (error == null || error.isEmpty()) {
					info = new Info("Thao tác thành công", "Đã xóa các quản lý cụm thi! ");
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