package controller.quang;

import java.util.HashMap;
import java.util.Random;

import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import model.bean.GiaoVien;
import model.bean.Info;
import model.bo.quang.GiaoVienBO;
import model.bo.quang.TinhThanhPhoBO;
import model.bo.quang.TruongTHPTBO;

@SuppressWarnings("serial")
public class DangKyGiaoVienAction extends ActionSupport {

	private GiaoVien gv;
	private String hinhAnh;
	private HashMap<String, String> listTinh;
	private HashMap<String, String> listTHPT;
	private Info info;

	@Override
	public String execute() throws Exception {
		if (hinhAnh != null) {
			gv.setHinhAnh("anhThanhVien/" + Library.renameFile("/anhThanhVien", hinhAnh, gv.getSoCMND()));
		} else {
			gv.setHinhAnh("images/default.jpg");
		}

		Random random = new Random();
		gv.setMatKhau(String.valueOf(random.nextInt()));

		GiaoVienBO giaoVienBO = new GiaoVienBO();
		if (giaoVienBO.insertGiaoVien(gv)) {
			info = new Info("Đăng ký thành công", "Bạn đã đăng ký thành công, vui lòng đợi xác thực tài khoản!");
			return SUCCESS;
		} else {
			info = new Info("Đăng ký thất bại", "Lỗi nhập liệu, vui lòng thử lại!");
			return ERROR;
		}
	}

	public String display() {
		listTinh = new TinhThanhPhoBO().getAllSelect();
		listTHPT = new TruongTHPTBO().getAllSelect();
		return NONE;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
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

	public HashMap<String, String> getListTHPT() {
		return listTHPT;
	}

	public void setListTHPT(HashMap<String, String> listTHPT) {
		this.listTHPT = listTHPT;
	}

}
