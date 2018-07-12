package controller.quang;

import java.util.ArrayList;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.GiangVien;
import model.bean.GiaoVien;
import model.bean.Info;
import model.bean.QuanLyCumThi;
import model.bean.QuanTriVien;
import model.bean.ThiSinh;
import model.bo.quang.GiangVienBO;
import model.bo.quang.GiaoVienBO;
import model.bo.quang.QuanLyCumThiBO;
import model.bo.quang.QuanTriVienBO;
import model.bo.quang.ThiSinhBO;

@SuppressWarnings("serial")
public class ShowInfoHienThiAction extends ActionSupport {

	private GiaoVien gv;
	private ThiSinh ts;
	private GiangVien giangVien;
	private QuanTriVien qtv;
	private QuanLyCumThi qlct;
	private Info info;

	private String tenDanToc;
	private String tenDoiTuongUT;
	private String tenXaHK;
	private String tenHuyenHK;
	private String tenTinhHK;

	private String tenTruongDHCD;

	private String tenTruongTHPT10;
	private String tenTruongTHPT11;
	private String tenTruongTHPT12;
	private String tenTinhTHPT10;
	private String tenTinhTHPT11;
	private String tenTinhTHPT12;
	private String tenKhuVucUT;

	private String tenCumThi;

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		String soCMND = (String) session.get("soCMND");
		String loaiTK = (String) session.get("account");
		Map<String, Object> rs;

		if (soCMND != null && !soCMND.isEmpty()) {

			// show info thi sinh
			if ("1".equals(loaiTK)) {
				ThiSinhBO thiSinhBO = new ThiSinhBO();
				rs = thiSinhBO.getInfoHienThi(soCMND);

				if (rs == null) {
					info = new Info("Lỗi hệ thống", "Lỗi trong quá trình xử lý, vui lòng quay lại sau!");
					return ERROR;
				} else {

					ts = (ThiSinh) rs.get("thiSinh");
					tenXaHK = (String) rs.get("tenXa");
					tenHuyenHK = (String) rs.get("tenHuyen");
					tenTinhHK = (String) rs.get("tenTinh");
					tenDanToc = (String) rs.get("tenDanToc");
					tenDoiTuongUT = (String) rs.get("tenDoiTuongUT");

					ArrayList<Object> lop10 = (ArrayList<Object>) rs.get("lop10");
					ArrayList<Object> lop11 = (ArrayList<Object>) rs.get("lop11");
					ArrayList<Object> lop12 = (ArrayList<Object>) rs.get("lop12");

					if (lop10 != null) {
						tenTruongTHPT10 = (String) lop10.get(2);
						tenTinhTHPT10 = (String) lop10.get(3);
					}
					if (lop11 != null) {
						tenTruongTHPT11 = (String) lop11.get(2);
						tenTinhTHPT11 = (String) lop11.get(3);
					}
					if (lop12 != null) {
						tenTruongTHPT12 = (String) lop12.get(2);
						tenTinhTHPT12 = (String) lop12.get(3);
					}

					tenKhuVucUT = (String) rs.get("tenKhuVucUT");

					return "thisinh";
				}
			}

			// show info giao vien
			if ("2".equals(loaiTK)) {
				GiaoVienBO giaoVienBO = new GiaoVienBO();
				gv = giaoVienBO.getInfoHienThi(soCMND);
				if (gv == null) {
					info = new Info("Lỗi hệ thống", "Lỗi trong quá trình xử lý, vui lòng quay lại sau!");
					return ERROR;
				} else {
					return "giaovien";
				}
			}

			// show info giang vien
			if ("3".equals(loaiTK)) {
				GiangVienBO giangVienBO = new GiangVienBO();
				rs = giangVienBO.getInfoHienThi(soCMND);
				if (rs == null) {
					info = new Info("Lỗi hệ thống", "Lỗi trong quá trình xử lý, vui lòng quay lại sau!");
					return ERROR;
				} else {

					giangVien = (GiangVien) rs.get("giangVien");
					tenXaHK = (String) rs.get("tenXa");
					tenHuyenHK = (String) rs.get("tenHuyen");
					tenTinhHK = (String) rs.get("tenTinh");
					tenTruongDHCD = (String) rs.get("tenTruongDHCD");

					return "giangvien";
				}

			}

			// show info quanLyCumThi
			if ("4".equals(loaiTK)) {
				QuanLyCumThiBO qlctBO = new QuanLyCumThiBO();
				rs = qlctBO.getInfoHienThi(soCMND);

				if (rs == null) {
					info = new Info("Lỗi hệ thống", "Lỗi trong quá trình xử lý, vui lòng quay lại sau!");
					return ERROR;
				} else {

					qlct = (QuanLyCumThi) rs.get("qlct");
					tenXaHK = (String) rs.get("tenXa");
					tenHuyenHK = (String) rs.get("tenHuyen");
					tenTinhHK = (String) rs.get("tenTinh");
					tenCumThi = (String) rs.get("tenCumThi");

					return "qlcumthi";
				}
			}

			// show info Quản trị viên
			if ("5".equals(loaiTK)) {
				QuanTriVienBO qtvBO = new QuanTriVienBO();
				rs = qtvBO.getInfoHienThi(soCMND);

				if (rs == null) {
					info = new Info("Lỗi hệ thống", "Lỗi trong quá trình xử lý, vui lòng quay lại sau!");
					return ERROR;
				} else {

					qtv = (QuanTriVien) rs.get("qtv");
					tenXaHK = (String) rs.get("tenXa");
					tenHuyenHK = (String) rs.get("tenHuyen");
					tenTinhHK = (String) rs.get("tenTinh");

					return "quantrivien";
				}

			}
			info = new Info("Lỗi hệ thống", "Lỗi trong quá trình xử lý, vui lòng quay lại sau!");
			return ERROR;
		} else {
			info = new Info("Thao tác thất bại", "Bạn chưa đăng nhập!");
			return ERROR;
		}
	}

	public String getTenCumThi() {
		return tenCumThi;
	}

	public void setTenCumThi(String tenCumThi) {
		this.tenCumThi = tenCumThi;
	}

	public String getTenKhuVucUT() {
		return tenKhuVucUT;
	}

	public void setTenKhuVucUT(String tenKhuVucUT) {
		this.tenKhuVucUT = tenKhuVucUT;
	}

	public String getTenTruongTHPT10() {
		return tenTruongTHPT10;
	}

	public void setTenTruongTHPT10(String tenTruongTHPT10) {
		this.tenTruongTHPT10 = tenTruongTHPT10;
	}

	public String getTenTruongTHPT11() {
		return tenTruongTHPT11;
	}

	public void setTenTruongTHPT11(String tenTruongTHPT11) {
		this.tenTruongTHPT11 = tenTruongTHPT11;
	}

	public String getTenTruongTHPT12() {
		return tenTruongTHPT12;
	}

	public void setTenTruongTHPT12(String tenTruongTHPT12) {
		this.tenTruongTHPT12 = tenTruongTHPT12;
	}

	public String getTenTinhTHPT10() {
		return tenTinhTHPT10;
	}

	public void setTenTinhTHPT10(String tenTinhTHPT10) {
		this.tenTinhTHPT10 = tenTinhTHPT10;
	}

	public String getTenTinhTHPT11() {
		return tenTinhTHPT11;
	}

	public void setTenTinhTHPT11(String tenTinhTHPT11) {
		this.tenTinhTHPT11 = tenTinhTHPT11;
	}

	public String getTenTinhTHPT12() {
		return tenTinhTHPT12;
	}

	public void setTenTinhTHPT12(String tenTinhTHPT12) {
		this.tenTinhTHPT12 = tenTinhTHPT12;
	}

	public ThiSinh getTs() {
		return ts;
	}

	public void setTs(ThiSinh ts) {
		this.ts = ts;
	}

	public GiangVien getGiangVien() {
		return giangVien;
	}

	public void setGiangVien(GiangVien giangVien) {
		this.giangVien = giangVien;
	}

	public QuanTriVien getQtv() {
		return qtv;
	}

	public void setQtv(QuanTriVien qtv) {
		this.qtv = qtv;
	}

	public QuanLyCumThi getQlct() {
		return qlct;
	}

	public void setQlct(QuanLyCumThi qlct) {
		this.qlct = qlct;
	}

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public String getTenDanToc() {
		return tenDanToc;
	}

	public void setTenDanToc(String tenDanToc) {
		this.tenDanToc = tenDanToc;
	}

	public String getTenDoiTuongUT() {
		return tenDoiTuongUT;
	}

	public void setTenDoiTuongUT(String tenDoiTuongUT) {
		this.tenDoiTuongUT = tenDoiTuongUT;
	}

	public String getTenXaHK() {
		return tenXaHK;
	}

	public void setTenXaHK(String tenXaHK) {
		this.tenXaHK = tenXaHK;
	}

	public String getTenHuyenHK() {
		return tenHuyenHK;
	}

	public void setTenHuyenHK(String tenHuyenHK) {
		this.tenHuyenHK = tenHuyenHK;
	}

	public String getTenTinhHK() {
		return tenTinhHK;
	}

	public void setTenTinhHK(String tenTinhHK) {
		this.tenTinhHK = tenTinhHK;
	}

	public String getTenTruongDHCD() {
		return tenTruongDHCD;
	}

	public void setTenTruongDHCD(String tenTruongDHCD) {
		this.tenTruongDHCD = tenTruongDHCD;
	}

	public GiaoVien getGv() {
		return gv;
	}

	public void setGv(GiaoVien gv) {
		this.gv = gv;
	}

}
