package controller.longnt;

import java.util.HashMap;
import java.util.Random;

import model.bean.Info;
import model.bean.QuanLyCumThi;
import model.bo.longnt.CumThiBO;
import model.bo.longnt.QuanLyCumThiBO;
import model.bo.longnt.TinhThanhPhoBO;

import com.opensymphony.xwork2.ActionSupport;

import common.Library;

@SuppressWarnings("serial")
public class DangKyQuanLyCumThiAction extends ActionSupport {
	private QuanLyCumThi qlct;
	private String hinhAnh;
	private HashMap<String, String> listTinh;
	private HashMap<String, String> listCumThi;
	private Info info;

	@Override
	public String execute() throws Exception {
		Random random = new Random();
		qlct.setMatKhau(String.valueOf(random.nextInt()));
		if (hinhAnh != null) {
			qlct.setHinhAnh("anhThanhVien/"
					+ Library.renameFile("/anhThanhVien", hinhAnh,
							qlct.getSoCMND()));
		} else {
			qlct.setHinhAnh("images/default.jpg");
		}
		QuanLyCumThiBO qlctbo = new QuanLyCumThiBO();
		if (qlctbo.insertQuanLyCumThi(qlct)) {
			info = new Info(
					"Quản lý cụm thi",
					"Đăng ký thông tin thành công.<br/>Mọi thông tin cần thiết sẽ được gửi về email của bạn trong thời gian sớm nhất!");
			return "info";
		} else {
			info = new Info("Lỗi",
					"Có lỗi trong quá trình thực hiện. Vui lòng kiểm tra lại!");
			return "info";
		}
	}

	public String showDangKyQuanLyCumThi() {
		listTinh = new TinhThanhPhoBO().getAllSelect();
		listCumThi = new CumThiBO().getAllSelect();
		return SUCCESS;
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

	public HashMap<String, String> getListCumThi() {
		return listCumThi;
	}

	public void setListCumThi(HashMap<String, String> listCumThi) {
		this.listCumThi = listCumThi;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}
}
