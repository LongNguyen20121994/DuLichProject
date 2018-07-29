package controller.dulich;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.DLTourBO;

@SuppressWarnings("serial")
public class AjaxTourAction extends ActionSupport {
	private String maLoai;
	
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HashMap<String, String> list = new DLTourBO().getAllByMaLoaiTour(maLoai);
		out.println("<option value='-1'>Chọn Loại Tour</option>");
		for (Map.Entry<String, String> m : list.entrySet()) {
			out.println("<option value='" + m.getKey() + "'>" + m.getValue() + "</option>");
		}
		return null;
	}
}
