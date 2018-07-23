package controller.dulich;

import java.util.HashMap;

import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import controller.khoi.LoginAction;
import model.bean.DLTour;
import model.bean.Info;
import model.bo.DLTinhBO;
import model.bo.DLTourBO;

@SuppressWarnings("serial")
public class DangKyTourAction extends ActionSupport {
	private DLTour tour;
	private String maTour;
	private String hinhAnh;
	private int maxTour;
	private HashMap<String, String> listTinh;
	private Info info;

	public DLTour getTour() {
		return tour;
	}

	public void setTour(DLTour tour) {
		this.tour = tour;
	}

	public String getMaTour() {
		return maTour;
	}

	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public int getMaxTour() {
		return maxTour;
	}

	public void setMaxTour(int maxTour) {
		this.maxTour = maxTour;
	}

	public HashMap<String, String> getListTinh() {
		return listTinh;
	}

	public void setListTinh(HashMap<String, String> listTinh) {
		this.listTinh = listTinh;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String execute() {
		info = new LoginAction().checkLogin("6");
		if (info != null) {
			if (info.getTieuDe() == null) {
				return "login";
			} else {
				return "info";
			}
		}
		listTinh = new DLTinhBO().getAllSelect();
		String timMax = new DLTourBO().getMaxRecord();
		if (timMax != null) {
			maxTour = catChuoi(timMax) + 1;
		} else {
			maxTour = 1;
		}
		tour.setMaTour(taoMa(maxTour));
		if (hinhAnh != null) {
			tour.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, "" + taoMa(maxTour)));
		} else {
			tour.setHinhAnh("images/default.jpg");
		}
		DLTourBO tourbo = new DLTourBO();
		if (tourbo.insertTour(tour)) {
			info = new Info("Tạo tour", "Tạo Tour thành công.!");
			return "info";
		} else {
			info = new Info("Tạo tour", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}

	public String taoMa(int max) {
		int i, n = max;
		for (i = 1; n > 10; i++)
			n /= 10;
		StringBuilder ma = new StringBuilder();
		for (int j = 0; j < 10 - i; j++)
			ma.append("0");
		return "TUR" + ma.append(max);
	}

	public int catChuoi(String chuoi) {
		int so = Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}

	public String showDangKyTour() {
		listTinh = new DLTinhBO().getAllSelect();
		return SUCCESS;
	}

	public String showCapNhatTour() {
		info = new LoginAction().checkLogin("6");
		if (info != null) {
			if (info.getTieuDe() == null) {
				return "login";
			} else {
				return "info";
			}
		}
		tour = new DLTourBO().getInfo(maTour);
		listTinh = new DLTinhBO().getAllSelect();
		// listHuyen = new HuyenQuanBO().getListHuyenSelect(gv.getMaTinh());
		// listXa = new XaPhuongBO().getListXaSelect(gv.getMaTinh(),gv.getMaHuyen());
		// listDHCD = new TruongDHCDBO().getAllSelect();
		return SUCCESS;
	}

	public String capNhatTour() {
		info = new LoginAction().checkLogin("6");
		if (info != null) {
			if (info.getTieuDe() == null) {
				return "login";
			} else {
				return "info";
			}
		}
		if (hinhAnh != null) {
			tour.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, tour.getMaTour()));
		}

		DLTourBO tourbo = new DLTourBO();
		if (tourbo.updateTour(tour)) {
			info = new Info("Cập nhật tour", "Cập nhật Tour thành công.!");
			return "info";
		} else {
			info = new Info("Cập nhật tour", "Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}
}
