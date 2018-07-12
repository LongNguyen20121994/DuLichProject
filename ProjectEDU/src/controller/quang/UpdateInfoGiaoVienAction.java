package controller.quang;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.GiaoVien;
import model.bean.Info;
import model.bo.quang.GiaoVienBO;
import model.bo.quang.HuyenQuanBO;
import model.bo.quang.TinhThanhPhoBO;
import model.bo.quang.TruongTHPTBO;
import model.bo.quang.XaPhuongBO;

@SuppressWarnings("serial")
public class UpdateInfoGiaoVienAction extends ActionSupport {

	private GiaoVien gv;
	private String hinhAnh;
	private HashMap<String, String> listTinh;
	private HashMap<String, String> listHuyen;
	private HashMap<String, String> listXa;

	private HashMap<String, String> listTHPT;
	private Info info;
	private GiaoVienBO giaoVienBO;

	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("2".equals(account)) {
				if (hinhAnh != null) {
					gv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, gv.getSoCMND()));
				}

				giaoVienBO = new GiaoVienBO();
				if (giaoVienBO.updateInfo(gv)) {
					session.remove("hoTen");
					session.put("hoTen", gv.getHoTen());
					session.remove("hinhAnh");
					session.put("hinhAnh", gv.getHinhAnh());
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
			if ("2".equals(account)) {
				// ajax show
				listTinh = new TinhThanhPhoBO().getAllSelect();

				giaoVienBO = new GiaoVienBO();
				gv = giaoVienBO.getInfo(soCMND);
				if (gv != null) {
					listTHPT = new TruongTHPTBO().getListTHPTSelect(gv.getMaTinhTHPT());

					listHuyen = new HuyenQuanBO().getListHuyenSelect(gv.getMaTinh());
					listXa = new XaPhuongBO().getListXaSelect(gv.getMaTinh(), gv.getMaHuyen());
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

	public GiaoVien getGv() {
		return gv;
	}

	public void setGv(GiaoVien gv) {
		this.gv = gv;
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

	public HashMap<String, String> getListTHPT() {
		return listTHPT;
	}

	public void setListTHPT(HashMap<String, String> listTHPT) {
		this.listTHPT = listTHPT;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

}
