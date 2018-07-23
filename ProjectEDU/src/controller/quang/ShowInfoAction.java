package controller.quang;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.Info;

@SuppressWarnings("serial")
public class ShowInfoAction extends ActionSupport {
	private Info info;

	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return SUCCESS;
	}

}
