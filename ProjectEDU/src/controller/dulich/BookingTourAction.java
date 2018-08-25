package controller.dulich;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import model.bean.DLChiTietDatTour;
import model.bean.DLChiTietKhachHang;
import model.bean.DLChiTietTour;
import model.bean.DLHopDong;
import model.bean.DLHopDongChiTiet;
import model.bean.DLKhachHang;
import model.bean.DLTour;
import model.bean.DLTourTrangChu;
import model.bean.Info;
import model.bo.ChiTietKhachHangBO;
import model.bo.ChiTietTourBO;
import model.bo.DLTourBO;
import model.bo.HopDongBO;
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
	private boolean ptThanhToan;
	private DLChiTietKhachHang ctkh;
	private List<DLChiTietKhachHang> listCtkhNL;
	private List<DLChiTietKhachHang> listCtkhTE;
	private List<DLChiTietKhachHang> listCtkhTN;
	private List<DLChiTietKhachHang> listCtkhSS;
	private DLKhachHang kh;
	private DLHopDong hd;
	
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
	public boolean isPtThanhToan() {
		return ptThanhToan;
	}
	public void setPtThanhToan(boolean ptThanhToan) {
		this.ptThanhToan = ptThanhToan;
	}
	public DLChiTietKhachHang getCtkh() {
		return ctkh;
	}
	public void setCtkh(DLChiTietKhachHang ctkh) {
		this.ctkh = ctkh;
	}
	public List<DLChiTietKhachHang> getListCtkhNL() {
		return listCtkhNL;
	}
	public void setListCtkhNL(List<DLChiTietKhachHang> listCtkhNL) {
		this.listCtkhNL = listCtkhNL;
	}
	public List<DLChiTietKhachHang> getListCtkhTE() {
		return listCtkhTE;
	}
	public void setListCtkhTE(List<DLChiTietKhachHang> listCtkhTE) {
		this.listCtkhTE = listCtkhTE;
	}
	public List<DLChiTietKhachHang> getListCtkhTN() {
		return listCtkhTN;
	}
	public void setListCtkhTN(List<DLChiTietKhachHang> listCtkhTN) {
		this.listCtkhTN = listCtkhTN;
	}
	public List<DLChiTietKhachHang> getListCtkhSS() {
		return listCtkhSS;
	}
	public void setListCtkhSS(List<DLChiTietKhachHang> listCtkhSS) {
		this.listCtkhSS = listCtkhSS;
	}
	public DLHopDong getHd() {
		return hd;
	}
	public void setHd(DLHopDong hd) {
		this.hd = hd;
	}
	@Override
	public String execute() throws Exception {
		if (maTour != null && !maTour.isEmpty() && btnTiep == null) {
			tourTrangChu = new DLTourBO().getInfoTtTourBook(maTour);
			tour = new DLTourBO().getInfo(tourTrangChu.getMaTour());
			ctTour = new ChiTietTourBO().getInfoGanNhat(tourTrangChu.getMaTour());
			if (tour == null) {
				info = new Info("Thông báo", "Chưa có lịch Tour!");
				return ERROR;
			} else {
				return SUCCESS;
			}
		}if(btnTiep != null){
			String timMax = new KhachHangBO().getMaxRecord();
			int max = 0;
			if(timMax != null) {
				max = catChuoi(timMax) + 1;
			}
			else {
				max = 1;
			}
			kh.setMaKH("KHH"+taoMa(max));
			KhachHangBO khbo = new KhachHangBO();
			DLTourTrangChu tur1 = new DLTourBO().getInfoTtTourBook(maTour);
			
			// lấy thông tin tour
			DLTourBO dlbo = new DLTourBO();
			tour = dlbo.getInfo(tur1.getMaTour());

			if (khbo.insertKhachHang(kh)) {
				// Create session cho acc khach hang
				Map<String, Object> session = ActionContext.getContext().getSession();
				session.put("email", kh.getEmail());
				session.put("makh", kh.getMaKH());
				session.put("tieude", tour.getTieuDe());				
				
				HopDongBO hdbo = new HopDongBO();
				hd = new DLHopDong();
				timMax = hdbo.getMaxRecord();
				max = 0;
				if(timMax != null) {
					max = catChuoi(timMax) + 1;
				}
				else {
					max = 1;
				}
				
				// Tạo mã HD
				hd.setMaHopDong("MHD"+taoMa(max));
				hd.setTenHopDong("Hợp đồng đặt Tour");
				hd.setMaKH(kh.getMaKH());
				hd.setMaCtTour(maChiTietTour);
				hd.setGiaTien(tourTrangChu.getSoNguoiLon() * getGiaTien(tur1.getGiaVe()) + tourTrangChu.getSoTreEm()*Math.round((getGiaTien(tur1.getGiaVe()) * 0.7)*10)/10
						+ tourTrangChu.getSoTreNho()*Math.round((getGiaTien(tur1.getGiaVe()) * 0.5)*10)/10 + tourTrangChu.getSoSoSinh()*200000
						);
				if(ptThanhToan == true) {
					hd.setSoTienDc(hd.getGiaTien());
				}else {
					hd.setSoTienDc(Math.round((hd.getGiaTien() * 0.3)*10)/10);
				}
				tur1.setPtThanhToan(ptThanhToan);

				// Create thông tin hợp đồng
				DLHopDongChiTiet hdct= new DLHopDongChiTiet();
				hd.setDieuKhoan(hdct.createHopDong(tour, kh, tur1, hd));

				if(hdbo.insertHopDong(hd)) {
					ChiTietKhachHangBO ctkhbo = new ChiTietKhachHangBO();
					timMax = ctkhbo.getMaxRecord();
					max = 0;
					if(timMax != null) {
						max = catChuoi(timMax) + 1;
					}
					else {
						max = 1;
					}
					int i;
					if(tourTrangChu.getSoNguoiLon() > 1) {
						listCtkhNL = new ArrayList<DLChiTietKhachHang>(); 
						for (i = 0; i < tourTrangChu.getSoNguoiLon() - 1; i ++ ) {
							ctkh = new DLChiTietKhachHang();
							ctkh.setMaChiTiet("CTK"+taoMa(max+i));
							ctkh.setMaKH(kh.getMaKH());
							ctkh.setLoaiKH(1);
							listCtkhNL.add(ctkh);
						}
					}
					if(tourTrangChu.getSoTreEm() > 0) {
						listCtkhTE = new ArrayList<DLChiTietKhachHang>();
						for (i = 0; i < tourTrangChu.getSoTreEm(); i ++ ) {
							ctkh = new DLChiTietKhachHang();
							ctkh.setMaChiTiet("CTK"+taoMa(max+i + listCtkhNL.size()));
							ctkh.setMaKH(kh.getMaKH());
							ctkh.setLoaiKH(2);
							listCtkhTE.add(ctkh);
						}
					}
					if(tourTrangChu.getSoTreNho() > 0) {
						listCtkhTN = new ArrayList<DLChiTietKhachHang>();
						for (i = 0; i < tourTrangChu.getSoTreNho(); i ++ ) {
							ctkh = new DLChiTietKhachHang();
							ctkh.setMaChiTiet("CTK"+taoMa(max+i + listCtkhNL.size() +  listCtkhTE.size()));
							ctkh.setMaKH(kh.getMaKH());
							ctkh.setLoaiKH(3);
							listCtkhTN.add(ctkh);
						}
					}
					if(tourTrangChu.getSoSoSinh() > 0) {
						listCtkhSS = new ArrayList<DLChiTietKhachHang>();
						for (i = 0; i < tourTrangChu.getSoSoSinh(); i ++ ) {
							ctkh = new DLChiTietKhachHang();
							ctkh.setMaChiTiet("CTK"+taoMa(max+i + listCtkhNL.size() + listCtkhTE.size()+ listCtkhTN.size()));
							ctkh.setMaKH(kh.getMaKH());
							ctkh.setLoaiKH(4);
							listCtkhSS.add(ctkh);
						}
					}
					return SUCCESS;
				}else {
					return ERROR;
				}
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
		for (i = 1; n >= 10; i++)
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
	public double getGiaTien(String giaVe) {
		return Double.parseDouble(giaVe.substring(0,giaVe.length()-4));
	}
}
