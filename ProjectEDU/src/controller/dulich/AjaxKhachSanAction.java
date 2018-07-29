package controller.dulich;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import model.bo.KhachSanBO;

@SuppressWarnings("serial")
public class AjaxKhachSanAction extends ActionSupport {
	private String maTinh;

	public String getMaTinh() {
		return maTinh;
	}

	public void setMaTinh(String maTinh) {
		this.maTinh = maTinh;
	}

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HashMap<String, String> list = new KhachSanBO().getAllByMaTinh(maTinh);
		out.println("<option value='-1'>Chọn Tỉnh</option>");
		for (Map.Entry<String, String> m : list.entrySet()) {
			out.println("<option value='" + m.getKey() + "'>" + m.getValue() + "</option>");
		}
		return null;
	}
}
