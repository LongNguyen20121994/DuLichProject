package model.bean;

import java.sql.Date;

public class PhongThiThucTe {
	private String iDPhong;
	private String maMonThi;
	private String maPT;
	private Date ngayGioThi;

	public String getIDPhong() {
		return iDPhong;
	}

	public void setIDPhong(String iDPhong) {
		this.iDPhong = iDPhong;
	}

	public String getMaMonThi() {
		return maMonThi;
	}

	public void setMaMonThi(String maMonThi) {
		this.maMonThi = maMonThi;
	}

	public String getMaPT() {
		return maPT;
	}

	public void setMaPT(String maPT) {
		this.maPT = maPT;
	}

	public Date getNgayGioThi() {
		return ngayGioThi;
	}

	public void setNgayGioThi(Date ngayGioThi) {
		this.ngayGioThi = ngayGioThi;
	}

}
