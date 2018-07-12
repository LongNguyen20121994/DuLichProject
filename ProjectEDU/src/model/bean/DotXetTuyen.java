package model.bean;

public class DotXetTuyen {
	private String maDotXT;
	private String tenDotXT;
	private boolean trangThai;

	public DotXetTuyen(){}
	
	public DotXetTuyen(String maDotXT, String tenDotXT, boolean trangThai){
		this.maDotXT = maDotXT;
		this.tenDotXT = tenDotXT;
		this.trangThai = trangThai;
	}
	
	public String getMaDotXT() {
		return maDotXT;
	}

	public void setMaDotXT(String maDotXT) {
		this.maDotXT = maDotXT;
	}

	public String getTenDotXT() {
		return tenDotXT;
	}

	public void setTenDotXT(String tenDotXT) {
		this.tenDotXT = tenDotXT;
	}

	public boolean isTrangThai() {
		return trangThai;
	}

	public void setTrangThai(boolean trangThai) {
		this.trangThai = trangThai;
	}

}
