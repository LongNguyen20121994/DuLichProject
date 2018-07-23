package controller.quang;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.MonThi;
import model.bo.quang.ChiTietDKDTBO;
import model.bo.quang.DangKyDuThiBO;
import model.bo.quang.GiaoVienBO;
import model.bo.quang.ThiSinhBO;
import model.bo.quang.TruongTHPTBO;

@SuppressWarnings("serial")
public class AjaxDKDTAction extends ActionSupport {
	private String soCMND;
	
	private String maTinh;

	private String maCumThi;

	private String maMon;
	private String namTS;
	private String soCMNDUpdate;

	/**
	 * load table môn thi
	 */
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		soCMND = (String) session.get("soCMND");
		if (soCMNDUpdate != null && !"".equals(soCMNDUpdate))
			soCMND = soCMNDUpdate;
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//System.out.println(soCMND + namTS);
		ArrayList<MonThi> listMT = new ChiTietDKDTBO().getAllMonThi(soCMND, Integer.valueOf(namTS));
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

	/**
	 * ajax show Trường THPT
	 * 
	 * @return
	 * @throws Exception
	 */
	public String showTHPT() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HashMap<String, String> list;
		if ("-1".equals(maTinh)) {
			list = new TruongTHPTBO().getAllSelect();
		} else {
			list = new TruongTHPTBO().getListTHPTSelect(maTinh);
		}
		out.println("<option value='-1'>Chọn trường THPT</option>");
		for (Map.Entry<String, String> m : list.entrySet()) {
			out.println("<option value='" + m.getKey() + "'>" + m.getValue() + "</option>");
		}
		return null;
	}

	/**
	 * Ajax check cụm thi DKDT
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkCumThiDKDT() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		soCMND = (String) session.get("soCMND");
		if (soCMNDUpdate != null && !"".equals(soCMNDUpdate))
			soCMND = soCMNDUpdate;
		
	//	System.out.println("cumthi");
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

	//	System.out.println(soCMND + "," +maCumThi+","+namTS);
		if (new DangKyDuThiBO().validCumThi(soCMND, maCumThi, Integer.valueOf(namTS))) {
			out.println("invalid");
		} else {
			out.println("valid");
		}
		return null;
	}

	/**
	 * Ajax check môn thi DKDT
	 * 
	 * @return
	 * @throws Exception
	 */
	public String checkMonThiDKDT() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		soCMND = (String) session.get("soCMND");
		if (soCMNDUpdate != null && !"".equals(soCMNDUpdate))
			soCMND = soCMNDUpdate;
		
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (new ChiTietDKDTBO().validMonThi(soCMND, Integer.valueOf(namTS), maMon)) {
			out.println("invalid");
		} else {
			out.println("valid");
		}
		return null;
	}
	
	/**
	 * Check SoCMND thi sinh
	 * @return
	 * @throws Exception
	 */
	public String checkThiSinh()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (new ThiSinhBO().isSoCMND(soCMND)) {
			out.println("invalid");
		} else {
			out.println("valid");
		}
		return null;
	}
	
	/**
	 * Check SoCMND giao vien
	 * @return
	 * @throws Exception
	 */
	public String checkGiaoVien()throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		if (new GiaoVienBO().isSoCMND(soCMND)) {
			out.println("invalid");
		} else {
			out.println("valid");
		}
		return null;
	}
	
	public String getSoCMND() {
		return soCMND;
	}

	public void setSoCMND(String soCMND) {
		this.soCMND = soCMND;
	}

	public String getSoCMNDUpdate() {
		return soCMNDUpdate;
	}

	public void setSoCMNDUpdate(String soCMNDUpdate) {
		this.soCMNDUpdate = soCMNDUpdate;
	}

	public String getMaCumThi() {
		return maCumThi;
	}

	public void setMaCumThi(String maCumThi) {
		this.maCumThi = maCumThi;
	}

	public String getMaMon() {
		return maMon;
	}

	public void setMaMon(String maMon) {
		this.maMon = maMon;
	}

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public String getNamTS() {
		return namTS;
	}

	public void setNamTS(String namTS) {
		this.namTS = namTS;
	}

}
