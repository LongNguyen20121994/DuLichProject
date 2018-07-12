package controller.quang;

import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;
import model.bean.ThiSinh;
import model.bo.quang.NamTuyenSinhBO;
import model.bo.quang.ThiSinhBO;

@SuppressWarnings("serial")
public class UpdateListThiSinhGVAction extends ActionSupport {

	private Info info;
	private List<ThiSinh> listTS = null;
	private List<String> listSoCMND;
	private String soCMNDUpdate;
	private int namTS;

	private String btnSua;
	private String btnXoa;

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");
		
		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("2".equals(account)) {
				if (!listSoCMND.isEmpty()) {
					if (btnXoa != null) {
						return "delete";
					} else if (btnSua != null) {
						if (listSoCMND.size() == 1) {
							soCMNDUpdate = listSoCMND.get(0);
							return "edit";
						} else {
							info = new Info("Thao tác thất bại", "Cập nhật chỉ được chọn một thí sinh!");
							return ERROR;
						}
					} else {
						info = new Info("Thao tác thất bại", "Lỗi trong quá trình xử lý vui lòng thử lại sau!");
						return ERROR;
					}
				} else {
					info = new Info("Thao tác thất bại", "Bạn chưa chọn thí sinh!");
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

	/**
	 * 
	 * Show table thi sinh theo giao vien
	 */
	public String display() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("2".equals(account)) {
				namTS = new NamTuyenSinhBO().getNamTSHienTai();
				listTS = new ThiSinhBO().getListInfoThiSinhGV(soCMND);
				if (listTS != null) {
					return SUCCESS;
				} else {
					info = new Info("Thao tác thất bại", "Lỗi trong quá trình xử lý vui lòng thử lại sau!");
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

	public String getSoCMNDUpdate() {
		return soCMNDUpdate;
	}

	public void setSoCMNDUpdate(String soCMNDUpdate) {
		this.soCMNDUpdate = soCMNDUpdate;
	}

	public String getBtnSua() {
		return btnSua;
	}

	public void setBtnSua(String btnSua) {
		this.btnSua = btnSua;
	}

	public String getBtnXoa() {
		return btnXoa;
	}

	public void setBtnXoa(String btnXoa) {
		this.btnXoa = btnXoa;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<ThiSinh> getListTS() {
		return listTS;
	}

	public void setListTS(List<ThiSinh> listTS) {
		this.listTS = listTS;
	}

	public List<String> getListSoCMND() {
		return listSoCMND;
	}

	public void setListSoCMND(List<String> listSoCMND) {
		this.listSoCMND = listSoCMND;
	}

}
