package controller.dulich;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class LogoutAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		Map<String, Object> session = ActionContext.getContext().getSession();
		session.remove("soCMND");
		session.remove("hoTen");
		session.remove("hinhAnh");
		session.remove("account");
		return SUCCESS;
	}
}
