package model.bo;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

import model.bean.DLTour;
import model.bean.DLTourTrangChu;
import model.dao.DLTourDAO;

public class DLTourBO {

	public String getMaxRecord() {
		return new DLTourDAO().getMaxRecord();
	}
	
	public boolean insertTour(DLTour tour) {
		// TODO Auto-generated method stub
		return new DLTourDAO().insertTour(tour);
	}
	
	public boolean updateTour(DLTour tour) {
		// TODO Auto-generated method stub
		return new DLTourDAO().updateTour(tour);
	}
	
	public List<DLTourTrangChu> getTop(String maLt) throws ParseException {
		return new DLTourDAO().getTop(maLt);
	}
	
	public List<DLTour> getAllByMaLoaiObject(String maLoai) {
		return new DLTourDAO().getAllByMaLoaiObject(maLoai);
	}
	
	public HashMap<String, String> getAllByMaLoaiTour(String maLoai) {
		return new DLTourDAO().getAllByMaLoaiTour(maLoai);
	}
	public HashMap<String, String> getAllBy() {
		return new DLTourDAO().getAllBy();
	}

	public DLTour getInfo(String maTour) {
		return new DLTourDAO().getInfo(maTour);
	}
	public DLTourTrangChu getInfoTtTour(String maTour) {
		return new DLTourDAO().getInfoTtTour(maTour);
	}
	
	public boolean deleteListTour(List<String> listMaTour) {
		return new DLTourDAO().deleteListTour(listMaTour);
	}
	
	public List<DLTour> getAll() {
		return new DLTourDAO().getAll();
	}
	
	public List<DLTour> getAllLikeName(String name) {
		return new DLTourDAO().getAllLikeName(name);
	}
}
