package model.bo.khoi;

import java.util.List;

import model.bean.DoiTuongUT;
import model.dao.khoi.DoiTuongUTDAO;

public class DoiTuongUTBO {

	public List<DoiTuongUT> getAll() {
		return new DoiTuongUTDAO().getAll();
	}

	public boolean updateDoiTuongUT(DoiTuongUT dt) {
		return new DoiTuongUTDAO().updateDoiTuongUT(dt);
	}

	public boolean insertDoiTuongUT(DoiTuongUT dt) {
		return new DoiTuongUTDAO().insertDoiTuongUT(dt);
	}

	public boolean deleteListDoiTuongUT(List<String> listMaDT) {
		return new DoiTuongUTDAO().deleteListDoiTuongUT(listMaDT);
	}

}
