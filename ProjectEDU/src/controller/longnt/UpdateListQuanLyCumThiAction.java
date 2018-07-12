package controller.longnt;

import java.util.List;
import java.util.Map;

import model.bean.Info;
import model.bean.QuanLyCumThi;
import model.bo.longnt.QuanLyCumThiBO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UpdateListQuanLyCumThiAction extends ActionSupport {
	private Info info;
	private List<String> listSoCMND;
	private String soCMNDUpdate;
	private List<QuanLyCumThi> listqlct = null;
	private String btXoa;
	private String btSua;

	@Override
	public String execute() throws Exception {
		System.out.print("dddddddddddddd");
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			int account = Integer.parseInt((String) session.get("account"));
			if (account ==4) {
				if (!listSoCMND.isEmpty()) {
					if (btXoa != null) {
						System.out.print("okkk"+listSoCMND.get(0));
						return "delete";
					} else if (btSua != null) {
						if (listSoCMND.size() == 1) {
							soCMNDUpdate = listSoCMND.get(0);

							return "edit";
						}else{
							info = new Info("Thao tác thất bại",
									"Lỗi hệ thống, vui lòng thử lại!");
							return ERROR;
						}
					} else {
						info = new Info("Thao tác thất bại",
								"Bạn chỉ được chọn một quản lý cụm thi!");
						return ERROR;
					}
				} else {
					info = new Info("Thao tác thất bại",
							"Bạn chưa chọn quản lý cụm thi!");
					return ERROR;
				}
			} else {
				info = new Info("Lỗi", "Bạn không đủ quyền vào trang này!");
				return ERROR;
			}
		} else {
			info = new Info("Thao tác thất bại!", "Bạn chưa đăng nhập");
			return ERROR;
		}
	}

	public String display() {

		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");
		if (soCMND != null && !soCMND.isEmpty()) {
			int account = Integer.parseInt((String) session.get("account"));
			if (account == 4) {
				listqlct = new QuanLyCumThiBO().getListInfoQuanLyCumThi(soCMND);
				if (listqlct != null) {
					return SUCCESS;
				} else {
					info = new Info("Lỗi trong quá trình xử lý",
							"Vui lòng thử lại sau!");
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

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<String> getListSoCMND() {
		return listSoCMND;
	}

	public void setListSoCMND(List<String> listSoCMND) {
		this.listSoCMND = listSoCMND;
	}

	public String getSoCMNDUpdate() {
		return soCMNDUpdate;
	}

	public void setSoCMNDUpdate(String soCMNDUpdate) {
		this.soCMNDUpdate = soCMNDUpdate;
	}

	public List<QuanLyCumThi> getListqlct() {
		return listqlct;
	}

	public void setListqlct(List<QuanLyCumThi> listqlct) {
		this.listqlct = listqlct;
	}

	public String getBtXoa() {
		return btXoa;
	}

	public void setBtXoa(String btXoa) {
		this.btXoa = btXoa;
	}

	public String getBtSua() {
		return btSua;
	}

	public void setBtSua(String btSua) {
		this.btSua = btSua;
	}
}
