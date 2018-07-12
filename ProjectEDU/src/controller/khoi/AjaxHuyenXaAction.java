package controller.khoi;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.khoi.HuyenQuanBO;
import model.bo.khoi.TruongDHCDBO;
import model.bo.khoi.XaPhuongBO;

@SuppressWarnings("serial")
public class AjaxHuyenXaAction extends ActionSupport {
	private String maTinh;
	private String maHuyen;

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	public String getMaHuyen() {
		return maHuyen;
	}

	public void setMaHuyen(String maHuyen) {
		this.maHuyen = maHuyen;
	}

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HashMap<String, String> list = new HuyenQuanBO().getListHuyenSelect(maTinh);
		out.println("<option value='-1'>Chọn huyện (quận)</option>");
		for (Map.Entry<String, String> m : list.entrySet()) {
			out.println("<option value='" + m.getKey() + "'>" + m.getValue() + "</option>");
		}
		return null;
	}
	public String showXa() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HashMap<String, String> list = new XaPhuongBO().getListXaSelect(maTinh,maHuyen);
		out.println("<option value='-1'>Chọn xã (phường)</option>");
		for (Map.Entry<String, String> m : list.entrySet()) {
			out.println("<option value='" + m.getKey() + "'>" + m.getValue() + "</option>");
		}
		return null;
	}

	public String showDHCD() throws Exception{
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HashMap<String, String> list;
		if("-1".equals(maTinh)){
			list = new TruongDHCDBO().getAllSelect();
		} else {
			list = new TruongDHCDBO().getListDHCDSelect(maTinh);
		}
		out.println("<option value='-1'>Chọn trường Đại học (Cao đẳng)</option>");
		for (Map.Entry<String, String> m : list.entrySet()) {
			out.println("<option value='" + m.getKey() + "'>" + m.getValue() + "</option>");
		}
		return null;
	}
}
