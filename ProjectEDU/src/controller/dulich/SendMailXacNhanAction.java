package controller.dulich;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.Library;
import common.SendEmail;
import model.bean.DLKhachHang;
import model.bo.ChiTietTourBO;
import model.bo.KhachHangBO;

@SuppressWarnings("serial")
public class SendMailXacNhanAction extends ActionSupport {
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String execute() throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Map<String, Object> session = ActionContext.getContext().getSession();
		// tao pass
		Random random = new Random();
		String pass = String.valueOf(random.nextInt());
		// update pass to DB
		KhachHangBO khbo = new KhachHangBO();
		if(khbo.updateKhachHang((String) session.get("makh"), Library.md5(pass))) {
			String tieuDe = "THONG TIN TAI KHOAN DAT TOUR";
			StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
			noiDung.append("<h2 style='background-color:#e30050;padding:1.5% 3%; margin:2px;'>Thông tin tài khoản đặt tour</h2>");
			noiDung.append("<div style='padding:2% 3.5%;'><p>Bạn đã đặt thành công tour: " + (String) session.get("tieude") 
					+ " trên website Du Lịch Việt của chúng tôi. <br/>Vui lòng sử dụng email đặt tour và mật khẩu bên dưới để đăng nhập!</p>");
			noiDung.append("<p>Mã Khách hàng : <b>" + (String) session.get("makh"));
			noiDung.append("</b><p>Mật khẩu : <b>" + pass);
			noiDung.append("</b><br/><p>Link đăng nhập: http://localhost:8081/ProjectEDU/showLoginKhachHang.trip<p>");
			email = (String) session.get("email");
			if(new SendEmail(email, tieuDe, noiDung.toString()).sendGmail()) {
				
				if((String) session.get("mact") != null && (String) session.get("soluong") != null) {
					ChiTietTourBO cttbo = new ChiTietTourBO();
					cttbo.updateSoLuong((String) session.get("mact"),(String) session.get("soluong"));
				}
				
				if (session.get("email") != null) {
					session.replace("email", email.trim() + "-" + pass);
				} else {
					session.put("email", email.trim() + "-" + pass);
				}
				out.println("<font style='color:blue'>Thành công. vui lòng kiểm tra lại email</font>");
			} else {
				out.println("<font style='color:red'>Không tìm thấy email bạn cung cấp</font>");
			}
			return SUCCESS;
		}
		return ERROR;
	}
	
	public void nhanVienGuiMail(DLKhachHang kh) throws Exception {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		//Map<String, Object> session = ActionContext.getContext().getSession();
		// tao pass
		Random random = new Random();
		String pass = String.valueOf(random.nextInt());
		// update pass to DB
		KhachHangBO khbo = new KhachHangBO();
		if(khbo.updateKhachHang(kh.getMaKH(), Library.md5(pass))) {
			String tieuDe = "THONG TIN TAI KHOAN DAT TOUR";
			StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
			noiDung.append("<h2 style='background-color:#e30050;padding:1.5% 3%; margin:2px;'>Thông tin tài khoản đặt tour</h2>");
			noiDung.append("<div style='padding:2% 3.5%;'><p>Bạn đã đặt tour" 
					+ " trên website Du Lịch Việt của chúng tôi. <br/>Vui lòng sử dụng email đặt tour và mật khẩu bên dưới để đăng nhập!</p>");
			noiDung.append("<p>Mã Khách hàng : <b>" + kh.getMaKH());
			noiDung.append("</b><p>Mật khẩu : <b>" + pass);
			noiDung.append("</b><br/><p>Link đăng nhập: http://localhost:8081/ProjectEDU/showLoginKhachHang.trip<p>");
			email = kh.getEmail();
			if(new SendEmail(email, tieuDe, noiDung.toString()).sendGmail()) {
				out.println("<font style='color:blue'>Thành công. vui lòng kiểm tra lại email</font>");
			} else {
				out.println("<font style='color:red'>Không tìm thấy email bạn cung cấp</font>");
			}
		}
	}
}
