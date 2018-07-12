package controller.khoi;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import common.SendEmail;

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
		Random random = new Random();
		String maXN = String.valueOf(Math.abs(random.nextInt()));
		String tieuDe = "Mã xác nhận đăng ký tài khoản";
		StringBuilder noiDung = new StringBuilder("<div style='border:5px inset #CCC;'>");
		noiDung.append("<h2 style='background-color:#4d90fe;padding:1.5% 3%; margin:2px;'>Thông tin đăng ký tài khoản</h2>");
		noiDung.append("<div style='padding:2% 3.5%;'><p>Bạn đã gửi một yêu cầu xác nhận email đăng ký tài khoản trên website "
				+ "tuyển sinh của chúng tôi. <br/>Nếu không bạn gửi yêu cầu bạn có thể bỏ qua email này!</p>");
		noiDung.append("<p>Để hoàn thành đăng ký bạn vui lòng ");
		noiDung.append(" sử dụng mã số : <b>" + maXN);
		noiDung.append("</b> để điền vào thông tin đăng ký ở website");
		if(new SendEmail(email, tieuDe, noiDung.toString()).sendGmail()) {
			if (session.get("email") != null) {
				session.replace("email", email.trim() + "-" + maXN);
			} else {
				session.put("email", email.trim() + "-" + maXN);
			}
			out.println("<font style='color:blue'>Đã gửi thành công. vui lòng kiểm tra lại email</font>");
		} else {
			out.println("<font style='color:red'>Không thể kết nối đến email bạn cung cấp</font>");
		}
		return null;
	}
}
