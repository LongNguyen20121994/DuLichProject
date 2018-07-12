package controller.quang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.Info;
import model.bean.ThiSinh;
import model.bean.ThiSinhTHPT;
import model.bo.quang.DanTocBO;
import model.bo.quang.DoiTuongUTBO;
import model.bo.quang.ThiSinhBO;
import model.bo.quang.ThiSinhTHPTBO;
import model.bo.quang.TinhThanhPhoBO;

@SuppressWarnings("serial")
public class DangKyThiSinhTuDoAction extends ActionSupport {

	private Info info;
	private ThiSinh ts;
	private ThiSinhTHPT tstr10, tstr11, tstr12;
	private String hinhAnh;
	private HashMap<String, String> listTinh;
	private HashMap<String, String> listDanToc;
	private HashMap<String, String> listDoiTuongUT;
	private ThiSinhBO tsbo;
	private ThiSinhTHPTBO tsTHPTbo;

	@Override
	public String execute() throws Exception {
		Random random = new Random();
		ts.setMatKhau(String.valueOf(random.nextInt()));
		if (hinhAnh != null) {
			ts.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, ts.getSoCMND()));
		} else {
			ts.setHinhAnh("images/default.jpg");
		}
		// System.out.println(ts);

		Map<String, Object> session = ActionContext.getContext().getSession();
		String acc = (String) session.get("account");
		ts.setTrangThai(false);
		if (acc != null) {
			int account = Integer.parseInt((String) session.get("account"));
			String nguoiDK = (String) session.get("soCMND");
			if (account == 2) {
				ts.setNguoiDK(nguoiDK);
				ts.setTrangThai(true);
			}
		}
		tsbo = new ThiSinhBO();
		if (tsbo.insertThiSinh(ts)) {
			tstr10.setSoCMND(ts.getSoCMND());
			tstr11.setSoCMND(ts.getSoCMND());
			tstr12.setSoCMND(ts.getSoCMND());

			tstr10.setLop(10);
			tstr11.setLop(11);
			tstr12.setLop(12);

			ArrayList<ThiSinhTHPT> list = new ArrayList<ThiSinhTHPT>();
			list.add(tstr10);
			list.add(tstr11);
			list.add(tstr12);
			
			tsTHPTbo = new ThiSinhTHPTBO();
			String tsTHPTError = tsTHPTbo.themListThiSinhTHPT(list);
			if (tsTHPTError != null && !"".equals(tsTHPTError)) {
				info = new Info("Đăng ký thất bại", "Lỗi nhập liệu trường THPT");
				return ERROR;
			} else {
				info = new Info("Đăng ký thành công",
						" Bạn đã đăng ký thành công, Xin hãy đợi chúng tôi xác thực thông tin!");
				return SUCCESS;
			}

		} else {
			info = new Info("Đăng ký thất bại", "Lỗi nhập liệu thông tin thí sinh");
			return ERROR;
		}
	}

	public String display() {
		listTinh = new TinhThanhPhoBO().getAllSelect();
		listDanToc = new DanTocBO().getAllSelect();
		listDoiTuongUT = new DoiTuongUTBO().getAllSelect();
		return NONE;
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

	public ThiSinhTHPT getTstr10() {
		return tstr10;
	}

	public void setTstr10(ThiSinhTHPT tstr) {
		this.tstr10 = tstr;
	}

	public HashMap<String, String> getListDoiTuongUT() {
		return listDoiTuongUT;
	}

	public void setListDoiTuongUT(HashMap<String, String> listDoiTuongUT) {
		this.listDoiTuongUT = listDoiTuongUT;
	}

	public HashMap<String, String> getListDanToc() {
		return listDanToc;
	}

	public void setListDanToc(HashMap<String, String> listDanToc) {
		this.listDanToc = listDanToc;
	}

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}

	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
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

}
