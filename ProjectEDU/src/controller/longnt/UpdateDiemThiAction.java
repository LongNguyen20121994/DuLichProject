/**
 * 
 */
package controller.longnt;

import java.util.List;
import java.util.Map;

import model.bean.ChiTietDKDT;
import model.bean.Info;
import model.bo.longnt.ChiTietDKDTBO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class UpdateDiemThiAction extends ActionSupport {

	private Info info;
	private List<ChiTietDKDT> listChiTietDKDT =  null;
	private List<String> listSoCMND;
	private String SoCMNDUpdate;
	
	private String btnSua;
	private String btnXoa;

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");
		if (soCMND != null && !soCMND.isEmpty()) {
			if (!listSoCMND.isEmpty()) {
				if (btnXoa != null) {
					return "delete";
				} else if (btnSua != null) {
					if (listSoCMND.size() == 1) {
						SoCMNDUpdate = listSoCMND.get(0);
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
			info = new Info("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}
	
	//Danh sách thông tin điểm thi
	public String display() {
		Map<String,Object> session =  ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");
		if (soCMND != null && !soCMND.isEmpty()){
			listChiTietDKDT = new ChiTietDKDTBO().getListInfoChiTietDKDT(soCMND);
			if ( listChiTietDKDT != null){
				return SUCCESS;
			} else {
				info = new Info("Thao tác thất bại", "Lỗi trong quá trình xử lý vui lòng thử lại sau!");
				return ERROR;
			}
		} else {
			info = new Info ("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<ChiTietDKDT> getListChiTietDKDT() {
		return listChiTietDKDT;
	}

	public void setListChiTietDKDT(List<ChiTietDKDT> listChiTietDKDT) {
		this.listChiTietDKDT = listChiTietDKDT;
	}

	public List<String> getListSoCMND() {
		return listSoCMND;
	}

	public void setListSoCMND(List<String> listSoCMND) {
		this.listSoCMND = listSoCMND;
	}

	public String getSoCMNDUpdate() {
		return SoCMNDUpdate;
	}

	public void setSoCMNDUpdate(String soCMNDUpdate) {
		SoCMNDUpdate = soCMNDUpdate;
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

}
