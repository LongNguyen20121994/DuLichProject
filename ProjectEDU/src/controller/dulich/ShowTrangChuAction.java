package controller.dulich;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLTourTrangChu;
import model.bo.DLTourBO;

@SuppressWarnings("serial")
public class ShowTrangChuAction extends ActionSupport {
	private List<DLTourTrangChu> listToursMB;
	private List<DLTourTrangChu> listToursMT;
	private List<DLTourTrangChu> listToursMN;
	
	public List<DLTourTrangChu> getListToursMB() {
		return listToursMB;
	}
	public void setListToursMB(List<DLTourTrangChu> listToursMB) {
		this.listToursMB = listToursMB;
	}
	public List<DLTourTrangChu> getListToursMT() {
		return listToursMT;
	}
	public void setListToursMT(List<DLTourTrangChu> listToursMT) {
		this.listToursMT = listToursMT;
	}
	public List<DLTourTrangChu> getListToursMN() {
		return listToursMN;
	}
	public void setListToursMN(List<DLTourTrangChu> listToursMN) {
		this.listToursMN = listToursMN;
	}

	@Override
	public String execute() throws Exception {
		listToursMB = new DLTourBO().getTop("1");
		listToursMT = new DLTourBO().getTop("2");
		listToursMN = new DLTourBO().getTop("3");
		return SUCCESS;
	}
}
