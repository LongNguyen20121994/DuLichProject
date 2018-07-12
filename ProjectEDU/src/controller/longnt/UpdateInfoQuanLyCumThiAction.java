package controller.longnt;

import java.util.HashMap;
import java.util.Map;

import model.bean.Info;
import model.bean.QuanLyCumThi;
import model.bo.longnt.CumThiBO;
import model.bo.longnt.QuanLyCumThiBO;
import model.bo.quang.HuyenQuanBO;
import model.bo.quang.TinhThanhPhoBO;
import model.bo.quang.XaPhuongBO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import common.Library;

@SuppressWarnings("serial")
public class UpdateInfoQuanLyCumThiAction extends ActionSupport{
	private QuanLyCumThi  qlct;
	private String hinhAnh;
	private HashMap<String, String> listTinh;
	private HashMap<String, String> listHuyen;
	private HashMap<String, String> listXa;
	
	private HashMap<String, String> listCumThi;
	private Info info;
	private QuanLyCumThiBO qlctBO = new QuanLyCumThiBO();
	
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("4".equals(account)) {
				if (hinhAnh != null) {
					qlct.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, qlct.getSoCMND()));
				}else {
					qlct.setHinhAnh((String) session.get("hinhAnh"));
				}

				if (qlctBO.updateInfo(qlct)) {
					session.replace("hoTen", qlct.getHoTen());
					session.replace("hinhAnh", qlct.getHinhAnh());
					return SUCCESS;
				} else {
					info = new Info("Thao tác thất bại", "Lỗi nhập liệu, vui lòng thử lại!");
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
	
	public String display() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");
		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("4".equals(account)) {
				// ajax show
				listTinh = new TinhThanhPhoBO().getAllSelect();

				qlct = qlctBO.getInfo(soCMND);
				if (qlct != null) {
					listCumThi = new CumThiBO().getListCumThiSelect();
					listHuyen = new HuyenQuanBO().getListHuyenSelect(qlct.getMaTinh());
					listXa = new XaPhuongBO().getListXaSelect(qlct.getMaTinh(), qlct.getMaHuyen());
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

	public QuanLyCumThi getQlct() {
		return qlct;
	}

	public void setQlct(QuanLyCumThi qlct) {
		this.qlct = qlct;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}

	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}

	public HashMap<String, String> getListHuyen() {
		return listHuyen;
	}

	public void setListHuyen(HashMap<String, String> listHuyen) {
		this.listHuyen = listHuyen;
	}

	public HashMap<String, String> getListXa() {
		return listXa;
	}

	public void setListXa(HashMap<String, String> listXa) {
		this.listXa = listXa;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public QuanLyCumThiBO getQlctBO() {
		return qlctBO;
	}

	public void setQlctBO(QuanLyCumThiBO qlctBO) {
		this.qlctBO = qlctBO;
	}

	public HashMap<String, String> getListCumThi() {
		return listCumThi;
	}

	public void setListCumThi(HashMap<String, String> listCumThi) {
		this.listCumThi = listCumThi;
	}
	
	
}
