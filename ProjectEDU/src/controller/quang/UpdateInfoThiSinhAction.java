package controller.quang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.Info;
import model.bean.ThiSinh;
import model.bean.ThiSinhTHPT;
import model.bo.quang.DanTocBO;
import model.bo.quang.DoiTuongUTBO;
import model.bo.quang.HuyenQuanBO;
import model.bo.quang.ThiSinhBO;
import model.bo.quang.ThiSinhTHPTBO;
import model.bo.quang.TinhThanhPhoBO;
import model.bo.quang.TruongTHPTBO;
import model.bo.quang.XaPhuongBO;

@SuppressWarnings("serial")
public class UpdateInfoThiSinhAction extends ActionSupport {

	private Info info;
	private ThiSinh ts;
	private ThiSinhTHPT tstr10, tstr11, tstr12;
	private String hinhAnh;
	private HashMap<String, String> listTinh;
	private HashMap<String, String> listHuyen;
	private HashMap<String, String> listXa;

	private HashMap<String, String> listDanToc;
	private HashMap<String, String> listDoiTuongUT;
	private HashMap<String, String> listTruong10;
	private HashMap<String, String> listTruong11;
	private HashMap<String, String> listTruong12;

	private ThiSinhBO tsBO;
	private ThiSinhTHPTBO tsthptBO;

	private String soCMNDUpdate;

	private String showDangKyDuThiTHP;

	@Override
	public String execute() throws Exception {
		System.out.println("okok");
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");

		if (soCMND != null && !soCMND.isEmpty()) {
			String account = (String) session.get("account");
			System.out.println(account);
			if ("1".equals(account) || ("2".equals(account) && soCMNDUpdate != null && !"".equals(soCMNDUpdate))) {
				if(showDangKyDuThiTHP !=null && !"".equals(showDangKyDuThiTHP)){
					return "showDangKyDuThiTHPT";
				}
				ArrayList<ThiSinhTHPT> listTSTHPT = new ArrayList<ThiSinhTHPT>();

				if (soCMNDUpdate != null && !"".equals(soCMNDUpdate)){
					soCMND = soCMNDUpdate;
				}

				tstr10.setSoCMND(soCMND);
				tstr11.setSoCMND(soCMND);
				tstr12.setSoCMND(soCMND);

				tstr10.setLop(10);
				tstr11.setLop(11);
				tstr12.setLop(12);

				listTSTHPT.add(tstr10);
				listTSTHPT.add(tstr11);
				listTSTHPT.add(tstr12);

				if (hinhAnh != null) {
					ts.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, ts.getSoCMND()));
				}

				tsBO = new ThiSinhBO();
				tsthptBO = new ThiSinhTHPTBO();
				if (tsBO.updateInfo(ts) && tsthptBO.updateInfo(listTSTHPT)) {
					if (soCMNDUpdate != null && !"".equals(soCMNDUpdate)) {
						info = new Info("Thao tác thành công", "Đã cập nhật thông tin thí sinh: " + soCMND);
						return "giaovien";
					} else {
						session.remove("hoTen");
						session.put("hoTen",ts.getHoTen());
						session.remove("hinhAnh");
						session.put("hinhAnh", ts.getHinhAnh());
						return SUCCESS;
					}
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
			if ("1".equals(account) || ("2".equals(account) && soCMNDUpdate != null && !"".equals(soCMNDUpdate))) {
				if (soCMNDUpdate != null && !"".equals(soCMNDUpdate))
					soCMND = soCMNDUpdate;
				// ajax show
				listTinh = new TinhThanhPhoBO().getAllSelect();
				listDanToc = new DanTocBO().getAllSelect();
				listDoiTuongUT = new DoiTuongUTBO().getAllSelect();

				// get info thí sinh
				ArrayList<ThiSinhTHPT> listTSTHPT = new ArrayList<ThiSinhTHPT>();
				tsBO = new ThiSinhBO();
				ts = tsBO.getInfo(soCMND);
				
				tsthptBO = new ThiSinhTHPTBO();
				listTSTHPT = tsthptBO.getListUpdate(soCMND);

				if (ts != null) {

					// add lop 10
					if (listTSTHPT.size() > 0) {
						if (listTSTHPT.get(0).getLop() == 10) {
							tstr10 = listTSTHPT.get(0);
							listTruong10 = new TruongTHPTBO().getListTHPTSelect(tstr10.getMaTinhTHPT());
						} else {
							if (listTSTHPT.get(0).getLop() == 11) {
								tstr11 = listTSTHPT.get(0);
								listTruong11 = new TruongTHPTBO().getListTHPTSelect(tstr11.getMaTinhTHPT());
							} else {
								tstr12 = listTSTHPT.get(0);
								listTruong12 = new TruongTHPTBO().getListTHPTSelect(tstr12.getMaTinhTHPT());
							}
						}
					}

					// add lop 11
					if (listTSTHPT.size() > 1) {
						if (listTSTHPT.get(1).getLop() == 10) {
							tstr10 = listTSTHPT.get(1);
							listTruong10 = new TruongTHPTBO().getListTHPTSelect(tstr10.getMaTinhTHPT());
						} else {
							if (listTSTHPT.get(1).getLop() == 11) {
								tstr11 = listTSTHPT.get(1);
								listTruong11 = new TruongTHPTBO().getListTHPTSelect(tstr11.getMaTinhTHPT());
							} else {
								tstr12 = listTSTHPT.get(1);
								listTruong12 = new TruongTHPTBO().getListTHPTSelect(tstr12.getMaTinhTHPT());
							}
						}
					}

					// add lop 12
					if (listTSTHPT.size() > 2) {
						if (listTSTHPT.get(2).getLop() == 10) {
							tstr10 = listTSTHPT.get(2);
							listTruong10 = new TruongTHPTBO().getListTHPTSelect(tstr10.getMaTinhTHPT());
						} else {
							if (listTSTHPT.get(2).getLop() == 11) {
								tstr11 = listTSTHPT.get(2);
								listTruong11 = new TruongTHPTBO().getListTHPTSelect(tstr11.getMaTinhTHPT());
							} else {
								tstr12 = listTSTHPT.get(2);
								listTruong12 = new TruongTHPTBO().getListTHPTSelect(tstr12.getMaTinhTHPT());
							}
						}
					}

					listHuyen = new HuyenQuanBO().getListHuyenSelect(ts.getMaTinh());
					listXa = new XaPhuongBO().getListXaSelect(ts.getMaTinh(), ts.getMaHuyen());

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

	public String getShowDangKyDuThiTHP() {
		return showDangKyDuThiTHP;
	}

	public void setShowDangKyDuThiTHP(String showDangKyDuThiTHP) {
		this.showDangKyDuThiTHP = showDangKyDuThiTHP;
	}

	public String getSoCMNDUpdate() {
		return soCMNDUpdate;
	}

	public void setSoCMNDUpdate(String soCMNDUpdate) {
		this.soCMNDUpdate = soCMNDUpdate;
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

	public HashMap<String, String> getListTruong11() {
		return listTruong11;
	}

	public void setListTruong11(HashMap<String, String> listTruong11) {
		this.listTruong11 = listTruong11;
	}

	public HashMap<String, String> getListTruong12() {
		return listTruong12;
	}

	public void setListTruong12(HashMap<String, String> listTruong12) {
		this.listTruong12 = listTruong12;
	}

	public HashMap<String, String> getListTruong10() {
		return listTruong10;
	}

	public void setListTruong10(HashMap<String, String> listTruong) {
		this.listTruong10 = listTruong;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public ThiSinh getTs() {
		return ts;
	}

	public void setTs(ThiSinh ts) {
		this.ts = ts;
	}

	public ThiSinhTHPT getTstr10() {
		return tstr10;
	}

	public void setTstr10(ThiSinhTHPT tstr10) {
		this.tstr10 = tstr10;
	}

	public ThiSinhTHPT getTstr11() {
		return tstr11;
	}

	public void setTstr11(ThiSinhTHPT tstr11) {
		this.tstr11 = tstr11;
	}

	public ThiSinhTHPT getTstr12() {
		return tstr12;
	}

	public void setTstr12(ThiSinhTHPT tstr12) {
		this.tstr12 = tstr12;
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

	public HashMap<String, String> getListDanToc() {
		return listDanToc;
	}

	public void setListDanToc(HashMap<String, String> listDanToc) {
		this.listDanToc = listDanToc;
	}

	public HashMap<String, String> getListDoiTuongUT() {
		return listDoiTuongUT;
	}

	public void setListDoiTuongUT(HashMap<String, String> listDoiTuongUT) {
		this.listDoiTuongUT = listDoiTuongUT;
	}

}
