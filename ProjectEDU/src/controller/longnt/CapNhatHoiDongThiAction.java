package controller.longnt;

import java.util.List;
import java.util.Map;

import model.bean.CumThi;
import model.bean.HoiDongThi;
import model.bean.Info;
import model.bo.longnt.CumThiBO;
import model.bo.longnt.HoiDongThiBO;
import model.bo.longnt.QuanLyCumThiBO;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class CapNhatHoiDongThiAction extends ActionSupport {
	private HoiDongThi hdt;
	private Info info;
	private List<HoiDongThi> list;
	private List<String> listMaHDT;
	private String maHDT;
	private String cumThi;

	private String classInput;
	private String classList;
	private String btnAddNew;
	private String btnUpdate;
	private String btnDelete;

	public String showCapNhatHoiDongThi() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("4".equals(account)) {
				if (classList == null) {
					classInput = "active";
				}
				list = new HoiDongThiBO().getAll();
				CumThi ct = new CumThiBO().getInfo(new QuanLyCumThiBO().getInfo(soCMND).getDonViThi());
				cumThi = ct.getMaCT() + " - " + ct.getTenCumThi();
				if (maHDT != null) {
					hdt = new HoiDongThiBO().getInfo(maHDT);
					if (hdt == null) {
						maHDT = null;
					}
				}
				return SUCCESS;
			} else {
				info = new Info("Lỗi", "Bạn không đủ quyền vào trang này!");
				return ERROR;
			}
		} else {
			info = new Info("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}

	@Override
	public String execute() throws Exception {
		if (btnUpdate != null) {
			if (new HoiDongThiBO().updateHoiDongThi(hdt)) {
				info = new Info("", "<font style='color:blue;'>" + hdt.getTenHDT() + " đã được cập nhật!</font><br/>");
			} else {
				info = new Info("", "<font style='color:red;'>" + hdt.getTenHDT() + " cập nhật không thành công!</font><br/>");
			}
			classList = "active";
		} else {
			if (btnAddNew != null) {
				cumThi = cumThi.split(" - ")[0];
				if (new HoiDongThiBO().insertHoiDongThi(hdt,cumThi)) {
					info = new Info("", "<font style='color:blue;'>"
							+ hdt.getTenHDT()
							+ " đã được thêm vào danh sách!</font><br/>");
					classList = "active";
				} else {
					info = new Info("", "<font style='color:red;'>"
							+ hdt.getTenHDT()
							+ " thêm không thành công!</font><br/>");
					classInput = "active";
				}
			}
		}
		return SUCCESS;
	}

	public String capNhatListHoiDongThi() {
		if (btnAddNew == null) {
			if (listMaHDT == null || listMaHDT.size() == 0) {
				info = new Info("", "<font style='color:red;'>Vui lòng chọn hội đồng thi trước khi thao tác!</font><br/>");
				classList = "active";
			} else {
				if (btnUpdate != null) {
					if (listMaHDT.size() == 1) {
						maHDT = listMaHDT.get(0);
						classInput = "active";
					} else {
						info = new Info("", "<font style='color:red;'>Chức năng sửa chỉ áp dụng cho 1 hội đồng thi!</font><br/>");
						classList = "active";
					}
				} else {
					if (btnDelete != null) {
						if (new HoiDongThiBO().deleteListHoiDongThi(listMaHDT)) {
							info = new Info("","<font style='color:red;'>Đã xóa thành công!</font><br/>");
						}
						classList = "active";
					}
				}
			}
		}
		return SUCCESS;
	}

	public String getCumThi() {
		return cumThi;
	}

	public void setCumThi(String cumThi) {
		this.cumThi = cumThi;
	}

	public HoiDongThi getHdt() {
		return hdt;
	}

	public void setHdt(HoiDongThi hdt) {
		this.hdt = hdt;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public List<HoiDongThi> getList() {
		return list;
	}

	public void setList(List<HoiDongThi> list) {
		this.list = list;
	}

	public List<String> getListMaHDT() {
		return listMaHDT;
	}

	public void setListMaHDT(List<String> listMaHDT) {
		this.listMaHDT = listMaHDT;
	}

	public String getMaHDT() {
		return maHDT;
	}

	public void setMaHDT(String maHDT) {
		this.maHDT = maHDT;
	}

	public String getClassInput() {
		return classInput;
	}

	public void setClassInput(String classInput) {
		this.classInput = classInput;
	}

	public String getClassList() {
		return classList;
	}

	public void setClassList(String classList) {
		this.classList = classList;
	}

	public String getBtnAddNew() {
		return btnAddNew;
	}

	public void setBtnAddNew(String btnAddNew) {
		this.btnAddNew = btnAddNew;
	}

	public String getBtnUpdate() {
		return btnUpdate;
	}

	public void setBtnUpdate(String btnUpdate) {
		this.btnUpdate = btnUpdate;
	}

	public String getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(String btnDelete) {
		this.btnDelete = btnDelete;
	}
}
