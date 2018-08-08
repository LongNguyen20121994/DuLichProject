package controller.dulich;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLChiTietTour;
import model.bean.DLTour;
import model.bean.DLTourTrangChu;
import model.bean.Info;
import model.bo.ChiTietTourBO;
import model.bo.DLTourBO;

@SuppressWarnings("serial")
public class BookingTourAction extends ActionSupport  {
	private DLTourTrangChu tourTrangChu;
	private DLTour tour;
	private DLChiTietTour ctTour;
	private List<DLChiTietTour> listCtTour;
	private String maTour;
	private Info info;
	private String hinhAnh;
	
	public DLTour getTour() {
		return tour;
	}
	public void setTour(DLTour tour) {
		this.tour = tour;
	}
	public DLChiTietTour getCtTour() {
		return ctTour;
	}
	public void setCtTour(DLChiTietTour ctTour) {
		this.ctTour = ctTour;
	}	
	public String getMaTour() {
		return maTour;
	}
	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}
	public Info getInfo() {
		return info;
	}
	public void setInfo(Info info) {
		this.info = info;
	}
	public DLTourTrangChu getTourTrangChu() {
		return tourTrangChu;
	}
	public void setTourTrangChu(DLTourTrangChu tourTrangChu) {
		this.tourTrangChu = tourTrangChu;
	}
	public List<DLChiTietTour> getListCtTour() {
		return listCtTour;
	}
	public void setListCtTour(List<DLChiTietTour> listCtTour) {
		this.listCtTour = listCtTour;
	}
	public String getHinhAnh() {
		return hinhAnh;
	}
	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}
	@Override
	public String execute() throws Exception {
		if (maTour != null && !maTour.isEmpty()) {
			tourTrangChu = new DLTourBO().getInfoTtTour(maTour);
			tour = new DLTourBO().getInfo(maTour);
			ctTour = new ChiTietTourBO().getInfoGanNhat(maTour);
			listCtTour = new ChiTietTourBO().getTheoMaTour(maTour);
			if (tour == null) {
				info = new Info("Thông báo", "Chưa có lịch Tour!");
				return ERROR;
			} else {
				return SUCCESS;
			}
		} else {
			info = new Info("Lỗi hệ thống", "Không thể đặt Tour này!");
			return ERROR;
		}
	}
}
