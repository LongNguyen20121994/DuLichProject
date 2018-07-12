package model.dulich;

/**
 * @author LongNT
 *
 */
public class Tour {
	private String maTour;

	private String tenTour;

	private double giaTour;

	public Tour() {

	}

	public Tour(String maTour, String tenTour, double giaTour) {
		this.maTour = maTour;
		this.tenTour = tenTour;
		this.giaTour = giaTour;
	}

	public String getMaTour() {
		return maTour;
	}

	public void setMaTour(String maTour) {
		this.maTour = maTour;
	}

	public String getTenTour() {
		return tenTour;
	}

	public void setTenTour(String tenTour) {
		this.tenTour = tenTour;
	}

	public double getGiaTour() {
		return giaTour;
	}

	public void setGiaTour(double giaTour) {
		this.giaTour = giaTour;
	}

}
