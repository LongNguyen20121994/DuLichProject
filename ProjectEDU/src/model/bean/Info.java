package model.bean;

public class Info {
	private String tieuDe;
	private String noiDung;

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public Info(String tieuDe, String noiDung) {
		super();
		this.tieuDe = tieuDe;
		this.noiDung = noiDung;
	}

}
