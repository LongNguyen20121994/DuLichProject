package controller.quang;

import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.ChiTietDKDT;
import model.bean.DangKyDuThi;
import model.bean.Info;
import model.bo.quang.ChiTietDKDTBO;
import model.bo.quang.CumThiBO;
import model.bo.quang.DangKyDuThiBO;
import model.bo.quang.MonThiBO;
import model.bo.quang.NamTuyenSinhBO;
import model.bo.quang.ThiSinhTHPTBO;

@SuppressWarnings("serial")
public class DangKyDuThiTHPTAction extends ActionSupport {

	private String[] listMT;
	private Info info;
	private HashMap<String, String> listCumThi;
	private HashMap<String, String> listMon;
	private DangKyDuThi dkdt;
	private ChiTietDKDT ctDKDT;
	private int namTS;
	private String soCMNDUpdate;

	@Override
	public String execute() throws Exception {
		//System.out.println("okkkkkkkk");
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			if ("1".equals(account) || ("2".equals(account) && soCMNDUpdate != null && !"".equals(soCMNDUpdate))) {
				if (soCMNDUpdate != null && !"".equals(soCMNDUpdate))
					soCMND = soCMNDUpdate;
			//	System.out.println(soCMNDUpdate);

				dkdt.setSoCMND(soCMND);
				dkdt.setNamTS(namTS);

				boolean checkDel = false;
				boolean checkInsert = false;

				if (listMT != null) {
					checkDel = new ChiTietDKDTBO().deleteListMonDKDT(soCMND, namTS, listMT);
				} else {
					if (ctDKDT != null) {
						ctDKDT.setNamTS(namTS);
						ctDKDT.setSoCMND(soCMND);
						checkInsert = new ChiTietDKDTBO().insertChiTietDKDT(dkdt, ctDKDT);
					} else {
						info = new Info("Lỗi", "Bạn phải chọn môn để đăng ký thi hoặc hủy thi!");
						return ERROR;
					}
				}
				if (checkInsert) {
					return SUCCESS;
				} else {
					if (checkDel) {
						return SUCCESS;
					} else {
						info = new Info("Lỗi", "Lỗi trong quá trình xử lý, vui lòng thử lại!");
						return ERROR;
					}
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
			
			if ("1".equals(account) || ("2".equals(account) && soCMNDUpdate != null && !"".equals(soCMNDUpdate))) {
				if (soCMNDUpdate != null && !"".equals(soCMNDUpdate))
					soCMND = soCMNDUpdate;
				if (new ThiSinhTHPTBO().isComplete(soCMND)) {
					// System.out.println("in: " + soCMND + "[" + soCMNDUpdate + "]");
					namTS = new NamTuyenSinhBO().getNamTSHienTai();
					listCumThi = new CumThiBO().getAllSelect();
					listMon = new MonThiBO().getAllSelect();
					dkdt = new DangKyDuThiBO().getInfo(soCMND, namTS);

					return SUCCESS;
				} else {
					info = new Info("Thao tác thất bại",
							"Vui lòng cập nhật đầy đủ thông tin cá nhân trước khi đăng ký dự thi!");
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

	public String getSoCMNDUpdate() {
		return soCMNDUpdate;
	}

	public void setSoCMNDUpdate(String soCMNDUpdate) {
		this.soCMNDUpdate = soCMNDUpdate;
	}

	public String[] getListMT() {
		return listMT;
	}

	public void setListMT(String[] listMT) {
		this.listMT = listMT;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public HashMap<String, String> getListCumThi() {
		return listCumThi;
	}

	public void setListCumThi(HashMap<String, String> listCumThi) {
		this.listCumThi = listCumThi;
	}

	public HashMap<String, String> getListMon() {
		return listMon;
	}

	public void setListMon(HashMap<String, String> listMon) {
		this.listMon = listMon;
	}

	public DangKyDuThi getDkdt() {
		return dkdt;
	}

	public void setDkdt(DangKyDuThi dkdt) {
		this.dkdt = dkdt;
	}

	public ChiTietDKDT getCtDKDT() {
		return ctDKDT;
	}

	public void setCtDKDT(ChiTietDKDT ctDKDT) {
		this.ctDKDT = ctDKDT;
	}

	public int getNamTS() {
		return namTS;
	}

	public void setNamTS(int namTS) {
		this.namTS = namTS;
	}

}
