package controller.dulich;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLChiTietDatTour;
import model.bean.DLChiTietTour;
import model.bean.DLKhachHang;
import model.bean.DLTour;
import model.bean.DLTourTrangChu;
import model.bean.Info;
import model.bo.ChiTietTourBO;
import model.bo.DLTourBO;
import model.bo.KhachHangBO;

@SuppressWarnings("serial")
public class BookingTourAction extends ActionSupport  {
	private DLTourTrangChu tourTrangChu;
	private DLTour tour;
	private DLChiTietTour ctTour;
	private String maChiTietTour;
	private List<DLChiTietTour> listCtTour;
	private List<DLChiTietDatTour> listChiTietDatTour;
	private DLChiTietDatTour ctDatTour;
	private String maTour;
	private Info info;
	private String hinhAnh;
	private String btnTiep;
	private int soNguoiLon;
	
	private DLKhachHang kh;
	
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
	public DLKhachHang getKh() {
		return kh;
	}
	public void setKh(DLKhachHang kh) {
		this.kh = kh;
	}
	public String getBtnTiep() {
		return btnTiep;
	}
	public void setBtnTiep(String btnTiep) {
		this.btnTiep = btnTiep;
	}
	public int getSoNguoiLon() {
		return soNguoiLon;
	}
	public void setSoNguoiLon(int soNguoiLon) {
		this.soNguoiLon = soNguoiLon;
	}
	public List<DLChiTietDatTour> getListChiTietDatTour() {
		return listChiTietDatTour;
	}
	public void setListChiTietDatTour(List<DLChiTietDatTour> listChiTietDatTour) {
		this.listChiTietDatTour = listChiTietDatTour;
	}
	public DLChiTietDatTour getCtDatTour() {
		return ctDatTour;
	}
	public void setCtDatTour(DLChiTietDatTour ctDatTour) {
		this.ctDatTour = ctDatTour;
	}
	public String getMaChiTietTour() {
		return maChiTietTour;
	}
	public void setMaChiTietTour(String maChiTietTour) {
		this.maChiTietTour = maChiTietTour;
	}
	@Override
	public String execute() throws Exception {
		if (maTour != null && !maTour.isEmpty() && btnTiep == null) {
			tourTrangChu = new DLTourBO().getInfoTtTour(maTour);
			tour = new DLTourBO().getInfo(maTour);
			ctTour = new ChiTietTourBO().getInfoGanNhat(maTour);
			/*listCtTour = new ChiTietTourBO().getTheoMaTour(maTour);*/
			if (tour == null) {
				info = new Info("Thông báo", "Chưa có lịch Tour!");
				return ERROR;
			} else {
				return SUCCESS;
			}
		} else if(btnTiep != null){
			/*String timMax = new ChiTietDatTourBO().getMaxRecord();
			int max = 0;
			for(int i = 0; i < tourTrangChu.getSoNguoiLon(); i++) {
				if(timMax != null) {
					max = catChuoi(timMax) + i;
				}
				else {
					max = 1 + i;
				}
				ctDatTour = new DLChiTietDatTour();
				ctDatTour.setMaChiTietDatTour(taoMa(max));
				ctDatTour.setMaChiTietTour(maChiTietTour);
				listChiTietDatTour.add(ctDatTour);
				//ChiTietDatTourBO ctdattourbo = new ChiTietDatTourBO();
				if (ctdattourbo.insertTour(ctDatTour)) {
					
				}
			}
			return SUCCESS;*/
			String timMax = new KhachHangBO().getMaxRecord();
			int max = 0;
			if(timMax != null) {
				max = catChuoi(timMax);
			}
			else {
				max = 1;
			}
			kh.setMaKH("KHH"+taoMa(max));
			KhachHangBO khbo = new KhachHangBO();
			if (khbo.insertKhachHang(kh)) {
				btnTiep = null;
				info = new Info("Thông báo","<font style='color:blue;'>"+ kh.getHoTen()+" Đã đặt Tour thành công!</font><br/>");
				return SUCCESS;
			} else {
				info = new Info("Thông báo lỗi","<font style='color:red;'>"+ kh.getHoTen() +" Có lỗi trong quá trình thực hiện, vui lòng kiểm tra lại!</font><br/>");
				btnTiep = null;
				return ERROR;
			}
		}else {
			info = new Info("Lỗi hệ thống", "Không thể đặt Tour này!");
			return ERROR;
		}
	}

	public String taoMa(int max) {
		int i, n = max;
		for (i = 1; n > 10; i++)
			n /= 10;
		StringBuilder ma = new StringBuilder();
		for (int j = 0; j < 7 - i; j++)
			ma.append("0");
		return "" + ma.append(max);
	}
	
	public int catChuoi(String chuoi) {
		int so=Integer.parseInt(chuoi.substring(3, chuoi.length()));
		return so;
	}
}
