package controller.khoi;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.HoSoXetTuyen;
import model.bean.KhoiThi;
import model.bean.KhoiThiNganhDHCD;
import model.bean.MonThi;
import model.bean.XetTuyen;
import model.bo.khoi.ChiTietHoSoBO;
import model.bo.khoi.ChiTietKhoiThiBO;
import model.bo.khoi.GiangVienBO;
import model.bo.khoi.KhoiThiNganhDHCDBO;
import model.bo.khoi.NganhDHCDBO;
import model.bo.khoi.XetTuyenBO;

@SuppressWarnings("serial")
public class AjaxHoSoAction extends ActionSupport {
	private String maKhoi;
	private String maMon;
	private String maNganh;
	private String maTruong;
	private int namTuyenSinh;
	private String maDotXT;

	public String getMaNganh() {
		return maNganh;
	}

	public void setMaNganh(String maNganh) {
		this.maNganh = maNganh;
	}

	public int getNamTuyenSinh() {
		return namTuyenSinh;
	}

	public void setNamTuyenSinh(int namTuyenSinh) {
		this.namTuyenSinh = namTuyenSinh;
	}

	public String getMaKhoi() {
		return maKhoi;
	}

	public void setMaKhoi(String maKhoi) {
		this.maKhoi = maKhoi;
	}

	public String getMaMon() {
		return maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public String getMaTruong() {
		return maTruong;
	}

	public void setMaTruong(String maTruong) {
		this.maTruong = maTruong;
	}

	public String getMaDotXT() {
		return maDotXT;
	}

	public void setMaDotXT(String maDotXT) {
		this.maDotXT = maDotXT;
	}

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		ArrayList<MonThi> listMT = new ChiTietKhoiThiBO().getAllMonThi(maKhoi);
		if (!listMT.isEmpty()) {
			out.println("<tr><td><b>Mã môn</b></td><td><b>Tên môn</b></td><td align='center'><b>Xóa</b></td></tr>");
			for (MonThi mon : listMT) {
				out.println(
						"<tr><td>" + mon.getMaMonThi() + "</td><td>" + mon.getTenMonThi() + "</td><td align='center'>"
								+ "<input type='checkbox' name='listMT' value='" + mon.getMaMonThi() + "'/></td></tr>");
			}
		}
		return null;
	}

	public String checkMonThi() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		if (new ChiTietKhoiThiBO().validMonThi(maKhoi, maMon)) {
			out.println("invalid");
		} else {
			out.println("valid");
		}
		return null;
	}

	public String checkKhoiThi() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map<String, Object> session = ActionContext.getContext().getSession();
		KhoiThiNganhDHCD ktn = new KhoiThiNganhDHCD();
		if (session.get("soCMND") != null) {
			ktn.setMaTruong(new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong());
			String[] tmp = maNganh.split("-");
			ktn.setMaNganh(tmp[0]);
			ktn.setDaoTao(tmp[1]);
			ktn.setMaKhoi(maKhoi);
			ktn.setNamTuyenSinh(namTuyenSinh);
			if (new KhoiThiNganhDHCDBO().validKhoiThi(ktn)) {
				out.println("invalid");
			} else {
				out.println("valid");
			}
		}
		return null;
	}

	public String showListKhoiThi() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map<String, Object> session = ActionContext.getContext().getSession();
		KhoiThiNganhDHCD ktn = new KhoiThiNganhDHCD();
		if (session.get("soCMND") != null) {
			ktn.setMaTruong(new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong());
			String[] tmp = maNganh.split("-");
			ktn.setMaNganh(tmp[0]);
			ktn.setDaoTao(tmp[1]);
			ktn.setNamTuyenSinh(namTuyenSinh);
			ArrayList<KhoiThi> listKT = new KhoiThiNganhDHCDBO().getAllKhoiThi(ktn);
			if (!listKT.isEmpty()) {
				out.println(
						"<tr><td><b>Mã khối</b></td><td><b>Tên khối</b></td><td align='center'><b>Xóa</b></td></tr>");
				for (KhoiThi kt : listKT) {
					out.println("<tr><td>" + kt.getMaKhoi() + "</td><td>" + kt.getTenKhoi() + "</td><td align='center'>"
							+ "<input type='checkbox' name='listKT' value='" + kt.getMaKhoi() + "'/></td></tr>");
				}
			}
		}
		return null;
	}

	public String showNganhDHCD() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HashMap<String, String> list = new NganhDHCDBO().getAllTruongSelect(maTruong);
		out.println("<option value='-1'>Chọn ngành tuyển sinh</option>");
		for (Map.Entry<String, String> m : list.entrySet()) {
			out.println("<option value='" + m.getKey() + "'>" + m.getValue() + "</option>");
		}
		return null;
	}

	public String showKhoiThi() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		KhoiThiNganhDHCD ktn = new KhoiThiNganhDHCD();
		ktn.setMaTruong(maTruong);
		String[] tmp = maNganh.split("-");
		ktn.setMaNganh(tmp[0]);
		ktn.setDaoTao(tmp[1]);
		ktn.setNamTuyenSinh(namTuyenSinh);
		out.println("<option value='-1'>Chọn khối thi</option>");
		ArrayList<KhoiThi> listKT = new KhoiThiNganhDHCDBO().getAllKhoiThi(ktn);
		if (!listKT.isEmpty()) {
			for (KhoiThi kt : listKT) {
				out.println("<option value='" + kt.getMaKhoi() + "'>" + kt.getTenKhoi() + "</option>");
			}
		}
		return null;
	}

	public String showChiTietHoSo() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (session.get("soCMND") != null) {
			HoSoXetTuyen ct = new HoSoXetTuyen();
			ct.setSoCMND((String) session.get("soCMND"));
			ct.setNamTS(namTuyenSinh);
			ct.setMaDotXT(maDotXT);
			ct.setMaTruong(maTruong);
			ArrayList<String> list = new ChiTietHoSoBO().getAllHoSo(ct);
			if (!list.isEmpty()) {
				out.println("<tr><th>Mã Ngành</th><th>Tên ngành</th><th>Khối thi</th><th style='text-align:center'>Đăng ký | Xóa</th></tr>");
				for (String s : list) {
					String tmp[] = s.split("--");
					out.println("<tr><td>" + tmp[0] + "</td><td>"+ tmp[1] + "</td><td>Khối " + tmp[2] +"</td><td align='center'>"
							+ "<input type='checkbox' name='ktNganh' value='"+tmp[3]+"' checked='true'/></td></tr>");
				}
			}
		}
		return null;
	}
	
	public String showListKhoiThiXetTuyen() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map<String, Object> session = ActionContext.getContext().getSession();
		KhoiThiNganhDHCD ktn = new KhoiThiNganhDHCD();
		if (session.get("soCMND") != null) {
			ktn.setMaTruong(new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong());
			String[] tmp = maNganh.split("-");
			ktn.setMaNganh(tmp[0]);
			ktn.setDaoTao(tmp[1]);
			ktn.setNamTuyenSinh(namTuyenSinh);
			ArrayList<KhoiThi> listKT = new KhoiThiNganhDHCDBO().getAllKhoiThi(ktn);
			ArrayList<XetTuyen> listXT = new XetTuyenBO().getAllMonThiNganh(ktn);
			if (!listKT.isEmpty()) {
				for (KhoiThi kt : listKT) {
					out.println("<div><h4>" + kt.getTenKhoi() + "</h4>");
					List<XetTuyen> list = listXT;
					List<XetTuyen> result = list.stream().filter(p -> p.getMaKhoi().equals(kt.getMaKhoi())).collect(Collectors.toList());
					out.println("<table class='table'>");
					for(XetTuyen xt : result){
						String[] tam = xt.getMaMonThi().split(" - ");
						out.println("<tr><th style='padding-top:15px;'>"+tam[1]+"</th><input type='hidden' name='monKhoi' "
								+ "value='"+tam[0] + "-" + kt.getMaKhoi()+"'/>");
						out.println("<td><input type='number' name='heSo' class='form-control' "
								+ "value='"+xt.getHeSo()+"'/></td></tr>");
					}
					out.println("</table></div>");
				}
			} else {
				out.println("<font style='color:red;'><i>Chưa cập nhật khối thi</i></font>");
			}
		}
		return null;
	}
	
	public String showListKhoiThiNganhDHCD() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map<String, Object> session = ActionContext.getContext().getSession();
		KhoiThiNganhDHCD ktn = new KhoiThiNganhDHCD();
		if (session.get("soCMND") != null) {
			ktn.setMaTruong(new GiangVienBO().getInfo((String) session.get("soCMND")).getMaTruong());
			String[] tmp = maNganh.split("-");
			ktn.setMaNganh(tmp[0]);
			ktn.setDaoTao(tmp[1]);
			ktn.setNamTuyenSinh(namTuyenSinh);
			ArrayList<KhoiThi> listKT = new KhoiThiNganhDHCDBO().getAllKhoiThi(ktn);
			if (!listKT.isEmpty()) {
				out.println("<table class='table'><tr><th>Khối thi</th><th>Điểm chuẩn</th></tr>");
				for (KhoiThi kt : listKT) {
					out.println("<tr><th style='padding-top:15px'>" + kt.getTenKhoi() + "</th><td>");
					out.println("<input type='number' name='diemChuan' step='0.01' value='" + kt.getGhiChu() + "' class='form-control'/>");
					out.println("<input type='hidden' name='maKhoi' "
								+ "value='"+ kt.getMaKhoi()+"'/></td></tr>");
				}
			} else {
				out.println("<font style='color:red;'><i>Chưa cập nhật khối thi</i></font>");
			}
		}
		return null;
	}
}
